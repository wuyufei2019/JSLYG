<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>违法行为裁量管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqzf/cfcl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
				    <td class="width-20 active"><label class="pull-right">处罚档次：</label></td>
					<td class="width-80" colspan="3">
						<input value="${wfxwcl.m1}" type="text" id="M1" name="M1" style="width:100%;height: 30px;" class="easyui-textbox" data-options="required:true,validType:'length[0,250]'" />
					</td>
				</tr>
				<tr>
				    <td class="width-20 active"><label class="pull-right">裁量幅度：</label></td>
					<td class="width-80" colspan="3">
						<input value="${wfxwcl.m2}" type="text" id="M2" name="M2" style="width:100%;height: 80px;" class="easyui-textbox" data-options="multiline:true ,validType:'length[0,1000]'" />
					</td>
				</tr>
				<tr>
				    <td class="width-20 active"><label class="pull-right">最小E值（元）：</label></td>
					<td class="width-30" >
						<input value="${wfxwcl.m3}" type="text" id="M3" name="M3" style="width:100%;height: 30px;" class="easyui-numberbox" data-options="required:true,min:0" />
					</td>
					<td class="width-20 active"><label class="pull-right">最大E值（元）：</label></td>
					<td class="width-30" >
						<input value="${wfxwcl.m4}" type="text" id="M4" name="M4" style="width:100%;height: 30px;" class="easyui-numberbox" data-options="required:true,min:0" />
					</td>
				</tr>
				<tr>
				    <td class="width-20 active"><label class="pull-right">档位编号：</label></td>
					<td class="width-80" colspan="3">
						<input value="${wfxwcl.m5}" type="text" id="M5" name="M5" style="width:36%;height: 30px;" class="easyui-numberbox" data-options="required:true,min:0" />
						<font style="color: red;">（不涉及分档为0一档为1二档为2三档为3依次类推）</font>
					</td>
				</tr>
				
				<input type="hidden" name="ID1" value="${wfxwcl.ID1}" />
				<input type="hidden" name="M6" value="${wfxwcl.m6}" />
				<c:if test="${not empty wfxwcl.ID}">
					<input type="hidden" name="ID" value="${wfxwcl.ID}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;
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
		    		parent.parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    		parent.dg.datagrid('reload');
		    		parent.layer.close(index);//关闭对话框。
		    	}else{
		    		parent.parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    	}
		    }    
		});
	
	});
	
</script>
</body>
</html>