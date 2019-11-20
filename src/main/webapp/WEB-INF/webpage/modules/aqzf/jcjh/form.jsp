<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>安全计划信息管理</title>
    <meta name="decorator" content="default"/>
    <%@ include file="/WEB-INF/webpage/include/kindeditor.jsp" %>
    <script type="text/javascript"
            src="${ctx}/static/model/js/aqzf/jcjh/index.js?v=4"></script>
</head>
<body>

<form id="inputForm" action="${ctx}/aqzf/jcjh/${action}"
      method="post" class="form-horizontal">
    <table
            class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">计划时间：</label></td>
            <td class="width-35"><input id="M2" name="M2" style="height: 30px; width: 100%;" type="text"
                                        class="easyui-datebox" data-options="editable:false,required:'true'"
                                        value="${jcjh.m2 }">
            </td>
            <td class="width-15 active"><label class="pull-right">企业分组<span
                    style="color:red">（所选企业分组为直属不包含下属）</span>：</label></td>
            <td class="width-35"><input type="text" id="M3" name="M3" class="easyui-combotree"
                                        style="height: 30px; width: 100%;" value="${jcjh.m3}"
                                        data-options="multiple:true,cascadeCheck:false,editable:false,valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson'"/>
            </td>
        </tr>

        <input type="hidden" id="M4" name="M4" value="${jcjh.m4}"/>
        <%-- <td class="width-15 active"><label class="pull-right">检查科室：</label></td>
        <td class="width-35"><input type="text" id="M5" name="M5" class="easyui-combobox" style="height: 30px; width: 100%;" value="${jcjh.m5}" data-options="panelHeight:'150px',editable:true,valueField:'text',textField:'text',url:'${ctx }/system/department/deptjson'" /></td> --%>
        <tr>
            <td class="width-15" colspan="2" style="text-align:center">
                <label style="color: red;font-size: 20px;margin-top: 10px;">企业随机</label>
            </td>
            <td class="width-15" colspan="2" style="text-align:center">
                <label style="color: red;font-size: 20px;margin-top: 10px;">行政执法人员随机</label>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">企业随机条件：</label></td>
            <td class="width-35">
                <div style="margin-top: 7px;">
                    <span style="font-size: 15px;">每组随机企业数：</span>
                    <input type="text" id="sl" name="sl" class="easyui-numberbox" style="height: 25px; width: 50px;"
                           value="" data-options="editable:true,min:0"/> 家企业<label id="qytotal"
                                                                                   style="color: red;"></label>
                    <br>
                    <div style="margin-top: 7px;">
                        <span style="font-size: 15px;">过滤条件：</span>
                        <input type="radio" name="gltj" style="margin-bottom:5px;" value="0"/>不过滤
                        <input type="radio" name="gltj" style="margin-bottom:5px;margin-left: 10px;" value="1"
                               checked="checked"/>本年度内不重复
                        <!-- <input type="radio" name="gltj" style="margin-bottom:5px;margin-left: 10px;" value="2"/>本季度内
                        <input type="radio" name="gltj" style="margin-bottom:5px;margin-left: 10px;" value="3"/>本月内 -->
                    </div>
                </div>
                <a id="sjsc" class="btn btn-info btn-sm" onclick="sjsc()" style="margin-top: 10px;">企业随机生成</a>
            </td>
            <td class="width-15 active"><label class="pull-right">行政执法人员随机条件：</label></td>
            <td class="width-35">
                <div>
                    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
                        <tbody id="fzqk">
                        <tr>
                            <span style="font-size: 15px;">随机执法人员数：</span>

                            <input class="easyui-numberspinner" id="rygs" name="rygs" value="2"
                                   data-options="increment:1, min: 2"
                                   style="width:100%;height: 30px">
                            <br>
                            <div style="margin-top: 7px;">
                        </tr>
                        </tbody>
                    </table>
                </div>
                <a id="sjzfry" class="btn btn-info btn-sm" onclick="sjzfry()" style="">行政执法人员随机生成</a>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">所选企业：</label></td>
            <td class="width-35">
                <!-- 负责传值 -->
                <div id="qyIDs">
                    <input id="qyids" type="hidden" name="qyids"/>
                    <div id="qyList"></div>
                    <a href="javascript:void(0)" class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left"
                       onclick="openqylist()" title="选择企业"><i class="fa fa-plus"></i> 选择企业</a><span id="tip"
                                                                                                    style="color:red">（如果不选择企业，则添加失败）</span>
                </div>
            </td>
            <td class="width-15 active"><label class="pull-right">所选行政执法人员：</label></td>
            <td class="width-35">
                <!-- 负责传值 -->
                <div id="ryIDs">
                    <input id="ryids" type="hidden" name="ryids"/>
                    <div id="ryList"></div>
                    <a href="javascript:void(0)" class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left"
                       onclick="openrylist()" title="选择行政执法人员"><i class="fa fa-plus"></i> 选择行政执法人员</a>
                </div>
            </td>
        </tr>
        <tr>

        </tr>
        <c:if test="${not empty jcjh.ID}">
            <input type="hidden" name="ID" value="${jcjh.ID}"/>
        </c:if>
        </tbody>
    </table>
