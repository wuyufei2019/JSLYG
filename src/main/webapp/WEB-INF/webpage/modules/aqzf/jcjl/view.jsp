<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.4.5/datagrid-detailview.js"></script>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">检查编号：</label></td>
					<td class="width-30" colspan="3">${jcjl.m0 }</td>
				</tr>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">被检查单位：</label></td>
					<td class="width-30" colspan="3">${jcjl.qyname }</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">地址：</label></td>
					<td class="width-30" colspan="3">
						${jcjl.m1 }
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">法定代表人（负责人）：</label></td>
					<td class="width-30">${jcjl.m2 }</td>
					<td class="width-20 active"><label class="pull-right">职务：</label></td>
					<td class="width-30">${jcjl.m3 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-30">${jcjl.m4 }</td>
					<td class="width-20 active"><label class="pull-right">检查场所：</label></td>
					<td class="width-30">${jcjl.m5 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">检查时间起：</label></td>
					<td class="width-30" >${jcjl.m6 }</td>
					<td class="width-20 active"><label class="pull-right">检查时间止：</label></td>
					<td class="width-30" >${jcjl.m7 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">检查人员：</label></td>
					<td class="width-30" colspan="3">${jcjl.m8 }</td>
				</tr>


				<tr>
					<td class="width-20 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-85" colspan="3" >
						<table id="tt"  ></table>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">存在问题：</label></td>
					<td class="width-30" colspan="3">
						<table id="czwttable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
							<tr >
								<td style="text-align: center;width: 80%;">问题</td>
								<td style="text-align: center;width: 20%;">图片(点击查看原图)</td>
							</tr>
						</table>
						
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-30" colspan="3" style="height:80px">
						${jcjl.m10 }
					</td>
				</tr>	

				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
    var m15 = '${jcjl.m15}';
    var wfxw;
    if(m15 == null || m15 == '' || m15 == '1') {
        wfxw = 'wfxw';
    }else if(m15 == '2') {
        wfxw = 'wfxwtwo';
    }

	var wtListInit='${czwt}';
		nrList=JSON.parse(wtListInit);  
	var wtallids = "";
	var qtwtallids = "";
	var czzgwtallids = "";
	var i = 1;
	$.each(nrList, function(idx, obj) {
    	var url=obj.m4;
    	var czwt=obj.m2;
    	var qtwtms=obj.m3;
    	var czzgwt=obj.m5;
    	wtallids +="czwtidwd_" + i+",";
    	qtwtallids +="qtwtms_" + i+",";
    	czzgwtallids +="czzgwt_" + i + ",";
    	if((url!=null)&&(url!="")&&(url!="undefined")){
	    	var img=url.split("||");
			var $trHtml = $(
				'<tr>'+
				'<td>检查情况：<br/>'+
				'<input type="text" id="qtwtms_'+ i +'" style="width: 100%;height: 40px;" value="'+qtwtms+'" /><br/>'+
				'须整改的问题：<br/>'+
				'<input type="text" id="czzgwt_'+ i +'" style="width: 100%;height: 40px;" value="'+czzgwt+'" /><br/>'+
				'涉嫌违法行为：<br/>'+
				'<input type="text" id="czwtidwd_'+ i +'" style="width: 100%;height: 30px;" value="'+czwt+'" />'+
				'</td>'+
				'<td>'+
				'<div style="text-align:center;">' +
				'<a target="_blank" href="'+img[0]+'">'+
				'<img src=\''+img[0]+'\' style=\'width:100px; height: 100px\'>' +
				'<div class=\'info\'>' + img[1] + '</div>' +
				'</a>'+
				'</div>'+
				'</td>'+
				'<td>'+
				'</td>'+
				'</tr>'
			 );	
    	}else{
    		var img=url.split("||");
			var $trHtml = $(
				'<tr>'+
				'<td style="text-align:center;">'+
				'<input type="text" id="czwtidwd_'+ i +'" style="width: 100%;height: 100%;" value="'+czwt+'" />'+
				'</td>'+
				'<td>'+
				'</td>'+
				'<td>'+
				'</td>'+
				'</tr>'
			 );	
    	}
    	i++;
		//id和数量加1
    	var  $list= $("#czwttable tr").eq(-1);
		$list.after( $trHtml );
	});
	
	if(wtallids.length>0){
			wtallids = wtallids.substr(0,wtallids.length - 1);
	}
	var wtallid = wtallids.split(",");
	for (x in wtallid){
		$("#"+wtallid[x]).combobox({
			editable:true ,
			valueField:'id',
			textField:'text',
			panelHeight:'100px',
			multiline:true ,
			readonly:'true',
			url:ctx+'/aqzf/'+wfxw+'/idlist'
		});
	}
	if(qtwtallids.length>0){
		qtwtallids = qtwtallids.substr(0,qtwtallids.length - 1);
	}
	var qtwtallid = qtwtallids.split(",");
	for (w in qtwtallid){
		$("#"+qtwtallid[w]).textbox({
			editable:true ,
			readonly:'true',
			multiline:true 
		});
	}
	if(czzgwtallids.length>0){
		czzgwtallids = czzgwtallids.substr(0,czzgwtallids.length - 1);
	}
	var czzgwtid = czzgwtallids.split(",");
	for (y in czzgwtid){
		$("#"+czzgwtid[y]).textbox({
			editable:true ,
			readonly:'true',
			multiline:true 
		});
	}
	
	var faid;
	var jcwtallids = "";
	var wtmsallids = "";
	var zgwtallids = "";
	faid= '${jcjl.id}';
	var target=$('#tt').datagrid({
		method : "get",
		view: detailview,
		width:705,
		nowrap:false,
		singleSelect:true,
		url:ctx+'/aqzf/jcjl/nrlist2/'+faid,
		columns:[[
			{field:'jcdy',title:'检查单元',width:192},
			{field:'jcnr',title:'检查内容',width:343},
			{field:'jcjg',title:'操作',width:143,
				formatter: function(value, rowData, rowIndex){
					if(value=='1'){
                		return '符合<input disabled="disabled" checked="checked" type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_1_' + rowData.id + ' value=1 ' + '/>' +
                		'不符合<input disabled="disabled" type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_2_' + rowData.id + ' value=0 ' + ' />';
            		}else{
            		    return '符合<input disabled="disabled" type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_1_' + rowData.id + ' value=1 ' + '/>' +
                		'不符合<input disabled="disabled" checked="checked" type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_2_' + rowData.id + ' value=0 ' + ' />';
            		}
            	}
			},
			{field:'ID',title:'id',width:50,hidden:'true'}
		]], 
        detailFormatter:function(rowIndex, rowData){
       		jcwtallids +="jcwt_" + rowData.id+",";
       		wtmsallids +="wtms_" + rowData.id+",";
       		zgwtallids +="zgwt_" + rowData.id+",";
            var picurl = '<td><div style="height: 10px;"></td>';
        	var url=rowData.pic;
        	if((url!=null)&&(url!="")&&(url!="undefined")){
        		var img=url.split("||");
        		picurl ='<td class="width-30" colspan="3">' +
						'图片：' +	
						'</td>'+									
						'</tr>'+
						'<tr>'+
						'<td class="width-30" colspan="3">' + 
        				'<div style="float: left;text-align: center;margin: 0 10px 10px 0;" id="'+rowData.id+'" >'+
			 			'<a target="_blank" id="pic_'+rowData.id+'" href="'+img[0]+'"><img src="'+img[0]+'" alt="'+img[1]+'" width="100px;"/><br/>'+img[1]+'</a>'+
			 			'<p></p>'+
			 			'</div>'+
			 			'</td>';
        	}
			return '<div id="ddv-' + rowIndex + '"><table style="width: 98%;">' +
					'<input type="hidden" name="jcnrid_' + rowData.id + '" value="' + rowData.id + '" />' +
					'<input type="hidden" id="jcnr_' + rowData.id + '" value="' + rowData.jcnr + '" />' +
					'<tr>'+
					'<td>' +
					'检查情况：' +	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td>' +
					'<input type="text" id="wtms_' + rowData.id + '" name="wtms_' + rowData.id + '" style="width: 667px;height: 50px;" value="'+rowData.wtms+'" />' +	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td>' +
					'须整改的问题：' +	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td>' +
					'<input type="text" id="zgwt_' + rowData.id + '" name="zgwt_' + rowData.id + '" style="width: 667px;height: 50px;" value="'+rowData.zgwt+'" />' +	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td>' +
					'涉嫌违法行为：' +	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td>' +
					'<input type="text" id="jcwt_' + rowData.id + '" name="jcwt_' + rowData.id + '" style="width: 667px;height: 30px;" value="'+rowData.jcwt+'" />' +	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					   picurl+									
					'</tr>'+
					'</table></div>';    
		},
		onLoadSuccess : function(rowdata, rowindex, rowDomElement) {	
		   	if(jcwtallids.length>0){
				jcwtallids = jcwtallids.substr(0,jcwtallids.length - 1);
			}
			var jcwtid = jcwtallids.split(",");
			for (x in jcwtid){
				$("#"+jcwtid[x]).combobox({
					editable:true ,
	    			valueField:'id',
	    			textField:'text',
	    			readonly:'true',
	    			//multiple:true ,  
        			//multiline:true ,
        			//separator:'||',
	    			panelHeight:'135px',
	    			url:'${ctx}/aqzf/'+wfxw+'/idlist'
				});
			}
			if(wtmsallids.length>0){
				wtmsallids = wtmsallids.substr(0,wtmsallids.length - 1);
			}
			var wtmsid = wtmsallids.split(",");
			for (w in wtmsid){
				$("#"+wtmsid[w]).textbox({
					editable:true ,
					readonly:'true',
        			multiline:true
				});
			}
			if(zgwtallids.length>0){
				zgwtallids = zgwtallids.substr(0,zgwtallids.length - 1);
			}
			var zgwtid = zgwtallids.split(",");
			for (w in zgwtid){
				$("#"+zgwtid[w]).textbox({
					editable:true ,
					readonly:'true',
        			multiline:true
				});
			}
		}
	});
</script>
</body>
</html>