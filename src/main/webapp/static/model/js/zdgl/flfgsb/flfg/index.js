var dg;
var d;
$(function(){
	dg=$('#zdgl_flfg_dg').datagrid({    
	method: "post",
    url:ctx+'/zdgl/flfg/list?qyid='+qyid,
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
        {field:'m1_1',title:'大类别',sortable:false,width:30,
        	formatter : function(value, row, index) {
	          	if(value=='1') {
	          		return '法律';  
	          	}else if(value=='2'){
	          		return '法规';  
	          	}else if(value=='3'){
	          		return '规章';  
	          	}else if(value=='4'){
	          		return '地方性法规';  
	          	}else if(value=='5'){
	          		return '政府文件';  
	          	}else if(value=='6'){
	          		return '标准规范';  
	          	}else if(value=='7'){
	          		return '其他';  
	          	}
          	} 
        },
        {field:'lb',title:'小类别',sortable:false,width:30},
        {field:'m2',title:'法律法规名称',sortable:false,width:90,align:'center',
        	formatter : function(value, row, index) {
	          	return "<a onclick='view2("+row.id+")'>"+value+"</a>";
          	} 
        },
        {field:'m3',title:'颁布单位',sortable:false,width:60,align:'center'},
        {field:'m4',title:'文件编号',sortable:false,width:60,align:'center'},
        {field:'m5',title:'发布日期',sortable:false,width:40,align:'center',
	    	formatter : function(value, row, index) {
	          	if(value!==null&&value!='') {
	          		var datetime=new Date(value);
	          		 return datetime.format('yyyy-MM-dd');  
	          	}	
          	} 
        },
        {field:'m6',title:'实施日期',sortable:false,width:40,align:'center',
      	    formatter : function(value, row, index) {
            	if(value!==null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM-dd');  
            	}
        	} 
        },
        {field:'lrname',title:'录入人员',sortable:false,width:40,align:'center'},
        {field:'s1',title:'录入时间',sortable:false,width:50,align:'center',
      	    formatter : function(value, row, index) {
            	if(value!==null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM-dd hh:mm:ss');  
            	}
        	} 
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#zdgl_flfg_tb'
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
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/zdgl/flfg/delete/"+ids,
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

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看法律法规库信息",ctx+"/zdgl/flfg/view/"+row.id,"800px", "400px","");
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	search();
}

//弹窗增加
function add() {
	openDialog("添加法律法规库信息",ctx+"/zdgl/flfg/create/","800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改法律法规库信息",ctx+"/zdgl/flfg/update/"+row.id,"800px", "400px","");
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	                {colval:'m1_1', coltext:'大类别'},
			   		{colval:'lb', coltext:'小类别'},
			   		{colval:'m2', coltext:'法律法规名称'},
			   		{colval:'m3', coltext:'颁布单位'},
			   		{colval:'m4', coltext:'文件编号'},
			   		{colval:'m5', coltext:'发布日期'},
			   		{colval:'m6', coltext:'实施日期'},
			   		{colval:'m7', coltext:'摘要'},
			   		{colval:'lrname', coltext:'录入人员'},
			   		{colval:'s1', coltext:'录入时间'}
			   	   ];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/zdgl/flfg/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}

//在线查看文件
function view2(id){
	window.open(ctx+"/zdgl/flfg/view2/"+id, "安全法律法规");
}

//类别管理
function lbgl(){
	openDialogView("类别分类管理",ctx+"/zdgl/flfg/lbgl/","500px", "400px","");
}
