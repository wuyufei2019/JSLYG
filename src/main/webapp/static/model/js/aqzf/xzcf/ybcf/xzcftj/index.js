var dg;
var d;
$(function() {
	dg = $('#ybcf_lasp_dg').datagrid({
			method : "get",
			url : ctx + '/xzcf/ybcf/lasp/list',
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
			columns : [ [
					{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
					{field : 'number',title : '编号',sortable : false,width : 60,align : 'center'},
					{field : 'dsperson',title : '公司名称',sortable : false,width : 60,align : 'center'},
					{field : 'cfryname',title : '当事人',sortable : false,width : 30,align : 'center'},
					{field : 'ayname',title : '案由',sortable : false,width : 80,align : 'center'},
					{field : 'casesource',title : '案件来源',sortable : false,width : 40,align : 'center'},
					{field : 'filldate',title : '立案时间',sortable : false,width : 40,align : 'center',
						formatter : function(value,row) {
							if(value!=null&&value!='') {
			              		var datetime=new Date(value);
			              		return datetime.format('yyyy-MM-dd');
			              	}
						}
					},
					{field : 'caozuo',title : '查看文书信息',sortable : false,width : 120,align : 'center',
						formatter : function(value, row, index) {
		                    var a = "";
							a+= "<a style='margin:2px' class='btn btn-danger btn-xs' onclick='viewlasp("+row.id+","+row.tempflag+")'>立案审批</a>";
		                    
							if(row.xwflag !='1'){
		                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>询问通知</a>";
		                    }else{
								a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick='viewxwtz("
									+ row.id+ ")'>询问通知</a>";
		                    }
							
							if(row.dcflag != "1"){
		                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>调查报告</a>";
		                    }else{
								a+= "<a style='margin:2px' class='btn btn-info btn-xs' onclick='viewdcbg("
								+ row.id + ")'>调查报告</a>";
		                    }
							
							if(row.gzflag != "1"){
		                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>处罚告知</a>";
		                    }else{
								a+= "<a style='margin:2px' class='btn btn-warning btn-xs' onclick='viewcfgz("
								+ row.id +")'>处罚告知</a>";
		                    }
							
							if(row.sbflag != "1"){
		                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>陈述申辩</a>";
		                    }else{
								a+= "<a style='margin:2px' class='btn btn-success btn-xs' onclick='viewcssb("
								+ row.id+")'>陈述申辩</a>";
		                    }
							
							if(row.tzflag != "1"){
		                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>听证告知</a>";
		                    }else{
								a+= "<a style='margin:2px' class='btn btn-danger btn-xs' onclick='viewtzgz("
								+row.id+")'>听证告知</a>";
		                    }
							
							if(row.cbflag!="1"){
		                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>案件呈批</a>";
		                    }else{
								a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick='viewajcp("
								+ row.id +")'>案件呈批</a>";
		                    }
							
							if(row.tlflag!="1"){
		                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>集体讨论</a>";
		                    }else{
								a+= "<a style='margin:2px' class='btn btn-info btn-xs' onclick='viewjttl("
								+ row.id+")'>集体讨论</a>";
		                    }
							
							if(row.cfjdflag!="1"){
		                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>处罚决定</a>";
		                    }else{
								a+= "<a style='margin:2px' class='btn btn-warning btn-xs' onclick='viewcfjd("
								+ row.id +")'>处罚决定</a>";
		                    }
							
							if(row.fkspflag!="1"){
		                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>缴款审批</a>";
		                    }else{
								a+= "<a style='margin:2px' class='btn btn-success btn-xs' onclick='viewjksp("
								+ row.id +")'>缴款审批</a>";
		                    }
		                    
							if(row.jaflag!="1"){
		                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>结案审批</a>";
		                    }else{
								a+= "<a style='margin:2px' class='btn btn-danger btn-xs' onclick='viewjasp("
								+ row.id +")'>结案审批</a>";
		                    }
		                    
							return a;
						}
					},
					{field : 'jdt',title : '进度',sortable : false,width : 80,align : 'center',
						formatter : function(value,row) {
							var ja= row.jaflag==1?1:0;
							var zz = 0;
							if(ja == 1){
								zz = 100+'%';
							}else{
							    zz = ((1+(row.xwflag==1?1:0)+(row.dcflag==1?1:0)+(row.gzflag==1?1:0)+(row.cbflag==1?1:0)+(row.cfjdflag==1?1:0))*14.3).toFixed(1)+'%';
							}
							var htmlstr = '<div class="easyui-progressbar progressbar" onclick="viewjd('+row.id+')" style="height: 40px;">'+
						    '<div class="progressbar-value" style="width: '+zz+'; height: 40px; line-height: 40px;">'+
						    '<div class="progressbar-text" style="width: 100%; height: 40px; line-height: 40px;background-color: pink;">'+zz+'</div></div></div>';  
						    return htmlstr;   
						}
					}
					 ] ],
			onDblClickRow : function(rowindex, rowdata,rowDomElement) {
				viewjd(rowdata.id);
			},
			onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
			},
			checkOnSelect : false,
			selectOnCheck : false,
			toolbar : '#ybcf_lasp_tb'
		});
});

