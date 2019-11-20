var dg;
var d;
$(function () {
    dg = $('#ysx_sbsz_dg').datagrid({
        method: "get",
        url: ctx + '/ysx/sbsz/ajlist',
        fit: true,
        fitColumns: true,
        border: false,
        idField: 'ID',
        striped: true,
        pagination: true,
        rownumbers: true,
        nowrap: false,
        pageNumber: 1,
        pageSize: 50,
        pageList: [50, 100, 150, 200, 250],
        scrollbarSize: 5,
        singleSelect: true,
        striped: true,
        columns: [[
            {field: 'ID', title: 'id', checkbox: true, width: 50, align: 'center'},
            {field: 'accName', title: '账户名称', sortable: false, width: 100, align: 'left'},
            {field: 'userId', title: '设备名称', sortable: false, width: 80},
            {field: 'passwd', title: '设备密码', sortable: false, width: 80},
            {field: 'softTerminal', title: '设备类别', sortable: false, width: 80,
            formatter:function (value) {
                return value?"软终端":"硬终端";
            }}
        ]],
        onDblClickRow: function () {
            view();
        },
        checkOnSelect: false,
        selectOnCheck: false,
        toolbar: '#ysx_sbsz_tb'
    });

});

//弹窗增加
function add(u) {
    openDialog("添加云视讯设备信息", ctx + "/ysx/sbsz/create/", "800px", "250px", "");
}

//删除
function del() {
    var row = dg.datagrid('getChecked');
    if (row == null || row == '') {
        layer.msg("请至少勾选一行记录！", {time: 1000});
        return;
    }

    var ids = "";
    for (var i = 0; i < row.length; i++) {
        if (ids == "") {
            ids = row[i].ID;
        } else {
            ids = ids + "," + row[i].ID;
        }
    }

    top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title: '提示'}, function (index) {
        $.ajax({
            type: 'get',
            url: ctx + "/ysx/sbsz/delete/" + ids,
            success: function (data) {
                layer.alert(data, {offset: 'rb', shade: 0, time: 2000});
                top.layer.close(index);
                dg.datagrid('reload');
                dg.datagrid('clearChecked');
                dg.datagrid('clearSelections');
            }
        });
    });

}

//弹窗修改
function upd() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {time: 1000});
        return;
    }
    openDialog("修改云视讯设备信息", ctx + "/ysx/sbsz/update/" + row.ID, "800px", "300px", "");
}

//查看
function view() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {time: 1000});
        return;
    }

    openDialogView("查看云视讯设备信息", ctx + "/ysx/sbsz/view/" + row.ID, "800px", "300px", "");

}

//创建查询对象并查询
function search() {
    $('#ysx_bsz_name').combobox('setValue', $('#ysx_bsz_name').combobox('getText'));
    var obj = $("#searchFrom").serializeObject();
    dg.datagrid('load', obj);
}

function reset() {
    $("#searchFrom").form("clear");
    var obj = $("#searchFrom").serializeObject();
    dg.datagrid('load', obj);
}