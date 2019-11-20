<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<!-- 
	Author : cap
	Date : 19/3/1
 -->
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>人员定位</title>
<link rel="stylesheet" href="${ctxStatic}/fengmap/lib/bootstrap.min.css">
<link rel="stylesheet" href="${ctxStatic}/fengmap/css/style.css">
<link rel="stylesheet" href="${ctxStatic}/model/css/rydw/style.css">
<!-- 引入自定义 css js -->
<link rel="stylesheet" href="${ctxStatic}/fengmap/css/team36.css">
<script type="text/javascript"
	src="${ctxStatic}/fengmap/js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/fengmap/js/team36.js"></script>

<script type="text/javascript" src="${ctxStatic}/model/js/lydw/rydw/index.js"></script>
</head>

<style>
* {
	-webkit-box-sizing: unset;
	-moz-box-sizing: unset;
	box-sizing: unset;
}

.table>tbody>tr>td,.table>tbody>tr>th,.table>tfoot>tr>td,.table>tfoot>tr>th,.table>thead>tr>td,.table>thead>tr>th
	{
	padding: 4px;
	text-align: center;
	font-size: 14px;
}

.typeBtn {
	display: inline-block;
	margin-bottom: 0px;
	font-size: 14px;
	font-weight: 400;
	line-height: 1.42857;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	touch-action: manipulation;
	cursor: pointer;
	user-select: none;
	background-image: none;
	padding: 6px 12px;
	border-width: 1px;
	border-style: solid;
	border-color: transparent;
	border-image: initial;
	border-radius: 4px;
}

.funcCtrlStyle {
	position: absolute;
	top: 15px;
	padding: 0 10px;
	display: flex;
	background: white;
	align-items: center;
	height: 35px;
	border-radius: 3px;
	border: 1px solid #ccc;
	box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.075);
}

.search_chosen {
	background: rgb(102, 175, 233);
	color: white
}

.search1:hover {
	cursor: pointer;
}

.search2:hover {
	cursor: pointer;
}

#searchSpan:hover {
	cursor: pointer;
	background: whitesmoke
}

input:focus {
	outline: none;
	border-color: 0px;
	box-shadow: 0 0 0px rgba(0, 0, 0, 0);
}
</style>
<body class="bd">
	<!-- fun view -->
	<div class="searchView" style="display:none">
		<div class="d1">
			<input id="inp" class="inp" placeholder="搜搜~" onclick="inp_click()"
				onblur="inp_blur(this)" oninput="input()" />
			<div class="close36 icd">
				<img class="closeic" style="display: none;"
					src="${ctxStatic}/fengmap/img/delete.png"
					onmousedown="view_close()" />
			</div>
			</input>

			<div class="road36 icd"></div>
		</div>

		<!--其他弹出内容外层div-->
		<div class="d2">
			<div class="row36 row1"></div>
			<div class="row36 row2"></div>
			<!-- 当input 输入字符时 隐藏前两个row 同时在该row中的ul里添加li，遍历ajax 检索项  -->
			<div class="row36 row3"></div>
			<!-- 小小操控栏 -->
			<div class="row36 row4"></div>
		</div>

	</div>

	<div class="search36 icd" style="display:none" onmousedown="search36()"></div>
	<!--<div class="hidSearch"></div>-->


	<!-- 右侧function bar
     给div 添加焦点事件时 需要添加tabindex 属性 去除焦点虚边线 添加hidefocus 属性
-->
	<div class="funcBar overhid" style="outline:0" tabindex="0"
		hidefocus="true" onblur='funcDivBlur(this)'>
		<div class="funcDiv0">
			<div class="funcDiv"
				style="border-left: 0px solid gainsboro;overflow: unset;">
				<div class="funcName" style="margin:0 6px 0 -2px;overflow: hidden;">
					围栏</div>
				<div class="">
					<select class="fenceSelect1" name="">
						<option value="0">关闭</option>
						<option value="1">第一个</option>
						<option value="2">第二个</option>
						<option value="3">第三个</option>
					</select>
				</div>
			</div>
		</div>

		<div class="funcDiv0">
			<div class="funcDiv">
				<img class="funcIc" src="${ctxStatic}/fengmap/img/Siren.png" /> <img
					class="funcIc2 hid" src="${ctxStatic}/fengmap/img/Siren2.png" />
				<div class="funcName">拉升报警器</div>
			</div>
		</div>

		<div class="funcDiv0">
			<div class="funcDiv">
				<img class="funcIc" src="${ctxStatic}/fengmap/img/alarmbell.png" />
				<img class="funcIc2 hid"
					src="${ctxStatic}/fengmap/img/alarmbell2.png" />
				<div class="funcName">警铃</div>
			</div>
		</div>

		<div class="funcDiv0">
			<div class="funcDiv">
				<img class="funcIc" src="${ctxStatic}/fengmap/img/camera.png" /> <img
					class="funcIc2 hid" src="${ctxStatic}/fengmap/img/camera2.png" />
				<div class="funcName">摄像头</div>
			</div>
		</div>

		<div class="funcDiv0 divBlur" onclick="view_warning()" tabindex="0"
			hidefocus="true" onblur='divBlur(this)'>
			<div class="funcDiv warning">
				<img class="funcIc" src="${ctxStatic}/fengmap/img/msg.png" /> <img
					class="funcIc2 hid" src="${ctxStatic}/fengmap/img/camera2.png" />
				<div class="msgCount">3</div>
				<div class="funcName" style="margin-left: 10px;">警报</div>
				<div class="upIc"></div>
			</div>

			<!-- 警报消息列表 -->
			<div class="warning_list"></div>
		</div>
	</div>


	<!--
    右侧 plural function bar
