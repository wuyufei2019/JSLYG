<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事故询问通知书记录表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/ybcf/sgxwtz/index.js?v=1.4"></script>
</head>
<body >
<div id="aqzf_sgxwtz_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	<input type="text" name="aqzf_sgxwtz_M1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '调查主题'" />
	<input type="text" name="aqzf_sgxwtz_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
	<input name="aqzf_sgxwtz_M2" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '询问时间'" />	
	<input name="aqzf_sgxwtz_flag" type="text"  class="easyui-combobox" style="height: 30px;"
								data-options="editable:false ,panelHeight:'auto' ,prompt: '是否立案',data: [
								        {value:'0',text:'未立案'},
								        {value:'1',text:'已立案'}]
						    " />      
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="aqzf:sgxwtz:add">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button> 
			</shiro:hasPermission> 
			<shiro:hasPermission name="aqzf:sgxwtz:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:sgxwtz:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:sgxwtz:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	</span>
        	<span id="divper2">
			</span>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
</div>

<table id="aqzf_sgxwtz_dg"></table> 
</body>
</html>