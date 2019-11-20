<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="table" tagdir="/WEB-INF/tags/table" %>
<%@ page import="com.cczu.sys.system.utils.UserUtil"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<% String lng ="";
   String lat ="";
if(UserUtil.getCurrentShiroUser()!=null && UserUtil.getCurrentShiroUser().getBar()!=null){
	lng=UserUtil.getCurrentShiroUser().getBar().getLng();
	lat=UserUtil.getCurrentShiroUser().getBar().getLat();
} %>
<c:set var="lat" value="<%=lat %>"/>
<c:set var="lng" value="<%=lng %>"/>
