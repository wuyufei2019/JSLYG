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
			for (var i = 0; i < arr1.length-1; i++) {
				var arr2=arr1[i].split("||");
				// 企业名称拼接
				var $li = $("<div id=\"qyid_"
						+ arr2[0]
						+ "\" style=\"margin-bottom: 10px;\">"
						+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;display:inline-block;\">"
						+ arr2[1] + " — " + arr2[2]
						+ "</a>"
						+ "<span class=\"ss\" onClick=\"removeQy('qyid_"
						+ arr2[0]
						+ "')\" style=\"cursor: pointer;\">删除</span>"
						+ " </div>");
				$list.append($li);
				ids = ids + arr2[0] + ",";
			}
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
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;display:inline-block;\">"
							+ arr2[1]
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removeRy('ryid_"
							+ arr2[0]
							+ "')\" style=\"cursor: pointer;\">删除</span>"
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