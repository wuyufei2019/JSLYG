<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>变更申请信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zyaqgl/bggl/bgsq/index.js?v=1.1"></script>
	<script type="text/javascript">
		var usertype = '${usertype}';
	</script>
</head>
<body >
<!-- 工具栏 -->
<div id="zyaqgl_bgsq_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<c:if test="${usertype != '1'}">
			<input type="text" id="zyaqgl_bgsq_qy_name" name="zyaqgl_bgsq_qy_name" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		</c:if>
		<input name="aqzf_bgsq_s1" class="easyui-datebox"  style="height: 30px;" data-options="editable:false ,prompt: '申请日期'" />
		<input name="aqzf_bgsq_m2" class="easyui-datebox"  style="height: 30px;" data-options="editable:false ,prompt: '变更日期'" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="zyaqgl:bgsq:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="zyaqgl:bgsq:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zyaqgl:bgsq:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
		</span>
			<shiro:hasPermission name="zyaqgl:bgsq:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	
        	<shiro:hasPermission name="zyaqgl:bgsq:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
	     
        	<span id="divper2">
        	<shiro:hasPermission name="zyaqgl:bgsq:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/erm/yjyl/yljh/yjdw/exinjump','${ctx}/erm/yjyl/yljh/yjdw/exin','${ctx}/static/templates/应急队伍导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
			</span>
		
			</div>
	</div>
	</div> 
	   
</div>
<table id="zyaqgl_bgsq_dg"></table> 
<shiro:hasPermission name="zyaqgl:xgfpdjh:sh">
<script>sh = 1;</script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaqgl:xgfpdjh:sp">
<script>sp = 1;</script>
</shiro:hasPermission>
<script type="text/javascript">
var qyid = '${qyid}';
</script>
</body>
</html>