var dg;
var d;
$(function(){
	dg=$('#sgzn_hzsg_dg').datagrid({    
	method: "post",
    url:ctx+'/sgzn/hzsg/list', 
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
              {field:'m1',title:'事故单位名称',sortable:false,width:100,align:'center'},    
              {field:'m3',title:'事故时间',sortable:false,width:100,align:'center',
	      	      formatter : function(value, row, index) {
	              	  if(value!==null&&value!='') {
	              		  var datetime=new Date(value);
	              		   return datetime.format('yyyy-MM-dd hh:mm:ss');  
	              	  }
	          	  }
              },
              {field:'m4',title:'地点',sortable:false,width:100,align:'center',
            	  formatter : function(value, row, index) {
            		  return value+"县（市、区）"+row.m4_1+"镇（街道、园区）";
            	  }
              },
              {field:'m11',title:'经济损失（万元）',sortable:false,width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sgzn_hzsg_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加火灾事故信息",ctx+"/sgzn/hzsg/create/","1000px", "600px","");
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
			url:ctx+"/sgzn/hzsg/delete/"+ids,
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
	
	openDialog("修改火灾事故信息",ctx+"/sgzn/hzsg/update/"+row.id,"1000px", "600px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看火灾事故信息",ctx+"/sgzn/hzsg/view/"+row.id,"1000px", "600px","");
	
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
}


//导出word
function fileexportword(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/sgzn/hzsg/exportword/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}



//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'班次'},
			   		{colval:'m2', coltext:'接班时间'},
			   		{colval:'m3', coltext:'交班时间'},
			   		{colval:'m4', coltext:'人数'},
			   		{colval:'m5', coltext:'备注'}
		   	];

	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/zybc/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}