<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全操作规程</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">规程编号：</label></td>
					<td class="width-35" colspan="3">${czgc.m2 }</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">规程名称：</label></td>
					<td class="width-35" colspan="3">${czgc.m1 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">版本号：</label></td>
					<td class="width-35">${czgc.m3 }</td>
					<td class="width-15 active"><label class="pull-right">发布日期：</label></td>
					<td class="width-35" ><fmt:formatDate pattern="yyyy-MM-dd" value="${czgc.m4 }" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">制度正文：</label></td>
					<td class="width-35" colspan="3">${czgc.m5 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">附件（单文件）：</label></td>
					<td colspan="3">
						<c:if test="${not empty czgc.m6}">
						<c:set var="url" value="${fn:split(czgc.m6, '||')}" />
							<div  style="margin-bottom: 10px;">
							<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
							</div>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">编辑部门：</label></td>
					<td class="width-35">${czgc.bjbm }</td>
					<td class="width-15 active"><label class="pull-right">适用部门：</label></td>
					<td class="width-35">${czgc.sybm }</td>
				</tr>
				<%-- <tr >
					<td class="width-15 active"><label class="pull-right">录入人：</label></td>
					<td class="width-35" colspan="3">${czgc.lrr }</td>
				</tr> --%>
				<tr>
					<td class="width-15 active"><label class="pull-right">审核人：</label></td>
					<td class="width-35">${czgc.spr }</td>
					<td class="width-15 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${czgc.m11 }" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-35">${czgc.spyj }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">批准人：</label></td>
					<td class="width-35">${czgc.pzr }</td>
					<td class="width-15 active"><label class="pull-right">批准日期：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${czgc.m14 }" /></td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">批准意见：</label></td>
					<td class="width-35">${czgc.pzyj }</td>
				</tr>
				</tbody>
			</table>

	</form>
</body>
</html>