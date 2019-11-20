var dg;
var d;
var qyids;
var sd;
var hylx;
$(function(){
	qyids=window.parent.getqyids();
	sd=window.parent.getsd();
	hylx=window.parent.gethylx();
	if(sd == '' || sd == null){
		sd = 'flag';
	}
	if(hylx == '' || hylx == null){
		hylx = 'flag';
	}
	dg=$('#jcjh_qychoose_dg').datagrid({    
	method: "get",
    url:ctx+'/aqzf/jcjh/qylist?sd='+sd+'&hylx='+hylx, 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	pagination:false,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	scrollbarSize:0,
	singleSelect:true,
	striped:true,
    columns:[[    
		{field:'id',title:'id',checkbox:true,width:50,align:'center'},    
		{field:'m1',title:'企业名称',sortable:false,width:100,align:'center',
			formatter : function(value, row, index) {
              	if(value!='') {
              		 return value + " — " + row.wgname;  
              	}
          	}	
		}   
	]],
	checkOnSelect:true,
	selectOnCheck:false,
	onLoadSuccess:function(rowdata, rowindex, rowDomElement){
		$.each(rowdata.rows,function(i,row){
			if((','+qyids).indexOf(','+row.id+',')!=-1){
				$("#jcjh_qychoose_dg").datagrid('selectRow',i);
			}
		});
	},
    toolbar:'#jcjh_qychoose_tb'
	});
});


function searchqy() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}
function getidname(){
	var row=$('#jcjh_qychoose_dg').datagrid('getChecked');
	var idname="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			idname = idname + row[i].id +"||"+row[i].m1 +"||"+row[i].wgname + ",";
		}
	}
	return idname;
}

