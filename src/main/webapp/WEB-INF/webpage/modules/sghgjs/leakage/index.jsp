<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>持续泄漏事故计算</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctxStatic}/model/js/sghgjs/leakage/index.js?v=1.1"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script>
    <style type="text/css">
        .BMap_cpyCtrl {
            display: none;
        }

        .anchorBL {
            display: none;
        }

        #leakagedituContent label {
            max-width: none;
        }

        .yjcl_table {
            border-collapse: collapse;
            width: 100%
        }

        .yjcl_table td {
            border-width: 1px;
            border-color: #ccc;
            border-style: dotted;
            margin: 0;
            padding: 0;
            height: 23px;
        }

        .yjcl_table_td {
            background-color: #efefef;
            width: 30%;
            text-align: left;
        }

        .ssjl_table_td {
            background-color: #efefef;
            width: 65%;
            text-align: left;
        }

        .xzqy {
            position: fixed;
            z-index: 999;
            margin-left: calc(50% - 118px);
            margin-top: 10px;
            /*background: #20576d9e;*/
            padding: 5px 15px;
            border-radius: 5px;
            /*box-shadow: 0 0 16px 0 #00000057;*/
        }
    </style>
</head>
<body>
<tr style="height: 50px;">
    <td class="ead_yjjc_form_id_table_td2"><div class="xzqy">
        <input  type="text" id="bis_cjxx_qy_name" name="bis_cjxx_qy_name" class="easyui-combobox aaa" style="height: 30px;"
                data-options="editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '请选择企业' "/>
    </div>
    </td>
</tr>
<div style="height:100%;width:100%;border:#ccc solid 1px;position:absolute;" id="leakagedituContent"></div>


<div id="leakage_yjzy_win" style="display: none;width: 100%;height: 100%;">
    <table id="leakage_yjzy_dg"></table>
</div>
<div id="leakage_yjcl_win" style="display: none;">
    <table id="leakage_yjcl_dg" class="yjcl_table">
        <tr>
            <td class="yjcl_table_td">物质名称：</td>
            <td id="table_yjcl_m1"></td>
        </tr>
        <tr>
            <td class="yjcl_table_td">主要危险性:</td>
            <td id="table_yjcl_m2"></td>
        </tr>
        <tr>
            <td class="yjcl_table_td">事故应急处置技术:</td>
            <td id="table_yjcl_m5"></td>
        </tr>
    </table>
</div>
<div id="leakage_ssjl_win" style="display: none;">
    <table id="leakage_ssjl_dg" class="yjcl_table" style="text-align: center;">
        <tr>
            <td class="ssjl_table_td">物质名称：</td>
            <td id="table_ssjl_m2"></td>
        </tr>
        <tr>
            <td class="ssjl_table_td">初始隔离距离(少量泄漏)/米：</td>
            <td id="table_ssjl_m3"></td>
        </tr>
        <tr>
            <td class="ssjl_table_td">白天下风向防护距离(少量泄漏)/米：</td>
            <td id="table_ssjl_m4"></td>
        </tr>
        <tr>
            <td class="ssjl_table_td">夜晚下风向防护距离(少量泄漏)/米：</td>
            <td id="table_ssjl_m5"></td>
        </tr>
        <tr>
            <td class="ssjl_table_td">初始隔离距离(大量泄漏)/米：</td>
            <td id="table_ssjl_m6"></td>
        </tr>
        <tr>
            <td class="ssjl_table_td">白天下风向防护距离(大量泄漏)/米：</td>
            <td id="table_ssjl_m7"></td>
        </tr>
        <tr>
            <td class="ssjl_table_td">夜晚下风向防护距离(大量泄漏)/米：</td>
            <td id="table_ssjl_m8"></td>
        </tr>
    </table>
</div>