-->
	<div class="plurBtn" style="display:none">
		<div class="plurIc"></div>
	</div>

	<div class="plurBar overhid" style="display:none">
		<div class="plurItem plurItem1">
			<!--hover 滑出二级菜单-->

			<img class="plurItemIc" src="${ctxStatic}/fengmap/img/area.png">
		</div>

		<div class="plurItem plurItem2">
			<!--hover 滑出二级菜单-->

			<img class="plurItemIc" src="${ctxStatic}/fengmap/img/area.png">
		</div>

		<div class="plurItem plurItem3">
			<!--hover 滑出二级菜单-->

			<img class="plurItemIc" src="${ctxStatic}/fengmap/img/area.png">
		</div>

		<div class="plurItem plurItem4">
			<!--hover 滑出二级菜单-->

			<img class="plurItemIc" src="${ctxStatic}/fengmap/img/area.png">
		</div>

		<div class="plurItem plurItem5">
			<!--hover 滑出二级菜单-->

			<img class="plurItemIc" src="${ctxStatic}/fengmap/img/area.png">
		</div>
	</div>


	<!-- 蜂鸟 map-->
	<div id="fengMap"></div>
	
	<!-- 人员警报提示框-->
	<div class="promt">
		<div class="alert alert-info alert-dismissable hidden" id="polygon-info">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
			<strong>提示：目标点已进入电子围栏范围</strong>
		</div>
	</div>

	<!--  -->
	<div class=""
		style="width: 200px;position:absolute;left:15px;top:15px;display:flex;flex-direction: column;">
		<div class=""
			style="border-radius:4px; border: 1px solid #ccc;width: 198px;display:flex;background:white;overflow: hidden;">
			<div class="search1 search_chosen"
				style="padding:6px 0;margin: 0px;width: 100px;text-align:center;">
				搜建筑</div>
			<div class="search2"
				style="padding:6px 0;margin: 0px;width: 100px;text-align:center;">
				搜人员</div>
		</div>

		<!-- 建筑物搜索框 -->
		<div class="search_1" style="width: 200px;">
			<input type="text" class="form-control"
				style="box-shadow: 0 0 0px rgba(0, 0, 0, 0);outline:none;border-color: #ccc;width: calc(100% - 26px); height: 23px;MARGIN-TOP: -1px;"
				placeholder="建筑物名称.." id="seachText">
			<ul id="hotwords" class="hotwords" style="display:none;width: 198px;">
			</ul>
		</div>

		<!-- 人员搜索 -->
		<div class="search_2"
			style="overflow: hidden;display:none;position:absolute;top:33px;padding:0 10px;border: 1px solid #ccc; box-shadow: inset 0 1px 2px rgba(0,0,0,0.075);background:white;align-items:center;height:35px;border-radius:3px;">
			<input id="input_2"
				style="width:130px; height: 36px;border:0px;outline: none;"
				placeholder="人员名称.." /> <span id="searchSpan"
				style="padding:7px 13px 7px 15px;color:grey;font-size:15px; flex-shrink: 0;"
				onclick="findPerson()">搜索</span>
		</div>
	</div>

	<!-- 分类查询按钮-->
	<div class="typeBtns" style="left: 600px;display:none">
		<button class="typeBtn btn-default" data-id="">员工</button>
		<button class="typeBtn btn-default" data-id="160000">访客</button>
		<button class="typeBtn btn-default" data-id="160000">其他</button>
	</div>

	<!-- 部门selector -->
	<div class="funcCtrlStyle" style="left:228px;display:none">
		<span style="margin-right:5px">部门 </span> <select class="deptSelect"
			name="">
			<option value="0">关闭</option>
			<option value="1">第一个</option>
			<option value="2">第二个</option>
			<option value="3">第三个</option>
		</select>
	</div>

	<!-- 电子围栏selector -->
	<div class="funcCtrlStyle" style="left:228px;/*left:361px;*/">
		<span id="testaa" style="margin-right:5px">显示围栏 </span>
		<select class="fenceSelect" name="">

		</select>
	</div>

	<!--搜索出来的数据显示在table-->
	<div id="table-container" class="scrollbar"
		style="width:calc(100% - 89px);padding: 5px;position: absolute;bottom:0px;background:rgb(255,255,255,1)">
		<div class="title">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
		</div>
		<table class="table table-bordered table-hover" id="table"
			style="margin-bottom: 0px;">
			<thead>
				<tr>
					<th>行号</th>
					<th>建筑Id</th>
					<th>建筑名称</th>
					<th>楼层</th>
					<th>x</th>
					<th>y</th>
					<th>z</th>
				</tr>
			</thead>
			<tbody id="table-body">
			</tbody>
		</table>
	</div>


	<!-- 数据浮窗 -->
	<div class=""
		style="display:flex;flex-direction: column;width:220px;background:rgba(255,255,255,0.9);position:absolute;top:15px;right:15px;border-radius:5px;box-shadow:0 0 8px 0 rgb(0,0,0,.3)">
		<div class=""
			style="width:100%;display:flex;align-items:center;border-bottom:0.1px solid #c1c1c1;">
			<div class="" style="width:50%;padding:8px 0;text-align:center;">
				部门</div>
			<div class="" style="width:50%;padding:5px 0;text-align:center;">
				人员</div>
		</div>
		<div class="" style="width:calc(100% - 10px);padding:5px; max-height: 94px;overflow-y: scroll;">
			<ul id="deptPersonInfo" style="padding-left:0px;font-size:13px;margin-bottom:0px;">

			</ul>
		</div>
	</div>

	<!--模型拾取操作按钮-->
	<div class="operating" style="display:none">
		<button class="btn btn-default">开启模型拾取</button>
		<button class="btn btn-default">关闭模型拾取</button>
	</div>
	<!-- data-backdrop="false" -->
	<div id="dlgModelInfo" class="modal fade">
		<div class="modal-dialog bottom">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">拾取对象类型：</h4>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="row">
							<div class="col-md-4">鼠标点击位置坐标：</div>
							<div class="col-md-8" id="m-info"></div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>


