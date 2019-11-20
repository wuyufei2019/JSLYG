var dg;

$(function(){
	dg=$('#aqzf_cfcl_dg').datagrid({    
	method: "post",
    url:ctx+'/aqzf/cfcl/list?id1='+id1+"&version="+version,
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
			{field : 'm1',title : '处罚档次',sortable : false,width : 40},
			{field : 'm2',title : '裁量幅度',sortable : false,width : 100,align : 'center'},
			{field : 'e',title : 'E值（元）',sortable : false,width : 40,align : 'center',
           	  formatter : function(value, row, index) {
           		  	return row.m3+"~"+row.m4;  
            	}	 
			}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        upd();
    },
    onLoadSuccess: function(){
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqzf_cfcl_tb'
	});
	
});

//添加
function add(version) {
	openDialog("添加处罚裁量",ctx+"/aqzf/cfcl/create/"+id1+"?version="+version,"750px", "300px","");
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
			url:ctx+"/aqzf/cfcl/delete/"+ids,
			success: function(data){
				parent.layer.alert(data, {icon:6,title: '提示',offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
 
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改处罚裁量",ctx+"/aqzf/cfcl/update/"+row.id,"750px", "300px","");
	
}

