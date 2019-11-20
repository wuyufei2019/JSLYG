<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>储罐维护</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

	function doSubmit(){
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
</head>
<body>

     <form id="inputForm" action="${ctx}/zxjkyj/cgxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			    <tr >
					<td class="width-20 active"><label class="pull-right">储罐名称：</label></td>
					<td class="width-30" colspan="3">
						<input name="M1" class="easyui-textbox" value="${cglist.m1 }" style="width: 100%;height: 30px;" data-options="editable:false,required:'true',validType:'length[0,50]'" />
					</td>
				</tr>
				
				<tr >
					<td class="width-20 active"><label class="pull-right">位号：</label></td>
					<td class="width-30" colspan="3">
						<input name="M9"  class="easyui-textbox" value="${cglist.m9 }" style="width: 100%;height: 30px;"
								data-options="editable:false" />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">罐径（m）：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="M10" value="${cglist.m10 }" style="width: 100%;height: 30px;" data-options="editable:false,validType:'mone'" /></td>
					<td class="width-20 active"><label class="pull-right">罐高（m）：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="M11" value="${cglist.m11 }" style="width: 100%;height: 30px;" data-options="editable:false,validType:'mone'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">容积（㎥）：</label></td>
					<td class="width-30" colspan="3"><input name="M3" class="easyui-textbox" value="${cglist.m3 }" style="width: 100%;height: 30px;" data-options="editable:false,validType:'mone'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">一级液位预警比例（小数）：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="Level1" value="${cglist.level1 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
					<td class="width-20 active"><label class="pull-right">二级液位预警比例（小数）：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="Level2" value="${cglist.level2 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">一级高温预警（℃）：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="Temperature1" value="${cglist.temperature1 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
					<td class="width-20 active"><label class="pull-right">二级高温预警（℃）：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="Temperature2" value="${cglist.temperature2 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">一级高压预警（MPa）：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="Pressure1" value="${cglist.pressure1 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
					<td class="width-20 active"><label class="pull-right">二级高压预警（MPa）：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="Pressure2" value="${cglist.pressure2 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
				</tr>
				<c:if test="${usertype eq '9'}">
				<tr>
				    <td class="width-20 active"><label class="pull-right">  液位点号：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="r1" value="${cglist.r1 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,10]'" /></td>
					<td class="width-20 active"><label class="pull-right">  温度点号：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="r2" value="${cglist.r2 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,10]'" /></td>
				</tr>	
				<tr>
				    <td class="width-20 active"><label class="pull-right">  压力点号 ：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="r3" value="${cglist.r3 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,10]'" /></td>
					<td class="width-20 active"><label class="pull-right">  流量点号 ：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="r4" value="${cglist.r4 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,10]'" /></td>
				</tr>
				</c:if>
				<c:if test="${not empty cglist.ID}">
					<input type="hidden" name="ID" value="${cglist.ID}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
</body>
</html>