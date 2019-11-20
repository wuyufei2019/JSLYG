<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>自由裁量</title>
	<meta name="decorator" content="default"/>
</head>
<body >
	<div>
		<form id="inputForm" class="form-horizontal">
			<div style="text-align:center;margin: 20px;">
				<h1 style="color: red;">自由裁量计算</h1>				
			</div>
			<table
				class="table table-bordered  dataTables-example dataTable no-footer"
				style="margin:0 auto;width:80%;">
				<tbody>
					<tr>
						<td class="width-20 active"><label class="pull-right">违法行为：</label></td>
						<td class="width-80"><input id="wfxwid" class="easyui-combobox" value=""  style="width:100%;height:30px;"
							data-options="required:'true',editable:true ,valueField:'id',textField:'text',panelHeight:'150px',url:'${ctx}/aqzf/wfxwtwo/idlist'" /></td>
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
						<input type="checkbox" name="sqcf" style="margin-bottom: 5px;" value="5"/>企业安全风险辨识管控机制建设是否到位<br/>
						<input type="checkbox" name="sqcf" style="margin-bottom: 5px;" value="6"/>特殊岗位机械化换人、自动化减人是否到位<br/>
						</td>
					</tr>
					<tr>
						<td class="width-20 active"><label class="pull-right">事故处罚：</label></td>
						<td class="width-80" style="padding-top: 10px;padding-bottom: 10px;">
						<input type="checkbox" name="sgcf" style="margin-bottom: 5px;" value="1"/>对事故调查积极配合<br/>
						<input type="checkbox" name="sgcf" style="margin-bottom: 5px;" value="2"/>对事故善后处理妥善积极<br/>
						<input type="checkbox" name="sgcf" style="margin-bottom: 5px;" value="3"/>对事故隐患按照‘四不放过’原则积极采取措施整治到位<br/>
						<input type="checkbox" name="sgcf" style="margin-bottom: 5px;" value="4"/>积极组织或支持安全生产公益活动<br/>
						<input type="checkbox" name="sgcf" style="margin-bottom: 5px;" value="5"/>企业安全风险辨识管控机制建设是否到位<br/>
						<input type="checkbox" name="sgcf" style="margin-bottom: 5px;" value="6"/>特殊岗位机械化换人、自动化减人是否到位<br/>
						</td>
					</tr>
					<tr>
						<td class="width-20 active"><label class="pull-right">分母：</label></td>
						<td class="width-30">
							<input id="cffm" class="easyui-numberbox" value="" style="width:80px;height:30px;"
								data-options="required:'true',editable:true,min:0,precision:0"/>
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
$("#wfxwid").combobox({  
      onSelect: function () {  
          var id1 = $("#wfxwid").combobox('getValue');
          zkln(id1);
      }  
});

$(function(){
	var flag = '${flag}';
	if(flag == '0'){
		var wfid = '${wfid}';
		$("#wfxwid").combobox('setValue',wfid);
		zkln(wfid);
	}
});  

function zkln(id1){
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
	    if(dcd==''||dcd==null||dcg==''||dcg==null){
	    	layer.msg("请完善选择处罚档次对应的处罚范围！",{time: 3000});
	    	return;
	    }
	    
	    //事前处罚
	    var checksqcf = document.getElementsByName("sqcf");  
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
		var checksgcf = document.getElementsByName("sgcf");  
		var sgcf_counts = 0;//事故处罚个数
		var sgcfgs = '';//事故处罚公式
		for(var i=0;i<checksgcf.length;i++){
			if(checksgcf[i].checked){         
				sgcf_counts++;
				if(sgcf_counts == 1){
					sgcfgs += 'b'+(i+1);
				}else{
					sgcfgs += '+'+'b'+(i+1);
				}
			}
		}
		if(sqcf_counts>0&&sgcf_counts>0){
			layer.msg("事前处罚和事故处罚叠加！",{time: 3000});
	    	return;
		}
		
		//从重处罚
		var czcfxs = 1;
		var checkczcf = document.getElementsByName("czcf"); 
		for(var i=0;i<checkczcf.length;i++){
			if(checkczcf[i].checked){         
				czcfxs = czcfxs * (checkczcf[i].value.split("||")[1] - (-1));
			}
		} 
		var jg = parseInt((dcg - ((dcg-dcd)*(sqcf_counts+sgcf_counts)/$("#cffm").numberbox('getValue')))*czcfxs);//处罚结果
		$("#jgxs").html("");
		
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
		
		var zz1 = dc+'处罚（处罚下限为'+xx+'='+dcd+'元，处罚上限为'+sx+'='+dcg+'元）';
		var zz2 = '';
		if(czcfxs == 1){
			zz2 = sx+'-（'+sx+'-'+xx+'）*（'+gs+'）\/'+$("#cffm").numberbox('getValue')+'='+jg+"元";
		}else{
			zz2 = '（'+sx+'-（'+sx+'-'+xx+'）*（'+gs+'）\/'+$("#cffm").numberbox('getValue')+'）*'+czcfxs+'='+jg+"元";
		}
		
		
		$("#jgxs").append('<h3 style="color: red;">处罚档次：定为'+zz1+'</h3>');
		$("#jgxs").append('<h3 style="color: red;">具体裁量计算：S='+zz2+'</h3>');
		
		$("#jg").val(jg);
		$("#zz1").val(zz1);
		$("#zz2").val(zz2);
	}
}

function getzyclwfid(){
	var zyclwfid=$("#wfxwid").combobox('getValue');
	return zyclwfid;
}

function getzycljg(){
	var zycljg=$('#jg').val();
	return zycljg;
}

function getzyclzz1(){
	var zyclzz1=$('#zz1').val();
	return zyclzz1;
}

function getzyclzz2(){
	var zyclzz2=$('#zz2').val();
	return zyclzz2;
}
</script>
</body>
</html>