</form>


<script type="text/javascript">
    function sjzfry() {
        var $list = $("#ryList");
        var ryids = "";
        $("#ryids").val(ryids);
        $list.html("");
        $.ajax({
            type: 'post',
            url: ctx + "/aqzf/zfry/sjsc",
            data: {
                "rygs": $("#rygs").textbox("getValue")
            },
            success: function (data) {
                var arr1 = data.split(",");
                var ids = "";
                for (var i = 0; i < arr1.length - 1; i++) {
                    var arr2 = arr1[i].split("||");
                    var $li = $("<div id=\"ryid_"
                        + arr2[0]
                        + "\" style=\"margin-bottom: 10px;\">"
                        + "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;display:inline-block;width:180px;\">"
                        + arr2[1]
                        + "</a>"
                        + "<span class=\"ss\" onClick=\"removeRy('ryid_"
                        + arr2[0]
                        + "')\" style=\"cursor: pointer;margin-right:20px;\">删除</span>"
                        + " <button onclick=\"rydgsj(" + arr2[0] + ");return false;\">随机</button>"
                        + " </div>");
                    $list.append($li);
                    ids = ids + arr2[0] + ",";
                }
                $("#ryids").val(ids);
            }
        });
    }

    function rydgsj(ryid) {
        $.ajax({
            type: 'post',
            url: ctx + "/aqzf/jcjh/rydgsj",
            data: {
                "ryid": ryid,
                "ryids": $("#ryids").val()
            },
            success: function (data) {
                if (data.flag == '1') {
                    var $li = $("<div id=\"ryid_"
                        + data.id
                        + "\" style=\"margin-bottom: 10px;\">"
                        + "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;display:inline-block;width:180px;\">"
                        + data.m1 + " — " + data.zname
                        + "</a>"
                        + "<span class=\"ss\" onClick=\"removeRy('ryid_"
                        + data.id
                        + "')\" style=\"cursor: pointer;margin-right:20px;\">删除</span>"
                        + " <button onclick=\"rydgsj(" + data.id + ");return false;\">随机</button>"
                        + " </div>");
                    $("#ryid_" + ryid).after($li);
                    $("#ryids").val($("#ryids").val() + data.id + ",");
                    removeRy("ryid_" + ryid);
                } else {
                    layer.msg("该组没有剩余人员，无法再随机！", {
                        time: 2000
                    });
                }
            }
        });
    }

    var qytotal = 0;

    function sjsc() {
        var sl = $("#sl").numberbox('getValue');
        if (sl == "" || sl == null || sl == undefined || sl == "0") {
            layer.msg("请输入随机生成数量！", {time: 3000});
            return;
        } else {
            var $list = $("#qyList");
            var sds = $("#M3").combotree('getValues');
            var sd = "";
            for (j = 0; j < sds.length; j++) {
                if (j == 0) {
                    sd = "\'" + sds[j] + "\'";
                } else {
                    sd += ",\'" + sds[j] + "\'";
                }
            }
            var hylx = $("#M4").val();
            //过滤条件
            var radio = document.getElementsByName("gltj");
            var gltj = "0";
            for (i = 0; i < radio.length; i++) {
                if (radio[i].checked) {
                    gltj = radio[i].value;
                }
            }
            var qyids = "";
            if (sd == '' || sd == null) {
                sd = 'flag';
            }
            if (hylx == '' || hylx == null) {
                hylx = 'flag';
            }
            $("#qyids").val(qyids);
            $list.html("");
            $.ajax({
                type: 'post',
                url: ctx + "/aqzf/jcjh/sjsc",
                data: {
                    "sd": sd,
                    "hylx": hylx,
                    "sl": sl,
                    "gltj": gltj//过滤条件
                },
                success: function (data) {
                    var arr1 = data.split(",");
                    var ids = "";
                    qytotal = 0;
                    for (var i = 0; i < arr1.length - 1; i++) {
                        var arr2 = arr1[i].split("||");
                        // 企业名称拼接
                        var $li = $("<div id=\"qyid_"
                            + arr2[0]
                            + "\" style=\"margin-bottom: 10px;\">"
                            + "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;display:inline-block;width:380px;\">"
                            + arr2[1] + " — " + arr2[2]
                            + "</a>"
                            + "<span class=\"ss\" onClick=\"removeQy('qyid_"
                            + arr2[0]
                            + "')\" style=\"cursor: pointer;margin-right:20px;\">删除</span>"
                            + " <button onclick=\"qydgsj(" + arr2[0] + ");return false;\">随机</button>"
                            + " </div>");
                        $list.append($li);
                        ids = ids + arr2[0] + ",";
                        qytotal++;
                    }
                    $("#qytotal").html("（共" + qytotal + "家）");
                    $("#tip").hide();
                    $("#qyids").val(ids);
                }
            });
        }
    }

    function qydgsj(qyid) {
        //过滤条件
        var radio = document.getElementsByName("gltj");
        var gltj = "0";
        for (i = 0; i < radio.length; i++) {
            if (radio[i].checked) {
                gltj = radio[i].value;
            }
        }
        $.ajax({
            type: 'post',
            url: ctx + "/aqzf/jcjh/qydgsj",
            data: {
                "qyid": qyid,
                "qyids": $("#qyids").val(),
                "gltj": gltj//过滤条件
            },
            success: function (data) {
                if (data.flag == '1') {
                    var $li = $("<div id=\"qyid_"
                        + data.qyid
                        + "\" style=\"margin-bottom: 10px;\">"
                        + "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;display:inline-block;width:380px;\">"
                        + data.qyname + " — " + data.m1
                        + "</a>"
                        + "<span class=\"ss\" onClick=\"removeQy('qyid_"
                        + data.qyid
                        + "')\" style=\"cursor: pointer;margin-right:20px;\">删除</span>"
                        + " <button onclick=\"qydgsj(" + data.qyid + ");return false;\">随机</button>"
                        + " </div>");
                    $("#qyid_" + qyid).after($li);
                    $("#qyids").val($("#qyids").val() + data.qyid + ",");
                    removeQy("qyid_" + qyid);
                } else {
                    layer.msg("该分组下没有剩余企业，无法再随机！", {
                        time: 2000
                    });
                }
            }
        });
    }

    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    var validateForm;

    function doSubmit() {
        var divtxt = $("#qyList").text();
        if (divtxt == null || divtxt == '') {
            top.layer.confirm('您还没有选择任何企业，否则添加失败!', {
                icon: 3,
                title: '提示'
            }, function (index) {
                $("#inputForm").serializeObject();
                $("#inputForm").submit();
                top.layer.close(index);
            });
        }
        else {
            $("#inputForm").serializeObject();
            $("#inputForm").submit();
        }
    }

    $(function () {
        var flag = true;
        $('#inputForm').form({
            onSubmit: function () {
                var isValid = $(this).form('validate');
                if (isValid && flag) {
                    flag = false;
                    $.jBox.tip("正在提交，请稍等...", 'loading', {opacity: 0});
                    return true;
                }
                return false; // 返回false终止表单提交
            },
            success: function (data) {
                $.jBox.closeTip();
                if (data == 'success') {
                    parent.layer.open({icon: 1, title: '提示', offset: 'rb', content: '操作成功！', shade: 0, time: 2000});
                    parent.dg.datagrid('reload');
                    parent.layer.close(index);//关闭对话框。
                } else {
                    parent.layer.open({icon: 2, title: '提示', offset: 'rb', content: '操作失败！', shade: 0, time: 2000});
                }
            }
        });


        //日期控件只显示年月
        var M2 = $('#M2');
        M2.datebox({
            onShowPanel: function () {//显示日期选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
                span.trigger('click'); //触发click事件弹出月份层
                if (p.find('div.calendar-menu').is(':hidden')) p.find('div.calendar-menu').show();
                if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                    tds = p.find('div.calendar-menu-month-inner td');
                    tds.click(function (e) {
                        e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                        var year = /\d{4}/.exec(span.html())[0]//得到年份
                            , month = parseInt($(this).attr('abbr'), 10); //
                        M2.datebox('hidePanel')//隐藏日期对象
                            .datebox('setValue', year + '-' + month); //设置日期的值
                    });
                }, 0);
                yearIpt.unbind();//解绑年份输入框中任何事件
            },
            parser: function (s) {
                if (!s) return new Date();
                var arr = s.split('-');
                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
            },
            formatter: function (d) {
                return d.getFullYear() + '-' + (d.getMonth() + 1);
            }
        });
        var p = M2.datebox('panel'), //日期选择对象
            tds = false, //日期选择对象中月份
            aToday = p.find('a.datebox-current'),
            yearIpt = p.find('input.calendar-menu-year'),//年份输入框
            //显示月份层的触发控件
            span = aToday.length ? p.find('div.calendar-title span') :
                p.find('span.calendar-text');
        if (aToday.length) {//重新绑定新事件设置日期框为今天，防止弹出日期选择面板

            aToday.unbind('click').click(function () {
                var now = new Date();
                M2.datebox('hidePanel').datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1));
            });
        }

        //初始化分组信息
        var fzqk = $("#fzqk");
        var fzxx = '${fzqk}';
        fzxx = JSON.parse(fzxx);
        $.each(fzxx, function (idx, obj) {
            var $tdHtml = $('<tr><td>' +
                '<input type="checkbox" name="qrfz" style="margin-bottom: 7px;" value="' + obj.id + '"/>' +
                '<span style="font-size: 15px;"> ' + obj.m1 + '（' + obj.m2 + '人）</span></td>' +
                '<td>' +
                '<input type="text" id="qrfz_' + obj.id + '" class="easyui-numberbox" style="height: 25px; width: 50px;" value="" data-options="editable:true,min:0" /> 人</td></tr>');
            fzqk.append($tdHtml);
            $("#qrfz_" + obj.id).numberbox({
                min: 0
            });
        });
    });
</script>
</body>
</html>