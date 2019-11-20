var dgh1;
var dgh2;
var d;

$(function () {
    dgh1 = $('#bjyj_cgxx_dg').datagrid({
        nowrap: false,
        method: "post",
        url: ctx + '/zxjkyj/alarm/list',
        fit: true,
        queryParams: {status: 0},
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
            {field: 'm1', title: '企业名称', sortable: false, width: 100, align: 'left'},
            {
                field: 'alarmtime', title: '报警时间', sortable: false, width: 50, align: 'center',
                formatter: function (value, row, index) {
                    if (value != null && value != '') {
                        var dt = new Date(value);
                        return dt.format("yyyy-MM-dd hh:mm:ss");
                    } else {
                        return '/';
                    }
                }
            },
            {field: 'value', title: '监测值', sortable: false, width: 50, align: 'left'},
            {field: 'point_low_alarm', title: '低限报警值', sortable: false, width: 50, align: 'left'},
            {field: 'point_low_low_alarm', title: '低低限报警值', sortable: false, width: 50, align: 'left'},
            {field: 'point_high_alarm', title: '高限报警值', sortable: false, width: 50, align: 'left'},
            {field: 'point_high_high_alarm', title: '高高限报警值', sortable: false, width: 50, align: 'left'},
            {field: 'alarmtype', title: '报警类型', sortable: false, width: 60, align: 'center'},
            {
                field: 'status', title: '是否处理', sortable: false, width: 50, align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "已处理";
                    } else {
                        return "未处理";
                    }
                },
                styler: function (value, row, index) {
                    if (value == "1") {
                    } else {
                        return 'background-color:#FFD2D2;';
                    }
                },
            },
            {
                field: 'caozuo', title: '操作', sortable: false, width: 50, align: 'center',
                formatter: function (value, row, index) {
                    return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='upd("
                        + row.id + ")'>报警处理</a>";
                }
            }
        ]],
        onDblClickRow: function (rowdata, rowindex, rowDomElement) {
            view();
        },
        onLoadSuccess: function () {
            $(this).datagrid("autoMergeCells", ['qyname']);
        },
        enableHeaderClickMenu: true,
        enableRowContextMenu: false,
        toolbar: '#bjyj_cgxx_tb'
    });

});


//处理情况查询
function search() {
    var obj = $("#bjyj_cgxx_searchFrom").serializeObject();
    $('#bjyj_cgxx_dg').datagrid('load', obj);
}

//清空
function clearA() {
    $("#bjyj_cgxx_searchFrom").form("clear");
    var obj = $("#bjyj_cgxx_searchFrom").serializeObject();
    $('#bjyj_cgxx_dg').datagrid('load', obj);
}

//学习记录datagrid
$(function () {
    dgh2 = $('#bjyj_cgxx_dg2').datagrid({
        nowrap: false,
        method: "post",
        url: ctx + '/zxjkyj/alarm/list',
        fit: true,
        queryParams: {status: 1},
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
            {field: 'm1', title: '企业名称', sortable: false, width: 100, align: 'left'},
            {
                field: 'alarmtime', title: '报警时间', sortable: false, width: 50, align: 'center',
                formatter: function (value, row, index) {
                    if (value != null && value != '') {
                        var dt = new Date(value);
                        return dt.format("yyyy-MM-dd hh:mm:ss");
                    } else {
                        return '/';
                    }
                }
            },
            {field: 'value', title: '监测值', sortable: false, width: 50, align: 'left'},
            {field: 'point_low_alarm', title: '低限报警值', sortable: false, width: 50, align: 'left'},
            {field: 'point_low_low_alarm', title: '低低限报警值', sortable: false, width: 50, align: 'left'},
            {field: 'point_high_alarm', title: '高限报警值', sortable: false, width: 50, align: 'left'},
            {field: 'point_high_high_alarm', title: '高高限报警值', sortable: false, width: 50, align: 'left'},
            {field: 'alarmtype', title: '报警类型', sortable: false, width: 60, align: 'center'},
            {
                field: 'reason', title: '原因', sortable: false, width: 150, align: 'center'

            }
        ]],
        onDblClickRow: function (rowdata, rowindex, rowDomElement) {
            view2();
        },
        onLoadSuccess: function () {
            $(this).datagrid("autoMergeCells", ['qyname']);
        },
        enableHeaderClickMenu: true,
        enableRowContextMenu: false,
        toolbar: '#bjyj_cgxx_tb2'
    });

});


//学习记录查询
function search2() {
    var obj = $("#bjyj_cgxx_searchFrom2").serializeObject();
    $('#bjyj_cgxx_dg2').datagrid('load', obj);
}

//清空
function clearA2() {
    $("#bjyj_cgxx_searchFrom2").form("clear");
    var obj = $("#bjyj_cgxx_searchFrom2").serializeObject();
    $('#bjyj_cgxx_dg2').datagrid('load', obj);
}

//报警处理
function upd(id) {
    openDialog("报警信息处理", ctx + "/zxjkyj/alarm/deal/" + id, "800px", "250px", "");

}

//查看(未处理)
function view() {
    var row = dgh1.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {time: 1000});
        return;
    }

    openDialogView("查看储罐报警信息", ctx + "/zxjkyj/alarm/view/" + row.id, "800px", "400px", "");

}

//查看(已处理)
function view2() {
    var row = dgh2.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {time: 1000});
        return;
    }

    openDialogView("查看储罐报警信息", ctx + "/zxjkyj/alarm/view/" + row.id, "800px", "400px", "");

}

