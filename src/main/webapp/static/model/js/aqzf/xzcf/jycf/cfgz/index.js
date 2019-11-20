var dg;

$(function(){
	dg=$('#jycf_cfgz_dg').datagrid({    
	method: "post",
    url:ctx+'/xzcf/jycf/cfgz/list', 
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
  			{field : 'm0',title : '编号',sortable : false,width : 70},
			{field : 'm1',title : '企业名称',sortable : false,width : 80,align : 'center'},
			{field : 'm7',title : '行政处罚',sortable : false,width : 130,align : 'center'},
			{field : 'm10',title : '告知时间',sortable : false,width : 60,align : 'center',
				formatter : function(value, row, index) {
                  	if(value!=null&&value!='') {
                  		var datetime=new Date(value);
                  		return datetime.format('yyyy-MM-dd');
                  	}
              	}	 
			},
			{field : 'caozuo',title : '添加操作',sortable : false,width : 80,align : 'center',
				formatter : function(value, row, index) {
					var a ='';
					if(row.csflag == '1'){
						a += "<a class='btn btn-default btn-xs'>陈述申辩</a>";
					}else{
						a += "<a class='btn btn-info btn-xs' onclick='addCssb("
							+ row.id + ")'>陈述申辩</a>";
					}
					
					if(row.jdflag == '1'){
						a += "<a class='btn btn-default btn-xs' style='margin-left:10px;'>处罚决定</a>";
					}else{
						a += "<a class='btn btn-info btn-xs' style='margin-left:10px;' onclick='addCfjd("
							+ row.id + ")'>处罚决定</a>";
					}
					return a;
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
    toolbar:'#jycf_cfgz_tb'
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
			url:ctx+"/xzcf/jycf/cfgz/delete/"+ids,
			success: function(data){
				layer.alert(data, {icon:6,title: '提示',offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
 
}

//添加
function add() {
	openDialog("添加简易处罚告知信息",ctx+"/xzcf/jycf/cfgz/create","800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改简易处罚告知信息",ctx+"/xzcf/jycf/cfgz/update/"+row.id,"800px", "400px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看简易处罚告知信息",ctx+"/xzcf/jycf/cfgz/view/"+row.id,"800px", "400px","");
	
}

//导出告知书word
function fileexportgzs(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/xzcf/jycf/cfgz/export/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}


function addCssb(id) {
	openDialog("添加陈述申辩信息",ctx+"/xzcf/jycf/cssb/create/"+id,"800px", "400px","");
}

function addCfjd(id) {
	openDialog("添加处罚决定信息",ctx+"/xzcf/jycf/cfjd/create/"+id,"800px", "400px","");
}


//导出陈述申辩
function fileexportcssb(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	$.ajax({
		url:ctx+"/xzcf/jycf/cssb/export/gz/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}

//导出陈述申辩
function fileexportjds(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	$.ajax({
		url:ctx+"/xzcf/jycf/cfjd/export/gz/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}
