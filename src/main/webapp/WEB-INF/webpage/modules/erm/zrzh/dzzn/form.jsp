<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>地质灾害</title>
	<meta name="decorator" content="default"/>
	<%-- <script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script> --%>
</head>
<body>

     <form id="inputForm" action="${ctx}/zrzh/dzzn/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
						<td class="width-35" >
							连云市<input value="${dzzn.m0 }" name="M0" class="easyui-textbox" style="width: 270px;height: 30px;"data-options="required:'true'" />&nbsp;局
						</td>
						<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
						<td class="width-35" ><input id="M0_1" name="M0_1" class="easyui-datetimebox" value="${dzzn.m0_1}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
						<td class="width-35" >
							连云市<input value="${dzzn.m0 }" name="M0" class="easyui-textbox" style="width: 200px;height: 30px;"data-options="required:'true'" />&nbsp;局
						</td>
						<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
						<td class="width-35" ><input id="M0_1" name="M0_1" class="easyui-datetimebox" value="${dzzn.m0_1}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					</tr>
				</c:if>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告简况：</label></td>
					<td class="width-35" colspan="3">
						<input id="M4" name="M4" class="easyui-textbox" value="${dzzn.m4 }" style="width: 75px;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/>县（市、区）
						<input id="M4_1" name="M4_1" class="easyui-textbox" value="${dzzn.m4_1 }" style="width: 70px;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/>镇（街道、园区）发生了一起
						<input id="M2" name="M2" class="easyui-combobox "  style="width: 100px;height: 30px;" value="${dzzn.m2 }" data-options="panelHeight:'auto',validType:'length[0,50]' ,data: [{value:'小型',text:'小型'},
																																				                                                    {value:'中型',text:'中型'},
																																				                                                    {value:'大型',text:'大型'},                       
																																				                                                    {value:'特大型',text:'特大型'}]"/>地质灾害</td>
					</td>
				</tr>
			
				<tr>
					<td class="width-15 active"><label class="pull-right">位置：</label></td>
					<td class="width-35" >
						<input id="M4_x"  class="easyui-textbox" value="${dzzn.m4 }" style="width: 75px;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/>县（市、区）
						<input id="M4_z"  class="easyui-textbox" value="${dzzn.m4_1 }" style="width: 70px;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/>镇（街道、园区）</td>
					<td class="width-15 active"><label class="pull-right">灾情时间：</label></td>
					<td class="width-35" ><input id="M3" name="M3" class="easyui-datetimebox" value="${dzzn.m3}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">地质灾害类型：</label></td>
					<td class="width-35" ><input id="M5" name="M5" class="easyui-combobox" value="${dzzn.m5 }" style="width: 100%;height: 30px;" data-options=" panelHeight:'auto',validType:'length[0,50]' ,data: [{value:'滑坡',text:'滑坡'},                   
																																				                                                    				{value:'崩塌',text:'崩塌'}]"/></td>
					<td class="width-15 active"><label class="pull-right">受损房屋间数（间）：</label></td>
					<td class="width-35" ><input id="M10" name="M10" class="easyui-textbox" value="${dzzn.m10 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">死亡人数：</label></td>
					<td class="width-35" ><input id="M6" name="M6" class="easyui-textbox" value="${dzzn.m6 }" style="width:100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">受威胁转移人数：</label></td>
					<td class="width-35" ><input id="M7" name="M7" class="easyui-textbox" value="${dzzn.m7 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">受伤人数：</label></td>
					<td class="width-35" ><input id="M9" name="M9" class="easyui-textbox" value="${dzzn.m9 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">被困、失踪人数：</label></td>
					<td class="width-35" ><input id="M8" name="M8" class="easyui-textbox" value="${dzzn.m8 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">地质灾害简况：</label></td>
					<td class="width-35" colspan="3"><input id="M12" name="M12"  value="${dzzn.m12}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true,multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应对处置情况：</label></td>
					<td class="width-35" colspan="3"><input id="M13" name="M13"  value="${dzzn.m13}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true,multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><input id="M14" name="M14"  value="${dzzn.m14}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告人：</label></td>
					<td class="width-35" ><input id="M15" name="M15" class="easyui-textbox" value="${dzzn.m15 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35" ><input id="M16" name="M16" class="easyui-textbox" value="${dzzn.m16 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/></td>
				</tr>
				
				
				<c:if test="${not empty dzzn.ID}">
					<input type="hidden" name="ID" value="${dzzn.ID}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${dzzn.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${dzzn.s3}" />
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