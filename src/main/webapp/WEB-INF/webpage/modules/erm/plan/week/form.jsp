<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>训练周计划管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<form id="inputForm" action="${ctx}/erm/train-plan/week/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tr>
            <td class="width-5 active" style="text-align: center"
                colspan="7">计划名称：<input class="easyui-textbox" style="width: 350px;height: 30px;" name="planName"
                                        value="${entity.planName}" id="planName" data-options="required:true"></td>
        </tr>
        <tr>
            <td class="width-5 "><label class="pull-right">岗位部门：</label></td>
            <td class="width-30" colspan="4"><input style="width: 200px;height: 30px;" name="deptName"
                                                    class="easyui-combobox" value="${entity.deptName }"
                                                    data-options="required:true,valueField: 'text',multiple:true,
				                                    textField: 'text',url: '${ctx}/system/department/deptjson'"/></td>
            <td class="width-10 "><label class="pull-right">时间：</label></td>
            <td class="width-30"><input style="width: 200px;height: 30px;" class="easyui-datebox"
                                        id="createtime" readonly="true"
                                        value="${entity.s1 }" data-options=""/></td>
        </tr>


        <tr style="text-align: center">
            <td class="width-5 active" colspan="2"><label>时间：</label></td>
            <td class="width-30 active"><label>训练内容：</label></td>
            <td class="width-10 active"><label>地点：</label></td>
            <td class="width-10 active"><label>参加人：</label></td>
            <td class="width-10 active"><label>组训责任人：</label></td>
            <td class="width-30 active"><label>落实情况：</label></td>
        </tr>
        <c:if test="${action eq 'create'}">

            <tr>
                <td class="width-5 " rowspan="2">周一：<input style="width: 100px;height: 30px;" class="easyui-datebox"
                                                           name="time" id="week1"
                                                           value="${detail.time }" data-options="required:true"></td>
                <td class="width-5">上午<input style="width: 50px;height: 30px;" class="easyui-numberspinner" name="hours"
                                             value="${detail.hours }" data-options="">小时
                </td>
                <input type="hidden" name="time1" value="上午">
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="content"
                                             value="${detail.content }" data-options="multiline:true"></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="address"
                                             value="${detail.address }" data-options=""></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-combobox persons"
                                             value="${detail.persons }"
                                             data-options="valueField: 'text',multiple:true,
				                                    textField: 'text',url: '${ctx}/system/user/ryjson?qyid=${qyid}'">
                    <input type="hidden" name="persons">
                </td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="duty"
                                             value="${detail.duty }" data-options=""></td>
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="situation"
                                             value="${detail.situation }" data-options="multiline:true"></td>
            </tr>
            <tr>
                <td class="width-5">下午<input style="width: 50px;height: 30px;" class="easyui-numberspinner" name="hours"
                                             value="${detail.hours }" data-options="">小时
                </td>
                <input type="hidden" name="time1" value="下午">
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="content"
                                             value="${detail.content }" data-options="multiline:true"></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="address"
                                             value="${detail.address }" data-options=""></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-combobox persons"
                                             value="${detail.persons }"
                                             data-options="valueField: 'text',multiple:true,
				                                    textField: 'text',url: '${ctx}/system/user/ryjson?qyid=${qyid}'">
                    <input type="hidden" name="persons">
                </td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="duty"
                                             value="${detail.duty }" data-options=""></td>
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="situation"
                                             value="${detail.situation }" data-options="multiline:true"></td>
            </tr>
            <tr>
                <td class="width-5 " rowspan="2">周二：<input style="width: 100px;height: 30px;" class="easyui-datebox"
                                                           name="time" id="week2" readonly="true"
                                                           value="${detail.time }" data-options=""></td>
                <td class="width-5">上午<input style="width: 50px;height: 30px;" class="easyui-numberspinner" name="hours"
                                             value="${detail.hours }" data-options="">小时
                </td>
                <input type="hidden" name="time1" value="上午">
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="content"
                                             value="${detail.content }" data-options="multiline:true"></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="address"
                                             value="${detail.address }" data-options=""></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-combobox persons"
                                             value="${detail.persons }"
                                             data-options="valueField: 'text',multiple:true,
				                                    textField: 'text',url: '${ctx}/system/user/ryjson?qyid=${qyid}'">
                    <input type="hidden" name="persons">
                </td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="duty"
                                             value="${detail.duty }" data-options=""></td>
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="situation"
                                             value="${detail.situation }" data-options="multiline:true"></td>
            </tr>
            <tr>
                <td class="width-5">下午<input style="width: 50px;height: 30px;" class="easyui-numberspinner" name="hours"
                                             value="${detail.hours }" data-options="">小时
                </td>
                <input type="hidden" name="time1" value="下午">
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="content"
                                             value="${detail.content }" data-options="multiline:true"></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="address"
                                             value="${detail.address }" data-options=""></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-combobox persons"
                                             value="${detail.persons }"
                                             data-options="valueField: 'text',multiple:true,
				                                    textField: 'text',url: '${ctx}/system/user/ryjson?qyid=${qyid}'">
                    <input type="hidden" name="persons">
                </td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="duty"
                                             value="${detail.duty }" data-options=""></td>
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="situation"
                                             value="${detail.situation }" data-options="multiline:true"></td>
            </tr>
            <tr>
                <td class="width-5 " rowspan="2">周三：<input style="width: 100px;height: 30px;" class="easyui-datebox"
                                                           name="time" id="week3" readonly="true"
                                                           value="${detail.time }" data-options=""></td>
                <td class="width-5">上午<input style="width: 50px;height: 30px;" class="easyui-numberspinner" name="hours"
                                             value="${detail.hours }" data-options="">小时
                </td>
                <input type="hidden" name="time1" value="上午">
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="content"
                                             value="${detail.content }" data-options="multiline:true"></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="address"
                                             value="${detail.address }" data-options=""></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-combobox persons"
                                             value="${detail.persons }"
                                             data-options="valueField: 'text',multiple:true,
				                                    textField: 'text',url: '${ctx}/system/user/ryjson?qyid=${qyid}'">
                    <input type="hidden" name="persons">
                </td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="duty"
                                             value="${detail.duty }" data-options=""></td>
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="situation"
                                             value="${detail.situation }" data-options="multiline:true"></td>
            </tr>
            <tr>
                <td class="width-5">下午<input style="width: 50px;height: 30px;" class="easyui-numberspinner" name="hours"
                                             value="${detail.hours }" data-options="">小时
                </td>
                <input type="hidden" name="time1" value="下午">
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="content"
                                             value="${detail.content }" data-options="multiline:true"></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="address"
                                             value="${detail.address }" data-options=""></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-combobox persons"
                                             value="${detail.persons }"
                                             data-options="valueField: 'text',multiple:true,
				                                    textField: 'text',url: '${ctx}/system/user/ryjson?qyid=${qyid}'">
                    <input type="hidden" name="persons">
                </td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="duty"
                                             value="${detail.duty }" data-options=""></td>
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="situation"
                                             value="${detail.situation }" data-options="multiline:true"></td>
            </tr>
            <tr>
                <td class="width-5 " rowspan="2">周四：<input style="width: 100px;height: 30px;" class="easyui-datebox"
                                                           name="time" id="week4" readonly="true"
                                                           value="${detail.time }" data-options=""></td>
                <td class="width-5">上午<input style="width: 50px;height: 30px;" class="easyui-numberspinner" name="hours"
                                             value="${detail.hours }" data-options="">小时
                </td>
                <input type="hidden" name="time1" value="上午">
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="content"
                                             value="${detail.content }" data-options="multiline:true"></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="address"
                                             value="${detail.address }" data-options=""></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-combobox persons"
                                             value="${detail.persons }"
                                             data-options="valueField: 'text',multiple:true,
				                                    textField: 'text',url: '${ctx}/system/user/ryjson?qyid=${qyid}'">
                    <input type="hidden" name="persons">
                </td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="duty"
                                             value="${detail.duty }" data-options=""></td>
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="situation"
                                             value="${detail.situation }" data-options="multiline:true"></td>
            </tr>
            <tr>
                <td class="width-5">下午<input style="width: 50px;height: 30px;" class="easyui-numberspinner" name="hours"
                                             value="${detail.hours }" data-options="">小时
                </td>
                <input type="hidden" name="time1" value="下午">
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="content"
                                             value="${detail.content }" data-options="multiline:true"></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="address"
                                             value="${detail.address }" data-options=""></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-combobox persons"
                                             value="${detail.persons }"
                                             data-options="valueField: 'text',multiple:true,
				                                    textField: 'text',url: '${ctx}/system/user/ryjson?qyid=${qyid}'">
                    <input type="hidden" name="persons">
                </td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="duty"
                                             value="${detail.duty }" data-options=""></td>
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="situation"
                                             value="${detail.situation }" data-options="multiline:true"></td>
            </tr>
            <tr>
                <td class="width-5 " rowspan="2">周五：<input style="width: 100px;height: 30px;" class="easyui-datebox"
                                                           name="time" id="week5" readonly="true"
                                                           value="${detail.time }" data-options=""></td>
                <td class="width-5">上午<input style="width: 50px;height: 30px;" class="easyui-numberspinner" name="hours"
                                             value="${detail.hours }" data-options="">小时
                </td>
                <input type="hidden" name="time1" value="上午">
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="content"
                                             value="${detail.content }" data-options="multiline:true"></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="address"
                                             value="${detail.address }" data-options=""></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-combobox persons"
                                             value="${detail.persons }"
                                             data-options="valueField: 'text',multiple:true,
				                                    textField: 'text',url: '${ctx}/system/user/ryjson?qyid=${qyid}'">
                    <input type="hidden" name="persons">
                </td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="duty"
                                             value="${detail.duty }" data-options=""></td>
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="situation"
                                             value="${detail.situation }" data-options="multiline:true"></td>
            </tr>
            <tr>
                <td class="width-5">下午<input style="width: 50px;height: 30px;" class="easyui-numberspinner" name="hours"
                                             value="${detail.hours }" data-options="">小时
                </td>
                <input type="hidden" name="time1" value="下午">
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="content"
                                             value="${detail.content }" data-options="multiline:true"></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="address"
                                             value="${detail.address }" data-options=""></td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-combobox persons"
                                             value="${detail.persons }"
                                             data-options="valueField: 'text',multiple:true,
				                                    textField: 'text',url: '${ctx}/system/user/ryjson?qyid=${qyid}'">
                    <input type="hidden" name="persons">
                </td>
                <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="duty"
                                             value="${detail.duty }" data-options=""></td>
                <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="situation"
                                             value="${detail.situation }" data-options="multiline:true"></td>
            </tr>
        </c:if>


        <c:if test="${action eq 'update'}">
            <c:forEach var="detail" items="${details}" varStatus="status" step="2">
                <tr>
                    <td class="width-5 " rowspan="2">周${status.count}：
                        <input style="width: 100px;height: 30px;" class="easyui-datebox" name="time"
                                            id="week5" readonly="true"
                                            value="${detail.time }" data-options="">
                    </td>
                    <td class="width-5">上午<input style="width: 50px;height: 30px;" class="easyui-numberspinner"
                                                 name="hours"
                                                 value="${detail.hours }" data-options="">小时
                    </td>
                    <input type="hidden" name="time1" value="上午">
                    <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="content"
                                                 value="${detail.content }" data-options="multiline:true"></td>
                    <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="address"
                                                 value="${detail.address }" data-options=""></td>
                    <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-combobox persons"
                                                 value="${detail.persons }"
                                                 data-options="valueField: 'text',multiple:true,
				                                    textField: 'text',url: '${ctx}/system/user/ryjson?qyid=${qyid}'">
                        <input type="hidden" name="persons" value="${detail.persons}">
                    </td>
                    <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="duty"
                                                 value="${detail.duty }" data-options=""></td>
                    <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox"
                                                 name="situation"
                                                 value="${detail.situation }" data-options="multiline:true"></td>
                </tr>
                <tr>
                    <td class="width-5">下午<input style="width: 50px;height: 30px;" class="easyui-numberspinner"
                                                 name="hours"
                                                 value="${details[status.index+1].hours }" data-options="">小时
                    </td>
                    <input type="hidden" name="time1" value="下午">
                    <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox" name="content"
                                                 value="${details[status.index+1].content }"
                                                 data-options="multiline:true"></td>
                    <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="address"
                                                 value="${details[status.index+1].address }" data-options=""></td>
                    <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-combobox persons"
                                                 value="${details[status.index+1].persons }"
                                                 data-options="valueField: 'text',multiple:true,
				                                    textField: 'text',url: '${ctx}/system/user/ryjson?qyid=${qyid}'">
                        <input type="hidden" name="persons">
                    </td>
                    <td class="width-10 "><input style="width: 100%;height: 30px;" class="easyui-textbox" name="duty"
                                                 value="${details[status.index+1].duty }" data-options=""></td>
                    <td class="width-30 "><input style="width: 100%;height: 50px;" class="easyui-textbox"
                                                 name="situation"
                                                 value="${details[status.index+1].situation }" data-options="multiline:true"></td>
                </tr>
            </c:forEach>

        </c:if>

        <tr>
            <td class="width-5 active" style="height: 40px" colspan="2"><label class="pull-right">要求：</label></td>
            <td class="width-10" colspan="5"><input type="text" style="width: 100%;height: 30px;" class="easyui-textbox"
                                                    value="${entity.require}" id="require" name="require"> </td>
        </tr>

        <tr>
            <td class="width-5 "><label class="pull-right">制表人：</label></td>
            <td class="width-30" colspan="4"><input style="width: 200px;height: 30px;" name="lister" id="lister"
                                                    class="easyui-textbox"
                                                    value="${entity.lister }" data-options="required:true"/></td>
            <td class="width-10 "><label class="pull-right">批准人：</label></td>
            <td class="width-30"><input style="width: 200px;height: 30px;" class="easyui-textbox"
                                        id="reviewer" name="reviewer"
                                        value="${entity.reviewer }" data-options="required:true"/></td>
        </tr>

        <c:if test="${not empty entity.ID}">
            <input type="hidden" name="ID" value="${entity.ID}"/>
            <input type="hidden" name="qyid" value="${entity.qyid}"/>
            <input type="hidden" name="qyName" value="${entity.qyName}"/>
            <input type="hidden" name="S1"
                   value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
            <input type="hidden" name="S3" value="${entity.s3}"/>
        </c:if>
    </table>

    <tbody>
