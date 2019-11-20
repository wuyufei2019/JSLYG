var dg;
var d;
$(function() {
	dg = $('#ybcf_ylasp_dg').datagrid({
		method : "get",
		url : ctx + '/xzcf/ybcf/ylasp/list',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		striped : true,
		pagination : true,
		rownumbers : true,
		nowrap : false,
		pageNumber : 1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		scrollbarSize : 5,
		singleSelect : true,
		striped : true,
		columns : [ [
				{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
				{field : 'dsperson',title : '当事人',sortable : false,width : 60,align : 'center'},
				{field : 'ayname',title : '案由',sortable : false,width : 80,align : 'center'},
				{field : 'casesource',title : '案件来源',sortable : false,width : 50,align : 'center'},
				{field : 'filldate',title : '预立案时间',sortable : false,width : 50,align : 'center',
					formatter : function(value,row) {
						if(value!=null&&value!='') {
		              		var datetime=new Date(value);
		              		return datetime.format('yyyy-MM-dd');
		              	}
					}
				},
				{field : 'ylaflag',title : '操作',sortable : false,width : 60,align : 'center',
					formatter : function(value, row, index) {
						if(value=='0'){
							return "<a style='margin:2px' class='btn btn-info btn-xs' onclick='addlasp("+row.id+")'>立案</a>";
						}else{
							return "<a style='margin:2px' class='btn btn-default btn-xs'>已立案</a>";
						}
					}
				}] ],
				onDblClickRow : function(rowdata, rowindex,rowDomElement) {
					view();
				},
				onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#ybcf_ylasp_tb'
			});
});

// 添加预立案审批记录
function add() {
	openDialog("添加预立案审批", ctx + "/xzcf/ybcf/ylasp/create", "80%","100%", "");
}

// 删除
function del() {
	var row = dg.datagrid('getChecked');
	if (row == null || row == '') {
		layer.msg("请至少勾选一行记录！", {
			time : 1000
		});
		return;
	}

	var ids = "";
	for (var i = 0; i < row.length; i++) {
		if (ids == "") {
			ids = row[i].id;
		} else {
			ids = ids + "," + row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {
		icon : 3,
		title : '提示'
	}, function(index) {
		$.ajax({
			type : 'get',
			url : ctx + "/xzcf/ybcf/ylasp/delete/" + ids,
			success : function(data) {
				layer.alert(data, {
					offset : 'rb',
					shade : 0,
					time : 2000
				});
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
}

// 弹窗修改
function upd() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	layer.open({
	    type: 2,  
	    shift: 1,
	    area: ['80%', '100%'],
	    title: "修改预立案审批记录",
	    maxmin: false, 
	    content: ctx + "/xzcf/ybcf/ylasp/update/" + row.id ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];
	         var inputForm = body.find('#inputForm');
	         iframeWin.contentWindow.doSubmit();
	         //window.location.reload();
		  },
		  cancel: function(index){ 
	     }
	}); 
}

// 查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("预立案审批信息查看",ctx + "/xzcf/ybcf/ylasp/view/" + row.id, "80%","100%", "");

}

// 创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//添加立案审批记录
function addlasp(ylaid) {
	top.layer.confirm('你确定要立案？', {
		icon : 3,
		title : '提示'
	}, function(index) {
		$.ajax({
			type : 'get',
			url : ctx + "/xzcf/ybcf/ylasp/createlasp/"+ylaid,
			success : function(data) {
				layer.alert(data, {
					offset : 'rb',
					shade : 0,
					time : 2000
				});
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
}
