<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加调查报告信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/ybcf/dcbg/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>
				<c:if test="${action eq 'updateSub'}">
                       <tr>
                            <td class="width-15 active"><label class="pull-right">编号：</label></td>
                            <td class="width-35" colspan="3"><input name="bgbh"
                                      class="easyui-textbox" editable="false"
                                      value="${ybe.bgbh }" style="width: 100%;height: 30px;" /></td>
                       </tr>
                </c:if>
				<tr>
					<td class="width-15 active"><label class="pull-right">案件名称：</label></td>
					<td class="width-35" colspan="3"><input id="casename" name="casename" class="easyui-textbox"  value="${ybe.casename }" 
                        style="width: 100%;height: 30px;" data-options="required:'true'"/></td>
				</tr>	
				<tr>
					<td class="width-15 active"><label class="pull-right">案由：</label></td>
					<td class="width-35"><input id="ayname" name="ayname" class="easyui-textbox"  value="${ybe.ayname }" 
                        style="width: 100%;height: 30px;" data-options="required:'true'"/></td>
					<%-- <td class="width-15 active"><label class="pull-right">调查时间：</label></td>
					<td class="width-35"><input name="researchdate"id="researchdate" class="easyui-datebox" value="${ybe.researchdate }" 
                        style="width: 100%;height: 30px;" /></td> --%>	
                    <td class="width-15 active"><label class="pull-right">承办机构：</label></td>
					<td class="width-35"><input id="cbjg" name="cbjg" class="easyui-textbox"  value="${ybe.cbjg }" 
                        style="width: 100%;height: 30px;" data-options=""/></td>
				</tr>
                <tr>
                     <td class="width-15 active" ><label class="pull-right">调查人：</label></td>
                     <td class="width-35"  ><input  id="enforcer1"name="enforcer1" class="easyui-combobox" value="${ybe.enforcer1 }" 
                     style="width: 100%;height: 30px;" 
                     data-options="panelHeight:'142px',required:'true',editable:true,valueField:'text',textField:'text',url:'${ctx}/aqzf/zfry/idlist'"/></td>
                      <td class="width-15 active" ><label class="pull-right">调查人：</label></td>
                     <td class="width-35"  ><input id="enforcer2"name="enforcer2" class="easyui-combobox" value="${ybe.enforcer2 }" 
                     style="width: 100%;height: 30px;"
                      data-options="panelHeight:'142px',editable:true,valueField:'text',textField:'text',url:'${ctx}/aqzf/zfry/idlist'"/></td>
                    </tr>
                    <tr>
                         <td class="width-15 active"><label class="pull-right">案件基本情况：</label></td>
                         <td class="width-35" colspan="3">
                    <input type="text" id="researchresult" name="researchresult" class="easyui-textbox" value="${ybe.researchresult}"
                        data-options="multiline:true , validType:'length[0,1000]'" style="width: 100%;height: 80px;" /></td>
                    </tr>
                    <tr>
						<td class="width-15 active"><label class="pull-right">查实违法行为：</label></td>
						<td class="width-35" colspan="3" id="wfxwall"></td>
					</tr>
                    <tr>
                         <td class="width-15 active"><label class="pull-right">违反条款：</label></td>
                         <td class="width-35" colspan="3"><input id="unlaw"name="unlaw" class="easyui-textbox" value="${ybe.unlaw }"  style="width: 100%;height: 50px;" 
                        data-options="multiline:true"/>
                    </td>
                    </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">违反条款详情：</label></td>
                    <td class="width-35" colspan="3">
                    <input id="unlawall" name="unlawall"  class="easyui-textbox" value="${ybe.unlawall }" style="width: 100%;height: 150px;" 
                    data-options="multiline:true"/></td>
                </tr>
		        <tr><td class="width-15" colspan="4" style="text-align:center"><label style="color: red;font-size: 20px;margin-top: 10px;">一、当事人基本信息</label></td> </tr>
                <c:if test="${cfdxlx == '2'}">
                    <td class="width-15 active"><label class="pull-right">当事人：</label></td>
                    <td class="width-35" >
                        <input name="cfryname" id="cfryname" readonly value="${ybe.cfryname }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:['length[0,25]']" />
                    </td>
                </c:if>
                <tr>
					<td class="width-15 active"><label class="pull-right">公司名称：</label></td>
					<td class="width-35" >
					<input name="qyname"id="qyname" value="${ybe.qyname}" class="easyui-textbox" style="width: 100%;height: 30px;" 
                         data-options="required:'true',readonly:'true'" />
					</td>
					
					<td class="width-15 active"><label class="pull-right">法定代表人/负责人：</label></td>
					<td class="width-35"  ><input  id="legalperson"name="legalperson" class="easyui-textbox" value="${ybe.legalperson }" 
                         style="width: 100%;height: 30px;"  /></td>
				</tr>
				<tr>
				<c:if test="${action eq 'createSub'}">
					<td class="width-15 active"><label class="pull-right">经济类型：</label></td>
					<td class="width-35" >
					<input id="economytype" class="easyui-combobox" name="economytype" value="${ybe.economytype }" style="width:100%;height:30px;"
					data-options="panelHeight:150,editable:false ,valueField: 'id',textField: 'text',url:'${ctx}/tcode/dict/jjlx'"/></td>
				</c:if>
				<c:if test="${action eq 'updateSub'}">
					<td class="width-15 active"><label class="pull-right">经济类型：</label></td>
					<td class="width-35" >
					<input id="economytype" class="easyui-combobox" name="economytype" value="${ybe.economytype }" style="width:100%;height:30px;"
					data-options="panelHeight:150,editable:false ,valueField: 'text',textField: 'text',url:'${ctx}/tcode/dict/jjlx'"/></td>
				</c:if>
					<td class="width-15 active"><label class="pull-right">成立时间：</label></td>
                    <td class="width-35"><input name="qyfounddate" id="qyfounddate" class="easyui-datebox" value="${ybe.qyfounddate}" 
                        style="width: 100%;height: 30px;" /></td>   
				</tr>
                    
                 <tr>
                     <td class="width-15 active"><label class="pull-right">企业地址：</label></td>
                     <td class="width-35" colspan="3"><input id="qyaddress" name="qyaddress" class="easyui-textbox" 
                     value="${ybe.qyaddress }" style="width: 100%;height: 30px;" data-options="validType:['length[0,100]']" /></td>
                 </tr>
                 <tr>
                     <td class="width-15 active"><label class="pull-right">经营范围：</label></td>
                     <td class="width-35" colspan="3"><input id="businessscope" name="businessscope" class="easyui-textbox" 
                     value="${ybe.businessscope }" style="width: 100%;height: 30px;" data-options="validType:['length[0,250]']" /></td>
                 </tr>
				
				<tr>
					<td class="width-15" colspan="4" style="text-align:center"><label style="color: red;font-size: 20px;margin-top: 10px;">二、调查情况</label></td> 
				</tr>  
				
				<tr>
				    <td style="text-align:center" colspan="4">
						<a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addNr()" title="添加证据"><i class="fa fa-plus"></i> 添加证据</a>
					</td>	
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">相关证据：</label></td>
					<td class="width-35" colspan="3">
						<table id="czwttable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7"  >
							<tr >
								<td style="text-align: center;width: 70%;  ">证据</td>
								<td style="text-align: center;width: 15%;">照片(点击查看原图)</td>
								<td style="text-align: center;">操作</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<%-- <tr>
					<td class="width-15 active"><label class="pull-right">（二）询问调查：</label></td>
					<td class="width-35" colspan="3"><input type="text" name="xwresearch" id="xwresearch" class="easyui-textbox" value="${ybe.xwresearch }"
                        data-options="multiline:true , validType:'length[0,1000]'" style="width: 100%;height: 80px;" /></td>
				</tr> --%>
                <%-- <tr><td class="width-15" colspan="4" style="text-align:center"><label>三、违法事实</label></td> </tr>  
				<tr>
					<td class="width-15 active"><label class="pull-right">违法事实：</label></td>
					<td class="width-35"colspan="3"><input type="text"id="illactevidence"name="illactevidence"class="easyui-textbox" 
                        value="${ybe.illactevidence}"data-options="multiline:true,validType:'length[0,1000]'"style="width:100%;height:80px;"/>
                       </td>
				</tr> --%>
                <tr><td class="width-15" colspan="4" style="text-align:center"><label style="color: red;font-size: 20px;margin-top: 10px;">三、处理建议</label></td> </tr>  
                <%-- <tr>
                    <td class="width-15 active"><label class="pull-right">承办人意见：</label></td>
                    <td class="width-35" colspan="3"><input type="text" name="opinion" class="easyui-textbox" value="${ybe.opinion}" 
                     data-options="multiline:true , validType:'length[0,500]'" style="width: 100%;height: 50px;" /></td>
                </tr> --%>
                <tr>
                    <td class="width-15 active"><label class="pull-right">处罚依据：</label></td>
                    <td class="width-35" colspan="3">
                    <input id="enlaw" name="enlaw"  class="easyui-textbox" value="${ybe.enlaw }" style="width: 100%;height: 50px;" 
                    data-options="multiline:true"/>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">处罚标准：</label></td>
                    <td class="width-35" colspan="3">
                    <input id="punishresult" name="punishresult"  class="easyui-textbox" value="${ybe.punishresult }" style="width: 100%;height: 150px;" 
                    data-options="multiline:true"/></td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">自由裁量：</label></td>
                    <td class="width-35" colspan="3">
                    <a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="zycl()" title="自由裁量"><i class="fa fa-arrow-circle-right"></i> 自由裁量</a>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">行政处罚：</label></td>
                    <td class="width-35" colspan="3">
                    <input id="xzcf"name="xzcf"class="easyui-textbox"value="${ybe.xzcf }"style="width: 100%;height: 50px;" 
                     data-options="multiline:true,validType:'length[0,250]'"/> 
                     </td>
                </tr>
			    
			    <%-- <tr>
					<td class="width-15 active"><label class="pull-right">法制审核意见：</label></td>
					<td class="width-35" colspan="3">
						<c:choose>
						<c:when test="${ybe.fzshyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="fzshyj" />同意
							<input type="radio" value="0" class="i-checks"  name="fzshyj" checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="fzshyj" checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="fzshyj" />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-15 active"><label class="pull-right">法制审核人：</label></td>
					<td class="width-35"><input type="text" id="fzshr" name="fzshr" class="easyui-combobox" value="${ybe.fzshr }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-15 active"><label class="pull-right">法制审核日期：</label></td>
					<td class="width-35"><input id="fzshsj" name="fzshsj" class="easyui-datebox" value="${ybe.fzshsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
			    
			    <tr>
					<td class="width-15 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-35" colspan="3">
						<c:choose>
						<c:when test="${ybe.shyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="shyj" />同意
							<input type="radio" value="0" class="i-checks"  name="shyj" checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="shyj" checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="shyj" />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-15 active"><label class="pull-right">审核人：</label></td>
					<td class="width-35"><input type="text" id="shr" name="shr" class="easyui-combobox" value="${ybe.shr }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-15 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-35"><input id="shsj" name="shsj" class="easyui-datebox" value="${ybe.shsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr> --%>
				<tr>
					<td class="width-15 active"><label class="pull-right">负责人意见：</label></td>
					<td class="width-35" colspan="3">
						<c:choose>
						<c:when test="${ybe.spyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="spyj" />同意
							<input type="radio" value="0" class="i-checks"  name="spyj" checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="spyj" checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="spyj" />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-15 active"><label class="pull-right">负责人：</label></td>
					<td class="width-35"><input type="text" name="spr" class="easyui-combobox" value="${ybe.spr }"  data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist'" style="height: 30px;width: 100%" /></td>
					<td class="width-15 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-35"><input id="spsj" name="spsj" class="easyui-datebox" value="${ybe.spsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
			    
				<tr>
				</tr>
				<!-- 隐藏域 -->
				  <input type='hidden' name="S1" value="<fmt:formatDate value="${ybe.s1}"/>"/>
				  <input type="hidden" name="ID" value="${ybe.ID}"/>
				  <input type="hidden" name="id1" value="${ybe.id1}"/>
				  <input type="hidden" name="id2" value="${ybe.id2}"/>
				  <input type="hidden" id="sccids" name="sccids" value="${ybe.sccids}" />
			</tbody>
		</table>
