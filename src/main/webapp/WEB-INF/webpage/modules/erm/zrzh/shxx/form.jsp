<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>水旱信息</title>
	<meta name="decorator" content="default"/>
	<%-- <script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script> --%>
</head>
<body>

     <form id="inputForm" action="${ctx}/zrzh/shxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
						<td class="width-35" >
							连云市<input value="${shxx.m0 }" name="M0" class="easyui-textbox" style="width: 270px;height: 30px;"data-options="required:'true'" />&nbsp;局
						</td>
						<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
						<td class="width-35" ><input id="M0_1" name="M0_1" class="easyui-datetimebox" value="${shxx.m0_1}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">报告单位：</label></td>
						<td class="width-35" >
							连云市<input value="${shxx.m0 }" name="M0" class="easyui-textbox" style="width: 200px;height: 30px;"data-options="required:'true'" />&nbsp;局
						</td>
						<td class="width-15 active"><label class="pull-right">报告时间：</label></td>
						<td class="width-35" ><input id="M0_1" name="M0_1" class="easyui-datetimebox" value="${shxx.m0_1}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					</tr>
				</c:if>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告简况：</label></td>
					<td class="width-35" colspan="3">
						<input id="M1" name="M1" class="easyui-textbox"  style="width: 335px;height: 30px;" value="${shxx.m1 }"  data-options="required:'true',validType:'length[0,100]'" />&nbsp;&nbsp;（区域）发生了一起&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="M2" name="M2" class="easyui-combobox "  style="width: 100px;height: 30px;" value="${shxx.m2 }" data-options="panelHeight:'auto',validType:'length[0,50]' ,data: [{value:'一般',text:'一般'},
																																                                                                    {value:'较大',text:'较大'},
																																                                                                    {value:'重大',text:'重大'},                       
																																                                                                    {value:'特别重大',text:'特别重大'}]"/>
						<input id="M2_1" name="M2_1" class="easyui-combobox "  style="width: 100px;height: 30px;" value="${shxx.m2_1 }" data-options="panelHeight:'auto',validType:'length[0,50]' ,data: [{value:'洪水',text:'洪水'},
																																	                                                                      {value:'干旱',text:'干旱'}]"/>灾害</td>     
				</tr>
			
				<tr>
					<td class="width-15 active"><label class="pull-right">灾害地点：</label></td>
					<td class="width-35">
						<input id="M4" name="M4" class="easyui-textbox" value="${shxx.m4 }" style="width: 120px;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/>县（市、区）
						<%-- <input id="M4_1" name="M4_1" class="easyui-textbox" value="${shxx.m4_1 }" style="width: 100px;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/>镇（街道、园区）</td> --%>
					<td class="width-15 active"><label class="pull-right">灾情时间：</label></td>
					<td class="width-35" ><input id="M3" name="M3" class="easyui-datetimebox" value="${shxx.m3}" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">一般洪涝或发生决口或出现重大险情地段：</label></td>
					<td class="width-35" colspan="3"><input id="M5" name="M5" class="easyui-textbox" value="${shxx.m5 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,250]' "/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">受涝范围：</label></td>
					<td class="width-35" colspan="3"><input id="M6" name="M6" class="easyui-textbox" value="${shxx.m6 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,250]' "/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">干旱程度：</label></td>
					<td class="width-35" colspan="3"><input id="M7" name="M7" class="easyui-textbox" value="${shxx.m7 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,250]' "/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">24小时连续降雨量或1小时降雨量（毫米）：</label></td>
					<td class="width-35" colspan="3"><input id="M8" name="M8" class="easyui-textbox" value="${shxx.m8 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,50]' "/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">主要内河水位（米）：</label></td>
					<td class="width-35" ><input id="M9" name="M9" class="easyui-textbox" value="${shxx.m9 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,250]' "/></td>
					<td class="width-15 active"><label class="pull-right">主要内河水位（级）：</label></td>
					<td class="width-35" ><input id="M9_1" name="M9_1" class="easyui-textbox" value="${shxx.m9_1 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,250]' "/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">主要内河水位<br>（描述）：</label></td>
					<td class="width-35" colspan="3"><input id="M9_2" name="M9_2" class="easyui-textbox" value="${shxx.m9_2 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,250]' "/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">水旱简况：</label></td>
					<td class="width-35" colspan="3"><input id="M12" name="M12"  value="${shxx.m12}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true,multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应对处置情况：</label></td>
					<td class="width-35" colspan="3"><input id="M13" name="M13"  value="${shxx.m13}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true,multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><input id="M14" name="M14"  value="${shxx.m14}"   class="easyui-textbox" style="width: 100%;height: 100px;" data-options="multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">报告人：</label></td>
					<td class="width-35" ><input id="M15" name="M15" class="easyui-textbox" value="${shxx.m15 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/></td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35" ><input id="M16" name="M16" class="easyui-textbox" value="${shxx.m16 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,50]' "/></td>
				</tr>
				
				
				<c:if test="${not empty shxx.ID}">
					<input type="hidden" name="ID" value="${shxx.ID}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${shxx.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${shxx.s3}" />
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