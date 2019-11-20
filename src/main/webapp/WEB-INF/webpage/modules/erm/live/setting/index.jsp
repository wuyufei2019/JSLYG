<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>物料实时监测数据</title>
    <meta name="decorator" content="default"/>
</head>
<body class="gray-bg">
<div id="tabs" class="easyui-tabs" fit="true">
    <div title="政府直播间" style="height:100%;" data-options="">
        <iframe id="zf" frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no></iframe>
    </div>
    <div title="企业直播间" style="height:100%;" data-options="">
        <iframe id="qy" frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no></iframe>
    </div>
    <div title="公共直播间" style="height:100%;" data-options="">
        <iframe id="pub" frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no></iframe>
    </div>
</div>
<script type="text/javascript">
    //防止页面多次加载
    $(function () {
        $('#zf').attr('src', "${ctx}/yjjy/live/setting/index2?type=0");
        $('#tabs').tabs({
            onSelect: function (title) {
                if (title == "政府直播间") {
                    $('#zf').attr('src', "${ctx}/yjjy/live/setting/index2?type=0");
                } else if (title == "企业直播间") {
                    $('#qy').attr('src', "${ctx}/yjjy/live/setting/index2?type=1");
                }else if (title == "公共直播间") {
                    $('#pub').attr('src', "${ctx}/yjjy/live/setting/index2?type=2");
                }
            }
        });
    });
</script>
</body>
</html>