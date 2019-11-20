<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>企业变更信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src=" ${ctx}/static/model/js/qyxx/qybg/index.js?v=1.2"></script>
</head>
<body >
<!-- 工具栏 -->
   <div id="dg_tb" style="padding:5px;height:auto">
      <form id="searchFrom" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
            <c:if test="${usertype ne '1'}">
       	        <input type="text" name="view_qyname" class="easyui-combobox" style="height: 30px;" data-options="valueField: 'text',textField: 'text',
                 url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
            </c:if>
            <input type="text" name="view_modification" class="easyui-combobox" style="height: 30px;" data-options="valueField: 'value',textField: 'text',panelHeight:'auto',
                   data:[{text:'关停增减',value:'关停增减'}, {text:'辖区转移',value:'辖区转移'}, {text:'信息变更',value:'信息变更'}],prompt: '变更事项'" />
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
               class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
               <%-- <shiro:hasPermission name="bis:qybg:add">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
                     <i class="fa fa-plus"></i> 添加
                  </button>
               </shiro:hasPermission> --%>
               <%--<shiro:hasPermission name="bis:qybg:sh">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="review()" title="审核">
                     <i class="fa fa-file-text-o"></i> 审核
                  </button>
               </shiro:hasPermission>--%>
               <shiro:hasPermission name="bis:qybg:delete">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="bis:qybg:view">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button>
               </shiro:hasPermission>
               <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
                  <i class="glyphicon glyphicon-repeat"></i> 刷新
               </button>

            </div>
         </div>
      </div>
      
   </div>

   <table id="table_dg"></table> 

<script type="text/javascript">
var usertype=${usertype}
</script>
</body>
</html>