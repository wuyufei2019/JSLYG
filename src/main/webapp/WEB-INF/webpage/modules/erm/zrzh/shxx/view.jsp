<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>水旱信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
					<td class="width-35">连云港市${shxx.m0 }局</td>
					<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${shxx.m0_1 }"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">报告简况：</label></td>
					<td class="width-35" colspan="3">${shxx.m1 }（区域）发生了一起${shxx.m2 }灾害</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">灾害地点：</label></td>
					<td class="width-35">${shxx.m4 }县（市、区）</td>
					<td class="width-15 active"><label class="pull-right">灾情时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${shxx.m3 }"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">一般洪涝或发生决口或出现重大险情地段：</label></td>
					<td class="width-35" colspan="3">${shxx.m5 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">受涝范围：</label></td>
					<td class="width-35" colspan="3">${shxx.m6 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">干旱程度：</label></td>
					<td class="width-35" colspan="3">${shxx.m7 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">24小时连续降雨量或1小时降雨量：</label></td>
					<td class="width-35" colspan="3">${shxx.m8 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">主要内河水位（米）：</label></td>
					<td class="width-35" >${shxx.m9 }</td>
					<td class="width-15 active"><label class="pull-right">主要内河水位（级）：</label></td>
					<td class="width-35" >${shxx.m9_1 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">主要内河水位（描述）：</label></td>
					<td class="width-35" colspan="3">${shxx.m9_2 }</td>
				</tr>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">水旱简况：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${shxx.m12}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应对处置情况：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${shxx.m13}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" style="height:100px">${shxx.m14}</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告人：</label></td>
					<td class="width-35">${shxx.m15 }</td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35">${shxx.m16 }</td>
				</tr>
				
				</tbody>
			</table>
       </form>
</body>
</html>