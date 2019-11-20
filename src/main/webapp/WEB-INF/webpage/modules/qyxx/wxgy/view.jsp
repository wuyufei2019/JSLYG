<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
  <head>
    <title>危险工艺</title>
	<meta name="decorator" content="default"/>
  </head>
  
  <body>
  <form class="form-horizontal">
   <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
   				<tbody>
				<tr>
					<td class="width-20 active"> <label class="pull-right">高危工艺：</label> </td>
					<td class="width-30">${label }</td>
					<td class="width-20 active"><label class="pull-right">套数：</label></td>
					<td class="width-30">${qylist.m2 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">反应类型：</label></td>
					<td class="width-30">${info.m1 }</td>
					<td class="width-20 active"><label class="pull-right">重点监控单元：</label></td>
					<td class="width-30">${info.m2 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">工艺简介：</label></td>
					<td class="width-80" colspan="3">${info.m3 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">工艺危险特点：</label></td>
					<td class="width-80" colspan="3">${info.m4 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">典型工艺：</label></td>
					<td class="width-80" colspan="3">${info.m5 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">重点监控工艺参数：</label></td>
					<td class="width-80" colspan="3">${info.m6 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">安全控制的基本要求：</label></td>
					<td class="width-80" colspan="3">${info.m7 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">宜采用的控制方式：</label></td>
					<td class="width-80" colspan="3">${info.m8 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
					 <c:if test="${not empty qylist.m4}">
					 <c:forTokens items="${qylist.m4}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" width="300px;"/><br/>${urlna[1]}</a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">图纸附件：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty qylist.m5}">
					 <c:forTokens items="${qylist.m5}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80" colspan="3">${qylist.m3 }</td>
				</tr>
				</tbody>
			</table>
			</form>
  </body>
</html>