<script src="${ctxStatic}/fengmap/lib/jquery-2.1.4.min.js"></script>
<script src="${ctxStatic}/fengmap/lib/fengmap.min.js"></script>
<script src="${ctxStatic}/fengmap/js/createBubble.js"></script>
<script src="${ctxStatic}/fengmap/lib/bootstrap.min.js"></script>
<script src="${ctxStatic}/fengmap/js/layerGroup.js"></script>
<script type="text/javascript">
//获取版本号,设置title
//document.title = '地图单击事件V' + fengmap.VERSION;

//定义全局map变量
var map;
var fmapID = 'zalk-dlfactory';

//控制文本标注层显示/隐藏
var labelVisible = true;
//控制POI标注层显示/隐藏
var poiVisible = true;
// 楼层id
var groupId = 1;

// 标注
var layer0 = null;
var personLayer = null;

var im = null;
var im2 = null;
var jump;
var markerImg = "";
var personMarkerImg;
var markerHeight = 2;
var markerAnimation = true;
var personMarkerAnimation = true;
//	标注添加的类型：0：初始化生成 ,1：查询生成（需清除之前的marker）,2：查询聚焦（换marker颜色，并跳动）
var addType = 0;

// 人员实时追踪
var count = 0;
var locationMarker = null;

// 	分类查询
var groupLayer;
var searchAnalyser;
var defaultGroupID = 1; //默认显示楼层
var res = [];

//	电子围栏显示
var polygonMarker;
var addPolygon = true;
var addFenceMarker = true;
var moveMarker = true;
var imageFenceMarker = null;
var fenceLayer = null;

// 控制是否弹出信息框
var startPick = false;
//判断当前是否点击的是poi,控制点击公共设施的时候只弹出公共设施的信息框
var clickedPOI = false;
// 点击事件ID
var eventID = null;

var eventID2 = null;
var bubbleObj = null;

// 人员点位数组
var personCoords = null;

// popmarker 对象
var popMarker = null;

// 人员点位数组
var personMarkerInfo = [];

// 人员imagemarker对象map
var markerMap = {};

// 电子围栏数据
var fenceDataList = [];

// 围栏pmmarker对象map
var fenceMap = {};

// 请求点位实时数据的定时器
var updateTimer;

// 是否聚焦
var focus = 1;

