<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>自由裁量</title>
	<meta name="decorator" content="default"/>
</head>
<body >
	<div>
        <div style="text-align:center;margin: 20px;">
            <h1 style="color: red;">自由裁量计算</h1>
        </div>
		<form id="inputForm" class="form-horizontal">
			<table
				class="table table-bordered  dataTables-example dataTable no-footer"
				style="margin:0 auto;min-width:900px;width:1000px;">
				<tbody id="zycl1">
					<tr>
						<td class="width-20 active"><label class="pull-right">违法行为：</label></td>
						<td class="width-80"><input id="wfxwid" class="easyui-combobox" value=""  style="width:100%;height:30px;"
                                                    data-options="required:'true',editable:true ,valueField:'id',textField:'text',panelHeight:'150px',url:'${ctx}/aqzf/wfxw/idlist'"/></td>
					</tr>
					<tr>
						<td class="width-20 active"><label class="pull-right">处罚档次：</label></td>
						<td class="width-80" id="cfdc"></td>
					</tr>
					<tr>
						<td class="width-20 active"><label class="pull-right">裁量幅度：</label></td>
						<td class="width-80" id="clfd" style="padding-top: 10px;padding-bottom: 10px;"></td>
					</tr>
					<tr>
						<td class="width-20 active"><label class="pull-right">事前处罚：</label></td>
						<td class="width-80" style="padding-top: 10px;padding-bottom: 10px;">
						<input type="checkbox" name="sqcf" style="margin-bottom: 5px;" value="1"/>对违法行为认识到位<br/>
						<input type="checkbox" name="sqcf" style="margin-bottom: 5px;" value="2"/>立即采取措施纠正<br/>
						<input type="checkbox" name="sqcf" style="margin-bottom: 5px;" value="3"/>通过安全生产标准化考评<br/>
						<input type="checkbox" name="sqcf" style="margin-bottom: 5px;" value="4"/>积极组织或支持安全生产公益活动<br/>
						</td>
					</tr>
					<tr>
						<td class="width-20 active"><label class="pull-right">事故处罚：</label></td>
						<td class="width-80" style="padding-top: 10px;padding-bottom: 10px;">
						<input type="checkbox" name="sgcf" style="margin-bottom: 5px;" value="1"/>对事故调查积极配合<br/>
						<input type="checkbox" name="sgcf" style="margin-bottom: 5px;" value="2"/>对事故善后处理妥善积极<br/>
						<input type="checkbox" name="sgcf" style="margin-bottom: 5px;" value="3"/>对事故隐患按照‘四不放过’原则积极采取措施整治到位<br/>
						<input type="checkbox" name="sgcf" style="margin-bottom: 5px;" value="4"/>积极组织或支持安全生产公益活动<br/>
						</td>
					</tr>
					<tr>
						<td class="width-20 active"><label class="pull-right">从轻处罚：</label></td>
						<td class="width-80" style="padding-top: 10px;padding-bottom: 10px;">
						<input type="checkbox" name="cqcf" style="margin-bottom: 5px;" value="1||0.1"/>已满 14 周岁不满 18 周岁的公民实施安全生产违法行为<br/>
						<input type="checkbox" name="cqcf" style="margin-bottom: 5px;" value="2||0.4"/>主动消除或者减轻安全生产违法行为危害后果<br/>
						<input type="checkbox" name="cqcf" style="margin-bottom: 5px;" value="3||0.2"/>受他人胁迫实施安全生产违法行为<br/>
						<input type="checkbox" name="cqcf" style="margin-bottom: 5px;" value="4||0.2"/>配合安全监管执法机关查处安全生产违法行为，有立功表现<br/>
						<input type="checkbox" name="cqcf" style="margin-bottom: 5px;" value="5||0.1"/>主动投案，向安全监管执法机关如实交待自己的违法行为<br/>
						<input type="checkbox" name="cqcf" style="margin-bottom: 5px;" value="6||0.1"/>具有法律、行政法规规定的其他从轻处罚情形<br/>
						</td>
					</tr>
					<tr>
						<td class="width-20 active"><label class="pull-right">从重处罚：</label></td>
						<td class="width-80" style="padding-top: 10px;padding-bottom: 10px;">
						<input type="checkbox" name="czcf" style="margin-bottom: 5px;" value="1||0.3"/>一年内因同一种安全生产违法行为受到两次以上行政处罚<br/>
						<input type="checkbox" name="czcf" style="margin-bottom: 5px;" value="2||0.2"/>在处置突发事件期间实施安全生产违法行为<br/>
						<input type="checkbox" name="czcf" style="margin-bottom: 5px;" value="3||0.2"/>违法行为情节恶劣，造成人身死亡（重伤、急性工业中毒）或者严重社会影响<br/>
						<input type="checkbox" name="czcf" style="margin-bottom: 5px;" value="4||0.3"/>故意实施违法行为<br/>
						<input type="checkbox" name="czcf" style="margin-bottom: 5px;" value="5||0.2"/>对举报人、证人打击报复<br/>
						<input type="checkbox" name="czcf" style="margin-bottom: 5px;" value="6||0.2"/>未妥善处理事故善后<br/>
						<input type="checkbox" name="czcf" style="margin-bottom: 5px;" value="7||0.3"/>对事故发生负有主要责任<br/>
						<input type="checkbox" name="czcf" style="margin-bottom: 5px;" value="8||0.2"/>对事故发生负有重要责任<br/>
						<input type="checkbox" name="czcf" style="margin-bottom: 5px;" value="9||0.2"/>具有法律、行政法规规定的其他从重处罚情形<br/>
						</td>
					</tr>
                    <tr>
                        <td colspan="2">
                            <div style="text-align:center;margin: 20px;" id="jgxs"></div>
                            <div style="text-align:center;margin: 20px;">
                                <a id="zycljs" class="btn btn-primary"  onclick="jycljs()" style="width:120px">处罚计算</a>
                            </div>
                        </td>
                    </tr>
				</tbody>
			</table>
		</form>
        <form id="inputForm2" class="form-horizontal">
            <table
                    class="table table-bordered  dataTables-example dataTable no-footer"
                    style="margin:0 auto;min-width:900px;width:1000px;">
                <tbody id="zycl2">
                    <tr>
                        <td class="width-20 active"><label class="pull-right">违法行为：</label></td>
                        <td class="width-80"><input id="wfxwid2" class="easyui-combobox" value=""  style="width:100%;height:30px;"
                                                    data-options="required:'true',editable:true ,valueField:'id',textField:'text',panelHeight:'150px',url:'${ctx}/aqzf/wfxwtwo/idlist'" /></td>
                    </tr>
                    <tr>
                        <td class="width-20 active"><label class="pull-right">处罚档次：</label></td>
                        <td class="width-80" id="cfdc2"></td>
                    </tr>
                    <tr>
                        <td class="width-20 active"><label class="pull-right">裁量幅度：</label></td>
                        <td class="width-80" id="clfd2" style="padding-top: 10px;padding-bottom: 10px;"></td>
                    </tr>
                    <tr>
                        <td class="width-20 active"><label class="pull-right">事前处罚：</label></td>
                        <td class="width-80" style="padding-top: 10px;padding-bottom: 10px;">
                            <input type="checkbox" name="sqcf2" style="margin-bottom: 5px;" value="1"/>对违法行为认识到位<br/>
                            <input type="checkbox" name="sqcf2" style="margin-bottom: 5px;" value="2"/>立即采取措施纠正<br/>
                            <input type="checkbox" name="sqcf2" style="margin-bottom: 5px;" value="3"/>通过安全生产标准化考评<br/>
                            <input type="checkbox" name="sqcf2" style="margin-bottom: 5px;" value="4"/>积极组织或支持安全生产公益活动<br/>
                            <input type="checkbox" name="sqcf2" style="margin-bottom: 5px;" value="5"/>企业安全风险辨识管控机制建设是否到位<br/>
                            <input type="checkbox" name="sqcf2" style="margin-bottom: 5px;" value="6"/>特殊岗位机械化换人、自动化减人是否到位<br/>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-20 active"><label class="pull-right">事故处罚：</label></td>
                        <td class="width-80" style="padding-top: 10px;padding-bottom: 10px;">
                            <input type="checkbox" name="sgcf2" style="margin-bottom: 5px;" value="1"/>对事故调查积极配合<br/>
                            <input type="checkbox" name="sgcf2" style="margin-bottom: 5px;" value="2"/>对事故善后处理妥善积极<br/>
                            <input type="checkbox" name="sgcf2" style="margin-bottom: 5px;" value="3"/>对事故隐患按照‘四不放过’原则积极采取措施整治到位<br/>
                            <input type="checkbox" name="sgcf2" style="margin-bottom: 5px;" value="4"/>积极组织或支持安全生产公益活动<br/>
                            <input type="checkbox" name="sgcf2" style="margin-bottom: 5px;" value="5"/>企业安全风险辨识管控机制建设是否到位<br/>
                            <input type="checkbox" name="sgcf2" style="margin-bottom: 5px;" value="6"/>特殊岗位机械化换人、自动化减人是否到位<br/>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-20 active"><label class="pull-right">分母：</label></td>
                        <td class="width-30">
                            <input id="cffm2" class="easyui-numberbox" value="" style="width:80px;height:30px;"
                                   data-options="required:'true',editable:true,min:0,precision:0"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-20 active"><label class="pull-right">从重处罚：</label></td>
                        <td class="width-80" style="padding-top: 10px;padding-bottom: 10px;">
                            <input type="checkbox" name="czcf2" style="margin-bottom: 5px;" value="1||0.3"/>一年内因同一种安全生产违法行为受到两次以上行政处罚<br/>
                            <input type="checkbox" name="czcf2" style="margin-bottom: 5px;" value="2||0.2"/>在处置突发事件期间实施安全生产违法行为<br/>
                            <input type="checkbox" name="czcf2" style="margin-bottom: 5px;" value="3||0.2"/>违法行为情节恶劣，造成人身死亡（重伤、急性工业中毒）或者严重社会影响<br/>
                            <input type="checkbox" name="czcf2" style="margin-bottom: 5px;" value="4||0.3"/>故意实施违法行为<br/>
                            <input type="checkbox" name="czcf2" style="margin-bottom: 5px;" value="5||0.2"/>对举报人、证人打击报复<br/>
                            <input type="checkbox" name="czcf2" style="margin-bottom: 5px;" value="6||0.2"/>未妥善处理事故善后<br/>
                            <input type="checkbox" name="czcf2" style="margin-bottom: 5px;" value="7||0.3"/>对事故发生负有主要责任<br/>
                            <input type="checkbox" name="czcf2" style="margin-bottom: 5px;" value="8||0.2"/>对事故发生负有重要责任<br/>
                            <input type="checkbox" name="czcf2" style="margin-bottom: 5px;" value="9||0.2"/>具有法律、行政法规规定的其他从重处罚情形<br/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div style="text-align:center;margin: 20px;" id="jgxs2"></div>
                            <div style="text-align:center;margin: 20px;">
                                <a id="zycljs2" class="btn btn-primary"  onclick="jycljs2()" style="width:120px">处罚计算</a>
                            </div>
                        </td>
                    </tr>
                    <input type="hidden" id="jg" value=""/>
                    <input type="hidden" id="zz1" value=""/>
                    <input type="hidden" id="zz2" value=""/>
                </tbody>
            </table>
        </form>
	</div>
