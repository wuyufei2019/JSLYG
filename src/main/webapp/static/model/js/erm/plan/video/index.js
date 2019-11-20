
//弹窗增加
function add(u) {
	openDialog("上传文件信息",ctx+"/erm/train-plan/video/create","600px", "400px","");
}

//删除
function del(id){
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/erm/train-plan/video/delete/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				location.reload();
			}
		});
	});
}

//创建查询对象并查询
function search(){
	$("#searchFrom").submit();
}

function reset(){
	$("#searchFrom").form("clear");
	search(); 
}
