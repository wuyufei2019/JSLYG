<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>绑定摄像头</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<div class="easyui-panel" style="width:100%;height:100%;">
    <div class="easyui-layout" data-options="fit:true">
        <div collapsible="true" title="绑定摄像头列表" data-options="region:'west'" style="width:35%;height:100%">
            <div id="qylist">
            </div>
        </div>
        <div data-options="region:'center'" style="width:65%;height:100%">
            <div id="camerachoose_tb" style="padding:5px;height:auto">
                <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
                    <div class="form-group" style="margin-top:10px">
                        <input type="text" name="filter_LIKES_deviceSerial" id="deviceSerial" class="easyui-textbox" style="width:140px;height: 30px"
                               data-options="prompt:'序列号'"/>
                        <input type="text" name="filter_LIKES_channelName" id="channelName" class="easyui-textbox"
                               style="width:140px;height: 30px"
                               data-options="prompt:'通道名称'"/>
                        <%--<input type="hidden" name="code" id="code"/>--%>
                    </div>
                </form>
                <div class="pull-right" style="margin-top:-40px;margin-left:180px">
                    <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchqy()" ><i class="fa fa-search"></i> 查询</button>
                    <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
                </div>
            </div>
            <table id="camerachoose_dg"></table>
        </div>
    </div>
</div>
<!-- 工具栏 -->

<script type="text/javascript">
    var dg;
    var qylist = $('#qylist');
    var nvrDeviceSerial = '${deviceSerial}';

    $(function () {
        dg = $('#camerachoose_dg').datagrid({
            url: "${ctx}" + "/zxjkyj/hkvision/camera/list",
            queryParams: {

            },
            fit: true,
            fitColumns: true,
            border: false,
            idField: 'id',
            pagination: false,
            rownumbers: true,
            scrollbarSize: 0,
            singleSelect: false,
            striped: true,
            columns: [[
                {field: 'ID', title: 'id', checkbox: true, width: 50, align: 'center'},
                {field: 'deviceSerial', title: '设备序列号', sortable: false, width: 100, align: 'center'},
                {field: 'channelName', title: '通道名称', sortable: false, width: 100, align: 'center'},
            ]],
            checkOnSelect: true,
            selectOnCheck: true,
            onLoadSuccess: function (rowdata, rowindex, rowDomElement) {

                $.each(rowdata.rows, function (i, row) {
                    if ('1' == row.flag) {
                        $('#camerachoose_dg').datagrid('checkRow', i);
                    } else {
                        return;
                    }
                });
            },
            onCheck: function (rowIndex, rowData) {
                var html = $("<div id='" + rowIndex + "' style='margin-left:5px;margin-bottom: 10px;'>"
                    + "<a style='color:#337ab7;text-decoration:none;cursor:pointer;'>"
                    + rowData.channelName +"&nbsp;||&nbsp;"+rowData.deviceSerial+ "</a><span  onClick='removeQy(" + rowIndex
                    + ")' style='cursor: pointer;margin-left:5px;float:right'>删除</span></div>");
                qylist.append(html);
            },
            onUncheck: function (rowIndex, rowData) {
                removeQy(rowIndex);
            },
            onUncheckAll: function (rowIndex, rowData) {
                qylist.empty();
            },
            onCheckAll: function (rowIndex, rowData) {
                qylist.empty();
                var rows = dg.datagrid('getChecked');
                for (var i = 0; i < rows.length; i++) {
                    var html = $("<div id='" + rowIndex + "' style='margin-left:5px;margin-bottom: 10px;'>"
                        + "<a style='color:#337ab7;text-decoration:none;cursor:pointer;'>"
                        + rows[i].channelName +"&nbsp;||&nbsp;"+rows[i].deviceSerial+"</a><span  onClick='removeQy(" + i
                        + ")' style='cursor: pointer;margin-left:5px;float:right'>删除</span></div>");
                    qylist.append(html);
                }
            },
            toolbar: '#camerachoose_tb'
        });
    });

    function removeQy(id) {
        $("#" + id).remove();
        dg.datagrid('uncheckRow', id);
    };

    function searchqy() {
        var obj = $("#searchFrom").serializeObject();
        dg.datagrid('load', obj);
    }

    function reset() {
        $("#searchFrom").form("clear");
        $("#code").val('${code}');
        var obj = $("#searchFrom").serializeObject();
        dg.datagrid('load', obj);
    }

    function getDeviceSerials() {
        var row = $('#camerachoose_dg').datagrid('getChecked');
        var ids = "";
        if (row != null) {
            for (var i = 0; i < row.length; i++) {
                ids = ids + row[i].deviceSerial + ",";
            }
        }
        return ids.substring(0, ids.length - 1);
    }
</script>
</body>
</html>