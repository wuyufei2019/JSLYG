<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>会议信息</title>
    <meta name="decorator" content="default"/>
    <style>
        .d_body {
            width: 98%;
            height: 100%;
            margin: 1% 1% 1% 1%;
        }

        .bulue {
            color: #137fd8;
        }

        .shang {
            width: 100%;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 10px 0 #d0d0d0;
            overflow: hidden;
        }

        .shang > div {
            width: 100%;
            display: flex;
        }

        .shang_l {
            width: 75%;
        }

        .shang_l .shang_t {
            width: 100%;
            display: flex;
        }

        .shang_l .info_img {
            width: 20%;
            display: flex;
            justify-content: center;
            align-items: center;
            background: #c4e2f7;
            position: relative;
        }

        .shang_l .info {
            width: 76%;
            background: #f8f8f8;
            margin-right: 4%;
        }

        .shang_l .info_list {
            width: 100%;
            display: flex;
        }

        .info_tit {
            color: black;
            padding: 10px;
            font-size: 18px
        }

        .info_list .list1 {
            width: 35%;
            text-align: center;
        }

        .info_list .list3 {
            width: 40%;
        }

        .info_list .list2 {
            width: 25%;
        }

        .info_list .list2 p {
            text-align: right;
            margin-right: 30px;
        }

        .info_list .list2 p span {
            margin-right: 10px
        }

        .info_list p span {
            margin-left: 10px
        }

        .info_list p {
            margin-bottom: 5px;
        }

        .info_img > div.ic_ {
            width: 60px;
            height: 60px;
            background: url(${ctx}/static/model/images/ysx/hy_.png);
            background-size: 100% 100%;
        }

        .info_img > div.text_ {
            position: absolute;
            margin-top: 45px;
            font-weight: 600;
            color: #137fd8;
        }

        .shang_r {
            width: 25%;
            display: flex;
            justify-content: center;
            align-items: center;
            background: #f8f8f8;
        }

        .shang_r > div {
            width: 200px;
            height: 200px;
            background: url(${ctx}/static/model/images/ysx/sxj_.png);
            background-size: 100% 100%;
        }

        .showCard1 {
            width: 100%;
            display: flex;
        }

        .showCard1 .card1 {
            width: 18%;
            margin: 1%;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
        }

        .showCard1 .card2 {
            width: 38%;
            margin: 1%;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            border-left: 1px solid #d6d6d6;
        }

        .showCard2 {
            width: 100%;
            display: none;
        }

        .showCard1 p {
            margin: 4px 0 0;
        }

        .IcLab > div {
            width: 18%;
            margin: 1%;
            padding: 25px;
            background: #c4e2f7;
            text-align: center;
            color: #333;
            font-size: 20px;
            border-radius: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .IcLab div img {
            width: 36px;
        }

        .IcLab div span {
            font-size: 16px;
            margin-left: 20px
        }

        .IcLab div span:hover {
            cursor: pointer;
        }

        .xia {
            display: flex;
            width: 100%;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 10px 0 #d0d0d0;
            margin-top: 15px;
        }

        .xia_l {
            width: 78%;
            margin-right: 2%;
        }

        .xia_r {
            width: 20%
        }

        .xia_head {
            display: flex;
            width: calc(100% - 12px);
            margin-left: 12px;
            border-bottom: 1px solid #e0e0e0;
        }

        .xia_head .tit {
            font-size: 15px;
            color: #4b8ccc;
            border-bottom: 2px solid #1588dc;
            height: 26px;
            padding: 0 15px 0 0;
        }

        .xia_head1 {
            display: flex;
            width: 48%;
            padding: 4px 1% 4px;
            margin: 10px 1% 0;
            justify-content: space-between;
        }

        .xia_head1 .icb img {
            width: 24px;
        }

        .xia_head1 .icb img:hover {
            cursor: pointer;
        }

        .xia_r .btns {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 10px;
        }

        .xia_r .btns .btn1 {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            margin-right: 15px;
        }

        .xia_r .btns .btn2 {
            padding: 8px 15px 6px;
            border-radius: 18px;
            background: #1588dc;
            color: white;
            font-size: 16px;
            font-weight: 600;
        }

        #list_ {
            overflow-y: scroll;
            overflow-x: hidden;
            margin-bottom: 10px;
        }

        #list_t, #list_t2 {
            list-style-type: none;
            padding-left: 6px;
            background: #f8f8f8;
            margin-left: 12px;
            margin-bottom: 0px;
        }

        #list_t li, #list_t2 li {
            width: 100%;
            display: flex;
            align-items: center;
            padding: 8px 25px;
        }

        #list_t li:hover, #list_t2 li:hover {
            cursor: pointer;
        }

        #list_t li:nth-of-type(odd), #list_t2 li:nth-of-type(odd) {
            background: #f8f8f8;
        }

        #list_t li:nth-of-type(even), #list_t2 li:nth-of-type(even) {
            background: #fff;
        }

        #list_t .tr_d, #list_t2 .tr_d {
            width: 15%;
        }

        #list_t .tr_d_, #list_t2 .tr_d_ {
            width: 20%;
        }

        #list_t .tr_d img, #list_t2 .tr_d img {
            width: 32px;
        }

        #list_t .tr_d_f, #list_t2 .tr_d_f {
            width: 10%;
            text-align: center;
        }

        #list_t .tr_d_f_r, #list_t2 .tr_d_f_r {
            width: 10%;
            text-align: center;
            color: #127bc7;
            border-left: 1px solid #bdb6b6;
        }

        #list_- {
            background: #f8f8f8;
        }
    </style>
    <script type="text/javascript" src="${ctxStatic}/ckplayer/ckplayer.js"></script>
