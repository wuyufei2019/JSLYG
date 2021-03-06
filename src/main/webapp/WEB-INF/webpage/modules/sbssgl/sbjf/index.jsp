<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备设施管理设备交付</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/sbjf/index.js?v=1.0"></script>
</head>
<body >
<div id="sbssgl_sbjf_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<c:if test="${type eq '2'}">
			<input type="text" name="qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		</c:if>
		<input name="m4" class="easyui-textbox"  style="height: 30px;" data-options="editable:true,prompt: '设备编号'" />
		<input name="m5" class="easyui-textbox"  style="height: 30px;" data-options="editable:true,prompt: '名称型号'" />
		<input name="m15" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '启用日期'" />	
		<input name="m20" class="easyui-combobox"  style="height: 30px;" data-options="editable:false,prompt: '状态',panelHeight:'auto',data: [
										{value:'0',text:'待上传附件'},
								        {value:'1',text:'待建立设备台账'},
								        {value:'2',text:'已建立设备台账'}]" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<%-- <shiro:hasPermission name="sbssgl:sbjf:add">
		       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission> --%>
			<shiro:hasPermission name="sbssgl:sbjf:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sbssgl:sbjf:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sbssgl:sbjf:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="sbssgl:sbjf:exportword">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出设备交付单"><i class="fa fa-file-word-o"></i> 导出设备交付单</button>
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 	   
</div>
<table id="sbssgl_sbjf_dg"></table> 
<script>
	var type = '${type}';
	var uploadrole = '0';//上传附件 
	var sbglrole = '0';//设备台账添加权限
</script>
<shiro:hasPermission name="sbssgl:sbjf:upload">
	<script>uploadrole = '1';</script>
</shiro:hasPermission>
<shiro:hasPermission name="sbssgl:sbgl:add">
	<script>sbglrole = '1';</script>
</shiro:hasPermission>
</body>
</html>