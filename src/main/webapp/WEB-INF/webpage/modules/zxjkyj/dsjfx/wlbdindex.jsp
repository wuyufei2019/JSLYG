<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>统计分析</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/model/js/aqjg/aqjdjc/tjfx/view.js?v=1.1"></script> --%>
</head>
<body>

     <h2 style="text-align:center">物料储罐历史波动图</h2>
     <div style="margin:20px 0 10px 0;"></div>
     <div id="searchFrom" style="text-align: center"><c:if test="${empty usertype }">
               <input type="text" id="check_company_name" name="check_company_name"
                    class="easyui-combobox" style="height: 30px;"
                    data-options="editable:true ,valueField: 'm1',textField: 'm1',url:'${ctx}/zxjkyj/cgssjc/qyjson',prompt: '企业名称' " />
          </c:if> <input type="text" name="starttime" id="starttime" class="easyui-datebox"
               style="height: 30px;"
               data-options="panelHeight:'auto' ,editable:false , prompt: '开始日期' " /> <input
               type="text" id="endtime" name="endtime" class="easyui-datebox" style="height: 30px;"
               data-options="panelHeight:'auto' ,editable:false , prompt: '结束日期' " />
          <button class="btn btn-info btn-sm" onclick="search()">
               <i class="fa fa-search"></i> 查询
          </button>
          <button class="btn btn-danger btn-sm" onclick="reset()">
               <i class="fa fa-refresh"></i> 全部
          </button></div>
     <div class="ibox-content" style="height:500px;width:100%">
          <div id="main" style="height:400px;width:100%"></div>
     </div>
     </div>
     </div>

     <script type="text/javascript">
     var param;
     var y = [];//y轴
	 var legend = [];//legend值
	 var x = [];//x轴
     $(function(){
    	 initParam();
    	 getDataLoadChart(param);
     });
  	 function getDataLoadChart(param){
			$.ajax({
				url : ctx + "/zxjkyj/dsjfx/getlinejson",
				type : "POST",
				data : param,
				dataType : "json",
				success : function(data) {
					y = [];//y轴
					legend = [];//legend值
					x = [];//x轴
				    x = data.date;
					for (key in data) {
						if (key != "date") {
							y.push({
								"name" : key,
								"type" : "line",
								"data" : data[key]
							});
							legend.push(key);
						}
					}
					loadEcharts();
				}
 		});
	 }
	 function initParam(){
		 param={"starttime" : $("#starttime").datebox('getValue'),
					"endtime" : $("#endtime").datebox('getValue')};
 	 	 if('${usertype}'!='1'){
 		 param.qyid= $("#check_company_name").combobox('getText');
 		 }
	 }
		function search() {
			var d1 = $("#starttime").datebox('getValue');
			var d2 = $("#endtime").datebox('getValue');
			if ((d2 == "" && d1 == "") || (d1 != "" && d2 != "")) {
				 initParam();
		    	 getDataLoadChart(param);
			} else {
				layer.msg("请选择日期！", {
					time : 1000
				});
				return;
			}
		}
		function reset() {
			$("#searchFrom").form("clear");
			param={};
	    	 getDataLoadChart(param);
		}
		// 路径配置
 		require.config({
 			paths : {
 				echarts : '${ctxStatic}/echarts'
 			}
 		});
		function loadEcharts() {
			require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/line' ], function(ec) {
				var myChart = ec.init(document.getElementById('main'));
				myChart.showLoading();
						myChart.hideLoading();
						myChart.setOption({

							tooltip : {
								trigger : 'axis'
							},
							legend : {
								data : legend
							},
							toolbox : {
								show : true,
								feature : {
									magicType : {
										show : true,
										type : [ 'line', 'bar' ]
									},
									restore : {
										show : true
									},
									saveAsImage : {
										show : true
									}
								}
							},
							xAxis : [ {
								type : 'category',
								boundaryGap : false,
								data : x
							} ],
							yAxis : [ {
								type : 'value',
								'name':'m³',
							} ],
							series : y
						});

					});
			}
    
	</script>
</body>
</html>