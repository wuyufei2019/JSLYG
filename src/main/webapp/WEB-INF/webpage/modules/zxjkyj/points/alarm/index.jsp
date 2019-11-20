<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>储罐报警信息</title>
<meta name="decorator" content="default"/>
<%--
<script type="text/javascript" src="${ctx}/static/model/js/zxjkyj/points/alarm/index.js?v=1"></script>
--%>
</head>
<body>
<div class="easyui-tabs" fit="true">
		<div title="未处理" style="height:100%;" data-options="">
			<div id="bjyj_cgxx_tb" style="padding:5px;height:auto">
			<div class="row">
					<div class="col-sm-12">	
        	<form id="bjyj_cgxx_searchFrom" style="margin-bottom: 8px;" action="" class="form-inline">
        	    <c:if test="${usertype != '1'}">
					<input type="text" id=qyid name="qyid" class="easyui-combobox"  style="height:
					30px;width:250px;"
						   data-options="editable:true ,valueField: 'id',textField: 'm1',url:'${ctx}/zxjkyj/points/maintian/qyjson',prompt: '企业名称' "/>
        		</c:if>
				<input id="status" type="hidden" name="status" value="0" />
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clearA()" ><i class="fa fa-refresh"></i> 全部</span>
			</form>
			<div class="pull-left">
			<span id="divper">
				<shiro:hasPermission name="zxjkyj:cgbj:export">
           			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport1()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
	    		</shiro:hasPermission>
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i>查看</button> 
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</span>
			</div>
			<div style = "text-align:right;">
        	</div>
        	</div>  
        	</div>
        	</div>      
			<table id="bjyj_cgxx_dg"></table> 
		</div>

		
		<div title="已处理" style="height:100%;" data-options="">
			<div id="bjyj_cgxx_tb2" style="padding:5px;height:auto">	
			<div class="row">
					<div class="col-sm-12">	
        	<form id="bjyj_cgxx_searchFrom2" style="margin-bottom: 8px;" action="" class="form-inline">
				<c:if test="${usertype != '1'}">
					<input type="text" id=qyid name="qyid" class="easyui-combobox"  style="height:
					30px;width:250px;"
						   data-options="editable:true ,valueField: 'id',textField: 'm1',url:'${ctx}/zxjkyj/points/maintian/qyjson',prompt: '企业名称' "/>
				</c:if>
				<input id="status2" type="hidden" name="status" value="1" />
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clearA2()" ><i class="fa fa-refresh"></i> 全部</span>
			</form>
			<div class="pull-left">
			<span id="divper">
				<shiro:hasPermission name="zxjkyj:cgbj:export">
           			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport2()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
	    		</shiro:hasPermission>
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view2()" title="查看"><i class="fa fa-search-plus"></i>查看</button> 
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</span>
			</div>
			<div style = "text-align:right;">
        	</div>
        	</div>  
        	  </div>
        	</div> 
			<table id="bjyj_cgxx_dg2"></table> 
		</div>
</div>


<div id="bjyj_cgxx_dlg"></div>  

