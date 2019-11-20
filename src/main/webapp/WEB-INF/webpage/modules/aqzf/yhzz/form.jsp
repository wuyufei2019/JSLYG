<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>企业变更</title>
    <meta name="decorator" content="default"/>
    <script src="${ctxStatic}/common/webUpload.js"></script>
</head>
<body style="height: 100%">

<form id="inputForm" action="" method="post" class="form-horizontal">
    <div class="easyui-accordion" id="accordion" border="true" data-options="multiple:true" style="padding-right: 10px">
        <div id="basicinfo" title="检查记录" border="false">
            <table class="table table-bordered  table-condensed dataTables-example dataTable ">
                <tbody>
                <tr>
                    <td class="width-15 active"><label class="pull-right">检查编号：</label></td>
                    <td class="width-35" colspan="3">${record.m0 }</td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">被检查单位：</label></td>
                    <td class="width-35" colspan="3">${record.qyname }</td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">地址：</label></td>
                    <td class="width-35" colspan="3">
                        ${record.m1 }
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">法定代表人（负责人）：</label></td>
                    <td class="width-35">${record.m2 }</td>
                    <td class="width-15 active"><label class="pull-right">职务：</label></td>
                    <td class="width-35">${record.m3 }</td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
                    <td class="width-35">${record.m4 }</td>
                    <td class="width-15 active"><label class="pull-right">检查场所：</label></td>
                    <td class="width-35">${record.m5 }</td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">检查时间起：</label></td>
                    <td class="width-35">${record.m6 }</td>
                    <td class="width-15 active"><label class="pull-right">检查时间止：</label></td>
                    <td class="width-35">${record.m7 }</td>
                </tr>

                <tr>
                    <td class="width-15 active"><label class="pull-right">检查人员：</label></td>
                    <td class="width-35" colspan="3">${record.m8 }</td>
                </tr>
                <input type="hidden" name="ID1" value="${record.id}">

                </tbody>
            </table>

        </div>
        <c:forEach var="hidden" items="${hiddens}" varStatus="status">
            <div title="<c:if test="${hidden.id2 !=0 }">${hidden.jcdy }--${hidden.jcnr }</c:if>
                    <c:if test="${hidden.id2 ==0 }">其他问题</c:if>" border="false"
                 id="compare"
                 style="height: 440px">
                <div class="easyui-layout" fit="true" id="layoutt">
                    <div id="center" data-options="region:'center'" selected="true" collapsible="true">
                        <table id="comparetable" class="table table-bordered  dataTables-example dataTable no-footer"
                               style="width:100%;">
                            <tbody>
                            <tr>
                                <td class="width-15 active"><label class="pull-right">隐患名称：</label></td>
                                <td class="width-3 active text-center " colspan="3">
                                    <input name="name" class="easyui-textbox" value="${hidden.name }"
                                           style="width: 100%;height: 30px;" data-options=" readonly:'true' "/>
                                </td>
                            </tr>
                            <tr>
                                <td class="width-25 active text-center " colspan="2">整改前
                                </td>
                                <td class="width-25 active text-center " style="color: red" colspan="2">整改后
                                </td>
                            </tr>
                            <tr>
                                <td class="width-15 active"><label class="pull-right">检查情况：</label></td>
                                <td class="width-35">${hidden.condi }</td>
                                <td class="width-15 active"><label class="pull-right">整改人：</label></td>
                                <td class="width-35">
                                    <input name="person1" class="easyui-textbox" value="${entity.person1 }"
                                           style="width: 100%;height: 30px;" data-options=" required:'true' "/>
                                </td>
                            </tr>
                            <tr>
                                <td class="width-15 active"><label class="pull-right">需整改的问题：</label></td>
                                <td class="width-35" height="80px">${hidden.que }</td>
                                <td class="width-15 active"><label class="pull-right">负责人：</label></td>
                                <td class="width-35">
                                    <input name="person2" class="easyui-textbox" value="${entity.person2 }"
                                           style="width: 100%;height: 30px;" data-options=" required:'true' "/>
                                </td>
                            </tr>
                            <tr>
                                <td class="width-15 active"><label class="pull-right">涉嫌违法行为：</label></td>
                                <td class="width-35"><input type="text" class="easyui-combobox"
                                                            value="${hidden.unlaw}"
                                                            style="width: 100%;height: 50px"
                                                            data-options="multiline:true,valueField: 'id',textField:
                                                        'text',url:'${ctx}/aqzf/wfxwtwo/idlist'"/>
                                </td>
                                <td class="width-15 active"><label class="pull-right">整改时限(天)：</label></td>
                                <td class="width-35">
                                    <input class="easyui-numberspinner" name="time" value="10"
                                           data-options="increment:1"
                                           style="width:100%;height: 30px">
                                </td>
                            </tr>
                            <tr>
                                <td class="width-15 active"><label class="pull-right">整改前图片：</label></td>
                                <td class="width-35">
                                    <c:if test="${not empty hidden.pic}">
                                        <c:set var="url" value="${fn:split(hidden.pic, '||')}"/>
                                        <div style="margin-bottom: 10px;">
                                            <a href="${url[0]}" target="_blank"><img style="width:100px;height: 100px;"
                                                                                     src="${url[0]}"></a>
                                        </div>
                                    </c:if>
                                </td>
                                <td class="width-15 active"><label class="pull-right">整改后图片：</label></td>
                                <td class="width-35">
                                    <div id="pic_uploader_${status.index+1}" style="margin-left:10px"></div>
                                </td>
                            </tr>

                            <input type="hidden" name="ID2" value="${hidden.id}">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>

</form>

<script type=" text/javascript">
    var action = '${action}';
    var len = ${fn:length(hiddens)};
    $(function () {

        for (var i = 1; i <= len; i++) {
            $('#accordion').accordion('select', i);
            $("#pic_uploader_" + i).WebImageUpload({fileNumLimit:1,hiddenInputId: 'pic', existurl: '${entity.pic}'});
        }
    });

    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

    function doSubmit() {
        var pic = $("input[name='pic']").length;
        if(pic < len){
            layer.msg("请上传图片");
            return;
        }
        confirmx('添加后不能修改，确定要添加隐患整治信息吗?', function (index) {
            var obj = $("#inputForm").serialize();
            $.ajax({
                type: 'POST',
                url: '${ctx}/aqzf/yhzz/${action}',
                data: obj,
                success: function (data) {
                    $.jBox.closeTip();
                    if (data == 'success') {

                        parent.dg.datagrid('reload');
                        parent.layer.closeAll();//关闭对话框。
                        parent.layer.open({
                            icon: 1,
                            title: '提示',
                            offset: 'rb',
                            content: '操作完成！',
                            shade: 0,
                            time: 2000
                        });
                    } else {

                        parent.dg.datagrid('reload');
                        parent.layer.closeAll();//关闭对话框。
                        parent.layer.open({
                            icon: 2,
                            title: '提示',
                            offset: 'rb',
                            content: '操作失败！',
                            shade: 0,
                            time: 2000
                        });
                    }
                },
                beforeSend: function () {
                    var isValid = $("#inputForm").form('validate');
                    if (isValid) {
                        $.jBox.tip("正在提交，请稍等...", 'loading', {
                            opacity: 0
                        });
                        return true;
                    }
                    return false; // 返回false终止表单提交
                }
            });
        });
    }
</script>

</body>
</html>