<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title></title>
    <meta name="decorator" content="default"/>
    <title>连云区公共安全监管（应急指挥）云平台</title>
    <link href="${ctxStatic}/model/visualData/css/common2.css" rel="stylesheet">
    <script src="${ctxStatic}/jquery/jquery-3.3.1.min.js"></script>
    <script src="${ctxStatic}/model/visualData/js/Plugin/echarts.min.js"></script>
    <script src="${ctxStatic}/model/visualData/js/common2.js"></script>
    <script src="${ctx}/static/model/visualData/js/trend.js"></script>
    <style>
        th {
            text-align: unset;
        }
    </style>
</head>
<body>
<!--顶部-->
<header class="header left">
    <div class="left nav">
        <ul style="display: flex;">
            <li style="display: flex;justify-content: center;align-items: center;"><i class="nav_1"></i><a
                    href="${ctx}/a/vd_page">总体概况</a></li>
            <li class="nav_active" style="display: flex;justify-content: center;align-items: center;">
                <i class="nav_7"></i><a href="${ctx}/a/vd_page3" style="margin-top: 5px;">分类统计</a>
            </li>
        </ul>
    </div>
    <div class="header_center left" style="position:relative">

        <h2><strong>连云区应急指挥平台大数据</strong></h2>

    </div>
    <div class="right nav text_right">
        <ul style="display: flex;justify-content: flex-end;">
            <li style="display: flex;justify-content: center;align-items: center;"><i class="nav_2"></i><a
                    href="${ctx}/bis/qyjbxx/onemap2">应急大数据</a></li>
            <li style="display:none"><i class="nav_11"></i><a href="${ctx}/a/vd_page5">重大危险源</a></li>
            <li style="display: flex;justify-content: center;align-items: center;"><i class="nav_5"></i><a
                    href="${ctx}/a/vd_page8">视频监控</a></li>
            <li style="display: flex;justify-content: center;align-items: center;"><i class="nav_3"></i><a
                    href="${ctx}/">进入平台</a></li>
        </ul>
    </div>

</header>
<!--内容部分-->
<div class="con left" style="width:50%;">
    <!--统计分析图-->
    <div class="div_any">
        <div class="left div_table_box" style="width: 48%;">
            <div class="div_any_child" style="height: 420px;">
                <div class="div_any_title"><img src="${ctxStatic}/model/visualData/images/title_16.png">危险作业</div>
                <div class="table_p">
                    <table>
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>作业内容</th>
                            <th>发布时间</th>
                        </tr>
                        </thead>
                        <tbody id="rwtable">
                        <tr hidden="hidden"></tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="left div_any01" style="width:48%;">
            <div class="div_any_child" style="height: 420px;">
                <div class="div_any_title"><img src="${ctxStatic}/model/visualData/images/title_14.png">报备走势</div>
                <p id="lineChart1" class="p_chart" style="height: 400px;"></p>

            </div>
        </div>
        <div class="left div_any01" style="width:48%;">
            <div class="div_any_child" style="height: 420px;">
                <div class="div_any_title"><img src="${ctxStatic}/model/visualData/images/title_14.png">到期提醒</div>
                <p id="histogramChart2" class="p_chart" style="height: 400px;"></p>

            </div>
        </div>
        <div class="left div_table_box" style="width: 48%;">
            <div class="div_any_child" style="height: 420px;">
                <div class="div_any_title"><img src="${ctxStatic}/model/visualData/images/title_16.png">预警信息</div>
                <div class="table_p">
                    <table>
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>预警内容</th>
                            <th>发布时间</th>
                        </tr>
                        </thead>
                        <tbody id="rwtable2">
                        <tr hidden="hidden"></tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="left div_any01" style="width:100%;display:none">
            <div class="div_any_child" style="width:98%;position:relative;height: 420px;">
                <div class="div_any_title"><img src="${ctxStatic}/model/visualData/images/title_13.png">主要传染病</div>
                <div id="histogramChart1"
                     style="width: 45%;display: inline-block;height: 400px;margin-top: 15px;"></div>
                <div id="histogramChart2" style="width: 52%;height: 66%;display: inline-block;"></div>
                <div style="width: 50%;height: 20%;display: inline-block;color:red;position: absolute;right: 2%;top: 8%;">
                    <div style="display: inline-block;width:33%;text-align:center;">
                        <div style="color:#fff;font-size: 18px;line-height: 32px;">病例报告数</div>
                        <div style="color:#87cefa;font-size: 30px;line-height: 42px;">2354</div>
                    </div>
                    <div style="display: inline-block;width:33%;text-align:center;">
                        <div style="color:#fff;font-size: 18px;line-height: 32px;">死亡数</div>
                        <div style="color:red;font-size: 30px;line-height: 42px;">26</div>
                    </div>
                    <div style="display: inline-block;width:33%;text-align:center;">
                        <div style="color:#fff;font-size: 18px;line-height: 32px;">同比发病数趋势</div>
                        <div style="color:#ff7f50;font-size: 30px;line-height: 42px;">5.36%</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="con right" style="width:50%;">
    <div class="div_any">
        <div class="left div_any01" style="width:48%;">
            <div class="div_any_child" style="height: 420px;">
                <div class="div_any_title"><img src="${ctxStatic}/model/visualData/images/title_18.png">乡镇网格化巡检率排行</div>
                <p id="histogramChart3" class="p_chart" style="height: 400px;"></p>
            </div>
        </div>
        <div class="right div_any01" style="width:48%;">
            <div class="div_any_child" style="height: 420px;">
                <div class="div_any_title"><img src="${ctxStatic}/model/visualData/images/title_19.png">网格化安全完工排行
                </div>
                <p id="lineChart2" class="p_chart" style="height: 400px;"></p>
            </div>
        </div>
        <div class="left div_any01" style="width:100%;">
            <div class="div_any_child" style="width:98%;position:relative;height: 420px;">
                <div class="div_any_title"><img src="${ctxStatic}/model/visualData/images/title_17.png">风险预警雷达图</div>
                <div id="pieChart1" style="width: 45%;display: inline-block;height: 400px;margin-top: 15px;"></div>
                <div id="pieChart2" style="width: 52%;height: 90%;display: inline-block;"></div>
            </div>
        </div>
    </div>
