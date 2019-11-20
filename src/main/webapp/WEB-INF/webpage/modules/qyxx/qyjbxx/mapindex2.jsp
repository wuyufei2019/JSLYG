<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title></title>
    <meta name="decorator" content="default"/>
    <script src="${ctxStatic}/model/visualData/js/Plugin/echarts.min.js"></script>
    <script type="text/javascript" src="http://developer.baidu.com/map/custom/stylelist.js"></script>
    <style type="text/css">
        html, body {
            margin: 0;
            padding: 0;
        }

        .datagrid-row-alt {
            background: rgb(65, 138, 230);
            color: white
        }

        .datagrid-row-alt {
            background: rgb(65, 138, 230);
            color: white
        }

        .datagrid-header, .datagrid-td-rownumber {
            background-color: #3c0f0f;
            background: -webkit-linear-gradient(top, #418ae6 0, #1b3553 100%);
            background: -moz-linear-gradient(top, #418ae6 0, #1b3553 100%);
            background: -o-linear-gradient(top, #418ae6 0, #1b3553 100%);
            background: linear-gradient(to bottom, #418ae6 0, #1b3553 100%);
            background-repeat: repeat-x;
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#F9F9F9, endColorstr=#efefef, GradientType=0);
        }

        table {
            background-color: #183675;
            color: white
        }

        .datagrid .datagrid-pager {
            background: #3a4a5a;
            color: white
        }

        ::-webkit-scrollbar-track {
            background-color: #1e496f;
        }

        .datagrid-htable, .datagrid-btable, .datagrid-ftable {
            color: #ffffff;
            border-collapse: separate;
        }

        .datagrid-header-row {
            background: #0d253a
        }

        .btn-group label {
            margin-right: 5px !important;
        }

        #dituContent {
            height: 100%;
            width: 100%;
        }

        .BMap_cpyCtrl {
            display: none;
        }

        .anchorBL {
            display: none;
        }

        #dituContent label {
            max-width: none;
        }

        .boxx2 .list-inline {
            margin-top: 10px;
            float: left
        }

        .boxx2 .list-inline li {
            width: 47%;
            height: 40px;
            line-height: 40px;
            margin-left: 9px;
            margin-bottom: 10px;
            padding-left: 0px;
            overflow: hidden;
            white-space: nowrap;
            margin-bottom: 10px;
        }

        .boxx2 .list-inline li a {
            text-decoration: none;
            height: 45px;
            line-height: 45px;
            padding-left: 5px;
            display: inline-block;
        }

        .boxx2 .list-inline li i {
            display: inline-block;
            width: 44px;
            height: 44px;
            font-size: 24px;
            padding-left: 12px;
            line-height: 45px;
            color: #fff;
            margin: auto 0;
        }

        .boxx2 .list-inline li .iblue {
            background-color: #69B6EA;
            float: left;
        }

        .boxx2 .list-inline li .igreen {
            background-color: #91D33E;
            float: left;
        }

        .boxx2 .list-inline .blue {
            background-color: #D2E8F6;
        }

        .boxx2 .list-inline .green {
            background-color: #DFEECD;
        }

        .boxx2 span {
            font: 14px/16px "微软雅黑";
        }

        .container-fluid {
            height: 97%;
            padding-right: 5px;
        }

        .btn_cap {
            margin: 10px 0;
            max-width: 800px;
            position: absolute;
            z-index: 999;
            background: url(/JJXQ/static/model/visualData/img/zuo2.png);
            background-size: 100% 100%;
            padding: 10px;
        }

        .btn-primary {
            background-color: #1823758a;
            border-color: #0dadc1;
            color: #FFFFFF;
        }

        .btn-info {
            background-color: #00a3de;
            border-color: #0dadc1;
            color: #FFFFFF;
        }

        .floatCard {
            width: 300px;
            height: 234px;
            background: url(${ctx}/static/model/visualData/img/zuo2.png);
            background-size: 100% 100%;
            position: absolute;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .floatCard:hover {
            cursor: pointer;
        }

        .floatCard.card1 {
            background-size: 100% 100%;
            bottom: 264px;
        }

        .cardtitle {
            padding: 5px 15px;
            background: #418ae6;
            margin: 12px 0 8px;
            box-shadow: 0 0 8px 1px #71c5d0 inset;
            /* border-radius: 4px; */
            color: white;
        }

        .cardbody {
            display: flex;
            flex-wrap: wrap;
            padding: 0 10px 10px;
        }

        .cardblock_t {
            text-align: center;
            font-size: 14px;
            color: white;
            font-weight: 600;
            padding: 8px 0 5px;
        }

        .card3 .cardblock_t {
            text-align: center;
            font-size: 14px;
            color: white;
            font-weight: 600;
            padding: 7px 0 4px;
        }

        .cardblock_b img {
            width: 24px
        }

        .cardblock_b span {
            font-size: 14px;
            font-weight: 400;
            color: white;
        }

        .card3 .cardblock_b span {
            font-size: 12px;
            font-weight: 400;
            color: white;
        }

        .card3 .cardblock_b img {
            width: 18px;
            height: 18px;
            margin: 2px 0 4px;
        }

        .cardCblock {
            width: 29.3%;
            margin: 2%;
        }

        .cardblock_b {
            display: flex;
            justify-content: space-between;
            padding: 10px 20px;
        }

        .card3 .cardblock_b {
            display: flex;
            justify-content: space-between;
            padding: 0px 5px;
        }

        .grad {
            background: -webkit-linear-gradient(#07223878, #3939ff85); /* Safari 5.1 - 6.0 */
            background: -o-linear-gradient(#07223878, #3939ff85); /* Opera 11.1 - 12.0 */
            background: -moz-linear-gradient(#07223878, #3939ff85); /* Firefox 3.6 - 15 */
            background: linear-gradient(#07223878, #3939ff85); /* 标准的语法 */
        }

        .floatCard.card2 {
            background-size: 100% 100%;
            bottom: 20px;
        }

        .card2 .cardCblock {

        }

        .floatCard.card3 {
            background-size: 100% 100%;
            left: 322px;
            bottom: 20px;
        }

        .floatCard.card4 {
            background-size: 100% 100%;
            right: 10px;
            bottom: 264px
        }

        .bs-example {
            padding: 10px;
            margin-right: 0;
            margin-left: 0;
            background-color: #283f53;
            border: #0f6acf 1px solid;
            border-radius: 5px;
            -webkit-box-shadow: none;
            box-shadow: none;
            height: 100%;
        }

        .datagrid-header, .datagrid-toolbar, .datagrid-pager, .datagrid-footer-inner {
            border-color: #293d7b;
        }

        #weather > div {
            width: 100%;
            color: white;
            font-size: 14px;
            padding: 5px;
        }

        .header {
            width: 100%;
            height: 80px;
            background-color: #030829;
        }

        .left {
            float: left;
        }

        .nav {
            width: 35%;
        }

        .nav > ul > li {
            display: inline-block;
            width: 120px;
            text-align: center;
            height: 50px;
            position: relative;
            line-height: 50px;
            margin-top: 15px;
            box-sizing: border-box;
            border-radius: 5px;
        }

        .nav > ul > li:hover {
            border-bottom: 4px solid #4b8df8;
            box-shadow: -10px 0px 15px #034c6a inset, /*å·¦è¾¹é˜´å½±*/ 0px -10px 15px #034c6a inset, /*ä¸Šè¾¹é˜´å½±*/ 10px 0px 15px #034c6a inset, /*å³è¾¹é˜´å½±*/ 0px 10px 15px #034c6a inset;
            box-sizing: border-box;
        }

        .nav > ul > li i {
            width: 16px;
            height: 16px;
            display: inline-block;
            position: relative;
            top: 3px;
            margin-right: 5px;
        }

        .nav_1 {
            background: url(${ctx}/static/model/visualData/img/nav_1.png);
        }

        .nav > ul > li > a {
            color: #ffffff;
            /* display: inline-block; */
            /* padding: 0 15px 0 5px; */
            font-size: 14px;
        }

        .nav_active {
            border-bottom: 4px solid #4b8df8;
            box-shadow: -10px 0px 15px #034c6a inset, /*å·¦è¾¹é˜´å½±*/ 0px -10px 15px #034c6a inset, /*ä¸Šè¾¹é˜´å½±*/ 10px 0px 15px #034c6a inset, /*å³è¾¹é˜´å½±*/ 0px 10px 15px #034c6a inset;
            box-sizing: border-box;
        }

        .nav li > a {
            display: unset;
            /* white-space: nowrap; */
        }

        .nav_4 {
            background-image: url(${ctx}/static/model/visualData/img/nav_4.png);
        }

        .header_center {
            width: 30%;
            margin: 0px auto;
            color: #FFFFff;
            text-align: center;
            height: 80px;
            background-image: url(${ctx}/static/model/visualData/images/logoBg.png);
            background-size: 100% 100%;
            font-family: "微软雅黑" !important;
        }

        .header_center h2 {
            margin-top: 16px !important;
            font-size: 22px !important;
        }

        .text_right {
            text-align: right;
        }

        .right {
            float: right;
        }

        .nav_6 {
            background-image: url(${ctx}/static/model/visualData/img/nav_6.png);
        }

        .nav_3 {
            background-image: url(${ctx}/static/model/visualData/img/nav_3.png);
        }

        .bs-example {
            height: calc(100% - 80px);
        }

    </style>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/GeoUtils/1.2/src/GeoUtils_min.js"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/model/js/qyxx/qyjbxx/mapindex.js?v=2.0"></script>
    <script type="text/javascript" src="${ctxStatic}/model/js/qyxx/qyjbxx/datagrid.js?v=1.2"></script>
    <link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.css"/>
    <link href="${ctxStatic}/model/css/home/styles.css" rel="stylesheet"/>
</head>
<body>
<!--顶部-->
<header class="header left">
    <div class="left nav">
        <ul style="padding-left: 0px;">
            <li><i class="nav_1"></i><a href="${ctx}/a/vd_page">总体概况</a></li>
             <li><i class="nav_7"></i><a href="${ctx}/a/vd_page3">分类统计</a> </li>

        </ul>
    </div>
    <div class="header_center left" style="position:relative">
        <h2><strong>连云区公共安全监管（应急指挥）云平台</strong></h2>
    </div>
    <div class="right nav text_right">
        <ul>

            <li class="nav_active"><i class="nav_3"></i><a href="${ctx}/bis/qyjbxx/onemap2">应急大数据</a></li>
            <li style="display:none"><i class="nav_11"></i><a href="${ctx}/a/vd_page5">重大危险源</a></li>
            <li style=""><i class="nav_5"></i><a href="${ctx}/a/vd_page8">视频监控</a></li>
            <li><i class="nav_3"></i><a href="${ctx}/">进入平台</a></li>
        </ul>
    </div>
</header>

<div class="container-fluid bggrey" style=" padding: 0;margin: 0;height: 100%;">
    <div class="toptips mgt5 mgb10"></div>
    <div id="mapcontent" class="col-lg-12 bs-example"
         style="margin-right:0px;border-width:0px;height: calc(100% - 70px);">
        <div class="col-lg-12" style="width:100%;display: flex;justify-content: center;position: absolute; 
        bottom: 0px;top: 0;left: 0px;padding-left:0px;padding-right:0px">
            <div class="btn_cap">
                <span id="btngroup">
                    <button type="button" class="btn btn-primary btn-danger btn-xs" id="qyfb">企业分布</button>
                    <button type="button" class="btn btn-primary btn-xs" id="fxxx">风险点信息</button>
                    <button type="button" class="btn btn-primary btn-xs" id="cgxx">储罐信息</button>
                    <button type="button" class="btn btn-primary btn-xs" id="spjk">视频监控</button>
                    <button type="button" class="btn btn-primary btn-xs" id="fxyt">固有风险云图</button>
                    <button type="button" class="btn btn-primary btn-xs" id="zdzd">两重点一重大分布图</button>
                    <button type="button" class="btn btn-primary btn-xs" id="wgfx">网格风险分布图</button>
                    <button type="button" class="btn btn-primary btn-xs" id="yjdw">应急队伍</button>
                    <button type="button" class="btn btn-primary btn-xs" id="yjzb">应急装备</button>
                    <button type="button" class="btn btn-primary btn-xs" id="yjwz">应急物资</button>
                    <button type="button" class="btn btn-primary btn-xs" id="bncs">避难场所</button>
  <%--                  <button type="button" class="btn btn-primary btn-xs" id="ryfb">人员分布</button>
                    <button type="button" class="btn btn-primary btn-xs" id="wxzy">每日危险作业分布图</button>
                    <button type="button" class="btn btn-primary btn-xs" id="cbs">每日承包商分布图</button>
                    <button type="button" class="btn btn-primary btn-xs" id="whpcl">危化品车辆</button>--%>
                </span>
                <span style="margin:5px auto;">
                    <input id="keyword" type="text" class="easyui-combobox" style="height: 25px;width:120px"
                           data-options="valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>

                    <button type="button" class="btn btn-info btn-xs" onclick="searchAll('keyword')">搜索
                    </button>
                    <button type="button" class="btn btn-info btn-xs" onclick="resetAll()">全部数据</button>
                </span>
            </div>
            <%-- <div id="dituContent"></div>--%>
            <div class="bs-example" id="dituContent" style="border: #0f6acf 0px solid;border-radius: 0px;"></div>
        </div>

        <!-- 悬浮卡 -->
        <div class="boxx2 col-lg-4  bs-example" style="height:234px;float: right;width: calc(100% - 650px);background-color: #283f53d1;
    border: #0f6acf 1px solid;box-shadow:0px 0px 6px 0px #424242;overflow-y:scroll;bottom:20px;right:10px;position:absolute">
            <div class="cardtitle" style="margin: 0px;width: 82px;margin-left:calc(50% - 41px)">企业分布</div>
            <table id="mapdatagrid" style="height: calc(100% - 30px);"></table>
        </div>

        <div class="floatCard card1">
            <!-- weather 天气插件 -->
            <div id="weather-view-he"></div>
            <!--
	    	<div class="cardtitle">基础信息</div>
	    	<div class="cardbody">
	    		<div class="cardCblock grad">
	    			<div class="cardblock_t">企业</div>
	    			<div class="cardblock_b">
						<img src="${ctx}/static/model/visualData/img/ic1.png">
						<span>56</span>
					</div>
		    	</div>
		    	<div class="cardCblock grad">
	    			<div class="cardblock_t">重大危险源</div>
	    			<div class="cardblock_b">
						<img src="${ctx}/static/model/visualData/img/ic2.png">
						<span>25</span>
					</div>
		    	</div>
		    	<div class="cardCblock grad">
	    			<div class="cardblock_t">视频监控</div>
	    			<div class="cardblock_b">
						<img src="${ctx}/static/model/visualData/img/ic3.png">
						<span>36</span>
					</div>
		    	</div>
		    	<div class="cardCblock grad">
	    			<div class="cardblock_t">风险点</div>
	    			<div class="cardblock_b">
						<img src="${ctx}/static/model/visualData/img/ic4.png">
						<span>115</span>
					</div>
		    	</div>
		    	<div class="cardCblock grad">
	    			<div class="cardblock_t">危化品车辆</div>
	    			<div class="cardblock_b">
						<img src="${ctx}/static/model/visualData/img/ic5.png">
						<span>91</span>
					</div>
		    	</div>
		    	<div class="cardCblock grad">
	    			<div class="cardblock_t">网格点</div>
	    			<div class="cardblock_b">
						<img src="${ctx}/static/model/visualData/img/ic6.png">
						<span>46</span>
					</div>
		    	</div>
	    	</div>-->
        </div>


        <div class="floatCard card2">
            <div class="cardtitle">应急资源</div>
            <div class="cardbody">
                <div class="cardCblock grad" style="width: 46%;">
                    <div class="cardblock_t">应急队伍</div>
                    <div class="cardblock_b">
                        <img src="${ctx}/static/model/visualData/img/ic7.png">
                        <span>56</span>
                    </div>
                </div>
                <div class="cardCblock grad" style="width: 46%;">
                    <div class="cardblock_t">应急物资</div>
                    <div class="cardblock_b">
                        <img src="${ctx}/static/model/visualData/img/ic8.png">
                        <span>25</span>
                    </div>
                </div>
                <div class="cardCblock grad">
                    <div class="cardblock_t">应急装备</div>
                    <div class="cardblock_b">
                        <img src="${ctx}/static/model/visualData/img/ic9.png">
                        <span>36</span>
                    </div>
                </div>
                <div class="cardCblock grad">
                    <div class="cardblock_t">应急场所</div>
                    <div class="cardblock_b">
                        <img src="${ctx}/static/model/visualData/img/ic10.png">
                        <span>115</span>
                    </div>
                </div>
                <div class="cardCblock grad">
                    <div class="cardblock_t">人员管理</div>
                    <div class="cardblock_b">
                        <img src="${ctx}/static/model/visualData/img/ic11.png">
                        <span>115</span>
                    </div>
                </div>
            </div>
        </div>


        <div class="floatCard card3">
            <div class="cardtitle">警告信息</div>
            <div class="cardbody">
                <div class="cardCblock grad" style="width: 46%;">
                    <div class="cardblock_t">实时储罐</div>
                    <div class="cardblock_b">
                        <span>设备总数</span>
                        <span>506</span>
                        <img src="${ctx}/static/model/visualData/img/123.png">
                    </div>
                    <div class="cardblock_b">
                        <span>设备总数</span>
                        <span>436</span>
                        <img src="${ctx}/static/model/visualData/img/123.png">
                    </div>
                </div>
                <div class="cardCblock grad" style="width: 46%;">
                    <div class="cardblock_t">实时储罐</div>
                    <div class="cardblock_b">
                        <span>设备总数</span>
                        <span>356</span>
                        <img src="${ctx}/static/model/visualData/img/123.png">
                    </div>
                    <div class="cardblock_b">
                        <span>设备总数</span>
                        <span>656</span>
                        <img src="${ctx}/static/model/visualData/img/123.png">
                    </div>
                </div>
                <div class="cardCblock grad" style="width: 46%;">
                    <div class="cardblock_t">实时储罐</div>
                    <div class="cardblock_b">
                        <span>设备总数</span>
                        <span>504</span>
                        <img src="${ctx}/static/model/visualData/img/123.png">
                    </div>
                    <div class="cardblock_b">
                        <span>设备总数</span>
                        <span>256</span>
                        <img src="${ctx}/static/model/visualData/img/123.png">
                    </div>
                </div>
                <div class="cardCblock grad" style="width: 46%;">
                    <div class="cardblock_t">实时储罐</div>
                    <div class="cardblock_b">
                        <span>设备总数</span>
                        <span>262</span>
                        <img src="${ctx}/static/model/visualData/img/123.png">
                    </div>
                    <div class="cardblock_b">
                        <span>设备总数</span>
                        <span>654</span>
                        <img src="${ctx}/static/model/visualData/img/123.png">
                    </div>
                </div>
            </div>
        </div>

        <div class="floatCard card4">
            <div class="cardtitle">基础信息统计</div>
            <div class="cardbody2" style="width: 85%;">
                <div id="pieChart1" style="height: 176px;"></div>

                <!--
	    		<div id="weather">
	    			<img style="margin: 0 16px 0 30px;" src="${ctxStatic}/model/visualData/img/tq.png" width="100"/>
	    			<span style="font-size:24px;font-weight:600;color:white" class="blue"><span>20°</span><span>27°</span></span>
	    		</div>
				<div id="weather" style="display:block">
					<div style="background: #9db2ca70;"><span class="blue">星期日</span><img width="26" style="margin: 0 16px 0 100px;" src="${ctxStatic}/model/visualData/img/tq.png"/><span class="blue">21°</span><span>28°</span></div>
					<div><span class="blue">星期一</span><img width="26" style="margin: 0 16px 0 100px;" src="${ctxStatic}/model/visualData/img/tq2.png"/><span class="blue">19°</span><span>26°</span></div>
					<div style="background: #9db2ca70;"><span class="blue">星期二</span><img width="26" style="margin: 0 16px 0 100px;" src="${ctxStatic}/model/visualData/img/tq3.png"/><span class="blue">23°</span><span>31°</span></div>
				</div>-->
            </div>
        </div>
    </div>
</div>

<div id="select_type" style="display:none;height:350px">
    <table id="areadata"></table>
</div>
<div id="zdzdtab" class="easyui-tabs" data-options="fit:true"
     style="width:850px;height:auto;text-align: center;display:none;">
</div>

<!-- 天气插件 -->
<script>
    WIDGET = {ID: 'cpzQXGV2gi'};
</script>
<script type="text/javascript" src="https://apip.weatherdt.com/view/static/js/r.js?v=1111"></script>

<script type="text/javascript">
    var ctx = '${ctx}';
    var mappoint = '${mappoint}';
    var MAP_TYPE = "qyfb";//类型全局变量
    var BIS_R = []; //红
    var BIS_O = [];//橙
    var BIS_Y = [];//黄
    var BIS_B = [];//蓝
    initMap("dituContent");

    var mapStyle = {
        features: ["road", "building", "water", "land"],//隐藏地图上的"poi",
        style: 'midnight',
    };
    map.setMapStyle(mapStyle);


    addZoomListener();

    if ('${disablescroll}' == 'yes')//首页和一张图的禁用滚轮设置
        map.disableScrollWheelZoom();
    var Global_Search_Data = [];//后台返回map点数据，搜索用

    $(function () {
        initChart();

        var primarywidth = $("#mapcontent").width();
        $("#btngroup button").click(function () {
            $("#btngroup button").removeClass("btn-danger");
            $(this).addClass("btn-danger");
        });
        $("#rightcont").click(function () {
            $(".boxx2").fadeToggle();
            $("#mapcontent").width($(".bggrey").width() - 30);
            $("#leftcont").toggle();
        });
        $("#leftcont").click(function () {
            $("#mapcontent").width(primarywidth);
            $(".boxx2").toggle();
            $("#leftcont").toggle();
        });

        getQyfbJson();
        loadMapData_qyfb();

        function getData(url) {
            var d1;
            $.ajax({
                type: "POST",
                //url : ctx + "/bis/qyjbxx/maplist/",
                url: ctx + url,
                async: false,
                dataType: 'json',
                success: function (data) {
                    d1 = data;
                }
            });
            return d1;
        }

        $("#fxxx").click(function () {
            MAP_TYPE = 'fxxx';
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    $("#titletext").text("风险点信息");
                    getQyfbJson();
                    loadMapData_qyfb();
                }
            });
            $.jBox.closeTip();
        });
        $("#cgxx").click(function () {
            MAP_TYPE = 'cgxx';
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    Global_Search_Data = getData("/bis/cgxx/cgmapjson");
                    setSearchData(Global_Search_Data);
                    if (Global_Search_Data != undefined) {
                        $("#titletext").text("储罐信息");
                        reloadMap(Global_Search_Data);// 创建和初始化地图
                        loadMapData_cgxx();
                    }
                }
            });
            $.jBox.closeTip();
        });
        $("#spjk").click(function () {
            MAP_TYPE = 'spjk';
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    Global_Search_Data = getData("/zxjkyj/spjk/qymaplist");
                    setSearchData(Global_Search_Data);
                    if (Global_Search_Data != undefined) {
                        //markerArr = eval(Global_Search_Data);
                        $("#titletext").text("视频监控");
                        reloadMap(Global_Search_Data);// 创建和初始化地图
                        loadMapData_spjk();
                    }
                }
            });
            $.jBox.closeTip();
        });

        $("#qyfb").click(function () {
            MAP_TYPE = 'qyfb';
            addZoomListener();
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    $("#titletext").text("企业分布");
                    getQyfbJson();
                    loadMapData_qyfb();
                }
            });
            $.jBox.closeTip();
        });

        $("#fxyt").click(function () {
            MAP_TYPE = 'fxyt';
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    map.clearOverlays();
                    createPolygon();
                    loadHeatMap(getData("/fxgk/fxfb/showfxyt"), 900);
                }
            });
            $.jBox.closeTip();
        });
        $("#zdzd").click(function () {
            MAP_TYPE = 'zdzd';
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    Global_Search_Data = getData("/fxgk/fxfb/showzdzd");
                    setSearchData(Global_Search_Data);
                    if (Global_Search_Data) {
                        $("#titletext").html("两重点一重大信息<i class='fa fa-circle' style='color:#ffd075;'>重点监管</i><i class='fa fa-circle' style='color:#e57e20;'>高危工艺</i><i class='fa fa-circle' style='color:#ed2d2d;'>重大危险源</i>");
                        reloadMap(Global_Search_Data);
                        loadMapData_zdzd();
                    }
                }
            });
            $.jBox.closeTip();
        });
        $("#wgfx").click(function () {
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    MAP_TYPE = "wgfx";
                    map.clearOverlays();
                    var mapData = getData("/fxgk/fxfb/barrocolor");
                    var data = mapData.list;
                    var colorlist = [];
                    var len = data.length;
                    var max = mapData.max;
                    var min = mapData.min;
                    var dif = max - min;
                    var dif2 = dif / 2;
                    var everyd = 255 / dif2;
                    var startRGB = colorRgb('#00ff00');//绿色,先变前两位00
                    startR = startRGB[0];
                    startG = startRGB[1];
                    startB = startRGB[2];
                    var endRGB = colorRgb('#ff0000');
                    endR = endRGB[0];
                    endG = endRGB[1];
                    endB = endRGB[2];
                    colorlist.push('#00ff00');
                    for (var i = 1; i < len - 1; i++) {
                        var count = data[i].COUNT;
                        if ((count - min) <= dif2) {
                            startR = parseInt(everyd * (count - min));
                            colorlist.push("rgb(" + startR + "," + startG + "," + startB + ")");
                        } else {
                            endG = parseInt(everyd * (max - count));
                            colorlist.push("rgb(" + endR + "," + endG + "," + endB + ")");
                        }
                    }
                    colorlist.push('#ff0000');
                    createPolygons(data, colorlist);
                    loadMapData_wgfx(data, colorlist);
                    $("#titletext").text("网格风险分布");
                }
            });
            $.jBox.closeTip();
        });
        $("#yjdw").click(function () {
            MAP_TYPE = 'yjdw';
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    Global_Search_Data = getData("/erm/yjdw/maplist/").data;
                    setSearchData(Global_Search_Data);
                    if (Global_Search_Data != undefined) {
                        reloadMap(Global_Search_Data);// 创建和初始化地图
                        loadMapData_yj(Global_Search_Data);
                        $("#titletext").text("应急队伍");
                    }
                }
            });
            $.jBox.closeTip();
        });
        $("#yjzb").click(function () {
            MAP_TYPE = 'yjzb';
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    Global_Search_Data = getData("/erm/yjzb/maplist/1").data;
                    setSearchData(Global_Search_Data);
                    if (Global_Search_Data != undefined) {
                        reloadMap(Global_Search_Data);// 创建和初始化地图
                        loadMapData_yj(Global_Search_Data);
                        $("#titletext").text("应急装备");
                    }
                }
            });
            $.jBox.closeTip();
        });
        $("#yjwz").click(function () {
            MAP_TYPE = 'yjwz';
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    Global_Search_Data = getData("/erm/yjwz/maplist/1").data;
                    setSearchData(Global_Search_Data);
                    if (Global_Search_Data != undefined) {
                        reloadMap(Global_Search_Data);// 创建和初始化地图
                        loadMapData_yj(Global_Search_Data);
                        $("#titletext").text("应急物资");
                    }
                }
            });
            $.jBox.closeTip();
        });
        $("#bncs").click(function () {
            MAP_TYPE = 'bncs';
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    Global_Search_Data = getData("/erm/bncs/maplist").data;
                    setSearchData(Global_Search_Data);
                    if (Global_Search_Data != undefined) {
                        reloadMap(Global_Search_Data);// 创建和初始化地图
                        loadMapData_yj(Global_Search_Data);
                        $("#titletext").text("避难场所");
                    }
                }
            });
            $.jBox.closeTip();
        });
        $("#ryfb").click(function () {
            MAP_TYPE = 'ryfb';
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    Global_Search_Data = [{
                        title: "许宏斌",
                        pointx: "120.940578",
                        pointy: "31.901546",
                        address: "南通瑞翔新材料有限公司",
                        contact: "13951319311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "徐洪芬",
                        pointx: "120.962555",
                        pointy: "31.855715",
                        address: "南通天和树脂有限公司",
                        contact: "13951312311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "徐章兴",
                        pointx: "120.969061",
                        pointy: "31.847652",
                        address: "欧区爱铸造材料（中国）有限公司",
                        contact: "13951322311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "侯杰",
                        pointx: "120.956532",
                        pointy: "31.833934",
                        address: "中化南通石化储运有限公司",
                        contact: "15231322311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "朱晓峰",
                        pointx: "120.960352",
                        pointy: "31.860037",
                        address: "江苏宝灵化工股份有限公司",
                        contact: "13941322311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "赵锋",
                        pointx: "120.95324",
                        pointy: "31.839281",
                        address: "南通嘉民港储有限公司",
                        contact: "16951322311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "高建荣",
                        pointx: "120.970786",
                        pointy: "31.865247",
                        address: "朗盛高新材料(南通)有限公司",
                        contact: "14551322311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "夏海平",
                        pointx: "120.959156",
                        pointy: "31.863819",
                        address: "台橡宇部(南通)化学工业有限公司",
                        contact: "17651322311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "熊建增",
                        pointx: "120.952649",
                        pointy: "31.85606",
                        address: "南通千红石化港储有限公司",
                        contact: "12451322311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "范裕兴",
                        pointx: "120.964698",
                        pointy: "31.834141",
                        address: "江苏王子制纸有限公司",
                        contact: "19051322311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "孙振",
                        pointx: "120.933294",
                        pointy: "31.91191",
                        address: "凡特鲁斯特种化学品（南通）有限公司",
                        contact: "14551322311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "顾宏伟",
                        pointx: "120.962095",
                        pointy: "31.865825",
                        address: "台橡（南通）实业有限公司",
                        contact: "15451322311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "陶军国",
                        pointx: "120.971643",
                        pointy: "31.86747",
                        address: "迈图高新材料（南通）有限公司",
                        contact: "13955522311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "丁钧",
                        pointx: "120.971152",
                        pointy: "31.845773",
                        address: "日立化成工业（南通）化工有限公司",
                        contact: "17751322311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "吴增飞",
                        pointx: "120.974766",
                        pointy: "31.839617",
                        address: "万洲石化（江苏）有限公司",
                        contact: "13951323331",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "刘灿明",
                        pointx: "121.031085",
                        pointy: "31.823144",
                        address: "上海振华重工集团（南通）传动机械有限公司",
                        contact: "16551311311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "徐惠明",
                        pointx: "120.947771",
                        pointy: "31.864263",
                        address: "惠生(南通)重工有限公司",
                        contact: "17833322311",
                        icon: "/static/model/images/map/person.png"
                    }, {
                        title: "沈骐",
                        pointx: "120.936052",
                        pointy: "31.90183",
                        address: "南通中集安瑞科食品装备有限公司",
                        contact: "1251322311",
                        icon: "/static/model/images/map/person.png"
                    }
                    ];
                    setSearchData(Global_Search_Data);
                    if (Global_Search_Data != undefined) {
                        reloadMap(Global_Search_Data);// 创建和初始化地图
                        loadMapData_ryfb(Global_Search_Data);
                        $("#titletext").text("人员分布");
                    }
                }
            });
            $.jBox.closeTip();
        });
        $("#wxzy").click(function () {
            MAP_TYPE = 'wxzy';
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    Global_Search_Data = getData("/cbsgl/cngg/wxzymapjson");
                    setSearchData(Global_Search_Data);
                    if (Global_Search_Data != undefined) {
                        reloadMap(Global_Search_Data);// 创建和初始化地图
                        loadMapData_wxzy();
                        /*$("#titletext").html("危险作业<i class='fa fa-circle' style='color:#ed2d2d;'>动火11家</i>" +
                            "<i class='fa fa-circle' style='color:#e67f21;'>登高4家</i>" +
                            "<i class='fa fa-circle' style='color:#ffd075;'>有限空间3家</i>" +
                            "<i class='fa fa-circle' style='color:#07A0FD;'>外来方施工2家</i>" +
                            "<i class='fa fa-circle' style='color:#1ab394;'>动土0家</i>");*/
                    }
                }
            });
            $.jBox.closeTip();
        });
        $("#cbs").click(function () {
            MAP_TYPE = 'cbs';
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    Global_Search_Data = getData("/cbsgl/cngg/cbsmapjson");
                    setSearchData(Global_Search_Data);
                    if (Global_Search_Data != undefined) {
                        reloadMap(Global_Search_Data);// 创建和初始化地图
                        loadMapData_cbs();
                    }
                }
            });
            $.jBox.closeTip();
        });
        $("#whpcl").click(function () {
            MAP_TYPE = 'whpcl';
            $("#keyword").combobox("setValue", "");
            $.jBox.tip("请稍等...", 'loading', {
                opacity: 0,
                loaded: function () {
                    Global_Search_Data = [{
                        pointx: "120.940578",
                        pointy: "31.901546",
                        title: "南通瑞翔新材料有限公司",
                        address: "南通瑞翔新材料有限公司",
                        count: "3",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.962555",
                        pointy: "31.855715",
                        address: "南通天和树脂有限公司",
                        title: "南通天和树脂有限公司",
                        count: "3",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.969061",
                        pointy: "31.847652",
                        title: "欧区爱铸造材料（中国）有限公司",
                        address: "欧区爱铸造材料（中国）有限公司",
                        count: "4",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.956532",
                        pointy: "31.833934",
                        title: "中化南通石化储运有限公司",
                        address: "中化南通石化储运有限公司",
                        count: "3",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.960352",
                        pointy: "31.860037",
                        title: "江苏宝灵化工股份有限公司",
                        address: "江苏宝灵化工股份有限公司",
                        count: "5",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.95324",
                        pointy: "31.839281",
                        title: "南通嘉民港储有限公司",
                        address: "南通嘉民港储有限公司",
                        count: "1",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.970786",
                        pointy: "31.865247",
                        title: "朗盛高新材料(南通)有限公司",
                        address: "朗盛高新材料(南通)有限公司",
                        count: "2",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.959156",
                        pointy: "31.863819",
                        title: "台橡宇部(南通)化学工业有限公司",
                        address: "台橡宇部(南通)化学工业有限公司",
                        count: "3",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.952649",
                        pointy: "31.85606",
                        title: "南通千红石化港储有限公司",
                        address: "南通千红石化港储有限公司",
                        count: "3",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.964698",
                        pointy: "31.834141",
                        title: "江苏王子制纸有限公司",
                        address: "江苏王子制纸有限公司",
                        count: "4",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.933294",
                        pointy: "31.91191",
                        title: "凡特鲁斯特种化学品（南通）有限公司",
                        address: "凡特鲁斯特种化学品（南通）有限公司",
                        count: "3",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.962095",
                        pointy: "31.865825",
                        title: "台橡（南通）实业有限公司",
                        address: "台橡（南通）实业有限公司",
                        count: "3",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.971643",
                        pointy: "31.86747",
                        title: "迈图高新材料（南通）有限公司",
                        address: "迈图高新材料（南通）有限公司",
                        count: "1",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.971152",
                        pointy: "31.845773",
                        title: "日立化成工业（南通）化工有限公司",
                        address: "日立化成工业（南通）化工有限公司",
                        count: "2",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.974766",
                        pointy: "31.839617",
                        title: "万洲石化（江苏）有限公司",
                        address: "万洲石化（江苏）有限公司",
                        count: "1",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "121.031085",
                        pointy: "31.823144",
                        title: "上海振华重工集团（南通）传动机械有限公司",
                        address: "上海振华重工集团（南通）传动机械有限公司",
                        count: "3",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.947771",
                        pointy: "31.864263",
                        title: "惠生(南通)重工有限公司",
                        address: "惠生(南通)重工有限公司",
                        count: "1",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.936052",
                        pointy: "31.90183",
                        title: "南通中集安瑞科食品装备有限公司",
                        address: "南通中集安瑞科食品装备有限公司",
                        count: "2",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.986012",
                        pointy: "31.99163",
                        title: "南通金钛特种钢有限公司",
                        address: "南通金钛特种钢有限公司",
                        count: "1",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }, {

                        pointx: "120.976002",
                        pointy: "31.91173",
                        title: "南通祥电力机械有限",
                        address: "南通祥电力机械有限",
                        count: "3",
                        icon: "/static/model/images/ead/yjjc/i_yjzb.png"
                    }
                    ];
                    setSearchData(Global_Search_Data);
                    if (Global_Search_Data != undefined) {
                        reloadMap(Global_Search_Data);// 创建和初始化地图
                        loadMapData_whpcl(Global_Search_Data);
                        $("#titletext").html("危化品车辆");
                    }
                }
            });
            $.jBox.closeTip();
        });

    });


    var nameLabels = [];

    function addZoomListener() {
        map.addEventListener("zoomend", zoomfun);
        map.addEventListener("moveend", zoomfun);
    }

    function zoomfun() {
        if (MAP_TYPE == 'qyfb') {
            if (map.getZoom() > 16) {
                var bounds = map.getBounds();
                $.each(BIS_R, function (index, value) {
                    var point = new BMap.Point(value.pointx, value.pointy);
                    if (BMapLib.GeoUtils.isPointInRect(point, bounds)) {
                        var opts = {
                            position: point,
                            offset: new BMap.Size(5, -10)    //设置文本偏移量
                        }
                        var label = new BMap.Label(value.title, opts);  // 创建文本标注对象
                        label.setStyle({"border-color": "red"});
                        map.addOverlay(label);
                        nameLabels.push(label);
                    }
                });
                $.each(BIS_O, function (index, value) {
                    var point = new BMap.Point(value.pointx, value.pointy);
                    if (BMapLib.GeoUtils.isPointInRect(point, bounds)) {
                        var opts = {
                            position: point,
                            offset: new BMap.Size(5, -10)     //设置文本偏移量
                        }
                        var label = new BMap.Label(value.title, opts);  // 创建文本标注对象
                        label.setStyle({"border-color": "orange"});
                        map.addOverlay(label);
                        nameLabels.push(label);
                    }
                });
                $.each(BIS_Y, function (index, value) {
                    var point = new BMap.Point(value.pointx, value.pointy);
                    if (BMapLib.GeoUtils.isPointInRect(point, bounds)) {
                        var opts = {
                            position: point,
                            offset: new BMap.Size(5, -10)    //设置文本偏移量
                        }
                        var label = new BMap.Label(value.title, opts);  // 创建文本标注对象
                        label.setStyle({"border-color": "yellow"});
                        map.addOverlay(label);
                        nameLabels.push(label);
                    }
                });
                $.each(BIS_B, function (index, value) {
                    var point = new BMap.Point(value.pointx, value.pointy);
                    if (BMapLib.GeoUtils.isPointInRect(point, bounds)) {
                        var opts = {
                            position: point,
                            offset: new BMap.Size(5, -10)     //设置文本偏移量
                        }
                        var label = new BMap.Label(value.title, opts);  // 创建文本标注对象
                        label.setStyle({"border-color": "blue"});
                        map.addOverlay(label);
                        nameLabels.push(label);
                    }
                });
            } else {
                $.each(nameLabels, function (index, value) {
                    map.removeOverlay(value);
                });
                nameLabels = [];
            }
        }
    }

    function searchAll() {
        search();
        if (MAP_TYPE == 'qyfb' || MAP_TYPE == 'fxxx') {
            search_qyfb();
        } else if (MAP_TYPE == 'cgxx') {
            search_cgxx();
        } else if (MAP_TYPE == 'spjk') {
            search_spjk();
        } else if (MAP_TYPE == 'zdzd') {
            search_zdzd();
        } else if (MAP_TYPE == 'wxzy') {
            search_wxzy();
        } else if (MAP_TYPE == 'cbs') {
            search_cbs();
        }
    }

    function resetAll() {
        reset();
        if (MAP_TYPE == 'zdzd') {
            reset_zdzd();
        } else if (MAP_TYPE == 'wgfx') {
            $("#wgfx").click();
        } else
            reset_qyfb();
    }

    // 渲染基础信息饼图
    function initChart() {
        var pieChart1 = echarts.init(document.getElementById('pieChart1'));
        pieChart1.setOption({
            color: ["#4690E4", "#B6E969", "#DC143C", "#FFFF00", "#FF7F50", "#DB1ECD",],

            legend: {
                y: '132',
                x: 'center',
                textStyle: {
                    color: '#ffffff',

                },
                data: ['企业', '视频监控', '危化品车辆', '风险点', '重大危险源', '危化品车辆', '网格点'],
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a}<br/>{b}<br/>{c}个 ({d}%)"
            },
            calculable: false,
            series: [
                {
                    name: '基础分类',
                    type: 'pie',
                    radius: ['40%', '70%'],
                    center: ['50%', '38%'],
                    itemStyle: {
                        normal: {
                            label: {
                                show: false
                            },
                            labelLine: {
                                show: false
                            }
                        },
                        emphasis: {
                            label: {
                                show: true,
                                position: 'center',
                                textStyle: {
                                    fontSize: '12',
                                    fontWeight: 'bold'
                                }
                            }
                        }
                    },
                    data: [
                        {value: 2652, name: '企业'},
                        {value: 24, name: '视频监控'},
                        {value: 234, name: '危化品车辆'},
                        {value: 13, name: '重大危险源'},
                        {value: 21025, name: '风险点'},
                        {value: 2651, name: '网格点'}
                    ]
                }
            ]
        });
    }

    $(document).ready(function () {
        $(".boxx2 .datagrid .datagrid-wrap>.datagrid-view").css("height", "calc(100% - 30px)");
        $(".boxx2>.datagrid>.datagrid-wrap").css("181px");
        $(".boxx2 .datagrid").css("height", "calc(100% - 30px)");
        $(".datagrid-wrap.panel-body.panel-body-noheader.panel-body-noborder").css("height", "calc(100% - 0px)");
        $(".datagrid-wrap.panel-body.panel-body-noheader.panel-body-noborder .pagination-num").css("color", "black");
        $(".datagrid-wrap.panel-body.panel-body-noheader.panel-body-noborder .pagination-page-list").css("color", "black");
        $(".datagrid-wrap.panel-body.panel-body-noheader.panel-body-noborder .datagrid-pager").css("border-radius", "0px");
        var aa = $("#weaItemsWraper #wea-items .second").text();
        console.log(aa);
    })
</script>
</body>
</html>