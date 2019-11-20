<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>储罐监测维护数据信息</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<form id="inputForm" action="${ctx}/bis/cgjcwhsj/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-20 active"><label class="pull-right">设备编码：</label></td>
            <td class="width-30" colspan="3"><input name="equipCode" id="equipCode" class="easyui-combobox" value="${entity.equipCode }" style="width: 100%;height: 30px;"
                                        data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text'"/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">指标编码：</label></td>
            <td class="width-30" colspan="3"><input name="cgqbh" id="targetCode" class="easyui-textbox" value="${entity.targetCode }" style="width: 100%;height: 30px;"
                                                    data-options="validType:'length[0,100]'"/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">指标名称：</label></td>
            <td class="width-30"><input class="easyui-textbox" name="targetName" value="${entity.targetName }" style="width: 100%;height: 30px;"
                                    data-options="validType:'length[0,50]'"/></td>

            <td class="width-20 active"><label class="pull-right">指标类型：</label></td>
            <td class="width-30"><input class="easyui-combobox" name="targetType" value="${entity.targetType }" style="width: 100%;height: 30px;"
                                        data-options="panelHeight:'auto',editable:false,valueField:'text',textField:'text',url:'${ctx}/tcode/dict/zblx'"/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">计量单位：</label></td>
            <td class="width-30"><input name="unit" class="easyui-textbox" value="${entity.unit }" style="width: 100%;height: 30px;"
                                        data-options="validType:'length[0,10]'"/></td>

            <td class="width-20 active"><label class="pull-right">位号：</label></td>
            <td class="width-30"><input class="easyui-textbox" name="bitNo" value="${entity.bitNo }"
                                        style="width: 100%;height: 30px;" data-options="validType:'length[0,100]'"/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">阈值上限：</label></td>
            <td class="width-30"><input class="easyui-numberbox" name="thresholdUp" value="${entity.thresholdUp }" style="width: 100%;height: 30px;"
                                        data-options="required:true,precision:2"/></td>

            <td class="width-20 active"><label class="pull-right">阈值上上限：</label></td>
            <td class="width-30"><input name="thresholdUpplus" class="easyui-numberbox" value="${entity.thresholdUpplus }" style="width: 100%;height: 30px;"
                                        data-options="required:true,precision:2"/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">阈值下限：</label></td>
            <td class="width-30"><input name="thresholdDown" class="easyui-numberbox" value="${entity.thresholdDown }" style="width: 100%;height: 30px;"
                                        data-options="required:true,precision:2"/></td>

            <td class="width-20 active"><label class="pull-right">阈值下下限：</label></td>
            <td class="width-30"><input class="easyui-numberbox" name="thresholdDownplus" value="${entity.thresholdDownplus }"
                                        style="width: 100%;height: 30px;" data-options="required:true,precision:2"/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">量程上限：</label></td>
            <td class="width-30"><input class="easyui-numberbox" name="rangeUp" value="${entity.rangeUp }"
                                        style="width: 100%;height: 30px;" data-options="required:true,precision:2"/></td>

            <td class="width-20 active"><label class="pull-right">量程下限：</label></td>
            <td class="width-30"><input class="easyui-numberbox" name="rangeDown" value="${entity.rangeDown }"
                                        style="width: 100%;height: 30px;" data-options="required:true,precision:2"/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">描述：</label></td>
            <td class="width-30" colspan="3"><input class="easyui-textbox" name="targetDescribe" value="${entity.targetDescribe }"
                                        style="width: 100%;height: 50px;" data-options="multiline:true,validType:'length[0,100]'"/></td>

        </tr>

        <c:if test="${not empty entity.ID}">
            <input type="hidden" name="ID" value="${entity.ID}"/>
            <input type="hidden" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
            <input type="hidden" name="createBy" value="${entity.createBy}"/>
            <input type="hidden" name="status" value="${entity.status}"/>
        </c:if>
        </tbody>
    </table>
</form>

<script type="text/javascript">
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    var validateForm;

    function doSubmit() {
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

</script>
</body>
</html>