<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加惩罚告知记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<tr>
					<td class="width-15 active"><label class="pull-right">编号：</label></td>
					<td class="width-35"  colspan="3">${jie.number }</td>
				</tr>
				<tr>
					<td class="width-15 active" ><label class="pull-right">当事人:</label></td>
					<td class="width-35"  >${jie.name }</td>
					<td class="width-15 active"><label class="pull-right">处罚告知时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${jie.punishdate}"/></td>	
				</tr>
		        <tr>
					<td class="width-15 active"><label class="pull-right">案件基本情况：</label></td>
					<td class="width-35" colspan="3">${jie.illegalact }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">违法行为：</label></td>
					<td class="width-35" colspan="3">${jie.wfxw }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">主要证据：</label></td>
					<td class="width-35" colspan="3">${jie.evidence }</td>
				</tr> 
				<tr>
					<td class="width-15 active"><label class="pull-right">违反条款：</label></td>
					<td class="width-35"  colspan="3">${jie.unlaw }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">处罚依据：</label></td>
					<td class="width-35" colspan="3">${jie.enlaw }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">行政处罚：</label></td>
					<td class="width-35" colspan="3">${jie.xzcf }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">详情：</label></td>
					<td class="width-35" colspan="3">${jie.punishresult }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系人：</label></td>
				    <td class="width-35">${jie.contactname }</td>
				    <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
				    <td class="width-35">${jie.phone }</td>	    
				</tr>
		</table>
	</div>
</body>
</html>