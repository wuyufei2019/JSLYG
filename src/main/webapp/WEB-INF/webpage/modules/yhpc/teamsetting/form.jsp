<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>班组排版设置</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
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

    </script>
    <style type="text/css">

        .dragitem {
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 15px;
            font-weight: bold;
            height: 50px;
        }

        .targetarea {
            height: 50px;
        }

        .proxy {
            border: 1px solid #ccc;
            width: 80px;
            background: #fafafa;
        }


        input {
            border: none;
            text-align: center;
        }

        .til-1 span {
            flex-shrink: 0;
            font-size: 14px;
            font-weight: bold
        }

        .til-1 {
            display: flex;
            align-items: center;
            margin: 10px;
            font-size: 14px;
            font-weight: bold
        }

        .row-1 {
            background: rgb(246, 247, 250);
        }

        .btn-1 {
            flex-shrink: 0;
            padding: 8px 12px;
            border-radius: 5px;
            color: white;
            background: rgb(92, 184, 92);
            margin-left: 10px
        }

        .btn-2 {
            flex-shrink: 0;
            padding: 8px 12px;
            border-radius: 5px;
            color: white;
            background: rgb(91, 192, 222);
            margin-left: 10px
        }

        .table-1 {
            border: solid rgb(236, 237, 244);
            border-width: 2px 0px 0px 2px;
        }

        .table-1 td {
            border: solid rgb(236, 237, 244);
            border-width: 0px 2px 2px 0px;
        }

        .table-1 .th-1 {
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 15px;
            font-weight: bold
        }

        .table-1 .easyui-droppable.th-1 {
            color: rgb(91, 192, 222)
        }
    </style>

</head>
<body>

