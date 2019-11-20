<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>年度救援训练计划管理</title>
	<meta name="decorator" content="default" />
	<style>
		td .easyui-textbox{width: 100%}
	</style>
</head>
<body>

<form id="inputForm" class="form-horizontal">.
	<table id="bqhtable0" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
		<h1 style="width: 100%;text-align: center;font-weight: bolder">年度应急救援技能训练计划</h1>
		<tr>
			<td class="width-15 active"><label class="pull-right" style="text-align: center;width: 100%">部门：</label></td>
			<td class="width-35">
				${year.depname }
			</td>
			<td class="width-15 active"><label class="pull-right" style="text-align: center;width: 100%">时间：</label></td>
			<td class="width-35">
				<fmt:formatDate value="${year.s1 }"/>
			</td>
		</tr></table>
	<table id="bqhtable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >

		<tr>
			<td colspan="2" style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;"><label>时间</label></td>
			<td style="text-align: center;width: 40%;height: 30px;background: #f5f5f5;"><label>训练内容</label></td>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;flex-shrink: 0"><label>组训方式</label></td>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;flex-shrink: 0"><label>组训责任人</label></td>
		</tr>

		<tr>
			<td rowspan="3" style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><label>一季度</label></td>
			<td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">1月份</td>
			<td style="text-align: center;width: 10%;height: 60px;background: #f5f5f5;">${year.m1 }</td>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">${year.m1_2 }</td>
			<td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;">${year.m1_3 }</td>
		</tr>

		<tr>
			<td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">2月份</td>
			<td style="text-align: center;width: 10%;height: 60px;background: #f5f5f5;">${year.m2 }</td>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">${year.m2_2 }</td>
			<td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;">${year.m2_3 }</td>
		</tr>

		<tr>
			<td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">3月份</td>
			<td style="text-align: center;width: 10%;height: 60px;background: #f5f5f5;">${year.m3 }</td>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">${year.m3_2 }</td>
			<td style="text-align: center;width: 53%;height: 30px;background: #f5f5f5;">${year.m3_3 }</td>
		</tr>

		<tr>
			<td rowspan="3" style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><label>二季度</label></td>
			<td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">4月份</td>
			<td style="text-align: center;width: 10%;height: 60px;background: #f5f5f5;">${year.m4 }</td>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">${year.m4_2 }</td>
			<td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;">${year.m4_3 }</td>
		</tr>

		<tr>
			<td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">5月份</td>
			<td style="text-align: center;width: 10%;height: 60px;background: #f5f5f5;">${year.m5 }</td>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">${year.m5_2 }</td>
			<td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;">${year.m5_3 }</td>
		</tr>

		<tr>
			<td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">6月份</td>
			<td style="text-align: center;width: 10%;height: 60px;background: #f5f5f5;">${year.m6 }</td>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">${year.m6_2 }</td>
			<td style="text-align: center;width: 53%;height: 30px;background: #f5f5f5;">${year.m6_3 }</td>
		</tr>

		<tr>
			<td rowspan="3" style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><label>三季度</label></td>
			<td style="text-align: center;width: 10%;height: 60px;background: #f5f5f5;">7月份</td>
			<td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">${year.m7 }</td>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">${year.m7_2 }</td>
			<td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;">${year.m7_3 }</td>
		</tr>

		<tr>
			<td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">8月份</td>
			<td style="text-align: center;width: 10%;height: 60px;background: #f5f5f5;">${year.m8 }</td>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">${year.m8_2 }</td>
			<td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;">${year.m8_3 }</td>
		</tr>
		<tr>
			<td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">9月份</td>
			<td style="text-align: center;width: 10%;height: 60px;background: #f5f5f5;">${year.m9 }</td>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">${year.m9_2 }</td>
			<td style="text-align: center;width: 53%;height: 30px;background: #f5f5f5;">${year.m9_3 }</td>
		</tr>

		<tr>
			<td rowspan="3" style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><label>4季度</label></td>
			<td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">10月份</td>
			<td style="text-align: center;width: 10%;height: 60px;background: #f5f5f5;">${year.m10 }</td>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">${year.m10_2 }</td>
			<td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;">${year.m10_3 }</td>
		</tr>

		<tr>
			<td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">11月份</td>
			<td style="text-align: center;width: 10%;height: 60px;background: #f5f5f5;">${year.m11 }</td>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">${year.m11_2 }</td>
			<td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;">${year.m11_3 }</td>
		</tr>

		<tr>
			<td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">12月份</td>
			<td style="text-align: center;width: 10%;height: 60px;background: #f5f5f5;">
				${year.m12 }
			</td>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">${year.m12_2 }</td>
			<td style="text-align: center;width: 53%;height: 30px;background: #f5f5f5;">${year.m12_3 }</td>
		</tr>
	</table>
	<table id="bqhtable2" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
		<tr>
			<td colspan="2" style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">要求</td>
			<td colspan="2" style="text-align: center;height: 60px;background: #f5f5f5;">
				${year.m13 }
			</td>
		</tr>
	</table>
	<table id="bqhtable3" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
		<tr>
			<td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">制表人：</td>
			<td style="text-align: center;width: 30%;height: 30px;background: #f5f5f5;">${year.m14 }</td>
			<td  style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">批准人：</td>
			<td style="text-align: center;width: 30%;height: 30px;background: #f5f5f5;">${year.m15 }</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;width: 25%;height: 30px;background: #f5f5f5;"></td>
			<td  style="text-align: center;width: 25%;height: 30px;background: #f5f5f5;">制表单位：</td>
			<td style="text-align: center;width: 25%;height: 30px;background: #f5f5f5;">${year.qyname }</td>
		</tr>
	</table>
</form>
</body>
</html>