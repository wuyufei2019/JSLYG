var dg;

$(function(){
	dg=$('#ybcf_dcbg_dg').datagrid({    
	method: "post",
    url:ctx+'/xzcf/ybcf/dcbg/list', 
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
  			{field : 'bgbh',title : '编号',sortable : false,width : 60},
			{field : 'qyname',title : '当事人',sortable : false,width : 80,align : 'center',
                formatter: function (value,row,index) {
                    if(row.cfdxlx == '2') {
                        return row.cfryname;
                    }else {
                        return row.qyname;
                    }
                }
            },
			{field : 'casename',title : '案件名称',sortable : false,width :80,align : 'center'},
			{field : 'dcr',title : '调查人',sortable : false,width :60,align : 'center',
				formatter: function(value,row,index){
					if(row.enforcer2){
						return row.enforcer1+"、"+row.enforcer2;
					}
					return row.enforcer1;
				}
			},
			{field : 'xzcf',title : '行政处罚',sortable : false,width : 80,align : 'center'}
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
    toolbar:'#ybcf_dcbg_tb'
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
			url:ctx+"/xzcf/ybcf/dcbg/delete/"+ids,
			success: function(data){
				layer.alert(data, {icon:6,title: '提示',offset: 'rb',shade: 0,time: 2000}); 
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
	
	openDialog("修改调查报告信息",ctx+"/xzcf/ybcf/dcbg/update/"+row.id,"80%", "100%","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看调查报告信息",ctx+"/xzcf/ybcf/dcbg/view/"+row.id,"80%", "100%","");
	
}

function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	$.ajax({
		url:ctx+"/xzcf/ybcf/dcbg/export/dc/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}