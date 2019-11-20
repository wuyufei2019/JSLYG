<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>处罚审批添加</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
    <script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/ybcf/cfsp/index.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/xzcf/ybcf/cfsp/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  <tr>
				  <td class="width-15 active"><label class="pull-right">案件名称：</label></td>
				  <td class="width-35" colspan="3"><input type="text" id="casename" name="casename" class="easyui-textbox" value="${cfsp.casename }"  style="height: 30px;width: 100%" data-options="editable:false"/></td>
			  </tr>
			  <tr>
				  <td class="width-15 active"><label class="pull-right">企业名称：</label></td>
				  <td class="width-35"><input id="dsperson" name="dsperson" type="text"  class="easyui-textbox" value="${cfsp.dsperson}"  data-options="editable:false" style="height: 30px;width: 100%" /></td>
				  <td class="width-15 active"><label class="pull-right">立案编号：</label></td>
				  <td class="width-35"><input id="M1" name="M1" type="text" value="${cfsp.m1}"  class="easyui-textbox" data-options="editable:false" style="height: 30px;width: 100%" /></td>
			  </tr>
			    <tr>
					<td class="width-15 active"><label class="pull-right">案件经办人：</label></td>
					<td class="width-35"><input  id="M6"name="M6" class="easyui-combobox" value="${cfsp.m6 }"
												 style="width: 100%;height: 30px;"
												 data-options="panelHeight:'142px',required:'true',editable:true,valueField:'text',textField:'text',url:'${ctx}/aqzf/zfry/idlist'"/></td>
				    <td class="width-15 active"><label class="pull-right">时间：</label></td>
					<td class="width-35"><input id="M2" name="M2" class="easyui-datetimebox" value="${cfsp.m2 }" style="height: 30px;width: 100%;" data-options="editable:false" /></td>
				</tr>
			  <tr>
				  <td class="width-15 active"><label class="pull-right" style="width: 80px">办案部门相关负责人意见：</label></td>
				  <td class="width-35" colspan="3"><input id="M7" name="M7" type="text"  class="easyui-textbox" value="${cfsp.m7 }"   style="height: 60px;width: 100%" /></td>
			  </tr>
			  <tr>
				  <td class="width-15 active"><label class="pull-right" style="width: 80px;">法制部门合法性审查意见：</label></td>
				  <td class="width-35" colspan="3"><input id="M8" name="M8" type="text"  class="easyui-textbox" value="${cfsp.m8 }"   style="height: 60px;width: 100%" /></td>
			  </tr>
			  <tr>
				  <td class="width-15 active"><label class="pull-right">分管领导：</label></td>
				  <td class="width-35" colspan="3"><input  id="M9"name="M9" class="easyui-combobox" value="${cfsp.m9 }"
														   style="width: 100%;height: 30px;"
														   data-options="panelHeight:'142px',required:'true',editable:true,valueField:'text',textField:'text',url:'${ctx}/aqzf/zfry/idlist'"/></td>
			  </tr>
			  <tr>
				  <td class="width-15 active"><label class="pull-right">分管领导审核意见：</label></td>
				  <td class="width-35" colspan="3"><input id="M10" name="M10" type="text"  class="easyui-textbox" value="${cfsp.m10 }"   style="height: 60px;width: 100%" /></td>
			  </tr>
			  <tr>
				  <td class="width-15 active"><label class="pull-right">主要领导：</label></td>
				  <td class="width-35" colspan="3"><input  id="M11"name="M11" class="easyui-combobox" value="${cfsp.m11 }"
														   style="width: 100%;height: 30px;"
														   data-options="panelHeight:'142px',required:'true',editable:true,valueField:'text',textField:'text',url:'${ctx}/aqzf/zfry/idlist'"/></td>
			  </tr>
			  <tr>
				  <td class="width-15 active"><label class="pull-right">主要领导审核意见：</label></td>
				  <td class="width-35" colspan="3"><input id="M12" name="M12" type="text"  class="easyui-textbox" value="${cfsp.m12 }"   style="height: 60px;width: 100%" /></td>
			  </tr>
				<input type="hidden" name="ID3" value="${cfsp.ID3}" />
			  <input type="hidden" name="M3" value="${cfsp.m3}" />
			  <input type="hidden" name="M4" value="${cfsp.m4}" />
			  <input type="hidden" name="M5" value="${cfsp.m5}" />
				<c:if test="${not empty cfsp.ID}">
					<input type="hidden" name="ID" value="${cfsp.ID}" />
					<input type="hidden" name="S1"  value="<fmt:formatDate value="${cfsp.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
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
		    	if(data=='success')
		    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    	else
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    	parent.dg.datagrid('reload');
		    	parent.layer.close(index);//关闭对话框。
		    }    
		});
		
		//富文本渲染
		onloadEditor();
	});
	
</script>
</body>
</html>