<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查点选择</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/bcrw/index_jcdchoose.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="yhpc_jcdchoose_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group" style="margin-top:10px">
		<input type="text" name="jcdname"  class="easyui-textbox" style="height: 30px" data-options="prompt: '检查点名称'" />
		<input type="text" name="fxfj" class="easyui-combobox" style="height: 30px;" data-options="prompt: '风险分级',panelHeight:'auto',editable:false,data: [
										{value:'1',text:'重大'},
								        {value:'2',text:'较大'},
								        {value:'3',text:'一般'},
								        {value:'4',text:'普通'}
								        ]"/> 
		<input type="text" id="zzbm" name="zzbm"  class="easyui-combotree" style="height: 30px"
			   data-options="editable:true,prompt: '责任部门',valueField:'id', textField:'text',
			   panelHeight:'200',url:'${ctx}/system/department/idjson',method:'GET'" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchjcd()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>
</div>
<table id="yhpc_jcdchoose_dg"></table> 
</body>
</html>