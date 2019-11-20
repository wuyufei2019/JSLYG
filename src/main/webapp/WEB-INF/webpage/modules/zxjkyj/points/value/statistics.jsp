<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>统计分析</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
    <%-- <script type="text/javascript" src="${ctx}/static/model/js/aqjg/aqjdjc/tjfx/view.js?v=1.1"></script> --%>
</head>
<body>

<div class="easyui-panel" style="width:100%;height:100%;">
    <div class="easyui-layout" data-options="fit:true" border="false">
        <div data-options="region:'west'" border="false" style="width:100%;">
            <div class="ibox-content">
                <div class="echart" id="main" style="width:100%;height:380px"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var data = eval('${result}');
    var loadData = [];
    $.each(data, function (index, item) {
        loadData.push([new Date(item.updatetime), item.value]);
    });
    $(function () {
        var height = $(window).height();
        $(".echart").height(height * 2 / 5);
        loadEcharts('main', '统计');
    });
    // 路径配置
    require.config({
        paths: {
            echarts: '${ctxStatic}/echarts'
        }
    });

    function loadEcharts(div, title, legend, data) {
        require(['echarts', 'echarts/chart/line', 'echarts/chart/scatter'], function (ec) {
            var myChart = ec.init(document.getElementById(div));
            myChart.setOption({
                title: {
                    text: '时间坐标折线图',
                },
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        var date = new Date(params.value[0]);
                        data = date.getFullYear() + '-'
                            + (date.getMonth() + 1) + '-'
                            + date.getDate() + ' '
                            + date.getHours() + ':'
                            + date.getMinutes();
                        return data + '<br/>'
                            + params.value[1]
                    }
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                dataZoom: {
                    show: true,
                    start: 70
                },
                grid: {
                    y2: 80
                },
                xAxis: [
                    {
                        type: 'time',
                        splitNumber: 10
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: 'series1',
                        type: 'line',
                        showAllSymbol: true,
                        symbolSize: function (value) {
                            return 2;
                        },
                        data: loadData,
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        },
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        }
                    }
                ]
            });
        });
    }
</script>
</body>
</html>