</head>
<body style="background: #f3f3f3;">

<div class="d_body">
    <div class="shang">
        <div class="">
            <div class="shang_l">
                <div class="shang_t">
                    <div class="info_img">
                        <div class="ic_"></div>
                        <div class="text_"><c:if test="{entity.confState eq 2}"> 进行中</c:if><c:if
                                test="{entity.confState eq 3}"> 已结束</c:if></div>
                    </div>
                    <div class="info">
                        <div class="info_tit">
                            &nbsp;&nbsp;&nbsp;&nbsp;${entity.strSubject}
                        </div>
                        <div class="info_list">
                            <div class="list1">
                                <p class=""><i class="fa fa-crosshairs"></i><span>会议ID</span></p>
                                <p class=""><i class="fa fa-user"></i><span>发起人</span></p>
                                <p class=""><i class="fa fa-lock"></i><span>主持密码</span></p>
                                <p class=""><i class="fa fa-lock"></i><span>会议密码</span></p>
                            </div>
                            <div class="list3">
                                <p class=""><span>${entity.strConfID.value}</span></p>
                                <p class=""><span>应急管理局</span></p>
                                <p class=""><span>${entity.stPassword.strChairman}</span></p>
                                <p class=""><span>${entity.stPassword.strParticipant}</span></p>
                            </div>
                            <div class="list2">
                                <p class=""><span>&nbsp;</span></p>
                                <p class=""><span>&nbsp;</span></p>
                                <p class=""><span>&nbsp;</span></p>
                                <p class=""><span>会议议程</span><i class="fa fa-angle-down" id="agenda"></i></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="showCard1">
                    <div class="card1" id="cardh">
                        0小时
                    </div>
                    <div class="card1">
                        <p class="" id="cardm">0分钟</p>
                        <p class="">剩余时间</p>
                    </div>
                    <div class="card1" id="cards">
                        0秒
                    </div>
                    <div class="card2">
                        <p class="">${entity.strBeginTime}-${entity.strEndTime}</p>
                        <p class="">会议时间</p>
                    </div>
                </div>
                <div class="showCard2">
                    <div class="">
                        ${entity.strAgenda.value}
                    </div>
                </div>
            </div>
            <div id="">
                <div id='playlist' style="margin:5px; height: 200px; width: 400px;float: left;"></div>
            </div>
        </div>
        <div class="IcLab">
            <%--  <div class="">
                  <img src="${ctx}/static/model/images/ysx/zb_.png"/><span>会议录制</span>
              </div>
              <div class="">
                  <img src="${ctx}/static/model/images/ysx/jy_.png"/><span>会场闭音</span>
              </div>
              <div class="">
                  <img src="${ctx}/static/model/images/ysx/lc_.png"/><span>锁定会场</span>
              </div>--%>

            <div class="" onclick="openProlongConf()">
                <a>
                    <img src="${ctx}/static/model/images/ysx/ys_.png"/><span>延长时间</span>
                </a>
            </div>
            <div class="" onclick="removeConf()">
                <a>
                    <img src="${ctx}/static/model/images/ysx/js_.png"/><span>结束会议</span>
                </a>
            </div>
        </div>
    </div>
    <div class="xia">
        <div class="xia_l">
            <div class="xia_head">
                <div class="xia_head1" style="border-right: 1px solid #e0e0e0;">
                    <div class="tit">
                        参会人员
                    </div>
                   <%-- <div class="icb icb1 show1">
                        <img src="${ctx}/static/model/images/ysx/qh_.png"/>
                    </div>--%>
                </div>
                <div class="xia_head1">
                   <%-- <form>
                        <div class="form-group" style="margin-bottom: 3px;">
                            <input type="email" class="form-control" id="exampleInputEmail1" placeholder="请输入搜索内容">
                        </div>
                    </form>