<script type="text/javascript">
var cfdc = $("#cfdc");
var clfd = $("#clfd");

$(function () {
    zycl2();
});
function zycl2() {
    $('#inputForm2').show();
    $('#inputForm').hide();
}
function zycl1() {
    $('#inputForm').show();
    $('#inputForm2').hide();
}

$("#wfxwid").combobox({  
      onSelect: function () {  
          var id1 = $("#wfxwid").combobox('getValue');
          $.ajax({
				type:'get',
				url: '${ctx}/aqzf/cfcl/clbz/'+id1,
				success : function(data) {
					var cfcl = JSON.parse(data);
					cfdc.html("");
					clfd.html("");
					$.each(cfcl, function(idx, obj) {
						var $tdHtml = $('<input type="radio" onclick="xzdc()" name="dc" style="margin-bottom:5px;" value="'+obj.m2+'||'+obj.m5+'"/>'+obj.m1+'</br>'+
										'处罚范围：<input type="text" id="dcd_'+obj.m5+'" class="easyui-numberbox" style="width:60px;height:25px;" value="'+obj.m3+'"/>'+' ~ '+
										'<input type="text" id="dcg_'+obj.m5+'" class="easyui-numberbox" style="width:60px;height:25px;" value="'+obj.m4+'"/></br></br>');	
						cfdc.append( $tdHtml );
						$("#dcd_"+obj.m5).numberbox({
							//required:'true',
							min:0
						});
						$("#dcg_"+obj.m5).numberbox({
							//required:'true',
							min:0
						});
					});
				}
		  });
      }  
})  

