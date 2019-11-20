<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>添加安监呈批记录</title>
<meta name="decorator" content="default" />
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/ybcf/ajclcp/${action}" method="post"
          class="form-horizontal">
          <table
               class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
               <tbody>
               <c:if test="${action eq 'updateSub'}">
                    <tr>
                         <td class="width-20 active"><label class="pull-right">编号：</label></td>
                         <td class="width-30"><input name="number" class="easyui-textbox"
                                   editable="false" value="${jce.number }"
                                   style="width: 100%;height: 30px;" /></td>
                    </tr>
               </c:if>
               <tr>
                    <td class="width-20 active"><label class="pull-right"> 案件名称： </label>
                    </td>
                    <td class="width-30" colspan="3"><input value="${jce.casename }"
                              id="casename" name="casename" style="width: 100%;height: 30px;"
                              class="easyui-textbox" /></td>
               </tr>
               <tr>
                    <td class="width-20 active"><label class="pull-right"> 受处罚类型：
                         </label></td>
                    <td class="width-30" style="text-align:left">
                        <c:if test="${cfdxlx == '2'}">
                            <input class="easyui-validatebox" type="radio" disabled style="margin-bottom: 6px;" name="cfdxlx" />公司
                            <input class="easyui-validatebox" type="radio" checked disabled style="margin-bottom: 6px;margin-left: 20px;" name="cfdxlx" />个人
                            <input type="hidden" name="percomflag" value="0">
                        </c:if>
                        <c:if test="${cfdxlx == null || cfdxlx == '1' || cfdxlx == ''}">
                            <input class="easyui-validatebox" type="radio" checked disabled style="margin-bottom: 6px;" name="cfdxlx" />公司
                            <input class="easyui-validatebox" type="radio" disabled style="margin-bottom: 6px;margin-left: 20px;" name="cfdxlx" />个人
                            <input type="hidden" name="percomflag" value="1">
                        </c:if>
                    </td>
               </tr>
               <tr name="company">
                    <td class="width-20 active"><label class="pull-right"> 公司名称：
                         </label></td>
                    <td class="width-30"><input id="qypunishname" name="punishname"
                              class="easyui-combobox" value="${jce.punishname }"
                              style="width: 100%;height: 30px;" 
                              data-options="readonly:'true',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td></td>
                    <td class="width-20 active"><label class="pull-right"> 企业地址： </label>
                    </td>
                    <td class="width-30"><input id="qyaddress" name="qyaddress"
                              class="easyui-textbox" value="${jce.qyaddress }"
                              style="width: 100%;height: 30px;" /></td>
               </tr>
               <tr name="company">
                    <td class="width-20 active"><label class="pull-right"> 法定代表人：
                         </label></td>
                    <td class="width-30"><input id="legal" name="legal"
                              class="easyui-textbox" value="${jce.legal }"
                              style="width: 100%;height: 30px;" /></td>
                    <td class="width-20 active"><label class="pull-right"> 职务： </label></td>
                    <td class="width-30"><input id="duty" name="duty"
                              class="easyui-textbox" value="${jce.duty }"
                              style="width: 100%;height: 30px;" /></td>
               </tr>
               <tr name="company">
                    <td class="width-20 active"><label class="pull-right"> 企业邮编： </label>
                    </td>
                    <td class="width-30"><input name="qyyb" type="text" id="qyyb"
                              value="${jce.qyyb }" class="easyui-textbox"
                              style="width: 100%;height: 30px;" data-options="validType:'ZIP'" />
                    </td>
               </tr>
               <tr name="person">
                    <td class="width-20 active"><label class="pull-right"> 被处罚人： </label>
                    </td>
                    <td class="width-30 "><input id="grpunishname" name="punishname"
                              class="easyui-textbox" value="${jce.punishname }"
                              style="width: 100%;height: 30px;" readonly
                              data-options="required:'true'" /></td>
                    <td class="width-20 active"><label class="pull-right"> 电话： </label></td>
                    <td class="width-30"><input id="pmobile" name="mobile"
                              class="easyui-textbox" value="${jce.mobile }"
                              style="width: 100%;height: 30px;" validtype="mobileAndTel" /></td>
               </tr>
               <tr name="person">
                    <td class="width-20 active"><label class="pull-right"> 年龄： </label></td>
                    <td class="width-30"><input id="perage" name="age"
                              class="easyui-textbox" value="${jce.age }"
                              style="width: 100%;height: 30px;"
                              data-options="validType:['length[0,2]','number']" /></td>
                    <td class="width-20 active"><label class="pull-right"> 性别： </label></td>
                    <td class="width-30"><input id="persex" name="sex"
                              class="easyui-combobox" value="${jce.sex }"
                              style="width:100%;height: 30px;"
                              data-options="panelHeight:'auto',editable:false,data: [{value:'男',text:'男'},
				{value:'女',text:'女'}]" />
                    </td>
               </tr>
               <tr name="person">
                    <td class="width-20 active"><label class="pull-right"> 所在单位： </label>
                    </td>
                    <td class="width-30" colspan="3"><input id="dwname" name="dwname"
                              class="easyui-textbox" value="${jce.dwname }"
                              style="width: 100%;height: 30px;" /></td>
               </tr>
               <tr name="person">
                    <td class="width-20 active"><label class="pull-right"> 单位地址： </label>
                    </td>
                    <td class="width-30" colspan="3"><input id="dwaddress"
                              name="dwaddress" class="easyui-textbox" value="${jce.dwaddress }"
                              style="width:100%;height: 30px;" /></td>
               </tr>
               <tr name="person">
                    <td class="width-20 active"><label class="pull-right"> 家庭地址： </label>
                    </td>
                    <td class="width-30"><input id="paddress" name="address"
                              class="easyui-textbox" value="${jce.address }"
                              style="width: 100%;height: 30px;"
                              data-options="validType:['length[0,100]']" /></td>
                    <td class="width-20 active"><label class="pull-right"> 家庭邮编： </label>
                    </td>
                    <td class="width-30"><input id="jtyb" name="jtyb"
                              class="easyui-textbox" value="${jce.jtyb }"
                              style="width:100%;height: 30px;" data-options="validType:'ZIP'" />
                    </td>
               </tr>
               <tr>
                    <td class="width-20 active"><label class="pull-right">
                              违法事实及处罚依据： </label></td>
                    <td class="width-30" colspan="3"><input id="illegalactandevidence"
                              name="illegalactandevidence"
                              value="${jce.illegalactandevidence }" class="easyui-textbox"
                              style="width: 100%;height: 150px;"
                              data-options="multiline:true,validType:['length[0,1000]']" /></td>
               </tr>
               <tr>
                    <td class="width-20 active"><label class="pull-right">
                              当事人申辩意见： </label></td>
                    <td class="width-30" colspan="3"><input id="sbrecord" name="sbrecord"
                              class="easyui-textbox" value="${jce.sbrecord }"
                              style="width: 100%;height: 30px;"
                              data-options="validType:['length[0,200]']"/>
                       </td>
                              
               </tr>
               <tr>
                    <td class="width-20 active"><label class="pull-right"> 承办人意见：
                         </label></td>
                    <td class="width-30" colspan="3"><input id="opinion" name="opinion"
                              class="easyui-textbox" value="${jce.opinion }"
                              style="width: 100%;height: 100px;"
                              data-options="multiline:true,validType:['length[0,1000]']" /></td>
               </tr>
               
           <tr>
				<td class="width-20 active"><label class="pull-right">承办人：</label></td>
				<td class="width-30"><input type="text" id="cbr1" name="cbr1" class="easyui-combobox" value="${jce.cbr1 }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
				<td class="width-20 active"><label class="pull-right">承办人：</label></td>
				<td class="width-30"><input type="text" id="cbr2" name="cbr2" class="easyui-combobox" value="${jce.cbr2 }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
		   </tr>    
           <tr>
            	<td class="width-20 active"><label class="pull-right">承办日期：</label></td>
				<td class="width-30"><input id="cbsj" name="cbsj" class="easyui-datebox" value="${jce.cbsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
           </tr>
           <tr>
				<td class="width-20 active"><label class="pull-right">法制审核意见：</label></td>
				<td class="width-30" colspan="3">
					<c:choose>
					<c:when test="${jce.fzshyj=='0'}">
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
				<td class="width-30"><input type="text" id="fzshr" name="fzshr" class="easyui-combobox" value="${jce.fzshr }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
				<td class="width-20 active"><label class="pull-right">法制审核日期：</label></td>
				<td class="width-30"><input id="fzshsj" name="fzshsj" class="easyui-datebox" value="${jce.fzshsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
			</tr>    
            <tr>
				<td class="width-20 active"><label class="pull-right">审核意见：</label></td>
				<td class="width-30" colspan="3">
					<c:choose>
						<c:when test="${jce.shyj=='0'}">
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
				<td class="width-30"><input type="text" id="shr" name="shr" class="easyui-combobox" value="${jce.shr }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
				<td class="width-20 active"><label class="pull-right">审核日期：</label></td>
				<td class="width-30"><input id="shsj" name="shsj" class="easyui-datebox" value="${jce.shsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">审批意见：</label></td>
				<td class="width-30" colspan="3">
					<c:choose>
						<c:when test="${jce.spyj=='0'}">
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
				<td class="width-30"><input type="text" name="spr" class="easyui-combobox" value="${jce.spr }"  data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist'" style="height: 30px;width: 100%" /></td>
				<td class="width-20 active"><label class="pull-right">审批日期：</label></td>
				<td class="width-30"><input id="spsj" name="spsj" class="easyui-datebox" value="${jce.spsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
			</tr>
            <!-- 隐藏域 -->
            <input type='hidden' name="id1" value="${jce.id1}" />
            <c:if test="${action eq 'updateSub' }">
                 <input type="hidden" name="ID" value="${jce.ID}" />
                 <input type='hidden' name="S1" value="<fmt:formatDate value="${jce.s1}"/>"/>
            </c:if>
            </tbody>
       </table>
  </form>

