<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>延期申请管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctx}/static/model/js/aqzf/yqsq/index.js?v=1.1"></script>
</head>
<body>

<div id="tb" style="padding:5px;height:auto">
    <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
        <div class="form-group">
            <input type="text" id="filter_number" name="filter_number" class="easyui-textbox" style="height: 30px;"
                   data-options="prompt: '编号'"/>
            <!-- <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
            <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span> -->
        </div>
    </form>
    <div class="pull-right" style="margin-top:-40px;margin-left:180px">
        <button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i>
            查询
        </button>
        <button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i>
            全部
        </button>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
               <%-- <shiro:hasPermission name="aqzf:yqsq:add">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addjh()"
                            title="添加"><i class="fa fa-plus"></i> 添加
                    </button>
                </shiro:hasPermission>--%>
                <shiro:hasPermission name="aqzf:yqsq:update">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="updjh()"
                            title="修改"><i class="fa fa-file-text-o"></i> 修改
                    </button>
                </shiro:hasPermission>
               <%-- <shiro:hasPermission name="aqzf:yqsq:delete">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="deljh()"
                            title="删除"><i class="fa fa-trash-o"></i> 删除
                    </button>
                </shiro:hasPermission>--%>
                <shiro:hasPermission name="aqzf:yqsq:view">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="viewjh()"
                            title="查看"><i class="fa fa-search-plus"></i> 查看
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
<!-- 工具栏 -->
<script type="text/javascript">
    var sh = false;
</script>

<shiro:hasPermission name="aqzf:yqsq:review">
    <script type="text/javascript">
        var sh = true;
    </script>
</shiro:hasPermission>
</body>
</html>