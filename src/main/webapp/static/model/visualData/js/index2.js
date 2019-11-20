var map;

//id（地图容器id）lng，lat 经纬度 ,zoom缩放等级
function initMap(div, lng, lat, zoom, cityname, type) {
    createMap(div, lng, lat, zoom, cityname, type);//创建地图
    setMapEvent();//设置地图事件
}

//创建地图函数：
function createMap(div, lng, lat, zoom, cityname, type) {
    var map = (type ? (new BMap.Map(div)) : (new BMap.Map(div, {mapType: BMAP_NORMAL_MAP})));
    var point;
    if (!lng && !lat) {
        //默认中心点
        point = new BMap.Point(119.345938, 34.765698);
    } else {
        point = new BMap.Point(lng, lat);//定义一个中心点坐标
    }
    if (!zoom) zoom = 15;
    if (!cityname) cityname = "连云港";
    map.centerAndZoom(point, zoom);//设定地图的中心点和坐标并将地图显示在地图容器中
    map.addControl(new BMap.NavigationControl());
    map.setCurrentCity(cityname);
    // map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
    window.map = map;//将map变量存储在全局
    window.point = point;
}

//地图事件设置函数：
function setMapEvent() {
    map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
    map.enableScrollWheelZoom();//启用地图滚轮放大缩小
    map.disableDoubleClickZoom();
    map.enableKeyboard();//启用键盘上下左右键移动地图
}

//获取地图，方便自主设置地图函数
function getMap() {
    return map;
}

var symptomName = last_month_day();

var lineChart,
    lineChart2,
    lineChart3,
    lineChart4,
    histogramChart,
    pieChart1,
    pieChart2,
    pieChart3;

$(function () {


    // init();
    //init2();
    $("#el-dialog").addClass("hide");
    $(".close").click(function (event) {
        $("#el-dialog").addClass("hide");
    });

    var date = new Date();
    var numble = date.getDate();
    var today = getFormatMonth(new Date());
    $("#date1").html(today);
    $("#date2").html(today);
    $("#date3").html(today);
    $("#date4").html(today);


    lay('.demo-input').each(function () {
        laydate.render({
            type: 'month',
            elem: this,
            trigger: 'click',
            theme: '#95d7fb',
            calendar: true,
            showBottom: true,
            done: function () {
                console.log($("#startDate").val())

            }
        })
    });

})


//mapChart渲染
function init() {
    var mapdata;
    $.ajax({
        type: "POST",
        url: ctx + "/bis/qyjbxx/maplist2",
        async: false,
        dataType: 'json',
        success: function (data) {
            mapdata = eval(data.data);
            mapChart = echarts.init(document.getElementById('mapChart'));
        }
    });
    //地图
    mapChart.setOption({
        bmap: {
            center: [120.398971, 32.049625],
            zoom: 14,
            roam: true,

        },
        tooltip: {
            trigger: 'item',
            formatter: function (params, ticket, callback) {
                return params.value[2]
            }
        },
        series: [{
            type: 'scatter',
            coordinateSystem: 'bmap',
            data: mapdata
        }]
    });

    mapChart.on('click', function (params) {
        $("#el-dialog").removeClass('hide');
        $("#reportTitle").html(params.value[2]);
    });

    var bmap = mapChart.getModel().getComponent('bmap').getBMap()
    bmap.addControl(new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP, BMAP_SATELLITE_MAP]}));
    bmap.setMapStyle({style: 'midnight'})

}


