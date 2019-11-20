<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>查看简易处罚告知记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<tr>
					<td class="width-15 active"><label class="pull-right">编号：</label></td>
					<td class="width-35" colspan="3">${jygz.m0 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-35" >${jygz.m1 }</td>
					<td class="width-15 active"><label class="pull-right">处罚告知时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${jygz.m10 }"/></td>	
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">违法行为描述：</label></td>
					<td class="width-85" colspan="3">${jygz.m2 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">证据：</label></td>
					<td class="width-85" colspan="3">${jygz.m3 }</td>
				</tr>
		        <tr>
					<td class="width-15 active"><label class="pull-right">违反条款：</label></td>
					<td class="width-85"  colspan="3">${jygz.m5 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">处罚依据：</label></td>
					<td class="width-85" colspan="3">${jygz.m6 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">行政处罚：</label></td>
					<td class="width-85" colspan="3">${jygz.m7 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系人：</label></td>
				    <td class="width-35">${jygz.m8 }</td>
				    <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
				    <td class="width-35">${jygz.m9 }</td>	    
				</tr>
		</table>
</body>
</html>