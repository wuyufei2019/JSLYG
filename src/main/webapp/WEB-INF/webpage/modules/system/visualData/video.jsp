<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>连云区公共安全监管（应急指挥）云平台</title>
    <link rel="stylesheet" href="${ctxStatic}/model/visualData/css/base.css">
    <link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
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
            <li><i class="nav_2"></i><a href="${ctx}/bis/qyjbxx/onemap2">应急大数据</a></li>
        </ul>
    </div>
    <div class="header_center left" style="position:relative">
        <h2><strong>连云区应急指挥平台大数据</strong></h2>
    </div>
    <div class="right nav text_right">
        <ul> <%--<li><i class="nav_11"></i><a href="${ctx}/a/vd_page5">重大危险源</a> </li>--%>
            <li class="nav_active"><i class="nav_6"></i><a href="${ctx}/a/vd_page8">视频监控</a></li>
            <li><i class="nav_3"></i><a href="${ctx}/">进入平台</a></li>
        </ul>
    </div>
</header>

<!--内容部分-->
<div class="con1 left" id="car_control">
    <div class="left car_left">
        <div class="left_up bow_shadow">
            <%-- <p>
                 <input type="text" placeholder="输入单位名称" class="carNo_input"><input type="button" class="find_but"/>
             </p>
     --%>
            <p class="set_list"><i class="list_i"></i> 设备列表：</p>
            <p>
            <ul id="treeDemo" class="ztree" style="overflow: scroll; height: calc(100% - 90px);"></ul>
            </p>
        </div>
        <%--<div class="left_down bow_shadow">
          &lt;%&ndash;  <div class="text_center"><a href="javascript:void (0)" class="tab_a tab_aActive">基本信息</a><a
                    href="javascript:void (0)" class="tab_a">云台控制</a></div>&ndash;%&gt;
            &lt;%&ndash;<div class="car_content">
            </div>&ndash;%&gt;
        </div>--%>
    </div>
    <div class="left car_center" style="width:81.5%">
        <iframe id="content" frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no
                src=""></iframe>
        <%--<video controls="controls"></video>
        <video controls="controls" class="magin_left"></video>
        <video controls="controls" class="magin_top"></video>
        <video controls="controls" class="magin_top magin_left"></video>--%>
    </div>
    <div class="right car_right" id="map" style="display:none"></div>
</div>
<script>
    var ctx = '${ctx}';
</script>

<script src="${ctxStatic}/jquery/jquery-3.3.1.min.js"></script>
<script src="${ctxStatic}/model/visualData/js/Plugin/echarts.min.js"></script>
<script src="${ctxStatic}/model/visualData/js/base.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=5ieMMexWmzB9jivTq6oCRX9j&callback"></script>
<script src="${ctxStatic}/model/visualData/js/car_control.js"></script>
<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.js"></script>
<script src="${ctxStatic}/model/visualData/js/listTree.js"></script>
</body>
</html>