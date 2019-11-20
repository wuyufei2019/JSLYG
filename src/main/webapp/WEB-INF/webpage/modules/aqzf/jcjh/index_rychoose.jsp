<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全执法检查计划</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/jcjh/index_rychoose.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="jcjh_rychoose_tb" style="padding-left:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group" style="margin-top:10px">
		<input type="text" name="rynm"  class="easyui-textbox" style="height: 30px" data-options="prompt: '执法人员名称'" />
		<%--<input type="text" name="fzid" style="height: 30px;"
		            class="easyui-combobox" data-options="panelHeight:'auto' ,editable:false,valueField:'id',textField:'text',url:'${ctx }/aqzf/ryfz/ryfzlist',prompt: '人员分组'"/>--%>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchry()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>   
    </div>
	</form>
</div>
<table id="jcjh_rychoose_dg"></table> 
</body>
</html>