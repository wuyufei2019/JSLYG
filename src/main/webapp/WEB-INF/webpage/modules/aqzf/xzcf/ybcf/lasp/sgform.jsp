<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加事故立案审批表</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/ybcf/lasp/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<%-- <tr>
					<td class="width-20 active"><label class="pull-right">事故调查主题：</label></td>
					<td class="width-30" colspan="3"><input id="sgtzid"  name="sgtzid"  class="easyui-combobox" value="${yle.sgtzid }" style="width: 100%;height: 30px;" 
					data-options="required:'true',panelHeight:'150px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/xzcf/ybcf/sgxwtz/dcztjson'" /></td>
				</tr> --%>
				<tr>
					<td class="width-20 active"><label class="pull-right">案由：</label></td>
                 	<td class="width-30" colspan="3">
						<input id="ayname"  name="ayname"  class="easyui-combobox" value="${yle.ayname }" style="width: 100%;height: 30px;" data-options="validType:['length[0,250]'],multiple:true,required:'true',panelHeight:'150px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/dict/json/ay'" />
				   </td>					
				</tr>
				<tr>
					<td class="width-20 active" ><label class="pull-right">案件来源:</label></td>
					<td class="width-30"  >
					<input id="casesource"  name="casesource"  class="easyui-combobox" value="${yle.casesource }" style="width: 100%;height: 30px;" data-options="required:'true',panelHeight:'150px',editable:false ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/dict/json/ajly'" />
					</td>
					<td class="width-20 active"><label class="pull-right">立案时间：</label></td>
					<td class="width-30"><input name="filldate" id="filldate" class="easyui-datebox" value="${yle.filldate }" style="width: 100%;height: 30px;" data-options="required:'true',editable:false"/></td>	
				</tr>
		        <tr>
					<td class="width-20 active"><label class="pull-right">案件名称：</label></td>
					<td class="width-80" colspan="3"><input id="casename" name="casename" class="easyui-textbox"  value="${yle.casename }" style="width: 100%;height: 30px;" data-options="required:'true',multiline:true,validType:['length[0,100]'] "/></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">处罚对象类型：</label></td>
					<td class="width-30" >
                        <input class="easyui-validatebox" type="radio" onclick="dxgg1()" checked value="1" style="margin-bottom: 6px;" name="cfdxlx" data-options="validType:'requireRadio[\'input[name=cfdxlx]\', \'\']'"/>公司
                        <input class="easyui-validatebox" type="radio" onclick="dxgg2()" value="2" style="margin-bottom: 6px;margin-left: 20px;" name="cfdxlx" />个人
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">公司名称：</label></td>
					<td class="width-30" >
					<input name="id1"id="id1" value="${yle.id1 }"   class="easyui-combobox" style="width: 100%;height: 30px;" data-options="readonly:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson' " />
					</td>
					
					<td class="width-20 active"><label class="pull-right">电话：</label></td>
					<td class="width-30" ><input id="contact" name="contact" class="easyui-textbox" value="${yle.contact }" style="width: 100%;height: 30px;"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">法定代表人/负责人：</label></td>
					<td class="width-30"  ><input  id="legalperson"name="legalperson" class="easyui-textbox" value="${yle.legalperson }" style="width: 100%;height: 30px;"  /></td>
					<td class="width-20 active"><label class="pull-right">邮编：</label></td>
					<td class="width-30">
					<input name="ybcode" type="text" id="ybcode" value="${yle.ybcode }"  class="easyui-textbox" style="width: 100%;height: 30px;"  data-options="validType:'ZIP'"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">地址：</label></td>
					<td class="width-30" colspan="3"><input id="dsaddress" name="dsaddress" class="easyui-textbox" value="${yle.dsaddress }" style="width: 100%;height: 30px;" data-options="validType:['length[0,100]']" /></td>
				</tr>
                <tr name="person" >
                    <td class="width-20 active"><label class="pull-right">当事人：</label></td>
                    <td class="width-30" >
                        <input name="cfryname" id="cfryname" value="${yle.cfryname }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:['length[0,25]'],events:{blur:function(){ opinionchange();ajjbqkchange(); }}" />
                    </td>
                    <td class="width-20 active"><label class="pull-right"> 电话： </label></td>
                    <td class="width-30"><input id="cfryphone" name="cfryphone"
                                                class="easyui-textbox" value="${yle.cfryphone }"
                                                style="width: 100%;height: 30px;" validtype="mobileAndTel" /></td>
                </tr>
                <tr name="person" >
                    <td class="width-20 active"><label class="pull-right"> 年龄： </label></td>
                    <td class="width-30"><input id="cfryage" name="cfryage"
                                                class="easyui-textbox" value="${yle.cfryage }"
                                                style="width: 100%;height: 30px;"
                                                data-options="validType:['length[0,2]','number']" /></td>
                    <td class="width-20 active"><label class="pull-right"> 性别： </label></td>
                    <td class="width-30"><input id="cfrysex" name="cfrysex"
                                                class="easyui-combobox" value="${yle.cfrysex }"
                                                style="width:100%;height: 30px;"
                                                data-options="panelHeight:'auto',editable:false,data: [{value:'男',text:'男'},{value:'女',text:'女'}]" />
                    </td>
                </tr>
				<tr>
				    <td align="center" colspan="4">
						<a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addRw()" title="添加案件记录"><i class="fa fa-plus"></i> 添加案件记录</a>
					</td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">案件记录：</label></td>
					<td class="width-30" colspan="3">
						<table id="rwtable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
							<tr >
								<td style="text-align: center;width: 84%;">记录情况</td>
								<td style="text-align: center;">操作</td>
							</tr>
						</table>	
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">发现日期：</label></td>
					<td class="width-30"><input id="fxsj" name="fxsj" class="easyui-datebox" value="${yle.fxsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">案件基本情况：</label></td>
					<td class="width-30" colspan="3"><input type="text" id="ajjbqk" name="ajjbqk" class="easyui-textbox" value="${yle.ajjbqk }" data-options="multiline:true , validType:'length[0,1000]'" style="width: 100%;height: 80px;" /></td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">承办人意见：</label></td>
					<td class="width-30" colspan="3"><input type="text" id="opinion" name="opinion" class="easyui-textbox" value="${yle.opinion }"  data-options="multiline:true , validType:'length[0,1000]'" style="width: 100%;height: 50px;" /></td>
				</tr>
				<tr>
					<td class="width-20 active" rowspan="2"><label class="pull-right">承办人：</label></td>
					<td class="width-30"  ><input  id="enforcer1"name="enforcer1" class="easyui-combobox" value="${yle.enforcer1 }" style="width: 100%;height: 30px;" data-options="panelHeight:'142px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/zfry/idlist'" /></td>
					<td class="width-20 active" rowspan="2"><label class="pull-right">证号：</label></td>
					<td class="width-30">
					<input name="identity1" id="identity1" value="${yle.identity1 }"  class="easyui-textbox" style="width: 100%;height: 30px;" /></td>
				</tr>
				<tr>
					<td class="width-20"  ><input  id="enforcer2"name="enforcer2" class="easyui-combobox" value="${yle.enforcer2 }" style="width: 100%;height: 30px;" data-options="panelHeight:'142px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/zfry/idlist'" /></td>
					<td class="width-30">
					<input name="identity2" id="identity2" value="${yle.identity2 }"   class="easyui-textbox" style="width: 100%;height: 30px;" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">承办日期：</label></td>
					<td class="width-30"><input id="cbsj" name="cbsj" class="easyui-datebox" value="${yle.cbsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">法制审核意见：</label></td>
					<td class="width-30" colspan="3">
						<c:choose>
						<c:when test="${yle.fzshyj=='0'}">
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
					<td class="width-20 active"><label class="pull-right">法制审核人：</label></td>
					<td class="width-30"><input type="text" id="fzshr" name="fzshr" class="easyui-combobox" value="${yle.fzshr }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">法制审核日期：</label></td>
					<td class="width-30"><input id="fzshsj" name="fzshsj" class="easyui-datebox" value="${yle.fzshsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-30" colspan="3">
						<c:choose>
						<c:when test="${yle.shyj=='0'}">
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
					<td class="width-20 active"><label class="pull-right">审核人：</label></td>
					<td class="width-30"><input type="text" id="shr" name="shr" class="easyui-combobox" value="${yle.shr }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-30"><input id="shsj" name="shsj" class="easyui-datebox" value="${yle.shsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">审批意见：</label></td>
					<td class="width-30" colspan="3">
						<c:choose>
						<c:when test="${yle.spyj=='0'}">
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
					<td class="width-20 active"><label class="pull-right">审批人：</label></td>
					<td class="width-30"><input type="text" name="spr" class="easyui-combobox" value="${yle.spr }"  data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist'" style="height: 30px;width: 100%" /></td>
					<td class="width-20 active"><label class="pull-right">审批日期：</label></td>
					<td class="width-30"><input id="spsj" name="spsj" class="easyui-datebox" value="${yle.spsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				
				 <tr>
					<td class="width-20 active" ><label class="pull-right">审批表附件：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_url">
					    <div id="urlfileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				<!-- 隐藏域 -->
				<input type="hidden" name="ID2" value="${yle.ID2}" />	
				<input type="hidden" name="dsperson" value="${yle.dsperson}" />
				<input type="hidden" name="sgtzid" value="${yle.sgtzid}" />
                <input type="hidden" name="zycllx" value="${yle.zycllx}" />
				<%-- <input type="hidden" id="casecondition" name="casecondition" value="${yle.casecondition}" /> --%>
				<%-- <c:if test="${not empty yle.ID}">	
				  <input type='hidden' name="S1" value="<fmt:formatDate value="${yle.s1}"/>"/>
				  <input type="hidden" name="S3" value="${yle.s3}" />
				  <input type="hidden" name="ID" value="${yle.ID}" />
				  <input type="hidden" name="gzflag" value="${yle.gzflag}" />
				  <input type="hidden" name="tzflag" value="${yle.tzflag}" />
				  <input type="hidden" name="cfjdflag" value="${yle.cfjdflag}" />
                  <input type="hidden" name="xwflag" value="${yle.xwflag}" />
                  <input type="hidden" name="cbflag" value="${yle.cfjdflag}" />
                  <input type="hidden" name="dcflag" value="${yle.dcflag}" />
                  <input type="hidden" name="cgflag" value="${yle.cgflag}" />
                  <input type="hidden" name="qzflag" value="${yle.qzflag}" />
                  <input type="hidden" name="jaflag" value="${yle.jaflag}" />
                  <input type="hidden" name="tempflag" value="${yle.tempflag}" />
                  <input type="hidden" name="sdhznumber" value="${yle.sdhznumber}" />
                  <input type="hidden" name="sbflag" value="${yle.sbflag}" />
                  <input type="hidden" name="tlflag" value="${yle.tlflag}" />
                  <input type="hidden" name="fkspflag" value="${yle.fkspflag}" />
                  <input type="hidden" name="cxflag" value="${yle.cxflag}" />
                  <input type="hidden" name="userid" value="${yle.userid}" />
                  <input type="hidden" name="casecondition" value="${yle.casecondition}" />
                </c:if> --%>
             </tbody>
		</table>
	
