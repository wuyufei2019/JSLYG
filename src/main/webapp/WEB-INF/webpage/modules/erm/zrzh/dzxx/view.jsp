<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>地震信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
					<td class="width-35">连云港市${dzxx.m0 }局</td>
					<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dzxx.m0_1 }"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">报告简况：</label></td>
					<td class="width-35" colspan="3">${dzxx.m1 }（区域）发生了一起${dzxx.m2 }地震</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">震源（经纬度）：</label></td>
					<td class="width-35">经度：${dzxx.m4_1 }&nbsp;&nbsp;&nbsp;&nbsp;纬度：${dzxx.m4_2 }</td>
					<td class="width-15 active"><label class="pull-right">灾情时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dzxx.m3 }"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">震级：</label></td>
					<td class="width-35">${dzxx.m4 }</td>
					<td class="width-15 active"><label class="pull-right">涉险总人数：</label></td>
					<td class="width-35">${dzxx.m5 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">死亡人数：</label></td>
					<td class="width-35">${dzxx.m6 }</td>
					<td class="width-15 active"><label class="pull-right">失踪人数：</label></td>
					<td class="width-35">${dzxx.m7 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">被困人数：</label></td>
					<td class="width-35">${dzxx.m8 }</td>
					<td class="width-15 active"><label class="pull-right">受伤人数：</label></td>
					<td class="width-35">${dzxx.m9 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">地震简况：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${dzxx.m12}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应对处置情况：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${dzxx.m13}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${dzxx.m14}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告人：</label></td>
					<td class="width-35">${dzxx.m15 }</td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35">${dzxx.m16 }</td>
				</tr>
				
				</tbody>
			</table>
       </form>
</body>
</html>