//查看立案审批
function viewlasp(id,tempflag) {
	if(tempflag=='1'){
		layer.msg("临时添加的立案审批记录，请先补全信息！",{time: 3000});
		return;
	}
	layer.open({
	    type: 2,  
	    area: ['80%', '100%'],
	    title: '查看立案审批信息',
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/lasp/view/" + id,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url : ctx + "/xzcf/ybcf/lasp/exportlasp/" + id,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

//查看询问通知
function viewxwtz(id) {
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '查看询问通知信息',
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/xwtz/viewxwtz/" + id,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url : ctx + "/xzcf/ybcf/xwtz/export/la/" + id,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

//查看调查报告
function viewdcbg(id) {
	layer.open({
	    type: 2,  
	    area: ['80%', '100%'],
	    title: '查看调查报告信息',
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/dcbg/viewdcbg/" + id,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url:ctx+"/xzcf/ybcf/dcbg/export/la/"+id,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

//查看处罚告知
function viewcfgz(id) {
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '查看处罚告知信息',
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/cfgz/viewcfgz/" + id,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url : ctx + "/xzcf/ybcf/cfgz/exportgzs/la/" + id,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

//查看陈述申辩
function viewcssb(id) {
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '查看陈述申辩信息',
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/cssbbl/viewcssb/" + id,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url:ctx+"/xzcf/ybcf/cssbbl/export/la/"+id,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

//查看听证告知
function viewtzgz(id) {
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '查看听证告知信息',
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/tzgz/viewtzgz/" + id,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url : ctx + "/xzcf/ybcf/tzgz/exporttz/la/" + id,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

//查看案件呈批
function viewajcp(id) {
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '查看案件呈批信息',
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/ajclcp/viewajcp/" + id,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url : ctx + "/xzcf/ybcf/ajclcp/exportajcp/la/" + id,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

//查看集体讨论
function viewjttl(id) {
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '查看集体讨论信息',
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/jttl/viewjttl/" + id,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url:ctx+"/xzcf/ybcf/jttl/export/la/"+id,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

//查看处罚决定
function viewcfjd(id) {
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '查看处罚决定信息',
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/cfjd/viewcfjd/" + id,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url : ctx + "/xzcf/ybcf/cfjd/exportcfjd/la/" + id,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

//查看缴款审批
function viewjksp(id) {
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '查看缴款审批信息',
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/fksp/viewjksp/" + id,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url:ctx+"/xzcf/ybcf/fksp/export/la/"+id,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

//查看结案审批
function viewjasp(id) {
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '查看结案审批信息',
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/jasp/viewjasp/" + id,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
		    	$.ajax({
		    		url : ctx + "/xzcf/ybcf/jasp/exportjasp/la/" + id,
		    		type : "POST",
		    		success : function(data) {
		    			window.open(ctx + data);
		    		}
		    	});
			},
	cancel: function(index){}
	}); 	
}

////导出强制执行word
//function fileexportqzzx() {
//	$.ajax({
//		url : ctx + "/xzcf/ybcf/qzzx/exportqzzx/la/" + row.id,
//		type : "POST",
//		success : function(data) {
//			window.open(ctx + data);
//		}
//	});
//}
//
////导出事先催告表
//function fileexportsxcg() {
//	$.ajax({
//		url : ctx + "/xzcf/ybcf/sxcg/exportsxcg/la/" + row.id,
//		type : "POST",
//		success : function(data) {
//			window.open(ctx + data);
//		}
//	});
//}

//查看进度
function viewjd(laid) {
	openDialogView("查看案件进度详情",ctx + "/xzcf/ybcf/lasp/viewjd/" + laid, "100%","100%", "");
}

// 创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'number', coltext:'编号'},
			   		{colval:'dsperson', coltext:'公司名称'},
			   		{colval:'cfryname', coltext:'当事人'},
			   		{colval:'ayname', coltext:'案由'},
			   		{colval:'casesource', coltext:'案件来源'},
			   		{colval:'lasj', coltext:'立案时间'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/xzcf/ybcf/tj/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}