</form>
<script type="text/javascript">
    var dx = '1';
    function dxgg1() {
        dx = '1';
        $("[name='person']").hide();
        $('#cfryname').textbox("textbox").validatebox({required:false});
        opinionchange();
        ajjbqkchange();
    }
    function dxgg2() {
        dx = '2';
        $("[name='person']").show();
        $('#cfryname').textbox("textbox").validatebox({required:true});
        opinionchange();
        ajjbqkchange();
    }
    $(function() {
        $("[name='person']").hide();
        if('${action}'=='updateSub2') {
            $("[name='person']").show();
            dx = '2';
        }
    });
    var version = '${yle.zycllx}';
    var wfxw;
    if(version == '2'){
        wfxw = 'wfxwtwo';
    }else {
        wfxw = 'wfxw';
    }

$.extend($.fn.validatebox.defaults.rules, {
    requireRadio: {  
        validator: function(value, param){  
        	var input = $(param[0]);
            input.off('.requireRadio').on('click.requireRadio',function(){
                $(this).focus();
            });
            return $(param[0] + ':checked').val() != undefined;
        },  
        message: '请选择！'  
        //message: 'Please choose option for {1}.'  
    }  
});

$(function(){
	if('${action}'=='createsg'){
		var date=new Date();
		$('#filldate').datebox('setValue', format(new Date()));	
		//$('#casesource').combobox('setValue', '事故');
		$('#cbsj').datebox('setValue', format(new Date()));	
		$('#fzshsj').datebox('setValue', format(new Date()));	
		$('#shsj').datebox('setValue', format(new Date()));	
		$('#spsj').datebox('setValue', format(new Date()));	
	}
	
	$("input",$("#id1").next("span")).blur(function(){
		var he = /^[0-9]+.?[0-9]*$/;  
		var qyflag = $("#id1").combobox("getValue");
		if(!he.test(qyflag) && qyflag != ''){
			$("#id1").combobox("setValue","");
		}
	}) 
});

