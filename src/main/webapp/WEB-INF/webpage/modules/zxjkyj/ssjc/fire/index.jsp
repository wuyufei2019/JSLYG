<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>视频监控</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<%--<div style="margin: 10px;text-align: center;">
    <input type="text" id="qy_name" name="qy_name" class="easyui-combobox" value="${qyname}"  style="height: 30px;width:300px;" data-options="editable:true ,valueField: 'id',textField: 'm1',url:'${ctx}/zxjkyj/spjk/qylist',prompt: '企业名称' "/>
    <button class="btn btn-info btn-sm" onclick="search()"><i class="fa fa-search"></i> 查询</button>
</div>--%>
<div id="qiyelist" style="width: 100%;margin: 0 auto; margin: 50px">
    <%-- <c:forEach items="${list}" var="st" varStatus="status">
        <div style="width:800px;height:640px;float: left;margin: 10px 15px;text-align: center;">
            <a href="javascript:void(0)" onclick="showsp('${st.deviceId}')">
                <iframe frameborder="0" src="${st.url}"
                        allow="autoplay *; microphone *; fullscreen *"
                        allowfullscreen="true" allowtransparency="true" allowusermedia="true"
                        frameBorder="no" width="800px" height="500px" ></iframe>
                <p style="color: #246793;padding: 0px 0;font-weight: bold;">${st.qyname}(${st.deviceId})</p>
            </a>
        </div>
    </c:forEach> --%>
    
    <div id="qiyelist" style="width:90%;margin: 0 auto;display:flex;flex-wrap:wrap;" >
		<c:forEach items="${list}" var="st" varStatus="status">
			<div style="width:300px;height:250px;margin: 10px 15px;text-align: center;" >
				<a href="javascript:void(0)" onclick="showsp('${st.deviceId}','${st.qyname}')" >
					<img src="${st.url}" style="width: 300px;" onerror="this.src='${ctx}/static/model/images/andmu/spdx.png'"/> 
					<p style="color: #246793;padding: 5px 0;font-weight: bold;">${st.qyname}(${st.deviceId})</p>
				</a>
			</div>
		</c:forEach> 
	</div>
</div>

<script type="text/javascript">
    //显示企业视频信息页面
    function showsp(n,qyname) {
        openDialogView(qyname+"视频监控查看", ctx + '/zxjkyj/fire/show/' + n, "1000px", "90%", "");
    }


    function search() {
        var qyname = $("#qy_name").combobox("getText");
        window.location.href = '${ctx}/zxjkyj/spjk/index?qyname=' + qyname;
    }

</script>

</body>
</html>