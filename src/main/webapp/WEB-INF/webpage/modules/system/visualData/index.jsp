<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title></title>
    <meta name="decorator" content="default"/>
    <title>连云区公共安全监管（应急指挥）云平台</title>
    <link href="${ctxStatic}/model/visualData/css/common.css" rel="stylesheet">
    <script src="${ctxStatic}/jquery/jquery-2.1.1.js"></script>
    <script src="${ctxStatic}/model/visualData/js/Plugin/echarts.min.js"></script>
    <script src="${ctxStatic}/model/visualData/js/Plugin/bmap.min.js"></script>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>

    <script src="${ctxStatic}/model/visualData/js/common2.js"></script>
    <script src="${ctxStatic}/model/visualData/js/index2.js"></script>
    <script src="${ctxStatic}/layer-v2.0/laydate/laydate.js"></script>
    <script type="text/javascript" src="${ctxStatic}/model/js/qyxx/qyjbxx/qyfbmapindex.js?v=1.4"></script>
    <script type="text/javascript" src="http://developer.baidu.com/map/custom/stylelist.js"></script>
    <script type="text/javascript"
            src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
    <script type="text/javascript"
            src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
    <style>
        .trans90 {
            transform: rotate(90deg);
            -ms-transform: rotate(90deg); /* IE 9 */
            -moz-transform: rotate(90deg); /* Firefox */
            -webkit-transform: rotate(90deg); /* Safari 和 Chrome */
            -o-transform: rotate(90deg);
        }
    </style>
</head>
<body>
<!--顶部-->
<header class="header left">
    <div class="left nav">
        <ul>
            <li class="nav_active"><i class="nav_1"></i><a href="${ctx}/a/vd_page">总体概况</a></li>
            <li><i class="nav_7"></i><a href="${ctx}/a/vd_page3">分类统计</a></li>


        </ul>
    </div>
    <div class="header_center left" style="position:relative">
        <h2><strong>连云区应急指挥平台大数据</strong></h2>
    </div>
    <div class="right nav text_right">
        <ul>
            <li><i class="nav_2"></i><a href="${ctx}/bis/qyjbxx/onemap2">应急大数据</a></li>
            <li style="display:none"><i class="nav_11"></i><a href="${ctx}/a/vd_page5">重大危险源</a></li>
            <li><i class="nav_6"></i><a href="${ctx}/a/vd_page8">视频监控</a></li>
            <li><i class="nav_3"></i><a href="${ctx}/">进入平台</a></li>
        </ul>
    </div>
</header>

