<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>物料实时监测数据</title>
    <meta name="decorator" content="default"/>
</head>
<body class="gray-bg">
<div class="easyui-panel" style="width:100%;height:100%;">
    <div class="easyui-layout" data-options="fit:true">
        <div collapsible="true" title="企业列表" data-options="region:'west'" style="width:18%;height:100%">
            <div id="wgcd">
            </div>
        </div>
        <div data-options="region:'center'" style="width:65%;height:100%">
            <div id="tb" style="padding:5px;height:auto">

                <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">

                    <div class="form-group">
                        <input type="text" name="filter_LIKES_targetName" class="easyui-textbox" style="height: 30px;"
                               data-options="prompt: '指标名称' "/>
                        <input type="text" name="filter_EQI_status" class="easyui-combobox" style="height: 30px;"
                               data-options="panelHeight:'auto',data:[{text:'无信息',value:1},{text:'离线',value:2},{text:'低限报警',value:3},
                               {text:'高限报警',value:4},{text:'正常',value:5}],prompt: '指标状态' "/>
                        <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i
                                class="fa fa-search"></i> 查询</span>
                        <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i
                                class="fa fa-refresh"></i> 全部</span>
                    </div>
                </form>
                <%--   <div class="row">
                       <div class="col-sm-12">
                           <div class="pull-left">
                               <button class="btn btn-warning btn-sm" onclick="fileexport()"><i
                                       class="fa fa-external-link"></i> 导出
                               </button>
                               <button class="btn btn-primary btn-sm" onclick="refresh()"><i
                                       class="glyphicon glyphicon-repeat"></i> 刷新
                               </button>
                           </div>
                       </div>
                   </div>--%>
            </div>
            <table id="ssjc_wl_dg"></table>
        </div>
    </div>
</div>
<%-- 报警信息toast弹出框 --%>
<div id="msg__s" style="position:fixed;bottom:13px;left:280px">