<script type="text/javascript">
    var flag = 0;
    var usertype = '${usertype}';
    if (usertype != '' && usertype != null) {
        var lng2 = '${lng2}';//经度
        var lat2 = '${lat2}';//纬度
        initMap("leakagedituContent", lng2, lat2, 16);
    } else {
        var lng = '${lng}';//经度
        var lat = '${lat}';//纬度
        //初始化地图
        initMap("leakagedituContent", lng, lat, 16);
    }

    function showDatagrid(postdata) {
        var divwidth = $("#leakagedituContent").width() / 2;
        if (divwidth > 350) divwidth = 350;
        var left = $(window).width() - divwidth;
        var height = $("#leakagedituContent").height();

        //应急资源框
        layer.open({
            type: 1,
            area: [divwidth + 'px', height / 4 + 'px'],
            offset: ['40px', left + 'px'],
            title: '应急资源',
            shift: 5,
            shade: 0,
            content: $('#leakage_yjzy_win')
        });


        //应急处理技术框
        layer.open({
            type: 1,
            area: [divwidth + 'px', height / 4 + 'px'],
            offset: [height / 4 + 45 + 'px', left + 'px'],
            title: '应急处理技术',
            shift: 5,
            shade: 0,
            content: $('#leakage_yjcl_win')
        });


        //疏散距离
        layer.open({
            type: 1,
            area: [divwidth + 'px', height / 4 + 'px'],
            offset: [height / 2 + 50 + 'px', left + 'px'],
            title: '疏散距离',
            shift: 5,
            shade: 0,
            content: $('#leakage_ssjl_win')
        });

        $('#leakage_yjzy_dg').datagrid({
            method: "post",
            url: ctx + '/erm/yjwz/json',
            loadMsg: '正在加载',
            fit: true,
            fitColumns: true,
            singleSelect: true,
            scrollbarSize: 0,
            border: false,
            height: 160,
            columns: [[
                {field: 'm2', title: '物资名称', width: 80},
                {field: 'm9', title: '储存地址', width: 100},
                {field: 'm3', title: '型号', align: 'center'},
                {field: 'm4', title: '数量', align: 'center'},
                {
                    field: 'dis', title: '距离(km)', width: 80, align: 'center',
                    formatter: function (value, row, index) {
                        if (value != null) {
                            value = value / 1000;
                            return value.toFixed(1);
                        } else {
                            return "";
                        }
                    }
                }
            ]],
            queryParams: postdata,
            enableHeaderClickMenu: false,
            enableRowContextMenu: false,
        });

    }

    function getData(postdata) {
        $.ajax({
            method: 'POST',
            url: ctx + '/sekb/msds/json',
            dataType: 'json',
            data: {name: postdata.wzname},
            success: function (data) {
                $('#table_yjcl_m1').html(data.m1);
                $('#table_yjcl_m2').html(data.m10);
                $('#table_yjcl_m5').html(data.m20);
            }
        });


        $.ajax({
            method: 'POST',
            url: ctx + '/sekb/csfhjl/json',
            dataType: 'json',
            data: {name: postdata.wzname},
            success: function (data) {
                $('#table_ssjl_m2').html(data.m2);
                $('#table_ssjl_m3').html(data.m3);
                $('#table_ssjl_m4').html(data.m4);
                $('#table_ssjl_m5').html(data.m5);
                $('#table_ssjl_m6').html(data.m6);
                $('#table_ssjl_m7').html(data.m7);
                $('#table_ssjl_m8').html(data.m8);
            }
        });
    }

    function show(postdata) {
        if (flag) {
            $('#leakage_yjzy_dg').datagrid('load', postdata);
            getData(postdata);
        } else {
            showDatagrid(postdata);
            getData(postdata);
            flag = 1;
        }
    }

    $('#bis_cjxx_qy_name').combobox({
        onChange : function(n,o){
            var qyid = $('#bis_cjxx_qy_name').combobox('getValue');
            $.ajax({
                url: ctx + '/sghgjs/leakage/getjwd',
                type: "get",
                data: {"qyid": qyid},
                dataType: "json",
                success: function (data) {
                    var lng1 = data.m16;
                    var lat1 = data.m17;
                    map.setCenter(new BMap.Point(lng1, lat1));
                }
            });
        }
    });
</script>
</body>
</html>