<!--内容部分-->
<div class="con left">
    <!--数据总概-->
    <div class="con_div">
        <div class="con_div_text left">
            <div class="con_div_text01 left">
                <img src="${ctxStatic}/model/visualData/images/info_1.png" class="left text01_img"/>
                <div class="left text01_div">
                    <p>企业总数</p>
                    <p><span class="afont" id="qysum"></span></p>
                </div>
            </div>
            <div class="con_div_text01 right">
                <img src="${ctxStatic}/model/visualData/images/info_2.png" class="left text01_img"/>
                <div class="left text01_div">
                    <p>两重点一重大</p>
                    <p><span class="afont" id="zdsum"></span></p>
                </div>
            </div>
        </div>
        <div class="con_div_text left">
            <div class="con_div_text01 left">
                <img src="${ctxStatic}/model/visualData/images/info_3.png" class="left text01_img"/>
                <div class="left text01_div">
                    <p>危险作业</p>
                    <p><span class="afont" id="wxzy"></span></p>
                </div>
            </div>
            <div class="con_div_text01 right">
                <img src="${ctxStatic}/model/visualData/images/info_4.png" class="left text01_img"/>
                <div class="left text01_div">
                    <p>危报总数</p>
                    <p><span class="afont" id="wbsum"></span></p>
                </div>
            </div>
        </div>
        <div class="con_div_text left">

            <div class="con_div_text01 left">
                <img src="${ctxStatic}/model/visualData/images/info_5.png" class="left text01_img"/>
                <div class="left text01_div">
                    <p>网格点</p>
                    <p><span class="afont" id="wgsum"></span></p>
                </div>
            </div>
            <div class="con_div_text01 right">
                <img class="trans90 left text01_img" src="${ctxStatic}/model/visualData/images/info_6.png"/>
                <div class="left text01_div">
                    <p>风险点</p>
                    <p><span class="afont" id="fxdsum"></span></p>
                </div>
            </div>
        </div>
    </div>
    <!--统计分析图-->
    <div class="div_any">
        <div class="left div_any01">
            <div class="div_any_child">
                <div class="div_any_title"><img src="${ctxStatic}/model/visualData/images/title_1.png">实时风险点统计</div>
                <p id="pieChart1" class="p_chart"></p>
            </div>
            <div class="div_any_child">
                <div class="div_any_title"><img src="${ctxStatic}/model/visualData/images/title_2.png">各风险等级企业数</div>
                <p id="histogramChart" class="p_chart"></p>
            </div>
        </div>
        <div class="div_any02 left ">
            <div class="div_any_child div_height">
                <div class="div_any_title any_title_width" style="z-index: 999"><img
                        src="${ctxStatic}/model/visualData/images/title_0.png">企业地图分布
                </div>
                <div id="mapChart" style="width:96%;height:96%;display: inline-block;margin:2%"></div>
            </div>
        </div>
        <div class="right div_any01">
            <div class="div_any_child">
                <div class="div_any_title"><img src="${ctxStatic}/model/visualData/images/title_3.png">实时隐患排查数(本周)</div>
                <p id="lineChart" class="p_chart"></p>
            </div>
            <div class="div_any_child">
                <div class="div_any_title"><img src="${ctxStatic}/model/visualData/images/title_4.png">实时隐患整改数(本周)</div>
                <p id="lineChart2" class="p_chart"></p>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        var ctx = '${ctx}';
        initMap("mapChart");
        getQyfbJson();
        var mapStyle = {
            features: ["road", "building", "water", "land"],//隐藏地图上的"poi",
            style: 'midnight',
        };
        map.setMapStyle(mapStyle);


        $.ajax({
            url: '${ctx}/syshome/kshsj',
            type: "POST",
            success: function (data) {
                var tjdata = eval(data);
                for (var key in tjdata) {
                    for (var i in tjdata[key]) {
                        $("#" + i).text(tjdata[key][i]);
                    }
                }
                loadEcharts(tjdata[0]);
                loadEcharts2(tjdata[0]);
            }
        });

        $.ajax({
            url: '${ctx}/syshome/yhpcs',
            type: "POST",
            success: function (data) {
                loadEcharts3(data);
            }
        });

        $.ajax({
            url: '${ctx}/syshome/yhzgs',
            type: "POST",
            success: function (data) {
                loadEcharts4(data);
            }
        });

    })

    function loadEcharts(data) {
        pieChart1 = echarts.init(document.getElementById('pieChart1'));
        pieChart1.setOption({

            color: ["#DC143C", "#FF7F50", "#FFFF00", "#4169E1",],

            legend: {
                y: '260',
                x: 'center',
                textStyle: {
                    color: '#ffffff',

                },
                data: ['红色风险点', '橙色风险点', '黄色风险点', '蓝色风险点'],
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a}<br/>{b}<br/>{c}个 ({d}%)"
            },
            calculable: false,
            series: [
                {
                    name: '采集数据量',
                    type: 'pie',
                    radius: ['40%', '70%'],
                    center: ['50%', '45%'],
                    itemStyle: {
                        normal: {
                            label: {
                                show: false
                            },
                            labelLine: {
                                show: false
                            }
                        },
                        emphasis: {
                            label: {
                                show: true,
                                position: 'center',
                                textStyle: {
                                    fontSize: '20',
                                    fontWeight: 'bold'
                                }
                            }
                        }
                    },
                    data: [
                        {value: data.fxdr, name: '红色风险点'},
                        {value: data.fxdc, name: '橙色风险点'},
                        {value: data.fxdh, name: '黄色风险点'},
                        {value: data.fxdl, name: '蓝色风险点'}
                    ]
                }
            ]
        });
    }

    function loadEcharts2(data) {
        histogramChart = echarts.init(document.getElementById('histogramChart'));
        histogramChart.setOption({
            color: ["#4169E1", "#FFFF00", "#FF7F50", "#DC143C"],
            legend: {
                y: '240',
                x: 'center',
                data: ['蓝色风险企业', '黄色风险企业', '橙色风险企业', '红色风险企业'],
                textStyle: {
                    color: '#ffffff',

                }
            },

            calculable: false,


            grid: {
                left: '5%',
                right: '5%',
                bottom: '20%',
                containLabel: true
            },

            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },

            xAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        show: true,
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    splitLine: {
                        lineStyle: {
                            color: ['#f2f2f2'],
                            width: 0,
                            type: 'solid'
                        }
                    }

                }
            ],

            yAxis: [
                {
                    type: 'category',
                    data: ['蓝色风险企业', '黄色风险企业', '橙色风险企业', '红色风险企业'],
                    axisLabel: {
                        show: true,
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    splitLine: {
                        lineStyle: {
                            width: 0,
                            type: 'solid'
                        }
                    }
                }
            ],

            series: [
                {
                    name: '蓝色风险企业',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                    data: [data.fxgkl, , ,]
                },
                {
                    name: '黄色风险企业',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                    data: [, data.fxgkh, ,]
                },
                {
                    name: '橙色风险企业',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                    data: [, , data.fxgkc,]
                },
                {
                    name: '红色风险企业',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                    data: [, , , data.fxgkr]
                }

            ]
        });
    }

    function loadEcharts3(data) {
        data = JSON.parse(data);
        lineChart = echarts.init(document.getElementById('lineChart'));
        lineChart.setOption({

            color: ["#DC143C", "#FF7F50", "#FFFF00", "#4169E1", "#da70d6"],
            legend: {
                y: '220',
                x: 'center',
                textStyle: {
                    color: '#ffffff',

                },
                data: ['红色风险点', '橙色风险点', '黄色风险点', '蓝色风险点', '自定义风险点'],
            },
            calculable: false,
            tooltip: {
                trigger: 'item',
                formatter: "{a}<br/>{b}<br/>{c}条"
            },
            yAxis: [
                {
                    type: 'value',
                    axisLine: {onZero: false},
                    axisLine: {
                        lineStyle: {
                            color: '#034c6a'
                        },
                    },

                    axisLabel: {
                        textStyle: {
                            color: '#fff'
                        },
                        formatter: function (value) {
                            return value + "条"
                        },
                    },
                    splitLine: {
                        lineStyle: {
                            width: 0,
                            type: 'solid'
                        }
                    }
                }
            ],
            xAxis: [
                {
                    type: 'category',
                    data: ['周一', '周二', '周三', '周四', '周五', '周六', '周七'],
                    axisLine: {
                        lineStyle: {
                            color: '#034c6a'
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
                    splitLine: {
                        lineStyle: {
                            width: 0,
                            type: 'solid'
                        }
                    },
                }
            ],
            grid: {
                left: '5%',
                right: '5%',
                bottom: '30%',
                containLabel: true
            },
            series: [
                {
                    name: '红色风险点',
                    type: 'line',
                    smooth: true,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                shadowColor: 'rgba(0,0,0,0.4)'
                            }
                        }
                    },
                    data: data.red
                },
                {
                    name: '橙色风险点',
                    type: 'line',
                    smooth: true,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                shadowColor: 'rgba(0,0,0,0.4)'
                            }
                        }
                    },
                    data: data.orange
                },
                {
                    name: '黄色风险点',
                    type: 'line',
                    smooth: true,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                shadowColor: 'rgba(0,0,0,0.4)'
                            }
                        }
                    },
                    data: data.yellow
                },
                {
                    name: '蓝色风险点',
                    type: 'line',
                    smooth: true,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                shadowColor: 'rgba(0,0,0,0.4)'
                            }
                        }
                    },
                    data: data.blue
                },
                {
                    name: '自定义风险点',
                    type: 'line',
                    smooth: true,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                shadowColor: 'rgba(0,0,0,0.4)'
                            }
                        }
                    },
                    data: data.custpoint
                }
            ]
        });
    }

    function loadEcharts4(data) {
        data = JSON.parse(data);
        lineChart2 = echarts.init(document.getElementById('lineChart2'));
        lineChart2.setOption({

            color: ["#DC143C", "#FF7F50", "#FFFF00", "#4169E1", "#da70d6"],
            legend: {
                y: '260',
                x: 'center',
                textStyle: {
                    color: '#ffffff',

                },
                data: ['红色风险点', '橙色风险点', '黄色风险点', '蓝色风险点', '自定义风险点'],
            },
            calculable: false,
            tooltip: {
                trigger: 'item',
                formatter: "{a}<br/>{b}<br/>{c}条"
            },
            yAxis: [
                {
                    type: 'value',
                    axisLine: {onZero: false},
                    axisLine: {
                        lineStyle: {
                            color: '#034c6a'
                        },
                    },

                    axisLabel: {
                        textStyle: {
                            color: '#fff'
                        },
                        formatter: function (value) {
                            return value + "条"
                        },
                    },
                    splitLine: {
                        lineStyle: {
                            width: 0,
                            type: 'solid'
                        }
                    }
                }
            ],
            xAxis: [
                {
                    type: 'category',
                    data: ['周一', '周二', '周三', '周四', '周五', '周六', '周七'],
                    axisLine: {
                        lineStyle: {
                            color: '#034c6a'
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
                    splitLine: {
                        lineStyle: {
                            width: 0,
                            type: 'solid'
                        }
                    },
                }
            ],
            grid: {
                left: '5%',
                right: '5%',
                bottom: '20%',
                containLabel: true
            },
            series: [
                {
                    name: '红色风险点',
                    type: 'line',
                    smooth: true,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                shadowColor: 'rgba(0,0,0,0.4)'
                            }
                        }
                    },
                    data: data.red
                },
                {
                    name: '橙色风险点',
                    type: 'line',
                    smooth: true,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                shadowColor: 'rgba(0,0,0,0.4)'
                            }
                        }
                    },
                    data: data.orange
                },
                {
                    name: '黄色风险点',
                    type: 'line',
                    smooth: true,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                shadowColor: 'rgba(0,0,0,0.4)'
                            }
                        }
                    },
                    data: data.yellow
                },
                {
                    name: '蓝色风险点',
                    type: 'line',
                    smooth: true,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                shadowColor: 'rgba(0,0,0,0.4)'
                            }
                        }
                    },
                    data: data.blue
                },
                {
                    name: '自定义风险点',
                    type: 'line',
                    smooth: true,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                shadowColor: 'rgba(0,0,0,0.4)'
                            }
                        }
                    },
                    data: data.custpoint
                }
            ]
        });
    }

</script>
</body>
</html>