</form>
<script type="text/javascript">
/* $(function(){
	var date=new Date();
	$('#researchdate').datebox('setValue', format(new Date()));
});
function format(date,type) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};  */

/* $("#unlaw").textbox({
	onChange: function(){
		var unlaw= $("#unlaw").textbox("getText");
		var html="经调查认定,"+'${ybe.qyname}'+"存在如下违法事实:"+'${ybe.casename}'+"，该行为违反了"+unlaw+"的规定。";
		$("#illactevidence").textbox('setValue',html);
	}
});

$("#enforcer1").combobox({
	onSelect: function(){
		var en1= $("#enforcer1").combobox("getText");
		var en2= $("#enforcer2").combobox("getText");
		var date=$("#researchdate").datebox('getValue').replace("-","年").replace("-","月")+"日";
		var casename=$("#casename").textbox("getValue");
		$("#xwresearch").textbox('clear',true);
		$("#xwresearch").textbox('setValue',date+",执法人员"+en1+","+en2+"对"+'${ybe.qyname}'+"现场负责人（某某某） 进行询问调查，并制作询问笔录，笔录中（某某某）承认（某某某）"+casename+"这一事实");
	}
});
$("#enforcer2").combobox({
	onSelect: function(){
		var en1= $("#enforcer1").combobox("getText");
		var en2= $("#enforcer2").combobox("getText");
		var date=$("#researchdate").datebox('getValue').replace("-","年").replace("-","月")+"日";
		var casename=$("#casename").textbox("getValue");
		$("#xwresearch").textbox('clear',true);
		$("#xwresearch").textbox('setValue',date+",执法人员"+en1+","+en2+"对"+'${ybe.qyname}'+"现场负责人（某某某） 进行询问调查，并制作询问笔录，笔录中（某某某）承认（某某某）"+casename+"这一事实");
	}
});

 $('#researchdate').datebox({
	onSelect: function(date){
	var en1= $("#enforcer1").combobox("getText");
	var en2= $("#enforcer2").combobox("getText");
	var casename=$("#casename").textbox("getValue");
		var d=format(date).replace("-","年").replace("-","月")+"日";
		$("#xwresearch").textbox('clear',true);
		$("#xwresearch").textbox('setValue',d+",执法人员"+en1+","+en2+"对"+'${ybe.qyname}'+"现场负责人（某某某） 进行询问调查，并制作询问笔录，笔录中（某某某）承认（某某某）"+casename+"这一事实。");
		
	}
});   */

