<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>法律法规识别</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zdgl/flfgsb/flsb/index.js?v=1"></script>
</head>
<body >
<div id="zdgl_flsb_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" id="zdgl_flsb_m2" name="zdgl_flsb_m2" style="height: 30px;" class="easyui-textbox" data-options="prompt: '法律法规名称'"/>
		<input id="zdgl_flsb_m1_1" name="zdgl_flsb_m1_1" class="easyui-combobox" style="height: 30px;" data-options="prompt: '大类别',panelHeight:'150px',editable:false,data: [
										{value:'1',text:'法律'},
								        {value:'2',text:'法规'},
								        {value:'3',text:'规章'},
								        {value:'4',text:'地方性法规'},
								        {value:'5',text:'政府文件'},
								        {value:'6',text:'标准规范'},
								        {value:'7',text:'其他'}]" />
		<input id="zdgl_flsb_m1" name="zdgl_flsb_m1" class="easyui-combotree" style="height: 30px;" data-options="prompt: '小类别',panelHeight:'150px',editable:false,url: '${ctx}/zdgl/flfg/treelist'" />
        <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		    <shiro:hasPermission name="zdgl:flsb:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="zdgl:flsb:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zdgl:flsb:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zdgl:flsb:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="zdgl:flsb:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
</div>
<table id="zdgl_flsb_dg"></table> 
<script>var role = '1';</script>
<shiro:hasPermission name="zdgl:flsb:sh">
	<shiro:lacksPermission  name="zdgl:flsb:sp">
		<script>role = '2';</script>
	</shiro:lacksPermission >
	<shiro:hasPermission name="zdgl:flsb:sp">
		<script>role = '4';</script>
	</shiro:hasPermission>
</shiro:hasPermission>
<shiro:lacksPermission  name="zdgl:flsb:sh">
	<shiro:hasPermission name="zdgl:flsb:sp">
		<script>role = '3';</script>
	</shiro:hasPermission>
</shiro:lacksPermission >
<script type="text/javascript">
var qyid = '${qyid}';
</script>
</body>
</html>