</form>

<script type="text/javascript">
    var usertype =${usertype};
    var action = '${action}';
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

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


    $(function () {
        if ('${username}') {
            $("#lister").textbox("setValue", '${username}');
        }
        $("#week1").datebox({
            onSelect: function (value) {
                loadData(value);
            }
        });
        $(".persons").combobox({
            onSelect: function (value) {
                $(this).parent("td").find("input[name='persons']").val($(this).combobox("getText"))
            },
            onUnselect: function () {
                $(this).parent("td").find("input[name='persons']").val($(this).combobox("getText"))
            }
        });
        if (action == 'create') {
            $("#createtime").datebox("setValue", new Date().format("yyyy-MM-dd"));
            $("#require").textbox("setValue", "严格要求，严格标准，科学施训，注重方法，确保效果与安全双落实。");
            loadData(new Date());
        }

    });

    function loadData(data) {
        var planName = "应急救援技能训练周计划---";
        var week1 = getWeekStartDate(data);
        var st = week1.format("yyyy-MM-dd");
        planName += st;
        $("#week1").datebox("setValue", st);
        week1.setDate(week1.getDate() + 1);
        $("#week2").datebox("setValue", week1.format("yyyy-MM-dd"));
        week1.setDate(week1.getDate() + 1);
        $("#week3").datebox("setValue", week1.format("yyyy-MM-dd"));
        week1.setDate(week1.getDate() + 1);
        $("#week4").datebox("setValue", week1.format("yyyy-MM-dd"));
        week1.setDate(week1.getDate() + 1);
        var et = week1.format("yyyy-MM-dd");
        $("#week5").datebox("setValue", et);
        planName += ('至' + et);
        $("#planName").textbox("setValue", planName);
    }

    function getWeekStartDate(now) {
        var nowDayOfWeek = now.getDay(); //今天本周的第几天
        var nowDay = now.getDate(); //当前日
        var nowMonth = now.getMonth(); //当前月
        var nowYear = now.getFullYear(); //当前年
        var weekStartDate = new Date(nowYear, nowMonth, nowDay - (nowDayOfWeek - 1));
        return weekStartDate;
    }

</script>
</body>
</html>