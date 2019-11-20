var dg;
var d;
$(function () {

    dg = $('#dg').datagrid(
        {
            method: "post",
            url: ctx + '/aqzf/yqsq/list',
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
                {field: 'number', title: '责改编号', sortable: false, width: 80, align: 'center'},
                {field: 'unitname', title: '公司名称', sortable: false, width: 100},
                {
                    field: 'm1', title: '推迟日期至', sortable: false, width: 70, align: 'center',
                    formatter: function (value) {
                        return new Date(value).format("yyyy-MM-dd");
                    }
                },
                {
                    field: 'operation', title: '操作', sortable: false, width: 70, align: 'center',
                    formatter: function (value, row, index) {
                        var html = "";
                        if (sh) {
                            html += '<a class=\'btn btn-info btn-xs\'' +
                                ' onclick=\'addSh(' + row.id + ')\'>审核</a>';
                        }
                        if (row.m6 == 1) {
                            html += '审核通过';
                        } else if (row.m6 == 0) {
                            html += '审核未通过';
                        } else {
                            html += '未审核';
                        }
                        return html;
                    }
                },
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
    $("#aqzf_jcjh_year").combobox('setValue', $("#aqzf_jcjh_year").combobox('getText'));
    var obj = $("#searchFrom").serializeObject();
    dg.datagrid('load', obj);
}

//添加方案
function addSh(id) {
    openDialog("延期申请审核", ctx + "/aqzf/yqsq/review/" + id, "900px", "400px", "");
}

//重置
function reset() {
    $("#searchFrom").form("clear");
    var obj = $("#searchFrom").serializeObject();
    dg.datagrid('load', obj);
}

//删除
function deljh() {
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


// 弹窗增加
function addjh(u) {
    openDialog("添加检查计划", ctx + "/aqzf/jcjh/create/", "800px", "400px", "");
}


// 弹窗修改
function updjh() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {
            time: 1000
        });
        return;
    }
    if (row.m6) {
        layer.msg("申请已审核，不允许修改");
        return;
    } else {
        openDialog("修改申请", ctx + "/aqzf/yqsq/update/" + row.id, "800px", "400px");
    }

}


// 查看
function viewjh() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {
            time: 1000
        });
        return;
    }
    openDialogView("信息查看", ctx + "/aqzf/yqsq/view/" + row.id, "800px", "400px");
}


