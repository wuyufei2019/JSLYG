var dg;
var d;
$(function() {
	dg = $('#table_dg').datagrid(
			{
				method : "post",
				url : ctx + '/aqzf/yhzz/list',
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
						{field : 'id',title : 'id',checkbox:true,width : 50,align : 'center'},
						{field : 'qyname',title : '公司名称',sortable : false,width : 50},
						{field : 'number',title : '检查记录编号',sortable : false,width : 50,align : 'center'},
						{field : 'name',title : '问题隐患',sortable : false,width : 200,align : 'center'},
						{field : 'person1',title : '整改人',sortable : false,width : 40,align : 'center'},
						{field : 'person2',title : '负责人',sortable : false,width : 40,align : 'center'},
				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					view();
				},
				onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
					$(this).datagrid("autoMergeCells", [ 'qyname','number' ]);
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#dg_tb'
			});

});

//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}
	var ids="";
	for(var i=0;i<row.length;i++){
//		if(row[i].panduan == "1"){
//			layer.msg(row[i].m1+"，"+row[i].qyname+"已添加检查方案不得删除，请重新勾选！",{time: 3000});
//			return;
//		}
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}
	
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/aqzf/jcjh/delete/"+ids,
			success: function(data){
				layer.alert(data, {icon:6,title: '提示',offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'计划时间'},
			   		{colval:'qyname', coltext:'公司名称'},
			   		{colval:'wgname', coltext:'企业所属分组'},
			   		{colval:'zfry', coltext:'执法人员'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/aqzf/jcjh/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}

//双随机
function add(u) {
	openDialog("添加检查计划", ctx + "/aqzf/jcjh/create/", "80%", "100%", "");
}

//弹窗修改
function upd() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	if(row.panduan == "1"){
		layer.msg(row.m1+"，"+row.qyname+"已添加检查方案不得修改！",{time: 3000});
		return;
	}else{
		openDialog("修改计划信息", ctx + "/aqzf/jcjh/update/" + row.id, "800px","400px", "");
	}
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
	openDialogView("信息查看",ctx + "/aqzf/yhzz/view/" + row.id, "90%", "70%" );
}

//重置
function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}
