<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>隐患排查记录</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<div id="yhpc_yhpcd_tb" style="padding:5px;height:auto">
    <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
        <div class="form-group">
            <input type="text" id="fxgk_fxxx_name" name="fxgk_fxxx_name" class="easyui-textbox" style="height: 30px;"
                   data-options="prompt: '隐患自查点名称' "/>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i
                    class="fa fa-search"></i> 查询</span>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i
                    class="fa fa-refresh"></i> 全部</span>
        </div>
    </form>

    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
		<span id="divper">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()"
                        title="添加"><i class="fa fa-plus"></i> 添加</button>
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()"
                        title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()"
                        title="删除"><i class="fa fa-trash-o"></i> 删除</button>
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()"
                        title="查看"><i class="fa fa-search-plus"></i> 查看</button>
        	</span>
                <span id="divper2">
			</span>
                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()"
                        title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新
                </button>

            </div>
            <div class="pull-right">
            </div>
        </div>
    </div>
</div>

<table id="yhpc_yhpcd_dg"></table>
<script>

    var dg;
    var d;
    $(function () {
        dg = $('#yhpc_yhpcd_dg').datagrid({
            method: "get",
            url: ctx + '/yhpc/yhpcd/list',
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
                {field: 'qyname', title: '企业名称', sortable: false, width: 80},
                {field: 'm1', title: '巡查点名称', sortable: false, width: 80, align: 'center'},
                {field: 'bindcontent', title: '绑定二维码', sortable: false, width: 50, align: 'center'},
                {field: 'rfid', title: '绑定rfid', sortable: false, width: 50, align: 'center'},
                {field: 'checkpointadderss', title: 'rfid卡批次代码', sortable: false, width: 50, align: 'center'},
                {
                    field: 'ewm', title: '二维码', sortable: false, width: 30, align: 'center',
                    formatter: function (value, row, index) {
                        return "<a class='btn btn-success btn-xs' onclick='openerm(" + row.id + ")'>生成二维码</a>";
                    }
                },
                {
                    field: 'zt', title: '状态', sortable: false, width: 30, align: 'center',
                    formatter: function (value, row, index) {
                        if (row.stoppointid != null) {
                            return "<a class='btn btn-danger btn-xs'>停产</a>";
                        } else {
                            return "<a class='btn btn-info btn-xs'>正常</a>";
                        }
                    }
                }
            ]],
            onDblClickRow: function (rowdata, rowindex, rowDomElement) {
                view();
            },
            onLoadSuccess: function (rowdata, rowindex, rowDomElement) {
                if (usertype == '1') {
                    $(this).datagrid("hideColumn", ['qyname']);
                } else {
                    $(this).datagrid("showColumn", ['qyname']);
                    $(this).datagrid("autoMergeCells", ['qyname']);
                }
            },
            checkOnSelect: false,
            selectOnCheck: false,
            toolbar: '#yhpc_yhpcd_tb'
        });

    });
</script>
</body>
</html>