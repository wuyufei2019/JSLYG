<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>执法人员信息查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">姓名：</label></td>
					<td class="width-35" >${zfry.m1}</td>
					<td class="width-15 active"><label class="pull-right">性别：</label></td>
					<td class="width-35" >${zfry.m2}</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">证件号：</label></td>
					<td class="width-35" >${zfry.m3}</td>
					<td class="width-15 active"><label class="pull-right">职称：</label></td>
					<td class="width-35" >${zfry.m4}</td>
				</tr>

                <tr>
					<td class="width-15 active"><label class="pull-right">专家类型：</label></td>
					<td class="width-35" >${zfry.m7}</td>	
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35" >${zfry.m5}</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">人员分组：</label></td>
					<td class="width-35" >${zfry.zm}</td>
					<td class="width-15 active"><label class="pull-right">管辖类型：</label></td>
					<td class="width-35" >${zfry.gxlx}</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>