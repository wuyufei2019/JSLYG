var dg;
var d;
$(function() {
	dg = $('#aqzf_jcjh_dg').datagrid(
			{
				method : "post",
				url : ctx + '/aqzf/jcjh/list',
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
						{field : 'id',title : 'id',checkbox:true,width : 50,align : 'center'},
						{field : 'm1',title : '计划时间',sortable : false,width : 50,align : 'center'},
						{field : 'qyname',title : '公司名称',sortable : false,width : 80},
						{field : 'wgname',title : '企业所属分组',sortable : false,width : 80,align : 'center'},
//						{field : 'm4',title : '行业类型',sortable : false,width : 40,align : 'center',
//							formatter : function(value, row, index) {
//								if(value == '1'){
//									return '工贸';
//								}else if(value == '2'){
//									return '化工';
//								}else{
//									return value;
//								}
//				            }
//						},
//						{field : 'm5',title : '检查处室',sortable : false,width : 40,align : 'center'},
						{field : 'zfry',title : '行政执法人员',sortable : false,width : 60,align : 'center'},
						{
							field : 'panduan',
							title : '操作',
							sortable : false,
							width : 50,
							align : 'center',
							formatter : function(value, row, index) {
								if(value == '1'){
									return '';
								}else if (value == '0'){
									return "<a class='btn btn-info btn-xs' onclick='addFa("+row.id+")'>添加方案</a>";
								}
				            }
						}
				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					view();
				},
				onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
					$(this).datagrid("autoMergeCells", [ 'm1' ]);
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#aqzf_jcjh_tb'
			});

});

//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}
	var ids="";
	for(var i=0;i<row.length;i++){
//		if(row[i].panduan == "1"){
//			layer.msg(row[i].m1+"，"+row[i].qyname+"已添加检查方案不得删除，请重新勾选！",{time: 3000});
//			return;
//		}
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}
	
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/aqzf/jcjh/delete/"+ids,
			success: function(data){
				layer.alert(data, {icon:6,title: '提示',offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'计划时间'},
			   		{colval:'qyname', coltext:'公司名称'},
			   		{colval:'wgname', coltext:'企业所属分组'},
			   		{colval:'zfry', coltext:'执法人员'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/aqzf/jcjh/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}

//双随机
function add(u) {
	openDialog("添加检查计划", ctx + "/aqzf/jcjh/create/", "80%", "100%", "");
}

//弹窗增加单个计划
function addjh(u) {
	openDialog("添加检查计划", ctx + "/aqzf/jcjh/create2/", "80%", "100%", "");
}

//弹窗修改
function upd() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	if(row.panduan == "1"){
		layer.msg(row.m1+"，"+row.qyname+"已添加检查方案不得修改！",{time: 3000});
		return;
	}else{
		openDialog("修改计划信息", ctx + "/aqzf/jcjh/update/" + row.id, "800px","400px", "");
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
	openDialogView("信息查看",ctx + "/aqzf/jcjh/view/" + row.id, "800px", "400px" );
}

//添加方案
function addFa(id){
		openDialog("添加检查方案", ctx + "/aqzf/jcfa/addFa/"+id, "80%","100%", "");
}

//重置
function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//创建查询对象并查询
function search() {
	$("#aqzf_jcjh_year").combobox('setValue',$("#aqzf_jcjh_year").combobox('getText'));
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//向子页面发送qyids
function  getqyids(){
	return $("#qyids").val();
}

//向子页面发送属地
function  getsd(){
	var sds = $("#M3").combotree('getValues');
	var sd = "";
	for(j = 0; j < sds.length; j++) {
		if(j==0){
			sd = "\'"+sds[j]+"\'";
		}else{
			sd += ",\'"+sds[j]+"\'";
		}
	}
	return sd;
}

//向子页面发送行业类型
function  gethylx(){
	return $("#M4").val();
}

//向子页面发送ryids
function  getryids(){
	return $("#ryids").val();
}

// 弹出企业选择框
function openqylist() {
	layer.open({
	    type: 2,  
	    area: ['60%', '80%'],
	    title: '选择企业',
        maxmin: false, 
	    content: ctx + "/aqzf/jcjh/choose" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	var  $list= $("#qyList");
	    	$list.html("");
	    	//var body = layer.getChildFrame('body', index);
	    	var iframeWin = layero.find('iframe')[0];
	    	var idname=iframeWin.contentWindow.getidname();
	        var arr1=idname.split(",");
	        var ids="";
	        qytotal = 0;
			for (var i = 0; i < arr1.length-1; i++) {
				var arr2=arr1[i].split("||");
				// 企业名称拼接
				var $li = $("<div id=\"qyid_"
						+ arr2[0]
						+ "\" style=\"margin-bottom: 10px;\">"
						+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;display:inline-block;width:380px;\">"
						+ arr2[1] + " — " + arr2[2]
						+ "</a>"
						+ "<span class=\"ss\" onClick=\"removeQy('qyid_"
						+ arr2[0]
						+ "')\" style=\"cursor: pointer;margin-right:20px;\">删除</span>"
						+ " <button onclick=\"qydgsj("+arr2[0]+");return false;\">随机</button>"
						+ " </div>");
				$list.append($li);
				ids = ids + arr2[0] + ",";
				qytotal++;
			}
			$("#qytotal").html("（共"+qytotal+"家）");
			$("#tip").hide();
			$("#qyids").val(ids);
			layer.close(index);
			},
	cancel: function(index){}
	}); 	
}

// 删除预览企业
function removeQy(fileid) {
	var ids = $("#qyids");
	var qy = ids.val();
	ids.val(qy.replace(fileid.split("_")[1] + ",", ""));
	$("#" + fileid).remove();
	--qytotal;
	$("#qytotal").html("（共"+qytotal+"家）");
};

//弹出执法人员选择框
function openrylist() {
	layer.open({
	    type: 2,  
	    area: ['60%', '80%'],
	    title: '选择执法人员',
        maxmin: false, 
	    content: ctx + "/aqzf/jcjh/rychoose" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	var  $list= $("#ryList");
	    	$list.html("");
	    	var iframeWin = layero.find('iframe')[0];
	    	var idname=iframeWin.contentWindow.getidname();
	        var arr1=idname.split(",");
	        var ids="";
	        for (var i = 0; i < arr1.length-1; i++) {
				var arr2=arr1[i].split("||");
					var $li = $("<div id=\"ryid_"
							+ arr2[0]
							+ "\" style=\"margin-bottom: 10px;\">"
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;display:inline-block;width:180px;\">"
							+ arr2[1]
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removeRy('ryid_"
							+ arr2[0]
							+ "')\" style=\"cursor: pointer;margin-right:20px;\">删除</span>"
							+ " <button onclick=\"rydgsj("+arr2[0]+");return false;\">随机</button>"
							+ " </div>");
					$list.append($li);
					ids = ids + arr2[0] + ",";
				}
			$("#ryids").val(ids);
			layer.close(index);
			},
	cancel: function(index){}
	}); 	
}

//删除预览执法人员
function removeRy(fileid) {
	var ids = $("#ryids");
	var ry = ids.val();
	ids.val(ry.replace(fileid.split("_")[1] + ",", ""));
	$("#" + fileid).remove();
};