var dg;
var d;
$(function() {
	dg = $('#xzcf_qzzx_dg')
			.datagrid(
					{
						method : "get",
						url : ctx + '/xzcf/ybcf/qzzx/list',
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
									title : 'ID',
									checkbox : true,
									width : 50,
									align : 'center'
								},{
									field : 'number',
									title : '编号',
									sortable : false,
									width : 70
								},
								{
									field : 'dsname',
									title : '当事人',
									sortable : false,
									width : 80,
									align : 'center'
								},
								{
									field : 'qzzxsj',
									title : '强制执行日期',
									sortable : false,
									width : 50,
									align : 'center',
									formatter : function(value, row, index) {
										if(value!=null&&value!='') {
						              		var datetime=new Date(value);
						              		return datetime.format('yyyy-MM-dd');
						              	}
					              	}	 
								},
								{
									field : 'clname',
									title : '相关材料',
									sortable : false,
									width : 200,
									align : 'left'
								}
								 ] ],
						onDblClickRow : function(rowdata, rowindex,rowDomElement) {
								view();
						},
						onLoadSuccess : function() {
							
						},
						checkOnSelect : false,
						selectOnCheck : false,
						toolbar : '#xzcf_qzzx_tb'
					});

});


//// 添加告知记录
//function add() {
//	openDialog("添加告知记录", ctx + "/xzcf/xzcf/qzzx/create", "750px",
//			"400px", "");
//}

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
			url : ctx + "/xzcf/ybcf/qzzx/delete/" + ids,
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
	openDialog("修改强制执行申请记录", ctx + "/xzcf/ybcf/qzzx/update/" + row.id, "800px","400px", "");
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
	openDialogView("强制执行申请信息查看",ctx + "/xzcf/ybcf/qzzx/view/" + row.id, "800px","400px", "");

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

// 导出强制执行申请书word
function fileexport() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}

	$.ajax({
		url : ctx + "/xzcf/ybcf/qzzx/exportqzzx/cg/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}

