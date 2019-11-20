<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>查看案件进度详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/ybcf/lasp/modernizr.js"></script>
	<link type="text/css" href="${ctx}/static/model/css/lasp/timeline.css" rel="stylesheet"  />
</head>
<body>
	<section id="cd-timeline" class="cd-container">
		<!-- 立案审批 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-picture">
				<img src="${ctx}/static/model/images/lasp/lasp.png" alt="">
			</div>

			<div class="cd-timeline-content">
				<h2>立案审批</h2>
				<p>立案编号：${lasp.number }
				<br>案件名称：${lasp.casename }
				<br>当事人：${lasp.dsperson }
				<br><br>案件基本情况：${lasp.ajjbqk }
				<br><br>承办人：${lasp.enforcer1 }&nbsp;&nbsp;&nbsp;${lasp.enforcer2 }
				</p>
				<!-- <a href="http://www.baidu.com" class="cd-read-more">阅读更多</a> -->
				<span class="cd-date"><fmt:formatDate type="DATE" value="${lasp.filldate}"/></span>
			</div> 
		</div> 

		<!-- 询问通知 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-movie">
				<img src="${ctx}/static/model/images/lasp/xwtz.png" alt="">
			</div> 

			<div class="cd-timeline-content">
				<h2>询问通知</h2>
				<c:choose>
				    <c:when test="${xwtz == null }">
				    	<p style="color: red;">未进行询问通知</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>询问通知编号：${xwtz.m0 }
				    	<br>调查主题：${xwtz.m1 }
				    	<br>被询问单位：${xwtz.qyname }
				    	<br>询问时间：<fmt:formatDate value="${xwtz.m2 }" pattern="yyyy-MM-dd HH:mm"/>
				    	<br>询问地点：${xwtz.m3 }
				    	<br>证件材料：${xwtz.m8 },${xwtz.m4 }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${xwtz.s1}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div>

		<!-- 询问笔录 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-location">
				<img src="${ctx}/static/model/images/lasp/xwbl.png" alt="">
			</div> 

			<div class="cd-timeline-content">
				<h2>询问笔录</h2>
				<c:choose>
				    <c:when test="${fn:length(xwbllist)>0}">
				    	<p>
				    	<c:forEach items="${xwbllist}" var="xwbl" varStatus="zpc">
				    		询问次号：${xwbl.m0 }
				    		<br>询问时间：<fmt:formatDate value="${xwbl.m1 }" pattern="yyyy-MM-dd HH:mm"/>&nbsp;~&nbsp;<fmt:formatDate value="${xwbl.m2 }" pattern="yyyy-MM-dd HH:mm"/>
				    		<br>被询问人姓名：${xwbl.m4 }<font>性别：${xwbl.m5 }</font>
				    		<br>年龄：${xwbl.m6 }<font>身份证号：${xwbl.m7 }</font>
				    		<br>工作单位：${xwbl.m8 }
				    		<br>职务/工种：
				    		<c:if test="${not empty xwbl.m9}">
							 	<c:set var="urlna" value="${fn:split(xwbl.m9, ':')}" />
							 	${urlna[1] }
							</c:if><font>电话：${xwbl.m11 }</font>
							<br>住址：${xwbl.m10 }
							<br>询问人：${xwbl.m12 }
							<c:if test="${!zpc.last}">
							<br><br><br>
							</c:if>
				    	</c:forEach>
						</p>
				    </c:when>
				    <c:otherwise>
				    	<p style="color: red;">未进行询问笔录</p>
				    </c:otherwise>
				</c:choose>
				<span class="cd-date"></span>
			</div> 
		</div> 
		
		<!-- 调查报告 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-picture">
				<img src="${ctx}/static/model/images/lasp/dcbg.png" alt="">
			</div>

			<div class="cd-timeline-content">
				<h2>调查终结报告</h2>
				<c:choose>
				    <c:when test="${dcbg == null }">
				    	<p style="color: red;">未进行调查终结报告</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>调查终结报告编号：${dcbg.bgbh }
				    	<br>当事人：${dcbg.qyname }<font>案由：${dcbg.ayname }</font>
				    	<br>承办机构：${dcbg.cbjg }<font>承办人：${dcbg.enforcer1 }&nbsp;&nbsp;&nbsp;${dcbg.enforcer2 }</font>
				    	<br>违反条款：${dcbg.unlaw }
				    	<br>处罚依据：${dcbg.enlaw }
				    	<br>行政处罚：${dcbg.xzcf }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${dcbg.s1}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div> 
		
		<!-- 行政处罚告知 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-movie">
				<img src="${ctx}/static/model/images/lasp/cfgz.png" alt="">
			</div>

			<div class="cd-timeline-content">
				<h2>行政处罚告知</h2>
				<c:choose>
				    <c:when test="${cfgz == null }">
				    	<p style="color: red;">未进行行政处罚告知</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>行政处罚告知编号：${cfgz.number }
				    	<br>当事人：${cfgz.name }
				    	<br>违法行为：${cfgz.wfxw }
				    	<br>行政处罚：${cfgz.xzcf }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${cfgz.punishdate}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div> 
		
		<!-- 陈述申辩 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-location">
				<img src="${ctx}/static/model/images/lasp/cssb.png" alt="">
			</div>

			<div class="cd-timeline-content">
				<h2>陈述申辩</h2>
				<c:choose>
				    <c:when test="${cssb == null }">
				    	<p style="color: red;">未进行陈述申辩</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>申辩时间：<fmt:formatDate value="${cssb.m1 }" pattern="yyyy-MM-dd HH:mm"/>&nbsp;~&nbsp;<fmt:formatDate value="${cssb.m2 }" pattern="yyyy-MM-dd HH:mm"/>
				    	<br>申辩地点：${cssb.m3 }
				    	<br>申辩人：${cssb.m4 }<font>性别：${cssb.m5 }</font>
				    	<br>工作单位：${cssb.m7 }
				    	<br>职务：${cssb.m6 }<font>电话：${cssb.m8 }</font>
				    	<br>联系地址：${cssb.m9 }
				    	<br><br>承办人：${cssb.m12 }&nbsp;&nbsp;&nbsp;${cssb.m14 }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${cssb.m1}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div> 
		
		<!-- 听证告知 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-picture">
				<img src="${ctx}/static/model/images/lasp/tzgz.png" alt="">
			</div>

			<div class="cd-timeline-content">
				<h2>行政处罚听证告知</h2>
				<c:choose>
				    <c:when test="${tzgz == null }">
				    	<p style="color: red;">未进行行政处罚听证告知</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>行政处罚听证告知编号：${tzgz.number }
				    	<br>当事人：${tzgz.name }
				    	<br>违法行为：${tzgz.illegalact }
				    	<br>违反法律：${tzgz.unlaw }
				    	<br>处罚依据：${tzgz.enlaw }
				    	<br>行政处罚：${tzgz.punishresult }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${tzgz.punishdate}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div> 
		
		<!-- 案件呈批 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-movie">
				<img src="${ctx}/static/model/images/lasp/ajcp.png" alt="">
			</div>

			<div class="cd-timeline-content">
				<h2>案件处理呈批</h2>
				<c:choose>
				    <c:when test="${ajcp == null }">
				    	<p style="color: red;">未进行案件处理呈批</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>案件处理呈批编号：${ajcp.number }
				    	<br>案件名称：${ajcp.casename }
				    	<c:if test="${ajcp.percomflag == '1' }">
					    	<br>处罚类型：企业
					    	<br>被处罚单位：${ajcp.punishname }
					    	<br>单位地址：${ajcp.qyaddress }
					    	<br>法定代表人：${ajcp.legal }<font>职务：${ajcp.duty }</font>
				    	</c:if>
				    	<c:if test="${ajcp.percomflag == '0' }">
				    		<br>处罚类型：个人
				    		<br>被处罚人：${ajcp.punishname }<font>电话：${ajcp.mobile }</font>
				    		<br>年龄：${ajcp.age }<font>性别：${ajcp.sex }</font>
				    		<br>所在单位：${ajcp.dwname }
				    		<br>单位地址：${ajcp.dwaddress }
				    		<br>家庭地址：${ajcp.address }
				    	</c:if>
				    	<br>当事人申辩意见：${ajcp.sbrecord }
				    	<br>承办人意见：${ajcp.opinion }
				    	<br><br>承办人：${ajcp.cbr1 }&nbsp;&nbsp;&nbsp;${ajcp.cbr2 }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${ajcp.cbsj}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div> 
		
		<!-- 集体讨论 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-location">
				<img src="${ctx}/static/model/images/lasp/jttl.png" alt="">
			</div>

			<div class="cd-timeline-content">
				<h2>集体讨论</h2>
				<c:choose>
				    <c:when test="${jttl == null }">
				    	<p style="color: red;">未进行集体讨论</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>案件处理呈批编号：${jttl.m1 }
				    	<br>案件名称：${jttl.m2 }
				    	<br>讨论时间：<fmt:formatDate value="${jttl.m3 }" pattern="yyyy-MM-dd HH:mm"/>&nbsp;~&nbsp;<fmt:formatDate value="${jttl.m4 }" pattern="yyyy-MM-dd HH:mm"/>
				    	<br>地点：${jttl.m5 }
				    	<br>主持人：${jttl.m6 }
				    	<br>汇报人：${jttl.m7 }
				    	<br>出席人员及其职务：${jttl.m9 }
				    	<br>结论性意见：${jttl.m12 }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${jttl.m3}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div> 
		
		<!-- 行政处罚决定 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-picture">
				<img src="${ctx}/static/model/images/lasp/cfjd.png" alt="">
			</div>

			<div class="cd-timeline-content">
				<h2>行政处罚决定</h2>
				<c:choose>
				    <c:when test="${cfjd == null }">
				    	<p style="color: red;">未进行行政处罚决定</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>行政处罚决定编号：${cfjd.number }
				    	<c:if test="${cfjd.percomflag == '1' }">
				    		<br>处罚类型：企业
				    		<br>被处罚单位：${cfjd.punishname }
				    		<br>法定代表人：${cfjd.legal }<font>职务：${cfjd.duty }</font>
				    		<br>单位地址：${cfjd.address }
				    		<br>联系电话：${cfjd.mobile }<font>邮编：${cfjd.ybcode }</font>
				    	</c:if>
				    	<c:if test="${cfjd.percomflag == '0' }">
				    		<br>处罚类型：个人
				    		<br>被处罚人：${cfjd.punishname }
				    		<br>性别：${cfjd.sex }<font>年龄：${cfjd.age }</font>
				    		<br>电话：${cfjd.mobile }<font>身份证：${cfjd.identity1 }</font>
				    		<br>所在单位：${cfjd.dwname }<font>职务：${cfjd.duty }</font>
				    		<br>单位地址：${cfjd.dwaddress }
				    		<br>家庭住址：${cfjd.address }
				    	</c:if>
				    	<br>行政处罚：${cfjd.xzcf }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${cfjd.punishdate}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div> 
		
		<!-- 罚款审批 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-movie">
				<img src="${ctx}/static/model/images/lasp/fksp.png" alt="">
			</div> 

			<div class="cd-timeline-content">
				<h2>延期（分期）缴纳罚款审核</h2>
				<c:choose>
				    <c:when test="${fksp == null }">
				    	<p style="color: red;">未进行延期（分期）缴纳罚款审核</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>延期（分期）缴纳罚款审核编号：${fksp.m1 }
				    	<br>当事人：${fksp.m4 }
				    	<br>申请理由：${fksp.m7 }
				    	<br>承办人意见：${fksp.m8 }
				    	<br><br>承办人：${fksp.m9 }&nbsp;&nbsp;&nbsp;${fksp.m10 }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${fksp.m11}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div>
		
		<!-- 罚款批准 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-location">
				<img src="${ctx}/static/model/images/lasp/fkpz.png" alt="">
			</div> 

			<div class="cd-timeline-content">
				<h2>延期（分期）缴纳罚款批准</h2>
				<c:choose>
				    <c:when test="${fn:length(fkpzlist)>0}">
				    	<p>
				    	<c:forEach items="${fkpzlist}" var="fkpz" varStatus="zpc">
				    		<c:if test="${zpc.first}">
					    		延期（分期）缴纳罚款批准编号：${fkpz.m1 }
					    		<br>缴款类型：
					    		<c:if test="${fkpz.m5 == '1'}">延期缴纳罚款
					    		</c:if>
					    		<c:if test="${fkpz.m5 == '2'}">分期缴纳罚款
					    		</c:if>
					    		<font>总罚款：${fkpz.m4 }</font>
				    		</c:if>
				    		<c:if test="${fkpz.m5 == '1'}">
				    		<br>当事人：${fkpz.m2 }<font>批准日期：<fmt:formatDate value="${fkpz.m11 }" pattern="yyyy-MM-dd"/></font>
				    		<br>延期截止日期：<fmt:formatDate value="${fkpz.m6 }" pattern="yyyy-MM-dd"/>
				    		</c:if>
				    		<c:if test="${fkpz.m5 == '2'}">
				    		<br>期数：${fkpz.m7 }<font>分期截止日期：<fmt:formatDate value="${fkpz.m8 }" pattern="yyyy-MM-dd"/></font>
				    		<br>当事人：${fkpz.m2 }<font>批准日期：<fmt:formatDate value="${fkpz.m11 }" pattern="yyyy-MM-dd"/></font>
				    		<br>缴纳罚款：${fkpz.m9 }元<font>未缴纳罚款：${fkpz.m10 }元</font>
				    		</c:if>
							<c:if test="${!zpc.last}">
							<br>
							</c:if>
				    	</c:forEach>
						</p>
				    </c:when>
				    <c:otherwise>
				    	<p style="color: red;">未进行延期（分期）缴纳罚款批准</p>
				    </c:otherwise>
				</c:choose>
				<span class="cd-date"></span>
			</div> 
		</div> 
		
		<!-- 事先催告 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-picture">
				<img src="${ctx}/static/model/images/lasp/sxcg.png" alt="">
			</div>

			<div class="cd-timeline-content">
				<h2>强制执行事先催告</h2>
				<c:choose>
				    <c:when test="${sxcg == null }">
				    	<p style="color: red;">未进行强制执行事先催告</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>强制执行事先催告编号：${sxcg.number }
				    	<br>当事人：${sxcg.qyname }
				    	<br>尚未履行的行政决定：${sxcg.xzjd }
				    	<br>罚款缴纳截止日期：${sxcg.finedate }
				    	<br>罚款：${sxcg.fine }元<font>加处罚款：${sxcg.extrafine }元</font>
				    	<br>合计：${sxcg.allfine }元
				    	<br>立即履行的行政决定：${sxcg.extraxzjd }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${sxcg.sxcgsj}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div> 
		
		<!-- 强制执行申请 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-movie">
				<img src="${ctx}/static/model/images/lasp/qzzx.png" alt="">
			</div> 

			<div class="cd-timeline-content">
				<h2>强制执行申请</h2>
				<c:choose>
				    <c:when test="${qzzx == null }">
				    	<p style="color: red;">未进行强制执行申请</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>强制执行申请编号：${qzzx.number }
				    	<br>当事人：${qzzx.dsname }<font>签发人：${qzzx.qfr }</font>
				    	<br>相关材料：${qzzx.clname }
						</p>
						<span class="cd-date"><fmt:formatDate type="DATE" value="${qzzx.qzzxsj}"/></span>
				    </c:otherwise>
				</c:choose>
			</div> 
		</div>
		
		<!-- 结案审批 -->
		<div class="cd-timeline-block">
			<div class="cd-timeline-img cd-location">
				<img src="${ctx}/static/model/images/lasp/jasp.png" alt="">
			</div>

			<div class="cd-timeline-content">
				<h2>结案审批</h2>
				<c:choose>
				    <c:when test="${jasp == null }">
				    	<p style="color: red;">未进行结案审批</p>
						<span class="cd-date"></span>
				    </c:when>
				    <c:otherwise>
				    	<p>结案审批编号：${jasp.number}
				    	<br>案件名称：${jasp.casename}
				    	<c:if test="${jasp.percomflag == '1'}">
				    		<br>被处罚单位：${jasp.punishname }
					    	<br>单位地址：${jasp.qyaddress }
					    	<br>法定代表人：${jasp.legal }<font>职务：${jasp.duty }</font>
				    	</c:if>
				    	<c:if test="${jasp.percomflag == '0'}">
				    		<br>被处罚人：${jasp.punishname }<font>电话：${jasp.mobile }</font>
				    		<br>年龄：${jasp.age }<font>性别：${jasp.sex }</font>
				    		<br>所在单位：${jasp.dwname }
				    		<br>单位地址：${jasp.dwaddress }
				    		<br>家庭地址：${jasp.address }
				    	</c:if>
				    	<br>处理结果：${jasp.result}
				    	<br>执行情况：${jasp.exeucondition}
				    	<br>承办人：${jasp.cbr1}&nbsp;&nbsp;&nbsp;${jasp.cbr2}
						<span class="cd-date"><fmt:formatDate type="DATE" value="${jasp.spsj}"/></span>
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