var dg;
$(function () {
    dg = $('#dg').datagrid({
        method: "post",
        url: ctx + '/erm/train/check-result/list',
        queryParams: {filter_EQL_planId: planId},
        fit: true,
        fitColumns: true,
        border: false,
        idField: 'id',
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
            {field: 'content', title: '考核内容', width: 150, align: 'center'},
            {field: 'standard', title: '考核标准', width: 150, align: 'center'},
            {field: 'lister', title: '制表人', width: 50, align: 'center'},
            {field: 'reviewer', title: '审核人', width: 50}
        ]],
        onDblClickRow: function (rowdata, rowindex, rowDomElement) {
            view();
        }
        ,
        checkOnSelect: false,
        selectOnCheck:
            false,
        toolbar:
            '#tb'
    })
    ;
});

//弹窗增加
function add(u) {
    openDialog("添加训练周计划考核信息", ctx + "/erm/train/check-result/create/"+planId, "100%", "100%", "");
}

function viewResult(u) {
    openDialog("训练周计划考核考核信息", ctx + "/erm/train/check-result/index/" + u, "100%", "100%", "");
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
            ids = row[i].id;
        } else {
            ids = ids + "," + row[i].id;
        }
    }

    top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title: '提示'}, function (index) {
        $.ajax({
            type: 'get',
            url: ctx + "/erm/train/check-result/delete/" + ids,
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

    openDialog("修改训练周计划考核信息", ctx + "/erm/train/check-result/update/" + row.id, "100%", "100%", "");

}


//查看
function view() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {time: 1000});
        return;
    }

    openDialogView("查看训练周计划考核信息", ctx + "/erm/train/check-result/view/" + row.id, "100%", "100%", "");

}

//创建查询对象并查询
function search() {
    var obj = $("#searchFrom").serializeObject();
    dg.datagrid('load', obj);
}

function reset() {
    $("#searchFrom").form("clear");
    var obj = $("#searchFrom").serializeObject();
    dg.datagrid('load', obj);
    //if(btflg=='2') $("#filter_EQS_departmen").hide();

}