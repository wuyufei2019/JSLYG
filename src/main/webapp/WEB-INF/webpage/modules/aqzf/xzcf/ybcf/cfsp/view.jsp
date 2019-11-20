<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>处罚审批查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<form id="inputForm" action="${ctx}/xzcf/ybcf/cfsp/${action}"  method="post" class="form-horizontal" >
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>
		<tr>
			<td class="width-15 active"><label class="pull-right">案件名称：</label></td>
			<td class="width-35" colspan="3"><input type="text" id="casename" name="casename" class="easyui-textbox" value="${cfsp.casename }"  style="height: 30px;width: 100%" data-options="editable:false"/></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
			<td class="width-35"><input id="dsperson" name="dsperson" type="text"  class="easyui-textbox" value="${cfsp.dsperson}"  data-options="editable:false" style="height: 30px;width: 100%" /></td>
			<td class="width-15 active"><label class="pull-right">立案编号：</label></td>
			<td class="width-35"><input id="M1" name="M1" type="text" value="${cfsp.m1}"  class="easyui-textbox" data-options="editable:false" style="height: 30px;width: 100%" /></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">案件经办人：</label></td>
			<td class="width-35"><input id="M6" name="M6" class="easyui-textbox" value="${cfsp.m6 }" style="height: 30px;width: 100%;" data-options="editable:false" /></td>
			<td class="width-15 active"><label class="pull-right">时间：</label></td>
			<td class="width-35"><input id="M3" name="M3" class="easyui-datetimebox" value="${cfsp.m2 }" style="height: 30px;width: 100%;" data-options="editable:false" /></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">办案部门负责人意见：</label></td>
			<td class="width-35" colspan="3"><input id="M7" name="M7" type="text"  class="easyui-textbox" value="${cfsp.m7 }"  data-options="editable:false" style="height: 80px;width: 100%" /></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">法制部门合法性审查意见：</label></td>
			<td class="width-35" colspan="3"><input id="M8" name="M8" type="text"  class="easyui-textbox" value="${cfsp.m8 }"  data-options="editable:false" style="height: 80px;width: 100%" /></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">分管领导：</label></td>
			<td class="width-35" colspan="3"><input id="M9" name="M9" type="text"  class="easyui-textbox" value="${cfsp.m9 }"  data-options="editable:false" style="height: 30px;width: 100%" /></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">分管领导审核意见：</label></td>
			<td class="width-35" colspan="3"><input id="M10" name="M10" type="text"  class="easyui-textbox" value="${cfsp.m10 }"  data-options="editable:false" style="height: 30px;width: 100%" /></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">主要领导：</label></td>
			<td class="width-35" colspan="3"><input id="M11" name="M11" type="text"  class="easyui-textbox" value="${cfsp.m11 }"  data-options="editable:false" style="height: 30px;width: 100%" /></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">主要领导审核意见：</label></td>
			<td class="width-35" colspan="3"><input id="M12" name="M12" type="text"  class="easyui-textbox" value="${cfsp.m12 }"  data-options="editable:false" style="height: 30px;width: 100%" /></td>
		</tr>
		</tbody>
	</table>
</form>
</body>
</html>