--%>
                    <div class="icb icb2">
                        <img src="${ctx}/static/model/images/ysx/add_.png"/>
                    </div>
                </div>
            </div>
            <div id="list_" class="">
                <ul id="list_t">

                    <li>
                        <div class="tr_d">类别
                        </div>
                        <div class="tr_d bulue">
                            用户名称
                        </div>
                        <div class="tr_d bulue">
                            用户id
                        </div>
                        <div class="tr_d">
                        </div>
                        <div class="tr_d_ bulue">
                            用户状态
                        </div>
                        <div class="tr_d_f bulue">
                            用户角色
                        </div>
                        <div class="tr_d_f_r">
                        </div>
                    </li>

                    <c:forEach items="${softUsers}" var="member">

                        <li id="${member.callID}">
                            <div class="tr_d">
                                <c:if test="${member.isSoftTerminal eq false}">
                                    <img src="${ctx}/static/model/images/ysx/dh_.png"/> 硬终端
                                </c:if>
                                <c:if test="${member.isSoftTerminal eq true}">
                                    <i class="fa fa-mobile-phone fa-3x"></i>&nbsp;&nbsp; &nbsp;软终端
                                </c:if>
                            </div>
                            <div class="tr_d">
                                    ${member.strName}
                            </div>
                            <div class="tr_d">
                                    ${member.callID}
                            </div>
                            <div class="tr_d">
                            </div>
                            <div class="tr_d_" id="state">
                                <c:choose>
                                    <c:when test="${member.state eq 1}">在会中</c:when>
                                    <c:when test="${member.state eq 2}">正在振铃</c:when>
                                    <c:when test="${member.state eq 3}">用户忙</c:when>
                                    <c:when test="${member.state eq 4}">用户无应答 </c:when>
                                    <c:when test="${member.state eq 5}">用户已挂机</c:when>
                                    <c:when test="${member.state eq 6}">会议未发起</c:when>
                                    <c:otherwise>无信息 </c:otherwise>
                                </c:choose>

                            </div>
                            <div class="tr_d_f">
                                <c:if test="${member.role eq 1}"> 主持人</c:if>
                                <c:if test="${member.role eq 2}"> 普通用户</c:if>
                            </div>
                            <div class="tr_d_f_r">
                                <a onclick="inviteSubscriber('${member.strUserID}','${member.role}','${member.isSoftTerminal}')">重新邀请</a>
                            </div>
                        </li>
                        <div>


                        </div>
                    </c:forEach>

                </ul>
            </div>
        </div>
        <div class="xia_r">
            <div id="list_-" style="height: 300px;overflow-y: scroll">
                <%--   <p style="margin: 5px">051880328543离开会议</p>
                   <p style="margin: 5px">051880328543离开会议</p>--%>
            </div>
            <div class="btns">
                <div class="btn1">
                    <%--<div class="btn1_t">
                        0/2
                    </div>
                    <div class="">
                        入会人数
                    </div>--%>
                </div>
                <%-- <div class="btn2">
                     一键邀请
                 </div>--%>
            </div>
        </div>
    </div>
