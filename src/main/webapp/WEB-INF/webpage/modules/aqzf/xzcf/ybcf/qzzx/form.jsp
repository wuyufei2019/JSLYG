<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加强制执行申请记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/ybcf/qzzx/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<c:if test="${action eq 'updateSub'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">编号：</label></td>
					<td class="width-30"  colspan="3"><input name="number" class="easyui-textbox" editable="false" value="${yqe.number }" 
                         style="width: 100%;height: 30px;" /></td>
				</tr>
				</c:if>
				
				<tr>
					<td class="width-20 active" ><label class="pull-right">签发人:</label></td>
					<td class="width-30" ><input name="qfr" id="qfr" class="easyui-textbox" value="${yqe.qfr }"
                         style="width: 100%;height: 30px;" data-options="validType:['length[0,25]']"/>
					</td>
                    <td class="width-20 active"><label class="pull-right">强制执行日期：</label></td>
                    <td class="width-30"><input id="qzzxsj"name="qzzxsj" class="easyui-datebox" value="${yqe.qzzxsj }" 
                         style="width: 100%;height: 30px;"data-options="editable:false"/></td>
				</tr>
				<tr>
					<td class="width-20 active" ><label class="pull-right">当事人:</label></td>
					<td class="width-30" ><input name="dsname" id="dsname" class="easyui-textbox" value="${yqe.dsname }"editable="false"
                         style="width: 100%;height: 30px;" data-options="validType:['length[0,100]'],required:'true'"/>
					</td>
                    <td class="width-20 active"><label class="pull-right">法院名称：</label></td>
                    <td class="width-30"><input id="court" name="court"  class="easyui-textbox" value="${yqe.court }" 
                    style="width: 100%;height: 30px;"  data-options="multiline:true , validType:['length[0,50]']"/></td>
				</tr>
                <tr>
                     <td class="width-20 active"><label class="pull-right">相关材料：</label></td>
                     <td class="width-30" colspan="3"><input id="clname" name="clname"  class="easyui-combobox" value="${yqe.clname}" 
                     style="width: 100%;height: 80px;" 
                    data-options="multiline:true,panelHeight:'auto',multiple:true,required:'true',editable:true,valueField:'text',textField:'text',url:'${ctx}/xzcf/ybcf/lasp/numberlist/${id1}'" /></td>
                </tr>
                <tr>
					<td class="width-20 active"><label class="pull-right">联系人：</label></td>
				    <td class="width-30"><input id="contactname" name="contactname" type="text"  class="easyui-combobox" value="${yqe.contactname }"  data-options="panelHeight:'142px',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/aqzf/zfry/idlist',validType:'length[0,25]'" style="height: 30px;width: 100%" /></td>
				    <td class="width-20 active"><label class="pull-right">联系电话：</label></td>
				    <td class="width-30"><input id="phone" name="phone" type="text"  class="easyui-textbox" value="${yqe.phone }"  data-options="validType:['length[0,20]','mobileAndTel'] " style="height: 30px;width: 100%" /></td>	    
				</tr>
				<!-- 隐藏域 -->
				 <input type='hidden' name="S1" value="<fmt:formatDate value="${yqe.s1}"/>"/>
				 <input type="hidden" name="ID" value="${yqe.ID}" />
				 <input type="hidden" name="id1" value="${yqe.id1}" />
			</tbody>
		</table>
</form>
<script type="text/javascript">
$(function(){
	if('${action}'=='createSub'){
	$('#qzzxsj').datebox('setValue', format(new Date()));	
	$("input[name='id1']").val('${id1}');
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