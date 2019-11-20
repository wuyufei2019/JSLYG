<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事故调查证据管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/xzcf/ybcf/sgdczj/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-15 active"><label class="pull-right">证据：</label></td>
					<td class="width-85" >
					<input type="text" id="M1" name="M1" value="${sgdczj.m1}" style="width: 100%;height: 80px;" class="easyui-textbox" data-options="multiline:true,required:true,validType:'length[0,500]'"/>
					</td>
				</tr>				
				<tr>
					<td class="width-15 active"><label class="pull-right">照片（单张）：</label></td>
					<td class="width-85" >
					<input type="hidden" id="M2" name="M2" value="${sgdczj.m2}" />
					<div id="uploader_ws_zp">
					    <div id="zpfileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				
				<input type="hidden" name="ID1" value="${sgdczj.ID1}" />
				<c:if test="${not empty sgdczj.ID}">
					<input type="hidden" name="ID" value="${sgdczj.ID}" />
					<input type="hidden" name="S1"  value="<fmt:formatDate value="${sgdczj.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var $ = jQuery,
	$list = $('#zpfileList'); 
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

	uploader.on( 'uploadSuccess', function( file ,res) {
		$.jBox.closeTip();
		if(res.error==0){
			var $li = $("<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
		                "<img>" +
		                "<div class=\"info\">" + file.name + "</div>" +
		            	"</div>");
		    $img = $li.find('img');        	
		    $list.html( $li );	
		    
		    // 创建缩略图
		    uploader.makeThumb( file, function( error, src ) {
		        if ( error ) {
		            $img.replaceWith('<span>不能预览</span>');
		            return;
		        }
		        $img.attr( 'src', src );
		    }, 100, 100 );
		    
			var newurl=res.url+"||"+res.fileName;			
			$('#M2').val( newurl );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	//判断是否上传图片
	var uploadImgFlag = false;
	function isuploadImg(){
		var img=$("input[name='M2']").val();
		if(img==null||img==""){
			uploadImgFlag=false;
		}else{
			uploadImgFlag=true;
		}
	}	
	
	if('${action}' == 'update'){
		var zpUrl = '${sgdczj.m2}';
		if(zpUrl!=null&&zpUrl!=''){
			var arry =zpUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_zp_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
			                "<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\">" +
			                "<div class=\"info\">" + arry2[1] + "</div>" +
			            "</div>"
			            );
			    $list.html( $li );
			}
		}
	}
	
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	function doSubmit(){
		/* isuploadImg();
		if(uploadImgFlag==false){
			layer.open({title: '提示',offset: 'auto',content: '请上传照片！',shade: 0 ,time: 2000 });
			return;
		} */
		$("#inputForm").submit(); 
	}
	
	$(function(){
	    var flag=true;
			$('#inputForm').form({
				onSubmit : function() {
					var isValid = $(this).form('validate');
			    	if(isValid&&flag){
			    		flag=false;
			    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
			    		return true;
			    	}
					return false; // 返回false终止表单提交
			},  
		    success:function(data){ 
		         $.jBox.closeTip();
		    	if(data=='success'){
		    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    		parent.dg.datagrid('reload');
		    		parent.layer.close(index);//关闭对话框。
		    	}else{
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    	}
		    }    
		});
	
	});
	
</script>
</body>
</html>