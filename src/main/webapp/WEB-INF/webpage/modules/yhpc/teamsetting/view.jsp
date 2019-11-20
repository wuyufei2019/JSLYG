<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>班组排版设置</title>
    <meta name="decorator" content="default"/>
    <style type="text/css">

        .dragitem {
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 15px;
            font-weight: bold;
            height: 50px;
        }

        .targetarea {
            height: 50px;
        }

        input {
            border: none;
            text-align: center;
        }

        .til-1 span {
            flex-shrink: 0;
            font-size: 14px;
            font-weight: bold
        }

        .til-1 {
            display: flex;
            align-items: center;
            margin: 10px;
            font-size: 14px;
            font-weight: bold
        }

        .row-1 {
            background: rgb(246, 247, 250);
        }

        .btn-1 {
            flex-shrink: 0;
            padding: 8px 12px;
            border-radius: 5px;
            color: white;
            background: rgb(92, 184, 92);
            margin-left: 10px
        }

        .btn-2 {
            flex-shrink: 0;
            padding: 8px 12px;
            border-radius: 5px;
            color: white;
            background: rgb(91, 192, 222);
            margin-left: 10px
        }

        .table-1 {
            border: solid rgb(236, 237, 244);
            border-width: 2px 0px 0px 2px;
        }

        .table-1 td {
            border: solid rgb(236, 237, 244);
            border-width: 0px 2px 2px 0px;
        }

        .table-1 .th-1 {
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 15px;
            font-weight: bold
        }

        .table-1 .easyui-droppable.th-1 {
            color: rgb(91, 192, 222)
        }
    </style>

</head>
<body>
<c:set value="${fn:length(depts)}" var="len"></c:set>
<c:set value="${fn:length(settings)}" var="len2"></c:set>
<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
    <tbody>
    <tr>
        <td colspan="4" id="worksort">
            <table style="width: 100%;">
                <tr>
                    <td style="display: flex;">
                        <table class="table-1" style="width: 100%;" id="teamsort">
                            <tr>
                                <c:forEach var="dept" items="${depts}">
                                    <td>
                                        <div class="title th-1 targetarea">${dept}</div>
                                    </td>
                                </c:forEach>
                            </tr>
                            <c:forEach var="i" begin="0" end="${len -1}">
                                <tr class="row-1" id="tr1">
                                    <c:forEach begin="0" end="${len2/len -1}" var="j">
                                        <td style="width: 25%;">
                                            <div class="easyui-droppable targetarea th-1">
                                                    ${settings[j*len+i].starttime}-
                                                    ${settings[j*len+i].endtime}
                                            </div>
                                        </td>
                                    </c:forEach>
                                </tr>

                            </c:forEach>

                            <!-- row-1 为背景换色 循环时标注 -->
                            <tfoot>
                            <td colspan="4" style="text-align: center"><a onclick="reCreate()"
                                                                          class="btn btn-primary">重新设置
                            </a></td>
                            </tfoot>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    </tbody>
</table>
<script>
    function reCreate() {
        layer.confirm('删除将重置班组排班！请确定！', {icon: 3, title: '提示'}, function (index) {
            window.location.href = "${ctx}/yhpc/teamsetting/recreate";
        });
    }

    console.log(${len}, ${len2})
    console.log(${len2/len})

    function doSubmit() {
        parent.layer.closeAll();
    }
</script>
</body>
</html>