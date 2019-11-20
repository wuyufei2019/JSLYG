<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>储罐管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
    <tbody>
    <tr>
        <td class="width-15 active"><label class="pull-right">主题：</label></td>
        <td class="width-35" colspan="3">${entity.strSubject }</td>
    <tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">会议议程：</label></td>
        <td class="width-35" colspan="3">${entity.strAgenda.value }</td>
    <tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">发起方式：</label></td>
        <td class="width-35">
            <c:if test="${entity.operateID eq 1}">即时会议</c:if>
            <c:if test="${entity.operateID eq 0}">预约会议</c:if>
        </td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">开始时间：</label></td>
        <td class="width-35">
            ${entity.strBeginTime }
        </td>
        <td class="width-15 active"><label class="pull-right">结束时间：</label></td>
        <td class="width-35">
            ${entity.strEndTime }
        </td>
    </tr>

    <tr>
        <td class="width-15 active"><label class="pull-right">与会企业：</label></td>
        <td class="width-35" colspan="3">
        </td>
    </tr>
    <tbody>


</table>

</body>
</html>