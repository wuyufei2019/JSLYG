<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>简易陈述申辩笔录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/jycf/cssb/index.js?v=1.2"></script>
</head>
<body >
<div id="jycf_cssbbl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="jycf_cssbbl_gzbh" class="easyui-textbox" style="height: 30px;" data-options="prompt: '告知编号'" />
		<input type="text" name="jycf_cssbbl_M11" class="easyui-textbox" style="height: 30px;" data-options="prompt: '案件名称'"/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="jycf:cssbbl:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="jycf:cssbbl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="jycf:cssbbl:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="jycf:cssbbl:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出陈述申辩笔录"><i class="fa fa-file-word-o"></i> 导出陈述申辩笔录</button> 
        	</shiro:hasPermission>
        	</span>
        	<span id="divper2">
			</span>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
</div>

<table id="jycf_cssbbl_dg"></table> 
</body>
</html>