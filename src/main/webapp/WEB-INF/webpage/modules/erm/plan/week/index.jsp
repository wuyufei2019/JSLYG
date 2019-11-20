<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>训练计划信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctx}/static/model/js/erm/plan/week/index.js?v=1.0"></script>
</head>
<body>
<!-- 工具栏 -->
<div id="tb" style="padding:5px;height:auto">
    <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
        <div class="form-group">
            <input type="text" name="filter_LIKES_qyName" class="easyui-combobox"
                   style="height: 30px;"
                   data-options="valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称 '"/>
            <input type="text" name="filter_LIKES_planName" class="easyui-textbox" style="height: 30px;"
                   data-options="prompt: '训练计划名称 '"/>
            <input type="text" name="filter_GED_S1" class="easyui-datebox" style="height: 30px;"
                   data-options="prompt: '开始时间 '"/>
            <input type="text" name="filter_LED_S1" class="easyui-datebox" style="height: 30px;"
                   data-options="prompt: '结束时间 '"/>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i
                    class="fa fa-search"></i> 查询</span>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i
                    class="fa fa-refresh"></i> 全部</span>
        </div>
    </form>

    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
                <shiro:hasPermission name="erm:train-plan:week:add">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()"
                            title="添加"><i class="fa fa-plus"></i> 添加
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="erm:train-plan:week:update">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()"
                            title="修改"><i class="fa fa-file-text-o"></i> 修改
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="erm:train-plan:week:delete">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()"
                            title="删除"><i class="fa fa-trash-o"></i> 删除
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="erm:train-plan:week:view">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()"
                            title="查看"><i class="fa fa-search-plus"></i> 查看
                    </button>
                </shiro:hasPermission>
                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()"
                        title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新
                </button>

            </div>
            <div class="pull-right">
            </div>
        </div>
    </div>

</div>


<table id="dg"></table>
<script type="text/javascript">
    var usertype = '${usertype}';
</script>
</body>
</html>