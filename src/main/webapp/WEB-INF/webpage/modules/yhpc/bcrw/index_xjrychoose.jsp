<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>巡检人员选择</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctx}/static/model/js/yhpc/bcrw/index_xjrychoose.js?v=1.2"></script>
</head>
<body>
<!-- 工具栏 -->
<div id="yhpc_xjrychoose_tb" style="padding:5px;height:auto">
    <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
        <div class="form-group" style="margin-top:10px">
            <input type="text" name="filter_LIKES_name" class="easyui-textbox" style="height: 30px"
                   data-options="prompt: '巡检人员名称'"/>
            <input type="text" name="gw" style="height: 30px;" class="easyui-combobox" style="width: 100%;height: 30px;"
                   data-options="prompt: '岗位',panelHeight: '150',valueField: 'text',textField: 'text',url:'${ctx}/tcode/dict/gwgz'"/>
            <input type="text" name="filter_LIKES_depcode" style="height: 30px;" class="easyui-combotree"
                   style="width: 100%;height: 30px;"
                   data-options="prompt: '责任部门',valueField:'id', textField:'text', panelHeight:'200',url:'${ctx}/system/department/idjson',method:'GET'"/>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchxjry()"><i
                    class="fa fa-search"></i> 查询</span>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i
                    class="fa fa-refresh"></i> 全部</span>
        </div>
    </form>
</div>
<table id="yhpc_xjrychoose_dg"></table>
</body>
</html>