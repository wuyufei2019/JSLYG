<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>年度救援训练计划管理</title>
<meta name="decorator" content="default" />
    <style>
        td .easyui-textbox{width: 100%}
    </style>
</head>
<body>

     <form id="inputForm" action="${ctx}/erm/train-plan/year/${action}" method="post" class="form-horizontal">.
         <table id="bqhtable0" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
             <h1 style="width: 100%;text-align: center;font-weight: bolder">年度应急救援技能训练计划</h1>
             <tr>
                 <td class="width-15 active"><label class="pull-right" style="text-align: center;width: 100%">部门：</label></td>
                 <td class="width-35">
                     <input style="width: 100%;height: 30px;" name="depid" class="easyui-combobox " value="${year.depid }"
                            data-options="editable:false, valueField: 'id',textField: 'text', url:'${ctx}/system/department/deptjson' " />
                 </td>
                 <td class="width-15 active"><label class="pull-right" style="text-align: center;width: 100%">时间：</label></td>
                 <td class="width-35">
                     <input style="width: 100%;height: 30px;" id="S1" name="S1" class="easyui-datebox " value="${year.s1 }"
                            data-options="editable:false " readonly="readonly" />
                 </td>
             </tr></table>
          <table id="bqhtable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >

              <tr>
                  <td colspan="2" style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;"><label>时间</label></td>
                  <td style="text-align: center;width: 40%;height: 30px;background: #f5f5f5;"><label>训练内容</label></td>
                  <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;flex-shrink: 0"><label>组训方式</label></td>
                  <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;flex-shrink: 0"><label>组训责任人</label></td>
               </tr>

              <tr>
                  <td rowspan="3" style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><label>一季度</label></td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">1月份</td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 60px;"  name="M1" class="easyui-textbox " value="${year.m1 }"
                                     data-options="multiline:true " /></td>
                  <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                                     name="M1_2" class="easyui-combobox " value="${year.m1_2 }"
                                     data-options="panelHeight:'auto', editable:false, valueField: 'text',textField: 'text',data: [
								        {value:'单个教练',text:'单个教练'},
								        {value:'分组训练',text:'分组训练'},
								        {value:'集体合练',text:'集体合练'}] " /></td>
                  <td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                                     name="M1_3" class="easyui-textbox " value="${year.m1_3 }"
                                     data-options="validType:'length[0,25]'" /></td>
              </tr>

              <tr>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">2月份</td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 60px;"  name="M2" class="easyui-textbox " value="${year.m2 }"
                                     data-options="multiline:true " /></td>
                  <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                                     name="M2_2" class="easyui-combobox " value="${year.m2_2 }"
                                     data-options="panelHeight:'auto', editable:false, valueField: 'text',textField: 'text',data: [
								        {value:'单个教练',text:'单个教练'},
								        {value:'分组训练',text:'分组训练'},
								        {value:'集体合练',text:'集体合练'}] " /></td>
                  <td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                                      name="M2_3" class="easyui-textbox " value="${year.m2_3 }"
                                      data-options="validType:'length[0,25]'" /></td>
              </tr>

              <tr>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">3月份</td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">
                      <input style="width: 100%;height: 60px;"  name="M3" class="easyui-textbox " value="${year.m3 }"  data-options="multiline:true " />
                  </td>
                  <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">
                      <input style="width: 100%;"
                                     name="M3_2" class="easyui-combobox " value="${year.m3_2 }"
                                     data-options="panelHeight:'auto', editable:false, valueField: 'text',textField: 'text',data: [
								        {value:'单个教练',text:'单个教练'},
								        {value:'分组训练',text:'分组训练'},
								        {value:'集体合练',text:'集体合练'}] "  /></td>
                  <td style="text-align: center;width: 53%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                                     name="M3_3" class="easyui-textbox " value="${year.m3_3 }"
                                     data-options="validType:'length[0,25]'" /></td>
              </tr>

              <tr>
                  <td rowspan="3" style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><label>二季度</label></td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">4月份</td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 60px;"  name="M4" class="easyui-textbox " value="${year.m4 }"
                                      data-options="multiline:true " /></td>
                  <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                                     name="M4_2" class="easyui-combobox " value="${year.m4_2 }"
                                     data-options="panelHeight:'auto', editable:false, valueField: 'text',textField: 'text',data: [
								        {value:'单个教练',text:'单个教练'},
								        {value:'分组训练',text:'分组训练'},
								        {value:'集体合练',text:'集体合练'}] " /></td>
                  <td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                                      name="M4_3" class="easyui-textbox " value="${year.m4_3 }"
                                      data-options="validType:'length[0,25]'" /></td>
              </tr>

              <tr>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">5月份</td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 60px;"  name="M5" class="easyui-textbox " value="${year.m5 }"
                                      data-options="multiline:true " /></td>
                  <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                                     name="M5_2" class="easyui-combobox " value="${year.m5_2 }"
                                     data-options="panelHeight:'auto', editable:false, valueField: 'text',textField: 'text',data: [
								        {value:'单个教练',text:'单个教练'},
								        {value:'分组训练',text:'分组训练'},
								        {value:'集体合练',text:'集体合练'}] " /></td>
                  <td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                                      name="M5_3" class="easyui-textbox " value="${year.m5_3 }"
                                      data-options="validType:'length[0,25]'" /></td>
              </tr>
              <tr>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">6月份</td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">
                      <input style="width: 100%;height: 60px;"  name="M6" class="easyui-textbox " value="${year.m6 }"  data-options="multiline:true " />
                  </td>
                  <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">
                      <input style="width: 100%;"
                             name="M6_2" class="easyui-combobox " value="${year.m6_2 }"
                             data-options="panelHeight:'auto', editable:false, valueField: 'text',textField: 'text',data: [
								        {value:'单个教练',text:'单个教练'},
								        {value:'分组训练',text:'分组训练'},
								        {value:'集体合练',text:'集体合练'}] "  /></td>
                  <td style="text-align: center;width: 53%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                             name="M6_3" class="easyui-textbox " value="${year.m6_3 }"
                             data-options="validType:'length[0,25]'" /></td>
              </tr>

              <tr>
                  <td rowspan="3" style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><label>三季度</label></td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">7月份</td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 60px;"  name="M7" class="easyui-textbox " value="${year.m7 }"
                              data-options="multiline:true " /></td>
                  <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                             name="M7_2" class="easyui-combobox " value="${year.m7_2 }"
                             data-options="panelHeight:'auto', editable:false, valueField: 'text',textField: 'text',data: [
								        {value:'单个教练',text:'单个教练'},
								        {value:'分组训练',text:'分组训练'},
								        {value:'集体合练',text:'集体合练'}] " /></td>
                  <td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                              name="M7_3" class="easyui-textbox " value="${year.m7_3 }"
                              data-options="validType:'length[0,25]'" /></td>
              </tr>

              <tr>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">8月份</td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 60px;"  name="M8" class="easyui-textbox " value="${year.m8 }"
                              data-options="multiline:true " /></td>
                  <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                             name="M8_2" class="easyui-combobox " value="${year.m8_2 }"
                             data-options="panelHeight:'auto', editable:false, valueField: 'text',textField: 'text',data: [
								        {value:'单个教练',text:'单个教练'},
								        {value:'分组训练',text:'分组训练'},
								        {value:'集体合练',text:'集体合练'}] " /></td>
                  <td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                              name="M8_3" class="easyui-textbox " value="${year.m8_3 }"
                              data-options="validType:'length[0,25]'" /></td>
              </tr>
              <tr>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">9月份</td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">
                      <input style="width: 100%;height: 60px;"  name="M9" class="easyui-textbox " value="${year.m9 }"  data-options="multiline:true " />
                  </td>
                  <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">
                      <input style="width: 100%;"
                             name="M9_2" class="easyui-combobox " value="${year.m9_2 }"
                             data-options="panelHeight:'auto', editable:false, valueField: 'text',textField: 'text',data: [
								        {value:'单个教练',text:'单个教练'},
								        {value:'分组训练',text:'分组训练'},
								        {value:'集体合练',text:'集体合练'}] "  /></td>
                  <td style="text-align: center;width: 53%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                             name="M9_3" class="easyui-textbox " value="${year.m9_3 }"
                             data-options="validType:'length[0,25]'" /></td>
              </tr>

              <tr>
                  <td rowspan="3" style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><label>4季度</label></td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">10月份</td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 60px;"  name="M10" class="easyui-textbox " value="${year.m10 }"
                              data-options="multiline:true " /></td>
                  <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                             name="M10_2" class="easyui-combobox " value="${year.m10_2 }"
                             data-options="panelHeight:'auto', editable:false, valueField: 'text',textField: 'text',data: [
								        {value:'单个教练',text:'单个教练'},
								        {value:'分组训练',text:'分组训练'},
								        {value:'集体合练',text:'集体合练'}] " /></td>
                  <td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                              name="M10_3" class="easyui-textbox " value="${year.m10_3 }"
                              data-options="validType:'length[0,25]'" /></td>
              </tr>

              <tr>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">11月份</td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 60px;"  name="M11" class="easyui-textbox " value="${year.m11 }"
                              data-options="multiline:true " /></td>
                  <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                             name="M11_2" class="easyui-combobox " value="${year.m11_2 }"
                             data-options="panelHeight:'auto', editable:false, valueField: 'text',textField: 'text',data: [
								        {value:'单个教练',text:'单个教练'},
								        {value:'分组训练',text:'分组训练'},
								        {value:'集体合练',text:'集体合练'}] " /></td>
                  <td style="text-align: center;width: 100%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                              name="M11_3" class="easyui-textbox " value="${year.m11_3 }"
                              data-options="validType:'length[0,25]'" /></td>
              </tr>

              <tr>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">12月份</td>
                  <td style="text-align: center;width: 10%;height: 30px;background: #f5f5f5;">
                      <input style="width: 100%;height: 60px;"  name="M12" class="easyui-textbox " value="${year.m12 }"  data-options="multiline:true " />
                  </td>
                  <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">
                      <input style="width: 100%;"
                             name="M12_2" class="easyui-combobox " value="${year.m12_2 }"
                             data-options="panelHeight:'auto', editable:false, valueField: 'text',textField: 'text',data: [
								        {value:'单个教练',text:'单个教练'},
								        {value:'分组训练',text:'分组训练'},
								        {value:'集体合练',text:'集体合练'}] "  /></td>
                  <td style="text-align: center;width: 53%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                             name="M12_3" class="easyui-textbox " value="${year.m12_3 }"
                             data-options="validType:'length[0,25]'" /></td>
              </tr>
          </table>
         <table id="bqhtable2" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
             <tr>
                 <td colspan="2" style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">要求</td>
                 <td colspan="2" style="text-align: center;height: 30px;background: #f5f5f5;">
                     <input style="width: 100%;height: 60px;"  name="M13" class="easyui-textbox " value="${year.m13 }"
                            data-options="multiline:true " />
                 </td>
             </tr>
         </table>
         <table id="bqhtable3" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
             <tr>
                 <td style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">制表人：</td>
                 <td style="text-align: center;width: 30%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                                                                                                     name="M14" class="easyui-textbox " value="${year.m14 }"
                                                                                                     data-options="validType:'length[0,25]'" /></td>
                 <td  style="text-align: center;width: 20%;height: 30px;background: #f5f5f5;">批准人：</td>
                 <td style="text-align: center;width: 30%;height: 30px;background: #f5f5f5;"><input style="width: 100%;height: 30px;"
                                                                                                     name="M15" class="easyui-textbox " value="${year.m15 }"
                                                                                                     data-options="validType:'length[0,25]'" /></td>
             </tr>

             <c:if test="${not empty year.id}">
                 <input type="hidden" name="ID" value="${year.id}" />
                 <input type="hidden" name="qyid" value="${year.qyid}" />
                 <input type="hidden" name="S3" value="${year.s3}" />
             </c:if>
         </table>
     </form>
     <script type="text/javascript">
        var usertype = ${usertype};
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        var validateForm;

        function doSubmit() {
            $("#inputForm").submit();
        }

        $(function() {
            if('${action}'=='create'){
                var date=new Date();
                $('#S1').datebox('setValue', new Date().format("yyyy-MM-dd"));
            }

            var flag = true;
            $('#inputForm').form({
                onSubmit : function() {
                    var isValid = $(this).form('validate');
                    if (isValid && flag) {
                        //$('#M3').combobox('setValue', $('#M3').combobox('getText'));
                        flag = false;
                        $.jBox.tip("正在提交，请稍等...", 'loading', {
                            opacity : 0
                        });
                        return true;
                    }
                    return false; // 返回false终止表单提交
                },
                success : function(data) {
                    $.jBox.closeTip();
                    if (data == 'success')
                        parent.layer.open({
                            icon : 1,
                            title : '提示',
                            offset : 'rb',
                            content : '操作成功！',
                            shade : 0,
                            time : 2000
                        });
                    else
                        parent.layer.open({
                            icon : 2,
                            title : '提示',
                            offset : 'rb',
                            content : '操作失败！',
                            shade : 0,
                            time : 2000
                        });
                    parent.dg.datagrid('reload');
                    parent.layer.close(index);//关闭对话框。
                }
            });

        });
	 </script>
</body>
</html>