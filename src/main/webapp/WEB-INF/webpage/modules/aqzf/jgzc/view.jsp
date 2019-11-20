<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>隐患整饬管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<form id="inputForm" action="${ctx}/aqzf/jgzc/${action}"
      method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-20 active"><label class="pull-right">逐一分析问题隐患隐患形成的主、客观原因：</label></td>
            <td class="width-80" ><input name="reason" type="text" class="easyui-textbox"
                                                    value="${entity.reason }"
                                                    data-options="multiline:true" style="height: 80px;width: 100%"/>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">公司、班组两级分贝展开套路，剖析危害：</label></td>
            <td class="width-80" ><input name="danger" type="text" class="easyui-textbox"
                                                    value="${entity.danger }"
                                                    data-options="multiline:true " style="height: 80px;width: 100%"/>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">进一步完善公司制度规定、考核标准、实施细节和奖惩措施</label></td>
            <td class="width-80" ><input name="measure" type="text" class="easyui-textbox"
                                                    value="${entity.measure }"
                                                    data-options=" multiline:true" style="height: 80px;width: 100%"/>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">厘清相关安全管理人员责任，给予有个负责人相应处罚：</label></td>
            <td class="width-80" ><input name="method" type="text" class="easyui-textbox"
                                                    value="${entity.method }"
                                                    data-options=" multiline:true" style="height: 80px;width: 100%"/>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">展开警示教育情况：</label></td>
            <td class="width-80" ><input name="situation" type="text" class="easyui-textbox"
                                                    value="${entity.situation }"
                                                    data-options=" multiline:true" style="height: 80px;width: 100%"/>
            </td>
        </tr>


        <tbody>
    </table>
</form>


<script type="text/javascript">


    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

    function doSubmit() {
        $("#inputForm").serializeObject();
        $("#inputForm").submit();
    }

    $(function () {
        if ('${action}' == 'create') {
            $("#ID1").val('${id1}');
        }

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