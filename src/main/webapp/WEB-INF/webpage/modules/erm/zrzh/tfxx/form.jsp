<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>台风信息</title>
	<meta name="decorator" content="default"/>
	<%-- <script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script> --%>
</head>
<body>

     <form id="inputForm" action="${ctx}/zrzh/tfxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
						<td class="width-35" >
							连云市<input value="${tfxx.m0 }" name="M0" class="easyui-textbox" style="width: 270px;height: 30px;"data-options="required:'true'" />&nbsp;局
						</td>
						<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
						<td class="width-35" ><input id="M0_1" name="M0_1" class="easyui-datetimebox" value="${tfxx.m0_1}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
						<td class="width-35" >
							连云市<input value="${tfxx.m0 }" name="M0" class="easyui-textbox" style="width: 200px;height: 30px;"data-options="required:'true'" />&nbsp;局
						</td>
						<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
						<td class="width-35" ><input id="M0_1" name="M0_1" class="easyui-datetimebox" value="${tfxx.m0_1}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					</tr>
				</c:if>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告简况：</label></td>
					<td class="width-35" colspan="3">
						<input id="M1" name="M1" class="easyui-textbox"  style="width: 335px;height: 30px;" value="${tfxx.m1 }"  data-options="required:'true',validType:'length[0,100]' " />&nbsp;&nbsp;（区域）发生了一起&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="M2" name="M2" class="easyui-combobox "  style="width: 100px;height: 30px;" value="${tfxx.m2 }" data-options="panelHeight:'auto',validType:'length[0,50]' ,data: [{value:'台风',text:'台风'},
																																                                                                    {value:'强台风',text:'强台风'},                      
																										                                                                                            {value:'超强台风',text:'超强台风'}]"/>灾害</td>     
				</tr>
	
				<tr>
					<td class="width-15 active"><label class="pull-right">灾害地点：</label></td>
					<td class="width-35" >
						<input id="M4" name="M4" class="easyui-textbox" value="${tfxx.m4 }" style="width: 120px;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/>县（市、区）
						<%-- <input id="M4_1" name="M4_1" class="easyui-textbox" value="${tfxx.m4_1 }" style="width: 100px;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/>镇（街道、园区）</td> --%>
					<td class="width-15 active"><label class="pull-right">灾情时间：</label></td>
					<td class="width-35" ><input id="M3" name="M3" class="easyui-datetimebox" value="${tfxx.m3}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>	
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">受热带气旋影响小时：</label></td>
					<td class="width-35"><input id="M5" name="M5" class="easyui-combobox" value="${tfxx.m5 }" style="width: 120px;height: 30px;" data-options=" panelHeight:'auto',validType:'length[0,50]' ,data: [{value:'24',text:'24'},
																																																			   		{value:'12',text:'12'},
																																																			    	{value:'6',text:'6'}]"/>小时内可能或者已经受热带气旋影响</td>
					<td class="width-15 active"><label class="pull-right">阵风等级：</label></td>
					<td class="width-15 active"><input id="M7" name="M7" class="easyui-textbox" value="${tfxx.m7 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">沿海或者陆地平均风力等级：</label></td>
					<td class="width-15 active" ><input id="M6" name="M6" class="easyui-textbox" value="${tfxx.m6 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">是否可能持续：</label></td>
					<td class="width-35"><input id="M7_1" name="M7_1" class="easyui-combobox" value="${tfxx.m7_1 }" style="width: 100%;height: 30px;" data-options=" panelHeight:'auto',validType:'length[0,50]' ,data: [{value:'是',text:'是'},
					                                                                                                                                                                                                     {value:'否',text:'否'}]"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">受台风影响，预报暴雨情况：</label></td>
					<td class="width-35" ><input id="M8" name="M8" class="easyui-combobox" value="${tfxx.m8 }" style="width: 95px;height: 30px;" data-options=" panelHeight:'auto',validType:'length[0,50]' ,data: [{value:'12',text:'12'},
					                                                                                                                                                                           					   {value:'6',text:'6'},
					                                                                                                                                                                           					   {value:'3',text:'3'}]"/>小时内降雨量
					                     <input id="M8_1" name="M8_1" class="easyui-textbox" value="${tfxx.m8_1 }" style="width: 95px;height: 30px;" data-options=" validType:'length[0,50]' "/>毫米以上
					</td>           
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right"></label></td>
					<td class="width-35">已达<input id="M8_2" name="M8_2" class="easyui-combobox" value="${tfxx.m8_2 }" style="width: 210px;height: 30px;" data-options="panelHeight:'auto',validType:'length[0,50]',data: [{value:'50',text:'50'},
					                                                                                                                                                                                                      {value:'100',text:'100'}]"/>毫米以上降雨量
					<td class="width-15 active"><label class="pull-right">降雨是否可能持续：</label></td>
					<td class="width-35"><input id="M8_3" name="M8_3" class="easyui-combobox" value="${tfxx.m8_3 }" style="width: 100%;height: 30px;" data-options="panelHeight:'auto',validType:'length[0,50]',data: [{value:'是',text:'是'},
					                                                                                                                                                                                                   {value:'否',text:'否'}]"/></td>
				</tr>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">受台风影响，沿海潮位情况：</label></td>
					<td class="width-35" ><input id="M9" name="M9" class="easyui-textbox" value="${tfxx.m9 }" style="width: 110px;height: 30px;" data-options=" validType:'length[0,50]' "/>河流潮位是否超过               
										  <input id="M9_1" name="M9_1" class="easyui-combobox" value="${tfxx.m9_1 }" style="width: 110px;height: 30px;" data-options=" panelHeight:'auto',validType:'length[0,50]',data: [{value:'警戒线',text:'警戒线'},
										  																																										  		  {value:'历史最高潮位',text:'历史最高潮位'},
					                                                                                                                                                                                                      {value:'历史最高水位',text:'历史最高水位'}] "/>
					</td>
					<td class="width-15 active"><label class="pull-right">河流潮位是否超过：</label></td>
					<td class="width-35"><input id="M9_2" name="M9_2" class="easyui-combobox" value="${tfxx.m9_2 }" style="width: 100%;height: 30px;" data-options="panelHeight:'auto',validType:'length[0,50]',data: [{value:'是',text:'是'},
					                                                                                                                                                                                                   {value:'否',text:'否'}]"/></td>
				</tr>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right"></label></td>
					<td class="width-35">沿海高潮位是否达到<input id="M10" name="M10" class="easyui-combobox" value="${tfxx.m10 }" style="width: 160px;height: 30px;" data-options="panelHeight:'auto',validType:'length[0,50]',data: [{value:'黄色',text:'黄色'},
					                                                                                                                                                                                                             {value:'橙色',text:'橙色'},
					                                                                                                                                                                                                             {value:'红色',text:'红色'}]"/>警戒潮位</td>
					<td class="width-15 active"><label class="pull-right">沿海高潮位是否达到：</label></td>
					<td class="width-35"><input id="M10_1" name="M10_1" class="easyui-combobox" value="${tfxx.m10_1 }" style="width: 100%;height: 30px;" data-options="panelHeight:'auto',validType:'length[0,50]',data: [{value:'是',text:'是'},
					                                                                                                                                                                                                      {value:'否',text:'否'}]"/></td>
				</tr>
				
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">台风简况：</label></td>
					<td class="width-35" colspan="3"><input id="M12" name="M12"  value="${tfxx.m12}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true,multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应对处置情况：</label></td>
					<td class="width-35" colspan="3"><input id="M13" name="M13"  value="${tfxx.m13}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true,multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><input id="M14" name="M14"  value="${tfxx.m14}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告人：</label></td>
					<td class="width-35" ><input id="M15" name="M15" class="easyui-textbox" value="${tfxx.m15 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35" ><input id="M16" name="M16" class="easyui-textbox" value="${tfxx.m16 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/></td>
				</tr>
				
				
				<c:if test="${not empty tfxx.ID}">
					<input type="hidden" name="ID" value="${tfxx.ID}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${tfxx.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${tfxx.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); 


	function doSubmit(){
		$("#inputForm").serializeObject();
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
	
	
	
	
	
</script>
</body>
</html>