<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>视频直播</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic}/ckplayer/ckplayer.js"></script>
    <style>
        .zbjblock {
            margin: 10px 10px 0px;
            background: #f1f1f1;
            box-shadow: 0 0 8px 0 #b9b9b9;
            padding-bottom: 1px;
        }

        .zbjblock .video {
            height: 140px;
            background: #000;
            margin: 5px;
        }

        .zbjblock .video .player_ {
            height: 100%;
            width: 100%;
        }

        .zbjblock .viewBtn {
            padding: 4px 10px 2px;
            background: darkorange;
            border-radius: 4px;
            cursor: pointer;
        }

        #playlist {
            width: 300px;
            height: 100%;
            overflow-y: scroll;
        }

        #dqzbj {
            width: calc(100% - 300px);
            height: 100%;
            display: flex;
            flex-wrap: wrap;
        }
    </style>
</head>

<body>
<div style="width: 100%;height: 100%;">
    <div style="display: flex;height: 100%;">
        <div id='playlist'>
            <c:forEach items="${lives}" var="live" varStatus="status">
                <div class="zbjblock" id="${status.index}">
                    <div style="padding: 8px 5px 6px;background: antiquewhite;display: flex;justify-content: space-between">
                        <span>${live.title}</span>
                        <span class="viewBtn" data-id="${live.uuid}">进入直播间</span>
                    </div>
                    <div class="video">
                        <div id='${live.uuid}' class="player_"></div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div id='dqzbj' >
            <div id='main' style="width: 100%" class="player_"></div>
        </div>
        </div>
    </div>

</div>


<script type="text/javascript">
    var playerList = {};
    var first = true;
    $.each($("#playlist .video").find("div"), function (index, item) {
        load($(item).attr("id"));
    });

    function load(uuid) {
        var liveUrl = '${liveUrl}' + uuid;
        var videoObject = {
            playerID: uuid,
            container: '#' + uuid,//“#”代表容器的ID，“.”或“”代表容器的class
            variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
            autoplay: true, //是否自动播放
            volume: 0,
            video: liveUrl, //视频地址
            live: true
        };
        var player = new ckplayer(videoObject);
        playerList[uuid] = player;
    }

    window.onload = function () {
        $('.viewBtn').click(function () {
            var id = $(this).attr('data-id');
            var liveUrl = '${liveUrl}' + id;
            var videoObject = {
                container: '#main',//“#”代表容器的ID，“.”或“”代表容器的class
                autoplay: true, //是否自动播放
                volume: 1,
                video: liveUrl, //视频地址
            };
            var player = new ckplayer(videoObject);
        })
        $('.zbjblock:first-child .viewBtn').trigger('click');
    }
</script>
</body>
</html>