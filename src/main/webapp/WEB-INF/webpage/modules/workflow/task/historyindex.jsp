<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>事件信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript"
            src="${ctx}/static/model/js/workflow/task/indexH.js?v=1.1"></script>
    <script type="text/javascript"
            src="${ctx}/static/model/js/activiti/workflow.js?v=1.0"></script>
</head>
<body>
<!-- 工具栏 -->
<div id="dg_tb" style="padding:5px;height:auto">
    <form id="searchFrom" action="" style="margin-bottom: 8px;"
          class="form-inline">
        <div class="form-group">
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i
                    class="fa fa-search"></i> 查询</span>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i
                    class="fa fa-refresh"></i> 全部</span>
        </div>
    </form>
    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
                <button class="btn btn-white btn-sm" data-toggle="tooltip"
                        data-placement="left" onclick="view()" title="查看">
                    <i class="fa fa-search-plus"></i> 查看
                </button>
                <button class="btn btn-white btn-sm " data-toggle="tooltip"
                        data-placement="left" onclick="refresh()" title="刷新">
                    <i class="glyphicon glyphicon-repeat"></i> 刷新
                </button>

            </div>
        </div>
    </div>

</div>


<table id="dg"></table>

</body>
</html>