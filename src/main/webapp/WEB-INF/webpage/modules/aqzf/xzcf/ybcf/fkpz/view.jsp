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
					<td class="width-20 active"><label class="pull-right">缴批编号：</label></td>
					<td class="width-30" colspan="3">${fkpz.m1 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">当事人：</label></td>
					<td class="width-30" >${fkpz.m2 }</td>	
				   <td class="width-20 active"><label class="pull-right">批准日期：</label></td>
				   <td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd" value="${fkpz.m11 }" /></td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">行政处罚：</label></td>
					<td class="width-30" colspan="3" >${fkpz.m3 }</td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">罚款（大写）：</label></td>
					<td class="width-30" >${fkpz.m4 }</td>	
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">类型：</label></td>
					<td class="width-30" colspan="3" style="height:30px">
						<c:choose>
						<c:when test="${fkpz.m5=='1'}">
							<input type="radio" class="i-checks" disabled checked="checked"/>延期缴纳罚款
							<input type="radio" class="i-checks" disabled />分期缴纳罚款
						</c:when>
						<c:otherwise>
							<input type="radio" class="i-checks" disabled />延期缴纳罚款
							<input type="radio" class="i-checks" disabled checked="checked"/>分期缴纳罚款
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				
				<tr id="z1">
					<td class="width-20 active"><label class="pull-right">延期截止日期：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd" value="${fkpz.m6 }" /></td>	
				</tr>
				
				<tr id="z2">
					<td class="width-20 active"><label class="pull-right">期数：</label></td>
					<td class="width-30" >${fkpz.m7 }</td>
					<td class="width-20 active"><label class="pull-right">分期截止日期：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd" value="${fkpz.m8 }" /></td>	
				</tr>
				<tr id="z3">
					<td class="width-20 active"><label class="pull-right">缴纳罚款（大写）：</label></td>
					<td class="width-30" >${fkpz.m9 }</td>
					<td class="width-20 active"><label class="pull-right">未缴纳罚款（大写）：</label></td>
					<td class="width-30" >${fkpz.m10 }</td>	
				</tr>
			  	</tbody>
			</table>
	 </form>
	 
<script type="text/javascript">
	$(function(){
		if('${fkpz.m5}'==1){
			$('#z1').show();
			$('#z2').hide();
			$('#z3').hide();
		}else{
			$('#z1').hide();
			$('#z2').show();
			$('#z3').show();
		}
	});
</script>
</body>
</html>