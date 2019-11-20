<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>监督检查统计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/jdjctj/index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="aqzf_jdjctj_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input id="aqzf_jdjctj_year" name="aqzf_jdjctj_year" style="height: 30px;"/>
	    <input id="aqzf_jdjctj_month" name="aqzf_jdjctj_month" class="easyui-combobox"  style="height: 30px;" value="" data-options="panelHeight:'auto' ,prompt: '月份',editable:true,data: [
						         {value:'1月',text:'1月'},
						         {value:'2月',text:'2月'}, 
						         {value:'3月',text:'3月'}, 
						         {value:'4月',text:'4月'}, 
						         {value:'5月',text:'5月'}, 
						         {value:'6月',text:'6月'},
						         {value:'7月',text:'7月'},
						         {value:'8月',text:'8月'}, 
						         {value:'9月',text:'9月'}, 
						         {value:'10月',text:'10月'}, 
						         {value:'11月',text:'11月'}, 
						         {value:'12月',text:'12月'}
						        	]  "/>
		<input type="text" name="aqzf_jdjctj_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>				        	
		<input type="text" id="aqzf_jdjctj_M4" name="aqzf_jdjctj_M4" class="easyui-combobox" style="height: 30px;" value="" data-options="editable:false,prompt: '行业类型',panelHeight:'auto' ,data:[{value:'1',text:'工贸'},{value:'2',text:'化工'}]" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="refresh()" ><i class="glyphicon glyphicon-repeat"></i> 刷新</span>
    </div>
	</form>

	<%-- <div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
        	<shiro:hasPermission name="aqzf:jdjctj:export">
        		<button class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportjcfa()" title=""><i class="fa fa-file-word-o"></i> 检查方案</button> 
        		<button class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportjcjl()" title=""><i class="fa fa-file-word-o"></i> 检查记录</button> 
        		<button class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportclcs()" title=""><i class="fa fa-file-word-o"></i> 现场处理措施</button> 
        		<button class="btn btn-warning btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportzlzg()" title=""><i class="fa fa-file-word-o"></i> 责令限期整改</button>
        		<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportzgfc()" title=""><i class="fa fa-file-word-o"></i> 整改复查意见</button> 
        	</shiro:hasPermission>
		</div>
	</div>
	</div>  --%>
</div>
<table id="aqzf_jdjctj_dg"></table> 
<script type="text/javascript">
//年份下拉框初始化
$("#aqzf_jdjctj_year").combobox({ 
	prompt: '年份',
	editable:'true',
	valueField:'year',    
	textField:'year',  
	panelHeight:'auto'
});
var data = [];
var thisYear;
var startYear=new Date().getUTCFullYear()+2;

for(var i=0;i<6;i++){
	thisYear=startYear-i;
	data.push({"year":thisYear});
}
	
$("#aqzf_jdjctj_year").combobox("loadData", data);//下拉框加载数据

</script>
</body>
</html>