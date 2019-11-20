var dg;
var d;
$(function() {
	dg = $('#aqzf_jdjctj_dg')
			.datagrid(
					{
						method : "get",
						url : ctx + '/aqzf/jdjctj/list',
						fit : true,
						fitColumns : true,
						border : false,
						idField : 'id',
						striped : true,
						pagination : true,
						rownumbers : true,
						nowrap : false,
						pageNumber : 1,
						pageSize : 50,
						pageList : [ 50, 100, 150, 200, 250 ],
						scrollbarSize : 5,
						singleSelect : true,
						striped : true,
						columns : [[
								{field : 'id',title : 'id',hidden:'true',width : 50,align : 'center'},
								{field : 'm1',title : '计划时间',sortable : false,width : 60,align : 'center'},
								{field : 'qyname',title : '公司名称',sortable : false,width : 100},
								{field : 'm3',title : '企业分组',sortable : false,width : 60,align : 'center'},
								{field : 'zfry',title : '行政执法人员',sortable : false,width : 60,align : 'center'},
								{
									field : 'caozuo',
									title : '查看文书信息',
									sortable : false,
									width : 100,
									align : 'center',
									formatter : function(value, row, index) {
					                    var a = "";
					                    if(row.panduan == "1"){
					                    	a+= "<a style='margin:2px' class='btn btn-info btn-xs' onclick='viewjcfa("
												+ row.faid + ")'>检查方案</a>";
					                    }else{
											a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>检查方案</a>";
					                    }
					                    
					                    if(row.jlcz == "1"){
					                    	a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick='viewjcjl("
												+ row.jlid + ")'>检查记录</a>";
					                    }else{
											a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>检查记录</a>";
					                    }
					                    
					                    if(row.clcz == "1"){
					                    	a+= "<a style='margin:2px' class='btn btn-success btn-xs' onclick='viewclcs("
												+ row.clid + ")'>现场处理</a>";
					                    }else{
											a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>现场处理</a>";
					                    }
					                    
					                    if(row.zlcz == "1"){
					                    	a+= "<a style='margin:2px' class='btn btn-warning btn-xs' onclick='viewzlzg("
												+ row.zlid + ")'>责令整改</a>";
					                    }else{
											a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>责令整改</a>";
					                    }
					                    
					                    if(row.fccz == "1"){
					                    	a+= "<a style='margin:2px' class='btn btn-danger btn-xs' onclick='viewzgfc("
												+ row.fcid +")'>复查意见</a>";
					                    }else{
											a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>复查意见</a>";
					                    }
										
										return a;
									}
								},
								{field : 'jdt',title : '进度',sortable : false,width : 80,align : 'center',
									formatter : function(value,row) {
										var zz = (((row.panduan=="1"?1:0)+(row.jlcz=="1"?1:0)+(row.clcz=="1"?1:0)+(row.zlcz=="1"?1:0))*25).toFixed(0)+'%';
										var htmlstr = '<div class="easyui-progressbar progressbar" onclick="viewjd('+row.id+')" style="margin-bottom: 5px;margin-top: 5px;height: 30px;">'+
									    '<div class="progressbar-value" style="width: '+zz+'; height: 30px; line-height: 30px;">'+
									    '<div class="progressbar-text" style="width: 100%; height: 30px; line-height: 30px;background-color: pink;">'+zz+'</div></div></div>';  
									    return htmlstr;   
									}
								}
							]],
						onDblClickRow : function(rowindex, rowdata,rowDomElement) {
								viewjd(rowdata.id);
						},
						onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
							$(this).datagrid("autoMergeCells", [ 'm1' ]);
						},
						checkOnSelect : false,
						selectOnCheck : false,
						toolbar : '#aqzf_jdjctj_tb'
					});
});

//查看进度
function viewjd(jhqyid) {
	openDialogView("查看监督检查进度详情",ctx + "/aqzf/jdjctj/viewjd/" + jhqyid, "100%","100%", "");
}

// 创建查询对象并查询
function search() {
	$("#aqzf_jdjctj_year").combobox('setValue',$("#aqzf_jdjctj_year").combobox('getText'));
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//查看检查方案
function viewjcfa(faid) {
	layer.open({
	    type: 2,  
	    area: ['80%', '100%'],
	    title: '查看检查方案信息',
        maxmin: false, 
	    content: ctx + "/aqzf/jcfa/view/" + faid,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url:ctx+"/aqzf/jcfa/export/"+faid,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

//查看检查记录
function viewjcjl(jlid) {
	layer.open({
	    type: 2,  
	    area: ['900px', '450px'],
	    title: '查看检查记录信息',
        maxmin: false, 
	    content: ctx + "/aqzf/jcjl/view/" + jlid,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url:ctx+"/aqzf/jcjl/export/"+jlid,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

//查看现场处理
function viewclcs(clid) {
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '查看现场处理信息',
        maxmin: false, 
	    content: ctx + "/aqzf/clcs/view/" + clid,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url:ctx+"/aqzf/clcs/exportword/"+clid,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

//查看责令整改
function viewzlzg(zlid) {
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '查看责令整改信息',
        maxmin: false, 
	    content: ctx + "/aqzf/zlzg/view/" + zlid,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url:ctx+"/aqzf/zlzg/exportword/"+zlid,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

//查看复查意见
function viewzgfc(fcid) {
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '查看复查意见信息',
        maxmin: false, 
	    content: ctx + "/aqzf/fcyj/view/" + fcid,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url:ctx+"/aqzf/fcyj/exportword/"+fcid,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}