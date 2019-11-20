<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>地震信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
	<%-- <script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script> --%>
	<style type="text/css">
		.BMap_cpyCtrl{ display:none; }  
		.anchorBL{ display:none;}   
	</style>
</head>
<body>

     <form id="inputForm" action="${ctx}/zrzh/dzxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
						<td class="width-35" >
							连云市<input value="${dzxx.m0 }" name="M0" class="easyui-textbox" style="width: 270px;height: 30px;"data-options="required:'true'" />&nbsp;局
						</td>
						<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
						<td class="width-35" ><input id="M0_1" name="M0_1" class="easyui-datetimebox" value="${dzxx.m0_1}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
						<td class="width-35" >
							连云市<input value="${dzxx.m0 }" name="M0" class="easyui-textbox" style="width: 200px;height: 30px;"data-options="required:'true'" />&nbsp;局
						</td>
						<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
						<td class="width-35" ><input id="M0_1" name="M0_1" class="easyui-datetimebox" value="${dzxx.m0_1}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					</tr>
				</c:if>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告简况：</label></td>
						<td class="width-35" colspan="3">
							<input id="M1" name="M1" class="easyui-textbox" style="width: 335px;height: 30px;" value="${dzxx.m1 }" data-options="required:'true',validType:'length[0,100]'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（区域）发生了&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input id="M4" name="M4" class="easyui-textbox"  style="width: 100px;height: 30px;" value="${dzxx.m4 }"  data-options="required:'true',validType:'length[0,50]'" />级
							<input id="M2" name="M2" class="easyui-combobox "  style="width: 100px;height: 30px;" value="${dzxx.m2 }" data-options="required:'true',panelHeight:'auto',validType:'length[0,50]' ,data: [{value:'一般',text:'一般'},
																																                                                                    					{value:'较大',text:'较大'},
																																                                                                    					{value:'重大',text:'重大'},                       
																																                                                                    					{value:'特别重大',text:'特别重大'}]"/>地震</td>     
				</tr>
			
				<tr>
					
					<td class="width-15 active"><label class="pull-right">震源（经纬度）：</label></td>
					<td style="height:30px;line-height:30px;">
						<label>经度</label>
						<input id="bis_map_c_x" name="M4_1" value="${dzxx.m4_1 }" class="easyui-textbox" readonly="readonly" data-options="required:'true'" style="width:100px;height:30px;"/>
						<label>纬度</label> 
						<input id="bis_map_c_y" name="M4_2" value="${dzxx.m4_2 }" class="easyui-textbox" readonly="readonly" data-options="required:'true'" style="width:100px;height:30px;"/>
						<a class="btn btn-primary" onclick="showMapXY( )" style="width:60px;">定位</a></td>
					<td class="width-15 active"><label class="pull-right">灾情时间：</label></td>
					<td class="width-35" ><input id="M3" name="M3" class="easyui-datetimebox" value="${dzxx.m3}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
				</tr>
				
				<tr>

				</tr>
					
				
				</tr>
					<td class="width-15 active"><label class="pull-right">震级：</label></td>
					<td class="width-35" ><input id="M4_a" class="easyui-textbox" value="${dzxx.m4 }" style="width: 100%;height: 30px;" data-options=" required:'true',validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">涉险总人数：</label></td>
					<td class="width-35" ><input id="M5" name="M5" class="easyui-textbox" value="${dzxx.m5 }" style="width: 100%;height: 30px;" data-options=" required:'true',validType:'length[0,50]' "/></td>
				
				<tr>
					
					<td class="width-15 active"><label class="pull-right">死亡人数：</label></td>
					<td class="width-35" ><input id="M6" name="M6" class="easyui-textbox" value="${dzxx.m6 }" style="width:100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">失踪人数：</label></td>
					<td class="width-35" ><input id="M7" name="M7" class="easyui-textbox" value="${dzxx.m7 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">被困人数：</label></td>
					<td class="width-35" ><input id="M8" name="M8" class="easyui-textbox" value="${dzxx.m8 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">受伤人数：</label></td>
					<td class="width-35" ><input id="M9" name="M9" class="easyui-textbox" value="${dzxx.m9 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
				</tr>
				

				
				<tr>
					<td class="width-15 active"><label class="pull-right">地震简况：</label></td>
					<td class="width-35" colspan="3"><input id="M12" name="M12"  value="${dzxx.m12}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true,multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应对处置情况：</label></td>
					<td class="width-35" colspan="3"><input id="M13" name="M13"  value="${dzxx.m13}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true,multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><input id="M14" name="M14"  value="${dzxx.m14}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告人：</label></td>
					<td class="width-35" ><input id="M15" name="M15" class="easyui-textbox" value="${dzxx.m15 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35" ><input id="M16" name="M16" class="easyui-textbox" value="${dzxx.m16 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/></td>
				</tr>
				
				
				<c:if test="${not empty dzxx.ID}">
					<input type="hidden" name="ID" value="${dzxx.ID}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${dzxx.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${dzxx.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
       
       	<div id="enterprise_dlg" style="width:100%;height:100%; text-align:center;display: none;" >
		<div><span style="color: red;margin: 0 10px;">点击地图标注震源位置!</span>
		<input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索条件',searcher:function search(value,name){ addmap(value); }" />
		</div>
		<div id="bis_enterprise_dlg_map" style="width:100%;height: 285px;"></div>
		</div>
       
       
 
<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); 
	
	var locx ='${dzxx.m4_1}';
	var locy ='${dzxx.m4_2}';
	

	function doSubmit(){
		$("#inputForm").serializeObject();
		$("#inputForm").submit(); 
	}
	
	
	$(function(){
		$("#M4").textbox({
			onChange:function(newValue,oldValue){
				$("#M4_a").textbox('setValue',newValue);   
			}
		});
		
		$("#M4_a").textbox({
			onChange:function(newValue,oldValue){
				$("#M4").textbox('setValue',newValue);   
			}
		});
		
		
	});
	
	
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
	
	//弹出地图界面
	function showMapXY( ){
		layer.open({
		    type: 1,  
		    area: ['500px', '400px'],
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
		    	$("#bis_map_c_x").textbox("setValue", locx);//经度
				$("#bis_map_c_y").textbox("setValue", locy);//纬度
				layer.close(index);
			  },
			  cancel: function(index){ 
		       }
		});
	}
	
	function addmap(searchcon){	
		initMap("bis_enterprise_dlg_map",locx,locy);
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
	
</script>
</body>
</html>