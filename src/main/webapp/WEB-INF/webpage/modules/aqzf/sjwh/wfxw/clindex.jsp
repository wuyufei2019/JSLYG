<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>违法行为裁量表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/sjwh/wfxw/clindex.js?v=1.2"></script>
</head>
<body >
<div id="aqzf_cfcl_tb" style="padding:5px;height:auto">
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<span id="divper">
		       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add('${version}')" title="添加"><i class="fa fa-plus"></i> 添加</button>
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
	   		</span>
        </div>
	</div>
	</div> 
</div>

<table id="aqzf_cfcl_dg"></table> 
<script type="text/javascript">
var id1 = '${id1}';
var version = '${version}';
</script>
</body>
</html>