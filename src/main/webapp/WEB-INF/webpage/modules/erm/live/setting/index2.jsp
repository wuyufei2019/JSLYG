<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>直播信息</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<!-- 工具栏 -->
<div id="tb" style="padding:5px;height:auto">
    <form id="searchFrom" style="margin-bottom: 8px;" class="form-inline">

        <div class="form-group">
            <c:if test="${usertype ne '1' and type eq '1'}">
                <input type="text" name="view_qyid" class="easyui-combobox"
                       style="height: 30px;"
                       data-options="valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称 '"/>
            </c:if>
            <c:if test="${usertype ne '1' and type eq '0'}">
                <input type="text" name="view_xzqy" class="easyui-combotree"
                       style="height: 30px;"
                       data-options="valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson',prompt: '网格名称 '"/>
            </c:if>
            <input type="text" name="view_live" class="easyui-combobox"
                   style="height: 30px;"
                   data-options="panelHeight:'auto',data:[{value:'1',text:'正在直播'},{value:'0',text:'未直播'}],prompt: '直播状态 '"/>
            <input type="text" id="view_title" name="view_title" class="easyui-textbox"
                   style="height: 30px;" data-options="prompt: '直播间标题'"/>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i
                    class="fa fa-search"></i> 查询</span>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i
                    class="fa fa-refresh"></i> 全部</span>
        </div>
    </form>

    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
                <shiro:hasPermission name="yjjy:live:add">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()"
                            title="添加"><i class="fa fa-plus"></i> 添加
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="yjjy:live:update">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()"
                            title="修改"><i class="fa fa-file-text-o"></i> 修改
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="yjjy:live:delete">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()"
                            title="删除"><i class="fa fa-trash-o"></i> 删除
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
    var type = '${type}';

    $(function () {
        dg = $('#dg').datagrid({
            method: "post",
            url: ctx + '/yjjy/live/setting/list?view_type='+type,
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
                {
                    field: 'm1', title: '所属', sortable: false, width: 150, align: 'center',
                    formatter: function (value) {
                        if (!value) {
                            return '应急管理局';
                        } else
                            return value;
                    }
                },
                {field: 'title', title: '直播间标题', sortable: false, width: 150, align: 'center'},
                {field: 'tag', title: '标签', sortable: false, width: 150, align: 'center'},
                {field: 'describe', title: '描述', sortable: false, width: 80, align: 'center'},
                {
                    field: 'operation', title: '操作', sortable: false, width: 80, align: 'center',
                    formatter: function (index, row) {
                        return '<a class="btn btn-primary btn-xs" onclick="openLiveUrl(\'' + (row.uuid) +
                            '\')">查看二维码</a>';
                    }
                },
                {
                    field: 'live', title: '状态', sortable: false, width: 80, align: 'center',
                    formatter: function (value, row) {
                        if (value == 1) {
                            return '<a>正在直播</a>';
                        } else {
                            return '<a>未直播</a>';
                        }
                    }
                },


            ]],
            onDblClickRow: function (rowdata, rowindex, rowDomElement) {
            },
            onLoadSuccess: function () {
                $(this).datagrid("autoMergeCells", ['m1']);
            },
            checkOnSelect: false,
            selectOnCheck: false,
            toolbar: '#tb'
        });

    });

    function openLiveUrl(uuid) {
        //打开图片查看对话框
        $.ajax({
            type: 'get',
            async: false,
            url: "${ctx}/yjjy/live/erm?uuid=" + uuid,
            success: function (data) {
                layer.open({
                    type: 1,
                    shift: 0,
                    area: ['300px', '310px'],
                    title: false,
                    closeBtn: 0,
                    shadeClose: true,
                    content: '<img id="o" border=0 src=${ctx}' + data + '>'
                });
            }
        });

    }

    //弹窗增加
    function add(u) {
        openDialog("添加直播间信息", ctx + "/yjjy/live/setting/create/", "800px", "350px", "");
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
                url: ctx + "/yjjy/live/setting/delete/" + ids,
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

        openDialog("修改直播间信息", ctx + "/yjjy/live/setting/update/" + row.id, "800px", "350px", "");

    }

    //查看
    function view() {
        var row = dg.datagrid('getSelected');
        if (row == null) {
            layer.msg("请选择一行记录！", {time: 1000});
            return;
        }
        openDialogView("查看直播间信息", ctx + "/yjjy/live/setting/view/" + row.id, "800px", "400px", "");

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