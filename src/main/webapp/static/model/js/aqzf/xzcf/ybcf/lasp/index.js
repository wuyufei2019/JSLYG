var dg;
var d;
$(function() {
	dg = $('#ybcf_lasp_dg')
			.datagrid(
					{
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
								{
									field : 'id',
									title : 'id',
									checkbox : true,
									width : 50,
									align : 'center'
								},
								{
									field : 'number',
									title : '编号',
									sortable : false,
									width : 60,
									align : 'center'
								},
								{
									field : 'dsperson',
									title : '公司名称',
									sortable : false,
									width : 60,
									align : 'center'
								},
								{
									field : 'cfryname',
									title : '当事人',
									sortable : false,
									width : 60,
									align : 'center'
								},
								{
									field : 'ayname',
									title : '案由',
									sortable : false,
									width : 80,
									align : 'center'
								},
								{
									field : 'casesource',
									title : '案件来源',
									sortable : false,
									width : 50,
									align : 'center'
								},
								{field : 'filldate',title : '立案时间',sortable : false,width : 50,align : 'center',
									formatter : function(value,row) {
										if(value!=null&&value!='') {
						              		var datetime=new Date(value);
						              		return datetime.format('yyyy-MM-dd');
						              	}
									}
								},
								{
									field : 'caozuo',
									title : '添加记录',
									sortable : false,
									width : 120,
									align : 'center',
									formatter : function(value, row, index) {
										var id=row.id;
										var faflag= row.faflag==1?1:0;
										var xwflag= row.xwflag==1?1:0;
										var gzflag= row.gzflag==1?1:0;
										var dcflag= row.dcflag==1?1:0;
										var tzflag= row.tzflag==1?1:0;
										var cfjdflag= row.cfjdflag==1?1:0;
										var cbflag= row.cbflag==1?1:0;
										var cgflag= row.cgflag==1?1:0;
										var qzflag= row.qzflag==1?1:0;
										var cfspflag = row.cfspflag==1?1:0;
                                        var cxflag= row.cxflag==1?1:0;
                                        var tempflag= row.tempflag==1?1:0;

					                    var a = "";
					                    if(cxflag=="1"){
					                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>立案审批</a>";
					                    }else{
											a+= "<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addLa("
											+ id + ")'>立案审批</a>";
					                    }
                                        if(faflag=="1"){
                                            a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>调查方案</a>";
                                        }else{
                                            a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick='addFa("
                                                + id+ ","+row.tempflag+ ")'>调查方案</a>";
                                        }
					                    
					                    if(xwflag=="1" || dcflag=="1"){
					                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>询问通知</a>";
					                    }else{
											a+= "<a style='margin:2px' class='btn btn-info btn-xs' onclick='addXw("
												+ id +","+tempflag+ ")'>询问通知</a>";
					                    }
					                    
					                    if(dcflag=="1"){
					                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>询问笔录</a>";
					                    }else{
											a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick='addBl("
												+ id+ "," +xwflag+ ","+tempflag+ ")'>询问笔录</a>";
					                    }
					                    
					                    if(dcflag=="1"){
					                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>调查报告</a>";
					                    }else{
											a+= "<a style='margin:2px' class='btn btn-info btn-xs' onclick='adddc("
											+ id+","+tempflag+ ")'>调查报告</a>";
					                    }
                                        if(cfspflag=="1"){
                                            a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>处罚审批</a>";
                                        }else{
                                            a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick='addCfsp("
                                                + id+ ","+row.tempflag+ ")'>处罚审批</a>";
                                        }



                                        if(row.tlflag=="1"){
                                            a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>集体讨论</a>";
                                        }else{
                                            a+= "<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addtl("
                                                + id+","+row.tempflag+")'>集体讨论</a>";
                                        }

                                        if(row.sbflag=="1"){
                                            a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>陈述申辩</a><br>";
                                        }else{
                                            a+= "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsb("
                                                + id+","+row.tempflag+")'>陈述申辩</a><br>";
                                        }

                                        if(gzflag=="1"){
                                            a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>处罚告知</a>";
                                        }else{
                                            a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick='addGz("
                                                + id +"," +dcflag+")'>处罚告知</a>";
                                        }

                                        if(tzflag=="1"){
                                            a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>听证告知</a>";
                                        }else{
                                            a+= "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addTz("
                                                +id+","+gzflag+")'>听证告知</a>";
                                        }

                                        if(cbflag=="1"){
                                            a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>案件呈批</a>";
                                        }else{
                                            a+= "<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addCp("
                                                + id +","+gzflag+")'>案件呈批</a>";
                                        }

                                        if(cfjdflag=="1"){
                                            a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>处罚决定</a>";
                                        }else{
                                            a+= "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addJd("
                                                + id +"," +row.cbflag+ ")'>处罚决定</a>";
                                        }

                                        if(row.jaflag=="1"){
                                            a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>结案审批</a>";
                                        }else{
                                            a+= "<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addJa("
                                                + id +"," +cfjdflag+")'>结案审批</a>";
                                        }
										
										return a;
									}
								}
								 ] ],
						onDblClickRow : function(rowdata, rowindex,rowDomElement) {
								view();
						},
						onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
						},
						checkOnSelect : false,
						selectOnCheck : false,
						toolbar : '#ybcf_lasp_tb'
					});
});