</div>
</div>
<link href="${ctx}/static/jqueryToast/css/toast.style.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/static/jqueryToast/js/toast.script.js"></script>
<script type="text/javascript">
    var usertype = '${usertype}';
    var dg;
    var dg2;
    var Pqyname;
    var G_qyid = '';

    function getBjxx() {
        // 清空 消息列表
        $("#msg__s").html("");
        $.ajax({
            type: 'POST',
            url: ctx + '/zxjkyj/alarm/json',
            success: function (parseData) {
                if (parseData.length > 0) {
                    //playAudio();
                    $.each(parseData, function (index, el) {
                        toastInfo("<span onclick='showBjxx(" + el.id +
                            ")' style='cursor: pointer;'><span style='color: purple;'><strong>" + el.m1 +
                            el.target_name + el.bit_no +
                            "</strong></span> " + el.alarmtype + "，请及时处理！</span>");
                    });
                }
            }
        })
    }

    function showBjxx(id) {
        openDialog("报警处理", '${ctx}/zxjkyj/alarm/deal2/' + id, "700px", "400px", "");
    }

    function playAudio() {
        var mp3 = new Audio("${ctx}/static/model/MP3/alarm.mp3");
        mp3.play();
    }

    function toastInfo(msg) {
        $.Toast($("#msg__s"), "报警信息", msg, "error", {
            stack: false,
            has_icon: true,
            has_close_btn: true,
            fullscreen: false,
            timeout: 0,
            sticky: true,
            has_progress: false,
            rtl: false,
        });
    }


    $(function () {
        getBjxx();
        dg = $('#ssjc_wl_dg').datagrid({
            nowrap: false,
            method: "post",
            url: ctx + '/zxjkyj/points/value/list',
            fit: true,
            fitColumns: true,
            selectOnCheck: false,
            nowrap: false,
            idField: 'id',
            striped: true,
            scrollbarSize: 0,
            pagination: true,
            rownumbers: true,
            pageNumber: 1,
            pageSize: 50,
            pageList: [20, 50, 100, 150, 200, 250],
            singleSelect: true,
            columns: [[
                {field: 'id', title: 'id', checkbox: true, width: 50, align: 'center'},
                {field: 'targetName', title: '指标名称', sortable: false, width: 120, align: 'center'},
                {field: 'targetType', title: '监测类型', sortable: true, width: 80, align: 'center'},
                {field: 'unit', title: '监测点位单位', sortable: false, width: 80, align: 'center'},
                {field: 'thresholdUp', title: '阈值上限', sortable: true, width: 80, align: 'center'},
                {field: 'thresholdUpplus', title: '阈值上上限', sortable: true, width: 80, align: 'center'},
                {field: 'thresholdDown', title: '阈值下限', sortable: true, width: 80, align: 'center'},
                {field: 'thresholdDownplus', title: '阈值下下限', sortable: true, width: 80, align: 'center'},
                {
                    field: 'value', title: '当前值', sortable: false, width: 80, align: 'center',
                    formatter: function (value) {
                        if (!value)
                            return '无数据';
                        else return value;
                    }
                },
                {
                    field: 'collectTime', title: '采集时间', sortable: true, width: 80, align: 'center',
                    formatter: function (value) {
                        if (value)
                            return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                    }
                },
                {
                    field: 'status', title: '状态', sortable: false, width: 80, align: 'center',
                    formatter: function (value, row) {
                        if (value == 1)
                            return '<a >无信息<a>';
                        else if (value == 2) {//离线
                            return '<a >离线<a>';
                        }
                        else if (value == 4) {
                            return '<a >高限报警<a>';
                        } else if (value == 3) {
                            return '<a >低限报警<a>';
                        }
                    }
                },
            ]],
            onDblClickRow: function (rowdata, row) {
                openDialogView("查看统计信息", '${ctx}/zxjkyj/points/value/statistics?pointId=' + row.id,
                    "100%", "100%");
            },
            onLoadSuccess: function () {
                $(this).datagrid("autoMergeCells", ['qyname']);
            },
            onLoadError: function () {
                layer.open({title: '提示', offset: 'rb', content: '数据加载中，请耐心等待。', shade: 0, time: 2000});
            },
            rowStyler: function (index, row) {
                if (row.status == 1) { //无信息
                    return 'background-color:#dadada;';
                } else if (row.status == 2) {//离线
                    return 'background-color:#f8ac59;';
                }
                else if (row.status == 4) {
                    return 'background-color:#e26969';
                } else if (row.status == 3) {
                    return 'background-color:#8dbeffa1;';
                }
            },
            enableHeaderClickMenu: true,
            enableRowContextMenu: false,
            toolbar: '#tb'
        });

        dg2 = $('#wgcd').datagrid({
            method: "post",
            url: ctx + '/zxjkyj/points/maintian/qyjson',
            fit: true,
            fitColumns: true,
            border: false,
            idField: 'id',
            iconCls: 'm2',
            animate: true,
            rownumbers: true,
            singleSelect: true,
            scrollbarSize: 0,
            striped: true,
            columns: [[
                {field: 'id', title: 'id', hidden: true, width: 100},
                {
                    field: 'm1', title: '企业名称', width: 100,
                    formatter: function (value, row) {
                        var html = value + "<br><span class='fa fa-check btn-primary btn-outline'> 数量" + row.total +
                            "</span>&nbsp;&nbsp;"
                            + "<span class='fa fa-eye btn-warning btn-outline'> 离线" + row.offline + "</span>&nbsp;&nbsp;"
                            + "<span class='fa fa-close btn-danger btn-outline'> 无信息" + row.nodata +
                            "</span>&nbsp;&nbsp;"
                        return html;
                    }
                },
            ]],
            enableHeaderClickMenu: false,
            enableHeaderContextMenu: false,
            enableRowContextMenu: false,
            dataPlain: true,
            onClickRow: function (index, row) {
                dg.datagrid('load', {qyid: row.id});
            }
        });


    });

    timerID = setInterval("refresh();getBjxx()", 60000 * 2);

    //清空
    function reset() {
        $("#searchFrom").form("clear");
        search();
    }

    //查询
    function search() {
        var obj = $("#searchFrom").serializeObject();
        dg.datagrid('load', obj);
    }

    //datagrid刷新
    function refresh() {
        dg.datagrid('reload');
    }


    //显示企业储罐信息页面
    function showqycg(n, qyname) {
        Pqyname = qyname;
        openDialogView(qyname + "实时储量", ctx + '/zxjkyj/cgssjc/view/' + n, "100%", "100%", "");
    }


    //显示企业储罐信息页面
    function view() {
        var row = dg.datagrid('getSelected');
        if (row == null) {
            layer.msg("请选择一行记录！", {time: 1000});
            return;
        }
        showqycg(row.qyid);
    }


    //导出选择企业页面
    function fileexport() {
        if (usertype == '1') {
            window.open('${ctx}/zxjkyj/cgssjc/export?ids=');
        } else {

            layer.open({
                type: 1,
                area: ['400px', '400px'],
                title: '选择企业',
                shift: 5,
                shade: 0,
                content: $('#selectQyPanel'),
                success: function (layero, index) {
                    loadSelectDatagrid();
                },
                btn: ['确定', '关闭'],
                yes: function (index, layero) {
                    var row = $('#qyDatagrid').datagrid('getChecked');

                    var ids = "";
                    for (var i = 0; i < row.length; i++) {
                        if (ids == "") {
                            ids = row[i].id;
                        } else {
                            ids = ids + "," + row[i].id;
                        }
                    }
                    window.open('${ctx}/zxjkyj/cgssjc/export?ids=' + ids);
                },
                cancel: function (index) {
                }

            });

        }
    }


</script>
</body>
</html>