var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

//提交
function doSubmit(){
	if('${action}'== 'createSub'){
		$('#economytype').combobox('setValue',$('#economytype').combobox('getText'));
	}
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
	    		$("input[name='czwt']").each(function(index,item){
	    			$(this).val($(this).val().replace(/,/g,"，"));
 				});
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

var wtid=1;
function addNr() {
	layer.open({
	    type: 2,  
	    area: ['700px', '350px'],
	    title: '添加证据',
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/dcbg/choosenr" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){	
	    	//jQuery 1.4 新增支持：参数index可以为负数。如果index为负数，则将其视作length + index
	    	var iframeWin = layero.find('iframe')[0];
	    	var url=iframeWin.contentWindow.geturl();
	    	var czwt=iframeWin.contentWindow.getczwt();
	    	if(czwt==""||czwt==null||czwt==undefined){
	    		layer.msg("请添加证据", {
	    			time : 2000
	    		});
	    		return;
	    	}
	    	/* if(url==""||url==null||url==undefined){
	    		layer.msg("请上传照片", {
	    			time : 2000
	    		});
	    		return;
	    	} */
	    	var img="";
	    	var url2='<input type="hidden" name="czwturl" value="" />';
	    	if(url!=null&&url!=""){
	    		img=url.split("||");
	    		url2='<div id="divwtfj_'+ wtid + '" >' +
				'<a target="_blank" href="'+img[0]+'">'+
				'<img src=\''+img[0]+'\' style=\'width:50px; height: 50px\'>' +
				'<div class=\'info\'>' + img[1] + '</div>' +
				'<input type="hidden" name="czwturl" value="' + url + '" />' +
				'</a>';
	    	}
	    	var filename=iframeWin.contentWindow.getfilename();
			var $li = $(
					'<tr style="height:80px;" >'+
					'<td style="width:40%;" >'+
					'<textarea name="czwt" class="textarea" style="border:0px;width: 100%;height: 80px;">'+czwt+'</textarea>'+
					'</td>'+
					'<td>'+
					'<div style="text-align:center;" id="divwtfj_'+ wtid + '" >' +
					url2+
					'</div>'+
					'<input type="hidden" name="wtfjnum_' + wtid + '" value="0" />' +
					'</td>'+
					'<td style="text-align: center;">'+
					'<a class="btn btn-info btn-sm" style="margin-bottom:5px; width:100px;" data-toggle="tooltip" data-placement="left" onclick="updatepic('+wtid+')" title="修改图片"><i class="fa fa-file-text-o"></i> 修改图片</a>'+
					'<button class="btn btn-info btn-sm" style="width:100px;" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
					'</td>'+
					'</tr>'
		            );
	    	var  $list= $("#czwttable tr").eq(-1);
			$list.after( $li );
			layer.close(index);//关闭对话框。
			//id加1
			wtid = wtid + 1;
			parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '添加成功！',shade: 0 ,time: 2000 });
		},
	cancel: function(index){}
	}); 	
}

