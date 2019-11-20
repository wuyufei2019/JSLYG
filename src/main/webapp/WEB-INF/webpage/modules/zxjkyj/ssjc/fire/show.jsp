<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>视频监控</title>
    <meta name="decorator" content="default"/>
</head>

<body>
<div id="playlist" style="margin: 0 auto;text-align: center">
    <iframe frameborder="0" src="${url}"
            allow="autoplay *; microphone *; fullscreen *"
            allowfullscreen="true" allowtransparency="true" allowusermedia="true"
            frameBorder="no" height="700px" width="900px"></iframe>
</div>


<script type="text/javascript">
</script>
</body>
</html>