<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>应急队伍管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
</head>
<body>

     <form id="inputForm" action="${ctx}/erm/yjdw/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">队伍名称：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M1" class="easyui-textbox " value="${res.m1 }" data-options="required:'true',validType:'length[0,20]'"/></td>
					<td class="width-15 active"><label class="pull-right">队伍类型：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M2" class="easyui-combobox " value="${res.m2 }" data-options="editable:false ,data: [
																																												        {value:'消防',text:'消防'},
																																												        {value:'公安',text:'公安'},
																																												        {value:'交通',text:'交通'},
																																												        {value:'燃气',text:'燃气'},
																																												        {value:'危化品',text:'危化品'},
																																												        {value:'电力',text:'电力'},
																																												        {value:'供水',text:'供水'},
																																												        {value:'排水',text:'排水'},
																																												        {value:'医疗',text:'医疗'},
																																												        {value:'环境',text:'环境'},
																																												        {value:'地震',text:'地震'},
																																												        {value:'安监',text:'安监'},
																																												        {value:'气象',text:'气象'},
																																												        {value:'搜救',text:'搜救'},
																																												        {value:'其他',text:'其他'} ]
																																										    "/></td>
				</tr>

				<tr >
					<td class="width-15 active"><label class="pull-right">队伍地址：</label></td>
					<td class="width-35" colspan="3">
						<input style="width: 100%;height: 30px;" name="M3" class="easyui-textbox" value="${res.m3 }" data-options="validType:'length[0,50]'"/>
					</td>
				</tr>
				
				<tr>
					<td id="map_test" class="width-15 active" ><label class="pull-right">位置 ：</label></td><td colspan="3">
						<label style="margin-left: 10px;height:30px;line-height:30px;">经度：</label>
						<input id="erm_map_c_x" name="M12" class="easyui-textbox" value="${res.m12 }" readonly="readonly" style="width:35%;height: 30px;"  data-options="" />
						<label style="margin-left: 10px;height:30px;line-height:30px;">纬度：</label>
						<input id="erm_map_c_y" name="M13" class="easyui-textbox" value="${res.m13 }" readonly="readonly" style="width:35%;height: 30px;"   data-options="" />
						<a class="btn btn-primary" onclick="showMapXY( )" style="width:60px;">定位</a></td></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">主要负责人：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"" name="M4" class="easyui-textbox " value="${res.m4 }" data-options="validType:'length[0,20]'"/></td>
					<td class="width-15 active"><label class="pull-right">应急电话：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"" name="M5" class="easyui-textbox" value="${res.m5 }" data-options="validType:'mobile'"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">主管部门：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"" name="M6" class="easyui-textbox " value="${res.m6 }" data-options="validType:'length[0,20]'"/></td>
					<td class="width-15 active"><label class="pull-right">总人数：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M7" class="easyui-numberbox" value="${res.m7 }" data-options="min:0,max:9999999999" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">成立时间：</label></td>
					<td class="width-35" ><input id="M9" name="M9" class="easyui-datebox" style="width: 100%;height: 30px;" value="${res.m9 }" data-options=" editable:true " /></td>
				</tr>
					
				<tr>
					<td class="width-15 active"><label class="pull-right">应急功能：</label></td>
					<td class="width-85" colspan="3">
					<input name="M8" type="text" value="${res.m8 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,250]'">
					</td>		
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">主要装备：</label></td>
					<td class="width-85" colspan="3">
					<input name="M11" type="text" value="${res.m11 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,250]'">
					</td>		
				</tr>
				
				<tr> 
					<td class="width-15 active"><label class="pull-right">应对事故类型：</label></td><td colspan="3"><input type="checkbox" id="ydlx1" name="M10" value="1" /><a style="cursor:default;" onclick="ydlxChange1()">物体打击</a>
																		                <input type="checkbox" id="ydlx2" name="M10" value="2" /><a style="cursor:default;" onclick="ydlxChange2()">车辆伤害</a>
																		                <input type="checkbox" id="ydlx3" name="M10" value="3" /><a style="cursor:default;" onclick="ydlxChange3()">机械伤害</a>
																		                <input type="checkbox" id="ydlx4" name="M10" value="4" /><a style="cursor:default;" onclick="ydlxChange4()">起重伤害</a>
																		                <input type="checkbox" id="ydlx5" name="M10" value="5" /><a style="cursor:default;" onclick="ydlxChange5()">触电</a><br/>
																		                <input type="checkbox" id="ydlx6" name="M10" value="6" /><a style="cursor:default;" onclick="ydlxChange6()">淹溺</a>
																		                <input type="checkbox" id="ydlx7" name="M10" value="7" /><a style="cursor:default;" onclick="ydlxChange7()">灼烫</a>
																		                <input type="checkbox" id="ydlx8" name="M10" value="8" /><a style="cursor:default;" onclick="ydlxChange8()">火灾</a>
																		                <input type="checkbox" id="ydlx9" name="M10" value="9" /><a style="cursor:default;" onclick="ydlxChange9()">高处坠落</a>
																		                <input type="checkbox" id="ydlx10" name="M10" value="10" /><a style="cursor:default;" onclick="ydlxChange10()">坍塌</a><br/>
																		                <input type="checkbox" id="ydlx11" name="M10" value="11" /><a style="cursor:default;" onclick="ydlxChange11()">冒顶片帮</a>
																		                <input type="checkbox" id="ydlx12" name="M10" value="12" /><a style="cursor:default;" onclick="ydlxChange12()">透水</a>
																		                <input type="checkbox" id="ydlx13" name="M10" value="13" /><a style="cursor:default;" onclick="ydlxChange13()">放炮</a>
																		                <input type="checkbox" id="ydlx14" name="M10" value="14" /><a style="cursor:default;" onclick="ydlxChange14()">火药爆炸</a>
																		                <input type="checkbox" id="ydlx15" name="M10" value="15" /><a style="cursor:default;" onclick="ydlxChange15()">瓦斯爆炸</a><br/>
																		                <input type="checkbox" id="ydlx16" name="M10" value="16" /><a style="cursor:default;" onclick="ydlxChange16()">锅炉爆炸</a>
																		                <input type="checkbox" id="ydlx17" name="M10" value="17" /><a style="cursor:default;" onclick="ydlxChange17()">容器爆炸</a>
																		                <input type="checkbox" id="ydlx18" name="M10" value="18" /><a style="cursor:default;" onclick="ydlxChange18()">其它爆炸</a>
																		                <input type="checkbox" id="ydlx19" name="M10" value="19" /><a style="cursor:default;" onclick="ydlxChange19()">中毒和窒息</a>
																		                <input type="checkbox" id="ydlx20" name="M10" value="20" /><a style="cursor:default;" onclick="ydlxChange20()">其它伤害</a></td>
				</tr>
				
				<c:if test="${not empty res.ID}">
					<input type="hidden" name="ID" value="${res.ID}" />
					<input type="hidden" name="ID1" value="${res.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${res.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${res.s3}" />
				</c:if>
			</table>

		  	<tbody>
       </form>
 
	<div id="enterprise_dlg" style="width:100%;height:100%; text-align:center;display: none;" >
		<div><span style="color: red;margin: 0 10px;">点击地图标注应急单位位置!</span>
		<input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索条件',searcher:function search(value,name){ addmap(value); }" />
		</div>
		<div id="erm_yjdw_dlg_map" style="width:100%;height: 285px;"></div>
	</div>
