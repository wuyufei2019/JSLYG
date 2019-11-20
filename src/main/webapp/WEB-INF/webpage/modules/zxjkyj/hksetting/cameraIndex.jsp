<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>海康设备设置</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<!-- 工具栏 -->
<div id="tb" style="padding:5px;height:auto">
    <%-- <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">

         <div class="form-group">
             <input type="text" name="filter_EQL_qyid" class="easyui-combobox"
                    style="height: 30px;"
                    data-options="valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称 '"/>
             <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i
                     class="fa fa-search"></i> 查询</span>
             <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i
                     class="fa fa-refresh"></i> 全部</span>
         </div>
     </form>--%>

    <div class="row"><%--
        <div class="col-sm-12">
            <div class="pull-left">
                <shiro:hasPermission name="api:hksetting:add">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()"
                            title="添加"><i class="fa fa-plus"></i> 添加
                    </button>
                </shiro:hasPermission>

                <shiro:hasPermission name="api:hksetting:add">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()"
                            title="修改"><i class="fa fa-plus"></i> 修改
                    </button>
                </shiro:hasPermission>

                <shiro:hasPermission name="api:hksetting:delete">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()"
                            title="删除"><i class="fa fa-trash-o"></i> 删除
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="api:hksetting:update">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="updName()"
                            title="修改"><i class="fa fa-file-text-o"></i> 修改设备名称
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="api:hksetting:add">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="init()"
                            title="添加"><i class="fa fa-plus"></i> 初始化NVR设备
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="api:hksetting:add">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left"
                            onclick="initCamera()"
                            title="添加"><i class="fa fa-plus"></i> 初始化摄像头设备
                    </button>
                </shiro:hasPermission>
                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()"
                        title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新
                </button>

            </div>
        </div>
    --%></div>

</div>


<table id="dg"></table>