window.onload = function() {
	//楼层控制控件配置参数
	var ctlOpt = new fengmap.controlOptions({
		//默认在右上角
		position : fengmap.controlPositon.RIGHT_BOTTOM,
		//默认显示楼层的个数
		showBtnCount : 7,
		//初始是否是多层显示，默认单层显示
		allLayer : true,
		//位置x,y的偏移量
		offset : {
			x : 5,
			y : 55
		},
		imgURL : '${ctxStatic}/fengmap/image/',
	});

	map = new fengmap.FMMap({
		//渲染dom
		container : document.getElementById('fengMap'),
		//地图数据位置
		mapServerURL : '${ctxStatic}/fengmap/data/' + fmapID,

		//主题数据位置
		mapThemeURL : '${ctxStatic}/fengmap/data/theme',
		//设置主题
		defaultThemeName : 'zalk-dlfactory',
		// 默认比例尺级别设置为20级
		defaultMapScaleLevel : 20,
		//两楼层间的高度
		defaultGroupSpace: 20,
		//不支持单击模型高亮，true为单击时模型高亮
		modelSelectedEffect : false,

		//是否对不可见图层启用透明设置 默认为true
		focusAlphaMode : false,
		//对不聚焦图层启用透明设置，当focusAlphaMode = true时有效
		focusAlpha : 0.1,

		//开发者申请应用下web服务的key
		key : '0c5907c2c43a800d7b007941d72126b5',
		//开发者申请应用名称
		appName : 'bh_dlkj',
	});

	//打开Fengmap服务器的地图数据和主题
	map.openMapById(fmapID);

	//禁用浏览器右键事件
	$('body').on("contextmenu", function(event) {
		return false;
	})

	/*
	 *	地图加载完成回调	===	提示搜索方法区
	 */
	map.on('loadComplete', function() {
		var glength = map.groupIDs.length;
		if(map.mapScaleLevel>18){
			for(var i = 0;i < glength;i++){
				var group3 = map.getFMGroup(map.groupIDs[i]);
				//遍历图层
				group3.traverse(function(fm, deep) {
					if (fm instanceof fengmap.FMFacilityLayer) {
						fm.visible = true;
					}
					if (fm instanceof fengmap.FMLabelLayer) {
						fm.visible = true;
					}
				});
			}
		}else{
			for(var i = 0;i < glength;i++){
				var group3 = map.getFMGroup(map.groupIDs[i]);
				//遍历图层
				group3.traverse(function(fm, deep) {
					if (fm instanceof fengmap.FMFacilityLayer) {
						fm.visible = false;
					}
					if (fm instanceof fengmap.FMLabelLayer) {
						fm.visible = false;
					}
				});
			}
		}
		
		//创建楼层(按钮型)，创建时请在地图加载后(loadComplete回调)创建。
		groupControl = new fengmap.scrollGroupsControl(map, ctlOpt);
		//通过楼层切换控件切换聚焦楼层时的回调函数
		//groupContro 即为楼层控件对象
		groupControl.onChange(function(groups, allLayer) {
			//groups 表示当前要切换的楼层ID数组,
			//allLayer表示当前楼层是单层状态还是多层状态。
			groupId = map.focusGroupID;

			//	自动跳转到地图中心点
			map.moveTo({
				//改变中心点x的位置
				x : map.center.x,
				//改变中心点y的位置
				y : map.center.y,
				//默认时间是0.3秒
				time : 1,
				callback : function() {
				}
			});
		});
		
		// 默认楼层加载完后不显示，需自定义设置要显示的楼层和聚焦层
		map.visibleGroupIDs = map.groupIDs;
		map.focusGroupID = map.groupIDs[0];

		// 获取人员点标注  coords
		getPersonCoords();
		
		// 获取部门、人数
		getDeptPersonCount();
		
		// 获取电子围栏信息
		getPolygonMarker();

		//获取搜索类
		searchAnalyser = map.searchAnalyser; 

		// 选择围栏显示
		$('.fenceSelect').change(function() {
			var polygonMarkerId = $(".fenceSelect").val();
			focus = 1;
			// 清空围栏
			removeFences();
			//	显示围栏
			if (polygonMarkerId != -1) {
				if(polygonMarkerId == -2) {
					focus = 0;
					for(var i=0;i<fenceDataList.length;i++){
						addPmMarker(fenceDataList[i].fence,focus);
					}
				}else{
					addPmMarker(fenceDataList[polygonMarkerId].fence,focus);
				}
			}
		});
		
	});


	/**
	 *	地图级别变化事件.比如pc端鼠标滚动放大\缩小事件等.
	 */
	map.on('scaleLevelChanged', function(event) {
		var glength = map.groupIDs.length;
		if(map.mapScaleLevel>18){
			console.log("aa "+map.mapScaleLevel)
			
			for(var i = 0;i < glength;i++){
				var group3 = map.getFMGroup(map.groupIDs[i]);

				//遍历图层
				group3.traverse(function(fm, deep) {
					if (fm instanceof fengmap.FMFacilityLayer) {
						fm.visible = true;
					}
					if (fm instanceof fengmap.FMLabelLayer) {
						fm.visible = true;
					}
				});
			}
			
		}else{
			console.log("bb "+map.mapScaleLevel)
			
			for(var i = 0;i < glength;i++){
				var group3 = map.getFMGroup(map.groupIDs[i]);

				//遍历图层
				group3.traverse(function(fm, deep) {
					if (fm instanceof fengmap.FMFacilityLayer) {
						fm.visible = false;
					}
					if (fm instanceof fengmap.FMLabelLayer) {
						fm.visible = false;
					}
				});
			}
		}
		
	})
	
	
	/**
	 *	基础点击弹出组件信息tip 方法区
	 */
	map.on('mapClickNode', function(event) {
		if (event.nodeType == fengmap.FMNodeType.IMAGE_MARKER) {
			var popmarkerInfo = event.target.name;
			var popmarkerInfoArr = popmarkerInfo.split("_");
			
			if(popMarker != null){
				popMarker.close();
			}
			//信息框控件大小配置
			var ctlOpt1 = new fengmap.controlOptions({
				mapCoord: {
					//设置弹框的x轴
					x: event.target.x,
					//设置弹框的y轴
					y: event.target.y,
					height: 0, //控制信息窗的高度
					//设置弹框位于的楼层
					groupID: event.target.groupID
				},
				//设置弹框的宽度
				width: 170,
				//设置弹框的高度
				height: 80,
				marginTop: 0,
				//设置弹框的内容
				content: '<div class="popMarkerDiv"><div style="height:30px;">'+popmarkerInfoArr[0]+'</div><div style="height:20px;font-size:12px">'+popmarkerInfoArr[1]+' - '+popmarkerInfoArr[2]+'</div><div style="height:20px;font-size:12px">'+popmarkerInfoArr[3]+'</div></div>',
				closeCallBack: function() {
					//信息窗点击关闭操作
					// alert('信息窗关闭了！');
				}
			});

			//添加弹框到地图上
			popMarker = new fengmap.FMPopInfoWindow(map, ctlOpt1);
			//popMarker.close();
			$(".fm-control-popmarker .popMarkerDiv").parent("div").css('height','70px');

			// 标注视野聚焦
			map.moveTo({
				//改变中心点x的位置
				x : event.target.x,
				//改变中心点y的位置
				y : event.target.y,
				groupID: event.target.groupID,
				//默认时间是0.3秒
				time : 1,
				callback : function() {
				}
			});
		}
	});


	$(".search_2").hide();
	$(".search1").click(function() {
			$(this).addClass("search_chosen").siblings()
					.removeClass("search_chosen");
			$(".search_1").show();
			$(".search_2").css('display', 'none');
		})
	$(".search2").click(function() {
		$(this).addClass("search_chosen").siblings()
				.removeClass("search_chosen");
		$(".search_1").hide();
		$(".search_2").css('display', 'flex');
	})
};


