<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>救助信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
					<td class="width-35">连云港市${jzxx.m0 }局</td>
					<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${jzxx.m0_1 }"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">报告简况：</label></td>
					<td class="width-35" colspan="3">${jzxx.m4 }县（市、区）${jzxx.m4_1 }镇（街道、园区）遭受${jzxx.m2 }自然灾害的救助情况</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">位置：</label></td>
					<td class="width-35" >${jzxx.m4 }县（市、区）${jzxx.m4_1 }镇（街道、园区）</td>
					<td class="width-15 active"><label class="pull-right">灾情时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${jzxx.m3 }"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">自然灾害类型：</label></td>
					<td class="width-35">${jzxx.m2 }</td>
					<td class="width-15 active"><label class="pull-right">等级：</label></td>
					<td class="width-35">${jzxx.m5 }</td>

				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">紧急转移安置或需紧急生活救助人数：</label></td>
					<td class="width-35">${jzxx.m7 }</td>
					<td class="width-15 active"><label class="pull-right">倒塌和严重损坏房屋数（间）：</label></td>
					<td class="width-35">${jzxx.m10 }</td>

				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">需政府救助人数：</label></td>
					<td class="width-35">${jzxx.m7_1 }</td>
					<td class="width-15 active"><label class="pull-right">死亡人数：</label></td>
					<td class="width-35">${jzxx.m6 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">占灾害发生地县（市、区）农业人口比例（%）：</label></td>
					<td class="width-35">${jzxx.m11 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">自然灾害简况：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${jzxx.m12}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应对处置情况：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${jzxx.m13}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${jzxx.m14}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告人：</label></td>
					<td class="width-35">${jzxx.m15 }</td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35">${jzxx.m16 }</td>
				</tr>
				
				</tbody>
			</table>
       </form>
</body>
</html>