</div>


<div></div>

<div style="display: none" id="prolongdiv">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-20 active"><label class="pull-right">延长时间：</label></td>
            <td class="width-80">
                <input style="width: 100%;height: 30px;" id="prolongTime" class="easyui-combobox" value="15"
                       data-options="required:'true',data:[{text:'15分钟',value:15},{text:'30分钟',value:30},
                       {text:'45分钟',value:45},{text:'60分钟',value:60}],panelHeight:'auto'"/></td>
        <tr>
        </tr>
        </tbody>
    </table>
</div>


<script type="text/javascript">
    var strConfID = '${strConfID}';
    var webSoketUrl;
    var endtime = '${entity.strEndTime}';
    var player;
    TimeDown(endtime);
    //load("rtmp://103.20.114.97:2935/051880328542/2be70c8ba8664e4e8ba3b935433ff513", "playlist");
    $(function () {


        // cap 设置xia div高度
        $("#list_").css("height", $(window).height() - $(".shang").height() - $(".xia_head").height() - 44);
        $("#list_-").css("height", $(".xia_r").height() - $(".btns").height() - 30);

        // 切换list_t
        $(".icb.icb1").click(function () {
            if ($(".icb.icb1").hasClass("show2")) {
                $("#list_t").css("display", "block");
                $("#list_t2").css("display", "none");

                $(".icb.icb1").addClass("show1").removeClass("show2");
            } else {
                $("#list_t2").css("display", "block");
                $("#list_t").css("display", "none");

                $(".icb.icb1").addClass("show2").removeClass("show1");
            }

        })
        $("#agenda").click(function () {
            $(".showCard1").toggle();
            $(".showCard2").toggle();
        })
        subscribeConference();
    });

    function removeConf() {
        layer.confirm('确定要结束会议？', {icon: 3, title: '提示'}, function (index) {
            $.post(ctx + '/zxjkyj/ysx/removeConf/' + strConfID, function (data) {
                if (data == "success") {
                    layer.msg("会议已结束！");
                }
                else
                    layer.msg(data);
            });
            top.layer.close(index);
            parent.layer.closeAll();//关闭对话框
            setTimeout(function () {
                parent.dg.datagrid('reload');
            }, 1500);//延迟执行
        });
    }

    function openProlongConf() {
        layer.open({
            title: "延迟会议",
            type: 1,
            area: ["400px", "150px"],
            content: $('#prolongdiv'),
            btn: ['确定', '关闭'],
            zIndex: 100,
            yes: function (index, layero) {
                $.post(ctx + '/zxjkyj/ysx/prolongConf/' + strConfID,
                    {prolongTime: $("#prolongTime").combobox("getValue")},
                    function (data) {
                        layer.close(index);
                        if (data == "success") {
                            location.reload();
                            layer.msg("会议已延迟！");
                        }
                        else
                            layer.msg(data);
                    });
            },
            cancel: function (index) {
            }
        });
    }

    //用户邀请
    function inviteSubscriber(subscriberID, conferenceRole,isSoftTerminal) {
        var param = {
            userId: '${entity.strUserId}',
            subscriber: {
                conferenceKey: {
                    conferenceID: '${entity.strConfID.value}',
                    subConferenceID: "0"
                },
                subscriberID: subscriberID,
                conferenceRole: conferenceRole == 1 ? "chair" : "general",
                isSoftTerminal: isSoftTerminal
            }
        };
        $.post(ctx + "/zxjkyj/ysx/inviteSubscriber", {param: JSON.stringify(param)}, function (data) {
            try {
                data = JSON.parse(data);
            } catch (e) {
                layer.msg(data.description);
            }

            if (data.code == "200") {
                layer.msg("邀请成功！");
            }
            else
                layer.msg(data.description);
        })
        ;
    }

    //订阅会议状态
    function subscribeConference() {
        var param = {
            userId: '${entity.strUserId}',
            address: "",
            conferenceKey: {
                conferenceID: '${entity.strConfID.value}',
                subConferenceID: "0"
            },
            expire: 5000,
            controlType: "terminal"
        };
        $.post(ctx + "/zxjkyj/ysx/SubscribeConference", {param: JSON.stringify(param)}, function (data) {
            data = JSON.parse(data);
            if (data) {
                webSoketUrl = data.wsAddress;
                //判断当前浏览器是否支持WebSocket
                var websocket = null;
                if ('WebSocket' in window) {
                    websocket = new WebSocket(webSoketUrl);
                }
                else {
                    alert('当前浏览器 Not support websocket')
                }

                //连接发生错误的回调方法
                websocket.onerror = function () {
                    console.log("WebSocket连接发生错误");
                };

                //连接成功建立的回调方法
                websocket.onopen = function () {
                    console.log("WebSocket连接成功");
                }

                //接收到消息的回调方法
                websocket.onmessage = function (event) {
                    var info = JSON.parse(event.data);
                    var msg = ['会议中', '正在呼叫', '正在加入会议', '离开会议', '号码不存在',
                                           '被叫忙', '用户无应答', '用户拒绝接听', '呼叫失败'];
                    if (info.conferenceInfo) {
                        var stateMap = {100: '正在呼叫', 200: '会议中', 403: '没有权限'};
                        $.each(info.conferenceInfo.attendees, function (idnex, item) {
                            var state = stateMap[item.inviteState.value];
                            $("#0" + item.addressEntry[0].address.substring(7, 18))
                                .find("#state").html(state ? state : '邀请失败');
                        });
                    }else if (info.subscriber) {
                        var subscriber = info.subscriber;
                        var html = "<p style='margin: 5px'>" + subscriber.subscribeName.value + ":&nbsp;" +
                            msg[subscriber.state.value] + "</p>";
                        $("#list_-").append(html);
                        $("#0" + subscriber.subscriberID.substring(7, 18))
                            .find("#state").html(msg[subscriber.state.value]);
                    }else if(info.conferenceNotifyType){
                        var state = info.status;
                        $("#" + info.subscriberID).find("#state").html(msg[state]);
                        var html = "<p style='margin: 5px'>" + info.subscriberID + ":&nbsp;" +
                            msg[state] + "</p>";
                        $("#list_-").append(html);
                    }
                }

                //连接关闭的回调方法
                websocket.onclose = function () {
                    console.log("WebSocket连接关闭");
                }

                //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
                window.onbeforeunload = function () {
                    websocket.close();
                }
            }
        })
        ;
    }

    /*
时间倒计时插件
TimeDown.js
*/
    function TimeDown(endtime) {
        //结束时间
        var endtime = new Date(endtime);
        //当前时间
        var nowDate = new Date();
        //相差的总秒数
        var totalSeconds = parseInt((endtime - nowDate) / 1000);
        if (totalSeconds < 0) {
            return false;
        }
        //取模（余数）
        var modulo = totalSeconds % (60 * 60 * 24);
        //小时数
        var hours = Math.floor(modulo / (60 * 60));
        modulo = modulo % (60 * 60);
        //分钟
        var minutes = Math.floor(modulo / 60);
        //秒
        var seconds = modulo % 60;
        //输出到页面
        $("#cardh").html(hours + "小时");
        $("#cardm").html(minutes + "分钟");
        $("#cards").html(seconds + "秒");
        //延迟一秒执行自己
        setTimeout(function () {
            TimeDown(endtime);
        }, 1000)
    }

    function load(seturl, divid) {
        var videoObject = {
            container: '#' + divid,//“#”代表容器的ID，“.”或“”代表容器的class
            variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
            autoplay: true, //是否自动播放
            video: seturl //视频地址
        };
        player = new ckplayer(videoObject);

    }


</script>
</body>
</html>