</div>

<script>
    // 指定图表的配置项和数据
    var option4 = {
        title: {
            text: '风险点统计图'
        },
        toolbox: {
            show: true,
            feature: {
                magicType: {
                    type: ['line', 'bar', 'stack', 'tiled']
                },
                restore: {},
                saveAsImage: {
                    show: true
                }
            }
        },
        tooltip: {},
        legend: {
            data: ['蓝', '黄', '橙', '红']
        },
        xAxis: {
            data: ["物体打击", "机械伤害", "车辆伤害", "起重伤害", "高处坠落", "中毒和窒息", "触电", "淹溺", "灼烫", "火灾", "坍塌", "透水", "放炮", "冒顶片帮", "火药爆炸", "瓦斯爆炸", "锅炉爆炸", "容器爆炸", "其它爆炸", "其它伤害"],
            name: '风险分类',
            nameTextStyle: {
                color: '#4488BB'
            },
            axisTick: {
                show: false,	//是否显示坐标轴刻度。
                lineStyle: {
                    color: '#4488BB'//刻度线的颜色
                }
            },
            splitLine: {
                show: true,
            },
            axisLine: {
                lineStyle: {
                    color: '#4488BB',//轴线颜色
                    width: 2
                }
            },
            axisLabel: {
                textStyle: {
                    color: '#383838'//刻度标签文字的颜色
                },
                interval: 0
            }
        },
        yAxis: {
            name: '风险点数',
            nameTextStyle: {
                color: '#4488BB'
            },
            nameGap: 12,//坐标轴名称与轴线之间的距离。
            axisTick: {
                show: false,//是否显示坐标轴刻度。

            },
            axisLine: {
                lineStyle: {
                    color: '#4488BB',//轴线颜色
                    width: 2
                }
            },
            axisLabel: {
                textStyle: {
                    color: '#383838'//刻度标签文字的颜色
                }
            }
        },
        series: [{
            name: '蓝',
            type: 'bar',
            data: [],
            itemStyle: {
                normal: {
                    label: {
                        show: true,//是否展示
                        position: 'top',
                        textStyle: {color: '#5690D2'},
                        formatter: '{c}'
                    },
                    color: '#5690D2'
                }
            }
        },
            {
                name: '黄',
                type: 'bar',
                data: [],
                itemStyle: {
                    normal: {
                        color: '#FFF147',
                        label: {
                            show: true,//是否展示
                            position: 'top',
                            textStyle: {color: '#5690D2'},
                            formatter: '{c}'
                        },
                    }
                }
            },
            {
                name: '橙',
                type: 'bar',
                data: [],
                itemStyle: {
                    normal: {
                        color: '#E87C25',
                        label: {
                            show: true,//是否展示
                            position: 'top',
                            textStyle: {color: '#5690D2'},
                            formatter: '{c}'
                        },
                    }
                }
            },
            {
                name: '红',
                type: 'bar',
                data: [],
                itemStyle: {
                    normal: {
                        color: '#FF0000', label: {
                            show: true,//是否展示
                            position: 'top',
                            textStyle: {color: '#5690D2'},
                            formatter: '{c}'
                        },
                    }
                }
            }]
    };

    $(function () {
        var ctx = '${ctx}';

        //危险作业
        $.ajax({
            url: '${ctx}/syshome/wxzybbtj',
            type: "POST",
            success: function (data) {
                var tjdata = eval(data);
                for (var key in tjdata) {
                    wxzy = tjdata[key];
                    var num = key;
                    var date = new Date(wxzy.s1);
                    var date2 = date.format("yyyy-MM-dd");
                    num++;
                    var html = " <tr><td>" + num + "</td><td>" + wxzy.zylx + "</td><td>" + date2 + "</td></tr>"
                    var $list = $("#rwtable tr").eq(-1);
                    $list.after(html);
                }
            }
        });

        //预警信息
        $.ajax({
            url: '${ctx}/syshome/alarm',
            type: "POST",
            success: function (data) {
                var tjdata = eval(data);
                for (var key in tjdata) {
                    alarm = tjdata[key];
                    var num = key;
                    var date = new Date(alarm.colltime);
                    var date2 = date.format("yyyy-MM-dd");
                    num++;
                    var html = " <tr><td>" + num + "</td><td>" + alarm.lx + "</td><td>" + date2 + "</td></tr>"
                    var $list = $("#rwtable2 tr").eq(-1);
                    $list.after(html);
                }
            }
        });

        //危险作业报备走势
        $.ajax({
            url: '${ctx}/syshome/wxzybbtj2',
            type: "POST",
            success: function (data) {
                var tjdata = eval(data);
                loadEcharts5(tjdata);
            }
        });

        //到期提醒
        $.ajax({
            url: '${ctx}/syshome/dqtx',
            type: "POST",
            success: function (data) {
                var tjdata = eval(data);
                loadEcharts6(tjdata);
            }
        });

        //风险预警雷达图
        $.ajax({
            url: '${ctx}/syshome/FXDjSg2',
            type: "POST",
            dataType: 'json',
            success: function (data) {
                var pieChart1 = echarts.init(document.getElementById('pieChart1'));
                pieChart1.setOption({
                    tooltip: {},
                    radar: [
                        {
                            indicator: data.shigu,
                            radius: 120,
                            startAngle: 90,
                            splitNumber: 4,
                            name: {
                                formatter: '【{value}】',
                                textStyle: {
                                    color: '#72ACD1'
                                }
                            },
                        },
                    ],
                    series: [
                        {
                            type: 'radar',
                            itemStyle: {
                                normal: {
                                    areaStyle: {
                                        type: 'default'
                                    }
                                }
                            },
                            data: [
                                {
                                    value: data.num,
                                    name: '风险次数'
                                }
                            ]
                        }
                    ]
                })
            }
        })

        //乡镇网格巡检率排行
        $.ajax({
            url: '${ctx}/wghgl/jdkh/yjlist2',
            type: "POST",
            success: function (data) {
                loadEcharts7(data);
            }
        });

        //乡镇网格风险点数量排行
        $.ajax({
            url: '${ctx}/wghgl/jdkh/wgfxdph',
            type: "POST",
            success: function (data) {
                loadEcharts8(data);
            }
        });
    })

    function loadEcharts5(data) {
        var date = [];
        var amount = [];
        for (var i = 0; i < data.length; i++) {
            date.push(data[i].date);
            amount.push(data[i].amount);
        }
        //危险作业报备走势
        var lineChart1 = echarts.init(document.getElementById('lineChart1'));
        lineChart1.setOption({
            title: {
                text: '危险作业报备趋势',
                textStyle: {
                    fontSize: 16,
                    color: '#ff7f50'
                },
            },
            color: ["#ff7f50"],
            grid: {
                left: '15%',
                right: '5%',
                bottom: '15%',

            },
            tooltip: {
                trigger: 'item',
                formatter: "{a}<br/>{b}<br/>{c}条"
            },

            calculable: true,
            yAxis: [
                {
                    type: 'value',
                    axisLine: {
                        lineStyle: {
                            color: '#ff7f50'
                        },
                    },
                    splitLine: {
                        "show": false
                    },
                    axisLabel: {
                        textStyle: {
                            color: '#fff'
                        },
                        formatter: function (value) {
                            return value + ""
                        },
                    },
                }
            ],
            xAxis: [
                {
                    type: 'category',
                    data: date,
                    boundaryGap: false,
                    axisLine: {
                        lineStyle: {
                            color: '#ff7f50'
                        },
                    },
                    splitLine: {
                        "show": false
                    },
                    axisLabel: {
                        // interval:0,
                        // rotate:40,
                        textStyle: {
                            color: '#fff'
                        },
                        formatter: function (value) {
                            return value + ""
                        },
                    },
                }
            ],
            series: [
                {
                    name: '报备个数',
                    type: 'line',
                    smooth: true,
                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data: amount
                },
            ]

        })
    }

    function loadEcharts6(data) {
        var lx = [];
        var sum = [];
        for (var i = 0; i < data.length; i++) {
            lx.push(data[i].lx);
            sum.push(data[i].sum);
        }
        //主要症状
        var histogramChart2 = echarts.init(document.getElementById('histogramChart2'));
        histogramChart2.setOption({

            color: ['#FD6C88'],
            grid: {
                left: '5%',
                right: '5%',
                bottom: '20%',
                containLabel: true
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a}<br/>{b}<br/>{c}人"
            },
            calculable: true,
            yAxis: [
                {
                    type: 'category',
                    data: lx,
                    axisLine: {
                        lineStyle: {
                            color: '#FD6C88'
                        },
                    },
                    axisLabel: {
                        textStyle: {
                            color: '#fff'
                        }
                    }
                }
            ],
            xAxis: [
                {
                    type: 'value',
                    axisLine: {
                        lineStyle: {
                            color: '#FD6C88'
                        },
                    },
                    splitLine: {
                        "show": false
                    },
                    axisLabel: {
                        textStyle: {
                            color: '#fff'
                        },
                        formatter: function (value) {
                            return value + ""
                        },
                    },
                }
            ],
            series: [
                {
                    name: '到期提醒',
                    type: 'bar',
                    barWidth: 20,
                    data: sum,
                },
            ]
        })
    }


    function loadEcharts8(data) {
        var tjdata = eval(data);
        var red = [];
        var orange = [];
        var yellow = [];
        var blue = [];
        var amount = [];
        var town = [];
        for (var i = 0; i < tjdata.length; i++) {
            red.push(tjdata[i].red);
            orange.push(tjdata[i].orange);
            yellow.push(tjdata[i].yellow);
            blue.push(tjdata[i].blue);
            amount.push(tjdata[i].amount);
            town.push(tjdata[i].m1);
        }
        //网格风险点数量排行
        var lineChart2 = echarts.init(document.getElementById("lineChart2"), "vintage");
        lineChart2.setOption({
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                axisLabel: {
                    color: '#fff',
                    fontSize: 10,
                    formatter: function (value, index) {
                        var value;
                        if (value >= 1000) {
                            value = value / 1000 + 'k';
                        } else if (value < 1000) {
                            value = value;
                        }
                        return value
                    }
                }
            },
            yAxis: {
                type: 'category',
                data: town,
                axisLabel: {
                    color: '#fff',
                    fontSize: 10,
                    formatter: function (value, index) {
                        var value;
                        if (value >= 1000) {
                            value = value / 1000 + 'k';
                        } else if (value < 1000) {
                            value = value;
                        }
                        return value
                    }
                }
            },
            series: [
                {
                    name: '红色风险点',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'insideRight'
                        }
                    },
                    data: red,
                    itemStyle: {normal: {color: '#FF0000'}}
                },
                {
                    name: '橙色风险点',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'insideRight'
                        }
                    },
                    data: orange,
                    itemStyle: {normal: {color: '#FFD700'}}
                },
                {
                    name: '黄色风险点',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: yellow,
                    itemStyle: {normal: {color: '#FFFF00'}}
                },
                {
                    name: '蓝色风险点',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: blue,
                    itemStyle: {normal: {color: '#9B30FF'}}
                },
                {
                    name: '风险点总数',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: amount,
                    itemStyle: {normal: {color: '#d2d2d2'}}
                }
            ]
        })
    }
</script>
</body>
</html>
