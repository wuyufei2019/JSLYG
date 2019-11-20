<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>临时询问通知管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/xzcf/ybcf/sgxwtz/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">被询问单位：</label></td>
				    <td class="width-35"><input id="ID2" name="ID2" type="text"  class="easyui-combobox" value="${sgxwtz.ID2}"  style="height: 30px;width: 100%" data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '若不存在该企业，请先添加!'" /></td>
				    <td class="width-15 active"><label class="pull-right">询问时间：</label></td>
				<td class="width-35"><input id="M2" name="M2" class="easyui-datetimebox" value="${sgxwtz.m2 }" style="height: 30px;width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
				<tr>
					<td class="width-15 active"><label class="pull-right">调查主题：</label></td>
					<td class="width-35" colspan="3" >
						<input style="width:100%;height: 30px;"  id="M1"name="M1"  class="easyui-textbox" value="${sgxwtz.m1 }" 
						data-options="required:'true',validType:'length[0,100]'" />
				   </td>	
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">询问地点：</label></td>
				    <td class="width-35" colspan="3"><input id="M3" name="M3" type="text"  class="easyui-textbox" value="${sgxwtz.m3 }"  data-options="required:'true',validType:'length[0,100]'" style="height: 30px;width: 100%" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">证件材料：</label></td>
				    <td class="width-35" colspan="3">
				    <input type="text" id="M5" name="M5" class="easyui-combobox" value="${sgxwtz.m5 }" style="width: 100%;height: 30px;" data-options="panelHeight:'auto', editable:false , multiple:true,data: [
							        {value:'身份证',text:'身份证'},
							        {value:'营业执照',text:'营业执照'},
							         {value:'法定代表人身份证明或者委托书',text:'法定代表人身份证明或者委托书'}
							        	]  "/>
				    </td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">其他材料：</label></td>
					<td class="width-35" colspan="3">
			        <input value="${sgxwtz.m4}" type="text" id="M4" name="M4" class="easyui-textbox" style="height: 80px;width: 100%"
								data-options="multiline:true,validType:'length[0,500]'" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系人：</label></td>
				    <td class="width-35"><input id="M6" name="M6" type="text"  class="easyui-combobox" value="${sgxwtz.m6 }"  data-options="panelHeight:'142px',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/aqzf/zfry/idlist',validType:'length[0,25]'" style="height: 30px;width: 100%" /></td>
				    <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
				    <td class="width-35"><input id="M7" name="M7" type="text"  class="easyui-textbox" value="${sgxwtz.m7 }"  data-options="validType:['length[0,20]','mobileAndTel'] " style="height: 30px;width: 100%" /></td>	    
				</tr>
				<c:if test="${not empty sgxwtz.ID}">
					<input type="hidden" name="ID" value="${sgxwtz.ID}" />
					<input type="hidden" name="ID1" value="${sgxwtz.ID1}" />
					<input type="hidden" name="flag" value="${sgxwtz.flag}" />
					<input type="hidden" name="S1"  value="<fmt:formatDate value="${sgxwtz.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	$(function(){
		if('${action}' == 'create'){
	    	$("#M2").datetimebox("setValue",formate(new Date()));  
	    }
	    
	    $("input",$("#ID2").next("span")).blur(function(){
			var he = /^[0-9]+.?[0-9]*$/;  
			var qyflag = $("#ID2").combobox("getValue");
			if(!he.test(qyflag) && qyflag != ''){
				$("#ID2").combobox("setValue","");
			}
		}) 
	});
	
	function formate(curr_time){
		 var strDate=curr_time.getFullYear()+"-";     
		    strDate +=curr_time.getMonth()+1+"-";     
		    strDate +=curr_time.getDate()+"-";     
		    strDate +=" "+curr_time.getHours()+":";     
		    strDate +=curr_time.getMinutes()+":";     
		    strDate +=curr_time.getSeconds();  
		    return strDate;
	}
	
	//下拉关联信息
	$("#M6").combobox({
		onSelect: function(){
				var m1=$("#M6").combobox('getValue');
				$.ajax({
				type:'get',
				url:ctx+"/aqzf/zfry/findbym1/"+m1,
				success: function(data){
					if(data != null){
						$("#M7").textbox('setValue',data.m5);
					}
				}
			});
		}
	});
	
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