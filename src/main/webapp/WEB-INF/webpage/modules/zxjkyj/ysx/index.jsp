<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>会议信息</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<!-- 工具栏 -->
<div id="tb" style="padding:5px;height:auto">
    <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">

        <div class="form-group">
            <input type="text" id="strSubject" name="filter_strSubject" class="easyui-textbox"
                   style="height: 30px;"
                   data-options="prompt: '输入主题',validType:['FUN[validateValue,\'暂不支持主题和时间同时查询\']']"/>

            <input id="strBeginTime" name="filter_strBeginTime" type="text" class="easyui-datebox" style="height: 30px;"
                   data-options="prompt: '开始时间'"/>
            <input id="strEndTime" name="filter_strEndTime" type="text" class="easyui-datebox" style="height: 30px;"
                   data-options="prompt: '结束时间'"/>
            <input name="filter_lShowType" type="text" class="easyui-combobox" style="height: 30px;"
                   data-options="prompt: '显示类型',panelHeight:'auto',
                                data:[{text:'全部',value:0},{text:'未召开',value:1},
                                {text:'进行中',value:2},{text:'已结束',value:3}]"/>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i
                    class="fa fa-search"></i> 查询</span>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i
                    class="fa fa-refresh"></i> 全部</span>
        </div>
    </form>

    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
                <shiro:hasPermission name="api:yxs:add">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()"
                            title="添加"><i class="fa fa-plus"></i> 添加
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="api:yxs:update">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()"
                            title="修改"><i class="fa fa-file-text-o"></i> 修改
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="api:yxs:control">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="control()"
                            title="修改"><i class="fa fa-cogs"></i> 会议控制
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="api:yxs:view">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()"
                            title="查看"><i class="fa fa-search-plus"></i> 查看
                    </button>
                </shiro:hasPermission>
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
    var usertype = '${usertype}';

    function validateValue(value) {
        var st = $("#strEndTime").datetimebox("getValue");
        var et = $("#strBeginTime").datetimebox("getValue");
        return !(st || et);
    }

    $(function () {
        $("#strEndTime").datetimebox({
            onChange: function () {
                var subject = $("#strSubject").textbox("getValue");
                if (subject) {
                    $(this).datetimebox("setValue", '');
                    layer.msg("暂不支持主题和时间同时查询！");
                    return false;
                }

            }
        });
        $("#strBeginTime").datetimebox({
            onChange: function () {
                var subject = $("#strSubject").textbox("getValue");
                if (subject) {
                    $(this).datetimebox("setValue", '');
                    layer.msg("暂不支持主题和时间同时查询！");
                    return false;
                }
            }
        });

        dg = $('#dg').datagrid({
            nowrap: false,
            method: "post",
            url: '${ctx}/zxjkyj/ysx/list',
            fit: true,
            fitColumns: true,
            selectOnCheck: false,
            nowrap: false,
            idField: 'strConfID',
            striped: true,
            scrollbarSize: 0,
            pagination: true,
            rownumbers: true,
            pageNumber: 1,
            pageSize: 50,
            pageList: [20, 50, 100, 150, 200, 250],
            singleSelect: true,
            columns: [[
                {field: 'strConfID', title: 'id', checkbox: true, width: 50, align: 'center'},
                {
                    field: 'strSubject', title: '会议主题', sortable: false, width: 150, align: 'center',
                    formatter: function (value) {
                        return value.value;
                    }
                },
                {
                    field: 'strBeginTime', title: '开始时间', sortable: false, width: 100, align: 'center',
                    formatter: function (value) {
                        var datetime = new Date(value);
                        return datetime.format('yyyy-MM-dd hh:mm:ss');
                    }
                },
                {
                    field: 'strEndTime', title: '结束时间', sortable: false, width: 100, align: 'center',
                    formatter: function (value) {
                        var datetime = new Date(value);
                        return datetime.format('yyyy-MM-dd hh:mm:ss');
                    }
                },
                {
                    field: 'confState', title: '会议状态', sortable: false, width: 100, align: 'center',
                    formatter: function (value, row) {
                        if (value == 0) {
                            var result = "预约成功";
                            if (usertype == 0) {
                                result += '<a class="btn btn-primary btn-xs" onclick="cancelConf(\'' +
                                    row.strConfID + '\')">取消会议</a>';
                            }
                            return result;
                        }

                        else if (value == 1) return '会议已创建';
                        else if (value == 2) return "<a class='btn btn-primary btn-xs' onclick='attend()'>加入会议</a>";
                        else if (value == 3) return '会议已经结束';
                    }
                },
            ]],
            onDblClickRow: function () {
                view();
            },
            onLoadSuccess: function () {
            },
            onLoadError: function () {
                layer.msg("接口请求超时，请稍后刷新");
            },
            rowStyler: function (index, row) {
                if (row.m10 != null && row.m10 > 0 && row.m8 > row.m10) {
                    return 'background-color:rgb(232, 190, 101);';
                }
            },
            enableHeaderClickMenu: true,
            enableRowContextMenu: false,
            toolbar: '#tb'
        });

    });


    function add() {
        openDialog("添加会议", ctx + '/zxjkyj/ysx/create', "800px", "450px", "");
    }

    function cancelConf(strConfID) {
        $.post(ctx + '/zxjkyj/ysx/cancelConf/' + strConfID, function (data) {

            if (data == "success") {
                refresh();
                layer.msg("取消成功！");
            }
            else
                layer.msg(data);
        });
    }

    function control() {
        var row = dg.datagrid('getSelected');
        if (row == null) {
            layer.msg("请选择一行记录！", {time: 1000});
            return;
        }
        if (row.confState == 2)
            openDialogView("会议控制", ctx + '/zxjkyj/ysx/control/' + row.strConfID, "100%", "100%", "");
        else
            layer.msg("非进行中会议！，不能控制");
    }

    function upd() {
        var row = dg.datagrid('getSelected');
        if (row == null) {
            layer.msg("请选择一行记录！", {time: 1000});
            return;
        }
        if (row.confState == 0)
            openDialog("修改会议", ctx + '/zxjkyj/ysx/update/' + row.strConfID, "800px", "450px", "");
        else
            layer.msg("非预约会议，不允许修改");
    }

    //显示企业有毒气体信息页面
    function view() {
        var row = dg.datagrid('getSelected');
        if (row == null) {
            layer.msg("请选择一行记录！", {time: 1000});
            return;
        }
        openDialogView("查看会议信息", '${ctx}/zxjkyj/ysx/view/' + row.strConfID, "800px", "450px", "");
    }

    //清空
    function reset() {
        $("#searchFrom").form("clear");
        search();
    }

    //查询
    function search() {
        var st = $("#strEndTime").datetimebox("getValue");
        var et = $("#strBeginTime").datetimebox("getValue");
        if ((st && !et) || (!st && et)) {
            layer.msg("时间起止必须同时填写！");
            return false;
        }
        var obj = $("#searchFrom").serializeObject();
        dg.datagrid('load', obj);
    }

    //datagrid刷新
    function refresh() {
        dg.datagrid('reload');
    }


</script>
</body>
</html>