/**
 *	获取部门、人数
 */
var deptPersonCount;
function getDeptPersonCount() {
	$.ajax({
		type : 'post',
		url : "${ctx}/lydw/rydw/bmtotaljson",
		success : function(data) {
			//清空ul
			$("#deptPersonInfo li").remove();
			if (data != null) {
				deptPersonCount = jQuery.parseJSON(data);
				var deptPersonCountLength = deptPersonCount.length;
				if (deptPersonCountLength > 0) {
					for (var i = 0; i < deptPersonCountLength; i++) {
						var html = '<li style="display:flex;align-items:center;">';
							html += '<div class="" style="width:50%;padding:5px 0;text-align:center;">'+deptPersonCount[i].bm+'</div>';
							html += '<div class="" style="width:50%;padding:5px 0;text-align:center;">'+deptPersonCount[i].count+'</div>';
							html += '</li>';
					}
					$("#deptPersonInfo").append(html);
				}
			}else{
				var html = '<li style="display:flex;align-items:center;">&nbsp;&nbsp;&nbsp;暂无信息..</li>';
				
				$("#deptPersonInfo").append(html);
			}
		}
	});
}
	

/**
 *	获取人员 personCoords
 */
function getPersonCoords() {
	$.ajax({
		type : 'post',
		url : "${ctx}/lydw/rydw/listjson",
		success : function(data) {
			if (data != null) {
				personCoords = jQuery.parseJSON(data);
				var personCoordsLength = personCoords.length;
				personMarkerImg = 0;
				var addType = 0;
				if (personCoordsLength > 0) {
					for (var i = 0; i < personCoordsLength; i++) {
						var popmarkerInfo = personCoords[i].name+"_"+personCoords[i].bm+"_"+personCoords[i].gw+"_"+personCoords[i].uptime;
						var personCoord = {
							x : personCoords[i].x,
							y : personCoords[i].y,
							z : personCoords[i].z - 1,
							name: personCoords[i].name,
							id : personCoords[i].xbid,
							popmarkerInfo : popmarkerInfo
							
						}
						// 原 person marker  locationmarker生成方法
						//addLocationMarkers(personCoord, personMarkerImg);
						
						// 将该标注点的简略信息存入personMarkerInfo数组中
						personMarkerInfo.push(personCoord);
					}
					
					// 调用 imagemarker批处理生成方法
					if(personMarkerInfo!=null){
						var length = personMarkerInfo.length;
						if(length>0 && length>50){
							addMarkersBatch(0,50,length);
						}
						else if(length>0 && length<=50){
							addMarkersBatch(0,length,length);
						}
					}
					
					//	设置定时器  每隔20秒钟请求一次点位的实时数据
				    updateTimer = window.setInterval(updateMarkers, 20000);
				}
			}
		}
	});
}


/**
 * 	全新人员批处理生成  imageMarker 
 */
