<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>气体信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/zxjkyj/qtxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  <c:if test="${usertype ne '1'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							${qtxx.qyname }
						</td>
					</tr>
			  </c:if>

			  <tr>
				<td class="width-20 active"><label class="pull-right">位号：</label></td>
				<td class="width-30">${qtxx.m1 }</td>
				<td class="width-20 active"><label class="pull-right">气体名称：</label></td>
				<td class="width-30">${qtxx.m2 }</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">气体类型：</label></td>
				<td class="width-30">${qtxx.m3 }</td>
				<td class="width-20 active"><label class="pull-right">一级预警(比例小数)：</label></td>
				<td class="width-30">${qtxx.level1 }</td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">二级预警(比例小数)：</label></td>
				<td class="width-30">${qtxx.level2 }</td>
				<td class="width-20 active"><label class="pull-right">传感器位置：</label></td>
				<td class="width-30">${qtxx.r1 }</td>
			</tr>
			</tbody>
		</table>
     </form>
</body>
</html>