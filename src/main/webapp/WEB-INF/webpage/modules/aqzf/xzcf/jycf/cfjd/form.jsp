<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加简易处罚决定记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/jycf/cfjd/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<c:if test="${action eq 'update'}">
                    <tr>
                         <td class="width-20 active"><label class="pull-right">编号：</label></td>
                         <td class="width-80" colspan="3"><input name="m0" class="easyui-textbox" editable="false" 
                         		value="${cfjd.m0 }" style="width: 100%;height: 30px;" /></td>
                    </tr>
                </c:if>
				<tr>
       				<td class="width-20 active"><label class="pull-right">受处罚类型：</label></td>
        			<td class="width-30" style="text-align:left">
	           			 <span>
	              		  <input type="radio" name="M1"style="width:15px;height:15px;margin-bottom:3px;" checked="true"  value="1">单位</input>
	               		  <input type="radio" name="M1"style="width:15px;height:15px;margin-bottom:3px;margin-left:10px"  value="2">个人</input>
	            		 </span>
        			</td>
        			<td class="width-20 active"><label class="pull-right">处罚时间：</label></td>
					<td class="width-30"><input id="M24" name="M24" class="easyui-datebox" value="${cfjd.m24 }" style="width: 100%;height: 30px;" /></td>	
   			   	</tr>
				<tr name="company">
					<td class="width-20 active"><label class="pull-right">被处罚单位：</label></td>
					<td class="width-80" colspan="3">
						<input value="${cfjd.m2 }" id="M2" name="M2" style="width: 100%;height: 30px;"
								class="easyui-textbox" data-options="required:'true',validType:['length[0,100]']" />
					</td>
				</tr>
				<tr name="company">
					<td class="width-20 active"><label class="pull-right">地址：</label></td>
					<td class="width-80"  colspan="3"  ><input id="M3"name="M3" class="easyui-textbox" value="${cfjd.m3 }" 
								style="width: 100%;height: 30px;" data-options="validType:['length[0,100]']" /></td>
				</tr>
				<tr name="company">
					<td class="width-20 active"><label class="pull-right">邮编：</label></td>
					<td class="width-30"><input name="M4" type="text" id="M4" value="${cfjd.m4 }"  class="easyui-textbox" 
								style="width: 100%;height: 30px;" data-options="validType:'ZIP'"/></td>
					<td class="width-20 active"><label class="pull-right">法定代表人：</label></td>
					<td class="width-30"><input id="M5" type="text" name="M5" class="easyui-textbox"  value="${cfjd.m5 }" 
								style="width: 100%;height: 30px;" data-options="validType:['length[0,10]']"/></td>
				</tr>
				<tr name="company">
				  	<td class="width-20 active"><label class="pull-right">职务：</label></td>
					<td class="width-30" ><input id="M6" name="M6"  class="easyui-textbox"  value="${cfjd.m6 }" 
								style="width: 100%;height: 30px;" data-options="validType:['length[0,50]']"/></td>
					<td class="width-20 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-30" ><input id="M7" name="M7" class="easyui-textbox"  value="${cfjd.m7 }" 
								style="width: 100%;height: 30px;" validtype="mobileAndTel"/></td>
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">被处罚人：</label></td>
					<td class="width-30" ><input value="${cfjd.m8 }"  id="M8" name="M8" style="width: 100%;height: 30px;"	
								class="easyui-textbox"	data-options="required:'true',validType:['length[0,100]']" />
					</td>
					<td class="width-20 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-30" ><input id="M11" name="M11" validType="idCode" class="easyui-textbox"  
								value="${cfjd.m11 }" style="width: 100%;height: 30px;" />
					</td>
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">性别：</label></td>
					<td class="width-30"><input id="M9" name="M9" class="easyui-combobox" value="${cfjd.m9 }" 
								style="width:100%;height: 30px;" data-options="panelHeight:'auto',editable:false,data: [{value:'男',text:'男'},
                                                {value:'女',text:'女'}]"/></td>	
					<td class="width-20 active"><label class="pull-right">年龄：</label></td>
					<td class="width-30" ><input id="M10" name="M10"  class="easyui-textbox"  value="${cfjd.m10 }" style="width: 100%;height: 30px;" validType="number"/></td>
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">家庭地址：</label></td>
					<td class="width-80"  colspan="3"  ><input id="M12" name="M12" class="easyui-textbox" value="${cfjd.m12 }" 
								style="width: 100%;height: 30px;" data-options="validType:['length[0,100]']" /></td>
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">单位：</label></td>
					<td class="width-80"  colspan="3"  ><input id="M13" name="M13" class="easyui-textbox" value="${cfjd.m13 }" 
								style="width: 100%;height: 30px;" data-options="validType:['length[0,100]']"/></td>	
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">单位地址：</label></td>
					<td class="width-80"  colspan="3"  ><input id="M15" name="M15" class="easyui-textbox" value="${cfjd.m15 }" 
								style="width: 100%;height: 30px;" data-options="validType:['length[0,100]']" /></td>
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">职务：</label></td>
					<td class="width-30" ><input id="M14" name="M14"  class="easyui-textbox"  value="${cfjd.m14 }" style="width: 100%;height: 30px;" 
								data-options="validType:['length[0,50]']"/></td>
					<td class="width-20 active"><label class="pull-right">电话：</label></td>
					<td class="width-30" ><input id="M16" name="M16" class="easyui-textbox"  value="${cfjd.m16 }" style="width: 100%;height: 30px;" 
								validtype="mobileAndTel"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">违法事实和证据：</label></td>
					<td class="width-80" colspan="3" ><input id="M17" name="M17"  class="easyui-textbox" value="${cfjd.m17 }" style="width: 100%;height: 100px;" 
								data-options="multiline:true"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">违反条款：</label></td>
					<td class="width-80"  colspan="3"><input id="M18" name="M18"  class="easyui-textbox" value="${cfjd.m18 }" style="width: 100%;height: 50px;" 
								data-options="multiline:true,validType:['length[0,500]']"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">处罚依据：</label></td>
					<td class="width-80" colspan="3"><input id="M19" name="M19"  class="easyui-textbox" value="${cfjd.m19 }" style="width: 100%;height: 50px;" 
								data-options="multiline:true,validType:['length[0,500]']"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">行政处罚：</label></td>
					<td class="width-80" colspan="3"><input id="M20" name="M20"  class="easyui-textbox" value="${cfjd.m20 }" style="width: 100%;height: 50px;" 
								data-options="multiline:true,validType:['length[0,500]'],required:'true'"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">罚款履行方式：</label></td>
	        		<td class="width-80" style="text-align:left" colspan="3">
	           			 <span>
		              		  <input type="radio" style="width:15px;height:15px;margin-bottom:3px;" checked="true" name="M21" value="1" />当场缴纳
		               		  <input type="radio"style="width:15px;height:15px;margin-bottom:3px;margin-left:10px"  name="M21" value="2" />15内银行缴纳
	            		 </span>
	        		</td>
				</tr>
				<tr>
					<td class="width-20 active" ><label class="pull-right">执法人员：</label></td>
                    <td class="width-30"  ><input  id="M22"name="M22" class="easyui-combobox" value="${cfjd.m22 }" style="width: 100%;height: 30px;" 
                     		data-options="panelHeight:'142px',editable:true,valueField:'text',textField:'text',url:'${ctx}/aqzf/zfry/idlist'"/></td>
                    <td class="width-20 active" ><label class="pull-right">执法人员：</label></td>
                    <td class="width-30"  ><input id="M23"name="M23" class="easyui-combobox" value="${cfjd.m23 }" style="width: 100%;height: 30px;"
                      		data-options="panelHeight:'142px',editable:true,valueField:'text',textField:'text',url:'${ctx}/aqzf/zfry/idlist'"/></td>
				</tr>
				
				<input type="hidden" name="ID1" value="${cfjd.ID1}" />
				<c:if test="${not empty cfjd.ID}">
					<input type="hidden" name="ID" value="${cfjd.ID}" />
					<input type="hidden" name="S1"  value="<fmt:formatDate value="${cfjd.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</c:if>
		 </tbody>
	</table>
</form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var a='${action}';

$(function(){
	if(a=="create"){
	    $('#M24').datebox('setValue', format(new Date()));	
		comResult();
	}else{
		if('${cfjd.m1}'=='1'){
			$("input[name='M1']").get(0).checked=true; 
			comResult();
		}else{
			$("input[name='M1']").get(1).checked=true; 
			personResult();
		}
		if('${cfjd.m21}'=='1'){
			$("input[name='M21']").get(0).checked=true; 
		}else{
			$("input[name='M21']").get(1).checked=true; 
		}
	}
});

function format(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};

function  comResult(){
	$("[name='person']").hide();
	$("[name='company']").show();
	//$("#M2").textbox("enable" );
	//$("#M3").textbox("enable" ); 
	//$("#M4").textbox("enable" ); 
	//$("#M5").textbox("enable" ); 
	$("#M6").textbox("enable" ); 
	//$("#M7").textbox("enable" );  
	$("#M8").textbox("disable"); 
	$("#M9").combobox("disable"); 
	$("#M10").textbox("disable"); 
	$("#M11").textbox("disable"); 
	$("#M12").textbox("disable"); 
	//$("#M13").textbox("disable"); 
	$("#M14").textbox("disable"); 
	//$("#M15").textbox("disable"); 
	$("#M16").textbox("disable"); 
}

function  personResult(){
	$("[name='person']").show();
	$("[name='company']").hide();
	//$("#M2").textbox("disable" );
	//$("#M3").textbox("disable" ); 
	//$("#M4").textbox("disable" ); 
	//$("#M5").textbox("disable" ); 
	$("#M6").textbox("disable" ); 
	//$("#M7").textbox("disable" );  
	$("#M8").textbox("enable"); 
	$("#M9").combobox("enable"); 
	$("#M10").textbox("enable"); 
	$("#M11").textbox("enable"); 
	$("#M12").textbox("enable"); 
	//$("#M13").textbox("enable"); 
	$("#M14").textbox("enable"); 
	//$("#M15").textbox("enable"); 
	$("#M16").textbox("enable"); 
}

$("input[name='M1']").change(function() {
	var m1flag = $("input[name='M1']:checked").val();
    if(m1flag=="1"){
   		comResult();
    }
    if(m1flag=="2"){
    	personResult();
    }
});

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