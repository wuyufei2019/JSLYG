var dg;
var d;
var jcdids;
$(function(){
	jcdids=window.parent.getjcdids();
	dg=$('#yhpc_jcdchoose_dg').datagrid({
		method: "get",
	    url:ctx+'/yhpc/bcrw/jcdlist', 
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
		scrollbarSize:0,
		singleSelect:true,
		checkOnSelect:true,
		selectOnCheck:false,
	    columns:[[    
	        {field:'id',title:'id',checkbox:true,align:'center'},
	        {field:'name',title:'检查点',width:100},
	        {field:'type',title:'类型',width:100,
			formatter:function (value) {
				return value==2?'自定义点':'风险点';
            }},
	        {field:'fxfj',title:'风险分级',width:60,
	        	formatter : function(value, row, index) {
	        		if(value=='1'){
	        			return '重大';
	        		}else if(value=='2'){
	        			return '较大';
	        		}else if(value=='3'){
	        			return '一般';
	        		}else if(value=='4'){
	        			return '低';
	        		}
	        	}
	        },
	        {field:'areaname',title:'责任部门',width:60}
	    ]],
		onLoadSuccess:function(rowdata, rowindex, rowDomElement){
            var arryids=jcdids.split(",");
            $.each(rowdata.rows,function(i,row){
                if(arryids.indexOf(row.id+"")!=-1){
                    $("#yhpc_jcdchoose_dg").datagrid('selectRow',i);
                }
            });
		},
	    toolbar:'#yhpc_jcdchoose_tb'
		});
});


function searchjcd() {
	var obj = $("#searchFrom").serializeObject();
    obj.zzbm=$("#zzbm").combotree('getText');
	dg.datagrid('load', obj);
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function getidname2(){
	var row=$('#yhpc_jcdchoose_dg').datagrid('getChecked');
	var idname="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			idname = idname + row[i].id+"-"+row[i].type +"||"+row[i].name+"||"+row[i].area+"||"+row[i].bindcontent+"||"+row[i].type+ ",";
		}
	}
	return idname;
}


