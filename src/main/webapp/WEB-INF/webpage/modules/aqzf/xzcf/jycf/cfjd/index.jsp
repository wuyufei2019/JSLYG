<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>简易处罚决定记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/jycf/cfjd/index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="jycf_cfjd_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="jycf_cfjd_M0" class="easyui-textbox" style="height: 30px;" data-options="prompt: '编号'" />
		<input type="text" name="jycf_cfjd_dsr" class="easyui-textbox" style="height: 30px;" data-options="prompt: '当事人'" />
		<input type="text" name="jycf_cfjd_M1" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto', prompt: '类型',editable:false,data: [
										{value:'1',text:'单位'},
								        {value:'2',text:'个人'}]" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="jycf:cfjd:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="jycf:cfjd:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="jycf:cfjd:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="jycf:cfjd:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportcfjd()" title="导出"><i class="fa fa-external-link"></i> 导出处罚决定书</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
</div>
<table id="jycf_cfjd_dg"></table> 
<script>
		
</script>
</body>
</html>