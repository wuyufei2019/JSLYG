<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>行政处罚统计管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/ybcf/xzcftj/index.js?v=1.2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="ybcf_lasp_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input name="ybcf_lasp_type" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto',
								editable:false ,data: [
										{value:'0',text:'预立案'},
								        {value:'1',text:'已立案'}],prompt: '状态' "/>
		<input type="text" name="ybcf_lasp_number" class="easyui-textbox" style="height: 30px;" data-options="prompt: '编号'" />
		<input type="text" name="ybcf_lasp_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '案由'" />
		<input type="text" name="ybcf_lasp_qyname" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'150px',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' " />
		<input type="text" name="ybcf_lasp_casesource" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'150px',editable:false ,valueField: 'text',textField: 'text',url:'${ctx}/aqzf/dict/json/ajly',prompt: '案件来源' " />
		立案时间：
		<input type="text" class="easyui-datebox" name="ybcf_lasp_startdate"  style="height: 30px;" data-options="prompt: '开始时间'"  >
		<input type="text" class="easyui-datebox" name="ybcf_lasp_enddate"  style="height: 30px;" data-options="prompt: '结束时间'"  >
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="fileexport()" ><i class="fa fa-external-link"></i> 导出Excel</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="refresh()" ><i class="glyphicon glyphicon-repeat"></i> 刷新</span>
    </div>
	</form>
</div>
<table id="ybcf_lasp_dg"></table> 
<script>
	
</script>
</body>
</html>