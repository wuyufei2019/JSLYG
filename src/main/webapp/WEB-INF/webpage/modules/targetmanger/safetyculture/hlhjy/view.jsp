<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>合理化建议查看</title>
<meta name="decorator" content="default"/>
<style type="text/css">
	 
	.fltitle{
		text-align:center;
		font-weight:bold;
		color: #BA0101;
		font-size: 24px;
		margin-bottom: 8px;
		padding-top: 30px;
		text-shadow: 1px 2px 2px #D2D7DA;
	}
	.intro{
    	font-size: 12px;
    	font-weight: normal;
    	text-align: center;
    	color: #999;
	}
	.shadow{
		width:1000px;
		box-shadow: 5px 5px 10px 5px #999;
		margin: 10px auto;
	}
	
</style>
</head>
<body>
<div class="shadow">
	<div style="padding: 30px 35px;">
	  	<div  class="fltitle" >${target.theme }</div>
	  	<div  class="intro" >发布时间: <fmt:formatDate value="${target.s1 }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
	  	<hr />
		<br />
	  	<div >
	  	<p style="line-height:20px;">${target.content }</p>
		  	<br />
		  	<hr />
		  	附件：
			<div >
				 <c:if test="${not empty target.url}">
				 <div id="art_addFormFj" style="width:100%;float: left;">
				 <c:forTokens items="${target.url}" delims="," var="url" varStatus="urls">
				 	<c:set var="urlna" value="${fn:split(url, '||')}" />
				 	<c:set var="urlscount" value="${urls.count}" />
				 	<div data-id='_file-${urls.index +1}' class="new_file_div" > 
				 	<a href="javascript:void(0)" style="color:blue;text-decoration:underline;cursor:pointer;" onclick="window.open('${urlna[0]}')">${urlna[1]}</a>&nbsp;&nbsp;
				 	</div>
				 </c:forTokens>
				 </div>
				 </c:if>
				 <div style="clear: both;"></div>
			</div>
	  	</div>
  	</div>
 </div>
 
 
</body>
</html>