function xzdc(){
	clfd.html("");
	var radio = document.getElementsByName("dc");  
    for (i=0; i<radio.length; i++) {  
        if (radio[i].checked) {  
            clfd.append(radio[i].value.split("||")[0]);  
        }  
    } 
}

function jycljs(){
	if($("#inputForm").form('validate')){
		var radio = document.getElementsByName("dc");
	    for (i=0; i<radio.length; i++) {  
	        if (radio[i].checked) {  
	            var m5 = radio[i].value.split("||")[1];  
	        }  
	    }
	    if(m5==undefined){
	    	layer.msg("请选择处罚档次！",{time: 3000});
	    	return;
	    }
	    //最低处罚
	    var dcd = $("#dcd_"+m5).numberbox('getValue');
	    //最高处罚
	    var dcg = $("#dcg_"+m5).numberbox('getValue');
	    //事前处罚个数
	    var checksqcf = document.getElementsByName("sqcf");  
		var sqcf_counts = 0;
		for(var i=0;i<checksqcf.length;i++){
			if(checksqcf[i].checked){         
				sqcf_counts++;
			}
		}
		//事故处罚个数
		var checksgcf = document.getElementsByName("sgcf");  
		var sgcf_counts = 0;
		for(var i=0;i<checksgcf.length;i++){
			if(checksgcf[i].checked){         
				sgcf_counts++;
			}
		}
		if(sqcf_counts>0&&sgcf_counts>0){
			layer.msg("事前处罚和事后处罚叠加！",{time: 3000});
	    	return;
		}
		//从轻处罚
		var cqcfxs = 1;
		var checkcqcf = document.getElementsByName("cqcf"); 
		for(var i=0;i<checkcqcf.length;i++){
			if(checkcqcf[i].checked){         
				cqcfxs = cqcfxs * (1-checkcqcf[i].value.split("||")[1]);
			}
		} 
		//从重处罚
		var czcfxs = 1;
		var checkczcf = document.getElementsByName("czcf"); 
		for(var i=0;i<checkczcf.length;i++){
			if(checkczcf[i].checked){         
				czcfxs = czcfxs * (checkczcf[i].value.split("||")[1] - (-1));
			}
		} 
		var jg = parseInt((dcg - ((dcg-dcd)*(sqcf_counts+sgcf_counts)/4))*cqcfxs*czcfxs);
		$("#jgxs").html("");
		$("#jgxs").append('<h3 style="color: red;">处罚数额：'+jg+'元&nbsp;&nbsp;（处罚下限：'+dcd+'元&nbsp;&nbsp;&nbsp;&nbsp;处罚上限：'+dcg+'元）</h3>');
	}
}


