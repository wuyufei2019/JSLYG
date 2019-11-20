<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>台风信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
					<td class="width-35">连云港市${tfxx.m0 }局</td>
					<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${tfxx.m0_1 }"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">报告简况：</label></td>
					<td class="width-35" colspan="3">${tfxx.m1 }（区域）发生了一起${tfxx.m2 }灾害</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">灾害地点：</label></td>
					<td class="width-35">${tfxx.m4 }县（市、区）</td>
					<td class="width-15 active"><label class="pull-right">灾情时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${tfxx.m3 }"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">受热带气旋影响小时数：</label></td>
					<td class="width-35" >${tfxx.m5 }小时内可能或者已经受热带气旋影响</td>
					<td class="width-15 active"><label class="pull-right">沿海或者陆地平均风力等级：</label></td>
					<td class="width-35" >${tfxx.m6 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">阵风等级：</label></td>
					<td class="width-35" >${tfxx.m7 }</td>
					<td class="width-15 active"><label class="pull-right">是否可能持续：</label></td>
					<td class="width-35" >${tfxx.m7_1 }</td>	
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">受台风影响，预报暴雨情况：</label></td>
					<td class="width-35" colspan="3">${tfxx.m8 }小时内降雨量${tfxx.m8_1}以上</td>		
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right"></label></td>
					<td class="width-35" >已达${tfxx.m8_2 }毫米以上降雨量</td>
					<td class="width-15 active"><label class="pull-right">降雨是否可能持续：</label></td>
					<td class="width-35" >${tfxx.m8_3 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">受台风影响，沿海潮位情况：</label></td>
					<td class="width-35" >${tfxx.m9 }河流潮位是否超过${tfxx.m9_1 }</td>
					<td class="width-15 active"><label class="pull-right">河流潮位是否超过：</label></td>
					<td class="width-35" >${tfxx.m9_2 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right"></label></td>
					<td class="width-35" >沿海高潮位是否达到${tfxx.m10}警戒潮位</td>
					<td class="width-15 active"><label class="pull-right">沿海高潮位是否达到：</label></td>
					<td class="width-35" >${tfxx.m10_1 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">水旱简况：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${tfxx.m12}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应对处置情况：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${tfxx.m13}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${tfxx.m14}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告人：</label></td>
					<td class="width-35">${tfxx.m15 }</td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35">${tfxx.m16 }</td>
				</tr>
				
				</tbody>
			</table>
       </form>
</body>
</html>