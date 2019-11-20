<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>缴批记录管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/xzcf/ybcf/fkpz/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${action eq 'update'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">缴批编号：</label></td>
						<td class="width-30" colspan="3"><input data-options="readonly:'true' " type="text" id="M1" name="M1" class="easyui-textbox" value="${fkpz.m1 }"  style="height: 30px;width: 100%" /></td>
					</tr>
				</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">当事人：</label></td>
					<td class="width-30" >
						<input style="width:100%;height: 30px;"  id="M2"name="M2"  class="easyui-textbox" value="${fkpz.m2 }" 
						data-options="required:'true',validType:'length[0,100]'" />
				   </td>	
				   <td class="width-20 active"><label class="pull-right">批准日期：</label></td>
				   <td class="width-30"><input name="M11" id="M11" class="easyui-datebox" value="${fkpz.m11 }" style="width: 100%;height: 30px;" /></td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">行政处罚：</label></td>
					<td class="width-30" colspan="3" >
						<input style="width:100%;height: 50px;"  id="M3"name="M3"  class="easyui-textbox" value="${fkpz.m3 }" 
						data-options="multiline:true,readonly:'true'" />
				   </td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">罚款总计（大写）：</label></td>
					<td class="width-30" >
						<input style="width:100%;height: 30px;"  id="M4"name="M4"  class="easyui-textbox" value="${fkpz.m4 }" 
						data-options="validType:'length[0,50]'" />
				   </td>	
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">类型：</label></td>
					<td class="width-30" colspan="3">
					<input type="radio" name="M5" style="margin-bottom: 6px;" value="1"> 延期缴纳罚款
					<input type="radio" name="M5" style="margin-bottom: 6px;margin-left: 20px;" value="2"> 分期缴纳罚款
                    </td>	
				</tr>
				
				<tr id="z1">
					<td class="width-20 active"><label class="pull-right">延期截止日期：</label></td>
					<td class="width-30"><input name="M6" id="M6" class="easyui-datebox" value="${fkpz.m6 }" style="width: 100%;height: 30px;" /></td>	
				</tr>
				
				
				
				<tr id="z2">
					<td class="width-20 active"><label class="pull-right">期数：</label></td>
					<td class="width-30" ><input style="width:100%;height: 30px;"  id="M7"name="M7"  class="easyui-textbox" value="${fkpz.m7 }" 
						data-options="validType:'length[0,25]'" />
				   	</td>
					<td class="width-20 active"><label class="pull-right">分期截止日期：</label></td>
					<td class="width-30"><input name="M8" id="M8" class="easyui-datebox" value="${fkpz.m8 }" style="width: 100%;height: 30px;" /></td>	
				</tr>
				<tr id="z3">
					<td class="width-20 active"><label class="pull-right">缴纳罚款（大写）：</label></td>
					<td class="width-30" >
						<input style="width:100%;height: 30px;"  id="M9"name="M9"  class="easyui-textbox" value="${fkpz.m9 }" 
						data-options="validType:'length[0,50]'" />
				    </td>
					<td class="width-20 active"><label class="pull-right">未缴纳罚款（大写）：</label></td>
					<td class="width-30" >
						<input style="width:100%;height: 30px;"  id="M10"name="M10"  class="easyui-textbox" value="${fkpz.m10 }" 
						data-options="validType:'length[0,50]'" />
				    </td>	
				</tr>
								
				
				<input type="hidden" name="ID1" value="${fkpz.ID1}" />
				<c:if test="${not empty fkpz.ID}">
					<input type="hidden" name="ID" value="${fkpz.ID}" />
					<input type="hidden" name="S1"  value="<fmt:formatDate value="${fkpz.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
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
	
	function format(date) {
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1);
		return date.getFullYear() + '-' + month + '-' + day;
	};
	
	$(function(){
		if('${action}'=='create'){
			var date=new Date();
			$('#M11').datebox('setValue', format(new Date()));	
			$("input[name='M5']").get(0).checked = true;
			$('#z2').hide();
			$('#z3').hide();
		}
		
		if('${action}'=='update'){
			if('${fkpz.m5}'==1){
				$("input[name='M5']").get(0).checked = true;
				$('#z1').show();
				$('#z2').hide();
				$('#z3').hide();
			}else{
				$("input[name='M5']").get(1).checked = true;
				$('#z1').hide();
				$('#z2').show();
				$('#z3').show();
			}
		}
		
		$(":radio").change(function() {
			var flag = $('input:radio:checked').val();
			if (flag == "1") {
				$('#z1').show();
				$('#z2').hide();
				$('#z3').hide();
			}else if (flag == "2") {
				$('#z1').hide();
				$('#z2').show();
				$('#z3').show();
			}
		});
	});
</script>
</body>
</html>