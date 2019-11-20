<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>设备设置</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<form id="inputForm" action="${ctx}/zxjkyj/hkvision/device-qy/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
            <td class="width-30" colspan="3">
                <input type="hidden" id="qyname" name="qyname" value="${sb.qyname }">
                <input value="${sb.qyid }" id="qyid" name="qyid" style="width: 100%;height: 30px;"
                       class="easyui-combobox"
                       data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'"/>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">设备序列号：</label></td>
            <td class="width-30">
                <input type="text" name="deviceSerial" class="easyui-textbox" value="${sb.deviceSerial }"
                       data-options="validType:'length[0,50]'" style="width: 100%;height: 30px;"/>
            </td>
            <td class="width-20 active"><label class="pull-right">设备验证码：</label></td>
            <td class="width-30">
                <input type="text" name="validateCode" class="easyui-textbox" value="${sb.validateCode }"
                       data-options="" style="width: 100%;height: 30px;"/>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">设备名称：</label></td>
            <td class="width-30">
                <input type="text" name="deviceName" class="easyui-textbox" value="${sb.deviceName }"
                       data-options="validType:'length[0,50]'" style="width: 100%;height: 30px;"/>
            </td>
            </td>
        </tr>

        <c:if test="${not empty sb.ID}">
            <input type="hidden" name="ID" value="${sb.ID}"/>
            <input type="hidden" name="status" value="${sb.status}"/>
            <input type="hidden" name="bind" value="${sb.bind}"/>
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
                    parent.layer.open({icon: 2, title: '提示', offset: 'rb', content: data, shade: 0, time: 2000});
                parent.dg.datagrid('reload');
                parent.layer.close(index);//关闭对话框。
            }
        });

    });

    function doSubmit() {
        $('#qyname').val($("#qyid").combobox('getText'));
        $("#inputForm").submit();
    }

</script>
</body>
</html>