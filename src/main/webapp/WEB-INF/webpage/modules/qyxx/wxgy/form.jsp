<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/form.js?v=1.2"></script>
</head>
<body>
	<form id="inputForm" action="${ctx}/bis/gwgy/${action}" method="post">
		<table class="table table-bordered dataTable" style="margin:auto;">
			<tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3"><div>
								<input value="${qylist.ID1 }" id="_qyid" name="ID1"
									style="width: 100%;height: 30px;" class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
							</div></td>
					</tr>
				</c:if>
				<c:if test="${usertype != '1' and action eq 'update'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3"><input
							value="${qylist.ID1 }" id="_qyid" name="ID1"
							style="width: 100%;height:30px;" class="easyui-combobox"
							data-options="editable:false, disabled:true,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
				</c:if>
				<tr>
					<td class="width-15 active"><label class="pull-right">高危工艺：</label></td>
					<td class="width-35"><div>
							<input name="M1" class="easyui-combobox" value="${qylist.m1 }"
								style="width:100%;height: 30px;"
								data-options="required:'true', editable:false ,valueField: 'id',textField: 'text',url:'${ctx}/tcode/dict/gwgy' " />
						</div></td>
					<td class="width-15 active"><label class="pull-right">套数：</label></td>
					<td class="width-35"><div>
							<input name="M2" class="easyui-textbox" value="${qylist.m2 }"
								style="width:100%;height: 30px;"
								data-options="required:'true',validType:'mone' " />
						</div></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
					<div id="uploader_ws_m4">
					    <div id="m4fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">图纸附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m5">
					    <div id="m5fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><input type="text" name="M3"
						value="${qylist.m3 }" class="easyui-textbox"
						style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,250]'" /></td>
				</tr>

				<c:if test="${not empty qylist.ID}">
					<input type="hidden" name="ID" value="${qylist.ID}" />
					<input type="hidden" name="ID1" value="${qylist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${qylist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${qylist.s3}" />
				</c:if>
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
	var usertype=${usertype};
	uploadImgFlag=false;//是否上传图片
	
	var $ = jQuery,
    $list = $('#m4fileList'); //图片上传
	$list2 = $('#m5fileList'); //文件上传
	
	var uploader = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=image',

	    pick: {
	    	id:'#imagePicker',
	    	multiple : false,
	    },
	    duplicate :true,	    
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/jpg,image/jpeg,image/png' 
	    },
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
	
	var fileuploader = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=draw',
	    		
	    pick: {
	    	id:'#filePicker',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	fileuploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    	isuploadImg();
    };
	
	// 图片上传成功，显示预览图
	uploader.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
		            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		                "<img>" +
		                "<div class=\"info\">" + file.name + "</div>" +
		            "</div>"
		            ),
	
		        $img = $li.find('img');

		    $list.append( $li );
	
		    // 创建缩略图
		    uploader.makeThumb( file, function( error, src ) {
		        if ( error ) {
		            $img.replaceWith('<span>不能预览</span>');
		            return;
		        }
	
		        $img.attr( 'src', src );
		    }, 100, 100 );
			
			
			var newurl=res.url+"||"+res.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M4" value="'+newurl+'"/>');
			
			$('#uploader_ws_m4').append( $input );
			uploadImgFlag=true;
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
 
	// 文件上传成功 
	fileuploader.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list2.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M5" value="'+newurl+'"/>');
			
			$('#uploader_ws_m5').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	
	
	if('${action}' == 'update'){
		var zpUrl = '${qylist.m4}';
		if(zpUrl!=null&&zpUrl!=''){
			var arry =zpUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_zp_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
			            	"<span class=\"cancel\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			                "<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\">" +
			                "<div class=\"info\">" + arry2[1] + "</div>" +
			            "</div>"
			            );

			    $list.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M4" value="'+arry[i]+'"/>');
				$('#uploader_ws_m4').append( $input );
			}
		}
		
		var fjUrl = '${qylist.m5}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list2.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M5" value="'+arry[i]+'"/>');
				$('#uploader_ws_m5').append( $input );
			}
		}
		isuploadImg();
	}
	
	//判断是否上传图片
	function isuploadImg(){
		var img=$("input[name='M4']").val();
		if(img==null||img==""){
			uploadImgFlag=false;
		}else{
			uploadImgFlag=true;
		}
	}	
	</script>
</body>
</html>
