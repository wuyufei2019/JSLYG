<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>储罐管理</title>
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
				<tr >
					<td class="width-20 active"><label class="pull-right">存储物料名称：</label></td>
					<td class="width-30" colspan="3">
						${cglist.m7 }
					</td>
				</tr>
								<tr>
					<td class="width-15 active"><label class="pull-right">危化品类别：</label></td>
					<td class="width-85" colspan="3">
					${cglist.ID3 }
					</td>
					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">罐径（m）：</label></td>
					<td class="width-30">${cglist.m10 }</td>
					<td class="width-20 active"><label class="pull-right">罐高（m）：</label></td>
					<td class="width-30">${cglist.m11 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">储罐类型：</label></td>
					<td class="width-30">${cglist.m2 }</td>
					<td class="width-20 active"><label class="pull-right">容积（㎥）：</label></td>
					<td class="width-30">${cglist.m3 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">材质：</label></td>
					<td class="width-30" >${cglist.m4 }</td>
					<td class="width-20 active"><label class="pull-right">储罐区面积（㎥）：</label></td>
					<td class="width-30" >
							${cglist.m12 }
						</td>
					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">有无防火堤：</label></td>
					<td class="width-30" >${cglist.m13 }</td>
					<td class="width-20 active"><label class="pull-right">防火堤所围面积（㎥）：</label></td>
					<td class="width-30" >
							${cglist.m14 }
						</td>
					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">火灾危险性等级：</label></td>
					<td class="width-30" >${cglist.m6 }</td>
					<td class="width-20 active"><label class="pull-right">CAS号：</label></td>
					<td class="width-30" >
							${cglist.m8 }
						</td>
					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
					 <c:if test="${not empty cglist.m15}">
					 <c:forTokens items="${cglist.m15}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" width="300px;"/><br/>${urlna[0]}</a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">图纸附件：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty cglist.m16}">
					 <c:forTokens items="${cglist.m16}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				</tbody>
			</table>
	 </form>
<script type="text/javascript">
	
</script>
</body>
</html>