<script>

    var dgh1;
    var dgh2;
    var d;

    $(function () {
        dgh1 = $('#bjyj_cgxx_dg').datagrid({
            nowrap: false,
            method: "post",
            url: ctx + '/zxjkyj/alarm/list',
            fit: true,
            queryParams: {status: 0},
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
                {field: 'm1', title: '企业名称', sortable: false, width: 100, align: 'left'},
                {
                    field: 'alarmtime', title: '报警时间', sortable: false, width: 50, align: 'center',
                    formatter: function (value, row, index) {
                        if (value != null && value != '') {
                            var dt = new Date(value);
                            return dt.format("yyyy-MM-dd hh:mm:ss");
                        } else {
                            return '/';
                        }
                    }
                },
                {field: 'value', title: '监测值', sortable: false, width: 50, align: 'left'},
                {field: 'point_low_alarm', title: '低限报警值', sortable: false, width: 50, align: 'left'},
                {field: 'point_low_low_alarm', title: '低低限报警值', sortable: false, width: 50, align: 'left'},
                {field: 'point_high_alarm', title: '高限报警值', sortable: false, width: 50, align: 'left'},
                {field: 'point_high_high_alarm', title: '高高限报警值', sortable: false, width: 50, align: 'left'},
                {field: 'alarmtype', title: '报警类型', sortable: false, width: 60, align: 'center'},
                {
                    field: 'status', title: '是否处理', sortable: false, width: 50, align: 'center',
                    formatter: function (value, row, index) {
                        if (value == "1") {
                            return "已处理";
                        } else {
                            return "未处理";
                        }
                    },
                    styler: function (value, row, index) {
                        if (value == "1") {
                        } else {
                            return 'background-color:#FFD2D2;';
                        }
                    },
                },
                {
                    field: 'caozuo', title: '操作', sortable: false, width: 50, align: 'center',
                    formatter: function (value, row, index) {
                        return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='upd("
                            + row.id + ")'>报警处理</a>";
                    }
                }
            ]],
            onDblClickRow: function (rowdata, rowindex, rowDomElement) {
                view();
            },
            onLoadSuccess: function () {
                $(this).datagrid("autoMergeCells", ['qyname']);
            },
            enableHeaderClickMenu: true,
            enableRowContextMenu: false,
            toolbar: '#bjyj_cgxx_tb'
        });

    });


    //处理情况查询
    function search() {
        var obj = $("#bjyj_cgxx_searchFrom").serializeObject();
        $('#bjyj_cgxx_dg').datagrid('load', obj);
    }

    //清空
    function clearA() {
        $("#bjyj_cgxx_searchFrom").form("clear");
        var obj = $("#bjyj_cgxx_searchFrom").serializeObject();
        $('#bjyj_cgxx_dg').datagrid('load', obj);
    }

    //学习记录datagrid
    $(function () {
        dgh2 = $('#bjyj_cgxx_dg2').datagrid({
            nowrap: false,
            method: "post",
            url: ctx + '/zxjkyj/alarm/list',
            fit: true,
            queryParams: {status: 1},
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
                {field: 'm1', title: '企业名称', sortable: false, width: 100, align: 'left'},
                {
                    field: 'alarmtime', title: '报警时间', sortable: false, width: 50, align: 'center',
                    formatter: function (value, row, index) {
                        if (value != null && value != '') {
                            var dt = new Date(value);
                            return dt.format("yyyy-MM-dd hh:mm:ss");
                        } else {
                            return '/';
                        }
                    }
                },
                {field: 'value', title: '监测值', sortable: false, width: 50, align: 'left'},
                {field: 'point_low_alarm', title: '低限报警值', sortable: false, width: 50, align: 'left'},
                {field: 'point_low_low_alarm', title: '低低限报警值', sortable: false, width: 50, align: 'left'},
                {field: 'point_high_alarm', title: '高限报警值', sortable: false, width: 50, align: 'left'},
                {field: 'point_high_high_alarm', title: '高高限报警值', sortable: false, width: 50, align: 'left'},
                {field: 'alarmtype', title: '报警类型', sortable: false, width: 60, align: 'center'},
                {
                    field: 'reason', title: '原因', sortable: false, width: 150, align: 'center'

                }
            ]],
            onDblClickRow: function (rowdata, rowindex, rowDomElement) {
                view2();
            },
            onLoadSuccess: function () {
                $(this).datagrid("autoMergeCells", ['qyname']);
            },
            enableHeaderClickMenu: true,
            enableRowContextMenu: false,
            toolbar: '#bjyj_cgxx_tb2'
        });

    });


    //学习记录查询
    function search2() {
        var obj = $("#bjyj_cgxx_searchFrom2").serializeObject();
        $('#bjyj_cgxx_dg2').datagrid('load', obj);
    }

    //清空
    function clearA2() {
        $("#bjyj_cgxx_searchFrom2").form("clear");
        var obj = $("#bjyj_cgxx_searchFrom2").serializeObject();
        $('#bjyj_cgxx_dg2').datagrid('load', obj);
    }

    //报警处理
    function upd(id) {
        openDialog("报警信息处理", ctx + "/zxjkyj/alarm/deal/" + id, "800px", "250px", "");

    }

    //查看(未处理)
    function view() {
        var row = dgh1.datagrid('getSelected');
        if (row == null) {
            layer.msg("请选择一行记录！", {time: 1000});
            return;
        }

        openDialogView("查看储罐报警信息", ctx + "/zxjkyj/alarm/view/" + row.id, "800px", "400px", "");

    }

    //查看(已处理)
    function view2() {
        var row = dgh2.datagrid('getSelected');
        if (row == null) {
            layer.msg("请选择一行记录！", {time: 1000});
            return;
        }

        openDialogView("查看储罐报警信息", ctx + "/zxjkyj/alarm/view/" + row.id, "800px", "400px", "");

    }



</script>
</body>
</html>