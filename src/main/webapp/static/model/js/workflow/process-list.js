var dg;
var d;
$(function () {
    dg = $('#dg').datagrid({
        method: "post",
        url: ctx + '/workflow/list',
        fit: true,
        fitColumns: true,
        border: false,
        idField: 'id',
        striped: true,
        pagination: false,
        rownumbers: true,
        nowrap: false,
        scrollbarSize: 5,
        singleSelect: true,
        striped: true,
        columns: [[
            {field: 'pid', title: '流程定义id', checkbox: true, width: 50, align: 'center'},
            {field: 'deploymentId', title: '部署id', sortable: false, width: 50, align: 'center'},
            {field: 'name', title: '名称', sortable: false, width: 50, align: 'center'},
            {field: 'version', title: '版本号', sortable: false, width: 20, align: 'center'},
            {
                field: 'resourceName', title: 'xml', sortable: false, width: 20, align: 'center',
                formatter: function (vlaue, row) {
                    return '<a target="_blank" href=' + ctx
                        + '/workflow/resource/read?processDefinitionId=' + row.pid + '&resourceType=xml\'>' + vlaue + '</a>';
                }
            },
            {
                field: 'diagramResourceName', title: '图片', sortable: false, width: 20, align: 'center',
                formatter: function (vlaue, row) {
                    return '<a target="_blank" href=' + ctx
                        + '/workflow/resource/read?processDefinitionId=' + row.pid + '&resourceType=image\'>' + vlaue + '</a>';
                }
            },
            {
                field: 'suspended', title: '是否挂起', sortable: false, width: 20, align: 'center',
                formatter: function (value) {
                    if (value)
                        return value + '<a href="' + ctx + 'processdefinition/update/active/${process.id}">激活</a>'
                    else
                        return value + '<a href="' + ctx + 'processdefinition/update/active/${process.id}">挂起</a>'
                }
            },
            {
                field: 'operation',
                title: '操作',
                sortable: false,
                width: 50,
                align: 'center',
                formatter: function (value, row) {
                    console.log(row)
                    return '<a href="'+ctx+'/workflow/process/delete?deploymentId='+row.deploymentId+'">删除</a>'+
                        '<a href="'+ctx+'/workflow/process/convert-to-model/'+row.pid+'">转换为Model</a>';

                }
            }

        ]],
        onDblClickRow: function (rowdata, rowindex, rowDomElement) {
            upd();
        },
        checkOnSelect: false,
        selectOnCheck: true,
        onLoadSuccess: function () {
        },
        toolbar: '#dg_tb'
    });
});

function deploy(id) {
    $.post(ctx + '/workflow/model/deploy/' + id, function (data) {
        if (data == 'success') {
            layer.msg("部署成功！");
        } else {
            layer.msg("部署失败！");
        }
    });
}

// 弹窗增加
function add() {
    openDialog("添加模型", ctx + "/workflow/model/create/", "800px", "300px", "");
}

// 删除
function del() {
    var row = dg.datagrid('getChecked');
    if (row == null || row == '') {
        layer.msg("请至少勾选一行记录！", {time: 1000});
        return;
    }
    top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title: '提示'}, function (index) {
        $.ajax({
            type: 'get',
            url: ctx + "/workflow/model/delete/" + row[0].id,
            success: function (data) {
                dg.datagrid('reload');
                dg.datagrid('clearChecked');
            }
        });
    });

}


function reset() {
    $("#searchFrom").form("clear");
    var obj = $("#searchFrom").serializeObject();
    dg.datagrid('load', obj);
    // if(btflg=='2') $("#filter_EQS_departmen").hide();
}