//预立案改为真立案
function addLa(laid) {
	top.layer.confirm('您确定将预立案修改为立案审批吗？如果确定，会将该预立案涉及的所有文书进行编号！', {
		icon : 3,
		title : '提示'
	}, function(index) {
		$.ajax({
			type : 'get',
			url : ctx + "/xzcf/ybcf/lasp/updateLabh/" + laid,
			success : function(data) {
				layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			},
			error : function() {
				layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
			}
		});
	});

}

//查看进度
function viewjd(laid) {
	openDialogView("查看案件进度详情",ctx + "/xzcf/ybcf/lasp/viewjd/" + laid, "100%","100%", "");
}
// 添加立案审批记录
function addla() {
    openDialog("添加立案审批", ctx + "/xzcf/ybcf/lasp/create", "800px","400px", "");
}


// 添加立案审批记录
function add() {
    openDialog("添加立案审批", ctx + "/xzcf/ybcf/lasp/create/2", "80%","100%", "");
}

//添加事故立案审批记录
function addsg() {
	openDialog("添加事故立案审批", ctx + "/xzcf/ybcf/lasp/create3", "80%","100%", "");
}

//添加调查报告
function adddc(id,t) {
	if (t==1) {
		layer.msg("请先补全立案审批信息！",{time: 3000});
		return;
	}
	openDialog("添加调查报告", ctx + "/xzcf/ybcf/dcbg/create/"+id, "80%","100%", "");
}

//添加缴款审批
function addFksp(id,t) {
	if (t!=1) {
		layer.msg("请先补全处罚决定信息！",{time: 3000});
		return;
	}
	openDialog("添加缴款审批",ctx+"/xzcf/ybcf/fksp/create/"+id,"800px", "400px","");
}

//添加缴款批准
function addFkpz(id,t) {
	if (t!=1) {
		layer.msg("请先补全缴款审批信息！",{time: 3000});
		return;
	}
	openDialog("添加缴款批准",ctx+"/xzcf/ybcf/fkpz/create/"+id,"800px", "400px","");
}

//添加集体讨论
function addtl(id,t) {
	if (t==1) {
		layer.msg("请先补全立案审批信息！",{time: 3000});
		return;
	}
	openDialog("添加集体讨论",ctx+"/xzcf/ybcf/jttl/create/"+id,"80%", "100%","");
}

//添加陈述申辩
function addsb(id,t) {
	if (t==1) {
		layer.msg("请先补全立案审批信息！",{time: 3000});
		return;
	}
	openDialog("添加陈述申辩",ctx+"/xzcf/ybcf/cssbbl/create/"+id,"80%", "100%","");
}

//补全信息记录
function addtemp(count) {
	if (count==0) {
		layer.msg("没有需要待补全的信息！",{time: 3000});
		return;
	}
	layer.open({
	    type: 2,  
	    shift: 1,
	    area: ['1000px', '100%'],
	    title: "补全审批记录",
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/lasp/update/temp" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];
	         var inputForm = body.find('#inputForm');
	         iframeWin.contentWindow.doSubmit();
		  },
		  cancel: function(index){ 
	     }
	}); 
}
//添加询问笔录
function addBl(id,f1,t) {
	if (t==1) {
		layer.msg("请先补全立案审批信息！",{time: 3000});
		return;
	}
	if (f1!=1) {
		layer.msg("您还没有添加询问通知！",{time: 3000});
		return;
	}
	openDialog("添加询问笔录",ctx+"/xzcf/ybcf/xwbl/create/"+id,"80%", "100%","");
}

