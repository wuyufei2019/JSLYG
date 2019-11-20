<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事故询问笔录查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				 <tr>
					<td class="width-15 active"><label class="pull-right">询问起始时间：</label></td>
					<td class="width-35">${sgxwbl.m1 }</td>
				    <td class="width-15 active"><label class="pull-right">询问结束时间：</label></td>
					<td class="width-35">${sgxwbl.m2 }</td>
				</tr>
			    <tr>
					<td class="width-15 active"><label class="pull-right">询问次号：</label></td>
					<td class="width-35" colspan="3">${sgxwbl.m0 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">询问地点：</label></td>
					<td class="width-35" colspan="3">${sgxwbl.m3 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">被询问人姓名：</label></td>
				    <td class="width-35">${sgxwbl.m4}</td>
					<td class="width-15 active"><label class="pull-right">性别：</label></td>
				    <td class="width-35">${sgxwbl.m5}</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">年龄：</label></td>
				    <td class="width-35">${sgxwbl.m6 }</td>
				    <td class="width-15 active"><label class="pull-right">身份证号：</label></td>
				    <td class="width-35">${sgxwbl.m7 }</td>	    
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">工作单位：</label></td>
				    <td class="width-35" colspan="3">${sgxwbl.m8 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">职务/工种：</label></td>
				    <td class="width-35">${sgxwbl.m9 }</td>
				    <td class="width-15 active"><label class="pull-right">电话：</label></td>
				    <td class="width-35">${sgxwbl.m11 }</td>	    
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">住址：</label></td>
					<td class="width-35" colspan="3">${sgxwbl.m10 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">询问人：</label></td>
				    <td class="width-35">${sgxwbl.m12 }</td>
				    <td class="width-15 active"><label class="pull-right">记录人：</label></td>
				    <td class="width-35">${sgxwbl.m13 }</td>	    
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">在场人员：</label></td>
				    <td class="width-35" colspan="3">${sgxwbl.m14 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">询问记录：</label></td>
				    <td class="width-35" colspan="3">${sgxwbl.m16 }</td>
				</tr>
			  </tbody>
			</table>
		  	
	 </form>
</body>
</html>