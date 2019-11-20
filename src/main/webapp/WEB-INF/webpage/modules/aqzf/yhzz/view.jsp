<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>延期申请管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<div class="easyui-accordion" id="accordion" border="true" data-options="multiple:true" style="padding-right: 10px">
    <div id="basicinfo" title="检查记录" border="false">
        <table class="table table-bordered  table-condensed dataTables-example dataTable ">
            <tbody>
            <tr>
                <td class="width-15 active"><label class="pull-right">检查编号：</label></td>
                <td class="width-35" colspan="3">${record.m0 }</td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right">被检查单位：</label></td>
                <td class="width-35" colspan="3">${record.qyname }</td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right">地址：</label></td>
                <td class="width-35" colspan="3">
                    ${record.m1 }
                </td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right">法定代表人（负责人）：</label></td>
                <td class="width-35">${record.m2 }</td>
                <td class="width-15 active"><label class="pull-right">职务：</label></td>
                <td class="width-35">${record.m3 }</td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
                <td class="width-35">${record.m4 }</td>
                <td class="width-15 active"><label class="pull-right">检查场所：</label></td>
                <td class="width-35">${record.m5 }</td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right">检查时间起：</label></td>
                <td class="width-35">${record.m6 }</td>
                <td class="width-15 active"><label class="pull-right">检查时间止：</label></td>
                <td class="width-35">${record.m7 }</td>
            </tr>

            <tr>
                <td class="width-15 active"><label class="pull-right">检查人员：</label></td>
                <td class="width-35" colspan="3">${record.m8 }</td>
            </tr>
            <input type="hidden" name="ID1" value="${record.id}">

            </tbody>
        </table>

    </div>
    <c:forEach var="hidden" items="${hiddens}" varStatus="status">
        <div title="<c:if test="${hidden.id2 !=0 }">${hidden.jcdy }--${hidden.jcnr }</c:if>
                    <c:if test="${hidden.id2 ==0 }">其他问题</c:if>" border="false"
             id="compare"
             style="height: 440px">
            <div class="easyui-layout" fit="true" id="layoutt">
                <div id="center" data-options="region:'center'" selected="true" collapsible="true">
                    <table id="comparetable" class="table table-bordered  dataTables-example dataTable no-footer"
                           style="width:100%;">
                        <tbody>
                        <tr>
                            <td class="width-15 active"><label class="pull-right">隐患名称：</label></td>
                            <td class="width-3 active text-center " colspan="3">
                                <input name="name" class="easyui-textbox" value="${hidden.name }"
                                       style="width: 100%;height: 30px;" data-options=" readonly:'true' "/>
                            </td>
                        </tr>
                        <tr>
                            <td class="width-25 active text-center " colspan="2">整改前
                            </td>
                            <td class="width-25 active text-center " style="color: red" colspan="2">整改后
                            </td>
                        </tr>
                        <tr>
                            <td class="width-15 active"><label class="pull-right">检查情况：</label></td>
                            <td class="width-35">${hidden.condi }</td>
                            <td class="width-15 active"><label class="pull-right">整改人：</label></td>
                            <td class="width-35">
                                <input name="person1" class="easyui-textbox" value="${hidden.person1 }"
                                       style="width: 100%;height: 30px;" data-options=" required:'true' "/>
                            </td>
                        </tr>
                        <tr>
                            <td class="width-15 active"><label class="pull-right">需整改的问题：</label></td>
                            <td class="width-35" height="80px">${hidden.que }</td>
                            <td class="width-15 active"><label class="pull-right">负责人：</label></td>
                            <td class="width-35">
                                <input name="person2" class="easyui-textbox" value="${hidden.person2 }"
                                       style="width: 100%;height: 30px;" data-options=" required:'true' "/>
                            </td>
                        </tr>
                        <tr>
                            <td class="width-15 active"><label class="pull-right">涉嫌违法行为：</label></td>
                            <td class="width-35"><input type="text" class="easyui-combobox"
                                                        value="${hidden.unlaw}"
                                                        style="width: 100%;height: 50px"
                                                        data-options="multiline:true,valueField: 'id',textField:
                                                        'text',url:'${ctx}/aqzf/wfxwtwo/idlist'"/>
                            </td>
                            <td class="width-15 active"><label class="pull-right">整改时限(天)：</label></td>
                            <td class="width-35">
                                <input class="easyui-numberspinner" name="time" value="${hidden.time}"
                                       data-options="increment:1"
                                       style="width:100%;height: 30px">
                            </td>
                        </tr>

                        <tr>
                            <td class="width-15 active"><label class="pull-right">整改前图片：</label></td>
                            <td class="width-35">
                                <c:if test="${not empty hidden.pic}">
                                    <c:set var="url" value="${fn:split(hidden.pic, '||')}"/>
                                    <div style="margin-bottom: 10px;">
                                        <a href="${url[0]}" target="_blank"><img style="width:100px;height: 100px;"
                                                                                 src="${url[0]}"></a>
                                    </div>
                                </c:if>
                            </td>
                            <td class="width-15 active"><label class="pull-right">整改后图片：</label></td>
                            <td class="width-35">
                                <c:if test="${not empty hidden.pic2}">
                                    <c:set var="url" value="${fn:split(hidden.pic2, '||')}"/>
                                    <div style="margin-bottom: 10px;">
                                        <a href="${url[0]}" target="_blank"><img style="width:100px;height: 100px;"
                                                                                 src="${url[0]}"></a>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:forEach>

</div>


<%--<script type="text/javascript">
    $('#accordion').accordion('select', "隐患整治");
</script>--%>
</body>
</html>