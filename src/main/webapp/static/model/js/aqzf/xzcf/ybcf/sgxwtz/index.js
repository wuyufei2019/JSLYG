var dg;

$(function(){
	dg=$('#aqzf_sgxwtz_dg').datagrid({    
	method: "post",
    url:ctx+'/xzcf/ybcf/sgxwtz/list', 
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
  			{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
  			{field : 'm1',title : '调查主题',sortable : false,width : 110},
			{field : 'qyname',title : '企业名称',sortable : false,width : 100,align : 'center'},
			{field : 'm2',title : '询问时间',sortable : false,width : 60,align : 'center',
				formatter : function(value, row, index) {
                  	if(value!=null&&value!='') {
                  		var datetime=new Date(value);
                  		return datetime.format('yyyy-MM-dd hh:mm');
                  	}
              	}	 
			},
			{field : 'm3',title : '询问地点',sortable : false,width : 130,align : 'center'},
			{field : 'caozuo',title : '添加操作',sortable : false,width : 80,align : 'center',
				formatter : function(value, row, index) {
					return	"<a  class='btn btn-info btn-xs' onclick='addSgxwbl("
					+ row.id + ")'>事故询问笔录</a><a  class='btn btn-info btn-xs' style='margin-left:10px;' onclick='addSgdczj("
					+ row.id + ")'>事故调查证据</a>";
              	}	 
			},
			{field : 'flag',title : '是否立案',sortable : false,width : 60,align : 'center',
				formatter : function(value, row, index) {
					if(value=='0'){
						return "<a style='margin:2px' class='btn btn-info btn-xs' onclick='addlasp("+row.id+")'>未立案</a>";
					}else{
						return "<a style='margin:2px' class='btn btn-default btn-xs'>已立案</a>";
					}
              	}	 
			}
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
    toolbar:'#aqzf_sgxwtz_tb'
	});
	
});

//添加立案审批记录
function addlasp(sgtzid) {
    openDialog("添加立案审批", ctx + "/xzcf/ybcf/sgxwtz/createla/2?sgtzid="+sgtzid, "80%","100%", "");
    /*layer.confirm('请选择自由裁量类型', {
        btn: ['新','旧'] //按钮
    }, function(){
        openDialog("添加立案审批", ctx + "/xzcf/ybcf/sgxwtz/createla/2?sgtzid="+sgtzid, "80%","100%", "");
        layer.close(layer.index-1);
    }, function(){
        openDialog("添加立案审批", ctx + "/xzcf/ybcf/sgxwtz/createla/1?sgtzid="+sgtzid, "80%","100%", "");
    });*/
}

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
			url:ctx+"/xzcf/ybcf/sgxwtz/delete/"+ids,
			success: function(data){
				layer.alert(data, {icon:6,title: '提示',offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
 
}

//添加
function add() {
	openDialog("添加事故询问通知信息",ctx+"/xzcf/ybcf/sgxwtz/create","800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改事故询问通知信息",ctx+"/xzcf/ybcf/sgxwtz/update/"+row.id,"800px", "400px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看事故询问通知信息",ctx+"/xzcf/ybcf/sgxwtz/view/"+row.id,"800px", "400px","");
	
}

function addSgxwbl(id) {
	openDialog("添加事故询问笔录信息",ctx+"/xzcf/ybcf/sgxwbl/create/"+id,"80%", "100%","");
}

function addSgdczj(id) {
	openDialog("添加事故调查证据信息",ctx+"/xzcf/ybcf/sgdczj/create/"+id,"800px", "400px","");
}
