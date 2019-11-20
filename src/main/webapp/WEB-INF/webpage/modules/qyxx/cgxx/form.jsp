<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>储罐管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var usertype=${usertype};

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;

function doSubmit(){
	if(usertype!= '1'){
	 	var options = $("#_qyid").combobox('options');  
     	var data = $("#_qyid").combobox('getData');		/* 下拉框所有选项 */  
     	var value = $("#_qyid").combobox('getValue');	/* 用户输入的值 */  
     	var b = false;		/* 标识是否在下拉列表中找到了用户输入的字符 */  
     	for (var i = 0; i < data.length; i++) {  
        	if (data[i][options.valueField] == value) {  
            	b=true;  
           	 	break;  
        	}  
    	}  
		if(b==false){
				layer.open({title: '提示',offset: 'auto',content: '所选企业不存在！',shade: 0 ,time: 2000 });
				return;
			}
		}
		if(uploadImgFlag==false){
			layer.open({title: '提示',offset: 'auto',content: '未上传现场照片，请上传！',shade: 0 ,time: 2000 });
			return;
		}
	$('#cgxx_M7').combobox('setValue',$('#cgxx_M7').combobox('getText'));
	$("#inputForm").submit(); 
}
$(function(){
	var flag=true;
	$('#inputForm').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
	    	if(isValid&&flag){
	    		flag=false;
	    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
	    		return true;
	    	}
			return false;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	$.jBox.closeTip();
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});
	
	</script>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/cgxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${cglist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-30" colspan="3">
						<input value="${cglist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;" class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr >
					<td class="width-20 active"><label class="pull-right">位号：</label></td>
					<td class="width-30" colspan="3">
						<input name="M9"  class="easyui-textbox" value="${cglist.m9 }" style="width: 100%;height: 30px;"
								data-options="required:'true',validType:['length[0,50]']" />
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">储罐名称：</label></td>
					<td class="width-30" colspan="3">
						<input name="M1" class="easyui-textbox" value="${cglist.m1 }" style="width: 100%;height: 30px;" data-options="required:'true',validType:'length[0,25]'" />
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">存储物料名称：</label></td>
					<td class="width-30" colspan="3">
						<input name="M7" id="cgxx_M7"  class="easyui-combobox"
								value="${cglist.m7 }" style="width: 100%;height: 30px;"  data-options="editable:true ,validType:'length[0,100]',valueField: 'id',textField: 'text',url:'${ctx}/bis/wlxx/wldict',
								filter: function (q, row) { //q：文本框内你输入的值，row：列表数据集合；
					                var opts = $(this).combobox('options');
					                return row[opts.textField].toLowerCase().indexOf(q.toLowerCase()) >= 0;
					            },
           						 onHidePanel: function () {
					                var txt_Box = $('#cgxx_M7').combobox('getText');
					                var listdata = $.data(this, 'combobox');
					                var rowdata = listdata.data;
					                for (var i = 0; i < rowdata.length; i++) {
					                    var _row = rowdata[i];
					                    var txt_Val = _row['text'];
					                    if (txt_Val.toLowerCase() == txt_Box.toLowerCase()) {
					                        $('#cgxx_M7').combobox('setValue', _row['id']);
					                        $('#cgxx_M7').val(_row['id']);
					                        return;
					                    }
					                }
					            },
						        onChange: function(rec){
									$.ajax({
							           url:'${ctx}/bis/wlxx/wlname2',
							           data:{'text':rec},
							           type : 'post',
							           dataType: 'json',
							           contentType:'application/x-www-form-urlencoded; charset=UTF-8',
							           success: function (data){
							                $('#cgxx_M8').textbox('setValue', data.cas);
							           }
							     	});
							    } " />
					</td>
				</tr>
								<tr>
					<td class="width-20 active"><label class="pull-right">危化品类别：</label></td>
					<td class="width-85" colspan="3">
					<input class="easyui-combobox" name="ID3" value="${cglist.ID3 }" style="width: 100%;height: 30px;"
								data-options="
								editable:false ,valueField: 'id',textField: 'text',url:'${ctx}/tcode/dict/wxhxpfl' " />
					</td>
					
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">储罐类型：</label></td>
					<td class="width-30"><input name="M2" class="easyui-combobox" style="width: 100%;height: 30px;"
								value="${cglist.m2 }" data-options="panelHeight:'auto', editable:false ,data: [
									{value:'1',text:'立式圆筒形储罐'},
									{value:'2',text:'卧式圆筒形储罐'},
									{value:'3',text:'球形储罐'},
									{value:'4',text:'其他储罐'}]" /></td>
					<td class="width-20 active"><label class="pull-right">材质：</label></td>
					<td class="width-30" ><input class="easyui-combobox" name="M4" value="${cglist.m4 }"
								style="width: 100%;height: 30px;"
								data-options=" panelHeight:'auto',editable:false,data: [
									{value:'1',text:'滚塑'},
							        {value:'2',text:'玻璃钢'},
							        {value:'3',text:'碳钢'},
							        {value:'4',text:'陶瓷'},
							        {value:'5',text:'橡胶'},
							        {value:'7',text:'不锈钢'},
							        {value:'6',text:'其他'} ]" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">罐径（m）：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="M10" value="${cglist.m10 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
					<td class="width-20 active"><label class="pull-right">罐高（m）：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="M11" value="${cglist.m11 }"
												style="width: 100%;height: 30px;"
												data-options="required:'true',validType:'mone'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">容积（㎥）：</label></td>
					<td class="width-30"><input name="M3" class="easyui-textbox" value="${cglist.m3 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
					<td class="width-20 active"><label class="pull-right">储罐区面积(㎥)：</label></td>
					<td class="width-30" >
							<input name="M12" class="easyui-textbox" value="${cglist.m12 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" />
						</td>
					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">有无防火堤：</label></td>
					<td class="width-30" ><input class="easyui-combobox" name="M13" value="${cglist.m13 }"
								style="width: 100%;height: 30px;"
								data-options="panelHeight:'auto',
								editable:false ,data: [
									{value:'1',text:'有'},
							        {value:'0',text:'无'} ],
										onLoadSuccess: function(){
								        	if(${cglist.m13 eq '1'}){
								        	}else{
								        		$('#M14').textbox({ required:false });
								        		$('#M14').textbox({ disabled:true });
								        		 $('#M14').val('');
								        		 $('#m14_td1').hide();
								        		 $('#m14_td2').hide();
								        	}
								        },
										onSelect: function(rec){
								        	if(rec.value=='1'){
								        		$('#M14').textbox({ disabled:false });
								        		$('#M14').textbox({ required:true });
								        		$('#m14_td1').show();
								        		$('#m14_td2').show();
								        	}else{
								        		$('#M14').textbox({ required:false });
								        		$('#M14').textbox({ disabled:true });
								        		 $('#M14').textbox('setValue','');
								        		 $('#m14_td1').hide();
								        		 $('#m14_td2').hide();
								        	}
								        }
	    					" /></td>
					<td class="width-20 active"><label class="pull-right">防火堤所围面积(㎥)：</label></td>
					<td class="width-30" >
							<input id="M14" name="M14" class="easyui-textbox" value="${cglist.m14 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" />
						</td>
					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">火灾危险性等级：</label></td>
					<td class="width-30" ><input class="easyui-combobox" name="M6" value="${cglist.m6 }"
								style="width: 100%;height: 30px;"
								data-options="panelHeight:'auto',
								editable:false ,data: [
							        {value:'1',text:'甲类'},
							        {value:'2',text:'乙类'},
							        {value:'3',text:'丙类'},
							        {value:'4',text:'丁类'},
							        {value:'5',text:'戊类'} ]
	    					" /></td>
					<td class="width-20 active"><label class="pull-right">CAS号：</label></td>
					<td class="width-30" >
							<input class="easyui-textbox" name="M8" id="cgxx_M8" value="${cglist.m8 }"
								style="width: 100%;height: 30px;"
								data-options="required:'true',validType:'length[0,50]'" />
						</td>
					
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
					<div id="uploader_ws_m15">
					    <div id="m15fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">图纸附件：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m16">
					    <div id="m16fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<c:if test="${not empty cglist.ID}">
					<input type="hidden" name="ID" value="${cglist.ID}" />
					<input type="hidden" name="ID1" value="${cglist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${cglist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${cglist.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	uploadImgFlag=false;//是否上传图片
	
	var $ = jQuery,
    $list = $('#m15fileList'); //图片上传
	$list2 = $('#m16fileList'); //文件上传
	
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
			uploadImgFlag=true;
		    // 创建缩略图
		    uploader.makeThumb( file, function( error, src ) {
		        if ( error ) {
		            $img.replaceWith('<span>不能预览</span>');
		            return;
		        }
	
		        $img.attr( 'src', src );
		    }, 100, 100 );
			
			
			var newurl=res.url+"||"+res.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M15" value="'+newurl+'"/>');
			
			$('#uploader_ws_m15').append( $input );
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
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M16" value="'+newurl+'"/>');
			
			$('#uploader_ws_m16').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	
	
	if('${action}' == 'update'){
		var zpUrl = '${cglist.m15}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M15" value="'+arry[i]+'"/>');
				$('#uploader_ws_m15').append( $input );
			}
		}
		
		var fjUrl = '${cglist.m16}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M16" value="'+arry[i]+'"/>');
				$('#uploader_ws_m16').append( $input );
			}
		}
		isuploadImg();
	}

	//判断是否上传图片
	function isuploadImg(){
		var img=$("input[name='M15']").val();
		if(img==null||img==""){
			uploadImgFlag=false;
		}else{
			uploadImgFlag=true;
		}
	}
	</script>
</body>
</html>