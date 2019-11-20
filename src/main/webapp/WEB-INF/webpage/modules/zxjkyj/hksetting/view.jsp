<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						${entity.qyname }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-35">${entity.userId }</td>

				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">设备密码：</label></td>
					<td class="width-35">${entity.passwd }</td>
				</tr>

				</tbody>
			</table>
	</form>
</body>
</html>