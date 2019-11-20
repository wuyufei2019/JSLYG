<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>计划考核管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<form id="inputForm" action="" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">制表人：</label></td>
            <td class="width-35"><input id="lister" class="easyui-textbox" name="lister"
                                        style="width: 100%;height: 30px;"
                                        data-options=""/></td>
            <td class="width-15 active"><label class="pull-right">批准人：</label></td>
            <td class="width-35"><input id="reviewer" class="easyui-textbox" name="reviewer"
                                        style="width: 100%;height: 30px;"
                                        data-options=""/></td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">填写日期：</label></td>
            <td class="width-35"><input name="S1" id="S1" class="easyui-datetimebox" value="${entity.s1}"
                                        style="width: 100%;height: 30px;"
                                        data-options="editable:false,required:'true'"/></td>

        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">考核内容：</label></td>
            <td class="width-35" colspan="3"><input name="content" id="content" class="easyui-textbox"
                                                    value="${entity.content}"
                                                    style="width: 100%;height: 30px;"
                                                    data-options="required:'true'"/></td>

        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">考核标准：</label></td>
            <td class="width-35" colspan="3"><input name="standard" id="standard" class="easyui-textbox"
                                                    value="${entity.standard}"
                                                    style="width: 100%;height: 30px;"
                                                    data-options="required:'true'"/></td>

        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">备注：</label></td>
            <td class="width-35" colspan="3"><input name="note" id="note" class="easyui-textbox" value="${entity.note}"
                                                    style="width: 100%;height: 30px;"
                                                    data-options=""/></td>

        </tr>

        </tbody>
    </table>
</form>
<div class="easyui-accordion" id="accordion" border="false">
</div>
<script type="text/javascript">
    var dg;
    var action = '${action}';
    var planId = '${planId}';
    var dgdata;//记录datagrid的的加载数据
    $(function () {
        if (action == 'create') {
            $("#S1").datetimebox("setValue", new Date().format("yyyy-MM-dd hh:mm:ss"));
            $("#lister").textbox("setValue", '${username}');
            deal(planId);
        }

    });

    function deal(planId) {
        $('#accordion').accordion({height: 'auto'});//调整datagrid高度
        if ($('#accordion').accordion('getPanel', 0))
            $('#accordion').accordion('remove', 0);
        $('#accordion').accordion('add', {
            title: '待考核人',
            content: '<table id="dg"></table>',
            selected: true,
        });
        dg = $('#dg').datagrid({
            method: "post",
            queryParams: {planId: planId},
            url: '${ctx}/erm/train-plan/week/person-list',
            fitColumns: true,
            border: false,
            idField: 'name',
            striped: true,
            rownumbers: true,
            nowrap: false,
            scrollbarSize: 0,
            singleSelect: true,
            striped: true,
            columns: [[
                {field: 'name', title: '员工姓名', sortable: false, width: 50},
                {
                    field: 'pass', title: '是否合格', sortable: false, width: 40, editor: {
                        type: 'combobox',
                        options: {
                            data: [{text:'合格'},{text:'不合格'}],
                            valueField: "text",
                            textField: "text",
                            editable: false,
                            panelHeight: 'auto',
                            required: true
                        }
                    }
                    , formatter: function
                        (value, row) {
                        if (value) return new Date(value).format("yyyy-MM-dd");
                        else return '';
                    }
                },

                {
                    field: 'recorder', title: '记录人', sortable: false, width: 30, editor: 'text',
                    formatter: function (value, row) {
                        if (!value) {
                            row.receiveperson = row.ename;
                        }
                        return row.receiveperson;
                    }
                },
                {
                    field: 'reviewer', title: '考核人', sortable: false, width: 30, editor: 'text',
                    formatter: function (value, row) {
                        if (!value) {
                            row.receiveperson = row.ename;
                        }
                        return row.receiveperson;
                    }
                }, {
                    field: 'caozuo', title: '操作', sortable: false, width: 30, align: 'center',
                    formatter: function (value, row, index) {
                        return "<a class=' fa fa-times-circle btn btn-danger btn-xs' onclick='remove(" + index + ")'>移除</a>";
                    }
                }
            ]],
            onClickCell: function (index) {
                clickfun(index);
            },
            onDblClickRow: function (index) {
                clickfun(index);
            },
            onLoadSuccess: function () {
                dgdata = dg.datagrid('getData').rows;
                editIndex=0;
                dg.datagrid('beginEdit', 0);
                $("#datagrid-row-r1-2-0").find("td[field='pass']").find("input[type='text']").focus();
            },
            checkOnSelect: false,
            selectOnCheck: false
        });
    }

    function clickfun(index) {
        var row = dg.datagrid('getData').rows[index];
        if (endEditing()) {
            editIndex = index;
            dg.datagrid('beginEdit', index);
        }
    }

    function remove(index) {
        dgdata.splice(index, 1);
        dg.datagrid('loadData', dgdata);
    }

    var editIndex = undefined;

    function endEditing() {
        if (editIndex == undefined) {
            return true;
        }
        if (dg.datagrid('validateRow', editIndex)) {
            dg.datagrid('endEdit', editIndex);
            //编辑过的行为1
            dg.datagrid("getData").rows[editIndex].flg = true;
            return true;
        } else {
            return false;
        }
    }


    //提交处理
    var layerindex = parent.layer.getFrameIndex(window.name); //获取窗口索引
    function exportbd() {
        var time = $("#time").datetimebox("getValue");
        var rows = dg.datagrid("getData").rows;
        var list = [];
        for (var index in rows) {
            var row = rows[index];
            var entity = {
                "jobtype": row.jobtype,
                "ename": row.ename,
                "goodsname": row.goodsname,
                "amount": row.amount,
                "specifications": row.specifications
            };
            list.push(entity);
        }
        $.ajax({
            type: 'POST',
            url: "${ctx}/lbyp/ffgl/exportbd",
            dataType: "text",
            contentType: "application/json",
            data: JSON.stringify(list),
            success: function (data) {
                window.open('${ctx}/download/' + data);
            }
        });
    }

    function doSubmit() {
        var time = $("#time").datetimebox("getValue");
        var rows = dg.datagrid("getData").rows;
        var list = [];
        for (var index in rows) {
            var row = rows[index];
            var entity = {
                "id1": row.eid,
                "goodsname": row.goodsname,
                "amount": row.amount,
                "receiveperson": row.receiveperson,
                "time": new Date(time.replace(/-/, "/"))
            };
            list.push(entity);
        }
        $.ajax({
            type: 'POST',
            url: "${ctx}/lbyp/ffgl/createall",
            dataType: "text",
            contentType: "application/json",
            data: JSON.stringify(list),
            success: function (data) {
                $.jBox.closeTip();
                if (data == 'success') {
                    parent.layer.open({
                        icon: 1,
                        title: '提示',
                        offset: 'rb',
                        content: '操作成功！',
                        shade: 0,
                        time: 2000
                    });
                }
                else
                    layer.open({
                        icon: 2,
                        title: '提示',
                        offset: 'rb',
                        content: '操作失败！',
                        shade: 0,
                        time: 2000
                    });
                parent.dg.datagrid('reload');
                parent.layer.close(layerindex);//关闭对话框。

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
    }

</script>


</body>
</html>