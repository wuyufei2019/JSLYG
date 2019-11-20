<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加惩罚告知记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
                <tr>
                     <td class="width-20 active"><label class="pull-right">编号：</label></td>
                     <td class="width-80" colspan="3">${cfjd.m0 }</td>
                </tr>
				<tr>
       				<td class="width-20 active"><label class="pull-right">受处罚类型：</label></td>
        			<td class="width-30" style="text-align:left">
	           		<c:if test="${cfjd.m1 == '1' }">
	           			单位
	           		</c:if>
	           		<c:if test="${cfjd.m1 == '2' }">
	           			个人
	           		</c:if>
        			</td>
        			<td class="width-20 active"><label class="pull-right">处罚时间：</label></td>
					<td class="width-30"><fmt:formatDate value="${cfjd.m24 }"/></td>	
   			   	</tr>
				<tr name="company">
					<td class="width-20 active"><label class="pull-right">被处罚单位：</label></td>
					<td class="width-80" colspan="3">${cfjd.m2 }</td>
				</tr>
				<tr name="company">
					<td class="width-20 active"><label class="pull-right">地址：</label></td>
					<td class="width-80"  colspan="3">${cfjd.m3 }</td>
				</tr>
				<tr name="company">
					<td class="width-20 active"><label class="pull-right">邮编：</label></td>
					<td class="width-30">${cfjd.m4 }</td>
					<td class="width-20 active"><label class="pull-right">法定代表人：</label></td>
					<td class="width-30">${cfjd.m5 }</td>
				</tr>
				<tr name="company">
				  	<td class="width-20 active"><label class="pull-right">职务：</label></td>
					<td class="width-30" >${cfjd.m6 }</td>
					<td class="width-20 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-30" >${cfjd.m7 }</td>
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">被处罚人：</label></td>
					<td class="width-30" >${cfjd.m8 }</td>
					<td class="width-20 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-30" >${cfjd.m11 }</td>
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">性别：</label></td>
					<td class="width-30">${cfjd.m9 }</td>	
					<td class="width-20 active"><label class="pull-right">年龄：</label></td>
					<td class="width-30" >${cfjd.m10 }</td>
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">家庭地址：</label></td>
					<td class="width-80"  colspan="3">${cfjd.m12 }</td>
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">单位：</label></td>
					<td class="width-80"  colspan="3">${cfjd.m13 }</td>	
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">单位地址：</label></td>
					<td class="width-80"  colspan="3">${cfjd.m15 }</td>
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">职务：</label></td>
					<td class="width-30" >${cfjd.m14 }</td>
					<td class="width-20 active"><label class="pull-right">电话：</label></td>
					<td class="width-30" >${cfjd.m16 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">违法事实和证据：</label></td>
					<td class="width-80" colspan="3" >${cfjd.m17 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">违反条款：</label></td>
					<td class="width-80"  colspan="3">${cfjd.m18 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">处罚依据：</label></td>
					<td class="width-80" colspan="3">${cfjd.m19 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">行政处罚：</label></td>
					<td class="width-80" colspan="3">${cfjd.m20 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">罚款履行方式：</label></td>
	        		<td class="width-80" style="text-align:left" colspan="3">
	         		<c:if test="${cfjd.m21 == '1' }">
	           			当场缴纳
	           		</c:if>
	           		<c:if test="${cfjd.m21 == '2' }">
	           			15内银行缴纳
	           		</c:if>
	        		</td>
				</tr>
				<tr>
					<td class="width-20 active" ><label class="pull-right">执法人员：</label></td>
                    <td class="width-30"  >${cfjd.m22 }</td>
                    <td class="width-20 active" ><label class="pull-right">执法人员：</label></td>
                    <td class="width-30"  >${cfjd.m23 }</td>
				</tr>
		</table>
</body>
<script type="text/javascript">
$(function(){
	if('${cfjd.m1 }'=='1'){
		$("[name='person']").hide();
		$("[name='company']").show();
	}else{
		$("[name='person']").show();
		$("[name='company']").hide();
	}
});
</script>
</html>