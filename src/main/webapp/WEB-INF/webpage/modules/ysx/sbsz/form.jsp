<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>设备设置</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<form id="inputForm" action="${ctx}/ysx/sbsz/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-20 active"><label class="pull-right">账户：</label></td>
            <td class="width-30" colspan="3">
                <input type="hidden" id="accName" name="accName" value="${sb.accName }">
                <input value="${sb.accId }" id="accId" name="accId" style="width: 100%;height: 30px;"
                       class="easyui-combobox" data-options="required:'true',valueField: 'id',textField:
                       'text',url:'${ctx}/system/user/ryjson'"/>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">设备账号：</label></td>
            <td class="width-30">
                <input type="text" name="userId" class="easyui-textbox" value="${sb.userId }"
                       data-options="required:'true',validType:'length[0,50]'" style="width: 100%;height: 30px;"/>
            </td>
            <td class="width-20 active"><label class="pull-right">设备密码：</label></td>
            <td class="width-30">
                <input type="text" name="passwd" class="easyui-textbox" value="${sb.passwd }"
                       data-options="validType:'length[0,20]'" style="width: 100%;height: 30px;"/>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">联系人：</label></td>
            <td class="width-30">
                <input type="text" name="contact" class="easyui-textbox" value="${sb.contact }"
                       data-options="validType:'length[0,50]'"
                       style="width: 100%;height: 30px;"/>
            </td>
            <td class="width-20 active"><label class="pull-right">联系人手机：</label></td>
            <td class="width-30">
                <input type="text" name="phone" class="easyui-textbox" value="${sb.phone }"
                       data-options="validType:['mobile','length[0,20]']"
                       style="width: 100%;height: 30px;"/>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">是否为软终端：</label></td>
            <td class="width-30">
                <input type="radio" value="false" class="i-checks" name="softTerminal"
                       checked="checked" />硬
                <input type="radio" value="true" class="i-checks" name="softTerminal"/>软

            </td>
        </tr>

        <c:if test="${not empty sb.ID}">
            <input type="hidden" name="ID" value="${sb.ID}"/>
        </c:if>

        </tbody>
    </table>
</form>

<script type="text/javascript">
    var action = '${action}';
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

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
                return false;	// 返回false终止表单提交
            },
            success: function (data) {
                $.jBox.closeTip();
                if (data == 'success')
                    parent.layer.open({icon: 1, title: '提示', offset: 'rb', content: '操作成功！', shade: 0, time: 2000});
                else
                    parent.layer.open({icon: 2, title: '提示', offset: 'rb', content: '操作失败！', shade: 0, time: 2000});
                parent.dg.datagrid('reload');
                parent.layer.close(index);//关闭对话框。
            }
        });

    });

    function doSubmit() {
         $('#accName').val($("#accId").combobox('getText'));
        $("#inputForm").submit();
    }

</script>
</body>
</html>