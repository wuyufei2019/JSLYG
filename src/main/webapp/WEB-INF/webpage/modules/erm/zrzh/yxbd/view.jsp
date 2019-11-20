<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>雨雪冰冻</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
					<td class="width-35">连云港市${yxbd.m0 }局</td>
					<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${yxbd.m0_1 }"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">报告简况：</label></td>
					<td class="width-35" colspan="3">${yxbd.m1 }发生${yxbd.m2 }雨雪冰冻灾害</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">位置：</label></td>
					<td class="width-35" >${yxbd.m4 }县（市、区）${yxbd.m4_1 }镇（街道、园区）</td>
					<td class="width-15 active"><label class="pull-right">灾情时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${yxbd.m3 }"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">24小时降雪量<br>（毫米）：</label></td>
					<td class="width-35">${yxbd.m5 }</td>
					<td class="width-15 active"><label class="pull-right">积雪深度（厘米）：</label></td>
					<td class="width-35">${yxbd.m7 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">日平均气温在o℃以下连续天数（天）：</label></td>
					<td class="width-35">${yxbd.m6}</td>
					<td class="width-15 active"><label class="pull-right">雨凇、电线积冰直径或地面结冰厚度<br>（毫米）：</label></td>
					<td class="width-35">${yxbd.m8 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">预报雨雪天气是否持续：</label></td>
					<td class="width-35" colspan="3">${yxbd.m9 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">雨雪冰冻简况：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${yxbd.m12}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应对处置情况：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${yxbd.m13}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${yxbd.m14}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告人：</label></td>
					<td class="width-35">${yxbd.m15 }</td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35">${yxbd.m16 }</td>
				</tr>
				
				</tbody>
			</table>
       </form>
</body>
</html>