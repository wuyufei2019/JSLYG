<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>储罐监测维护数据信息</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<!-- 工具栏 -->
<div id="tb" style="padding:5px;height:auto">
    <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">

        <div class="form-group">
            <input type="text" id="filter_equipcode" name="filter_equipcode" class="easyui-textbox"
                   style="height: 30px;" data-options="prompt: '设备编码'"/>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i
                    class="fa fa-search"></i> 查询</span>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i
                    class="fa fa-refresh"></i> 全部</span>
        </div>
    </form>

    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
                <shiro:hasPermission name="zxjkyj:pointsetting:add">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()"
                            title="添加"><i class="fa fa-plus"></i> 添加
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="zxjkyj:pointsetting:update">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()"
                            title="修改"><i class="fa fa-file-text-o"></i> 修改
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="zxjkyj:pointsetting:delete">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()"
                            title="删除"><i class="fa fa-trash-o"></i> 删除
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="zxjkyj:pointsetting:view">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()"
                            title="查看"><i class="fa fa-search-plus"></i> 查看
                    </button>
                </shiro:hasPermission>
                <%--<shiro:hasPermission name="zxjkyj:pointsetting:export">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="zxjkyj:pointsetting:exin">
                    <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/zxjkyj/points/maintian/exinjump','${ctx}/zxjkyj/points/maintian/exin','${ctx}/static/templates/储罐监测维护数据信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
                </shiro:hasPermission>--%>
                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()"
                        title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新
                </button>

            </div>
        </div>
    </div>

</div>


<table id="dg"></table>

<script type="text/javascript">
    var dg;
    var d;
    $(function () {
        dg = $('#dg').datagrid({
            method: "post",
            url: ctx + '/zxjkyj/points/maintian/list',
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
                {field: 'id', title: 'id', checkbox: true, width: 50, align: 'center'},
                {field: 'targetCode', title: '设备编码', sortable: false, width: 150, align: 'center'},
                {field: 'targetName', title: '指标名称', sortable: true, width: 120, align: 'center'},
                {field: 'targetType', title: '监测类型', sortable: true, width: 80, align: 'center'},
                {field: 'point', title: '监测点位', sortable: true, width: 100, align: 'center'},
                {field: 'unit', title: '监测点位单位', sortable: true, width: 80, align: 'center'},
                {field: 'thresholdUp', title: '阈值上限', sortable: false, width: 80, align: 'center'},
                {field: 'thresholdUpplus', title: '阈值上上限', sortable: true, width: 80, align: 'center'},
                {field: 'thresholdDown', title: '阈值下限', sortable: false, width: 80, align: 'center'},
                {field: 'thresholdDownplus', title: '阈值下下限', sortable: false, width: 80, align: 'center'},
                {
                    field: 'value', title: '当前值', sortable: false, width: 80, align: 'center',
                    formatter: function (value) {
                        if (!value)
                            return '无数据';
                        else return value;
                    }
                },
                {
                    field: 'collectTime', title: '收集时间', sortable: false, width: 80, align: 'center',
                    formatter: function (value) {
                        if (value)
                            return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                    }
                },
                {
                    field: 'noreport', title: '是否上报', sortable: false, width: 80, align: 'center',
                    formatter: function (value, row) {
                        var html = '<input disabled  class="easyui-switchbutton report"'
                            + (value == 1 ? '' : 'checked') + ' >';
                        return html;
                    }
                },
                {
                    field: 'operation', title: '上报按钮', sortable: false, width: 80, align: 'center',
                    formatter: function (value, row) {

                        var html = '<a class="btn btn-warning btn-xs" onclick="dealReport(' +
                            row.noreport + ',' + row.id + ')"> 改变 </a>';
                        return html;
                    }
                }
            ]],
            onDblClickRow: function (rowdata, rowindex, rowDomElement) {
                view();
            },
            onLoadSuccess: function () {
                $(this).datagrid('autoMergeCells', ['equip_code']);
                $.parser.parse();
            },
            checkOnSelect: false,
            selectOnCheck: false,
            toolbar: '#tb'
        });

    });

    //弹窗增加
    function add(u) {
        openDialog("添加储罐监测维护数据信息", ctx + "/zxjkyj/points/maintian/create/", "800px", "400px", "");
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
                url: ctx + "/zxjkyj/points/maintian/delete/" + ids,
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

    function dealReport(value, id) {

        var tip = '确定后该点位不会上报！';
        if (value == 1) {
            tip = '确定后该点位将会上报！';
        }
        value = (value == 1 ? null : 1);
        top.layer.confirm(tip, {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                type: 'post',
                url: ctx + "/zxjkyj/points/maintian/dealReport?id=" + id + "&report=" + value,
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

        openDialog("修改储罐监测维护数据信息", ctx + "/zxjkyj/points/maintian/update/" + row.id, "800px", "400px", "");

    }

    //查看
    function view() {
        var row = dg.datagrid('getSelected');
        if (row == null) {
            layer.msg("请选择一行记录！", {time: 1000});
            return;
        }
        openDialogView("查看储罐监测维护数据信息", ctx + "/zxjkyj/points/maintian/view/" + row.id, "800px", "400px", "");

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
    }
</script>
</body>
</html>