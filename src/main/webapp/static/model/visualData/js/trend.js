var symptomName = last_year_month();


$(function(){


  init();

})

function loadEcharts7(data) {
    var myColor = ['#1089E7', '#F57474', '#56D0E3', '#F8B448', '#8B78F6'];
    var tjdata=eval(data);
    var wgname=[];
    var ycs=[];
    var bl=[];
    for(var i=0;i<tjdata.length;i++){
        wgname.push(tjdata[i].wgname);
        ycs.push(tjdata[i].yxcs);
        bl.push(tjdata[i].bl);
    }
    //乡镇网格巡检率排行
    var histogramChart3 = echarts.init(document.getElementById('histogramChart3'));
    histogramChart3.setOption({
        grid: {
            top: '12%',
            left: '30%'
        },
        xAxis: {
            show: false
        },
        yAxis: [{
            show: true,
            data:  wgname,
            inverse: true,
            axisLine: {
                show: false
            },
            splitLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                color: '#fff',
                formatter: (value, index) => {
                return [`{lg|${index+1}}  ` + '{title|' + value + '} '].join('\n')
            },
            rich: {
                lg: {
                    backgroundColor: '#339911',
                    color: '#fff',
                    borderRadius: 15,
                    // padding: 5,
                    align: 'center',
                    width: 15,
                    height: 15
                },
            }
        },


    }, {
        show: true,
        inverse: true,
        data: ycs,
        axisLabel: {
            textStyle: {
                fontSize: 12,
                color: '#fff',
            },
        },
        axisLine: {
            show: false
        },
        splitLine: {
            show: false
        },
        axisTick: {
            show: false
        },

    }],
    series: [{
        name: '条',
        type: 'bar',
        yAxisIndex: 0,
        data: bl,
        barWidth: 10,
        itemStyle: {
            normal: {
                barBorderRadius: 20,
                color: function(params) {
                    var num = myColor.length;
                    return myColor[params.dataIndex % num]
                },
            }
        },
        label: {
            normal: {
                show: true,
                position: 'inside',
                formatter: '{c}%'
            }
        },
    }, {
        name: '框',
        type: 'bar',
        yAxisIndex: 1,
        barGap: '-100%',
        data: [100, 100, 100, 100,100, 100, 100, 100, 100, 100],
        barWidth: 15,
        itemStyle: {
            normal: {
                color: 'none',
                borderColor: '#00c1de',
                borderWidth: 1,
                barBorderRadius: 15,
            }
        }
    }, ]
})
}

 function init(){

   var myColor = ['#1089E7', '#F57474', '#56D0E3', '#F8B448', '#8B78F6'];

   //主要传染病
   var histogramChart1 = echarts.init(document.getElementById('histogramChart1'));
   histogramChart1.setOption({

     color:['#5bc0de'],
     grid:{
         left: '5%',
         right: '5%',
         bottom: '5%',
         containLabel: true
     },
     tooltip : {
        trigger: 'item',
        formatter: "{a}<br/>{b}<br/>{c}人"
    },
     calculable : true,
     xAxis : [
         {
             type : 'category',
             data : ['感染性腹泻','流行性感冒','登革热','手足口病','水痘','流行性眼腺炎','猩红热','甲型病毒性肝炎','疟疾'],
             axisLine:{
                  lineStyle:{
                      color: '#5bc0de'
                  },
              },
              axisLabel : {
                interval:0,
                rotate:40,
                  textStyle: {
                      color: '#fff'
                  }
              }
         }
     ],
     yAxis : [
         {
             type : 'value',
             axisLine:{
                 lineStyle:{
                     color: '#5bc0de'
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
     series : [
         {
             name:'主要传染病',
             type:'bar',
             barWidth : 20,
             data:[2210,1085,926,669,634,452,412,312,156],
         },
     ]
   })


   //疾病发病趋势
     var lineChart2 = echarts.init(document.getElementById("lineChart2"), "vintage");
     lineChart2.setOption({
         tooltip : {
             trigger: 'axis',
             axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                 type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
             }
         },
         grid: {
             left: '5%',
             right: '5%',
             top: '3%',
             bottom: '10%',
             containLabel: true
         },
         xAxis:  {
             type: 'value',
             axisLabel: {
                 color: '#fff',
                 fontSize: 10,
                 formatter: function(value,index){
                     var value;
                     if (value >=1000) {
                         value = value/1000+'k';
                     }else if(value <1000){
                         value = value;
                     }
                     return value
                 }
             }
         },
         yAxis: {
             type: 'category',
             data: ['一','二','三','周四'],
             axisLabel: {
                 color: '#fff',
                 fontSize: 10,
                 formatter: function(value,index){
                     var value;
                     if (value >=1000) {
                         value = value/1000+'k';
                     }else if(value <1000){
                         value = value;
                     }
                     return value
                 }
             }
         },
         series: [
             {
                 name: '未整改',
                 type: 'bar',
                 stack: '总量',
                 label: {
                     normal: {
                         show: false,
                         position: 'insideRight'
                     }
                 },
                 data: [19, 21, 0, 35],
                 itemStyle: {normal:{color:'#ff0000'}}
             },
             {
                 name: '已整改',
                 type: 'bar',
                 stack: '总量',
                 label: {
                     normal: {
                         show: false,
                         position: 'insideRight'
                     }
                 },
                 data: [60, 81, 49, 74],
                 itemStyle: {normal:{color:'#4CAF50'}}
             },
             {
                 name: '隐患总数',
                 type: 'bar',
                 stack: '总量',
                 label: {
                     normal: {
                         show: true,
                         position: 'insideRight'
                     }
                 },
                 data: [79, 102, 49, 109],
                 itemStyle: {normal:{color:'#d2d2d2'}}
             }
         ]
     })

   /*lineChart2.setOption({
     title: {
        text: '疾病发病趋势',
        textStyle:{
           fontSize:16,
           color:'#32cd32'
       },
       x:"center"
    },
     color:["#32cd32"],
     grid:{
         left: '15%',
         right: '5%',
         bottom: '25%',

     },
     tooltip : {
          trigger: 'item',
          formatter: "{a}<br/>{b}<br/>{c}人"
      },

     calculable : true,
         yAxis: [
             {
                 type: 'value',
                 axisLine:{
                     lineStyle:{
                         color: '#32cd32'
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
                 data : symptomName,
                 boundaryGap : false,
                 axisLine:{
                     lineStyle:{
                         color: '#32cd32'
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
     series : [
         {
             name:'疾病发病人数',
             type:'line',
             smooth:true,
             itemStyle: {normal: {areaStyle: {type: 'default'}}},
             data:[520, 232,701, 434, 190, 230, 210,120, 132, 101, 134, 890]
         },
     ]

   })*/

/*   //风险预警雷达图
   var pieChart1 = echarts.init(document.getElementById('pieChart1'));
   pieChart1.setOption({
	    tooltip: {},
	    radar: {
	        // shape: 'circle',
	        name: {
	            textStyle: {
	                color: '#fff',
	                backgroundColor: '#999',
	                borderRadius: 3,
	                padding: [3, 5]
	           }
	        },
	        indicator: [
	           { name: '机械伤害', max: 6500},
	           { name: '容器爆炸', max: 16000},
	           { name: '锅炉爆炸', max: 30000},
	           { name: '透水', max: 38000},
	           { name: '烫伤', max: 52000},
	           { name: '中毒窒息', max: 16000},
	           { name: '起重伤害', max: 30000},
	           { name: '其他伤害', max: 38000},
	           { name: '物理打击', max: 52000},
	           { name: '高处坠落', max: 25000}
	        ]
	    },
	    series: [{
	        name: '',
	        type: 'radar',
	        // areaStyle: {normal: {}},
	        data : [
	            {
	                value : [4300, 10000, 28000, 35000, 50000, 14000, 10000, 28000, 35000, 14000],
	                name : ''
	            },
	             {
	                value : [5000, 14000, 28000, 31000, 42000, 10000, 14000, 28000, 31000, 10000],
	                name : ''
	            }
	        ]
	    }]
   })*/

   //性别分布
   var labelFromatter = {
       normal : {
           label : {
              position : 'center',
               formatter : function (params){
                 console.log(params)
                 if(params.name == "女性"){
                   return "女性"+":"+(params.percent + '%')
                 }else{
                   return "男性"+":"+(params.percent + '%')
                 }
               },
           },
           labelLine : {
               show : false
           }
       },
   };

   var pieChart2 = echarts.init(document.getElementById('pieChart2'));
   pieChart2.setOption({
	    radar: [
	        {
	            indicator: [
	                { text: '车间' },
	                { text: '物料' },
	                { text: '仓库' },
	                { text: '危险作业' }
	                ,{ text: '生产作业' },
	                { text: '外来作业' },
	                { text: '安全管理' },
	                { text: '职业病危害' }
	            ],
	            center: ['50%', '50%'],
	            radius: 120,
	            startAngle: 90,
	            splitNumber: 4,
	            shape: 'circle',
	            name: {
	                formatter:'【{value}】',
	                textStyle: {
	                    color:'#72ACD1'
	                }
	            },
	            splitArea: {
	                areaStyle: {
	                    color: ['rgba(114, 172, 209, 0.2)',
	                    'rgba(114, 172, 209, 0.4)', 'rgba(114, 172, 209, 0.6)',
	                    'rgba(114, 172, 209, 0.8)', 'rgba(114, 172, 209, 1)'],
	                    shadowColor: 'rgba(0, 0, 0, 0.3)',
	                    shadowBlur: 10
	                }
	            },
	            axisLine: {
	                lineStyle: {
	                    color: 'rgba(255, 255, 255, 0.5)'
	                }
	            },
	            splitLine: {
	                lineStyle: {
	                    color: 'rgba(255, 255, 255, 0.5)'
	                }
	            }
	        }
	    ],
	    series: [
	        {
	            name: '雷达图',
	            type: 'radar',
	            itemStyle: {
	                emphasis: {
	                    // color: 各异,
	                    lineStyle: {
	                        width: 4
	                    }
	                }
	            },
	            data: [
	                {
	                    value: [6, 1, 243, 541, 10, 981, 200, 625],
	                    name: '图一',
	                    symbol: 'rect',
	                    symbolSize: 5,
	                    lineStyle: {
	                        normal: {
	                            type: 'dashed'
	                        }
	                    }
	                }
	            ]
	        }
	    ]
   })

 }
