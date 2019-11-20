<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>气体信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/zxjkyj/qtxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  <c:if test="${usertype eq '9' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${qtxx.ID1 }" id="ID1" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype eq '9' and action eq 'update'}">
				<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${qtxx.ID1 }" id="ID1" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				<input type="hidden" name="ID1" value="${qtxx.ID1}" />
				</c:if>

			  <tr>
				<td class="width-15 active"><label class="pull-right">位号：</label></td>
				<td class="width-35"><input class="easyui-textbox" name="M1" value="${qtxx.m1 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,20]'" /></td>
				<td class="width-15 active"><label class="pull-right">气体名称：</label></td>
				<td class="width-35"><input class="easyui-textbox" name="M2" value="${qtxx.m2 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,10]'" /></td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">气体类型：</label></td>
				<td class="width-35"><input class="easyui-textbox" name="M3" value="${qtxx.m3 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,10]'" /></td>
				<td class="width-15 active"><label class="pull-right">一级预警(比例小数)：</label></td>
				<td class="width-35"><input class="easyui-textbox" name="level1" value="${qtxx.level1 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">二级预警(比例小数)：</label></td>
				<td class="width-35"><input class="easyui-textbox" name="level2" value="${qtxx.level2 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
				<td class="width-15 active"><label class="pull-right">传感器位置：</label></td>
				<td class="width-35"><input class="easyui-textbox" name="r1" value="${qtxx.r1 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,15]'"  /></td>
			</tr>

			<c:if test="${not empty qtxx.ID}">
				<input type="hidden" name="ID" value="${qtxx.ID}" />
			</c:if>
			<c:if test="${usertype eq '1'}">
				<input type="hidden" name="ID1" value="${qtxx.ID1}" />
			</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;
    var action = '${action}';
    var usertype = '${usertype}';
	function doSubmit(){
	if(usertype=='9'&&action=='create'){
	var options = $("#ID1").combobox('options');  
     	var data = $("#ID1").combobox('getData');/* 下拉框所有选项 */  
     	var value = $("#ID1").combobox('getValue');/* 用户输入的值 */  
     	var b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */  
     	for (var i = 0; i < data.length; i++) {  
        	if (data[i][options.valueField] == value) {  
            	b=true;  
           	 	break;  
        	}  
    	}  
		if(b==false){
				layer.open({title: '提示',offset: 'auto',content: '所选企业不存在！',shade: 0 ,time: 2000 });
				return;
		}
		}
		$("#inputForm").submit(); 
	}
	
	$(function(){
		$('#inputForm').form({    
		    onSubmit: function(){    
		    	var isValid = $(this).form('validate');
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data){ 
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