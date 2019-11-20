<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>物料实时监测数据</title>
    <meta name="decorator" content="default"/>
</head>
<body class="gray-bg">
<div id="tabs" class="easyui-tabs" fit="true">
    <div title="私有课件" style="height:100%;" data-options="">
        <iframe id="priv" frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no ></iframe>
    </div>
    <div title="公共课件" style="height:100%;" data-options="">
        <iframe id="pub" frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no ></iframe>
    </div>
</div>
<script>
    $(function () {
        $('#priv').attr('src', "${ctx}/aqpx/kjkgl/index-priv");
        $('#tabs').tabs({
            onSelect: function (title) {
                if (title == "私有课件") {
                    $('#priv').attr('src', "${ctx}/aqpx/kjkgl/index-priv");
                } else if (title == "公共课件") {
                    $('#pub').attr('src', "${ctx}/aqpx/kjkgl/index-pub");
                }
            }
        });
    });

</script>
</body>
</html>