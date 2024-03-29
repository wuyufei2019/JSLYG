var dg;
var d;
var con1;
$(function(){   
	dg=$('#dg').datagrid({    
		nowrap:false,
	method: "post",
	url:ctx+'/bis/aqpxxx/list',
	queryParams : (parent.P_HeaderType=="expiration"?{isdq : 0 }:null),
	fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	checkOnSelect:false,
	selectOnCheck:false,
    columns:[[    
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
        {field:'M1',title:'安全培训人员姓名',sortable:false,width:100},    
        {field:'status',title:'人员在职状态',sortable:false,align:'center',width:60,
        	formatter : function(value,row){
    		if(value==2) return value='离职';
    		else return value='在职';
    	},styler :function(value){
    		if(value==2) return 'background-color:rgb(249, 156, 140);'
    	} },    
        {field:'M2',title:'安全培训人员类别',sortable:false,width:100,align:'center'},
        {field:'M3',title:'安全培训人员职务',sortable:false,width:100,align:'center'},
        {field:'m10',title:'证书名称',sortable:false,width:100,align:'center'},
        {field:'m11',title:'证书编号',sortable:false,width:100,align:'center'},
        {field:'M4',title:'最近培训时间',sortable:true,width:100,align:'center',
			formatter: function(value,row,index){
				if(value!=null){
					var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');    
				}  
			}},
        {field:'M5',title:'下次培训时间',sortable:true,width:100,align:'center',
				formatter: function(value,row,index){
					if(value!=null){
						var datetime = new Date(value);  
						 return datetime.format('yyyy-MM-dd');    
					}  
				},
		    	styler: function(value, row, index){
		    		var nowhm=(new Date()).getTime();
		    		var m5time=value;
		    		if(m5time){//时间存在的情况下
		    			if(nowhm<=m5time){//未过期（3个月以内）
			        		var cha=(m5time-nowhm)/1000/60/60/24;
			        		if(cha<=90) return 'background-color:rgb(255,240,245);';
			    		}else{
			    			return 'background-color:rgb(249, 156, 140);';
			    		}
		    		}
		    	}
        },
        {field:'m9',title:'有效期',sortable:true,width:100,align:'center',
			formatter: function(value,row,index){
				if(value!=null){
					var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');    
				}  
			},
	    	styler: function(value, row, index){
	    		var nowhm=(new Date()).getTime();
	    		var m9time=value;
	    		if(m9time){//时间存在的情况下
	    			if(nowhm<=m9time){//未过期（3个月以内）
		        		var cha=(m9time-nowhm)/1000/60/60/24;
		        		if(cha<=90) return 'background-color:rgb(255,240,245);';
		    		}else{
		    			return 'background-color:rgb(249, 156, 140);';
		    		}
	    		}
	    	}
        },
        {field:'M6',title:'发证机关',sortable:true,width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    }, onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['m1']);
    },
    onBeforeLoad:function(param){
    	if(f!=''&&f=='sys'){
    		$("#bis_aqpx_cx_m3").combobox('setValue','0');
    		param.M3=0;
    	 }
    },
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
	
	
	$.ajax({
		type:"GET",
        url:ctx+"/bis/aqpxxx/aqpxTimeEnd",
		success: function(data){
			if(data!='null'){
				layer.msg(data,{time: 3000});
			}
		}
	});
	
	
});

//添加
function add() {
	openDialog("添加安全培训信息",ctx+'/bis/aqpxxx/create',"800px","400px","");
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
			ids=row[i].ID;
		}else{
			ids=ids+","+row[i].ID;
		}
	}
	
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/bis/aqpxxx/delete/"+ids,
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

//修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改安全培训信息",ctx+'/bis/aqpxxx/update/'+row.ID,"800px", "400px","");
}

//保存
function saveBtn(){
	if($("#bis_aqpxxx_form_mainform").form('validate')){
		$.post(ctx+url,$("#bis_tzsbxx_form_mainform").serializeObject(),function(data){
			parent.$.messager.show({ title : "提示",msg: data, position: "bottomRight" });
			$("#bis_aqpxxx_add_div").window('destroy');
			dg.datagrid('reload');
		});
	}
}	


//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看安全培训信息",ctx+'/bis/aqpxxx/view/'+row.ID,"800px", "400px","");
}

//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}
//清空
function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
	search();
}
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.excolumn=[[    
				        {field:'colval',title:'colval',checkbox:true,width:50,align:'center'},    
				        {field:'coltext',title:'列名',width:100,align:'center'}
				    ]];
	window.exdata=[
			   		{colval:'m1', coltext:'姓名'},
			   		{colval:'m2', coltext:'安全人员类别'},
			   		{colval:'m3', coltext:'职务'},
			   		{colval:'m4', coltext:'最近培训时间'},
			   		{colval:'m5', coltext:'下次培训时间'},
			   		{colval:'m9', coltext:'有效期'},
			   		{colval:'m10', coltext:'证书名称'},
			   		{colval:'m11', coltext:'证书编号'},
			   		{colval:'m6', coltext:'发证机关'},
			   		{colval:'m7', coltext:'备注'},
		   	];
	window.colval="";
	window.coltext="";
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/bis/aqpxxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}