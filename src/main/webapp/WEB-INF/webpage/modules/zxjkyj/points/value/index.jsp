<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>物料实时监测数据</title>
    <meta name="decorator" content="default"/>
</head>
<body class="gray-bg">
<div class="easyui-tabs" fit="true">
    <div title="实时数据" style="height:100%;" href="${ctx}/zxjkyj/points/value/index2" data-options="">
    </div>
    <div title="历史数据" style="height:100%;" href="${ctx}/zxjkyj/points/value/index3" data-options="">
    </div>
    <div title="报警数据" style="height:100%;" href="${ctx}/zxjkyj/alarm/index" data-options="">
    </div>
</div>
</body>
</html>