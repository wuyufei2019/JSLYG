var dg;

$(function(){
	dg=$('#aqzf_fkpz_dg').datagrid({    
	method: "post",
    url:ctx+'/xzcf/ybcf/fkpz/list', 
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
  			{field : 'm1',title : '编号',sortable : false,width : 50},
			{field : 'm2',title : '当事人',sortable : false,width : 60,align : 'center'},
			{field : 'm4',title : '罚款总计（大写）',sortable : false,width : 40,align : 'center'},
			{field : 'm5',title : '类型',sortable : false,width : 40,align : 'center',
				formatter : function(value,row) {
					if(value == '1') {
						return '延期缴纳罚款';
					}else{
						return '分期缴纳罚款';
					}
				}
			},
			{field : 'jzrq',title : '延期（分期）日期',sortable : false,width : 40,align : 'center',
				formatter : function(value,row) {
					if(row.m5 == '1') {
						if(row.m6!=null&&row.m6!='') {
		              		var datetime=new Date(row.m6);
		              		return datetime.format('yyyy-MM-dd');
		              	}
					}else{
						if(row.m8!=null&&row.m8!='') {
		              		var datetime2=new Date(row.m8);
		              		return datetime2.format('yyyy-MM-dd');
		              	}
					}
				}
			},
			{field : 'm7',title : '期数',sortable : false,width : 20,align : 'center',
				formatter : function(value,row) {
					if(row.m5 == '1') {
						return '/';
					}else{
						return value;
					}
				}
			},
			{field : 'm9',title : '缴纳罚款（大写）',sortable : false,width : 40,align : 'center',
				formatter : function(value,row) {
					if(row.m5 == '1') {
		              	return '/';
					}else{
		              	return value;
					}
				}
			},
			{field : 'm10',title : '未缴纳罚款（大写）',sortable : false,width : 40,align : 'center',
				formatter : function(value,row) {
					if(row.m5 == '1') {
		              	return '/';
					}else{
		              	return value;
					}
				}
			}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
    onLoadSuccess: function(){
    	$(this).datagrid("autoMergeCells", [ 'm1' ]);
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqzf_fkpz_tb'
	});
	
});

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
			url:ctx+"/xzcf/ybcf/fkpz/delete/"+ids,
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
	openDialogView("查看缴批信息",ctx+"/xzcf/ybcf/fkpz/view/"+row.id,"800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改缴批信息",ctx+"/xzcf/ybcf/fkpz/update/"+row.id,"800px", "400px","");
}

//导出文书
function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	$.ajax({
		url:ctx+"/xzcf/ybcf/fkpz/export/tl/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}