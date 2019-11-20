<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>会议信息</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<form id="inputForm" action="${ctx}/zxjkyj/ysx/${action}" method="post" class="form-horizontal">

    <table
            class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">主题：</label></td>
            <td class="width-35" colspan="3"><input style="width: 100%;height: 30px;"
                                                    name="strSubject" class="easyui-textbox"
                                                    value="${entity.strSubject }"
                                                    data-options="validType:'length[0,250]',required:'true'"/></td>
        <tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">会议议程：</label></td>
            <td class="width-35" colspan="3"><input style="width: 100%;height: 50px;"
                                                    name="strAgenda" class="easyui-textbox"
                                                    value="${entity.strAgenda.value }"
                                                    data-options="multiline:true"/></td>
        <tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">发起方式：</label></td>
            <td class="width-35">
                <input style="width: 100%;height: 30px;" id="operateID"
                       name="operateID" class="easyui-combobox" value="${entity.operateID }"
                       data-options="editable:false,required:true,panelHeight:'auto',
												data:[{text:'即时会议',value:'1'},{text:'预约会议',value:'0'}]"/>
            </td>
            <td class="width-15 active"><label class="pull-right">会议时长：</label></td>
            <td class="width-35">
                <input style="width: 90%;height: 30px;" class="easyui-spinner" id="timeLen" name="timeLen"
                       value="${timeLen}"
                       data-options=""/>小时
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">开始时间：</label></td>
            <td class="width-35"><input style="width: 100%;height: 30px;" id="strBeginTime"
                                        name="strBeginTime" class="easyui-datetimebox"
                                        value="${entity.strBeginTime }"
                                        data-options="required:true"/>
            </td>
            <td class="width-15 active"><label class="pull-right">是否创建直播：</label></td>
            <td class="width-35">
                <input type="radio" value="true" class="i-checks" name="createLive"/>是
                <input type="radio" value="false" class="i-checks" name="createLive" checked="checked"/>否
            </td>

        </tr>

        <c:if test="${action eq 'create'}">
        <tr>
            <td class="width-15 active"><label class="pull-right">与会人员：</label></td>
            <td class="width-35" colspan="3">
                <input style="width: 100%;height: 50px;"
                       name="memberIds" class="easyui-combobox" value="${entity.MemberIds }"
                       data-options="multiple:true,multiline:true,valueField: 'id',textField: 'text',
							   url:'${ctx}/ysx/sbsz/jsonlist',prompt: '不选择默认全部参与 '"/>
            </td>
        </tr>
        </c:if>
        <tbody>
        <c:if test="${not empty entity.strConfID}">
        <input type="hidden" name="strConfID" value="${entity.strConfID}">
        </c:if>

    </table>
    <c:if test="${not empty entity.ID}">
        <input type='hidden' name="ID" value="${entity.ID}"/>
        <input type="hidden" name="ID1" value="${entity.ID1}"/>
        <input type="hidden" name="S1" value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
        <input type="hidden" name="S3" value="${entity.s3}"/>
    </c:if>
</form>


<script type="text/javascript">
    var action = '${action}';
    $(function () {

        $('#timeLen').spinner({
            required: true,
            max: 6,
            spin: function (down) {
                var value = parseFloat($(this).spinner("getValue"));
                if (down) {
                    if (value > 0.5)
                        $(this).spinner("setValue", value - 0.5);
                } else {
                    if (value < 5.5)
                        $(this).spinner("setValue", value + 0.5);
                }
            },
            validType: 'number'
        });

        $("#operateID").combobox({
            onSelect: function (item) {
                if (item.value == 1) {
                    $("#strBeginTime").datetimebox("setValue", new Date().format("yyyy-MM-dd hh:mm:ss"));
                    $("#strBeginTime").datetimebox("readonly", true);
                } else {
                    $("#strBeginTime").datetimebox("setValue", '');
                    $("#strBeginTime").datetimebox("readonly", false);
                }
            }
        });

        if (action == 'create') {
            $('#timeLen').spinner("setValue", 0.5);
        }else if(action == 'update'){
            $("#operateID").combobox("readonly",true);
        }
    });


    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

    function doSubmit() {
        var divtxt = $("#qyList").text();
        if (divtxt == null || divtxt == '') {
            top.layer.confirm('您还没有选择任何用户，这将会将信息发送给所有用户!', {
                icon: 3,
                title: '提示'
            }, function (index) {
                $("#inputForm").serializeObject();
                $("#inputForm").submit();
                top.layer.close(index);
            });
        } else {
            $("#inputForm").serializeObject();
            $("#inputForm").submit();
        }

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
                if (data.indexOf('success') != -1) {
                    parent.layer.open({
                        icon: 1,
                        title: '提示',
                        offset: 'rb',
                        content: '操作成功！',
                        shade: 0,
                        time: 2000
                    });
                    if ($("#operateID").combobox("getValue") == 1) {
                        parent.layer.open({
                            type: 2,
                            shift: 1,
                            area: ["100%", "100%"],
                            title: "会议控制",
                            maxmin: true,
                            content: ctx + '/zxjkyj/ysx/control/' + data.split(":")[1],
                            btn: ['关闭'],
                            cancel: function (index) {
                            }
                        });
                    }
                }
                else{
                    top.layer.msg("创建失败");
                }

                parent.dg.datagrid('reload');
                parent.layer.close(index);//关闭对话框


            }
        });

    });
</script>
</body>
</html>