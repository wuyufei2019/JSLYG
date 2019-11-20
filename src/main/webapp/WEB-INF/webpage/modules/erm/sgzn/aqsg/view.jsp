<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全事故信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
					<td class="width-35">连云港市${aqsg.m0 }局</td>
					<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${aqsg.m0_1 }"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">报告简况：</label></td>
					<td class="width-35" colspan="3">${aqsg.m1 }（单位）发生了一起${aqsg.m2 }事故</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">地点：</label></td>
					<td class="width-35" >${aqsg.m4 }县（市、区）${aqsg.m4_1 }镇（街道、园区）</td>
					<td class="width-15 active"><label class="pull-right">事故时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${aqsg.m3 }"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">涉险总人数：</label></td>
					<td class="width-35">${aqsg.m5 }</td>
					<td class="width-15 active"><label class="pull-right">死亡人数：</label></td>
					<td class="width-35">${aqsg.m6 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">失踪人数：</label></td>
					<td class="width-35">${aqsg.m7 }</td>
					<td class="width-15 active"><label class="pull-right">被困人数：</label></td>
					<td class="width-35">${aqsg.m8 }</td>
				</tr>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">受伤人数：</label></td>
					<td class="width-35">${aqsg.m9 }</td>
					<td class="width-15 active"><label class="pull-right">中毒人数：</label></td>
					<td class="width-35">${aqsg.m10 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">事故单位名称：</label></td>
					<td class="width-35">${aqsg.m1}</td>
					<td class="width-15 active"><label class="pull-right">经济类型：</label></td>
					<td class="width-35" colspan="3">${aqsg.m11 }</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">事故简况：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${aqsg.m12}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应对处置情况：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${aqsg.m13}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${aqsg.m14}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告人：</label></td>
					<td class="width-35">${aqsg.m15 }</td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35">${aqsg.m16 }</td>
				</tr>
				
				</tbody>
			</table>
       </form>
</body>
</html>