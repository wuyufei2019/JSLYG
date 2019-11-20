<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事故调查证据查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">调查主题：</label></td>
				    <td class="width-85" >${sgdczj.dczt }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
				    <td class="width-85" >${sgdczj.qyname }</td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">证据：</label></td>
					<td class="width-85" >${sgdczj.m1}</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">照片：</label></td>
					<td class="width-85" >
					 <c:if test="${not empty sgdczj.m2}">
					 <c:forTokens items="${sgdczj.m2}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" style="max-width: 200px;"/><br/>${urlna[1]}</a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
			  </tbody>
		</table>
	 </form>
</body>
</html>