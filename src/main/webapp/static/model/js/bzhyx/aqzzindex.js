var dg;
var d;
$(function(){
	dg=$('#dg').datagrid({    
	method: "post",
    url:ctx+'/bzhyx/aqzz/list', 
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
        {field:'qyname',title:'企业名称',sortable:false,width:100}, 
        {field:'aqzzsum',title:'安全职责',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
    			return "<a onclick='openDialogView(\"安全职责\",\""+ctx+"/target/aqzz/index?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
        	}
        }, 
        {field:'aqzrsxfsum',title:'安全责任书下发',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
    			return "<a onclick='openDialogView(\"安全责任书下发\",\""+ctx+"/target/aqzrs/index?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
        	}
        }, 
        {field:'aqzrshzsum',title:'安全责任书回执',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
    			return "<a onclick='openDialogView(\"安全责任书回执\",\""+ctx+"/target/aqzrssc/index?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
        	}
        }, 
        {field:'aqcnssum',title:'安全承诺书',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
    			return "<a onclick='openDialogView(\"安全承诺书\",\""+ctx+"/target/aqcns/index?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
        	}
        }, 
        {field:'zt',title:'运行状态',sortable:false,width:50,align:'center',
		    formatter : function(value, row, index) {
		    	var a = "";
	        	if(row.aqzzsum != 0 || row.aqzrsxfsum != 0 || row.aqzrshzsum != 0 || row.aqcnssum != 0){
	        		a = "<a style='margin:2px' class='btn btn-primary btn-xs'>运行</a>";
	        	}else{
	        		a = "<a style='margin:2px' class='btn btn-danger btn-xs'>未运行</a>";
	        	}
	        	return a;
		    } 
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    },
    onLoadSuccess: function(){
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#tb'
	});
	
});

//创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}