//上传图片
function updatepic(wtid) {
	layer.open({
	    type: 2,  
	    area: ['700px', '350px'],
	    title: '修改图片',
      maxmin: false, 
	    content: ctx + "/aqzf/jcjl/choose" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){	
	    	//jQuery 1.4 新增支持：参数index可以为负数。如果index为负数，则将其视作length + index
	    	var iframeWin = layero.find('iframe')[0];
	    	var url=iframeWin.contentWindow.geturl();
	    	/* if(url==""||url==null||url==undefined){
	    		layer.msg("请上传图片", {
	    			time : 2000
	    		});
	    		return;
	    	} */
	    	var url2='<input type="hidden" name="czwturl" value="" />';
	    	var img="";
	    	if(url!=null&&url!=""){
	    		img=url.split("||");
	    		url2='<a target="_blank" href="'+img[0]+'">'+
				'<img src=\''+img[0]+'\' style=\'width:50px; height: 50px\'>' +
				'<div class=\'info\'>' + img[1] + '</div>' +
				'</a>'+
				'<input type="hidden" name="czwturl" value="' + url + '" />' ;
	    	}
	    	var filename=iframeWin.contentWindow.getfilename();
			var $li = $(''+
					url2+
		            '');
			$('#divwtfj_'+wtid).html($li);
			layer.close(index);//关闭对话框。	
			parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '添加成功！',shade: 0 ,time: 2000 });
			},
	cancel: function(index){}
	}); 	
}

