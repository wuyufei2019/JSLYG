<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>安全计划信息管理</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
</head>
<body>
	<form id="inputForm" action="${ctx}/aqzf/jcjh/${action}"
		method="post" class="form-horizontal">
		<table
			class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">计划时间：</label></td>
					<td class="width-35" ><input style="height: 30px; width: 100%;" type="text" class="easyui-textbox" value="${jcjh.jhsj }" data-options="readonly:'true'" />
					</td>
					<td class="width-15 active"><label class="pull-right">企业分组：</label></td>
					<td class="width-35"><input type="text" class="easyui-textbox" style="height: 30px; width: 100%;" value="${jcjh.sd}" data-options="readonly:'true'" /></td>
				</tr>
                <%-- <tr>
					<td class="width-15 active"><label class="pull-right">行业类型：</label></td>
					<td class="width-35"><input type="text" class="easyui-textbox" style="height: 30px; width: 100%;" value="${jcjh.hylx}" data-options="readonly:'true'" /></td>
					<td class="width-15 active"><label class="pull-right">检查科室：</label></td>
					<td class="width-35"><input type="text" class="easyui-textbox" style="height: 30px; width: 100%;" value="${jcjh.m5}" data-options="readonly:'true'" /></td>
				</tr> --%>
				<tr>
					<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-85" colspan="3"><input value="${pe.ID2 }" id="ID2" name="ID2" style="width: 100%;height: 30px;" class="easyui-combobox"
									data-options="required:'true', panelHeight:'140px',valueField: 'id',textField: 'text',url:'${ctx}/aqzf/jcjh/qyidjson?xzqy=${jcjh.m3 }&jglx=${jcjh.m4 }'" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">行政执法人员：</label></td>
					<td class="width-85" colspan="3"><input type="text" name="M2" class="easyui-combobox" value="${pe.m2 }" style="width: 100%;height: 30px;" 
									data-options="required:'true', panelHeight:'140px', editable:false ,valueField: 'text', multiple:true, textField: 'ryfz',url:'${ctx}/aqzf/zfry/idlist' "/></td>
				</tr>

				<input type="hidden" name="ID" value="${pe.ID}" />
				<input type="hidden" name="ID1" value="${pe.ID1}" />
				<input type="hidden" name="M1" value="${pe.m1}" />
			</tbody>
		</table>
	</form>
<script type="text/javascript">	
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var validateForm;

		function doSubmit() {
			$("#inputForm").serializeObject();
			$("#inputForm").submit();
		}

		$(function() {
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
				success : function(data) {
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