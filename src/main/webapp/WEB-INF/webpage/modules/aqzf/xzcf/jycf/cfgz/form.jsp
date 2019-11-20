<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加简易处罚告知记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/jycf/cfgz/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<c:if test="${action eq 'update'}">
						<tr>
							<td class="width-15 active"><label class="pull-right">编号：</label></td>
							<td class="width-35" colspan="3"><input name="M0" class="easyui-textbox" editable="false" value="${jygz.m0 }" style="width: 100%;height: 30px;" /></td>
						</tr>
					</c:if>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-35" ><input name="ID1" id="ID1" value="${jygz.ID1 }" class="easyui-combobox" style="width: 100%;height: 30px;" 
					data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '若不存在该企业，请先添加!'" />
					</td>
					<td class="width-15 active"><label class="pull-right">处罚告知时间：</label></td>
					<td class="width-35"><input id="M10"name="M10" class="easyui-datebox" value="${jygz.m10 }" style="width: 100%;height: 30px;" 
					data-options="required:'true'"/></td>	
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">违法行为描述：</label></td>
					<td class="width-85" colspan="3"><input id="M2" name="M2"  class="easyui-textbox" value="${jygz.m2 }" style="width: 100%;height: 80px;" 
					data-options="multiline:true , validType:['length[0,1000]'],required:'true'"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">证据：</label></td>
					<td class="width-85" colspan="3"><input id="M3" name="M3"  class="easyui-textbox" value="${jygz.m3 }" style="width: 100%;height: 80px;" 
					data-options="multiline:true , validType:['length[0,1000]']"/></td>
				</tr>
				<tr>
				    <td align="center" colspan="4">
						<a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addRw()" title="添加违法行为"><i class="fa fa-plus"></i> 添加违法行为</a>
					</td>	
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">涉嫌违法行为：</label></td>
					<td class="width-85" colspan="3">
						<table id="rwtable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
							<tr >
								<td style="text-align: center;width: 84%;">违法行为</td>
								<td style="text-align: center;">操作</td>
							</tr>
						</table>	
					</td>
				</tr>	
				<tr>
					<td class="width-15 active"><label class="pull-right">违反条款：</label></td>
					<td class="width-85"  colspan="3"><input id="M5" name="M5"  class="easyui-textbox" value="${jygz.m5 }" style="width: 100%;height: 50px;" 
					data-options="multiline:true,validType:['length[0,500]']"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">处罚依据：</label></td>
					<td class="width-85" colspan="3"><input id="M6" name="M6"  class="easyui-textbox" value="${jygz.m6 }" style="width: 100%;height: 50px;" 
					data-options="multiline:true,validType:['length[0,500]']"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">行政处罚：</label></td>
					<td class="width-85" colspan="3"><input id="M7" name="M7"  class="easyui-textbox" value="${jygz.m7 }" style="width: 100%;height: 50px;" 
					data-options="multiline:true,validType:['length[0,500]'],required:'true'"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系人：</label></td>
				    <td class="width-35"><input id="M8" name="M8" type="text"  class="easyui-combobox" value="${jygz.m8 }"  data-options="panelHeight:'142px',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/aqzf/zfry/idlist',validType:'length[0,25]'" style="height: 30px;width: 100%" /></td>
				    <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
				    <td class="width-35"><input id="M9" name="M9" type="text"  class="easyui-textbox" value="${jygz.m9 }"  data-options="validType:['length[0,20]','mobileAndTel'] " style="height: 30px;width: 100%" /></td>	    
				</tr>
				
				<input type="hidden" id="M1" name="M1" value="${jygz.m1}" />
				<c:if test="${not empty jygz.ID}">
					<input type="hidden" name="ID" value="${jygz.ID}" />
					<input type="hidden" name="userid" value="${jygz.userid}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${jygz.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="csflag" value="${jygz.csflag}" />
					<input type="hidden" name="jdflag" value="${jygz.jdflag}" />
				</c:if>
			</tbody>
	 </table>
</form>
<script type="text/javascript">
$(function(){
	$("input",$("#ID1").next("span")).blur(function(){
		var he = /^[0-9]+.?[0-9]*$/;  
		var qyflag = $("#ID1").combobox("getValue");
		if(!he.test(qyflag) && qyflag != ''){
			$("#ID1").combobox("setValue","");
		}
	}); 
	
	if('${action}'=='create'){
		$('#M10').datebox('setValue', format(new Date()));	
	}
});

function format(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};

//下拉关联信息
$("#M8").combobox({
	onSelect: function(){
			var m1=$("#M8").combobox('getValue');
			$.ajax({
			type:'get',
			url:ctx+"/aqzf/zfry/findbym1/"+m1,
			success: function(data){
				if(data != null){
					$("#M9").textbox('setValue',data.m5);
				}
			}
		});
	}
});

var rwid=1;
function addRw() {
	var $list= $("#rwtable tr").eq(-1);
	var $li = $(
				'<tr style="height:40px" >'+
				'<td style="width:84%" align="center">'+
				'<input data-options="" type="text" id="aj_'+rwid+'" name="M4" class="easyui-textbox" value=""  style="height: 30px;width: 99%;" />'+
				'</td>'+
				'<td align="center">'+
				'<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
				'</td>'+
				'</tr>'
	            );
	$list.after( $li );
	
	$('#aj_'+rwid).combobox({  
		editable:true,
		url:'${ctx}/aqzf/wfxw/idlist',
		valueField:'id',
		textField:'text',
		panelHeight:'120px',
		onChange: function(){
			gbzt();
		}
	}); 
	rwid=rwid+1;
}

//删除指定行的任务
function removeTr(obj) {
	obj.remove();
	gbzt();
}

function gbzt(){
	var wfids='';
	var els =document.getElementsByName("M4");
	for (var i = 0, j = els.length; i < j; i++){
	    if(i==0){
	    	wfids += els[i].value;
	    }else{
	    	wfids += ","+els[i].value;
	    }
	}
	if(wfids!=undefined&&wfids.length != 0){
		$.ajax({
			type:'get',
			url:ctx+"/aqzf/wfxw/wfall/"+wfids,
			success: function(data){
				$("#M5").textbox('setValue',data.m2);
				$("#M6").textbox('setValue',data.m4);
			}
		});
	}else{
		$("#M5").textbox('setValue',"");
		$("#M6").textbox('setValue',"");
	}
}

if('${action}' == 'update'){
	var $list= $("#rwtable tr").eq(-1);
	var M4 = '${jygz.m4}';
	var arr = M4.split(',');
	for(j = 0; j < arr.length; j++) {
   		var $li = $(
				'<tr style="height:40px" >'+
				'<td style="width:84%" align="center">'+
				'<input data-options="" type="text" id="aj_'+rwid+'" name="M4" class="easyui-textbox" value="'+arr[j]+'"  style="height: 30px;width: 99%;" />'+
				'</td>'+
				'<td align="center">'+
				'<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
				'</td>'+
				'</tr>'
	            );
	    $list.after( $li );
	
		$('#aj_'+rwid).combobox({  
			editable:true,
			url:'${ctx}/aqzf/wfxw/idlist',
			valueField:'id',
			textField:'text',
			panelHeight:'120px',
			onChange: function(){
				gbzt();
			}
		}); 
		rwid=rwid+1;
	} 
}

var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

function doSubmit(){
	$("#M1").val($("#ID1").combobox("getText"));
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