<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>集体讨论记录查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  	<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">缴审编号：</label></td>
					<td class="width-30" colspan="3">${fksp.m1 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">案件名称：</label></td>
					<td class="width-30" colspan="3" >${fksp.m2 }</td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">处罚决定书文号：</label></td>
					<td class="width-30" colspan="3" >${fksp.m3 }</td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">当事人：</label></td>
					<td class="width-30" >${fksp.m4 }</td>
					<td class="width-20 active"><label class="pull-right">地址：</label></td>
					<td class="width-30" >${fksp.m5 }</td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">违法事实及处罚决定：</label></td>
					<td class="width-30" colspan="3">${fksp.m6 }</td>					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">申请理由：</label></td>
					<td class="width-30" colspan="3">${fksp.m7 }</td>					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">承办人意见：</label></td>
					<td class="width-30" colspan="3">${fksp.m8 }</td>					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">承办人：</label></td>
					<td class="width-30">${fksp.m9 }</td>
					<td class="width-20 active"><label class="pull-right">承办人：</label></td>
					<td class="width-30">${fksp.m10 }</td>
				</tr>    
	            <tr>
	            	<td class="width-20 active"><label class="pull-right">承办日期：</label></td>
					<td class="width-30"><fmt:formatDate type="date"  value="${fksp.m11 }" /></td>
	           	</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-30" colspan="3" style="height:30px" >
						<c:choose>
						<c:when test="${fksp.m12=='0'}">
							<input type="radio" value="1" class="i-checks" disabled />同意
							<input type="radio" value="0" class="i-checks" checked="checked" disabled />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" checked="checked" disabled />同意
							<input type="radio" value="0" class="i-checks" disabled />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">审核人：</label></td>
					<td class="width-30">${fksp.m13 }</td>
					<td class="width-20 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-30"><fmt:formatDate type="date"  value="${fksp.m14 }" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">审批意见：</label></td>
					<td class="width-30" colspan="3" style="height:30px">
						<c:choose>
						<c:when test="${fksp.m15=='0'}">
							<input type="radio" value="1" class="i-checks" disabled/>同意
							<input type="radio" value="0" class="i-checks" disabled checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" disabled checked="checked" />同意
							<input type="radio" value="0" class="i-checks" disabled />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">审批人：</label></td>
					<td class="width-30">${fksp.m16 }</td>
					<td class="width-20 active"><label class="pull-right">审批日期：</label></td>
					<td class="width-30"><fmt:formatDate type="date"  value="${fksp.m17 }" /></td>
				</tr>					
			  	</tbody>
			</table>
	 </form>
</body>
</html>