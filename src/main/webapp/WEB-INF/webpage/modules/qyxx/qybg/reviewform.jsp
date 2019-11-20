<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>企业变更</title>
<meta name="decorator" content="default" />
<style type="text/css">
/* 设置滚动条的样式 */
::-webkit-scrollbar {
width:12px;
}
/* 滚动槽 */
::-webkit-scrollbar-track {
-webkit-box-shadow:inset006pxrgba(0,0,0,0.3);
border-radius:10px;
}
/* 滚动条滑块 */
::-webkit-scrollbar-thumb {
border-radius:10px;
background:rgba(0,0,0,0.1);
-webkit-box-shadow:inset006pxrgba(0,0,0,0.5);
}
::-webkit-scrollbar-thumb:window-inactive {
background:rgba(255,0,0,0.4);
}
    .textred{color: red}
    .hidden{display: none}
</style>
</head>
<body style="height: 100%">

   <div class="easyui-accordion" id="accordion" border="true" data-options="multiple:true" style="padding-right: 10px">
      <div id="basicinfo" title="基本信息" border="false">
         <form id="inputForm" action="${ctx }/bis/qybg/${action}" method="post" class="form-horizontal">
            <table class="table table-bordered  table-condensed dataTables-example dataTable ">
               <tbody>
                  <tr>
                     <td class="width-15 active"><label class="pull-right">企业名称：</label></td>
                     <td class="width-35"><div>
                           <input value="${entity.qyid }" style="width: 100%;height: 30px;" class="easyui-combobox"
                              data-options="readonly: true,required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
                        </div></td>

                     <td class="width-15 active"><label class="pull-right">变更事项：</label></td>
                     <td class="width-35"><input style="width: 100%;height: 30px; " class="easyui-combobox"
                           value="${entity.modification }"
                           data-options="valueField: 'value',textField: 'text',panelHeight:'auto',readonly: true,
                     data:[{text:'关停增减',value:'关停增减'}, {text:'辖区转移',value:'辖区转移'}, {text:'信息变更',value:'信息变更'}],
                     validType:['length[0,10]']" /></td>
                  </tr>
                  <tr>
                     <td class="width-15 active"><label class="pull-right">变更内容：</label></td>
                     <td class="width-35" colspan="3"><input style="width: 100%;height: 50px;" class="easyui-textbox"
                           value="${entity.content }" data-options="readonly: true,multiline: true, validType:['length[0,250]']" /></td>
                  </tr>
                  <tr>
                     <td class="width-15 active"><label class="pull-right">申请人：</label></td>
                     <td class="width-35"><input style="width: 100%;height: 30px;" class="easyui-textbox"
                           value="${entity.applyer }" data-options="readonly: true, validType:['length[0,25]']" /></td>
                     <c:if test="${action eq 'review' }">
                        <td class="width-15 active"><label class="pull-right">审核人：</label></td>
                        <td class="width-35"><input name="reviewer" id="reviewer" style="width: 100%;height: 30px;" class="easyui-textbox"
                              value="${entity.reviewer }" data-options="required: true,validType:['length[0,25]']" /></td>
                  </tr>
                  <c:if test="${isview ne 'yes'}">
                  <tr>
                     <td class="width-15 active"><label class="pull-right">审核结果：</label></td>
                     <td class="width-35"><input type='radio' name='result' value='1' class='i-checks' checked='checked' />通过 <input
                           type='radio' name='result' value='0' class='i-checks' />未通过 </c:if>
                  </tr>
                  </c:if>
                  <c:if test="${isview eq 'yes'}">
                  <tr>
                      <td class="width-15 active"><label class="pull-right">审核结果：</label></td>
                      <td class="width-35" colspan="3">
                          <input type='radio' name='result' value='1' class='i-checks' <c:if test="${entity.result eq 1 }">  checked='checked'</c:if> />通过
                          <input type='radio' name='result' value='0' class='i-checks' <c:if test="${entity.result eq 0 }">  checked='checked'</c:if> />未通过
                          <input type='radio' name='result' value='' class='i-checks' <c:if test="${empty entity.result }">  checked='checked'</c:if> />未审核
                  </tr>
                  </c:if>
                     <input type="hidden" name="ID" value="${entity.ID}" />
               </tbody>
            </table>
         </form>
      </div>
      <div title="企业信息比较" border="false" id="compare" style="height: 280px">
         <div class="easyui-layout" fit="true" id="layoutt" >
            <div id="center" data-options="region:'center'" collapsible="true">
               <table id="comparetable" class="table table-bordered  dataTables-example dataTable no-footer" style="width:100%;">
                  <tbody>
                  <tr>
                      <td class="width-30 active"><label class="pull-right">变更条目</label>
                      </td>
                      <td class="width-35 active text-center" >变更前
                      </td>
                      <td class="width-35 active text-center ">变更后
                      </td>
                  </tr>
                  <tr class="hidden">
                      <td class="width-30 active" id="m1"><label class="pull-right">
                          企业名称：</label>
                      </td>
                      <td class="width-35 text-center ">${qylist.m1 }</td>
                      <td class="width-35 text-center textred">${qylistnew.m1 }</td>
                  </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m39"><label class="pull-right">
                           是否构成重大危险源：
                       </label></td>
                       <td class="width-35 text-center"><c:if test="${qylist.m39 eq 0 }">否</c:if> <c:if
                               test="${qylist.m39 eq 1 }">是</c:if></td>
                       <td class="width-35 text-center textred"><c:if test="${qylistnew.m39 eq 0 }">否</c:if> <c:if
                               test="${qylistnew.m39 eq 1 }">是</c:if></td>
                   </tr>
                   <tr class="hidden">
                       <td id="m40" class="width-30 active"><label class="pull-right">
                         重大危险源等级：
                       </label></td>
                       <td class="width-35 text-center"><c:choose>
                           <c:when test="${qylist.m40  eq '1'}">一级</c:when>
                           <c:when test="${qylist.m40  eq '2'}">二级</c:when>
                           <c:when test="${qylist.m40  eq '3'}">三级</c:when>
                           <c:when test="${qylist.m40  eq '4'}">四级</c:when>
                       </c:choose></td>
                       <td class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m40  eq '1'}">一级</c:when>
                           <c:when test="${qylistnew.m40  eq '2'}">二级</c:when>
                           <c:when test="${qylistnew.m40  eq '3'}">三级</c:when>
                           <c:when test="${qylistnew.m40  eq '4'}">四级</c:when>
                       </c:choose></td>
                   </tr>
                   
                   <tr class="hidden">
                       <td class="width-30 active" id="m50"><label class="pull-right">是否为重点消防单位：</label></td>
                       <td class="width-35 text-center"><c:choose>
                           <c:when test="${qylist.m50  eq '0'}">否</c:when>
                           <c:when test="${qylist.m50  eq '1'}">是</c:when>
                       </c:choose></td>
                       
                       <td class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m50  eq '0'}">否</c:when>
                           <c:when test="${qylistnew.m50  eq '1'}">是</c:when>
                       </c:choose></td>
                   </tr>
                   
                   <tr class="hidden">
                       <td class="width-30 active" id="m51"><label class="pull-right">是否为厂中厂：</label></td>
                       <td class="width-35 text-center"><c:choose>
                           <c:when test="${qylist.m51  eq '0'}">否</c:when>
                           <c:when test="${qylist.m51  eq '1'}">是</c:when>
                       </c:choose></td>
                       
                       <td class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m51  eq '0'}">否</c:when>
                           <c:when test="${qylistnew.m51  eq '1'}">是</c:when>
                       </c:choose></td>
                   </tr>
                   
                   <tr class="hidden">
                       <td class="width-30 active" id="m52"><label class="pull-right">是否为三合一：</label></td>
                       <td class="width-35 text-center"><c:choose>
                           <c:when test="${qylist.m52  eq '0'}">否</c:when>
                           <c:when test="${qylist.m52  eq '1'}">是</c:when>
                       </c:choose></td>
                       
                       <td class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m52  eq '0'}">否</c:when>
                           <c:when test="${qylistnew.m52  eq '1'}">是</c:when>
                       </c:choose></td>
                   </tr>

                   <tr class="hidden">
                       <td class="width-30 active" id="m44"><label class="pull-right">企业风险等级：</label></td>
                       <td class="width-35 text-center"><c:choose>
                           <c:when test="${qylist.m44  eq '1'}">红</c:when>
                           <c:when test="${qylist.m44  eq '2'}">橙</c:when>
                           <c:when test="${qylist.m44  eq '3'}">黄</c:when>
                           <c:when test="${qylist.m44  eq '4'}">蓝</c:when>
                       </c:choose></td>
                       
                       <td class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m44  eq '1'}">红</c:when>
                           <c:when test="${qylistnew.m44  eq '2'}">橙</c:when>
                           <c:when test="${qylistnew.m44  eq '3'}">黄</c:when>
                           <c:when test="${qylistnew.m44  eq '4'}">蓝</c:when>
                       </c:choose></td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m34"><label class="pull-right">规模情况：</label></td>
                       <td class="width-35 text-center"><c:choose>
                           <c:when test="${qylist.m34  eq '1'}">规上</c:when>
                           <c:when test="${qylist.m34  eq '2'}">规下</c:when>
                           <c:when test="${qylist.m34  eq '3'}">小微</c:when>
                       </c:choose></td>
                       
                       <td class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m34  eq '1'}">规上</c:when>
                           <c:when test="${qylistnew.m34  eq '2'}">规下</c:when>
                           <c:when test="${qylistnew.m34  eq '3'}">小微</c:when>
                       </c:choose></td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m36_1"><label class="pull-right">安全监管等级：</label></td>
                       <td class="width-35 text-center"><c:choose>
                           <c:when test="${qylist.m36_1 eq '1' }">A</c:when>
                           <c:when test="${qylist.m36_1  eq '2' }">B</c:when>
                           <c:when test="${qylist.m36_1  eq '3' }">C</c:when>
                           <c:when test="${qylist.m36_1  eq '4' }">D</c:when>
                           <c:when test="${qylist.m36_1  eq '5' }">未定级</c:when>
                           <c:otherwise></c:otherwise>
                       </c:choose></td>
                       
                       <td class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m36_1 eq '1' }">A</c:when>
                           <c:when test="${qylistnew.m36_1  eq '2' }">B</c:when>
                           <c:when test="${qylistnew.m36_1  eq '3' }">C</c:when>
                           <c:when test="${qylistnew.m36_1  eq '4' }">D</c:when>
                           <c:when test="${qylistnew.m36_1  eq '5' }">未定级</c:when>
                           <c:otherwise></c:otherwise>
                       </c:choose></td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m8_6"><label class="pull-right">属地安监机构：</label></td>
                       <td class="width-35 text-center">${qylist.m8_6 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m8_6 }</td>
                   </tr>
                   
                   <tr class="hidden">
                       <td class="width-30 active" id="m36"><label class="pull-right">监管分类：</label></td>
                       <td class="width-35 text-center">
                           <c:forTokens items="${qylist.m36}" delims="," var="m">
                       	   	   <c:if test="${m  eq '11'}">安监</c:if>
                       	   	   <c:if test="${m  eq '1'}">投资</c:if>
	                           <c:if test="${m  eq '2'}">企管</c:if>
	                           <c:if test="${m  eq '3'}">科技</c:if>
	                           <c:if test="${m  eq '4'}">环保</c:if>
	                           <c:if test="${m  eq '5'}">建筑</c:if>
	                           <c:if test="${m  eq '6'}">消防</c:if>
	                           <c:if test="${m  eq '7'}">燃气</c:if>
	                           <c:if test="${m  eq '8'}">商贸</c:if>
	                           <c:if test="${m  eq '9'}">卫生</c:if>
	                           <c:if test="${m  eq '10'}">其他</c:if>
                       	   </c:forTokens>
                 	   </td>
                       
                       <td class="width-35 text-center textred">
                       	   <c:forTokens items="${qylistnew.m36}" delims="," var="m">
                       	   	   <c:if test="${m  eq '11'}">安监</c:if>
                       	   	   <c:if test="${m  eq '1'}">投资</c:if>
	                           <c:if test="${m  eq '2'}">企管</c:if>
	                           <c:if test="${m  eq '3'}">科技</c:if>
	                           <c:if test="${m  eq '4'}">环保</c:if>
	                           <c:if test="${m  eq '5'}">建筑</c:if>
	                           <c:if test="${m  eq '6'}">消防</c:if>
	                           <c:if test="${m  eq '7'}">燃气</c:if>
	                           <c:if test="${m  eq '8'}">商贸</c:if>
	                           <c:if test="${m  eq '9'}">卫生</c:if>
	                           <c:if test="${m  eq '10'}">其他</c:if>
                       	   </c:forTokens>
                       </td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m10"><label class="pull-right">
                           经济类型：
                       </label></td>
                       <td class="width-35 text-center"><input class="easyui-combobox" value="${qylist.m10 }"
                                                   style="width:220px;height:30px;"
                                                   data-options="readonly:'true',valueField: 'id',textField: 'text',url:'${ctx}/tcode/dict/jjlx' " /></td>
                       <td class="width-35 text-center textred"><input class="easyui-combobox" value="${qylistnew.m10 }"
                                                   style="width:220px;height:30px;"
                                                   data-options="readonly:'true',valueField: 'id',textField: 'text',url:'${ctx}/tcode/dict/jjlx' " /></td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="ID2"><label class="pull-right">
                           所属网格：
                       </label></td>
                       <td class="width-35 text-center"><input class="easyui-combotree" value="${qylist.ID2 }"
                                                   style="width:220px;height:30px;"
                                                   data-options="readonly:'true',valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson' " /></td>
                       <td class="width-35 text-center textred"><input class="easyui-combotree" value="${qylistnew.ID2 }"
                                                   style="width:220px;height:30px;"
                                                   data-options="readonly:'true',valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson' " /></td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m53"><label class="pull-right">所属行业：</label></td>
                       <td class="width-35 text-center ">${qylist.m53 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m53 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m12"><label class="pull-right">行业类别：</label></td>
                       <td class="width-35 text-center "><input class="easyui-combotree" name="M12" value="${qylist.m12 }"
                                                   style="width:220px;height:30px;"
                                                   data-options="readonly:'true',method: 'get',url:'${ctx}/tcode/gbt/json'"/>
                       </td>
                       
                       <td class="width-35 text-center textred"><input class="easyui-combotree" name="M12" value="${qylistnew.m12 }"
                                                   style="width:220px;height:30px;"
                                                   data-options="readonly:'true',method: 'get',url:'${ctx}/tcode/gbt/json'"/>
                       </td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m13"><label class="pull-right">
                           企业行政隶属关系：
                       </label></td>
                       <td class="width-35 text-center "><c:choose>
                           <c:when test="${qylist.m13  eq '1'}">中央级</c:when>
                           <c:when test="${qylist.m13  eq '2'}">省级</c:when>
                           <c:when test="${qylist.m13  eq '3'}">市、地区级</c:when>
                           <c:when test="${qylist.m13  eq '4'}">县级</c:when>
                           <c:when test="${qylist.m13  eq '5'}">街道、镇、乡级</c:when>
                           <c:when test="${qylist.m13  eq '6'}">街道</c:when>
                           <c:when test="${qylist.m13  eq '7'}">镇</c:when>
                           <c:when test="${qylist.m13  eq '8'}">乡</c:when>
                           <c:when test="${qylist.m13  eq '9'}">其他</c:when>
                       </c:choose></td>
                       <td class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m13  eq '1'}">中央级</c:when>
                           <c:when test="${qylistnew.m13  eq '2'}">省级</c:when>
                           <c:when test="${qylistnew.m13  eq '3'}">市、地区级</c:when>
                           <c:when test="${qylistnew.m13  eq '4'}">县级</c:when>
                           <c:when test="${qylistnew.m13  eq '5'}">街道、镇、乡级</c:when>
                           <c:when test="${qylistnew.m13  eq '6'}">街道</c:when>
                           <c:when test="${qylistnew.m13  eq '7'}">镇</c:when>
                           <c:when test="${qylistnew.m13  eq '8'}">乡</c:when>
                           <c:when test="${qylistnew.m13  eq '9'}">其他</c:when>
                       </c:choose></td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m15"><label class="pull-right">
                           企业状态：
                       </label></td>
                       <td class="width-35 text-center "><c:choose>
                           <c:when test="${qylist.m15  eq '1'}">有效</c:when>
                           <c:when test="${qylist.m15  eq '2'}">无效</c:when>
                       </c:choose></td>
                       <td class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m15  eq '1'}">有效</c:when>
                           <c:when test="${qylistnew.m15  eq '2'}">无效</c:when>
                       </c:choose></td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m18"><label class="pull-right">标准化达标等级：</label></td>
                       <td class="width-35 text-center "><c:choose>
                           <c:when test="${qylist.m18  eq '1'}">一级</c:when>
                           <c:when test="${qylist.m18  eq '2'}">二级</c:when>
                           <c:when test="${qylist.m18  eq '3'}">三级</c:when>
                           <c:when test="${qylist.m18  eq '4'}">达标</c:when>
                           <c:when test="${qylist.m18  eq '5'}">未创建</c:when>
                       </c:choose></td>
                       
                       <td class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m18  eq '1'}">一级</c:when>
                           <c:when test="${qylistnew.m18  eq '2'}">二级</c:when>
                           <c:when test="${qylistnew.m18  eq '3'}">三级</c:when>
                           <c:when test="${qylistnew.m18  eq '4'}">达标</c:when>
                           <c:when test="${qylistnew.m18  eq '5'}">未创建</c:when>
                       </c:choose></td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m27"><label class="pull-right">
                          安全机构设置情况：
                       </label></td>
                       <td  class="width-35 text-center"><c:choose>
                           <c:when test="${qylist.m27  eq '1'}">是</c:when>
                           <c:when test="${qylist.m27  eq '2'}">否</c:when>
                       </c:choose> </td>
                       <td  class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m27  eq '1'}">是</c:when>
                           <c:when test="${qylistnew.m27  eq '2'}">否</c:when>
                       </c:choose> </td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m47"><label class="pull-right">
                           是否涉及重点监管危化品：
                       </label></td>
                       <td class="width-35 text-center "><c:choose>
                           <c:when test="${qylist.m47  eq '1'}">是</c:when>
                           <c:when test="${qylist.m47  eq '0'}">否</c:when>
                       </c:choose></td>
                       <td class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m47  eq '1'}">是</c:when>
                           <c:when test="${qylistnew.m47  eq '0'}">否</c:when>
                       </c:choose></td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m48"><label class="pull-right">
                           是否涉及高危工艺：
                       </label></td>
                       <td class="width-35 text-center "><c:choose>
                           <c:when test="${qylist.m48  eq '1'}">是</c:when>
                           <c:when test="${qylist.m48  eq '0'}">否</c:when>
                       </c:choose></td>
                       <td class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m48  eq '1'}">是</c:when>
                           <c:when test="${qylistnew.m48  eq '0'}">否</c:when>
                       </c:choose></td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m49"><label class="pull-right">
                           是否涉及剧毒品生产或使用：
                       </label></td>
                       <td class="width-35 text-center "><c:choose>
                           <c:when test="${qylist.m49  eq '1'}">是</c:when>
                           <c:when test="${qylist.m49  eq '0'}">否</c:when>
                       </c:choose></td>
                       <td class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m49  eq '1'}">是</c:when>
                           <c:when test="${qylistnew.m49  eq '0'}">否</c:when>
                       </c:choose></td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m2_1"><label class="pull-right">
                           营业执照类别：
                       </label></td>
                       <td class="width-35 text-center ">
                           ${qylist.m2_1}</td>
                       <td class="width-35 text-center textred">${qylist.m2_1}</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m46"><label class="pull-right">是否位于化工集中区：</label></td>
                       <td class="width-35 text-center "><c:choose>
                           <c:when test="${qylist.m46  eq '1'}">是</c:when>
                           <c:when test="${qylist.m46  eq '0'}">否</c:when>
                       </c:choose></td>
                       
                       <td class="width-35 text-center textred"><c:choose>
                           <c:when test="${qylistnew.m46  eq '1'}">是</c:when>
                           <c:when test="${qylistnew.m46  eq '0'}">否</c:when>
                       </c:choose></td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m46_1"><label class="pull-right">化工集中区名称：</label></td>
                       <td class="width-35 text-center">${qylist.m46_1 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m46_1 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m1_1"><label class="pull-right">母公司名称：</label></td>
                       <td class="width-35 text-center">${qylist.m1_1 }</td>
                       </td>
                       <td class="width-35 text-center textred">${qylistnew.m1_1 }</td>
                       </td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m1_2"><label class="pull-right">集团公司名称：</label></td>
                       <td class="width-35 text-center">${qylist.m1_2 }</td>
                       </td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m1_2 }</td>
                       </td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m8_1"><label class="pull-right">注册资金(万元)：</label></td>
                       <td class="width-35 text-center ">${qylist.m8_1 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m8_1 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m8_2"><label class="pull-right">年销售收入(万元)：</label></td>
                       <td class="width-35 text-center ">${qylist.m8_2 }</td>
                       <td class="width-35 text-center textred">${qylistnew.m8_2 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m8_3"><label class="pull-right">年利润(万元)：</label></td>
                       <td class="width-35 text-center ">${qylist.m8_3 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m8_3 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m8_4"><label class="pull-right">资产总额（万元）：</label></td>
                       <td class="width-35 text-center ">${qylist.m8_4 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m8_4 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m8"><label class="pull-right">注册地址：</label></td>
                       <td class="width-35 text-center">${qylist.m8 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m8 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m3_1"><label class="pull-right">统一社会信用代码：</label></td>
                       <td class="width-35 text-center">${qylist.m3_1 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m3_1 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m8_5"><label class="pull-right">占地面积（㎡）：</label></td>
                       <td class="width-35 text-center">${qylist.m8_5 }</td>
                       <td class="width-35 text-center textred">${qylistnew.m8_5 }</td>
                   </tr>
                   <tr class="hidden">
                       
                       <td class="width-30 active" id="m33"><label class="pull-right">企业实际生产/经营地址：</label></td>
                       <td class="width-35 text-center ">${qylist.m33 }</td>
                       <td class="width-35 text-center textred">${qylistnew.m33 }</td>
                       </td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m33_1"><label class="pull-right">企业官方网站地址：</label></td>
                       <td class="width-35 text-center">${qylist.m33_1 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m33_1 }</td>
                       </td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m4"><label class="pull-right">成立时间：</label></td>
                       <td class="width-35 text-center ">${qylist.m4}</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m4}</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m5"><label class="pull-right">
                           法定代表人：
                       </label></td>
                       <td class="width-35 text-center ">${qylist.m5 }</td>
                       <td class="width-35 text-center textred">${qylistnew.m5 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m11"><label class="pull-right">
                           行政区域：
                       </label></td>
                       <td class="width-35 text-center">
                           <div>
                               ${qylist.m11 }
                               <label style="margin-left: 10px;" for="M11">省</label>
                               ${qylist.m11_1 }
                               <label style="margin-left: 10px;" for="M11_1">市</label>
                               ${qylist.m11_2 }
                               <label style="margin-left: 10px;" for="M11_2">区</label>
                               ${qylist.m11_3 }
                               <label style="margin-left: 10px;" for="M11_3">镇或街道</label>
                           </div>
                       </td>
                       <td class="width-35 text-center textred">
                           <div>
                               ${qylistnew.m11 }
                               <label style="margin-left: 10px;" for="M11">省</label>
                               ${qylistnew.m11_1 }
                               <label style="margin-left: 10px;" for="M11_1">市</label>
                               ${qylistnew.m11_2 }
                               <label style="margin-left: 10px;" for="M11_2">区</label>
                               ${qylistnew.m11_3 }
                               <label style="margin-left: 10px;" for="M11_3">镇或街道</label>
                           </div>
                       </td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m6_1"><label class="pull-right">
                           联系人：
                       </label></td>
                       <td class="width-35 text-center ">${qylist.m6_1 }</td>
                       <td class="width-35 text-center textred">${qylistnew.m6_1 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m6"><label class="pull-right">
                           联系电话：
                       </label></td>
                       <td class="width-35 text-center">${qylist.m6 }</td>
                       <td class="width-35 text-center textred">${qylistnew.m6 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m6_2"><label class="pull-right">联系微信号：</label></td>
                       <td class="width-35 text-center">${qylist.m6_2 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m6_2 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m6_3"><label class="pull-right">联系QQ号：</label></td>
                       <td class="width-35 text-center">${qylist.m6_3 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m6_3 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m7"><label class="pull-right">电子邮件：</label></td>
                       <td class="width-35 text-center">${qylist.m7 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m7 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m33_2"><label class="pull-right">单位传真：</label></td>
                       <td class="width-35 text-center">${qylist.m33_2 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m33_2 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active"><label class="pull-right">
                           企业位置：
                       </label></td>
                       <td  class="width-35 text-center "><label
                               id="m16">经度：</label>${qylist.m16 }<label id="m17"> 纬度：</label> ${qylist.m17 }
                       <td  class="width-35 text-center textred"><label
                               id="m16">经度：</label>${qylistnew.m16 }<label id="m17"> 纬度：</label> ${qylistnew.m17 }
                   </tr>

                   <tr class="hidden">
                       <td class="width-30 active" id="m19"><label class="pull-right">主要负责人：</label></td>
                       <td class="width-35 text-center">${qylist.m19 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m19 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m20"><label class="pull-right">主要负责人固定电话：</label></td>
                       <td class="width-35 text-center">${qylist.m20 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m20 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m21"><label class="pull-right">主要负责人移动电话：</label></td>
                       <td class="width-35 text-center">${qylist.m21 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m21 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m22"><label class="pull-right">主要负责人电子邮箱：</label></td>
                       <td class="width-35 text-center">${qylist.m22 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m22 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m23"><label class="pull-right">安全负责人：</label></td>
                       <td class="width-35 text-center">${qylist.m23 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m23 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m24"><label class="pull-right">安全负责人固定电话：</label></td>
                       <td class="width-35 text-center">${qylist.m24 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m24 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m25"><label class="pull-right">安全负责人移动电话：</label></td>
                       <td class="width-35 text-center">${qylist.m25 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m25 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m26"><label class="pull-right">安全负责人电子邮箱：</label></td>
                       <td class="width-35 text-center">${qylist.m26 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m26 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m9"><label class="pull-right">邮政编码：</label></td>
                       <td class="width-35 text-center">${qylist.m9 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m9 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m28"><label class="pull-right">从业人员数量：</label></td>
                       <td class="width-35 text-center">${qylist.m28 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m28 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m29"><label class="pull-right">特种作业人员数量：</label></td>
                       <td class="width-35 text-center">${qylist.m29 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m29 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m30"><label class="pull-right">专职安全生产管理人员数：</label></td>
                       <td class="width-35 text-center">${qylist.m30 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m30 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m31"><label class="pull-right">专职应急管理人员数：</label></td>
                       <td class="width-35 text-center">${qylist.m31 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m31 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m30_1"><label class="pull-right">兼职安全生产管理人员数：</label></td>
                       <td class="width-35 text-center">${qylist.m30_1 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m30_1 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m32"><label class="pull-right">注册安全工程师人员数：</label></td>
                       <td class="width-35 text-center">${qylist.m32 }</td>
                       
                       <td class="width-35 text-center textred">${qylistnew.m32 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m37"><label class="pull-right">隐患排查治理制度（单文件）：</label></td>
                       <td class="width-35 text-center "><c:if test="${not empty qylist.m37}">
                           <c:set var="url" value="${fn:split(qylist.m37, '||')}"/>
                           <div style="margin-bottom: 10px;">
                               <a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;"
                                  onclick="window.open('${url[0]}')">${url[1]}</a>
                           </div>
                       </c:if></td>
                       <td class="width-35 text-center textred"><c:if test="${not empty qylistnew.m37}">
                           <c:set var="url" value="${fn:split(qylistnew.m37, '||')}"/>
                           <div style="margin-bottom: 10px;">
                               <a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;"
                                  onclick="window.open('${url[0]}')">${url[1]}</a>
                           </div>
                       </c:if></td>
                   </tr>
                  <tr class="hidden">
                      <td class="width-30 active" id="m38"><label class="pull-right">隐患排查治理计划（单文件）：</label></td>
                      <td class="width-35 text-center"><c:if test="${not empty qylist.m38}">
                          <c:set var="url" value="${fn:split(qylist.m38, '||')}" />
                          <div style="margin-bottom: 10px;">
                              <a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
                          </div>
                      </c:if></td>
                      <td class="width-35 text-center textred"><c:if test="${not empty qylistnew.m38}">
                          <c:set var="url" value="${fn:split(qylistnew.m38, '||')}" />
                          <div style="margin-bottom: 10px;">
                              <a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
                          </div>
                      </c:if></td>
                  </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m33_3"><label class="pull-right">企业平面图（单文件）：</label></td>
                       <td class="width-35 text-center "><c:if test="${not empty qylist.m33_3}">
                           <c:set var="url" value="${fn:split(qylist.m33_3, '||')}"/>
                           <div style="margin-bottom: 10px;">
                               <a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;"
                                  onclick="window.open('${url[0]}')">${url[1]}</a>
                           </div>
                       </c:if></td>
                       <td class="width-35 text-center textred"><c:if test="${not empty qylistnew.m33_3}">
                           <c:set var="url" value="${fn:split(qylistnew.m33_3, '||')}"/>
                           <div style="margin-bottom: 10px;">
                               <a style="text-decoration:none;cursor:pointer;margin-right:20px;"
                                  onclick="window.open('${url[0]}')">${url[1]}</a>
                           </div>
                       </c:if></td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m14"><label class="pull-right">经营范围：</label></td>
                       <td class="width-35 text-center">${qylist.m14 }</td>
                       <td class="width-35 text-center textred">${qylistnew.m14 }</td>
                   </tr>
                   <tr class="hidden">
                       <td class="width-30 active" id="m45"><label class="pull-right">备注：</label></td>
                       <td class="width-35 text-center">${qylist.m45 }</td>
                       <td class="width-35 text-center textred">${qylistnew.m45 }</td>
                   </tr>
                  </tbody>
               </table>
            </div>
         </div>
      </div>
   </div>


   <script type="text/javascript">
  		var qylistnew = eval(${entity.bisnewjson});
  		var action='${action}';
		//$("#compare").height(700);//设置比较内容高度
		$(function() {
		    if('${username}'){
				$("#reviewer").textbox("setValue",'${username}');
		    }
   		    $('#accordion').accordion('select', "企业信息比较");
		    //属性不同标红
		    for(var val in qylistnew){
		        if(val=="m16"||val=="m17"){
                    $("#"+val).parent().parent().removeClass("hidden");
                }else{
                    $("#"+val).parent().removeClass("hidden");
                }
		    }
		});
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		
		function doSubmit() {
		    confirmx('审核后无法修改，请确定审核信息！', function(tmpindex) {
      			var obj = $("#inputForm").serializeObject();
      	 		$.ajax({
      				type : 'POST',
      				url : "${ctx}/bis/qybg/" + action,
                    data: obj,
      				success : function(data) {
      					$.jBox.closeTip();
      					if (data == 'success') {
      						parent.layer.open({
      							icon : 1,
      							title : '提示',
      							offset : 'rb',
      							content : '审核完成！',
      							shade : 0,
      							time : 2000
      						});
      						parent.dg.datagrid('reload');
      						parent.layer.close(index);//关闭对话框。
      					} else {
      						parent.layer.open({
      							icon : 2,
      							title : '提示',
      							offset : 'rb',
      							content : '操作失败！',
      							shade : 0,
      							time : 2000
      						});
      					}
      				},
      				beforeSend : function() {
      					var isValid = $("#inputForm").form('validate');
      					if (isValid) {
      						$.jBox.tip("正在提交，请稍等...", 'loading', {
      							opacity : 0
      						});
      						return true;
      					}
      					return false; // 返回false终止表单提交
      				}
      			}); 
		    });
		}
	</script>

</body>
</html>