function init2() {
    lineChart3 = echarts.init(document.getElementById('lineChart3'));
    lineChart3.setOption({

        color: ["#87cefa", "#ff7f50",],
        legend: {
            y: 'top',
            x: 'center',
            textStyle: {
                color: '#ffffff',

            },
            data: ['门诊人次', '住院人次'],
        },
        calculable: false,
        tooltip: {
            trigger: 'item',
            formatter: "{a}<br/>{b}<br/>{c}人"
        },
        dataZoom: {
            show: true,
            realtime: true,
            start: 0,
            end: 18,
            height: 20,
            backgroundColor: '#f8f8f8',
            dataBackgroundColor: '#e4e4e4',
            fillerColor: '#87cefa',
            handleColor: '#87cefa',
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
                        return value + "人"
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
                data: symptomName,
                boundaryGap: false,
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
                name: '门诊费用',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        lineStyle: {
                            shadowColor: 'rgba(0,0,0,0.4)'
                        }
                    }
                },
                data: [1150, 180, 2100, 2415, 1212.1, 3125, 1510, 810, 2100, 2415, 1122.1, 3215, 1510, 801, 2001, 2245, 1232.1, 3245, 1520, 830, 2200, 2145, 1223.1, 3225, 150, 80, 200, 245, 122.1, 325]
            },
            {
                name: '住院费用',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        lineStyle: {
                            shadowColor: 'rgba(0,0,0,0.4)'
                        }
                    }
                },
                data: [2500, 1000, 3000, 5005, 3200.1, 3005, 2500, 1000, 3000, 5005, 3200.1, 3005, 2500, 1000, 3000, 5005, 3200.1, 3005, 2500, 1000, 3000, 5005, 3200.1, 3005, 2500, 1000, 3000, 5005, 3200.1, 3005, 2500, 1000, 3000, 5005, 3200.1, 3005,]
            },
        ]
    });


    lineChart4 = echarts.init(document.getElementById('lineChart4'));
    lineChart4.setOption({

        color: ["#87cefa", "#ff7f50",],
        calculable: false,
        tooltip: {
            trigger: 'item',
            formatter: "{a}<br/>{b}<br/>{c}元"
        },
        dataZoom: {
            show: true,
            realtime: true,
            start: 0,
            end: 18,
            height: 20,
            backgroundColor: '#f8f8f8',
            dataBackgroundColor: '#e4e4e4',
            fillerColor: '#87cefa',
            handleColor: '#87cefa',
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
                        return value + "元"
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
                data: symptomName,
                boundaryGap: false,
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
                name: '医疗费用',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        lineStyle: {
                            shadowColor: 'rgba(0,0,0,0.4)'
                        }
                    }
                },
                data: [1500, 800, 1200, 2450, 1122.1, 1325, 1150, 180, 1200, 1245, 1122.1, 1325, 150, 180, 1200, 2145, 1212.1, 3215, 1510, 180, 2100, 2415, 122.1, 325, 150, 80, 200, 245, 122.1, 325].reverse()
            },
        ]
    });

    //年龄分布
    pieChart2 = echarts.init(document.getElementById('pieChart2'));
    pieChart2.setOption({
        color: ["#32cd32", "#ff7f50", "#87cefa", "#FD6C88", "#4b5cc4", "#faff72"],
        tooltip: {
            trigger: 'item',
            formatter: "{a}<br/>{b}<br/>{c}人"
        },
        calculable: true,
        series: [
            {
                name: '发病人数',
                type: 'pie',
                radius: [30, 110],
                center: ['50%', '50%'],
                roseType: 'area',
                x: '50%',


                sort: 'ascending',
                data: [
                    {value: 10, name: '婴儿(1-3岁)'},
                    {value: 5, name: '少儿(4-10岁)'},
                    {value: 15, name: '少年(10-18岁)'},
                    {value: 25, name: '青年(18-45岁)'},
                    {value: 125, name: '中年(45-60岁)'},
                    {value: 175, name: '老年(60岁以上)'},
                ]
            }
        ]
    })

    //医疗费用组成
    pieChart3 = echarts.init(document.getElementById('pieChart3'));
    pieChart3.setOption({
        color: ["#32cd32", "#ff7f50", "#87cefa", "#FD6C88", "#4b5cc4", "#faff72"],
        tooltip: {
            trigger: 'item',
            formatter: "{a}<br/>{b}<br/>{c}元"
        },
        calculable: true,
        series: [
            {
                name: '发病人数',
                type: 'pie',
                radius: [30, 110],
                center: ['50%', '50%'],
                roseType: 'area',
                x: '50%',
                sort: 'ascending',
                data: [
                    {value: 10, name: '诊察费用'},
                    {value: 500, name: '检查费用'},
                    {value: 150, name: '检验费用'},
                    {value: 250, name: '西药费用'},
                    {value: 125, name: '中药费用'},
                    {value: 1750, name: '手术费用'},
                ]
            }
        ]
    })
}


window.addEventListener("resize", function () {
    mapChart.resize();
    pieChart1.resize();
    lineChart.resize();
    histogramChart.resize();
    lineChart2.resize();
    //lineChart3.resize();
    //lineChart4.resize();
    //pieChart2.resize();
    //pieChart3.resize();
});