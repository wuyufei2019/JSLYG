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

    <div class="row"></div>

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
            url: '${ctx}/zxjkyj/hkvision/device-qy/live/list',
            queryParams: {filter_EQS_deviceSerial: deviceSerial},
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
                {field: 'deviceSerial', title: '设备序列号', sortable: false, width: 50, align: 'center'},
                {field: 'channelNo', title: '通道号', sortable: false, width: 30, align: 'center'},
                {field: 'channelName', title: '通道名称', sortable: false, width: 100, align: 'center'},
                {field: 'hls', title: 'HLS流畅直播地址', sortable: false, width: 200, align: 'center'},
                {field: 'rtmp', title: 'rtmp直播地址', sortable: false, width: 200, align: 'center'}

            ]],
            onDblClickRow: function () {
                view();
            },
            onLoadSuccess: function () {
                $(this).datagrid("autoMergeCells", ['deviceSerial']);
            },
            onLoadError: function () {
                layer.msg("接口请求超时，请稍后刷新");
            },
            enableHeaderClickMenu: true,
            enableRowContextMenu: false,
            toolbar: '#tb'
        });

    });


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