<script type="text/javascript">
    var dg;
    var usertype = '${usertype}';
    var deviceSerial = '${deviceSerial}';

    $(function () {

        dg = $('#dg').datagrid({
            nowrap: false,
            method: "post",
            url: '${ctx}/zxjkyj/hkvision/device-qy/camera/list',
            queryParams: {deviceSerial: deviceSerial},
            fit: true,
            fitColumns: true,
            selectOnCheck: false,
            nowrap: false,
            idField: 'deviceSerial',
            striped: true,
            scrollbarSize: 0,
            pagination: true,
            rownumbers: true,
            pageNumber: 1,
            pageSize: 50,
            pageList: [20, 50, 100, 150, 200, 250],
            singleSelect: true,
            columns: [[
                {field: 'id', title: 'id', checkbox: true, width: 50, align: 'center'},
                {field: 'deviceName', title: '设备名称', sortable: false, width: 100, align: 'center'},
                {field: 'deviceSerial', title: '设备序列号', sortable: false, width: 100, align: 'center'},
                {field: 'channelNo', title: '通道号', sortable: false, width: 100, align: 'center'},
                {field: 'channelName', title: '通道名称', sortable: false, width: 200, align: 'center'},
                {
                    field: 'status', title: '设备转态', sortable: false, width: 50, align: 'center'
                    , formatter: function (value) {
                        if(value == 1 ){
                            return "在线";
                        }else if(value == -1){
                            return '未上报'
                        }else
                            return "离线";
                    },
                    styler: function (value, row, index) {
                        if (value == 1) {
                            return 'background-color:rgb(255, 228, 141);';
                        } else {
                            return 'background-color:rgb(249, 156, 140);';
                        }
                    },
                },
                {
                    field: 'open', title: '直播开通状态', sortable: false, width: 200, align: 'center'
                    , formatter: function (value, row) {
                        var html = "";
                        if (value) {
                            html = "<a class='btn btn-primary btn-xs' onclick='closeLive(\"" + row.deviceSerial + "\","
                                + row.channelNo + ")'>关闭直播</a>";
                        } else {
                            html = "<a class='btn btn-success btn-xs' onclick='openLive(\"" + row.deviceSerial + "\","
                                + row.channelNo + ",\"" + row.channelName + "\")'>开通直播</a>";
                        }
                        return html;
                    }
                }
            ]],
            onDblClickRow: function () {
                view();
            },
            onLoadSuccess: function () {
                $(this).datagrid("autoMergeCells", ['deviceName', 'deviceSerial']);
            },
            onLoadError: function () {
                layer.msg("接口请求超时，请稍后刷新");
            },
            enableHeaderClickMenu: true,
            enableRowContextMenu: false,
            toolbar: '#tb'
        });

    });


    function add() {
        openDialog("添加设备信息", ctx + '/zxjkyj/hkvision/device-qy/create', "700px", "300px", "");
    }

    function openLive(deviceSerial, channelNo,channelName) {
        $.post(ctx + '/zxjkyj/hkvision/device-qy/open',
            {deviceSerial: deviceSerial, channelNo: channelNo, channelName: channelName},
            function (data) {
                layer.msg(data);
                dg.datagrid('reload');
            });

        /*
        layer.open({
            type: 2,
            area: ["80%", "60%"],
            title: qyname+'绑定摄像头信息',
            maxmin: false,
            content:  ctx + '/zxjkyj/hkvision/device-qy/bind/'+deviceSerial,
            btn: ['绑定', '关闭'],
            yes: function(index, layero){
                var iframeWin = layero.find('iframe')[0];
                var ids=iframeWin.contentWindow.getDeviceSerials();
                if(ids==""){
                    layer.msg("未绑定任何摄像头");
                    return;
                }
                var index2=layer.load();
                $.ajax({
                    type:"post",
                    url:ctx+"zxjkyj/hkvision/device-qy/bind/"+ids ,
                    data:{nvrDeviceSerial:deviceSerial},
                    success:function(data){
                        if(data=="success"){
                            parent.layer.open({
                                icon : 1,
                                title : '提示',
                                offset : 'rb',
                                content : '保存成功！',
                                shade : 0,
                                time : 2000
                            });}

                        else{
                            parent.layer.open({
                                icon : 2,
                                title : '提示',
                                offset : 'rb',
                                content : '保存失败！',
                                shade : 0,
                                time : 2000
                            });
                        }
                        layer.close(index);//关闭对话框。
                        layer.close(index2);//关闭load层

                    }
                });
            },
            cancel: function(index){}
        });*/
    }

    function closeLive(deviceSerial, channelNo) {
        $.post(ctx + '/zxjkyj/hkvision/device-qy/close',
            {deviceSerial: deviceSerial, channelNo: channelNo},
            function (data) {
                layer.msg(data);
                dg.datagrid('reload');
            });
    }

    function upd() {
        var row = dg.datagrid('getSelected');
        if (row == null || row == '') {
            layer.msg("请至少勾选一行记录！", {time: 1000});
            return;
        }
        openDialog("修改设备信息", ctx + '/zxjkyj/hkvision/device-qy/update/' + row.id, "700px", "300px", "");
    }

    function init() {
        top.layer.confirm('确定要初始化设备吗？', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                type: 'get',
                url: ctx + "/zxjkyj/hkvision/device-qy/init",
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

    function initCamera() {
        top.layer.confirm('确定要初始化设备吗？', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                type: 'get',
                url: ctx + "/zxjkyj/hkvision/camera/init",
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


    function updName() {
        var row = dg.datagrid('getSelected');
        if (row == null || row == '') {
            layer.msg("请至少勾选一行记录！", {time: 1000});
            return;
        }
        layer.prompt({
            title: '请输入设备名称'
        }, function (value, index) {
            $.ajax({
                type: 'POST',
                contentType: 'application/json;charset=utf-8',
                url: ctx + "/zxjkyj/hkvision/device-qy/updateName",
                dataType: 'json',
                data: JSON.stringify({deviceSerial: row.deviceSerial, deviceName: value}),
                success: function (data) {
                    if (data == "success") {
                        parent.layer.msg("修改成功！");
                    } else {
                        parent.layer.msg("修改失败！");
                    }

                },
                complete: function (data) {
                    layer.close(index);
                    reset();
                }
            });


        });
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
            console.log(row[i]);
            if (ids == "") {
                ids = row[i].id;
            } else {
                ids = ids + "," + row[i].id;
            }
        }

        top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                type: 'get',
                url: ctx + "/zxjkyj/hkvision/device-qy/delete/" + ids,
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


    //清空
    function reset() {
        $("#searchFrom").form("clear");
        search();
    }

    //查询
    function search() {
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