<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>训练计划信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctx}/static/model/js/erm/plan/week/check/index.js?v=1.0"></script>
</head>
<body>
<!-- 工具栏 -->
<div id="tb" style="padding:5px;height:auto">

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
    var planId = '${planId}';
</script>
</body>
</html>