<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加惩罚告知记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/ybcf/cfgz/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<c:if test="${action eq 'updateSub'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">编号：</label></td>
						<td class="width-35"  colspan="3"><input name="number" class="easyui-textbox" editable="false" value="${jie.number }" style="width: 100%;height: 30px;" /></td>
					</tr>
				</c:if>
				<tr>
					<td class="width-15 active" ><label class="pull-right">当事人:</label></td>
					<td class="width-35"  >
					<input name="name" id="name" class="easyui-textbox" value="${jie.name }" style="width: 100%;height: 30px;" data-options="validType:['length[0,100]'],required:'true'"/>
					</td>
					<td class="width-15 active"><label class="pull-right">处罚告知时间：</label></td>
					<td class="width-35"><input id="punishdate"name="punishdate" class="easyui-datebox" value="${jie.punishdate }" style="width: 100%;height: 30px;" data-options="required:'true'"/></td>	
				</tr>
		        <tr>
					<td class="width-15 active"><label class="pull-right">案件基本情况：</label></td>
					<td class="width-35" colspan="3"><input id="illegalact" name="illegalact"  class="easyui-textbox" value="${jie.illegalact }" style="width: 100%;height: 80px;" data-options="multiline:true , validType:['length[0,1000]'],required:'true'"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">违法行为：</label></td>
					<td class="width-35"  colspan="3"><input id="wfxw" name="wfxw"  class="easyui-textbox" value="${jie.wfxw }" style="width: 100%;height: 50px;" data-options="multiline:true,validType:['length[0,500]'],required:'true'"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">主要证据：</label></td>
					<td class="width-85" colspan="3">
					<input id="evidence" name="evidence"  class="easyui-textbox" value="${jie.evidence }" style="width: 100%;height: 80px;" data-options="multiline:true , validType:['length[0,1000]'],required:'true'"/></td>
				</tr> 
				<tr>
					<td class="width-15 active"><label class="pull-right">违反条款：</label></td>
					<td class="width-35"  colspan="3"><input id="unlaw" name="unlaw"  class="easyui-textbox" value="${jie.unlaw }" style="width: 100%;height: 50px;" data-options="multiline:true,validType:['length[0,500]']"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">处罚依据：</label></td>
					<td class="width-35" colspan="3"><input id="enlaw" name="enlaw"  class="easyui-textbox" value="${jie.enlaw }" style="width: 100%;height: 50px;" data-options="multiline:true,validType:['length[0,500]']"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">行政处罚：</label></td>
					<td class="width-35" colspan="3"><input id="xzcf" name="xzcf"  class="easyui-textbox" value="${jie.xzcf }" style="width: 100%;height: 50px;" data-options="multiline:true,validType:['length[0,250]'],required:'true'"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">详情：</label></td>
					<td class="width-35" colspan="3"><input id="punishresult" name="punishresult"  class="easyui-textbox" value="${jie.punishresult }" style="width: 100%;height: 200px;" data-options="multiline:true"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系人：</label></td>
				    <td class="width-35"><input id="contactname" name="contactname" type="text"  class="easyui-combobox" value="${jie.contactname }"  data-options="panelHeight:'142px',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/aqzf/zfry/idlist',validType:'length[0,25]'" style="height: 30px;width: 100%" /></td>
				    <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
				    <td class="width-35"><input id="phone" name="phone" type="text"  class="easyui-textbox" value="${jie.phone }"  data-options="validType:['length[0,20]','mobileAndTel'] " style="height: 30px;width: 100%" /></td>	    
				</tr>
				<!-- 隐藏域 -->
				 <input type='hidden' name="S1" value="<fmt:formatDate value="${jie.s1}"/>"/>
				 <input type="hidden" name="ID" value="${jie.ID}" />
				 <input type="hidden" name="id1" value="${jie.id1}" />
				 <input type="hidden" name="cftype" value="${jie.cftype}" />
			</tbody>
		</table>
</form>
<script type="text/javascript">
$(function(){
	if('${action}'=='createSub'){
		$('#punishdate').datebox('setValue', format(new Date()));	
	}
});
function format(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};

//下拉关联信息
$("#contactname").combobox({
	onSelect: function(){
			var m1=$("#contactname").combobox('getValue');
			$.ajax({
			type:'get',
			url:ctx+"/aqzf/zfry/findbym1/"+m1,
			success: function(data){
				if(data != null){
					$("#phone").textbox('setValue',data.m5);
				}
			}
		});
	}
});

var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
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
			return false; //返回false终止表单提交
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