function addMarkersBatch(index,batchCount,maxCount) {
	if (index >= maxCount) {
		return;
	}
	for (var i = index; i < (batchCount + index); i++) {
		var newPersonCoord = personMarkerInfo[i];
		var newGroup = map.getFMGroup(map.groupIDs[newPersonCoord.z]);
		
		//返回当前层中第一个imageMarkerLayer,如果没有，则自动创建
		var newLayer = newGroup.getOrCreateLayer('imageMarker');
		
		//图标标注对象，默认位置为该楼层中心点
		var newIm = new fengmap.FMImageMarker({
			x: newPersonCoord.x,
			y:newPersonCoord.y,
			// 设置信息窗内容 
			name: newPersonCoord.popmarkerInfo,
			groupID: newPersonCoord.z + 1,
			height:2,
			ID:newPersonCoord.id,
			//设置图片路径
			url: '${ctxStatic}/fengmap/image/bluePersonMarker.png',
			//设置图片显示尺寸
			size: 24,
			callback: function() {
				index++;
				if (index % batchCount == 0) {
					addMarkersBatch(index,batchCount,maxCount);
				}
				console.log(index % batchCount)
			}
		});
		markerMap[newPersonCoord.id] = newIm;
		//console.log("gid "+newPersonCoord.z);
		
		newLayer.addMarker(newIm);
	}
};


/**
 * 人员ImageMarker 请求数据 实时定位更新
 */
function updateMarkers() {
	$.ajax({
		type : 'post',
		url : "${ctx}/lydw/rydw/listjson",
		success : function(data) {
			if (data != null) {
				personCoords = jQuery.parseJSON(data);
				var personCoordsLength = personCoords.length;
				personMarkerImg = 0;
				if (personCoordsLength > 0) {
					for (var i = 0; i < personCoordsLength; i++) {
						// 在markerMap 中找到相应id 的imagemarker对象，调用moveTo方法，实现实时定位更新
						if(markerMap[personCoords[i].xbid]){//console.log("我移！");
							markerMap[personCoords[i].xbid].moveTo({
								//设置imageMarker的x坐标
								x: personCoords[i].x,
								//设置imageMarker的y坐标
								y: personCoords[i].y,
								// 楼层
								z: personCoords[i].z - 1,
								// moveTo时间设置为6秒,默认为1秒。
								time: 3,
								//判断目标点是否进入围栏区域   personMarker: 人员当前位置
								update: function(personMarker) {
									// 判断PolygonMarker是否包含Marker当前的位置
									var isContained = polygonMarker.contain(personMarker);
									
									//未进入围栏
									if (!isContained) {
										personMarker.url = '${ctxStatic}/fengmap/image/redPeopleImage.png';
									} else {
										personMarker.url = '${ctxStatic}/fengmap/image/bluePeopleImage.png';
									}
								},
								callback: function() {
								},
							});
						}
					}
				}
			}
		}
	});
}


/**
 * 创建定位点标注		暂未调用
 */
function addLocationMarkers(personCoord, personMarkerImg) {
	if (personMarkerImg == 0) {
		personMarkerImg = "bluePersonMarker";
	} else if (personMarkerImg == 1) {
		personMarkerImg = "redPersonMarker";
	} else {
		personMarkerImg = "bluePersonMarker";
	}

	locationMarker = new fengmap.FMLocationMarker({
		//设置图片的路径
		url : '${ctxStatic}/fengmap/image/' + personMarkerImg + '.png',
		//设置图片显示尺寸
		size : 36,
		// 设置信息窗内容 
		name: personCoord.popmarkerInfo,
		//show: false,
		//设置图片高度
		height : 5,
		callback : function() {
			// 设置LocationMarker的，初始方向
			//locationMarker.direction = -90;
		}
	});
	
	map.addLocationMarker(locationMarker);
	
	locationMarker.setPosition({
		//设置定位点的x坐标
		x : personCoord.x,
		//设置定位点的y坐标
		y : personCoord.y,
		//设置定位点所在楼层
		groupID : map.groupIDs[personCoord.z],
		//设置定位点的高于楼层多少
		zOffset : 1,
	});
};


/**
 *	查找人员 跳动显示 点标注 markers
 */
function findPerson() {
    //	禁止瞬时连点
    $("#searchSpan").css('pointer-events','none');
	// 解除禁止连点
    var noclickingsTimer = window.setTimeout(noclickings, 500);
    function noclickings() {
    	$("#searchSpan").css('pointer-events','unset');
    	//去掉定时器的方法  
        window.clearTimeout(noclickingsTimer);
    }
    
	var personInpVal = $("#input_2").val();
	if (personInpVal != "" && personInpVal != "人员姓名..") {
		// 清空之前的点标注
		removeImageMarkers();
		
		// 除去地图元素高亮
		map.selectNull();
		
		// 关闭之前打开的pop marker
		if(popMarker != null){
			popMarker.close();
		}
		
		for(var i = 0; i < personMarkerInfo.length; i++){
			if(personMarkerInfo[i].name == personInpVal){
				var queryMarker = personMarkerInfo[i];
				
				var group2 = map.getFMGroup(map.groupIDs[queryMarker.z]);

				//返回当前层中第一个imageMarkerLayer,如果没有，则自动创建
				personLayer = group2.getOrCreateLayer('imageMarker');
				
				//添加图片标注层到模型层。否则地图上不会显示(实际上 加不加都行- -)
				group2.addLayer(personLayer);
				
				im2 = new fengmap.FMImageMarker({
					x : queryMarker.x,
					y : queryMarker.y,
					height : 4,
					url : '${ctxStatic}/fengmap/image/redImageMarker.png',
					size : 36,
					callback : function() {
						im2.alwaysShow();
						if (markerAnimation) {
							jump = im2.jump({
								times : 0,
								duration : 1,
								delay : 0.5,
								height : 6
							});
						}
					}
				});
				personLayer.addMarker(im2);

				// 标注视野聚焦
				map.moveTo({
					//改变中心点x的位置
					x : queryMarker.x,
					//改变中心点y的位置
					y : queryMarker.y,
					groupID: queryMarker.z + 1,
					//默认时间是0.3秒
					time : 1,
					callback : function() {
					}
				});
				
				break;
			}
		}
	}
}


