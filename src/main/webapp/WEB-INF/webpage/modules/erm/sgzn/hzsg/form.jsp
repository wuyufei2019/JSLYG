<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>火灾事故信息</title>
	<meta name="decorator" content="default"/>
	<%-- <script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script> --%>
</head>
<body>

     <form id="inputForm" action="${ctx}/sgzn/hzsg/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
						<td class="width-35" >
							连云市<input value="${hzsg.m0 }" name="M0" class="easyui-textbox" style="width: 270px;height: 30px;"data-options="required:'true'" />&nbsp;局
						</td>
						<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
						<td class="width-35" ><input id="M0_1" name="M0_1" class="easyui-datetimebox" value="${hzsg.m0_1}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
						<td class="width-35" >
							连云市<input value="${hzsg.m0 }" name="M0" class="easyui-textbox" style="width: 200px;height: 30px;"data-options="required:'true'" />&nbsp;局
						</td>
						<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
						<td class="width-35" ><input id="M0_1" name="M0_1" class="easyui-datetimebox" value="${hzsg.m0_1}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					</tr>
				</c:if>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告简况：</label></td>
					<td class="width-35" colspan="3">
						<input id="M1" name="M1" class="easyui-combobox"  style="width: 335px;height: 30px;" value="${hzsg.m1 }"  data-options="required:'true',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />&nbsp;&nbsp;（单位）发生了一起&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="M2" name="M2" class="easyui-textbox "  style="width: 100px;height: 30px;" value="${hzsg.m2 }" data-options="panelHeight:'auto',validType:'length[0,50]'"/>火灾事故</td>
					</td>
				</tr>
			
				<tr>
					
					<td class="width-15 active"><label class="pull-right">事故地点：</label></td>
					<td class="width-35">
						<input id="M4" name="M4" class="easyui-textbox" value="${hzsg.m4 }" style="width: 75px;height: 30px;" data-options="required:'true', validType:'length[0,100]' "/>县（市、区）
						<input id="M4_1" name="M4_1" class="easyui-textbox" value="${hzsg.m4_1 }" style="width: 70px;height: 30px;" data-options="required:'true', validType:'length[0,100]' "/>镇（街道、园区）</td>
					<td class="width-15 active"><label class="pull-right">事故时间：</label></td>
					<td class="width-35" ><input id="M3" name="M3" class="easyui-datetimebox" value="${hzsg.m3}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">死亡人数：</label></td>
					<td class="width-35" ><input id="M6" name="M6" class="easyui-textbox" value="${hzsg.m6 }" style="width:100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">失踪人数：</label></td>
					<td class="width-35" ><input id="M7" name="M7" class="easyui-textbox" value="${hzsg.m7 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
				</tr>
								
				<tr>
					<td class="width-15 active"><label class="pull-right">受伤人数：</label></td>
					<td class="width-35" ><input id="M9" name="M9" class="easyui-textbox" value="${hzsg.m9 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">经济类型（万元）：</label></td>
					<td class="width-35" ><input id="M11" name="M11" class="easyui-textbox" value="${hzsg.m11 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/></td>
				</tr>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">事故单位名称：</label></td>
					<td class="width-35">
						<input value="${hzsg.m1 }" id="M1_1" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="required:'true',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">事故简况：</label></td>
					<td class="width-35" colspan="3"><input id="M12" name="M12"  value="${hzsg.m12}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true,multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应对处置情况：</label></td>
					<td class="width-35" colspan="3"><input id="M13" name="M13"  value="${hzsg.m13}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true,multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><input id="M14" name="M14"  value="${hzsg.m14}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告人：</label></td>
					<td class="width-35" ><input id="M15" name="M15" class="easyui-textbox" value="${hzsg.m15 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35" ><input id="M16" name="M16" class="easyui-textbox" value="${hzsg.m16 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/></td>
				</tr>
				
				
				<c:if test="${not empty hzsg.ID}">
					<input type="hidden" name="ID" value="${hzsg.ID}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${hzsg.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${hzsg.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); 
	
	
	$(function(){
		$("#M1").combobox({
			onChange:function(newValue,oldValue){
				$("#M1_1").combobox('setValue',newValue);   
			}
		});
		
		$("#M1_1").combobox({
			onChange:function(newValue,oldValue){
				$("#M1").combobox('setValue',newValue);   
			}
		});
	});

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