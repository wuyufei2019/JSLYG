<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>车间管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">类别：</label></td>
					<td class="width-30" colspan="3">
					${wllist.m11}
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">物料名称：</label></td>
					<td class="width-30" colspan="3">
					${wllist.m1 }
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">年用量/年产量（t）：</label></td>
					<td class="width-30">${wllist.m2 }</td>
					<td class="width-20 active"><label class="pull-right">最大储量（t）：</label></td>
					<td class="width-30">${wllist.m3 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">CAS号：</label></td>
					<td class="width-30">${wllist.m4 }</td>
					<td class="width-20 active"><label class="pull-right">储存方式：</label></td>
					<td class="width-30">${wllist.m5 }</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">危化品类别：</label></td>
					<td class="width-30" colspan="3">
					${wllist.m8 }
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">主要危险性：</label></td>
					<td class="width-30" colspan="3">
					${wllist.m6 }
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">状态：</label></td>
					<td class="width-30" >${wllist.m9 }</td>
					<td class="width-20 active"><label class="pull-right">是否领证：</label></td>
					<td class="width-30" >
							${wllist.m10 }
						</td>
					
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">重点监管：</label></td>
					<td class="width-30" >${wllist.m12 }</td>
					<td class="width-20 active"><label class="pull-right">剧毒：</label></td>
					<td class="width-30" >
							${wllist.m13 }
						</td>
					
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">易制毒：</label></td>
					<td class="width-30" >${wllist.m14 }</td>					
				</tr>				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" >
					${wllist.m7 }
					</td>			
				</tr>
		  	</tbody>
			</table>
       </form>

</body>
</html>