// 清除imageMarker 对象
function removeImageMarkers() {
	if (personLayer != null && im2 != null) {
		personLayer.removeMarker(im2);
	}
	if (layer0 != null && im!=null) {
		layer0.removeMarker(im); alert(1)
	}
	
	// 搜索table隐藏
	oTable_container.style.display = 'none';
}


/**
 *	创建电子围栏
 */
function getPolygonMarker(polygonMarkerId) {
	//polygonMarkerId ?
    $.ajax({
        type:'post',
        url:"${ctx}/lydw/dzwl/dzwllist",
        success: function(data){ 
        	$(".fenceSelect option").remove();
         	// 生成电子围栏option
        	var html = '<option value="-2">显示全部</option><option value="-1">不显示</option>';
        	
        	var data = $.parseJSON(data);
            if(data.length > 0 && data != null){
                for (var i = 0; i < data.length; i++) {
                	var index = i;
                	 var fenceData = [];
                     var ponits = data[i].mappoint.split('||');
                     for (var j = 0; j < ponits.length; j++) {
                         var p = ponits[j].split(',');
                         
                         var coord = {
                             x: p[0],
                             y: p[1],
                             z: p[2]
                         }
                         fenceData.push(coord);
                     }
                     var fencesData = {
                    	fence: fenceData,
                    	fenceId: data[i].id
                     }
                     fenceDataList.push(fencesData);
                     html += '<option value="'+index+'">'+data[i].name+'</option>';
                 }
                
                focus = 0;
				for(var i=0;i<fenceDataList.length;i++){
					addPmMarker(fenceDataList[i].fence,fenceDataList[i].fenceId,focus);
				}
            }
            $(".fenceSelect").append(html);
        }
    });
}


// 真正想地图中添加电子围栏的方法
function addPmMarker(fenceData,fenceId,focus) {
	var gid = fenceData[0].z - 1;
    var group = map.getFMGroup(map.groupIDs[gid]);

    //返回当前层中第一个polygonMarker,如果没有，则自动创建
    fenceLayer = group.getOrCreateLayer('polygonMarker');

    polygonMarker = new fengmap.FMPolygonMarker({
        //设置透明度
        alpha: .5,
        //设置边框线的宽度
        lineWidth: 1,
        //设置高度
        height: 2,
        //设置多边形坐标点
        points: fenceData
    });

    fenceLayer.addMarker(polygonMarker);
	
    // 将该电子围栏对象polygonMarker 存入fenceMap 集合中备用
    fenceMap[fenceId] = polygonMarker;
    
    
    if(focus!=0){
    	// 标注视野聚焦
    	map.moveTo({
    		//改变中心点x的位置
    		x : fenceData[0].x,
    		//改变中心点y的位置
    		y : fenceData[0].y,
    		groupID: fenceData[0].z,
    		//默认时间是0.3秒
    		time : 0.3,
    		callback : function() {
    		}
    	});
    }
}


/**
 *  清空所有楼层的围栏
 */
function removeFences(){
	// 真正想地图中添加电子围栏的方法
	for(var i = 0; i<5;i++){
	    var group = map.getFMGroup(map.groupIDs[i]);

	    //返回当前层中第一个polygonMarker,如果没有，则自动创建
	    var fenceLayer_ = group.getOrCreateLayer('polygonMarker');
	    fenceLayer_.removeAll();
	}
}


/**
 *	原生搜索框  + table
 */
var oSeachText = document.querySelector('#seachText');
var oTable_container = document.querySelector('#table-container');
var oHotwords = document.querySelector('#hotwords');

$("body").click(function(e) {
	if (e.target.tagName == "CANVAS") {
		$(oHotwords).hide();
	}
});

//根据FID查询
function findCoordinate(fid) {
	oHotwords.style.display = 'none';
	var searchReq = new fengmap.FMSearchRequest(
			fengmap.FMNodeType.MODEL); //设置查询地图元素类型
	searchReq.FID(fid);
	searchAnalyser.query(searchReq, function(request, result) {
		//result 为查询到的结果集。
		var models = result[0];
		if (models != null) {
			filldata(models);
			oTable_container.style.display = 'block';
		} else {
			oTable_container.style.display = 'none';
		}
	});
};

