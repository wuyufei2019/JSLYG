<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>地质灾难</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
					<td class="width-35">连云港市${dzzn.m0 }局</td>
					<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dzzn.m0_1 }"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">报告简况：</label></td>
					<td class="width-35" colspan="3">${dzzn.m4 }县（市、区）${dzzn.m4_1 }镇（街道、园区）发生了一起${dzzn.m2 }地质灾害</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">位置：</label></td>
					<td class="width-35" >${dzzn.m4 }县（市、区）${dzzn.m4_1 }镇（街道、园区）</td>
					<td class="width-15 active"><label class="pull-right">灾情时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dzzn.m3 }"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">地质灾害类型：</label></td>
					<td class="width-35">${dzzn.m5 }</td>
					<td class="width-15 active"><label class="pull-right">受损房屋间数（间）：</label></td>
					<td class="width-35">${dzzn.m10 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">受威胁转移人数：</label></td>
					<td class="width-35">${dzzn.m7 }</td>
					<td class="width-15 active"><label class="pull-right">死亡人数：</label></td>
					<td class="width-35">${dzzn.m6 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">受伤人数：</label></td>
					<td class="width-35">${dzzn.m9 }</td>
					<td class="width-15 active"><label class="pull-right">被困、失踪人数：</label></td>
					<td class="width-35">${dzzn.m8 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">地质灾害简况：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${dzzn.m12}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应对处置情况：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${dzzn.m13}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${dzzn.m14}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告人：</label></td>
					<td class="width-35">${dzzn.m15 }</td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35">${dzzn.m16 }</td>
				</tr>
				
				</tbody>
			</table>
       </form>
</body>
</html>