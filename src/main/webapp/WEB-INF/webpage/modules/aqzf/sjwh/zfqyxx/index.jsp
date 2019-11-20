<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title></title>
<meta name="decorator" content="default" />
</head>
<body>
	<div>
			<form id="inputForm" class="form-horizontal">
				<table
					class="table table-bordered  dataTables-example dataTable no-footer"
					style="margin:0 auto;min-width:900px;width:1000px;">
					<tbody>
						<tr>
							<td class="width-20 active"><label class="pull-right"><font color="red">*</font>企业名称：</label></td>
							<td class="width-80" colspan="3"><input id="M1" name="M1" class="easyui-textbox" value=""  style="width:100%;height:30px;"
								data-options="required:'true',validType:'length[1,100]'" /></td>
						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">法定代表人：</label></td>
							<td class="width-30"><input id="M5" name="M5" style="width:100%;height:30px;" class="easyui-textbox"
								value="" data-options="validType:'length[1,50]'" /></td>
							<td class="width-20 active"><label class="pull-right">联系电话：</label></td>
							<td class="width-30"><input id="M6" name="M6" style="width:100%;height:30px;" class="easyui-textbox"
								value="" data-options="validType:'mobileAndTel' " /></td>
						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">注册地址：</label></td>
							<td class="width-80" colspan="3"><input id="M8" name="M8" class="easyui-textbox"
								value="" style="width:100%;height:30px;" data-options="validType:'length[1,100]'" /></td>
							
						</tr>
						<tr>
							
							<td class="width-20 active"><label class="pull-right">邮政编码：</label></td>
							<td class="width-30"><input id="M9" name="M9" class="easyui-textbox"
								value="" data-options="validType:'ZIP'" style="width:100%;height:30px;"/></td>
						</tr>
					</tbody>
				</table>
				<div style="text-align:center;margin: 20px;">
					<a  class="btn btn-primary"  onclick="updateInfor()" style="width:120px">保存企业信息</a>				
				</div>
			</form>
	</div>


	<script type="text/javascript">
	
	$.fn.validatebox.defaults.rules.remote.message = '企业名称已存在！';
		//企业名称校验
		$('#M1').textbox({    
		    required: true,    
		    validType:{
		    	length:[1,100],
		    	remote:["${ctx}/bis/qyjbxx/checkName","qyName"]
		    }
		}); 

	
	
	
		var flag=true;
		//保存
		function updateInfor() {
			if($("#inputForm").form('validate')&&flag){
				confirmx('确定添加企业信息无误吗?', function(index) {
					
					flag=false;
					$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
					
					$.ajax({
						type : 'post',
						url : '${ctx}/bis/qyjbxx/zfadd',
						data : $("#inputForm").serialize(),
						success : function(data) {
							$.jBox.closeTip();
							layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 3000 });
							flag=true;
							$("#M1").textbox("setValue","");
							$("#M5").textbox("setValue","");
							$("#M6").textbox("setValue","");
							$("#M8").textbox("setValue","");
							$("#M9").textbox("setValue","");
						},
						error : function(data) {
							$.jBox.closeTip();
							layer.open({icon:1,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 3000 });
							flag=true;
						}
					});
				});
			}
		}
	 
	
</script>

</body>
</html>