//添加询问通知
function addXw(id,t) {
	if (t==1) {
		layer.msg("请先补全立案审批信息！",{time: 3000});
		return;
	}
	openDialog("添加询问通知", ctx + "/xzcf/ybcf/xwtz/create/"+id, "800px","400px", "");
}
//添加处罚告知
function addGz(id,f1) {
	if (f1!=1) {
		layer.msg("您还没有添加调查报告信息！",{time: 3000});
		return;
	}
	openDialog("添加处罚告知", ctx + "/xzcf/ybcf/cfgz/create/"+id, "80%","100%", "");
}
//添加听证告知记录
function addTz(id,f2) {
	if (f2!= 1) {
		layer.msg("您还没有添加处罚告知信息！",{time: 3000});
		return;
	}
	openDialog("添加听证告知", ctx + "/xzcf/ybcf/tzgz/create/"+id, "80%","100%", "");
}
//添加处罚决定记录
function addJd(id,f1) {
	if (f1!=1) {
		layer.msg("您还没有添加案件呈批信息！",{time: 3000});
		return;
	}
	openDialog("添加处罚决定", ctx + "/xzcf/ybcf/cfjd/create/"+id, "80%","100%", "");
}

//添加结案审批记录
function addJa(id,f1) {
	if (f1!=1) {
		layer.msg("您还没有添加处罚决定信息！",{time: 3000});
		return;
	}
	openDialog("添加结案审批", ctx + "/xzcf/ybcf/jasp/create/"+id, "80%","100%", "");
}
//添加安监呈批处理记录
function addCp(id,f2) {
	if (f2!= 1) {
		layer.msg("您还没有添加处罚告知信息！",{time: 3000});
		return;
	}
	openDialog("添加案件呈批", ctx + "/xzcf/ybcf/ajclcp/create/"+id, "80%","100%", "");
}
// 删除
function del() {
	var row = dg.datagrid('getChecked');
	if (row == null || row == '') {
		layer.msg("请至少勾选一行记录！", {
			time : 1000
		});
		return;
	}

	var ids = "";
	for (var i = 0; i < row.length; i++) {
		if (ids == "") {
			ids = row[i].id;
		} else {
			ids = ids + "," + row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {
		icon : 3,
		title : '提示'
	}, function(index) {
		$.ajax({
			type : 'get',
			url : ctx + "/xzcf/ybcf/lasp/delete/" + ids,
			success : function(data) {
				layer.alert(data, {
					offset : 'rb',
					shade : 0,
					time : 2000
				});
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});

}

// 弹窗修改
function upd() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	if(row.dcflag == '1'){
		layer.msg("已生成调查报告，不得修改！", {
			time : 3000
		});
		return;
	}
	else{
		layer.open({
		    type: 2,  
		    shift: 1,
		    area: ['80%', '100%'],
		    title: "修改审批记录",
		    maxmin: false, 
		    content: ctx + "/xzcf/ybcf/lasp/update/" + row.id ,
		    btn: ['确定', '关闭'],
		    yes: function(index, layero){
		    	 var body = layer.getChildFrame('body', index);
		         var iframeWin = layero.find('iframe')[0];
		         var inputForm = body.find('#inputForm');
		         iframeWin.contentWindow.doSubmit();
			  },
			  cancel: function(index){ 
		     }
		}); 
	}
}

// 查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("审批信息查看",ctx + "/xzcf/ybcf/lasp/view/" + row.id, "80%","100%", "");

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

function exportws() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	openDialogView("导出文书",ctx + "/xzcf/ybcf/lasp/exportws/" + row.id, "600px","300px", "");
}

function exportjz() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 2000
		});
		return;
	}
	if (row.jaflag != "1"){
		layer.msg('该案子未结案！', {
			time : 2000
		});
		return;
	}
	layer.open({
	    type: 2,  
	    area: ['400px', '400px'],
	    title: '导出卷宗',
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/lasp/exportjzindex/" + row.id,
	    btn: ['导出文书', '关闭'],
	    yes: function(index, layero){
	    	var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];
	         var inputForm = body.find('#inputForm');
	         iframeWin.contentWindow.doSubmit();
		},
		cancel: function(index){}
	}); 	
}

//添加调查方案
function addFa(id,t) {
    if (t==1) {
        top.layer.confirm('请先补全立案审批信息', {
            icon : 2,
            title : '提示'
        }, function(index) {
            top.layer.close(index);
        });
        return;
    }
    openDialog("添加调查方案", ctx + "/xzcf/ybcf/dcfa/create/"+id, "900px","600px", "");
}

//添加处罚审批
function addCfsp(id,t) {
    if (t==1) {
        top.layer.confirm('请先补全立案审批信息', {
            icon : 2,
            title : '提示'
        }, function(index) {
            top.layer.close(index);
        });
        return;
    }
    openDialog("添加处罚审批", ctx + "/xzcf/ybcf/cfsp/create/"+id, "900px","600px", "");
}