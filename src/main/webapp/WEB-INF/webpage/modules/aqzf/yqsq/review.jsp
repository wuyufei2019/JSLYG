<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>延期申请管理</title>
    <meta name="decorator" content="default"/>
    <%@ include file="/WEB-INF/webpage/include/kindeditor.jsp" %>
</head>
<body>

<form id="inputForm" action="${ctx}/aqzf/yqsq/${action}"
      method="post" class="form-horizontal">
    <table
            class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-20 active"><label class="pull-right">申请单位：</label></td>
            <td class="width-30"><input name="unitname" type="text" class="easyui-textbox"
                                        value="${entity.unitname }"
                                        data-options="readonly:'true' " style="height: 30px;width: 100%"/>
            </td>
            <td class="width-20 active"><label class="pull-right">整改指令书编号：</label></td>
            <td class="width-30"><input type="text" class="easyui-textbox" name="number"
                                        value="${entity.number }"
                                        data-options="readonly:'true' " style="height: 30px;width: 100%"/>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">申请延期至：</label></td>
            <td class="width-30"><input id="M1" name="M1" class="easyui-datebox" value="${entity.m1 }"
                                        style="height: 30px;width: 100%;height: 30px;"
                                        data-options="readonly:'true' "/></td>
            <td class="width-20 active"><label class="pull-right">延长时限(天)：</label></td>
            <td class="width-30"><input id="M4" name="M4" class="easyui-numberbox" value="${entity.m4 }"
                                        style="height: 30px;width: 100%;height: 30px;"
                                        data-options="readonly:'true' "/></td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">延期整改隐患<br>内容及原因：</label></td>
            <td class="width-30" colspan="3">
                <input type="text" id="M2" name="M2" class="easyui-textbox" value="${entity.m2 }"
                       data-options="multiline:true,readonly:'true'" style="height: 80px;width: 100%;"/>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">审核是否通过：</label></td>
            <td class="width-30" colspan="3">
                <input type="radio" value="2" class="i-checks" checked name="M6"/>通过 <input
                    type="radio" value="3" class="i-checks" name="M6"/> 不通过
            </td>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">审核不通过原因：</label></td>
            <td class="width-30" colspan="3">
                <input type="text" id="M5" name="M5" class="easyui-textbox" value="${entity.m5 }"
                       data-options="multiline:true,validType:'length[0,200]',prompt:'审核不通过填写'"
                       style="height: 50px;width: 100%;"/>
            </td>
        </tr>
        <input type="hidden" name="oldTime" id="oldTime" value="<fmt:formatDate value="${entity.oldTime}"
        pattern="yyyy-MM-dd HH:mm:ss"/>">

        <input type="hidden" name="qyid" value="${entity.qyid}"/>
        <input type="hidden" name="ID1" value="${entity.ID1}"/>
        <input type="hidden" name="ID" value="${entity.ID}"/>
        <input type="hidden" name="S1"
               value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
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
        $("#M1").datebox({
            onChange: function (date) {
                var oldTime = $("#oldTime").val();
                var days = new Date(date).getTime() - new Date(oldTime).getTime();
                var day = parseInt(days / (1000 * 60 * 60 * 24));
                $("#M4").numberbox("setValue", day);

            }

        });

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