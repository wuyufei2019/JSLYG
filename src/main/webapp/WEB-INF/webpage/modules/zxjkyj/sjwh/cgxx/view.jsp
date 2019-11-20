<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>储罐信息查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">位号：</label></td>
					<td class="width-30" colspan="3">
						${cglist.m9 }
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">储罐名称：</label></td>
					<td class="width-30" colspan="3">
						${cglist.m1 }
					</td>
				</tr>
		
				<tr>
					<td class="width-20 active"><label class="pull-right">罐径（m）：</label></td>
					<td class="width-30">${cglist.m10 }</td>
					<td class="width-20 active"><label class="pull-right">罐高（m）：</label></td>
					<td class="width-30">${cglist.m11 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">容积（㎥）：</label></td>
					<td class="width-30" colspan="3">${cglist.m3 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">一级液位预警比例（小数）：</label></td>
					<td class="width-30" >${cglist.level1 }</td>
					<td class="width-20 active"><label class="pull-right">二级液位预警比例（小数）：</label></td>
					<td class="width-30" >
							${cglist.level2 }
						</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">一级高温预警（℃）：</label></td>
					<td class="width-30" >${cglist.temperature1 }</td>
					<td class="width-20 active"><label class="pull-right">二级高温预警（℃）：</label></td>
					<td class="width-30" >
							${cglist.temperature2 }
						</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">一级高压预警（MPa）：</label></td>
					<td class="width-30" >${cglist.pressure1 }</td>
					<td class="width-20 active"><label class="pull-right">二级高压预警（MPa）：</label></td>
					<td class="width-30" >
							${cglist.pressure2 }
						</td>
				</tr>
				<c:if test="${usertype eq '9'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">液位点号 ：</label></td>
					<td class="width-30" >${cglist.r1 }</td>
					<td class="width-20 active"><label class="pull-right">温度点号 ：</label></td>
					<td class="width-30" >
							${cglist.r2 }
						</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">压力点号 ：</label></td>
					<td class="width-30" >${cglist.r3 }</td>
					<td class="width-20 active"><label class="pull-right">流量点号 ：</label></td>
					<td class="width-30" >
							${cglist.r4 }
						</td>
				</tr>
				</c:if>
				</tbody>
			</table>
	 </form>
<script type="text/javascript">
	
</script>
</body>
</html>