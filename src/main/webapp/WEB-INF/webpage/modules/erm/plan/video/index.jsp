<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%> 
<html>
<head>
<title>风险标识库</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/erm/plan/video/index.js?v=1.1"></script>
<script type="text/javascript" src="${ctx}/static/model/js/aqpx/zxxx/jquery.media.js"></script>
<script type="text/javascript" src="${ctxStatic}/flexpaper/flexpaper.js"></script>

<style type="text/css">
	.up{border-radius: 5px;background: rgb(72, 195, 203);padding: 6.5px 15px;color: white; font-weight: bolder;font-size: 12px;margin: 0px 6px 0 30px;}
	.up:hover{cursor: pointer;border-radius: 5px;border:1px solid rgb(72, 195, 203) ;background: rgb(24, 166, 137);padding: 5.5px 14px;color: white; font-weight: bolder;font-size: 12px;margin: 0px 6px 0 30px; transition: all .5s;}
	.uploadArea{width: 100%;display: flex;align-items: center;margin:20px 0 0 0 }
	.upload{width:32px;height: 32px;margin: 0px 6px 0 30px; background: url('${ctx}/static/model/images/aqpx/zxxx/upload.png') no-repeat center;background-size: 100% 100%;}
	.upload:hover{cursor: pointer;width:32px;height: 32px;margin: 0px 6px 0 30px; background: url('${ctx}/static/model/images/aqpx/zxxx/upload-fill.png') no-repeat center;background-size: 100% 100%;}
	.uname{padding:3px 5px 9px 0;font-size: 13px;font-weight: 450;line-height: 24px}
	.line{overflow:hidden;text-overflow:ellipsis;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;}
	.line2{overflow:hidden;text-overflow:ellipsis;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient:vertical;}
	.fs{flex-shrink:0}
	.ic{width:18px;height: 18px;margin: 0px 0 0 6px;margin-top: -2px}
    .file:hover .ic{width:18px;height: 18px;margin: 0px 0 0 6px; background: url('${ctx}/static/model/images/lod.png') no-repeat center;background-size: 100% 100%;}
	.ic2{width:18px;height: 18px;margin: 0px 0 0 6px;margin-top: -2px}
	.file:hover .ic2{width:18px;height: 18px;margin: 0px 0 0 6px; background: url('${ctx}/static/model/images/del(1).png') no-repeat center;background-size: 100% 100%;}
	.lab{font-size: 12px; color: grey;}
	.fileBg{width:100%; height: 40px;display: flex;align-items: center;justify-content: center;}
	.file {
		width:160px;display:flex;flex-direction:column;margin: 13px 13px;
		border: none;
		box-shadow: 0px 0px 8.82px 0.18px #ccc;
	}
	.file:hover {
		margin-top:5px;
		width: 164px;
		cursor: pointer;
		/*border: 2px solid #30a6f5;
		-moz-box-shadow: 0 0 10px rgba(48, 166, 245, 1);
		-webkit-box-shadow: 0 0 10px rgba(48, 166, 245, 1);*/
		box-shadow: 0px 5px 12px 0 #B8B8B8;
	}
	.file:hover .fileBg {width:100%; height: 118px;}
	.fileName{padding:3px 15px;font-size: 14px;font-weight: bold;color: white;}
	.fileInfo{display: flex;flex-direction:column;padding:5px 8px 7px;}

	.files {
		width: 100%;
        display: flex;
        flex-wrap: wrap;
		/*height: 750px;*/
		margin: 15px 0 0 15px;
	}

.syiconbox {
	background-color: #f5f5f5;
   width:96%;
   height:50px;
}

.syiconbox h5 {
	font-size: 16px;
	margin: 6px 10px;
	padding: 10px;
	border-left: #1e87f2 5px solid;
}
#cap_vds{
	overflow-y: scroll;
	overflow-x: hidden;
	    background: aliceblue;
}
#cap_pager{
	padding: 4px 15px;
	    display: flex;
	        align-items: center;
	border-top: 1px solid #dedede;        
}
		.cap-1{
			width: 260px;
			height: 60px;
			overflow: auto;
			border: none;
		}

	.cap-1::-webkit-scrollbar {
		width: 5px;
		/*高宽分别对应横竖滚动条的尺寸*/
		height: 1px;
	}

	.cap-1::-webkit-scrollbar-thumb {
		/*滚动条里面小方块*/
		border-radius: 5px;
		-webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
		background: #c1c1c1;
	}
	.cap-1::-webkit-scrollbar-track {
		/*滚动条里面轨道*/
		-webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
		border-radius: 5px;
		background: #f5f5f5;
	}
	.content2 {
		font-weight: 500;
		margin-top: 3px;
		font-size: 13px;
	}