function format(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};

$list = $('#urlfileList');//附件上传
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

//附件
var fileuploader = WebUploader.create({

    auto: true,

    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

    server: '${ctx}/kindeditor/upload?dir=file',
    		
    pick: {
    	id:'#filePicker',
    	multiple : false,
    },
    duplicate :true  
});

fileuploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});

// 负责预览图的销毁
function removeFile(fileid) {
	$("#"+fileid).remove();
	$("#input_"+fileid).remove();
};

// 文件上传成功 
fileuploader.on( 'uploadSuccess', function( file ,yle) {
$.jBox.closeTip();
	if(yle.error==0){
		var $li = $(
	            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
	            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+yle.url+"')\">"+yle.fileName+"</a>"+
	            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	            "</div>"
	            );

	    
	    $list.append( $li );
 
		var newurl=yle.url+"||"+yle.fileName;
		
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="url" value="'+newurl+'"/>');
		
		$('#uploader_ws_url').append( $input );
	}else{
		layer.msg(yle.message,{time: 3000});
	}
});

var rwid=1;
function addRw() {
	var $list= $("#rwtable tr").eq(-1);
	var $li = $(
				'<tr style="height:130px" >'+
				'<td style="width:84%" align="center"><b style="float:left;color:red;">*</b><font style="float:left;">问题描述：</font><br/>'+
				'<input data-options="" type="text" id="wtms_'+rwid+'" name="wtms" class="easyui-textbox" value=""  style="height: 50px;width: 99%;" /><br/><font style="float:left;margin-left: 5px;">涉嫌违法行为：</font><br/>'+
				'<input data-options="" type="text" id="aj_'+rwid+'" name="wfxw" class="easyui-textbox" value=""  style="height: 30px;width: 99%;" />'+
				'</td>'+
				'<td align="center">'+
				'<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
				'</td>'+
				'</tr>'
	            );
	$list.after( $li );
	
	$('#wtms_'+rwid).textbox({  
		required:true,
		editable:true,
		multiline:true,
		onChange: function(){
			ajjbqkchange();
		}
	}); 
	
	$('#aj_'+rwid).combobox({  
		required:true,
		editable:true,
		url:'${ctx}/aqzf/'+wfxw+'/idlist',
		valueField:'id',
		textField:'text',
		panelHeight:'120px',
		onChange: function(){
			opinionchange();
		}
	}); 
	
	rwid=rwid+1;
}

