var dg;
var d;
$(function() {
	dg = $('#ybcf_lasp_dg').datagrid({
			method : "get",
			url : ctx + '/xzcf/ybcf/lahsz/list',
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
					{field : 'number',title : '编号',sortable : false,width : 60,align : 'center'},
					{field : 'dsperson',title : '当事人',sortable : false,width : 60,align : 'center'},
					{field : 'ayname',title : '案由',sortable : false,width : 80,align : 'center'},
					{field : 'casesource',title : '案件来源',sortable : false,width : 50,align : 'center'},
					{field : 'filldate',title : '立案时间',sortable : false,width : 50,align : 'center',
						formatter : function(value,row) {
							if(value!=null&&value!='') {
			              		var datetime=new Date(value);
			              		return datetime.format('yyyy-MM-dd');
			              	}
						}
					},
					{field : 'caozuo',title : '恢复操作',sortable : false,width : 40,align : 'center',
						formatter : function(value, row, index) {
							return "<a style='margin:2px' class='btn btn-info btn-xs' onclick='hflasp("+row.id+")'>恢复</a>";
		              	}	 
					}
			] ],
			onDblClickRow : function(rowindex, rowdata,rowDomElement) {
				view();
			},
			onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
			},
			checkOnSelect : false,
			selectOnCheck : false,
			toolbar : '#ybcf_lasp_tb'
		});
});

//查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("审批信息查看",ctx + "/xzcf/ybcf/lasp/view/" + row.id, "80%","100%", "");

}

//恢复立案审批
function hflasp(id) {
	top.layer.confirm('您确定要恢复？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/xzcf/ybcf/lahsz/hf/"+id,
			success: function(data){
				layer.alert("恢复成功", {icon:6,title: '提示',offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
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
