<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>数据维护/气体信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zxjkyj/sjwh/qtxx/index.js?v=2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="sjwh_qtxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				
	<div class="form-group">
	<c:if test="${usertype eq '9'}">
		<input type="text" id="zxjkyj_sjwh_qtxx_id1" name="zxjkyj_sjwh_qtxx_id1" class="easyui-combobox"  style="height: 30px" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/ygtjxx/qylist',prompt:'企业名称'  "/>
	</c:if>	
		<input type="text" id="zxjkyj_sjwh_qtxx_m1" name="zxjkyj_sjwh_qtxx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '气体名称'" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		    <shiro:hasPermission name="sjwh:qtxx:add">
		       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sjwh:qtxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sjwh:qtxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sjwh:qtxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		</div>
		
		<div class="pull-right">
		</div>
	</div>
	</div> 
	   
</div>


<table id="sjwh_qtxx_dg"></table> 

<script type="text/javascript">
var usertype = '${usertype}';
</script>
</body>
</html>