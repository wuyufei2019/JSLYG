<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>企业变更信息</title>
    <meta name="decorator" content="default"/>
    <style>  .textred {
        color: red
    }</style>
</head>
<body>
<!-- 工具栏 -->
<div id="dg_tb" style="padding:5px;height:auto">
    <form id="searchFrom" style="margin-bottom: 8px;" class="form-inline">
        <div class="form-group">
            <input type="text" name="view_modification" class="easyui-combobox" style="height: 30px;" data-options="valueField: 'text',textField: 'text',panelHeight:'auto',
                   data:[{text:'关停增减'}, {text:'辖区转移'}, {text:'信息变更'}],prompt: '变更事项'"/>
            <input type="text" name="view_result" class="easyui-combobox" style="height: 30px;" data-options="prompt: '审核结果',valueField: 'value',textField: 'text',panelHeight:'auto',
                   data:[{text:'通过',value:'1'}, {text:'未通过',value:'0'}]"/>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i
                    class="fa fa-search"></i> 查询</span> <span
                class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
        </div>
    </form>
</div>

<table id="table_dg"></table>

<script type="text/javascript">
    var Gdata;
    $.getJSON("${ctxStatic}/model/js/qyxx/qybg/data.json", function (data) {
        Gdata = data;
    });

    var dg = $('#table_dg').datagrid({
        nowrap: false,
        method: "post",
        url: ctx + '/bis/qybg/tablist/' +${qyid},
        loadMsg: '正在加载',
        fitColumns: true,
        selectOnCheck: false,
        autoRowHeight: false,
        border: false,
        idField: 'id',
        striped: true,
        pagination: false,
        rownumbers: true,
        scrollbarSize: 10,
        selectOnCheck: false,
        singleSelect: true,
        checkOnSelect: false,
        columns: [[
            {
                field: 's1', title: '变更申请时间', sortable: false, width: 40, align: 'center',
                formatter: function (value) {
                    return new Date(value).format("yyyy-MM-dd");
                }
            },
            {field: 'modification', title: '变更事项', sortable: false, width: 40, align: 'center'},
            {
                field: 'result', title: '审核结果', sortable: false, width: 30, align: 'center',
                formatter: function (value) {
                    if (value == 1) {
                        return "审核通过";
                    } else if (value == 0) {
                        return "审核未通过";
                    } else {
                        return "未审核";
                    }
                }
            }, {
                field: 'bgnr',
                title: '<table style="width: 100%"><tr><td colspan="3">变更内容<td></tr><tr><td style="width: 30%;text-align: right">变更项目</td><td style="width: 35%">变更前</td><td style="width: 35%">变更后</td></tr></table>',
                fixed: false,
                sortable: false,
                width: 200,
                align: 'center',
                formatter: function (value, row) {
                    var oldbis = JSON.parse(row.bisoldjson);
                    var newbis = JSON.parse(row.bisnewjson);
                    var html = '<table class="table table-bordered dataTable">';
                    for (var key in newbis) {
                        var tmp = Gdata[key];
                        if (tmp) {
                            if (typeof tmp === 'string') {
                                //文件分离出文件名
                                if (key == "m37" || key == "m38" || key == "m33_3") {
                                    html += getHtml(tmp, oldbis[key] ? oldbis[key].split("||")[1] : "", newbis[key].split("||")[1]);
                                } else if (key == 'm12') {
						           oldbis[key] = getHylbOldText(oldbis[key]);
						           newbis[key] = getHylbNewText(newbis[key]);
						           html += getHtml(tmp, (oldbis[key] ? oldbis[key] : ""), newbis[key] ? newbis[key] : "");
                                } else 
                                    html += getHtml(tmp, (oldbis[key] ? oldbis[key] : ""), newbis[key] ? newbis[key] : "");
                            } else if (typeof tmp === 'object') {
                                if (key == 'm36') {//监督分类为多选
                                    var oldStr = oldbis[key] ? getM36Str(oldbis[key], tmp.data) : "";
                                    var newStr = newbis[key] ? getM36Str(newbis[key], tmp.data) : "";
                                    html += getHtml(tmp.name, oldStr, newStr);
                                } else {
                                    html += getHtml(tmp.name, tmp.data[oldbis[key]] == undefined ? "" : tmp.data[oldbis[key]],
                                        tmp.data[newbis[key]] == undefined ? "" : tmp.data[newbis[key]]);
                                }
                            }
                        }
                    }
                    html += '</table>';
                    return html;
                }
            }
        ]],
        onLoadSuccess: function () {
            $(this).datagrid("autoMergeCells", ["qyname"]);
        },
        checkOnSelect: true,
        selectOnCheck: false,
        toolbar: '#dg_tb'
    });

    function getHtml(tmp, oldStr, newStr) {
        var html = '<tr><td class="width-30"><label class="pull-right">' + tmp
            + ':</label></td><td class="width-35 text-center ">' + oldStr
            + '</td><td class="width-35 text-center textred">' + newStr
            + "</td></tr>";
        return html;
    }

    function getM36Str(str, arrayComp) {
        if (str.indexOf(',') != -1) {
            var m36 = '';
            var arrayTmp = str.split(',');
            if (Array.isArray(arrayTmp) && arrayTmp.length > 0) {
                for (var index in arrayTmp) {
                    if (arrayTmp[index]) {
                        for (var key in arrayComp) {
                            if (key == arrayTmp[index]) {
                                m36 += arrayComp[key] + ",";
                            }
                        }
                    }
                }
            }
            return m36.substring(0, m36.length - 1);
        } else {
            return arrayComp[str];
        }
    }
    
    //根据oldbis[key]获取对应的名称
    function getHylbOldText(oldKeyValue) {
    	var oldString = "";
    	if (oldKeyValue != '') {
            $.ajax({
                type: 'get',
                async: false,
                url: '${ctx}/tcode/dictvalue/'+oldKeyValue,
                success: function(data){
               	var dataValue = JSON.parse(data);
               	oldString = dataValue[0].text;
               }
           }); 
         }
         return oldString;
    }
    
     //根据newbis[key]获取对应的名称
     function getHylbNewText(newKeyValue) {
    	var newString = "";
    	if (newKeyValue != '') {
            $.ajax({
                type: 'get',
                async: false,
                url: '${ctx}/tcode/dictvalue/'+newKeyValue,
                success: function(data){
               	var dataValue = JSON.parse(data);
               	newString = dataValue[0].text;
               }
           }); 
         } 
         return newString;
    }

    //创建查询对象并查询
    function search() {
        var obj = $("#searchFrom").serializeObject();
        dg.datagrid('load', obj);
    }

    //清空
    function reset() {
        $("#searchFrom").form("clear");
        search();
    }
</script>
</body>
</html>