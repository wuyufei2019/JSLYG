<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>连云区公共安全监管（应急指挥）云平台</title>
    <link rel="stylesheet" href="${ctxStatic}/bootstrap/3.3.4/css_default/bootstrap.min.css">
    <link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic}/model/visualData/js/jQueryPage/src/jquery.page.css">
    <link rel="stylesheet" href="${ctxStatic}/model/visualData/css/base.css">
</head>
<style>
    .header_center h2 {
        margin-top: 16px !important;
        font-size: 22px !important;
    }
</style>
<body>
<!--顶部-->
<header class="header left">
    <div class="left nav">
        <ul>
            <li><i class="nav_1"></i><a href="${ctx}/a/vd_page">总体概况</a></li>
            <li><i class="nav_7"></i><a href="${ctx}/a/vd_page3">分类统计</a></li>

        </ul>
    </div>
    <div class="header_center left" style="position:relative">
        <h2><strong>连云区应急指挥平台大数据</strong></h2>
    </div>
    <div class="right nav text_right">
        <ul>
            <li class="nav_active"><i class="nav_2"></i><a href="${ctx}/bis/qyjbxx/onemap2">应急大数据</a></li>
            <li style="display:none"><i class="nav_11"></i><a href="${ctx}/a/vd_page5">重大危险源</a></li>
            <li style=""><i class="nav_6"></i><a href="${ctx}/a/vd_page8">视频监控</a></li>
            <li><i class="nav_3"></i><a href="${ctx}/">进入平台</a></li>
        </ul>
    </div>
</header>

