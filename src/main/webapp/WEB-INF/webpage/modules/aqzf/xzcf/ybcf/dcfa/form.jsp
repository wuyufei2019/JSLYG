<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>询问笔录管理</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
    <script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/ybcf/dcfa/index.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/xzcf/ybcf/dcfa/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  <tr>
				  <td class="width-15 active"><label class="pull-right">企业名称：</label></td>
				  <td class="width-35"><input id="dsperson" name="dsperson" type="text"  class="easyui-textbox" value="${dcfa.dsperson}"  data-options="editable:false" style="height: 30px;width: 100%" /></td>
				  <td class="width-15 active"><label class="pull-right">立案编号：</label></td>
				  <td class="width-35"><input id="M1" name="M1" type="text" value="${dcfa.m1}"  class="easyui-textbox" data-options="editable:false" style="height: 30px;width: 100%" /></td>
			  </tr>
			    <tr>
					<td class="width-15 active"><label class="pull-right">调查开始时间：</label></td>
					<td class="width-35"><input id="M2" name="M2" class="easyui-datetimebox" value="${dcfa.m2 }" style="height: 30px;width: 100%;" data-options="editable:false" /></td>
				    <td class="width-15 active"><label class="pull-right">调查结束时间：</label></td>
					<td class="width-35"><input id="M3" name="M3" class="easyui-datetimebox" value="${dcfa.m3 }" style="height: 30px;width: 100%;" data-options="editable:false" /></td>
				</tr>
			  <tr>
				  <td class="width-15 active"><label class="pull-right">办案部门：</label></td>
				  <td class="width-35"><input id="M9" name="M9" type="text"  class="easyui-textbox" value="${dcfa.m9 }"  data-options="required:true" style="height: 30px;width: 100%" /></td>
				  <td class="width-15 active"><label class="pull-right">调查人员：</label></td>
				  <td class="width-35"><input  id="M4"name="M4" class="easyui-combobox" value="${dcfa.m4 }"
											   style="width: 100%;height: 30px;"
											   data-options="panelHeight:'142px',required:'true',multiple:true,editable:true,valueField:'text',textField:'text',url:'${ctx}/aqzf/zfry/idlist'"/></td>
			  </tr>
			  <tr>
				  <td class="width-15 active"><label class="pull-right">部门负责人：</label></td>
				  <td class="width-35"><input id="M7" name="M7" type="text"  class="easyui-textbox" value="${dcfa.m7 }"  data-options="required:true" style="height: 30px;width: 100%" /></td>
				  <td class="width-15 active"><label class="pull-right">分管领导：</label></td>
				  <td class="width-35"><input id="M8" name="M8" type="text"  class="easyui-textbox" value="${dcfa.m8 }"  data-options="required:true" style="height: 30px;width: 100%" /></td>
			  </tr>

			  <tr>
				  <td class="width-15 active"><label class="pull-right">调查步骤：</label></td>
				  <td class="width-35" colspan="3"><textarea id="M5" name="M5"
															 style="width:100%;height:300px;visibility:hidden;">${dcfa.m5 }</textarea></td>
			  </tr>
			  <tr>
				  <td class="width-15 active"><label class="pull-right">调查报告：</label></td>
				  <td class="width-35" colspan="3"><input type="text" id="M6" name="M6" class="easyui-textbox"
														  value="${dcfa.m6 }"  style="height: 50px;width: 100%"
														  data-options="multiline:true"/></td>
			  </tr>

				<input type="hidden" name="ID3" value="${dcfa.ID3}" />
				<c:if test="${not empty dcfa.ID}">
					<input type="hidden" name="ID" value="${dcfa.ID}" />
					<input type="hidden" name="S1"  value="<fmt:formatDate value="${dcfa.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

    $(function(){
        if('${action}' =='create'){
            $("#M6").textbox("setValue","完成调查后，于3日内提交调查报告。");
            $("#M5").val("1、对该公司存在问题进行核实，确认其是否存在${unlaw}的违法行为。<br>" +
            " 2、对该公司有关人员进行调查询问，做询问笔录，确认其是否存在${unlaw}的违法行为。<br>" +
            " 3、收集该公司的营业执照复印件。<br>" +
            " 4、收集该公司有关人员的身份证复印件等。");
		}
	});
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