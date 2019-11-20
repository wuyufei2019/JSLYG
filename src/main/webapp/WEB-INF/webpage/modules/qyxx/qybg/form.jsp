<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>企业变更</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm"  class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>

            <tr>
               <td class="width-15 active"><label class="pull-right">企业名称：</label></td>
               <td class="width-35"><div>
                     <input value="${entity.qyid }" id="qyid" name="qyid" style="width: 100%;height: 30px;" class="easyui-combobox"
                        data-options="readonly: true,required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
                  </div></td>

               <td class="width-15 active"><label class="pull-right">变更事项：</label></td>
               <td class="width-35"><input name="modification" id="modification" style="width: 100%;height: 30px;" class="easyui-combobox"
                     value="${entity.modification }"
                     data-options="valueField: 'value',textField: 'text',panelHeight:'auto',editable:false,
                     data:[{text:'关停增减',value:'关停增减'}, {text:'辖区转移',value:'辖区转移'}, {text:'信息变更',value:'信息变更'}],
                     validType:['length[0,10]']" /></td>
            </tr>
            <tr>
                     <td class="width-15 active"><label class="pull-right">变更内容：</label></td>
      				 <td class="width-35"  colspan="3"><input name="content" id="content" style="width: 100%;height: 80px;" class="easyui-textbox"
      					 value="${entity.content }" data-options="multiline: true, validType:['length[0,250]']" /></td>
                 </tr>  
                 <tr>
                     <td class="width-15 active"><label class="pull-right">申请人：</label></td>
      				 <td class="width-35" ><input name="applyer" id="applyer" style="width: 100%;height: 30px;" class="easyui-textbox"
      					 value="${entity.applyer }" data-options=" validType:['length[0,25]']" /></td>
                 </tr>
				<c:if test="${not empty entity.ID}">
					<input type="hidden" name="ID" value="${entity.ID}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${entity.s3}" />
				</c:if>
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
		
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var action='${action}';
		function doSubmit() {
			var obj = $("#inputForm").serializeObject();
			var data = Object.assign({bisjson : JSON.stringify(Object.assign(parent.$("#inputForm").serializeObject()))},obj);
			$.ajax({
				type : 'POST',
				url : "${ctx}/bis/qybg/" + action,
				data : data,
				success : function(data) {
					$.jBox.closeTip();
					if (data == 'success') {
						parent.layer.open({
							icon : 1,
							title : '提示',
							offset : 'rb',
							content : '信息已提交,请耐心等待审核！',
							shade : 0,
							time : 2000
						});
						parent.layer.close(index);//关闭对话框。
					} else {
						parent.layer.open({
							icon : 2,
							title : '提示',
							offset : 'rb',
							content : '操作失败！',
							shade : 0,
							time : 2000
						});
					}
				},
				beforeSend : function() {
					var isValid = $("#inputForm").form('validate');
					if (isValid) {
						$.jBox.tip("正在提交，请稍等...", 'loading', {
							opacity : 0
						});
						return true;
					}
					return false; // 返回false终止表单提交
				}
			});
		}
		$(function() {
		    if('${qyid}'){
				$("#qyid").combobox("setValue",'${qyid}');
		    }
		    if('${username}'){
				$("#applyer").textbox("setValue",'${username}');
		    }
		});
	</script>

</body>
</html>