<!--内容部分-->
<div class="map_con left" id="car_control">
    <!--左侧地图-->
    <div class="left map_left">
        <div class="map_box" id="map_box">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${ctx}/bis/qyjbxx/onemap2"
                    frameborder="0" data-id="${ctx}/bis/qyjbxx/onemap2" seamless></iframe>
        </div>
        <div class="map_top" style="display: none;">
            <ul>
                <li class="active"><i class="glyphicon glyphicon-pencil"></i><a href="javascript:void (0)"
                                                                                class="active">图层选择</a></li>
                <li><i class="glyphicon glyphicon-zoom-in"></i><a href="javascript:void (0)">缩小</a></li>
                <li><i class="glyphicon glyphicon-zoom-out"></i><a href="javascript:void (0)">放大</a></li>
                <li><i class="glyphicon glyphicon-screenshot"></i><a href="javascript:void (0)">对比</a></li>
                <li><i class="glyphicon glyphicon-search"></i><a href="javascript:void (0)">搜索</a></li>
                <li><i class="glyphicon glyphicon-wrench"></i><a href="javascript:void (0)">工具</a></li>
                <li><i class="glyphicon glyphicon-fullscreen"></i><a href="javascript:void (0)">全屏</a></li>
            </ul>
        </div>
        <div class="map_select">
            <p><input type="checkbox"><span>人员</span></p>
            <p><input type="checkbox"><span>车辆</span></p>
            <p><input type="checkbox"><span>房屋</span></p>
            <p><input type="checkbox"><span>摄像头</span></p>
        </div>

    </div>

    <!--右侧功能栏-->
    <div class="right map_right ">
        <div class="map_right_top">
            <ul>
                <li class="li_active">预警信息</li>
                <li id="cgxx">分类搜索</li>
                <li>专题信息</li>
            </ul>
        </div>
        <div class="map_con">
            <div class="map_con_div" style="display: block">
                <div class="map_chart">

                    <p id="char1" class="p_chart"></p>
                </div>
                <div class="div_any_child">
                    <!--<div class="div_any_title"><img src="img/title_4.png">行驶时长排名前5位 </div>-->
                    <div class="table_p">
                        <table>
                            <thead>
                            <tr>
                                <th>排名</th>
                                <th>车牌号</th>
                                <th>时长（km）</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>1</td>
                                <td>京A12345</td>
                                <td>134.2</td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>京A12345</td>
                                <td>134.2</td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>京A12345</td>
                                <td>134.2</td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>京A12345</td>
                                <td>134.2</td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>京A12345</td>
                                <td>134.2</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="map_con_div">
                <p>
                    <input type="text" placeholder="输入关键字" class="carNo_input"><input type="button" class="find_but"/>
                </p>
                <div class="map_work">
                    <ul>
                        <li>
                            <div class="img_div img_div01"></div>
                            <div class="img_div_text">便民服务</div>
                        </li>
                        <li>
                            <div class="img_div img_div02"></div>
                            <div class="img_div_text">便民服务</div>
                        </li>
                        <li>
                            <div class="img_div img_div01"></div>
                            <div class="img_div_text">便民服务</div>
                        </li>
                        <li>
                            <div class="img_div img_div01"></div>
                            <div class="img_div_text">便民服务</div>
                        </li>
                        <li>
                            <div class="img_div img_div01"></div>
                            <div class="img_div_text">便民服务</div>
                        </li>
                        <li>
                            <div class="img_div img_div01"></div>
                            <div class="img_div_text">便民服务</div>
                        </li>
                        <li>
                            <div class="img_div img_div01"></div>
                            <div class="img_div_text">便民服务</div>
                        </li>
                        <li>
                            <div class="img_div img_div01"></div>
                            <div class="img_div_text">便民服务</div>
                        </li>
                        <li>
                            <div class="img_div img_div01"></div>
                            <div class="img_div_text">便民服务</div>
                        </li>
                    </ul>
                    <div class="map_resList">
                        <p class="set_list"><i class="list_i"></i> 查询结果：<span class="right"><i
                                class="glyphicon glyphicon-backward back_i"></i><a href="javascript:void(0)"
                                                                                   onclick="back()">返回 </a> </span></p>
                        <ul>
                            <li><p><strong>爱尚美术馆</strong><span class="right"><a href="javascript:void(0)"
                                                                                title="视频"><img
                                    src="img/nav_5.png"> </a><a href="javascript:void(0)" title="定位"><img
                                    src="img/find01.png"> </a> </span></p>
                                <p>四川省成都市高新区北美一路31号</p></li>
                            <li><p><strong>爱尚美术馆</strong><span class="right"><a href="javascript:void(0)"
                                                                                title="视频"><img
                                    src="img/nav_5.png"> </a><a href="javascript:void(0)" title="定位"><img
                                    src="img/find01.png"> </a> </span></p>
                                <p>四川省成都市高新区北美一路31号</p></li>
                            <li><p><strong>爱尚美术馆</strong><span class="right"><a href="javascript:void(0)"
                                                                                title="视频"><img
                                    src="img/nav_5.png"> </a><a href="javascript:void(0)" title="定位"><img
                                    src="img/find01.png"> </a> </span></p>
                                <p>四川省成都市高新区北美一路31号</p></li>
                            <li><p><strong>爱尚美术馆</strong><span class="right"><a href="javascript:void(0)"
                                                                                title="视频"><img
                                    src="img/nav_5.png"> </a><a href="javascript:void(0)" title="定位"><img
                                    src="img/find01.png"> </a> </span></p>
                                <p>四川省成都市高新区北美一路31号</p></li>
                            <li><p><strong>爱尚美术馆</strong><span class="right"><a href="javascript:void(0)"
                                                                                title="视频"><img
                                    src="img/nav_5.png"> </a><a href="javascript:void(0)" title="定位"><img
                                    src="img/find01.png"> </a> </span></p>
                                <p>四川省成都市高新区北美一路31号</p></li>
                        </ul>
                        <div id="page"></div>
                    </div>
                </div>
            </div>
            <div class="map_con_div">
                <div class=" bow_shadow">
                    <p>
                        <input type="text" placeholder="输入关键字" class="carNo_input"><input type="button"
                                                                                          class="find_but"/>
                    </p>

                    <p class="set_list"><i class="list_i"></i> 专题列表：</p>
                    <p>
                    <ul id="treeDemo" class="ztree"></ul>
                    </p>
                </div>
            </div>

        </div>

    </div>

</div>
<script src="${ctxStatic}/jquery/jquery-3.3.1.min.js"></script>
<script src="${ctxStatic}/model/visualData/js/base.js"></script>
<script src="${ctxStatic}/model/visualData/js/echarts-all.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=5ieMMexWmzB9jivTq6oCRX9j&callback"></script>
<script src="${ctxStatic}/model/visualData/js/jQueryPage/src/jquery.page.js"></script>
<script src="${ctxStatic}/model/visualData/js/map_control.js"></script>
<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.js"></script>
<script src="${ctxStatic}/model/visualData/js/listTree.js"></script>
</body>
</html>