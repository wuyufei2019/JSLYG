var dg;
var d;
var cxtype;
$(function(){
	dg=$('#erm_year_dg').datagrid({    
	method: "post",
    url:ctx+'/erm/train-plan/year/list',
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
        {field:'qynm',title:'企业名称',width:100,align:'center'},    
        {field:'depname',title:'部门',width:100,align:'center'},
        {field:'s1',title:'制表时间',width:100,align:'center',
			formatter : function(value, row, index) {
				if(value!='') {
					var datetime=new Date(value);
					return datetime.format('yyyy-MM-dd');
				}
			}
		},
        {field:'m14',title:'制表人',width:100,align:'center'},
        {field:'m15',title:'批准人',width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    onLoadSuccess:function(){
		if(usertype == '1'){
			$(this).datagrid("hideColumn",['qynm']);
		}else{
    	    if(cxtype!=null&&cxtype!="2"){
    		   $(this).datagrid("hideColumn",['qynm']);
    	    }else{
    		   $(this).datagrid("showColumn",['qynm']);
    	    }
		}
		$(this).datagrid("autoMergeCells",['qynm']);
      },
    toolbar:'#erm_year_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加年度训练计划信息",ctx+"/erm/train-plan/year/create/","100%", "100%","");
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
			url:ctx+"/erm/train-plan/year/delete/"+ids,
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
	
	openDialog("修改年度训练计划信息",ctx+"/erm/train-plan/year/update/"+row.id,"100%", "100%","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看年度训练计划信息",ctx+"/erm/train-plan/year/view/"+row.id,"100%", "100%","");
	
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

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   	{colval:'m1', coltext:'姓名'},
		   		{colval:'m2', coltext:'部门'},
		   		{colval:'m3', coltext:'职务'},
		   		{colval:'m4', coltext:'应急电话(手机)'},
		   		{colval:'m5', coltext:'固定电话'},
		   		{colval:'m6', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/erm/train-plan/year/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}