<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>导出卷宗</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/ybcf/lasp/exportjz"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-30 active"><label class="pull-right">立案日期：</label></td>
					<td class="width-70"><input id="larq" name="larq" class="easyui-datebox" value="${larq }" style="width: 100%;height: 30px;" 
						data-options="required:true,editable:false" /></td>
				</tr>
				<tr>
					<td class="width-30 active"><label class="pull-right">结案日期：</label></td>
					<td class="width-70"><input id="jarq" name="jarq" class="easyui-datebox" value="${jarq }" style="width: 100%;height: 30px;" 
						data-options="required:true,editable:false" /></td>
				</tr>
				<tr>
					<td class="width-30 active"><label class="pull-right">归档日期：</label></td>
					<td class="width-70"><input id="gdrq" name="gdrq" class="easyui-datebox" value="${gdrq }" style="width: 100%;height: 30px;" 
						data-options="required:true,editable:false" /></td>
				</tr>
				<tr>
					<td class="width-30 active"><label class="pull-right">归档号：</label></td>
					<td class="width-70"><input id="gdh" name="gdh" class="easyui-textbox" value="2019" style="width: 100%;height: 30px;" 
						data-options="required:true" /></td>
				</tr>
				<tr>
					<td class="width-30 active"><label class="pull-right">保存期限：</label></td>
					<td class="width-70"><input name="bcqx" class="easyui-combobox" value="30年" style="width: 100%;height: 30px;" data-options="required:true,editable:false,panelHeight:'auto',data: [
										{value:'30年',text:'30年'},
								        {value:'永久',text:'永久'}]" /></td>
				</tr>
				<input type="hidden" name="id" value="${id}" />	
             </tbody>
		</table>
	
</form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

//提交
function doSubmit(){
	$("#inputForm").serializeObject();
	$("#inputForm").submit();
}

$(function(){
	$('#inputForm').form({
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
	    	if(isValid){
	    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
	    		return true;
	    	}
			return false; //返回false终止表单提交
	    },
	    success:function(data){
	    	$.jBox.closeTip();
	    	window.open(ctx + data);
	    }    
	});
});
</script>
</body>
</html>