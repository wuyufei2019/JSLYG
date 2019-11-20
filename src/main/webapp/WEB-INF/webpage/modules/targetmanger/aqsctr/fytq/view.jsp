<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>费用提取管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">年度：</label></td>
					<td class="width-35" colspan="3">
						${fytq.m1 }
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">销售收入(万元)：</label></td>
					<td class="width-35">${fytq.m2 }</td>
					<td class="width-15 active"><label class="pull-right">行业类型：</label></td>
					<td class="width-35">${fytq.m3}</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">提取标准：</label></td>
					<td class="width-35" colspan="3" height="80px" >
						${fytq.m4 }
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">提取数：</label></td>
					<td class="width-35" colspan="3">
						${fytq.m5 }
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" height="80px" >${fytq.m6 }</td>
				</tr>
				
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
</script>
</body>
</html>