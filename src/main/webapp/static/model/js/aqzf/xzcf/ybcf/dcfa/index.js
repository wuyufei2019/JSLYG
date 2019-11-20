var dg;

$(function(){
	dg=$('#aqzf_dcfa_dg').datagrid({    
	method: "post",
    url:ctx+'/xzcf/ybcf/dcfa/list', 
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
		{field: 'id', title: 'id', checkbox: true, width: 50, align: 'center'},
		{field: 'm1', title: '编号', sortable: false, width: 60},
		{field: 'dsperson', title: '企业名称', sortable: false, width: 60, align: 'center'},
		{
			field: 'm2', title: '调查开始时间', sortable: false, width: 60, align: 'center',
			formatter: function (value) {
				if (value != null && value != '') {
					var datetime = new Date(value);
					return datetime.format('yyyy-MM-dd hh:mm:ss');
				}
			}
		},
		{
			field: 'm3', title: '调查结束时间', sortable: false, width: 60, align: 'center',
			formatter: function (value) {
				if (value != null && value != '') {
					var datetime = new Date(value);
					return datetime.format('yyyy-MM-dd hh:mm:ss');
				}
			}
		},
		{field: 'm4', title: '调查人员', sortable: false, width: 40, align: 'center'},
		{field: 'm7', title: '部门负责人', sortable: false, width: 40, align: 'center'},
		{field: 'm8', title: '分管领导', sortable: false, width: 40, align: 'center'}
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
    toolbar:'#aqzf_dcfa_tb'
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
			url:ctx+"/xzcf/ybcf/dcfa/delete/"+ids,
			success: function(data){
				layer.alert(data, {icon:6,title: '提示',offset: 'rb',shade: 0,time: 2000}); 
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
	
	openDialog("修改调查方案信息",ctx+"/xzcf/ybcf/dcfa/update/"+row.id,"800px", "600px","");
	
}

//初始化
function onloadEditor(){

	var KConfigForFile = {
			uploadJson : ctx + '/kindeditor/uploadFile.shtml',
			fileManagerJson : ctx + '/kindeditor/manager.shtml',
			allowFileManager : false
		};
	
	var KEditorConfig= {
			uploadJson : ctx + '/kindeditor/uploadFile.shtml',
			fileManagerJson : ctx + '/kindeditor/manager.shtml',
			allowFileManager : false,
			filterMode: true,
			afterBlur: function(){this.sync();},
			afterChange : function() {
				  
				 },
		        pasteType: 1,
		        afterCreate: function () {
		            var self = this;
		            KindEditor.ctrl(document, 13, function () {
		                self.sync();
		                KindEditor('form[name=form1]')[0].submit();
		            });
		            KindEditor.ctrl(self.edit.doc, 13, function () {
		                self.sync();
		                KindEditor('form[name=form1]')[0].submit();
		            });
		        }
		};

	var upEditor = KindEditor.editor(KConfigForFile);
	
	//渲染富文本
	window.editor = KindEditor.create(
		'#M5',
		$.extend({},KEditorConfig,{
    		width: '700px',
    		items:[ 'source', '|', 'undo', 'redo', '|', 'justifyleft','justifycenter','justifyright','|',
					'fontsize','forecolor','hilitecolor','bold','italic','|','quickformat','|','image','|','link','fontname','fullscreen'
				  ]
    	 })
	);
 
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看调查方案信息",ctx+"/xzcf/ybcf/dcfa/view/"+row.id,"800px", "600px","");
	
}

function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	$.ajax({
		url:ctx+"/xzcf/ybcf/dcfa/export/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}