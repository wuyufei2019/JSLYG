<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>查看强制执行申请记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>
			<c:if test="${action eq 'updateSub'}">
			<tr>
				<td class="width-20 active"><label class="pull-right">编号：</label></td>
				<td class="width-30"  colspan="3">${yqe.number }</td>
			</tr>
			</c:if>
			
			<tr>
				<td class="width-20 active" ><label class="pull-right">签发人:</label></td>
				<td class="width-30" >${yqe.qfr }</td>
                <td class="width-20 active"><label class="pull-right">强制执行日期：</label></td>
                <td class="width-30"><fmt:formatDate value="${yqe.qzzxsj }"/></td>
			</tr>
			<tr>
				<td class="width-20 active" ><label class="pull-right">当事人:</label></td>
				<td class="width-30" >${yqe.dsname }</td>
				<td class="width-20 active"><label class="pull-right">法院名称：</label></td>
                <td class="width-30">${yqe.court }</td>
			</tr>
            <tr>
                <td class="width-20 active"><label class="pull-right">相关材料：</label></td>
                <td class="width-30" colspan="3" >${yqe.clname}</td>
            </tr>
            <tr>
				<td class="width-20 active"><label class="pull-right">联系人：</label></td>
			    <td class="width-30">${yqe.contactname }</td>
			    <td class="width-20 active"><label class="pull-right">联系电话：</label></td>
			    <td class="width-30">${yqe.phone }</td>	    
			</tr>
       	</tbody>
	</table>
</body>
</html>