function removeTr(obj) {
	obj.remove();
}

function initData(){
    var wtListInit = '${czwt}';
	nrList=JSON.parse(wtListInit);  
	$.each(nrList, function(idx, obj) {
    	var url=obj.m2;
    	var czwt=obj.m1;
    	var img=url.split("||");
    	var pic="";
    	if(url==null||url==""){
    		pic='<input type="hidden" name="czwturl" value="' + url + '" />';
    	}else{
    		pic='<a target="_blank" href="'+img[0]+'">'+
			'<img src=\''+img[0]+'\' style=\'width:50px; height: 50px\'>' +
			'<div class=\'info\'>' + img[1] + '</div>' +
			'</a>'+
			'<input type="hidden" name="czwturl" value="' + url + '" />';
    	}
		var $trHtml = $(
			'<tr style="height:80px" >'+
			'<td style="width:40%" >'+
			'<textarea name="czwt" class="textarea" style="border:0px;width: 100%;height: 80px;" >' +czwt+'</textarea>'+
			'</td>'+
			'<td>'+
			'<div style="text-align:center;" id="divwtfj_'+ wtid + '" >' +
			pic+
			'</div>'+
			'<input type="hidden" name="wtfjnum_' + wtid + '" value="0" />' +
			'</td>'+
			'<td style="text-align: center;">'+
			'<a class="btn btn-info btn-sm" style="margin-bottom:5px; width:100px;" data-toggle="tooltip" data-placement="left" onclick="updatepic('+wtid+')" title="修改图片"><i class="fa fa-file-text-o"></i> 修改图片</a>'+
			'<button class="btn btn-info btn-sm" style="width:100px;" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
			'</td>'+
			'</tr>'
		 );	
		//id和数量加1
		wtid=wtid+1;
    	var  $list= $("#czwttable tr").eq(-1);
		$list.after( $trHtml );
	});
}

