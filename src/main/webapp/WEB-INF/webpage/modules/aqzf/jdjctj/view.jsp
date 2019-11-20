<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>查看监督检查进度详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/ybcf/lasp/modernizr.js"></script>
	<link type="text/css" href="${ctx}/static/model/css/lasp/timeline.css" rel="stylesheet"  />
</head>
<body>
	<section id="cd-timeline" class="cd-container">
		<!-- 检查计划 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-picture">
				<img src="${ctx}/static/model/images/lasp/lasp.png" alt="">
			</div>

			<div class="cd-timeline-content">
				<h2>检查计划</h2>
				<p>计划时间：${jcjh.m1 }年${jcjh.m2 }
				<br>检查企业：${jcjh.qyname }
				<br>属地：${jcjh.sd }<font>行业类型：${jcjh.hylx }</font>
				<br>检查科室：${jcjh.m5 }
				</p>
				<!-- <a href="http://www.baidu.com" class="cd-read-more">阅读更多</a> -->
				<span class="cd-date"></span>
			</div> 
		</div> 

		<!-- 检查方案 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-movie">
				<img src="${ctx}/static/model/images/lasp/xwtz.png" alt="">
			</div> 

			<div class="cd-timeline-content">
				<h2>检查方案</h2>
				<c:choose>
				    <c:when test="${jcfa == null }">
				    	<p style="color: red;">未进行检查方案</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>检查方案编号：${jcfa.m0 }
				    	<br>检查企业：${jcjh.qyname }
				    	<br>企业地址：${jcfa.m1 }
				    	<br>检查方式：${jcfa.m7 }
				    	<br>行政执法人员：${jcfa.m5 }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${jcfa.m4}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div>

		<!-- 检查记录 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-location">
				<img src="${ctx}/static/model/images/lasp/xwbl.png" alt="">
			</div> 

			<div class="cd-timeline-content">
				<h2>检查记录</h2>
				<c:choose>
				    <c:when test="${jcjl == null }">
				    	<p style="color: red;">未进行检查记录</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>检查记录编号：${jcjl.m0 }
				    	<br>检查时间：<fmt:formatDate value="${jcjl.m6 }" pattern="yyyy-MM-dd HH:mm"/>&nbsp;~&nbsp;<fmt:formatDate value="${jcjl.m7 }" pattern="yyyy-MM-dd HH:mm"/>
						<br>检查企业：${jcjh.qyname }
						<br>检查场所：${jcjl.m5 }
						<br>检查人员：${jcjl.m8 }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${jcjl.m6}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div>
		
		<!-- 现场处理 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-picture">
				<img src="${ctx}/static/model/images/lasp/xwtz.png" alt="">
			</div> 

			<div class="cd-timeline-content">
				<h2>现场处理</h2>
				<c:choose>
				    <c:when test="${clcs == null }">
				    	<p style="color: red;">未进行现场处理</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>现场处理编号：${clcs.m0 }
				    	<br>现处企业：${jcjh.qyname }
				    	<br>处理决定：${clcs.m4 }
				    	<br>行政执法人员：${clcs.m7_1 }&nbsp;&nbsp;${clcs.m7_2 }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${clcs.m1}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div>
		
		<!-- 责令整改 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-movie">
				<img src="${ctx}/static/model/images/lasp/xwtz.png" alt="">
			</div> 

			<div class="cd-timeline-content">
				<h2>责令整改</h2>
				<c:choose>
				    <c:when test="${zlzg == null }">
				    	<p style="color: red;">未进行责令整改</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>责令整改编号：${zlzg.m0 }
				    	<br>责改企业：${jcjh.qyname }
				    	<br>整改完毕日期：<fmt:formatDate type="DATE" value="${zlzg.m3}"/>
				    	<br>行政执法人员：${zlzg.m6_1 }&nbsp;&nbsp;${zlzg.m6_2 }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${zlzg.m1}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div>

		<!-- 复查意见 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-location">
				<img src="${ctx}/static/model/images/lasp/xwbl.png" alt="">
			</div> 

			<div class="cd-timeline-content">
				<h2>整改复查意见</h2>
				<c:choose>
				    <c:when test="${fcyj == null }">
				    	<p style="color: red;">未进行整改复查</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>整改复查编号：${fcyj.m0 }
						<br>复查企业：${jcjh.qyname }
						<br>提出意见:${fcyj.m6 }
						<br>行政执法人员：${fcyj.m3_1 }&nbsp;&nbsp;${fcyj.m3_2 }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${fcyj.m1}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div>
	</section> 
	
<script type="text/javascript">
$(function(){
	var $timeline_block = $('.cd-timeline-block');
	var index = parent.layer.getChildFrame('body', parent.layer.getFrameIndex(window.name));
	
	$timeline_block.each(function(){
		if($(this).offset().top > index.scrollTop()+index.height()*0.75) {
			$(this).find('.cd-timeline-img, .cd-timeline-content').addClass('is-hidden');
		}
	});
	
	index.on('scroll', function(){
		$timeline_block.each(function(){
			if( $(this).offset().top <= index.scrollTop()+index.height()*0.75 && $(this).find('.cd-timeline-img').hasClass('is-hidden') ) {
				$(this).find('.cd-timeline-img, .cd-timeline-content').removeClass('is-hidden').addClass('bounce-in');
			}
		});
	});
});
</script>
</body>
</html>