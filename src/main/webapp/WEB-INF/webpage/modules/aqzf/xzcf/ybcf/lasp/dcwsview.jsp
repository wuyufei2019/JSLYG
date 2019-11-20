<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全执法导出文书</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	
		<table style="width: 100%;height: 120px;text-align: center;margin-top: 20px;">
			<tr>
				<td><button class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportlasp()" title=""><i class="fa fa-file-word-o"></i> 立案审批表</button></td>
				<td>
					<c:choose>
					    <c:when test="${lasp.xwflag == '1'}">
					    	<button class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportxw()" title=""><i class="fa fa-file-word-o"></i> 询问通知书</button>
					    </c:when>
					    <c:otherwise>
					    	<button class="btn btn-default btn-sm" data-toggle="tooltip" data-placement="left" title="" disabled="disabled"><i class="fa fa-file-word-o"></i> 询问通知书</button>
					    </c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
					    <c:when test="${lasp.dcflag == '1'}">
					    	<button style="width: 95.64px;" class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportdc()" title=""><i class="fa fa-file-word-o"></i> 调查报告</button>
					    </c:when>
					    <c:otherwise>
					    	<button style="width: 95.64px;" class="btn btn-default btn-sm" data-toggle="tooltip" data-placement="left" title="" disabled="disabled"><i class="fa fa-file-word-o"></i> 调查报告</button>
					    </c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
					    <c:when test="${lasp.gzflag == '1'}">
					    	<button class="btn btn-warning btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportgzs()" title=""><i class="fa fa-file-word-o"></i> 处罚告知书</button>
					    </c:when>
					    <c:otherwise>
					    	<button class="btn btn-default btn-sm" data-toggle="tooltip" data-placement="left" title="" disabled="disabled"><i class="fa fa-file-word-o"></i> 处罚告知书</button>
					    </c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>
					<c:choose>
					    <c:when test="${lasp.sbflag == '1'}">
					    	<button style="width: 95.64px;" class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportsb()" title=""><i class="fa fa-file-word-o"></i> 陈述申辩</button>
					    </c:when>
					    <c:otherwise>
					    	<button style="width: 95.64px;" class="btn btn-default btn-sm" data-toggle="tooltip" data-placement="left" title="" disabled="disabled"><i class="fa fa-file-word-o"></i> 陈述申辩</button>
					    </c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
					    <c:when test="${lasp.tzflag == '1'}">
					    	<button class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexporttz()" title=""><i class="fa fa-file-word-o"></i> 听证告知书</button>
					    </c:when>
					    <c:otherwise>
					    	<button class="btn btn-default btn-sm" data-toggle="tooltip" data-placement="left" title="" disabled="disabled"><i class="fa fa-file-word-o"></i> 听证告知书</button>
					    </c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
					    <c:when test="${lasp.cbflag == '1'}">
					    	<button class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportajcp()" title=""><i class="fa fa-file-word-o"></i> 案件呈批表</button>
					    </c:when>
					    <c:otherwise>
					    	<button class="btn btn-default btn-sm" data-toggle="tooltip" data-placement="left" title="" disabled="disabled"><i class="fa fa-file-word-o"></i> 案件呈批表</button>
					    </c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
					    <c:when test="${lasp.tlflag == '1'}">
					    	<button style="width: 95.64px;" class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexporttl()" title=""><i class="fa fa-file-word-o"></i> 集体讨论</button>
					    </c:when>
					    <c:otherwise>
					    	<button style="width: 95.64px;" class="btn btn-default btn-sm" data-toggle="tooltip" data-placement="left" title="" disabled="disabled"><i class="fa fa-file-word-o"></i> 集体讨论</button>
					    </c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>
					<c:choose>
					    <c:when test="${lasp.cfjdflag == '1'}">
					    	<button class="btn btn-warning btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportcfjd()" title=""><i class="fa fa-file-word-o"></i> 处罚决定书</button>
					    </c:when>
					    <c:otherwise>
					    	<button class="btn btn-default btn-sm" data-toggle="tooltip" data-placement="left" title="" disabled="disabled"><i class="fa fa-file-word-o"></i> 处罚决定书</button>
					    </c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
					    <c:when test="${lasp.fkspflag == '1'}">
					    	<button style="width: 95.64px;" class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexporfksp()" title=""><i class="fa fa-file-word-o"></i> 缴款审批</button>
					    </c:when>
					    <c:otherwise>
					    	<button style="width: 95.64px;" class="btn btn-default btn-sm" data-toggle="tooltip" data-placement="left" title="" disabled="disabled"><i class="fa fa-file-word-o"></i> 缴款审批</button>
					    </c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
					    <c:when test="${lasp.jaflag == '1'}">
					    	<button class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportjasp()" title=""><i class="fa fa-file-word-o"></i> 结案审批表</button>
					    </c:when>
					    <c:otherwise>
					    	<button class="btn btn-default btn-sm" data-toggle="tooltip" data-placement="left" title="" disabled="disabled"><i class="fa fa-file-word-o"></i> 结案审批表</button>
					    </c:otherwise>
					</c:choose>
				</td>
				<td><button class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexporsdhz()" title=""><i class="fa fa-file-word-o"></i> 送达回执书</button></td>
			</tr>
		</table>
	<%-- <button class="btn btn-white btn-sm" data-toggle="tooltip" style="background-color: #e84e40"data-placement="left" onclick="fileexportsxcg()" title=""><i class="fa fa-file-word-o"></i> 事先催告书</button> 
	<button class="btn btn-default btn-sm" data-toggle="tooltip"style="background-color:#e51c23" data-placement="left" onclick="fileexportqzzx()" title=""><i class="fa fa-file-word-o"></i> 强制执行书</button> --%>
