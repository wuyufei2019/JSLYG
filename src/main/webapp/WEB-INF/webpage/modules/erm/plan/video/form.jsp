<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>上传文视频</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/erm/train-plan/video/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-25 active"><label class="pull-right">视频主题：</label></td>
					<td class="width-75" colspan="3"><input name="m1" style="width: 100%;height: 30px;" class="easyui-textbox" data-options="required:'true'" /></td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">视频简介：</label></td>
					<td class="width-75" colspan="3"><input name="m2" style="width: 100%;height: 30px;" class="easyui-textbox" data-options="required:'true'" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">视频：</label></td>
					<td class="width-75" colspan="3">
						<div id="uploader_ws_m3">
					    <div id="fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择视频</div>
					</div>
					</td>
				</tr>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;
var uploadImgFlag = false;//判断是否上传图片

function doSubmit(){
	if(uploadImgFlag==false){
		layer.open({title: '提示',offset: 'auto',content: '未上传视频，请上传！',shade: 0 ,time: 2000 });
		return;
	}
	$("#inputForm").serializeObject();
	$("#inputForm").submit();
}

$(function(){
	$('#inputForm').form({
	    onSubmit: function(){
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },
	    success:function(data){
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else if(data=='nosupport')
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '不支持此文件上传！',shade: 0 ,time: 3000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.location.reload();
	    	parent.layer.close(index);//关闭对话框。
	    }
	});
});

var $ = jQuery,
$list = $('#fileList'); //图片上传

var uploader = WebUploader.create({

    auto: true,

    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	server: '${ctx}/kindeditor/uploadkj?dir=1',

    pick: {
    	id:'#filePicker',
    	multiple : false,
    },
    duplicate :true,
/*    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/jpg,image/jpeg,image/png'
    },*/
    compress :{
        width: 1200,
        height: 1200,
        quality: 90,
        allowMagnify: false,
        crop: false,
        preserveHeaders: false,
        noCompressIfLarger: false,
        compressSize: 1024*50
    }
});

uploader.on( 'uploadProgress', function( file, percentage ) {
	$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
});

//负责预览图的销毁
function removeFile(fileid) {
	$("#"+fileid).remove();
	$("#input_"+fileid).remove();
};

// 图片上传成功，显示预览图
uploader.on( 'uploadSuccess', function( file ,res) {
$.jBox.closeTip();

	if(res.error==0){
		var url=res.url.split(".");
		var fjurl=url[0]+"."+res.fileExt;
		var $li = $(
				"<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
				"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+fjurl+"')\">"+res.fileName+"</a>"+
				"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
				"</div>"
		);

	    $list.append( $li );

	    // // 创建缩略图
	    // uploader.makeThumb( file, function( error, src ) {
	    //     if ( error ) {
	    //         $img.replaceWith('<span>不能预览</span>');
	    //         return;
	    //     }
		//
	    //     $img.attr( 'src', src );
	    // }, 100, 100 );

		var newurl=res.url+"||"+res.fileName;
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="m3" value="'+newurl+'"/>');

		$('#uploader_ws_m3').append( $input );
		uploadImgFlag=true;

	}else{
		layer.msg(res.message,{time: 3000});
	}
});
	//判断是否上传图片
	function isuploadImg(){
		var img=$("input[name='m3']").val();
		if(img==null||img==""){
			uploadImgFlag=false;
		}else{
			uploadImgFlag=true;
		}
	}
</script>
</body>
</html>