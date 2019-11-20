<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>储罐监测维护数据信息</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<form id="inputForm" action="${ctx}/yjjy/live/setting/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-20 active"><label class="pull-right">直播间归属：</label></td>
            <td class="width-30" colspan="3">
                <input type="radio" class='i-checks' id="qy" name="type" value="1" checked>企业
                <input type="radio" class='i-checks' id="zf" name="type" value="0">政府
                <input type="radio" class='i-checks' id="pub" name="type" value="2">公共
            </td>
        </tr>

        <tr id="qy1">
            <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
            <td class="width-30" colspan="3">
                <input value="${entity.qyid }" id="qyid" name="qyid" style="width: 100%;height: 30px;"
                       class="easyui-combobox"
                       data-options="panelHeight:'100px',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt:'安监局所属直播间不要选择此项'"/>
            </td>
        </tr>
        <tr id="zf1" style="display: none">
            <td class="width-20 active"><label class="pull-right">行政区域：</label></td>
            <td class="width-30" colspan="3">
                <input value="${entity.xzqy }" id="xzqy" name="xzqy" style="width: 100%;height: 30px;"
                       class="easyui-combotree"
                       data-options="panelHeight:'100px',valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson',prompt:'企业所属直播间不要选择此项'"/>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">直播间标题：</label></td>
            <td class="width-30"><input name="title" id="title" class="easyui-textbox"
                                        value="${entity.title }" style="width: 100%;height: 30px;"
                                        data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">直播间id：</label></td>
            <td class="width-30"><input name="uuid" id="uuid" class="easyui-textbox"
                                        value="${entity.uuid }" style="width: 100%;height: 30px;"
                                        data-options="editable:false,validType:'length[0,100]'"/></td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">标签：</label></td>
            <td class="width-30" colspan="3"><input name="tag" id="tag1" class='i-checks' value="#教育培训#" type="radio"
                                                    checked>
                #教育培训#
                <input type="radio" class='i-checks' name="tag" id="tag2" value="#应急演练#">#应急演练#
                <input type="radio" class='i-checks' name="tag" id="tag3" value="#危险作业#">#危险作业#
                <input type="radio" class='i-checks' name="tag" id="tag4" value="#重大会议#">#重大会议#
                <input type="radio" class='i-checks' name="tag" id="tag5">
                <input  class="easyui-textbox" id="other"
                       style="width: 100px;height: 30px;"
                       data-options="prompt:'其他'"/>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">描述：</label></td>
            <td class="width-30" colspan="3"><input class="easyui-textbox" name="describe"
                                                    value="${entity.describe }"
                                                    style="width: 100%;height: 30px;"
                                                    data-options="validType:'length[0,50]'"/></td>
        </tr>

        <c:if test="${not empty entity.ID}">
            <input type="hidden" name="ID" value="${entity.ID}"/>
            <input type="hidden" name="live" value="${entity.live}"/>
        </c:if>

        </tbody>
    </table>
</form>

<script type="text/javascript">
    $(function () {
        $("#uuid").textbox("setValue", getUID());
        if('${action eq 'update'}'){
            if ('${entity.tag}' == "#教育培训#") {
                $('#tag1').iCheck('check');
            } else if ('${entity.tag}' == "#应急演练#") {
                $('#tag2').iCheck('check');
            } else if ('${entity.tag}' == "#危险作业#") {
                $('#tag3').iCheck('check');
            } else if ('${entity.tag}' == "#重大会议#") {
                $('#tag4').iCheck('check');
            }else{
                $('#tag5').iCheck('check');
                $("#other").textbox("setValue",'${entity.tag}');
            }

            if ('${entity.type}' == "0" ) {
                $('#zf').iCheck('check');
                tmp();

            } else if ('${entity.type}' == "1" ) {
                $('#qy').iCheck('check');
                $("#zf1").hide();
                $("#qy1").show();
                parseElm()
            }else if ('${entity.type}' == "2" ) {
                $('#pub').iCheck('check');
                tmp();
            }
        }




        $("#qy").on('ifChecked', function (event) {
            $("#zf1").hide();
            $("#qy1").show();
            parseElm()

        });
        $("#zf,#pub").on('ifChecked', function (event) {
            tmp();
        });
        function tmp() {
            $("#qy1").hide();
            $("#zf1").show();
            parseElm();
        }
        function parseElm() {
            $.parser.parse('#zf1')
            $.parser.parse('#qy1')
        }
    });
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

    function doSubmit() {
        $("#inputForm").submit();
    }

    function getUID() { // 获取唯一值
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }

    $(function () {
        var flag = true;
        $('#inputForm').form({
            onSubmit: function () {
                $("#tag5").val($("#other").textbox("getValue"));
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