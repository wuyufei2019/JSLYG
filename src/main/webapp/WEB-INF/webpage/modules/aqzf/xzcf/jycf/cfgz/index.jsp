<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>简易处罚告知记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/jycf/cfgz/index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="jycf_cfgz_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="jycf_cfgz_M0" class="easyui-textbox" style="height: 30px;" data-options="prompt: '编号'" />
		<input type="text" name="jycf_cfgz_M1" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'150px',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' " />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="jycf:cfgz:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="jycf:cfgz:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="jycf:cfgz:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="jycf:cfgz:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="jycf:cfgz:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportgzs()" title="导出"><i class="fa fa-external-link"></i> 导出处罚告知书</button>
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportcssb()" title="导出"><i class="fa fa-external-link"></i> 导出陈述申辩笔录</button> 
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportjds()" title="导出"><i class="fa fa-external-link"></i> 导出处罚决定书</button>   
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
</div>
<table id="jycf_cfgz_dg"></table> 
<script>
		
</script>
</body>
</html>