<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>图片上传</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引	
</script>
</head>
<body>

     <form id="inputForm" action="" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">检查情况：</label></td>
					<td class="width-80" >
					<input type="text" id="M2" name="M2" style="width: 100%;height: 80px;" class="easyui-textbox" data-options="required:true,editable:true,multiline:true"/>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">须整改的问题：</label></td>
					<td class="width-80" >
					<input type="text" id="M3" name="M3" style="width: 100%;height: 70px;" class="easyui-textbox" data-options="required:true,editable:true,multiline:true"/>
					</td>
				</tr>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">涉嫌违法行为：</label></td>
					<td class="width-80" >
					<input type="text" id="M1" name="M1" style="width: 100%;height: 30px;" class="easyui-combobox" data-options="required:true,editable:true ,valueField:'id',textField:'text',panelHeight:'100px',url:'${ctx}/aqzf/${version}/idlist'"/>
					</td>
				</tr>				
				<tr>
					<td class="width-20 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-80" >
					<div id="uploader_ws_m15">
					    <div id="m15fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">	
	var $ = jQuery,
    $list = $('#m15fileList'); //图片上传
	
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
	
    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
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
			var $input = $('<input id="url" type="hidden" name="url" value="'+newurl+'"/>'+
				'<input id="filename" type="hidden" name="filename" value="'+res.fileName+'"/>');
			
			$('#uploader_ws_m15').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});

function geturl(){
	var url=$('#url').val();
	if(url==null){
		url="";
	}
	return url;
}

function getfilename(){
	var filename=$('#filename').val();
	return filename;
}	

function getczwt(){
	var czwt=$('#M1').combobox('getValue');
	return czwt;
}

function getwtms(){
	var wtms=$('#M2').textbox('getValue');
	return wtms;
}

function getzgwt(){
	var zgwt=$('#M3').textbox('getValue');
	return zgwt;
}	
</script>
</body>
</html>