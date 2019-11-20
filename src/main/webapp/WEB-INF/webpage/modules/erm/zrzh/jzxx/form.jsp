<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>地质灾害</title>
	<meta name="decorator" content="default"/>
	<%-- <script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script> --%>
</head>
<body>

     <form id="inputForm" action="${ctx}/zrzh/jzxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
						<td class="width-35" >
							连云市<input value="${jzxx.m0 }" name="M0" class="easyui-textbox" style="width: 270px;height: 30px;"data-options="required:'true'" />&nbsp;局
						</td>
						<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
						<td class="width-35" ><input id="M0_1" name="M0_1" class="easyui-datetimebox" value="${jzxx.m0_1}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
						<td class="width-35" >
							连云市<input value="${jzxx.m0 }" name="M0" class="easyui-textbox" style="width: 200px;height: 30px;"data-options="required:'true'" />&nbsp;局
						</td>
						<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
						<td class="width-35" ><input id="M0_1" name="M0_1" class="easyui-datetimebox" value="${jzxx.m0_1}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					</tr>
				</c:if>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告简况：</label></td>
					<td class="width-35" colspan="3">
						<input id="M4" name="M4" class="easyui-textbox" value="${jzxx.m4 }" style="width: 75px;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/>县（市、区）
						<input id="M4_1" name="M4_1" class="easyui-textbox" value="${jzxx.m4_1 }" style="width: 70px;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/>镇（街道、园区）&nbsp;&nbsp;遭受
						<input id="M2" name="M2" class="easyui-combobox "  style="width: 110px;height: 30px;" value="${jzxx.m2 }" data-options="panelHeight:'auto',validType:'length[0,50]' ,data: [{value:'洪涝',text:'洪涝'},                      
																																				                                                    {value:'雨雪冰冻',text:'雨雪冰冻'}]"/>&nbsp;&nbsp;自然灾害的救助情况</td>
					</td>
				</tr>
			
				<tr>
					<td class="width-15 active"><label class="pull-right">地区：</label></td>
					<td class="width-35" >
						<input id="M4_x"  class="easyui-textbox" value="${jzxx.m4 }" style="width: 75px;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/>县（市、区）
						<input id="M4_z"  class="easyui-textbox" value="${jzxx.m4_1 }" style="width: 70px;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/>镇（街道、园区）</td>
					<td class="width-15 active"><label class="pull-right">灾情时间：</label></td>
					<td class="width-35" ><input id="M3" name="M3" class="easyui-datetimebox" value="${jzxx.m3}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">自然灾害类型：</label></td>
					<td class="width-35" ><input id="M2_1" class="easyui-combobox" value="${jzxx.m2 }" style="width: 100%;height: 30px;" data-options=" panelHeight:'auto',validType:'length[0,50]',data: [{value:'洪涝',text:'洪涝'},                      
																																				                                                          {value:'雨雪冰冻',text:'雨雪冰冻'}]"/></td>
					<td class="width-15 active"><label class="pull-right">等级：</label></td>
					<td class="width-35" ><input id="M5" name="M5" class="easyui-textbox" value="${jzxx.m5 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">需政府救助人数：</label></td>
					<td class="width-35" ><input id="M7_1" name="M7_1" class="easyui-textbox" value="${jzxx.m7_1 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">死亡人数：</label></td>
					<td class="width-35" ><input id="M6" name="M6" class="easyui-textbox" value="${jzxx.m6 }" style="width:100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
				</tr>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">紧急转移安置或需紧急生活救助人数：</label></td>
					<td class="width-35" ><input id="M7" name="M7" class="easyui-textbox" value="${jzxx.m7 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">倒塌和严重损坏房屋数（间）：</label></td>
					<td class="width-35" ><input id="M10" name="M10" class="easyui-textbox" value="${jzxx.m10}" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">占灾害发生地县（市、区）农业人口比例（%）：</label></td>
					<td class="width-35" ><input id="M11" name="M11" class="easyui-textbox" value="${jzxx.m11 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">灾害简况：</label></td>
					<td class="width-35" colspan="3"><input id="M11" name="M12"  value="${jzxx.m12}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true,multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应对处置情况：</label></td>
					<td class="width-35" colspan="3"><input id="M13" name="M13"  value="${jzxx.m13}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true,multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><input id="M14" name="M14"  value="${jzxx.m14}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告人：</label></td>
					<td class="width-35" ><input id="M15" name="M15" class="easyui-textbox" value="${jzxx.m15 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35" ><input id="M16" name="M16" class="easyui-textbox" value="${jzxx.m16 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/></td>
				</tr>
				
				
				<c:if test="${not empty jzxx.ID}">
					<input type="hidden" name="ID" value="${jzxx.ID}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${jzxx.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${jzxx.s3}" />
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
		$("#M4").textbox({
			onChange:function(newValue,oldValue){
				$("#M4_x").textbox('setValue',newValue);   
			}
		});
		$("#M4_1").textbox({
			onChange:function(newValue,oldValue){
				$("#M4_z").textbox('setValue',newValue);   
			}
		});
		
		
		$("#M4_x").textbox({
			onChange:function(newValue,oldValue){
				$("#M4").textbox('setValue',newValue);   
			}
		});
		$("#M4_z").textbox({
			onChange:function(newValue,oldValue){
				$("#M4_1").textbox('setValue',newValue);   
			}
		});
		
		$("#M2").combobox({
			onChange:function(newValue,oldValue){
				$("#M2_1").combobox('setValue',newValue);   
			}
		});
		$("#M2_1").combobox({
			onChange:function(newValue,oldValue){
				$("#M2").combobox('setValue',newValue);   
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
	
	
	
	
	
</script>
</body>
</html>