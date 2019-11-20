var dg;
var d;
var ryids;
$(function(){
	ryids=window.parent.getryids();
	dg=$('#jcjh_rychoose_dg').datagrid({    
	method: "get",
    url:ctx+'/aqzf/jcjh/rylist', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	pagination:false,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	scrollbarSize:0,
	singleSelect:true,
	striped:true,
    columns:[[    
		{field:'id',title:'id',checkbox:true,width:50,align:'center'},    
		{field:'ryfz',title:'姓名-组名',sortable:false,width:100,align:'center'}   
	]],
	checkOnSelect:true,
	selectOnCheck:false,
	onLoadSuccess:function(rowdata, rowindex, rowDomElement){
		$.each(rowdata.rows,function(i,row){
			if((','+ryids).indexOf(','+row.id+',')!=-1){
				$("#jcjh_rychoose_dg").datagrid('selectRow',i);
			}
		});
	},
    toolbar:'#jcjh_rychoose_tb'
	});
});


function searchry() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function getidname(){
	var row=$('#jcjh_rychoose_dg').datagrid('getChecked');
	var idname="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			idname = idname + row[i].id +"||"+row[i].ryfz+ ",";
		}
	}
	return idname;
}

