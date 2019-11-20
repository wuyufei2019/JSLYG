var dg;

$(function(){
	dg=$('#aqzf_fksp_dg').datagrid({    
	method: "post",
    url:ctx+'/xzcf/ybcf/fksp/list', 
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
  			{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
  			{field : 'm1',title : '编号',sortable : false,width : 70},
			{field : 'm2',title : '案件名称',sortable : false,width : 100,align : 'center'},
			{field : 'm4',title : '当事人（单位或个人）',sortable : false,width : 50,align : 'center'},
			{field : 'm5',title : '地址',sortable : false,width : 100,align : 'center'},
			{field : 'caozuo',title : '添加操作',sortable : false,width : 50,align : 'center',
				formatter : function(value, row, index) {
					return	"<a  class='btn btn-info btn-xs' onclick='addJfpz("
					+ row.id1 + ")'>缴罚批准</a>";
					
              	}	 
			}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
    onLoadSuccess: function(){
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqzf_fksp_tb'
	});
	
});

//添加缴款批准
function addJfpz(id) {
	openDialog("添加缴款批准",ctx+"/xzcf/ybcf/fkpz/create/"+id,"800px", "400px","");
}

//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}

//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids="";
	for(var i=0;i<row.length;i++){
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/xzcf/ybcf/fksp/delete/"+ids,
			success: function(data){
				layer.alert(data, {icon:6,title: '提示',offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
 
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看缴审信息",ctx+"/xzcf/ybcf/fksp/view/"+row.id,"800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改缴审信息",ctx+"/xzcf/ybcf/fksp/update/"+row.id,"800px", "400px","");
}

//导出文书
function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	$.ajax({
		url:ctx+"/xzcf/ybcf/fksp/export/tl/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}