if('${action}' == 'updateSub'){
	initData();
}

function formatz(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
}

$(function(){
	if('${action}'== 'createSub'){
		if('${flag}'=='1'){
			initData();
		}
		$('#spsj').datebox('setValue', formatz(new Date()));	
	}
});

var wfxwlist='${wfxwlist}';
    wfxw=JSON.parse(wfxwlist);  
var  $ajqklist= $("#wfxwall");
if ('${action}' == 'createSub') {
	$.each(wfxw, function(idx, obj) {
	    var $tdHtml = $('<input type="checkbox" onclick="addnr()" name="wfxwid" style="margin-bottom:6px;" value="'+obj.ID+'||'+idx+'"/>'+obj.m1+'</br>');	
		$ajqklist.append( $tdHtml );
	});
}

if ('${action}' == 'updateSub'){
	var allwfxw = ','+'${ybe.sccids}'+',';
	$.each(wfxw, function(idx, obj) {
		if(allwfxw.indexOf(','+obj.ID+',')!=-1){
			var $tdHtml = $('<input type="checkbox" checked="checked" onclick="addnr()" name="wfxwid" style="margin-bottom:6px;" value="'+obj.ID+'||'+idx+'"/>'+obj.m1+'</br>');	
			$ajqklist.append( $tdHtml );
		}else{
			var $tdHtml = $('<input type="checkbox" onclick="addnr()" name="wfxwid" style="margin-bottom:6px;" value="'+obj.ID+'||'+idx+'"/>'+obj.m1+'</br>');	
			$ajqklist.append( $tdHtml );
		}
	});
}

