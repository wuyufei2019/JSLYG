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
            <div id="wgcd2">
            </div>
        </div>
        <div data-options="region:'center'" style="width:65%;height:100%">
            <div id="tb2" style="padding:5px;height:auto">

                <form id="searchFrom2" action="" style="margin-bottom: 8px;" class="form-inline">

                    <div class="form-group">
                        <%-- <input type="text" id="qyid" name="qyid" class="easyui-combobox" style="height: 30px;"
                                data-options="editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>--%>
                        <input type="text" id="starttime" name="starttime" class="easyui-datetimebox"
                               style="height: 30px;"
                               data-options="prompt: '开始时间' "/>
                        <input type="text" id="endtime" name="endtime" class="easyui-datetimebox" style="height: 30px;"
                               data-options="prompt: '结束时间' "/>
                        <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i
                                class="fa fa-search"></i> 查询</span>
                        <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i
                                class="fa fa-refresh"></i> 全部</span>
                    </div>
                </form>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="pull-left">
                            <button class="btn btn-warning btn-sm" onclick="fileexport()"><i
                                    class="fa fa-external-link"></i> 导出
                            </button>
                            <button class="btn btn-primary btn-sm" onclick="refresh2()"><i
                                    class="glyphicon glyphicon-repeat"></i> 刷新
                            </button>
                            <label id="shuzi" style="font-size: 15px;color:#FF00FF">默认统计本日数据</label>
                        </div>
                    </div>
                </div>
            </div>
            <table id="dg2"></table>
        </div>
    </div>
</div>
<script type="text/javascript">
    var usertype = '${usertype}';
    var dg2;
    var dg1;
    var Pqyname;

    $(function () {
        dg2 = $('#dg2').datagrid({
            nowrap: false,
            method: "post",
            url: ctx + '/zxjkyj/points/value/hislist',
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
                {field: 'target_name', title: '指标名称', sortable: false, width: 100, align: 'center'},
                {field: 'target_type', title: '检测指标', sortable: false, width: 100, align: 'center'},
                {field: 'unit', title: '单位', sortable: false, width: 100, align: 'center'},
                {field: 'avgvalue', title: '平均值', sortable: false, width: 100, align: 'center'},
                {field: 'maxvalue', title: '最大值', sortable: false, width: 100, align: 'center'},
                {field: 'minvalue', title: '最小值', sortable: false, width: 100, align: 'center'},
            ]],
            onDblClickRow: function (rowdata, row, rowDomElement) {
                openDialogView("查看统计信息", '${ctx}/zxjkyj/points/value/statistics?pointId=' + row.id
                    + "&starttime=" + $("#starttime").datetimebox("getValue")
                    + "&endtime=" + $("#endtime").datetimebox("getValue"), "100%", "100%");
            },
            onLoadSuccess: function () {
                $(this).datagrid("autoMergeCells", ['qyname']);
            },
            onLoadError: function () {
                layer.open({title: '提示', offset: 'rb', content: '数据加载中，请耐心等待。', shade: 0, time: 2000});
            },
            rowStyler: function (index, row) {
                if (row.m10 != null && row.m10 > 0 && row.m8 > row.m10) {
                    return 'background-color:rgb(232, 190, 101);';
                }
            },
            enableHeaderClickMenu: true,
            enableRowContextMenu: false,
            toolbar: '#tb2'
        });

        dg = $('#wgcd2').datagrid({
            method: "post",
            url: ctx + '/zxjkyj/points/maintian/qyjson',
            fit: true,
            fitColumns: true,
            border: false,
            idField: 'id',
            animate: true,
            rownumbers: true,
            singleSelect: true,
            scrollbarSize: 0,
            striped: true,
            columns: [[
                {field: 'id', title: 'id', hidden: true, width: 100},
                {field: 'm1', title: '企业名称', width: 100}
            ]],
            enableHeaderClickMenu: false,
            enableHeaderContextMenu: false,
            enableRowContextMenu: false,
            dataPlain: true,
            onClickRow: function (index, row) {
                dg2.datagrid('load', {qyid: row.id});
            }
        });

    });


    //清空
    function reset() {
        $("#searchFrom2").form("clear");
        search();
    }

    //查询
    function search() {
        var obj = $("#searchFrom2").serializeObject();
        dg2.datagrid('load', obj);
    }

    //datagrid刷新
    function refresh2() {
        dg2.datagrid('reload');
    }


    //显示企业储罐信息页面
    function showqycg(n, qyname) {
        Pqyname = qyname;
        openDialogView(qyname + "实时储量", ctx + '/zxjkyj/cgssjc/view/' + n, "100%", "100%", "");
    }


    //显示企业储罐信息页面
    function view() {
        var row = dg2.datagrid('getSelected');
        if (row == null) {
            layer.msg("请选择一行记录！", {time: 1000});
            return;
        }
        showqycg(row.qyid);
    }



</script>
</body>
</html>