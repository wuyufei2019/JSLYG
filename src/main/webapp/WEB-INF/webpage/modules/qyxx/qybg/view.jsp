<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>企业变更</title>
<meta name="decorator" content="default" />
    <style>  .textred{color: red}</style>
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
               
              <tr>
                  <td class="width-15 active"><label class="pull-right">企业id：</label></td>
   				  <td class="width-35" > <input value="${entity.qyid }" id="qyid" name="qyid"
                                 style="width: 100%;height: 30px;" class="easyui-combobox"
                                 data-options="readonly:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
               
                  <td class="width-15 active"><label class="pull-right">变更事项：</label></td>
   				  <td class="width-35" >${entity.modification}</td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">变更内容：</label></td>
   				  <td class="width-35"  colspan="3" id ="changeinfo"></td>
              </tr>  
              <tr>
                  <td class="width-15 active"><label class="pull-right">申请人：</label></td>
   				  <td class="width-35" >${entity.applyer}</td>
               
                  <td class="width-15 active"><label class="pull-right">审核人：</label></td>
   				  <td class="width-35" >${entity.reviewer}</td>
              </tr>
              <tr>
                  <td class="width-15 active"><label class="pull-right">审核结果：</label></td>
   				  <td class="width-35" ><c:choose>
                 <c:when test="${entity.result eq '1' }">通过</c:when>
                 <c:when test="${entity.result eq '0' }">未通过</c:when>
                 <c:otherwise>未审核</c:otherwise>
                 </c:choose>  </td>
              </tr>
		</tbody>
	</table>
    <script>
        var oldbis = JSON.parse('${entity.bisoldjson}');
        var newbis = JSON.parse('${entity.bisnewjson}');
        $.getJSON("${ctxStatic}/model/js/qyxx/qybg/data.json", function (data) {
            var html = "";
            for (var key in newbis) {
                var tmp =  data[key];
                if(tmp){
                    if (typeof tmp=== 'string') {
                        html += tmp + ":"  + oldbis[key] + "&nbsp;&nbsp;-->&nbsp;&nbsp;<span class='textred'>" + newbis[key] + "</span></br>";
                    }else if (typeof tmp === 'object') {
                        html += tmp[name] + ":"  + tmp.data[oldbis[key]] + "&nbsp;&nbsp;-->&nbsp;&nbsp;<span class='textred'>" + tmp.data[oldbis[key]] + "</span></br>";
                    }
                }
            }
            $("#changeinfo").append(html);
        });


    </script>
</body>
</html>