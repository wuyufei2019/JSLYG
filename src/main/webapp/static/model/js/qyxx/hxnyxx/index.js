var dg;
var d;
$(function(){
	dg=$('#bis_hxnyxx_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/hxnyxx/list', 
    fit : true,
	fitColumns : true,
	border : false,
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
              {field:'ID',title:'id',checkbox:true,width:50,align:'center'},
              {field:'m1',title:'能源名称',sortable:false,width:100,align:'center'},    
              {field:'m2',title:'年用量（t/m3）',sortable:true,width:100,align:'center'},
              {field:'m3',title:'最大储存量（t/m3）',sortable:true,width:100,align:'center'},
              {field:'m4',title:'涉及工艺',sortable:false,width:100,align:'center'},
              {field:'m5',title:'是否进行安全评价',sortable:true,width:100,align:'center',
            	  formatter : function(value,row,index){
                		if(value=='0') return value='否';
                		if(value=='1') return value='是';
                		
              }},
              {field:'m6',title:'是否设置监控设施',sortable:true,width:100,align:'center',
                  formatter : function(value,row,index){
                    	if(value=='0') return value='否';
                    	if(value=='1') return value='是';
                    		
               }}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_hxnyxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加化学能源信息",ctx+"/bis/hxnyxx/create/","800px", "400px","");
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
			url:ctx+"/bis/hxnyxx/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
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
	
	openDialog("修改化学能源信息",ctx+"/bis/hxnyxx/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看化学能源信息",ctx+"/bis/hxnyxx/view/"+row.ID,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'能源名称'},
			   		{colval:'m2', coltext:'年用量（t/m3）'},
			   		{colval:'m3', coltext:'最大储存量（t/m3）'},
			   		{colval:'m4', coltext:'涉及工艺'},
			   		{colval:'m5', coltext:'是否进行安全评价'},
			   		{colval:'m6', coltext:'是否设置监控设施'},
			   		{colval:'m8', coltext:'设置监控设施内容'},
			   		{colval:'m7', coltext:'备注'},
		   	];

	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/hxnyxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}