//自由裁量（新）计算
var cfdc2 = $("#cfdc2");
var clfd2 = $("#clfd2");

$("#wfxwid2").combobox({
    onSelect: function () {
        var id1 = $("#wfxwid2").combobox('getValue');
        zkln2(id1);
    }
});

function zkln2(id1){
    $.ajax({
        type:'get',
        url: '${ctx}/aqzf/cfcl/clbz/'+id1,
        success : function(data) {
            var cfcl = JSON.parse(data);
            cfdc2.html("");
            clfd2.html("");
            $.each(cfcl, function(idx, obj) {
                var $tdHtml = $('<input type="radio" onclick="xzdc2()" name="dc2" style="margin-bottom:5px;" value="'+obj.m2+'||'+obj.m5+'"/>'+obj.m1+'</br>'+
                    '处罚范围：<input type="text" id="dcd2_'+obj.m5+'" class="easyui-numberbox" style="width:60px;height:25px;" value="'+obj.m3+'"/>'+' ~ '+
                    '<input type="text" id="dcg2_'+obj.m5+'" class="easyui-numberbox" style="width:60px;height:25px;" value="'+obj.m4+'"/></br></br>');
                cfdc2.append( $tdHtml );
                $("#dcd2_"+obj.m5).numberbox({
                    //required:'true',
                    min:0
                });
                $("#dcg2_"+obj.m5).numberbox({
                    //required:'true',
                    min:0
                });
            });
        }
    });
}
function xzdc2(){
    clfd2.html("");
    var radio = document.getElementsByName("dc2");
    for (i=0; i<radio.length; i++) {
        if (radio[i].checked) {
            clfd2.append(radio[i].value.split("||")[0]);
        }
    }
}
function jycljs2(){
    if($("#inputForm2").form('validate')){
        var radio = document.getElementsByName("dc2");
        for (i=0; i<radio.length; i++) {
            if (radio[i].checked) {
                var m5 = radio[i].value.split("||")[1];
            }
        }
        if(m5==undefined){
            layer.msg("请选择处罚档次！",{time: 3000});
            return;
        }
        //最低处罚
        var dcd = $("#dcd2_"+m5).numberbox('getValue');
        //最高处罚
        var dcg = $("#dcg2_"+m5).numberbox('getValue');
        if(dcd==''||dcd==null||dcg==''||dcg==null){
            layer.msg("请完善选择处罚档次对应的处罚范围！",{time: 3000});
            return;
        }

        //事前处罚
        var checksqcf = document.getElementsByName("sqcf2");
        var sqcf_counts = 0;//事前处罚个数
        var sqcfgs = '';//事前处罚公式
        for(var i=0;i<checksqcf.length;i++){
            if(checksqcf[i].checked){
                sqcf_counts++;
                if(sqcf_counts == 1){
                    sqcfgs += 'a'+(i+1);
                }else{
                    sqcfgs += '+'+'a'+(i+1);
                }
            }
        }
        //事故处罚个数
        var checksgcf = document.getElementsByName("sgcf2");
        var sgcf_counts = 0;//事故处罚个数
        var sgcfgs = '';//事故处罚公式
        for(var i=0;i<checksgcf.length;i++){
            if(checksgcf[i].checked){
                sgcf_counts++;
                if(sgcf_counts == 1){
                    sgcfgs += 'a'+(i+1);
                }else{
                    sgcfgs += '+'+'a'+(i+1);
                }
            }
        }
        if(sqcf_counts>0&&sgcf_counts>0){
            layer.msg("事前处罚和事故处罚叠加！",{time: 3000});
            return;
        }

        //从重处罚
        var czcfxs = 1;
        var checkczcf = document.getElementsByName("czcf2");
        for(var i=0;i<checkczcf.length;i++){
            if(checkczcf[i].checked){
                czcfxs = czcfxs * (checkczcf[i].value.split("||")[1] - (-1));
            }
        }
        var jg = parseInt((dcg - ((dcg-dcd)*(sqcf_counts+sgcf_counts)/$("#cffm2").numberbox('getValue')))*czcfxs);//处罚结果
        $("#jgxs2").html("");

        var dc = '';
        var xx = '';
        var sx = '';
        if(m5=='0'||m5=='1'){
            dc = '一档';
            xx = 'E1';
            sx = 'E2';
        }else if(m5=='2'){
            dc = '二档';
            xx = 'E2';
            sx = 'E3';
        }else if(m5=='3'){
            dc = '三档';
            xx = 'E3';
            sx = 'E4';
        }

        var gs = '';
        if(sqcf_counts>0){
            gs = sqcfgs;
        }else{
            gs = sgcfgs;
        }

        var zz1 = dc+'处罚（'+xx+'='+dcd+'，'+sx+'='+dcg+'）';
        var zz2 = '';
        if(czcfxs == 1){
            zz2 = sx+'-（'+sx+'-'+xx+'）*（'+gs+'）\/'+$("#cffm2").numberbox('getValue')+'='+jg;
        }else{
            zz2 = '（'+sx+'-（'+sx+'-'+xx+'）*（'+gs+'）\/'+$("#cffm2").numberbox('getValue')+'）*'+czcfxs+'='+jg;
        }


        $("#jgxs2").append('<h3 style="color: red;">处罚档次：定为'+zz1+'</h3>');
        $("#jgxs2").append('<h3 style="color: red;">具体裁量计算：S='+zz2+'</h3>');


    }
}
</script>
</body>
</html>