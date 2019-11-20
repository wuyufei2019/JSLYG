<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>延期申请管理</title>
    <meta name="decorator" content="default"/>
    <script src="${ctxStatic}/common/webUpload.js"></script>
</head>
<body>

<form id="inputForm" action="${ctx}/aqzf/yhzz/${action}"
      method="post" class="form-horizontal">
    <table
            class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">整改人：</label></td>
            <td class="width-35">
                <input name="person1" class="easyui-textbox" value="${entity.person1 }"
                       style="width: 100%;height: 30px;" data-options=" required:'true' "/>
            </td>
            <td class="width-15 active"><label class="pull-right">负责人：</label></td>
            <td class="width-35">
                <input name="person2" class="easyui-textbox" value="${entity.person2 }"
                       style="width: 100%;height: 30px;" data-options=" required:'true' "/>
            </td>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">整改时限(天)：</label></td>
            <td class="width-35">
                <input class="easyui-numberspinner" name="time" value="10"
                       data-options="increment:1"
                       style="width:100%;height: 30px">
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">整改后图片：</label></td>
            <td class="width-35">
                <div id="pic_uploader" style="margin-left:10px"></div>
            </td>
        </tr>



        <c:if test="${not empty entity.ID}">
        <input type="hidden" name="ID" value="${entity.ID}"/>
        <input type="hidden" name="ID1" value="${entity.ID1}"/>
        <input type="hidden" name="ID2" value="${entity.ID2}"/>
        <input type="hidden" name="S1"
               value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
        </c:if>
        <tbody>
    </table>
</form>


<script type="text/javascript">

    $("#pic_uploader").WebImageUpload({hiddenInputId: 'pic', existurl: '${entity.pic}'});
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

    function doSubmit() {
        $("#inputForm").serializeObject();
        $("#inputForm").submit();
    }

    $(function () {

        var flag = true;
        $('#inputForm').form({
            onSubmit: function () {
                var isValid = $(this).form('validate');
                if (isValid && flag) {
                    flag = false;
                    $.jBox.tip("正在提交，请稍等...", 'loading', {opacity: 0});
                    return true;
                }
                return false; // 返回false终止表单提交
            },
            success: function (data) {
                $.jBox.closeTip();
                if (data == 'success')
                    parent.layer.open({
                        icon: 1,
                        title: '提示',
                        offset: 'rb',
                        content: '操作成功！',
                        shade: 0,
                        time: 2000
                    });
                else
                    parent.layer.open({
                        icon: 2,
                        title: '提示',
                        offset: 'rb',
                        content: '操作失败！',
                        shade: 0,
                        time: 2000
                    });
                parent.dg.datagrid('reload');
                parent.layer.close(index);//关闭对话框。
            }
        });
    });


</script>
</body>
</html>