<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>预立案审批管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/ybcf/ylasp/index.js?v=1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="ybcf_ylasp_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="ybcf_ylasp_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '案由'" />
		<input type="text" name="ybcf_ylasp_qyname" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'150px',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' " />
		<input type="text" name="ybcf_ylasp_casesource" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'150px',editable:false ,valueField: 'text',textField: 'text',url:'${ctx}/aqzf/dict/json/ajly',prompt: '案件来源' " />
		预立案时间：
		<input type="text" class="easyui-datebox" name="ybcf_ylasp_startdate"  style="height: 30px;" data-options="prompt: '开始时间'"  >
		<input type="text" class="easyui-datebox" name="ybcf_ylasp_enddate"  style="height: 30px;" data-options="prompt: '结束时间'"  >
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="ybcf:ylasp:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="ybcf:ylasp:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="ybcf:ylasp:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="ybcf:ylasp:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
	</div> 
</div>
<table id="ybcf_ylasp_dg"></table> 
</body>
</html>