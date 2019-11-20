<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>检查计划信息查看</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<form id="inputForm" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-20 active"><label class="pull-right">申请单位：</label></td>
            <td class="width-30">${entity.unitname }
            </td>
            <td class="width-20 active"><label class="pull-right">整改指令书编号：</label></td>
            <td class="width-30">${entity.number }
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">申请延期至：</label></td>
            <td class="width-30">${entity.m1 }</td>
            <td class="width-20 active"><label class="pull-right">延长时限：</label></td>
            <td class="width-30">${entity.m4 }</td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">延期整改隐患<br>内容及原因：</label></td>
            <td class="width-30" colspan="3">
                ${entity.m2 }
            </td>
        </tr>
        <c:if test="${entity.m6 !=null}">
            <tr>
                <td class="width-20 active"><label class="pull-right">审核是否通过：</label></td>
                <td class="width-30" colspan="3">
                    <c:if test="${entity.m6 ==2}">通过 </c:if>
                    <c:if test="${entity.m6 ==3}">不通过 </c:if>
                </td>
                </td>
            </tr>
            <tr>
                <td class="width-20 active"><label class="pull-right">审核不通过原因：</label></td>
                <td class="width-30" colspan="3">
                        ${entity.m5 }
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>

</form>
</body>
</html>