//将发现时间转化成年月日格式
function fxsjformat(){
	var fxsj = $("#fxsj").datebox('getValue');
	if(fxsj!=null && fxsj!='' && fxsj!= undefined){
		var date = new Date(fxsj);
		return date.getFullYear()+'年'+(date.getMonth() + 1)+'月'+date.getDate()+'日';
	}else{
		return '';
	}
}

//发现时间改变
$('#fxsj').datebox({
    onChange: function(){
    	ajjbqkchange();
    }
});

//下拉关联信息
$("#enforcer1").combobox({
	onSelect: function(){
			var id=$("#enforcer1").combobox('getValue');
			$.ajax({
			type:'get',
			url:ctx+"/aqzf/zfry/findidcard/"+id,
			success: function(data){
				$("#identity1").textbox('setValue',data);
			}
		});
	}
});
$("#enforcer2").combobox({
	onSelect: function(){
			var id=$("#enforcer2").combobox('getValue');
			$.ajax({
			type:'get',
			url:ctx+"/aqzf/zfry/findidcard/"+id,
			success: function(data){
				$("#identity2").textbox('setValue',data);
			}
		});
	}
});

//验证下拉框的值是否有效
function checkcombobox(id){
	var options = $("#"+id).combobox('options');  
 	var data = $("#"+id).combobox('getData');		/* 下拉框所有选项 */  
 	var value = $("#"+id).combobox('getValue');	/* 用户输入的值 */  
 	var b = false;		/* 标识是否在下拉列表中找到了用户输入的字符 */  
 	for (var i = 0; i < data.length; i++) {  
    	if (data[i][options.valueField] == value) {  
        	b=true;  
       	 	break;  
    	}  
	}
 	return b;
}

