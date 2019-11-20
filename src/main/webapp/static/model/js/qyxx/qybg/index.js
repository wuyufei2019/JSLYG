var dg;
$(function(){
	dg=$('#table_dg').datagrid({    
	method: "post",
	url:ctx+'/bis/qybg/list', 
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
	scrollbarSize:10,
	singleSelect:true,
	striped:true,
	columns:[[
            {field:'qyname',title:'企业名称',sortable:false,width:80},
            {field:'modification',title:'变更事项',sortable:false,width:50,formatter:function(value){
                   var html="";
                   if(value=="关停增减"){
            	   	html = "关停增减";
                   }else if(value=="辖区转移"){
            	   	html = "辖区转移";
                   }else if(value=="信息变更"){
            	   	html = "信息变更";
                   }
                   return html;
            }},
            {field:'content',title:'变更内容',sortable:false,width:200},
            {field:'s1',title:'申请提交时间',sortable:false,width:80,
            	formatter: function(value){
            		if (value) {
            			return new Date(value).format("yyyy-MM-dd hh:mm:ss");
            		}
            	}
            },
            {field:'applyer',title:'申请人',sortable:false,width:50},
            {field:'s2',title:'审核时间',sortable:false,width:80,
            	formatter: function(value, row){
        			if (row.result) {
        				return new Date(value).format("yyyy-MM-dd hh:mm:ss");
        			}
            	}
            },
            {field:'reviewer',title:'审核人',sortable:false,width:50},
			{
				field: 'result', title: '审核结果', sortable: false, width: 50,align:'center', formatter: function (value) {
					var html = "";
					if (!value) {
						html = "未审核";
					} else if (value == "1") {
						html = "通过";
					} else if (value == "0") {
						html = "未通过";
					}
					return html;
				}, styler: function (value) {
                    if (value == "0") {
                        return "background-color:rgb(249, 156, 140)";
                    }else if(!value){
                        return 'background-color:rgb(255,240,245);';
					}
				}
			},
			{field:'operate',title:'操作',sortable:false,width:50,align:'center',formatter:function(value,row){
					if(!row.result){
                        return "<a class='btn btn-success btn-xs'onclick='review("+row.id+",\""+row.result+"\")'>审核</a>";
					}
				}}

        ]],
        onLoadSuccess: function(){
			if(usertype=='1'){
				dg.datagrid("hideColumn",["operate"]);
			}
        },
        onDblClickRow: function (){
             view();
        },
	checkOnSelect:false,
	selectOnCheck:false,
	toolbar:'#dg_tb'
	});
});

//弹窗增加
function add() {
	openDialog("添加企业变更信息",ctx+"/bis/qybg/create/","800px", "400px","");
}

//删除
function del(){
	var row = dg.datagrid('getSelected');
	if(row.length==0) {
	    layer.msg("请至少选择一行记录！",{time: 1000});
	    return;
	}
	if(row.result){
	    layer.msg("已审核，无法删除！",{time: 1000});
	    return;
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/bis/qybg/delete/"+row.id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
 
}

//弹窗修改
function review(id,result){
	if(result&&result!="null"&&result!="undefined"){
	   layer.msg("信息已审核，不允许再次审核!");
	}else{
	    openDialog("审核企业变更信息",ctx+"/bis/qybg/review/"+id,"80%", "500px","");
	}
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
    openDialogView("查看企业变更信息",ctx+"/bis/qybg/review/"+row.id+"?action=view","80%", "90%","");
	
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