function addnr() {
	var sccids = "";
	var unlaw = "";//违反条款
	var unlawall = "";//违反条款详情
	var enlaw = "";//处罚依据
	var punishresult = "";//处罚标准
	var zyclbz = "";//自由裁量标准
	$('input[name="wfxwid"]:checked').each(function(i){
	   if(0==i){
	       sccids = $(this).val().split("||")[0];
	       if(wfxw[$(this).val().split("||")[1]].m2 != null && wfxw[$(this).val().split("||")[1]].m2 != '' && wfxw[$(this).val().split("||")[1]].m2 != undefined){
				unlaw += wfxw[$(this).val().split("||")[1]].m2;
	       }
	       if(wfxw[$(this).val().split("||")[1]].m3 != null && wfxw[$(this).val().split("||")[1]].m3 != '' && wfxw[$(this).val().split("||")[1]].m3 != undefined){
				unlawall += wfxw[$(this).val().split("||")[1]].m3;
	       }
	       if(wfxw[$(this).val().split("||")[1]].m4 != null && wfxw[$(this).val().split("||")[1]].m4 != '' && wfxw[$(this).val().split("||")[1]].m4 != undefined){
				enlaw += wfxw[$(this).val().split("||")[1]].m4;
	       }
	       if(wfxw[$(this).val().split("||")[1]].m5 != null && wfxw[$(this).val().split("||")[1]].m5 != '' && wfxw[$(this).val().split("||")[1]].m5 != undefined){
				punishresult += wfxw[$(this).val().split("||")[1]].m5;
	       }
	       if(wfxw[$(this).val().split("||")[1]].m7 != null && wfxw[$(this).val().split("||")[1]].m7 != '' && wfxw[$(this).val().split("||")[1]].m7 != undefined){
				zyclbz += wfxw[$(this).val().split("||")[1]].m7;
	       }
	   }else{
	       sccids += (","+$(this).val().split("||")[0]);
	       if(wfxw[$(this).val().split("||")[1]].m2 != null && wfxw[$(this).val().split("||")[1]].m2 != '' && wfxw[$(this).val().split("||")[1]].m2 != undefined){
	       		unlaw += "，" + wfxw[$(this).val().split("||")[1]].m2;
	       }
	       if(wfxw[$(this).val().split("||")[1]].m3 != null && wfxw[$(this).val().split("||")[1]].m3 != '' && wfxw[$(this).val().split("||")[1]].m3 != undefined){
				unlawall += wfxw[$(this).val().split("||")[1]].m3;
	       }
	       if(wfxw[$(this).val().split("||")[1]].m4 != null && wfxw[$(this).val().split("||")[1]].m4 != '' && wfxw[$(this).val().split("||")[1]].m4 != undefined){
				enlaw += "，" + wfxw[$(this).val().split("||")[1]].m4;
	       }
	       if(wfxw[$(this).val().split("||")[1]].m5 != null && wfxw[$(this).val().split("||")[1]].m5 != '' && wfxw[$(this).val().split("||")[1]].m5 != undefined){
				punishresult += wfxw[$(this).val().split("||")[1]].m5;
	       }
	       if(wfxw[$(this).val().split("||")[1]].m7 != null && wfxw[$(this).val().split("||")[1]].m7 != '' && wfxw[$(this).val().split("||")[1]].m7 != undefined){
			   var c = wfxw[$(this).val().split("||")[1]].m7;
	    	   zyclbz += "，"+ c.substring(c.indexOf("》")+1,c.length);
	       }
	   }
	});
	$("#unlaw").textbox("setValue",unlaw);
	$("#unlawall").textbox("setValue",unlawall);
	$("#enlaw").textbox("setValue",enlaw+"，参照"+zyclbz);
	$("#punishresult").textbox("setValue",punishresult);
	$("#sccids").val(sccids);
}

function zycl() {
	var sids = $("#sccids").val();
	if(sids==null||sids==''||sids==undefined){
		sids = "flag";
	}
	layer.open({
	    type: 2,  
	    area: ['80%', '100%'],
	    title: '自由裁量',
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/dcbg/zycl/"+sids ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){	
	    	var iframeWin = layero.find('iframe')[0];
	    	var zyclxzcf=iframeWin.contentWindow.getzyclxzcf();
	    	$("#xzcf").textbox("setValue",$("#xzcf").textbox("getValue")+zyclxzcf);
	    	layer.close(index);//关闭对话框。
		},
		cancel: function(index){}
	}); 	
}
</script>
</body>
</html>