<script type="text/javascript">
var usertype=${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

function doSubmit(){
	$("#inputForm").submit(); 
}

$(function(){
	var flag=true;
	$('#inputForm').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
	    	if(isValid&&flag){
	    		flag=false;
	    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
	    		return true;
	    	}
			return false;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	$.jBox.closeTip();
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});



	var action = "${action}";
	var url="";
	var action = "${action}";
	var bmx_x="";
	var bmy_y="";
	var locx ='${res.m12}';
	var locy ='${res.m13}'; 
	var ydlx = "${ydlx}";
	var ydlxArr = ydlx.split(",");
	for(var i=0;i<ydlxArr.length;i++){
		$("input[name='M10']:checkbox[value='"+ydlxArr[i]+"']").attr('checked','true');
	}
	if (action == 'create') {
		url="/erm/yjdw/create";
		for(var i=1;i<=20;i++){
		$("input[name='M10']:checkbox[value='"+i+"']").attr('checked','true');
	}
	} else if (action == 'update') {
		url="/erm/yjdw/update";
	} else if (action == 'view') {
		for(var i=1;i<=15;i++){
			$("*[name='M" + i +"']").attr('readonly', 'readonly');
			$("*[name='M" + i +"']").css('background', '#eee');
		}
		$("*[name='M1']").attr('disabled', 'true');
		$("*[name='M10']").attr('disabled', 'true');
		$("#erm_yjdw_form_savebt").css('display','none');
		$("#map_test_divaxy").hide();
	}
	
	$(function(){ 
		$(".erm_yjdw_form_id_table td").mouseover(function() {
			$(this).addClass("over");
		});
		$(".erm_yjdw_form_id_table td").mouseout(function() {
			$(this).removeClass("over");
		});
		
		//init_map();		
		
		$("input[name='M10']:checkbox").css("width","18px");
		$("input[name='M10']:checkbox").css("height","18px");
	});
	
	//弹出地图界面
	function showMapXY( ){
		layer.open({
		    type: 1,  
		    area: ['500px', '300px'],
		    title: '标注坐标',
	        maxmin: true, 
	        shift: 1,
	        shade :0,
		    content: $('#enterprise_dlg'),
		    btn: ['确定', '关闭'],
		    success: function(layero, index){
		    	addmap("");
		    },
		    yes: function(index, layero){
		    	$("#erm_map_c_x").textbox("setValue", locx);//经度
				$("#erm_map_c_y").textbox("setValue", locy);//纬度
				layer.close(index);
			  },
			  cancel: function(index){ 
		       }
		});
	}
	
	function addmap(searchcon){	
		initMap("erm_yjdw_dlg_map",locx,locy,'','',1);
		map.setDefaultCursor("crosshair");//设置地图默认的鼠标指针样式
		var local = new BMap.LocalSearch(map, {
			renderOptions:{map: map}
		});
		local.search(searchcon);

		var marker = new BMap.Marker(point); //创建marker对象
		map.addOverlay(marker); //在地图中添加marker
		
		map.addEventListener("click", function(e){	
			locx=e.point.lng;
			locy=e.point.lat;
			var now_point =  new BMap.Point(e.point.lng, e.point.lat );
			marker.setPosition(now_point);//设置覆盖物位置
			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		});
		
	}
	
	function ydlxChange1(){
		$('#ydlx1').prop('checked',!$("#ydlx1").prop("checked"));
	}
	function ydlxChange2(){
		$('#ydlx2').prop('checked',!$("#ydlx2").prop("checked"));
	}
	function ydlxChange3(){
		$('#ydlx3').prop('checked',!$("#ydlx3").prop("checked"));
	}
	function ydlxChange4(){
		$('#ydlx4').prop('checked',!$("#ydlx4").prop("checked"));
	}
	function ydlxChange5(){
		$('#ydlx5').prop('checked',!$("#ydlx5").prop("checked"));
	}
	function ydlxChange6(){
		$('#ydlx6').prop('checked',!$("#ydlx6").prop("checked"));
	}
	function ydlxChange7(){
		$('#ydlx7').prop('checked',!$("#ydlx7").prop("checked"));
	}
	function ydlxChange8(){
		$('#ydlx8').prop('checked',!$("#ydlx8").prop("checked"));
	}
	function ydlxChange9(){
		$('#ydlx9').prop('checked',!$("#ydlx9").prop("checked"));
	}
	function ydlxChange10(){
		$('#ydlx10').prop('checked',!$("#ydlx10").prop("checked"));
	}
	function ydlxChange11(){
		$('#ydlx11').prop('checked',!$("#ydlx11").prop("checked"));
	}
	function ydlxChange12(){
		$('#ydlx12').prop('checked',!$("#ydlx12").prop("checked"));
	}
	function ydlxChange13(){
		$('#ydlx13').prop('checked',!$("#ydlx13").prop("checked"));
	}
	function ydlxChange14(){
		$('#ydlx14').prop('checked',!$("#ydlx14").prop("checked"));
	}
	function ydlxChange15(){
		$('#ydlx15').prop('checked',!$("#ydlx15").prop("checked"));
	}
	function ydlxChange16(){
		$('#ydlx16').prop('checked',!$("#ydlx16").prop("checked"));
	}
	function ydlxChange17(){
		$('#ydlx17').prop('checked',!$("#ydlx17").prop("checked"));
	}
	function ydlxChange18(){
		$('#ydlx18').prop('checked',!$("#ydlx18").prop("checked"));
	}
	function ydlxChange19(){
		$('#ydlx19').prop('checked',!$("#ydlx19").prop("checked"));
	}
	function ydlxChange20(){
		$('#ydlx20').prop('checked',!$("#ydlx20").prop("checked"));
	}
	
</script>
</body>
</html>