//提交
function doSubmit(){
	$("#enforcer1").combobox("setValue",$("#enforcer1").combobox("getText"));
	$("#enforcer2").combobox("setValue",$("#enforcer2").combobox("getText"));
	
	var value1 = $("#id1").combobox('getValue');
	if(value1 != ''){
		if(!checkcombobox('id1')){
			layer.open({title: '提示',offset: 'auto',content: '所选公司不存在，请在“自定义设置-基本信息库”添加！',shade: 0 ,time: 3000 });
			return;
		}
	}else{
		layer.open({title: '提示',offset: 'auto',content: '请选择公司名称！',shade: 0 ,time: 3000 });
		return;
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
	    		$("input[name='wtms']").each(function(index,item){
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

//删除指定行的任务
function removeTr(obj) {
	obj.remove();
	opinionchange();
	ajjbqkchange();
}

function ajjbqkchange(){
	var a='';
	var els =document.getElementsByName("wtms");
	var j = els.length;
	if(j == 1){
		a = els[0].value+"。";
	}else if(j > 1){
		for (var i = 0; i < j; i++){
			if(i==j-1){
				a += (i+1)+"."+els[i].value+"。";
			}else{
				a += (i+1)+"."+els[i].value+"；";
			}
		}
	}
    $("#ajjbqk").textbox('setValue',fxsjformat()+"，本机关执法人员对"+$("#id1").combobox("getText")+"执法检查过程中发现："+a);
}

function opinionchange(){
	var wfids='';
	var els =document.getElementsByName("wfxw");
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
			url:ctx+"/aqzf/"+wfxw+"/wftk/"+wfids,
			success: function(data){
                if(dx == '2') {
                    $("#opinion").textbox('setValue',$('#cfryname').textbox("getText")+"的上述行为，涉嫌违反"+data+"有关规定，建议立案调查。");
                }else {
                    $("#opinion").textbox('setValue',$("#id1").combobox("getText")+"的上述行为，涉嫌违反"+data+"有关规定，建议立案调查。");
                }
			}
		});
	}else{
		$("#opinion").textbox('setValue',"");
	} 
}
</script>
</body>
</html>