<script type="text/javascript">
var id = '${lasp.ID}';
var tempflag = '${lasp.tempflag}';
var xwflag = '${lasp.xwflag}';
var dcflag = '${lasp.dcflag}';
var gzflag = '${lasp.gzflag}';
var sbflag = '${lasp.sbflag}';
var tzflag = '${lasp.tzflag}';
var cbflag = '${lasp.cbflag}';
var tlflag = '${lasp.tlflag}';
var cfjdflag = '${lasp.cfjdflag}';
var fkspflag = '${lasp.fkspflag}';
var jaflag = '${lasp.jaflag}';
var qzflag = '${lasp.qzflag}';
var cgflag = '${lasp.cgflag}';

//导出立案审批word
function fileexportlasp() {
	if(tempflag=='1'){
		layer.msg("临时添加的立案审批记录，请先补全记录！",{time: 3000});
		return;
	}else{
		$.ajax({
			url : ctx + "/xzcf/ybcf/lasp/exportlasp/" + id,
			type : "POST",
			success : function(data) {
				window.open(ctx + data);
			}
		});
	}
}

//导出询问通知书word
function fileexportxw() {
	if (xwflag !='1') {
		layer.msg("您还没有添加询问记录！",{time: 3000});
		return;
	}
	$.ajax({
		url : ctx + "/xzcf/ybcf/xwtz/export/la/" + id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}

//导出调查报告word
function fileexportdc() {
	if(dcflag=='1'){
		$.ajax({
			url:ctx+"/xzcf/ybcf/dcbg/export/la/"+id,
			type : "POST",
			success : function(data) {
				window.open(ctx + data);
			}
		});
	}else{
		layer.msg("您还没有添加调查报告记录！",{time: 3000});
		return;
	}
}

//导出告知书记录word
function fileexportgzs() {
	if(gzflag=='1'){
		$.ajax({
			url : ctx + "/xzcf/ybcf/cfgz/exportgzs/la/" + id,
			type : "POST",
			success : function(data) {
				window.open(ctx + data);
			}
		});
	}else{
		layer.msg("您还没有添加告知记录！",{time: 3000});
		return;
	}
}

//导出陈述申辩word
function fileexportsb() {
	if(sbflag=='1'){
		$.ajax({
			url:ctx+"/xzcf/ybcf/cssbbl/export/la/"+id,
			type : "POST",
			success : function(data) {
				window.open(ctx + data);
			}
		});
	}else{
		layer.msg("您还没有添加陈述申辩笔录！",{time: 3000});
		return;
	}
}

//导出听证告知记录word
function fileexporttz() {
	if(tzflag=='1'){
		$.ajax({
			url : ctx + "/xzcf/ybcf/tzgz/exporttz/la/" + id,
			type : "POST",
			success : function(data) {
				window.open(ctx + data);
			}
		});
	}else{
		layer.msg("您还没有添加听证记录！",{time: 3000});
		return;
	}
}

//案件呈批
function fileexportajcp() {
	if (cbflag != '1') {
		layer.msg("您还没有添加案件呈批记录！",{time: 3000});
		return;
	}
	$.ajax({
		url : ctx + "/xzcf/ybcf/ajclcp/exportajcp/la/" + id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}

//导出集体讨论word
function fileexporttl() {
	if(tlflag=='1'){
		$.ajax({
			url:ctx+"/xzcf/ybcf/jttl/export/la/"+id,
			type : "POST",
			success : function(data) {
				window.open(ctx + data);
			}
		});
	}else{
		layer.msg("您还没有添加集体讨论记录！",{time: 3000});
		return;
	}
}

//导出处罚决定书word
function fileexportcfjd() {
	if (cfjdflag != '1') {
		layer.msg("您还没有添加处罚决定记录！",{time: 3000});
		return;
	}

	$.ajax({
		url : ctx + "/xzcf/ybcf/cfjd/exportcfjd/la/" + id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}

//导出缴款审批word
function fileexporfksp() {
	if(fkspflag=='1'){
		$.ajax({
			url:ctx+"/xzcf/ybcf/fksp/export/la/"+id,
			type : "POST",
			success : function(data) {
				window.open(ctx + data);
			}
		});
	}else{
		layer.msg("您还没有添加缴款审批！",{time: 3000});
		return;
	}
}

//导出结案审批word
function fileexportjasp() {
	if (jaflag != '1') {
		layer.msg("您还没有添加结案审批记录！",{time: 3000});
		return;
	}
	
	$.ajax({
		url : ctx + "/xzcf/ybcf/jasp/exportjasp/la/" + id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}

// 导出送达回执word
function fileexporsdhz() {
	if (tempflag == 1) {
		layer.msg("立案审批信息未补全，请先补全信息！",{time: 3000});
		return;
	}
	$.ajax({
		url : ctx + "/xzcf/ybcf/lasp/exportsdhz/" + id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}

//导出强制执行word
function fileexportqzzx() {
	if (qzflag !='1') {
		layer.msg("您还没有添加强制执行记录！",{time: 3000});
		return;
	}
	
	$.ajax({
		url : ctx + "/xzcf/ybcf/qzzx/exportqzzx/la/" + id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}

//导出事先催告表
function fileexportsxcg() {
	if (cgflag!=1) {
		layer.msg("您还没有添加事先催告记录！",{time: 3000});
		return;
	}
	$.ajax({
		url : ctx + "/xzcf/ybcf/sxcg/exportsxcg/la/" + id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}
</script>
</body>
</html>