// 原生搜索 table添加Markers
function addMarkers(gid, X, Y, markerImg) {
	// removeMarkers();
	removeImageMarkers();
	
	var group = map.getFMGroup(map.groupIDs[gid - 1]);

	//返回当前层中第一个imageMarkerLayer,如果没有，则自动创建
	layer0 = group.getOrCreateLayer('imageMarker');

	if (markerImg == 0) {
		markerImg = "bluePersonMarker";
		markerHeight = 2;
		markerAnimation = false;
	} else if (markerImg == 1) {
		markerImg = "redPersonMarker";
		markerHeight = 2;
		markerAnimation = false;
	} else {
		markerImg = "blueImageMarker";
		markerHeight = 8;
	}

	im = new fengmap.FMImageMarker({
		x : X,
		y : Y,
		height : markerHeight,
		url : '${ctxStatic}/fengmap/image/' + markerImg + '.png',
		size : 36,
		callback : function() {
			im.alwaysShow();
			if (markerAnimation) {
				jump = im.jump({
					times : 0,
					duration : 1,
					delay : 0.5,
					height : 10
				});
			}
		}
	});
	layer0.addMarker(im);
};


//定位跳转
function moveTo(coord) {
	map.focusGroupID = coord.groupID;
	map.moveTo(coord);
};

//删除Marker
function removeMarkers() {
	//获取多楼层Marker
	map.callAllLayersByAlias('imageMarker', function(layer0) {
		layer0.removeAll();
	});
};

//根据关键字查询店铺
oSeachText.addEventListener('keyup', function(e) {
	var keyword = this.value.trim();
	if (keyword !== '搜索' && keyword !== '') {
		var searchReq = new fengmap.FMSearchRequest(
				fengmap.FMNodeType.MODEL);
		searchReq.keyword(keyword);
		searchAnalyser.query(searchReq, function(request, result) {
			var models = result;
			if (models != null && models.length > 0) {
				oHotwords.style.display = 'block';
				listDate(models);
			} else {
				oTable_container.style.display = 'none';
				oHotwords.style.display = 'none';
			}
		});
	} else {
		oHotwords.style.display = 'none';
		$(oTable_container).hide();
	}
});

//创建提示下拉框
var listDate = function(data) {
	oHotwords.innerHTML = '';
	var li = '';
	for ( var i in data) {
		var model = data[i];
		li += '<li data-id="' + model.FID + '">'
				+ (!model.name ? "空" : model.name) + '</li>';
	}

	oHotwords.innerHTML = li;
	var aLi = oHotwords.getElementsByTagName('li');
	for (var i = 0; i < aLi.length; i++) {
		(function(i) {
			aLi[i].onclick = function(e) {
				e.stopPropagation(); //阻止冒泡到body
				var index = i;
				oSeachText.value = this.innerHTML;
				var fid = this.attributes["data-id"].nodeValue;
				findCoordinate(fid);
				oHotwords.style.display = 'none';
			}
		})(i);
	}
}


//	填充表格数据
function filldata(model) {
	var oTable = document.querySelector('#table');
	var oTableBody = document.querySelector('#table-body');
	var aTr = oTable.getElementsByTagName('tr');
	oTableBody.innerHTML = '';

	markerImg = 2;
	addMarkers(model.groupID, model.mapCoord.x, model.mapCoord.y,markerImg);

	var coord = {
		x : model.mapCoord.x,
		y : model.mapCoord.y,
		groupID : model.groupID
	};
	moveTo(coord); //定位跳转

	//地图上当前设置的模型元素处于高亮状态。
	//如果最后一个参数设置为true,storeSelect(model,true,true)，表示之前的和当前的模型都处于高亮转态。可使用map.selectNull()方法清除。
	map.storeSelect(model, true, false);
	oTableBody.innerHTML = '<tr class="active"><td style="padding: 4px;text-align:center">1</td><td>'
			+ (model.ID == undefined ? "空" : model.ID)
			+ '</td><td>'
			+ (!model.name ? "空" : model.name)
			+ '</td><td>'
			+ model.groupID
			+ '</td><td>'
			+ model.mapCoord.x
			+ '</td><td>'
			+ model.mapCoord.y
			+ '</td><td>' + model.mapCoord.z + '</td></tr>';

	for (var i = 0; i < aTr.length; i++) {
		(function(i) {
			aTr[i].onclick = function() {
				var index = i;
				if (index <= 0)
					return;
				var x = parseFloat(this.cells[4].innerText);
				var y = parseFloat(this.cells[5].innerText);
				var z = parseFloat(this.cells[6].innerText);
				var gid = parseInt(this.cells[3].innerText);
				var id = parseInt(this.cells[1].innerText);
				var index = parseInt(this.cells[0].innerText);

				var coord = {
					x : x,
					y : y,
					z : z,
					groupID : gid
				};
				moveTo(coord); //定位跳转

				map.storeSelect(model, true, false);
			}
		})(i);
	}
}

// table 关闭按钮
var oTitle = document.querySelectorAll('.title')[0];
oTitle.onclick = function() {
	oTable_container.style.display = 'none';
};
</script>

</body>
</html>
