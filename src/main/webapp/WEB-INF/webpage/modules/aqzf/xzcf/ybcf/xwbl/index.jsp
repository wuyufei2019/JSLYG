<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>询问通知笔录表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/ybcf/xwbl/index.js?v=1.6"></script>
</head>
<body >
<div id="aqzf_xwbl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	<input type="text" name="aqzf_xwbl_bh" class="easyui-textbox" style="height: 30px;" data-options="prompt: '编号'" />
	<input type="text" name="aqzf_xwbl_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
	<input name="aqzf_xwbl_M1" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '询问起始时间'" />
	<input name="aqzf_xwbl_M2" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '询问结束时间'" />	  	  	        
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper"> 
			<shiro:hasPermission name="aqzf:xwbl:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:xwbl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:xwbl:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="aqzf:xwbl:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-file-word-o"></i> 导出询问笔录</button> 
        	</shiro:hasPermission>
        	</span>
        	<span id="divper2">
			</span>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
</div>

<table id="aqzf_xwbl_dg"></table> 
</body>
</html>