<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>缴审记录管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/xzcf/ybcf/fksp/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${action eq 'update'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">缴审编号：</label></td>
					<td class="width-30" colspan="3"><input data-options="readonly:'true' " type="text" id="M1" name="M1" class="easyui-textbox" value="${fksp.m1 }"  style="height: 30px;width: 100%" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">案件名称：</label></td>
					<td class="width-30" colspan="3" >
						<input style="width:100%;height: 30px;"  id="M2"name="M2"  class="easyui-textbox" value="${fksp.m2 }" 
						data-options="required:'true',validType:'length[0,250]'" />
				   </td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">处罚决定书文号：</label></td>
					<td class="width-30" colspan="3" >
						<input style="width:100%;height: 30px;" type="text" id="M3"name="M3"  class="easyui-textbox" value="${fksp.m3 }" 
						data-options="readonly:'true'" />
				   </td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">当事人：</label></td>
					<td class="width-30" >
						<input style="width:100%;height: 30px;"  id="M4"name="M4"  class="easyui-textbox" value="${fksp.m4 }" 
						data-options="validType:'length[0,100]'" />
				   </td>
					<td class="width-20 active"><label class="pull-right">地址：</label></td>
					<td class="width-30" >
						<input style="width:100%;height: 30px;"  id="M5"name="M5"  class="easyui-textbox" value="${fksp.m5 }" 
						data-options="validType:'length[0,100]'" />
				   </td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">违法事实及处罚决定：</label></td>
					<td class="width-30" colspan="3"><input name="M6" type="text" value="${fksp.m6 }"   class="easyui-textbox" style="width: 100%;height: 150px;" data-options="multiline:true "></td>					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">申请理由：</label></td>
					<td class="width-30" colspan="3"><input name="M7" type="text" value="${fksp.m7 }"   class="easyui-textbox" style="width: 100%;height: 50px;" data-options="multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">承办人意见：</label></td>
					<td class="width-30" colspan="3"><input name="M8" type="text" value="${fksp.m8 }"   class="easyui-textbox" style="width: 100%;height: 50px;" data-options="multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">承办人：</label></td>
					<td class="width-30"><input type="text" id="M9" name="M9" class="easyui-combobox" value="${fksp.m9 }" style="width: 100%;height: 30px;" data-options="panelHeight:'100px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">承办人：</label></td>
					<td class="width-30"><input type="text" id="M10" name="M10" class="easyui-combobox" value="${fksp.m10 }" style="width: 100%;height: 30px;" data-options="panelHeight:'100px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
				</tr>    
	            <tr>
	            	<td class="width-20 active"><label class="pull-right">承办日期：</label></td>
					<td class="width-30"><input id="M11" name="M11" class="easyui-datebox" value="${fksp.m11 }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
	           	</tr>
	           	<tr>
					<td class="width-20 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-30" colspan="3">
						<c:choose>
					<c:when test="${fksp.m12=='0'}">
						<input type="radio" value="1" class="i-checks" name="M12" />同意
						<input type="radio" value="0" class="i-checks"  name="M12" checked="checked" />不同意
					</c:when>
					<c:otherwise>
						<input type="radio" value="1" class="i-checks" name="M12" checked="checked" />同意
						<input type="radio" value="0" class="i-checks"  name="M12" />不同意
					</c:otherwise>
					</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">审核人：</label></td>
					<td class="width-30"><input type="text" id="M13" name="M13" class="easyui-combobox" value="${fksp.m13 }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-30"><input id="M14" name="M14" class="easyui-datebox" value="${fksp.m14 }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">审批意见：</label></td>
					<td class="width-30" colspan="3">
						<c:choose>
					<c:when test="${fksp.m15=='0'}">
						<input type="radio" value="1" class="i-checks" name="M15" />同意
						<input type="radio" value="0" class="i-checks"  name="M15" checked="checked" />不同意
					</c:when>
					<c:otherwise>
						<input type="radio" value="1" class="i-checks" name="M15" checked="checked" />同意
						<input type="radio" value="0" class="i-checks"  name="M15" />不同意
					</c:otherwise>
					</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">审批人：</label></td>
					<td class="width-30"><input type="text" name="M16" class="easyui-combobox" value="${fksp.m16 }"  data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist'" style="height: 30px;width: 100%" /></td>
					<td class="width-20 active"><label class="pull-right">审批日期：</label></td>
					<td class="width-30"><input id="M17" name="M17" class="easyui-datebox" value="${fksp.m17 }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				
				<input type="hidden" name="ID1" value="${fksp.ID1}" />
				<c:if test="${not empty fksp.ID}">
					<input type="hidden" name="ID" value="${fksp.ID}" />
					<input type="hidden" name="S1"  value="<fmt:formatDate value="${fksp.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	$(function() {
		if ('${action}' == 'create') {
			$('#M11').datebox('setValue', format(new Date()));	
			$('#M14').datebox('setValue', format(new Date()));	
			$('#M17').datebox('setValue', format(new Date()));	
		}
	});
	
	function format(date) {
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1);
		return date.getFullYear() + '-' + month + '-' + day;
	};

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function doSubmit(){
		$("#inputForm").submit(); 
	}
	
	$(function(){
	    var flag=true;
			$('#inputForm').form({
				onSubmit : function() {
					var isValid = $(this).form('validate');
			    	if(isValid&&flag){
			    		flag=false;
			    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
			    		return true;
			    	}
					return false; // 返回false终止表单提交
			},  
		    success:function(data){ 
		         $.jBox.closeTip();
		    	if(data=='success'){
		    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    		parent.dg.datagrid('reload');
		    		parent.layer.close(index);//关闭对话框。
		    	}else{
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    	}
		    }    
		});
	
	});
	
</script>
</body>
</html>