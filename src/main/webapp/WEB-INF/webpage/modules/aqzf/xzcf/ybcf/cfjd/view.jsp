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
					<td class="width-15 active"><label class="pull-right">编号：</label></td>
					<td class="width-35" colspan="3">${jce.number }</td>
   			   </tr>
   			   <tr>
					<td class="width-15 active"><label class="pull-right">处罚时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd" value="${jce.punishdate }" /></td>
       				<td class="width-15 active"><label class="pull-right">受处罚类型：</label></td>
        			<td class="width-35" >
	           			<c:if test="${jce.percomflag eq 1}"> 单位 </c:if>
	           			<c:if test="${jce.percomflag eq 0}"> 个人 </c:if>
        			</td>
   			   </tr>
   			   <c:if test="${jce.percomflag eq 1}">
				<tr>
						<td class="width-15 active"><label class="pull-right">被处罚单位：</label></td>
						<td class="width-35" colspan="3">
							${jce.punishname }
						</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">法定代表人：</label></td>
					<td class="width-35">${jce.legal }</td>
					<td class="width-15 active"><label class="pull-right">职务：</label></td>
					<td class="width-35" >${jce.duty }</td>
				
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">电话：</label></td>
					<td class="width-35" >${jce.mobile }</td>
					<td class="width-15 active"><label class="pull-right">邮编：</label></td>
					<td class="width-35">${jce.ybcode }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">地址：</label></td>
					<td class="width-35"  colspan="3"  >${jce.address }</td>
				</tr>
				
				</c:if>
				<c:if test="${jce.percomflag eq 0}">
				<tr>
						<td class="width-15 active"><label class="pull-right">被处罚人姓名：</label></td>
						<td class="width-35" colspan="3">
							${jce.punishname }
						</td>
				</tr>
				
				<tr>
				<td class="width-15 active"><label class="pull-right">性别：</label></td>
					<td class="width-35">${jce.sex }</td>	
					<td class="width-15 active"><label class="pull-right">年龄：</label></td>
					<td class="width-35" >${jce.age }</td>
				</tr>
				
				  <tr>
					<td class="width-15 active"><label class="pull-right">电话：</label></td>
					<td class="width-35" >${jce.mobile }</td>
					<td class="width-15 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-35" >${jce.identity1 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">家庭地址：</label></td>
					<td class="width-35"  colspan="3"  >${jce.address }</td>
				</tr>
				
				<tr>
				<td class="width-15 active"><label class="pull-right">所在单位：</label></td>
					<td class="width-35">${jce.dwname }</td>	
					<td class="width-15 active"><label class="pull-right">职务：</label></td>
					<td class="width-35" >${jce.duty }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">单位地址：</label></td>
					<td class="width-35"  colspan="3" >${jce.dwaddress }</td>
				</tr>
				</c:if>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">案件基本情况：</label></td>
					<td class="width-35" colspan="3" >${jce.illegalactandevidence }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">违法行为：</label></td>
					<td class="width-35" colspan="3">${jce.wfxw }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">主要证据：</label></td>
					<td class="width-35" colspan="3">${jce.evidence }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">违反条款：</label></td>
					<td class="width-35"    colspan="3">${jce.unlaw }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">惩罚依据：</label></td>
					<td class="width-35"  colspan="3" >${jce.enlaw }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">行政处罚：</label></td>
					<td class="width-35" colspan="3">${jce.xzcf }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">详情：</label></td>
					<td class="width-35"   colspan="3">${jce.punishresult }</td>
				</tr>
		</table>
</body>
</html>