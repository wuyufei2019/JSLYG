<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>调查报告信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<tr>
                    <td class="width-15 active"><label class="pull-right">编号：</label></td>
                    <td class="width-35" colspan="3">${ybe.bgbh }</td>
                </tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">案件名称：</label></td>
					<td class="width-35" colspan="3">${ybe.casename }</td>
				</tr>	
				<tr>
					<td class="width-15 active"><label class="pull-right">案由：</label></td>
					<td class="width-35">${ybe.ayname }</td>
                    <td class="width-15 active"><label class="pull-right">承办机构：</label></td>
					<td class="width-35">${ybe.cbjg }</td>
				</tr>
                <tr>
                     <td class="width-15 active" ><label class="pull-right">调查人：</label></td>
                     <td class="width-35"  >${ybe.enforcer1 }</td>
                      <td class="width-15 active" ><label class="pull-right">调查人：</label></td>
                     <td class="width-35"  >${ybe.enforcer2 }</td>
                </tr>
                <tr>
                         <td class="width-15 active"><label class="pull-right">案件基本情况：</label></td>
                         <td class="width-35" colspan="3">${ybe.researchresult}</td>
                </tr>
                <tr>
                         <td class="width-15 active"><label class="pull-right">违反条款：</label></td>
                         <td class="width-35" colspan="3">${ybe.unlaw }
                    </td>
                    </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">违反条款详情：</label></td>
                    <td class="width-35" colspan="3">${ybe.unlawall }</td>
                </tr>
                <tr>
                    <td class="width-15" colspan="4" style="text-align:center"><label style="color: red;font-size: 20px;margin-top: 10px;">一、当事人基本信息</label>
                    </td>
                </tr>
                <c:if test="${cfdxlx == '2'}">
                    <td class="width-15 active"><label class="pull-right">当事人：</label></td>
                    <td class="width-35" >
                        ${ybe.cfryname }
                    </td>
                </c:if>
                <tr>
					<td class="width-15 active"><label class="pull-right">当事人：</label></td>
					<td class="width-35" >${ybe.qyname}</td>
					
					<td class="width-15 active"><label class="pull-right">法定代表人/负责人：</label></td>
					<td class="width-35"  >${ybe.legalperson }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">经济类型：</label></td>
					<td class="width-35" >${ybe.economytype }</td>
					
					<td class="width-15 active"><label class="pull-right">成立时间：</label></td>
                    <td class="width-35"> <fmt:formatDate value="${ybe.qyfounddate}" pattern="yyyy-MM-dd"/></td>   
				</tr>
                    
                 <tr>
                     <td class="width-15 active"><label class="pull-right">企业地址：</label></td>
                     <td class="width-35" colspan="3">${ybe.qyaddress }</td>
                 </tr>
                 <tr>
                     <td class="width-15 active"><label class="pull-right">经营范围：</label></td>
                     <td class="width-35" colspan="3">${ybe.businessscope }</td>
                 </tr>
				
				<tr><td class="width-15" colspan="4" style="text-align:center"><label style="color: red;font-size: 20px;margin-top: 10px;">二、调查情况</label></td> </tr>  
				<tr>
					<td class="width-15 active"><label class="pull-right">相关证据：</label></td>
					<td class="width-35" colspan="3">
						<table id="czwttable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
							<tr >
								<td style="text-align: center;width: 80%;">证据</td>
								<td style="text-align: center;width: 20%;">照片(点击查看原图)</td>
							</tr>
						</table>
						
					</td>
				</tr>
				
				
                    <tr><td class="width-15" colspan="4" style="text-align:center"><label style="color: red;font-size: 20px;margin-top: 10px;">三、处理建议</label></td> </tr>  
                <tr>
                    <td class="width-15 active"><label class="pull-right">处罚依据：</label></td>
                    <td class="width-35" colspan="3">${ybe.enlaw }</td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">处罚标准：</label></td>
                    <td class="width-35" colspan="3">${ybe.punishresult }</td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">行政处罚：</label></td>
                    <td class="width-35" colspan="3">${ybe.xzcf }</td>
                </tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">负责人意见：</label></td>
					<td class="width-35" colspan="3" style="height:30px;">
						<c:choose>
						<c:when test="${ybe.spyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="spyj" disabled/>同意
							<input type="radio" value="0" class="i-checks"  name="spyj" disabled checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="spyj" disabled checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="spyj" disabled />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-15 active"><label class="pull-right">负责人：</label></td>
					<td class="width-35">${ybe.spr }</td>
					<td class="width-15 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-35"><fmt:formatDate type="date"  value="${ybe.spsj }" /></td>
				</tr>
			</tbody>
		</table>
<script type="text/javascript">
var wtListInit='${czwt}';
	nrList=JSON.parse(wtListInit);  
$.each(nrList, function(idx, obj) {
    	var url=obj.m2;
    	var czwt=obj.m1;
    	if((url!=null)&&(url!="")&&(url!="undefined")){
	    	var img=url.split("||");
			var $trHtml = $(
				'<tr>'+
				'<td>'+
				czwt+
				'</td>'+
				'<td>'+
				'<div style="text-align:center;">' +
				'<a target="_blank" href="'+img[0]+'">'+
				'<img src=\''+img[0]+'\' style=\'width:50px; height: 50px\'>' +
				'<div class=\'info\'>' + img[1] + '</div>' +
				'</a>'+
				'</div>'+
				'</td>'+
				'<td>'+
				'</td>'+
				'</tr>'
			 );	
    	}else{
    		var img=url.split("||");
			var $trHtml = $(
				'<tr>'+
				'<td>'+
				czwt+
				'</td>'+
				'<td style="height:69px;">'+
				'</td>'+
				'<td>'+
				'</td>'+
				'</tr>'
			 );	
    	}
		//id和数量加1
    	var  $list= $("#czwttable tr").eq(-1);
		$list.after( $trHtml );
	});
</script>
</body>
</html>