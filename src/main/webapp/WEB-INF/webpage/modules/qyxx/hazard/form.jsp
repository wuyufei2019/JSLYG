<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title></title>
	<script>
		var demo = '';
		var action = "${action}";
		$(function () {
			if (action =='create'){
				demo='x';
			}
			if (action == 'update'){
				demo = "${wxyId}";
			}
			if (action == 'view'){
				demo = "${wxyId}";
				$("#saveButton").hide();
				$("#addButton").hide();
				$("#updateButton").hide();
				$("#deleteButton").hide();
			}

		})
	</script>
<script type="text/javascript" src="${ctx}/static/model/js/qyxx/hazardIdentity/index.js?v=1.2"></script>
</head>
<body>
	<div style="width:850px;height:auto;padding:5px 5px; margin: 0 auto;" >
		<form id="bis_hazard_form_mainform" novalidate>
			<table class="table table-bordered dataTable" style="margin:auto;">
				<tr>
					<td class="width-20 active"><label class="pull-right">危险源名称：</label></td>
					<td class="width-30" colspan="3">
						<input class="easyui-textbox" name="M12" id="bis_hazard_M12"
							   value="${bh.m12 }" style="width:645px;height:30px;"
							   data-options="required:true" />
					</td>

				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">企业档案R值：</label></td>
					<td class="width-30"> <input name="M2" id="bis_hazard_M2" class="easyui-textbox"
								value="${bh.m2 }" style="width:220px;height:30px;"
								data-options="validType:'mone'" />
					</td>
					<td class="width-20 active"><label class="pull-right">重大危险源级别：</label></td>
					<td class="width-30"><div>
							<input class="easyui-combobox" id="bis_hazard_M1" name="M1"
								value="${bh.m1 }" style="width:220px;height:30px;"
								data-options="editable:false ,data: [
								        {value:'1',text:'一级'},
								        {value:'2',text:'二级'},
								        {value:'3',text:'三级'},
								        {value:'4',text:'四级'} ]" />
						</div></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">增加暴露人数：</label></td>
					<td><input id="M9_1" name="M9_1" value="${bh.m9_1 }"
						class="easyui-numberbox" style="width:220px;height:30px;"
						data-options="min:0,validType:'length[1,10]'" /></td>
					<td class="width-20 active"><label class="pull-right">厂区人数：</label></td>
					<td class="width-30"> <input name="M3" class="easyui-textbox" value="${bh.m3 }"
								style="width:220px;height:30px;"
								data-options="validType:['length[0,10]','integ']" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">系统计算R值：</label></td>
					<td class="width-30"> <input class="easyui-textbox" value="${bh.m9 }"
								style="width:220px;height:30px;" data-options="disabled:true" />
					</td>
					<td class="width-20 active"><label class="pull-right">是否有监控预警系统：</label></td>
					<td class="width-30"><div>
							<input class="easyui-combobox" name="M4" value="${bh.m4 }"
								style="width:220px;height:30px;"
								data-options="panelHeight:50,editable:false ,data: [
								        {value:'0',text:'否'},
								        {value:'1',text:'是'} ]
						    " />
						</div></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">主要危险性：</label></td>
					<td class="width-30" colspan="3"> 
							<input class="easyui-combobox" name="M5" id="hcc"
								value="${bh.m5 }" style="width:645px;height:30px;"
								data-options="
								required:true,
								editable:false ,
								valueField: 'text',
								textField: 'text',
								multiple:'true',
								url:'${ctx}/tcode/dict/wxhxpzywx',
								onSelect: 
								function(rec){
								$('#haz').val($('#hcc').combobox('getValues').join(','));
								},
								onLoadSuccess: 
								function(){
								var hazx=$('#haz').val().split(',');
								$('#hcc').combobox('setValues', hazx);
								}
								 " />
				 </td>

				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">联系人：</label></td>
					<td class="width-30"><div>
							<input name="M6" class="easyui-textbox" value="${bh.m6 }"
								style="width:220px;height:30px;"
								data-options="validType:['length[0,50]']" />
						</div></td>
					<td class="width-20 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-30"><div>
							<input name="M7" class="easyui-textbox" value="${bh.m7 }"
								style="width:220px;height:30px;"
								data-options="validType:'mobile' " />
						</div></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">易导致事故类型： </label></td>
					<td class="width-30" align="left" colspan="3">
					<input type="checkbox" id="ydlx1" name="M10" class="i-checks" value="1" title="" lay-skin="primary" />物体打击 
					<input type="checkbox" id="ydlx2" name="M10" class="i-checks" value="2" title="" lay-skin="primary" />车辆伤害 
					<input type="checkbox" id="ydlx3" name="M10" class="i-checks" value="3" title="" lay-skin="primary" />机械伤害
					<input type="checkbox" id="ydlx4" name="M10" class="i-checks" value="4" title="" lay-skin="primary" />起重伤害 
					<input type="checkbox" id="ydlx5" name="M10" class="i-checks" value="5" title="" lay-skin="primary" />触电 
					<input type="checkbox" id="ydlx6" name="M10" class="i-checks" value="6" title="" lay-skin="primary" />淹溺 
					<input type="checkbox" id="ydlx7" name="M10" class="i-checks" value="7" title="" lay-skin="primary" />灼烫
					<input type="checkbox" id="ydlx8" name="M10" class="i-checks" value="8" title="" lay-skin="primary" />火灾 
					<input type="checkbox" id="ydlx9" name="M10" class="i-checks" value="9" title="" lay-skin="primary" />高处坠落 </br>
					<input type="checkbox" id="ydlx12" name="M10" class="i-checks" value="12" title="" lay-skin="primary" />透水
					<input type="checkbox" id="ydlx10" name="M10" class="i-checks" value="10" title="" lay-skin="primary" />坍塌
					<input type="checkbox" id="ydlx11" name="M10" class="i-checks" value="11" title="" lay-skin="primary" />冒顶片帮 
					<input type="checkbox" id="ydlx13" name="M10" class="i-checks" value="13" title="" lay-skin="primary" />放炮 
					<input type="checkbox" id="ydlx14" name="M10" class="i-checks" value="14" title="" lay-skin="primary" />火药爆炸
					<input type="checkbox" id="ydlx15" name="M10" class="i-checks" value="15" title="" lay-skin="primary" />瓦斯爆炸 
					<input type="checkbox" id="ydlx16" name="M10" class="i-checks" value="16" title="" lay-skin="primary" />锅炉爆炸 
					<input type="checkbox" id="ydlx17" name="M10" class="i-checks" value="17" title="" lay-skin="primary" />容器爆炸 
					<input type="checkbox" id="ydlx18" name="M10" class="i-checks" value="18" title="" lay-skin="primary" />其它爆炸</br>
					<input type="checkbox" id="ydlx19" name="M10" class="i-checks" value="19" title="" lay-skin="primary" />中毒和窒息 
					<input type="checkbox" id="ydlx20" name="M10" class="i-checks" value="20" title="" lay-skin="primary" />其它伤害</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
					<div id="uploader_ws_m11">
					    <div id="m11fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">安全监控措施：</label></td>
					<td class="width-30" colspan="3"><input type="text" name="M8"
						value="${bh.m8 }" class="easyui-textbox"
						style="width: 100%;height: 80px;" data-options="multiline:true" />
					</td>

				</tr>
				<c:if test="${not empty bh.ID}">
					<input type="hidden" name="ID" value="${bh.ID}" />
					<input type="hidden" name="ID1" value="${bh.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${bh.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${bh.s3}" />
				</c:if>
				<input type="hidden" id="haz" name="haz" value="${bh.m5}" />
				<input type="hidden" name="ID" id="ID" value="" />
			</table>
		</form>
		<div id="saveButton" style="text-align:center;margin: 20px;">
			<a id="bis_hazard_form_savebt" class="btn btn-primary"
				onclick="bis_hazard_form_save()" style="width:120px">保存</a>
		</div>
	</div>

	<div id="tb" style="padding:5px;height:auto">
		<div class="row">
			<div class="col-sm-12">
				<div class="pull-left">
					<button id="addButton" class="btn btn-white btn-sm" data-toggle="tooltip"
						data-placement="left" onclick="add()" title="添加">
						<i class="fa fa-plus"></i> 添加
					</button>
					<button id="updateButton" class="btn btn-white btn-sm" data-toggle="tooltip"
						data-placement="left" onclick="upd()" title="修改">
						<i class="fa fa-file-text-o"></i> 修改
					</button>
					<button id="deleteButton" class="btn btn-white btn-sm" data-toggle="tooltip"
						data-placement="left" onclick="del()" title="删除">
						<i class="fa fa-trash-o"></i> 删除
					</button>
					<button class="btn btn-white btn-sm" data-toggle="tooltip"
						data-placement="left" onclick="view()" title="查看">
						<i class="fa fa-search-plus"></i> 查看
					</button>
					<%--<button class="btn btn-white btn-sm" data-toggle="tooltip"--%>
						<%--data-placement="left" onclick="fileexport()" title="导出">--%>
						<%--<i class="fa fa-external-link"></i> 导出--%>
					<%--</button>--%>
					<button class="btn btn-white btn-sm " data-toggle="tooltip"
							data-placement="left" onclick="refresh()" title="刷新">
							<i class="glyphicon glyphicon-repeat"></i> 刷新
						</button>
				</div>
			</div>
		</div>
	</div>
	<div style="height:300px">
	<table id="dg"></table>
	</div>
	<div style="display:none">
		<shiro:hasAnyRoles name="企业">
			<input type="hidden" id="bis_qy_qx" value="qy" />
		</shiro:hasAnyRoles>
	</div>
	<script type="text/javascript">
	var dg;
	var $ = jQuery,
    $list = $('#m11fileList'); //图片上传
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

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
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		        $img = $li.find('img');

		    $list.append( $li );
			
			var newurl=res.url+"||"+res.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M11" value="'+newurl+'"/>');
			
			$('#uploader_ws_m11').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
 
	if('${action}' == 'update'){
		var zpUrl = '${qylist.m11}';
		if(zpUrl!=null&&zpUrl!=''){
			var arry =zpUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_zp_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );

			    $list.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M11" value="'+arry[i]+'"/>');
				$('#uploader_ws_m11').append( $input );
			}
		}
	}
	
	
	

		var url = "";
		var qylist = "${bh.m10}";
		var qylistArr = qylist.split(",");
		var returnId ;
		for (var i = 0; i < qylistArr.length; i++) {
			$("input[name='M10']:checkbox[value='" + qylistArr[i] + "']").attr('checked', 'true');
		}
		$(function() {
			if (action == 'create') {
				$("#bis_yc").hide();
			} else if (action == 'update') {
				$("#bis_yc").show();
				returnId = demo ;
			}else if (action == 'view'){
				returnId = demo ;
			}
	
			$("input[name='M10']:checkbox").css("width", "18px");
			$("input[name='M10']:checkbox").css("height", "18px");
					
			$("#bis_hazard_M1").combobox('readonly',true);
		});
	
		function bis_hazard_form_save() {
	
			// 添加修改区分
			if (action == 'create') {
				url = "${ctx}/bis/hazard/create/";
				$("#bis_hazard_form_id_table_td_div1_01").css('display', 'none');
			} else if (action == 'update') {
				url = "${ctx}/bis/hazard/update/";
			}

			var isValid = $("#bis_hazard_form_mainform").form('validate');
			if (!isValid) {
				return isValid; //返回false终止表单提交
			} else {
				$.ajax({
					type : "POST",
					url : url,
					data : $("#bis_hazard_form_mainform").serialize(),
					success : function(data) {
						returnId = data;
						demo = data;
						$("#ID").val(returnId);
						if (action == 'create'){
							var lj = '${ctx}/bis/hazard/update/';
							window.location = lj+ demo;
						}
						layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
					},
					error : function(e) {
						alert("出错：" + e);
					}

				});
			}
		}

		$(function() {
			if (returnId == ''||returnId==null){
				$("#tb").hide();
			} else {
				$("#tb").show();
			}
			$('#bis_hazard_M2').textbox({  
				  onChange: function(value){  
					  var r = value;
						if (r < 10) {
							$("#bis_hazard_M1").combobox("setValue", "4");
						} else if (10 <= r && r < 50) {
							$("#bis_hazard_M1").combobox("setValue", "3");
						} else if (50 <= r && r < 100) {
							$("#bis_hazard_M1").combobox("setValue", "2");
						} else {
							$("#bis_hazard_M1").combobox("setValue", "1");
						}
				  }  
			}); 
			 
		});
	function doSubmit() {
		$("#inputForm").submit();
		parent.layer.close(index);//关闭对话框。
        parent.window.location.reload();
	}
	</script>
</body>
</html>