var dg;
var d;
$(function(){
	dg=$('#zrzh_shxx_dg').datagrid({    
	method: "post",
    url:ctx+'/zrzh/shxx/list', 
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
              {field:'m1',title:'灾害区域',sortable:false,width:100,align:'center'},    
              {field:'m3',title:'灾情时间',sortable:false,width:100,align:'center',
	      	      formatter : function(value, row, index) {
	              	  if(value!==null&&value!='') {
	              		  var datetime=new Date(value);
	              		   return datetime.format('yyyy-MM-dd hh:mm:ss');  
	              	  }
	          	  }
              },
              {field:'m4',title:'灾害地点',sortable:false,width:100,align:'center',
            	  formatter : function(value, row, index) {
            		  return value+"县（市、区）";
            	  }
              },
              {field:'m2_1',title:'灾害类别',sortable:false,width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#zrzh_shxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加水旱信息",ctx+"/zrzh/shxx/create/","1000px", "600px","");
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
			url:ctx+"/zrzh/shxx/delete/"+ids,
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
	
	openDialog("修改水旱信息",ctx+"/zrzh/shxx/update/"+row.id,"1000px", "600px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看水旱信息",ctx+"/zrzh/shxx/view/"+row.id,"1000px", "600px","");
	
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
		url:ctx+"/zrzh/shxx/exportword/"+row.id,
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