</style>
</head>

<script>
    var winHeight = $(window).height();
    $(".files").css("height",winHeight);
    $(window).resize(function() {
        //alert('aaaa');
        winHeight = $(window).height();
        $("#files").css("height",winHeight);
    });
</script>
<body>
	<div class="uploadArea">
		<!--<div class="upload"></div><span>上传文件</span>-->
			<div class="up" onclick="add()"><i class="fa fa-plus"></i> 上传视频</div>
		<form id="searchFrom" action="${ctx}/erm/train-plan/video/index" style="height: 30px;margin-left: 5px" class="form-inline">
			<div class="form-group">
				<input type="text" id="m1" name="m1" class="easyui-textbox" value="${m1 }" style="height: 30px;" data-options="prompt: '标题'" />
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
			</div>
		</form>
	</div>
	<div id="cap_vds" class="files">
	    <c:forEach items="${list}" var="fj" varStatus="status">

		        <div class="file" style="width: 280px;height: 320px" >
		        	<c:set var="urlna" value="${fn:split(fj.m3, '||')}" />

					<%--<div id="output"></div>--%>
                    <a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">
						<video id="video" controls="controls">
							<source src="${urlna[0]}">
						</video>
					<div class='fileBg' style=" background: url(${urlna[0] }) no-repeat center;background-size: 100% 100%;height: 20px" >
                        ${urlna[1]}
					</div>
                    </a>
					<div class="fileInfo" >
						<div style="display: flex;justify-content: space-between;">
							<div class="line uname" style="line-height: 24px">视频主题：${fj.m1}</div>
						</div>
						<div style="display: flex;justify-content: space-between;">
							<div class="line uname cap-1" style="line-height: 48px"><span class="content2">视频简介：${fj.m2}</span></div>
						</div>
						<div style="display: flex;justify-content: space-between;">
							<div class="lab"><fmt:formatDate value="${fj.s1}" pattern="yyyy / MM / dd"/></div>
							<div style="display: flex">
		                        	<div class="ic2 fs" style="margin-left: 10px" onclick="del(${fj.id});"></div>
		                    </div>
						</div>
					</div>
				</div>
		</c:forEach>
	</div>
	<div id="cap_pager">
		<span>总记录数：${page.totalRecords }条</span>
		<span style="margin: 0 10px;">当前第&nbsp;${page.currentPage }&nbsp;页&nbsp;/&nbsp;共&nbsp;${page.totalPages }&nbsp;页</span>
		<a href="${ctx}/erm/train-plan/video/index?pagenum=1">首页</a>
		<a style="margin: 0 10px;" href="${ctx}/erm/train-plan/video/index?pagenum=${page.currentPage-1<1? 1 : page.currentPage-1}">上一页</a>
		<a href="${ctx}/erm/train-plan/video/index?pagenum=${page.currentPage+1<page.totalPages? page.currentPage+1 : page.totalPages}">下一页</a>
		<a style="margin: 0 10px;" href="${ctx}/erm/train-plan/video/index?pagenum=${page.totalPages}">尾页</a>
		    <input type="number" class="form-control" style="width: 50px;" id="num" placeholder="">
		<input id="btn" class="btn btn-default" type="button" style="background-color: #ffffff;color: #666;" value="跳转">
		<input id="pages" value="${page.totalPages }" type="hidden">
	</div>

<script type="text/javascript">
	(function(){
		// cap 设置xia div高度
    	$("#cap_vds").css("height", $(window).height()-$(".uploadArea").height()-$("#cap_pager").height()-46);
		
		var video, output;
		var scale = 0.8;
		var initialize = function() {
			// output = document.getElementById("output");
			video = document.getElementById("video");
			video.addEventListener('loadeddata',captureImage);
		};

		var captureImage = function() {
			var canvas = document.createElement("canvas");
			canvas.width = video.videoWidth * scale;
			canvas.height = video.videoHeight * scale;
			canvas.getContext('2d').drawImage(video, 0,10, canvas.width, canvas.height);

			var img = document.createElement("img");
			img.src = canvas.toDataURL("image/png");
			// output.appendChild(img);
		};
		initialize();
	})();


	$(function(){
		$("#btn").click(function(){
			var pageNum = Number($("#num").val());
			var pages = Number($("#pages").val());
			if(isNaN(pageNum)){
				layer.msg("请输入数字！");
				// alert("请输入数字！");
				return;
			}
			if(pageNum > pages||pageNum<0||pageNum==0){
				layer.msg("输入页码错误！");
				// alert("输入页码错误！");
				return;
			}
			location.href = "${ctx}/erm/train-plan/video/index?pagenum="+pageNum;
		});
	});
</script>
</body>


</html>