<script type="text/javascript">
	$(function() {
		if ('${action}' == 'createSub') {
			$("#grpunishname").textbox("setValue", '${yle.cfryname}');
			$("#pmobile").textbox("setValue", '${yle.cfryphone}');
			$("#perage").textbox("setValue", '${yle.cfryage}');
			$("#persex").combobox("setValue", '${yle.cfrysex}');
			$("#qypunishname").combobox("setValue", '${yle.dsperson}');
			$("#legal").textbox("setValue", '${yle.legalperson}');
			$("#qyaddress").textbox("setValue", '${yle.dsaddress}');
			$("#dwaddress").textbox("setValue", '${yle.dsaddress}');
			$("#dwname").textbox("setValue", '${yle.dsperson}');
			$("#qyyb").textbox("setValue", '${yle.ybcode}');
			$("#illegalactandevidence").textbox("setValue", '${yle.casecondition}');
			$("input[name='id1']").val('${id1}');
			$("#sbrecord").textbox('setValue', '无申辩意见。');
			$("#casename").textbox('setValue', '${yle.casename}');
			$("#opinion").textbox('setValue', '${yle.opinion}');
			$("input[name='id1']").val('${id1}');
			$("#cbr1").combobox("setValue", '${yle.enforcer1}');
			$("#cbr2").combobox("setValue", '${yle.enforcer2}');
			comResult();
			$('#cbsj').datebox('setValue', format(new Date()));	
			$('#fzshsj').datebox('setValue', format(new Date()));	
			$('#shsj').datebox('setValue', format(new Date()));	
			$('#spsj').datebox('setValue', format(new Date()));
            if ('${cfdxlx}' == '2') {
                personResult();
            } else {
                comResult();
            }
		}else{
			if ('${jce.percomflag}' == '1') {
				comResult();
			} else {
				personResult();
			}
		}
	});
			
	function format(date) {
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1);
		return date.getFullYear() + '-' + month + '-' + day;
	};
	
	$(":radio").change(function() {
		var flag = $('input:radio:checked').val();
		if (flag == "1") {
			comResult();
		}
		if (flag == "0") {
			personResult();
		}
	});
			
	/*$("#grpunishname").combobox({
		onSelect : function() {
			var id = $('#grpunishname').combobox('getValue');
			$.ajax({
				type : 'get',
				url : '$'{ctx}/bis/ygxx/ygdetail/' + id,
				success : function(data) {
					var d = JSON.parse(data);
					$("#persex").combobox('setValue', d.m3);
					$("#pmobile").textbox('setValue', d.m9);
					$("#identity").textbox('setValue', d.m8);
					if(d.m10!=''&&d.m10!=null&&d.m10!=undefined){
						$("#perage").textbox('setValue', new Date().getFullYear() - new Date(d.m10).getFullYear());
					}
					$("#dwname").textbox('setValue', d.m1);
					//$("#perduty").textbox('setValue',d.m7);	
					$("#dwaddress").textbox('setValue', d.m33);
				}
			});
		}
	});*/
			
	$("#qypunishname").combobox({
		onSelect : function() {
			var id = $('#qypunishname').combobox('getValue');
			$.ajax({
				type : 'get',
				url : '${ctx}/bis/qyjbxx/qydetail/' + id,
				success : function(data) {
					var d = JSON.parse(data);
					$("#legal").textbox('setValue', d.m5);
					$("#mobile").textbox('setValue', d.m6);
					$("#qyyb").textbox('setValue', d.m9);
					$("#qyaddress").textbox('setValue', d.m8);
				}
			});
		}
	});

	function personResult() {
		$("[name='person']").show();
		$("[name='company']").hide();
		$("#qypunishname").combobox("disable");
		$("#legal").textbox("disable");
		$("#qyyb").textbox("disable");
		$("#duty").textbox("disable");
		$("#qyaddress").textbox("disable");
		$('#grpunishname').combobox('enable');
		$('#pmobile').textbox('enable');
		$('#perage').textbox('enable');
		$('#persex').textbox('enable');
		$('#perduty').textbox('enable');
		$('#dwname').textbox('enable');
		$('#dwaddress').textbox('enable');
		$('#paddress').textbox('enable');
		$('#jtyb').textbox('enable');
	}

	function comResult() {
		$("[name='person']").hide();
		$("[name='company']").show();
		$("#qypunishname").combobox("enable");
		$("#legal").textbox("enable");
		$("#qyyb").textbox("enable");
		$("#duty").textbox("enable");
		$("#qyaddress").textbox("enable");
		$('#grpunishname').combobox('disable');
		$('#pmobile').textbox('disable');
		$('#perage').textbox('disable');
		$('#persex').textbox('disable');
		$('#perduty').textbox('disable');
		$('#dwname').textbox('disable');
		$('#dwaddress').textbox('disable');
		$('#paddress').textbox('disable');
		$('#jtyb').textbox('disable');
	}

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function doSubmit() {
        if ('${action}' == 'createSub') {
            if ('${cfdxlx}' == '2') {

            }else {
                $("#qypunishname").combobox("setValue", $("#qypunishname").combobox("getText"));
            }
        }else{
            if ('${cfdxlx}' == '2') {

            }else {
                $("#qypunishname").combobox("setValue", $("#qypunishname").combobox("getText"));
            }
        }
		$("#inputForm").serializeObject();
		$("#inputForm").submit();
	}
	
	$(function() {
		var flag = true;
		$('#inputForm').form({
			onSubmit : function() {
				var isValid = $(this).form('validate');
				if (isValid && flag) {
					flag = false;
					$.jBox.tip("正在提交，请稍等...", 'loading', {
						opacity : 0
					});
					return true;
				}
				return false; //返回false终止表单提交
			},
			success : function(data) {
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