<form id="inputForm" action="${ctx}/yhpc/teamsetting/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">选择部门：</label></td>
            <td class="width-35" colspan="3">
                <input type="text" class="easyui-combotree" id="dept" name="dept"
                       style="width: 100%;height: 30px;"
                       data-options="required:true, valueField:'id', textField:'text',multiple:
                       true,panelHeight:'200',url:'${ctx}/system/department/idjson'"/>
            </td>
        </tr>

        <tr>
            <td colspan="4" id="worksort">
                <table style="width: 100%;">
                    <tr>
                        <td style="width: 25%;display:flex">
                            <div class="title til-1"><span>每日班次</span><a class="btn-1" onclick="addWorkSort()">添加排班
                            </a></div>
                        </td>
                        <td style="width: 5%;">
                        </td>
                        <td>
                            <div class="title til-1">选择循环周期
                                <a class="btn-2" onclick="addCircle()">循环周期</a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%;">
                            <table class="table-1" id="worksorttable" style="width: 100%;">
                                <tr>
                                    <td>
                                        <div class="title th-1 targetarea">时间段
                                            <span style="color: red">(拖拽时间段至右侧)</span></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 100%;">
                                        <div class="dragitem row-1">休息</div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td style="width: 5%;">
                        </td>
                        <td style="display: flex;">
                            <table class="table-1" style="width: 100%;" id="teamsort">
                                <tr>
                                    <td id="teamtimetd">
                                        <div class="title th-1 targetarea">时间</div>
                                    </td>
                                </tr>

                                <!-- row-1 为背景换色 循环时标注 -->
                                <tr class="row-1" id="tr1">
                                    <td style="width: 25%;">
                                        <div class="easyui-droppable targetarea th-1" id="first">
                                            <input type="hidden" name="date">
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>

            </td>
        </tr>
        </tbody>
    </table>
    <div style="display: none;width: 100%;height: 40px;padding: 20px 50px " id="worktime">
        开始时间:&nbsp;&nbsp;<input id="worksttime" width="30px" class="easyui-timespinner"
                                data-options="required:true,showSeconds:true"/><br/>
        <div style="margin-top: 10px">
            结束时间:&nbsp;&nbsp;<input id="workedtime" width="30px" class="easyui-timespinner"
                                    data-options="required:true,showSeconds:true"/>
        </div>


        <script type="text/javascript">
            $(function () {
                var now = new Date();
                $("#first").append(now.format("yyyy-MM-dd"));
                $("#first input[name='date']").val(now.format("yyyy-MM-dd"));
                $("#dept").combotree({
                    cascadeCheck: false,
                    onChange: function (rec) {
                        if ($("#teamsort tr").length > 2) {
                            layer.msg("已添加循环周期,不能修改");
                            return false;
                        }
                        var nodes = $(this).combotree('tree').tree('getChecked');
                        $.each($("#teamsort td:first").nextAll(), function (index, item) {
                            $(item).remove();
                        });
                        $.each($("#tr1 td:first").nextAll(), function (index, item) {
                            $(item).remove();
                        });
                        $.each(nodes, function (index, item) {
                            var html = ' <td id="teamtimetd"> <div class="title th-1 targetarea">' +
                                item.text + '</div> </td>';
                            $("#teamsort tr:first").append(html);

                            var html2 = '<td style="width: 25%;"> <div class="easyui-droppable targetarea target th-1"> ' +
                                '<input type="hidden" class="timesort" name="worktime"> </div> </td>';
                            $("#tr1").append(html2);

                        });
                        loadSource();
                        loadTarget();


                    },
                    onBeforeCheck: function (node) {
                        if (!$("#dept").combotree("tree").tree('isLeaf', node.target)) {
                            return false;
                        }
                    }
                });

            });

            function addWorkSort() {
                layer.open({
                    area: ['350px', '200px'],
                    type: 1,
                    content: $('#worktime'),
                    btn: ['确定', '关闭'],
                    zIndex: 100,
                    yes: function (index, layero) {
                        var st = $("#worksttime").timespinner("getValue");
                        var ed = $("#workedtime").timespinner("getValue");
                        var html = ' <tr> <td style="width: 100%;"> <div class="dragitem row-1"> ' +
                            st + "-" + ed + ' </div> </td> </tr>';
                        $("#worksorttable tbody").append(html);
                        loadTarget();
                        loadSource();
                    },
                    cancel: function (index) {
                    }
                });
            }

            function addCircle() {
                if (!$("#dept").combotree("getValue")) {
                    layer.msg("请先选择部门班组！");
                    return false;
                }
                var len = $("#teamsort tr:first td").length;
                var now = new Date();
                now.setDate(now.getDate() + ($("#teamsort tr").length - 1));
                var html =
                    ' <tr class="row-1" > <td style="width: 25%;"> <div class="easyui-droppable targetarea th-1">' +
                    now.format("yyyy-MM-dd") + ' <input type="hidden"  name="date" value="' +
                    now.format("yyyy-MM-dd") + '"> </div> </td>';
                for (var i = 0; i < len - 1; i++) {
                    html += '  <td style="width: 25%;"> <div class="easyui-droppable targetarea target th-1" > ' +
                        '<input type="hidden"  name="worktime"> </div> </td>';
                }
                $("#teamsort").append(html);
                loadTarget();
            }

            function loadSource() {
                $('.dragitem').draggable({
                    revert: true,
                    deltaX: 10,
                    deltaY: 10,
                    proxy: function (source) {
                        var n = $('<div class="proxy"></div>');
                        n.html($(source).html()).appendTo('#source');
                        return n;
                    }
                });
            }

            function loadTarget() {
                $('.target').droppable({
                    accept: '.dragitem',
                    onDrop: function (e, source) {
                        var result = $(source).html();
                        var html = "";
                        //var parentTime = $(this).parent().parent().find("td:first input[name='time']").val();
                        if (result != '休息') {
                            $(this).find("input[name='worktime']").val(result);
                        }
                        html = $(this).find("input[name='worktime']").prop("outerHTML");
                        $(this).html(html + result);
                    }
                });
            }

        </script>
</body>
</html>