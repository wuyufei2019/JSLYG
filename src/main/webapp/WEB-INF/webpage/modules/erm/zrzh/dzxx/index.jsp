<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>地震信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/erm/zrzh/dzxx/index.js?v=1.1"></script>
</head>
<body>
<!-- 工具栏 -->
<div id="zrzh_dzxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	
	<input name="zrzh_dzxx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '地震区域' "/>
	<input name="zrzh_dzxx_m2" class="easyui-combobox" style="height: 30px;" data-options="prompt: '地震类别',panelHeight:'auto', data: [{value:'一般',text:'一般'},
											                                                                                          {value:'较大',text:'较大'},
											                                                                                          {value:'重大',text:'重大'},
					                                                                                                                  {value:'特别重大',text:'特别重大'}]"/>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>	
    </div>
	</form>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="zrzh:dzxx:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="zrzh:dzxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zrzh:dzxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zrzh:dzxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="zrzh:dzxx:exportword">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportword()" title="导出word"><i class="fa fa-external-link"></i> 导出word</button> 
        	</shiro:hasPermission>
<%--         	<shiro:hasPermission name="bis:zybc:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/bis/zybc/exinjump','${ctx}/bis/zybc/exin','${ctx}/static/templates/作业班次信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission> --%>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="zrzh_dzxx_dg"></table> 

</body>
</html>