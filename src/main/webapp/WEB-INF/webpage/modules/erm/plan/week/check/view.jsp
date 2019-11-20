<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>训练周计划管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>

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
                                         value="${details[status.index+1].situation }" data-options="multiline:true">
            </td>
        </tr>
    </c:forEach>


    <tr>
        <td class="width-5 active" style="height: 40px" colspan="2"><label class="pull-right">要求：</label></td>
        <td class="width-10" colspan="5">严格要求，严格标准，科学施训，注重方法，确保效果与安全双落实。</td>
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

</table>

</body>
</html>