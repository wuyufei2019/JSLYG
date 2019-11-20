var dg;
var d;
$(function () {

    dg = $('#dg').datagrid(
        {
            method: "post",
            url: ctx + '/aqzf/jgzc/list',
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
                {field: 'id', title: 'id', hidden: 'true', width: 50, align: 'center'},
                {field: 'qyname', title: '公司名称', sortable: false, width: 100},
                {field: 'number', title: '检查记录编号', sortable: false, width: 80, align: 'center'},
            ]],
            onDblClickRow: function (rowdata, rowindex, rowDomElement) {
                view();
            },
            onLoadSuccess: function (rowdata, rowindex, rowDomElement) {
                $(this).datagrid("autoMergeCells", ['m1']);
            },
            checkOnSelect: false,
            selectOnCheck: false,
            toolbar: '#tb'
        });

});


//创建查询对象并查询
function search() {
    var obj = $("#searchFrom").serializeObject();
    dg.datagrid('load', obj);
}

//弹窗修改
function upd() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {
            time: 1000
        });
        return;
    }
    openDialog("修改监管整饬信息", ctx + "/aqzf/jgzc/update/" + row.id, "800px", "400px", "");
}

//重置
function reset() {
    $("#searchFrom").form("clear");
    var obj = $("#searchFrom").serializeObject();
    dg.datagrid('load', obj);
}

//删除
function del() {
    var row = dg.datagrid('getSelected');
    if (row == null || row == '') {
        layer.msg("请选择一行记录！", {time: 1000});
        return;
    }
    if (row.m6) {
        layer.msg("申请已审核，不允许删除");
        return;
    } else {
        var ids = row.id;
        top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                type: 'get',
                url: ctx + "/aqzf/yqsq/delete/" + ids,
                success: function (data) {
                    layer.alert(data, {icon: 6, title: '提示', offset: 'rb', shade: 0, time: 2000});
                    top.layer.close(index);
                    dg.datagrid('reload');
                    dg.datagrid('clearChecked');
                    dg.datagrid('clearSelections');
                }
            });
        });
    }

}


// 查看
function view() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {
            time: 1000
        });
        return;
    }
    openDialogView("信息查看", ctx + "/aqzf/jgzc/view/" + row.id, "90%", "70%");
}

