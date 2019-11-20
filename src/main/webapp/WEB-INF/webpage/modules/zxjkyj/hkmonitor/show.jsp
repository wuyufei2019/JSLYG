<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>视频监控</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic}/ckplayer/ckplayer.js"></script>
</head>

<body>
<div style="text-align:center;margin: 0 auto;height: 100%;">

    <div class="easyui-panel" id="content" style="height:100%;display: flex;justify-content: center;">
        <div id='playlist' style="margin:5px; height: calc(100% - 70px);
        width: 1100px;display: flex;justify-content: center;flex-wrap: wrap">
            <c:forEach items="${page.result}" var="live" varStatus="status">
                <div style="">
                    <div id='${status.index}' data="${live.hls}"
                     style="margin:5px; height: 300px; width: 540px;"></div>
                <p>${live.channelName}</p>
                </div>
            </c:forEach>
        </div>
        <div id="pp"
             style="background:#efefef;border:1px solid #ccc;z-index:999999;position: fixed;bottom: 0px;margin-bottom:8px;left:
        calc(50% -
        150px);"
             data-options="
                total: ${page.totalCount},
                pageSize: 4,
                pageNumber:${page.pageNo},
                showPageList: false,
                showRefresh: false,
                onSelectPage: function (pageNumber, pageSize) {
                $('#content').panel('refresh',
                '${ctx}/zxjkyj/hikvision/show?filter_EQS_deviceSerial=${deviceSerial}&rows='+pageSize+
                '&page='+pageNumber);
                }"></div>
    </div>
</div>


<script type="text/javascript">

    $(function () {
        $('#pp').pagination({});
    });

    var player;
    $.each($("#playlist").find("div"), function (index, item) {
        load($(item).attr("data"), $(item).attr("id"));
    });

    function load(seturl, divid) {
        var videoObject = {
            container: '#' + divid,//“#”代表容器的ID，“.”或“”代表容器的class
            variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
            autoplay: false, //是否自动播放
            video: seturl //视频地址
        };
        player = new ckplayer(videoObject);
    }

</script>
</body>
</html>