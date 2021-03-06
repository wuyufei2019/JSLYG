<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>巡检班次任务查看</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<form id="inputForm" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">班次名称：</label></td>
            <td class="width-35">${plan.name}</td>
            <td class="width-15 active"><label class="pull-right">班次类型：</label></td>
            <td class="width-35">
                <c:if test="${plan.type eq '1'}">日检</c:if>
                <c:if test="${plan.type eq '2'}">周检</c:if>
                <c:if test="${plan.type eq '3'}">月检</c:if>
                <c:if test="${plan.type eq '4'}">年检</c:if>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">任务设置：</label></td>
            <td class="width-35" colspan="3">
                <table style="width: 100%;" border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7">
                    <tr>
                        <td style="text-align: center;width: 50%;">起始时间</td>
                        <td style="text-align: center;width: 50%;">结束时间</td>
                    </tr>
                    <c:if test="${plan.type != '3'}">
                        <c:forEach var="item" items="${times}" varStatus="status">
                            <tr>
                                <td style="text-align: center;width: 50%;">${item.starttime}</td>
                                <td style="text-align: center;width: 50%;">${item.endtime}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${plan.type eq '3'}">
                        <c:forEach var="item" items="${details}" varStatus="status">
                            <tr>
                                <td style="text-align: center;width: 50%;">${item.starttime}日</td>
                                <td style="text-align: center;width: 50%;">${item.endtime}日</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">巡检点：</label></td>
            <td class="width-35" colspan="3">
                <c:if test="${not empty points}">
                    <c:forEach items="${points}" var="point">
                        ${point }<br>
                    </c:forEach>
                </c:if>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">巡检人员：</label></td>
            <td class="width-35" colspan="3"><c:forEach items="${users}" var="user">
                ${user }<br>
            </c:forEach></td>
        </tr>
        </tbody>
    </table>

</form>
</body>
</html>