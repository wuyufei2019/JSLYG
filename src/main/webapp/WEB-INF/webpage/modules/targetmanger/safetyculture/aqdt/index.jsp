<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>安全动态管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/targetmanger/safetyculture/aqdt/index.js?v=1.2"></script>
</head>
<body>
	<!-- 工具栏 -->
	<div id="target_aqdt_tb" style="padding:5px;height:auto">
      <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
            <c:if test="${ usertype eq 'isbloc' }">
               <input type="text" name="list_qyname" style="height:30px" class="easyui-textbox" data-options="width:200,prompt: '企业名称'" />
            </c:if>
            <input type="text" name="list_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '主题名称'" />
            <input type="text" name="list_starttime" class="easyui-datebox" style="height: 30px;" data-options="editable:false , prompt: '开始日期' " />
            <input type="text" name="list_endtime" class="easyui-datebox" style="height: 30px;" data-options="editable:false , prompt: '结束日期' " />
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
               class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>
      <div class="row">
			<div class="col-sm-12">
				<div class="pull-left">
					<shiro:hasPermission name="target:aqdt:add">
						<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
							<i class="fa fa-plus"></i> 添加
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="target:aqdt:update">
						<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
							<i class="fa fa-file-text-o"></i> 修改
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="target:aqdt:delete">
						<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
							<i class="fa fa-trash-o"></i> 删除
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="target:aqdt:view">
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
	<table id="target_aqdt_dg"></table>
   <script type="text/javascript">
   var qyid= '${qyid}';
   var usertype='${usertype}';
   </script>
</body>
</html>