<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>报警管理列表</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
<div id="lydw_bjgl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
    <input name="lydw_bjgl_cx_rq" class="easyui-datebox" style="width: 120px;height: 30px;" data-options="editable:false,prompt: '日期'" />
    <input name="lydw_bjgl_cx_lb" class="easyui-combobox" style="width: 120px;height: 30px;" data-options="editable:true,valueField:'text',textField:'text',url:'${ctx }/lydw/bjgl/bjlb',prompt: '报警类别'" />
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
            <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
            <%--<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>--%>

            <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
	</div>    
</div>
<table id="lydw_bjgl_dg"></table>

<script type="text/javascript">
    var dg;
    $(function(){
        dg=$('#lydw_bjgl_dg').datagrid({
            method: "post",
            url:ctx+'/lydw/bjgl/list',
            fit : true,
            fitColumns : true,
            border : false,
            idField : 'id',
            striped:true,
            pagination:true,
            rownumbers:true,
            nowrap:false,
            pageNumber:1,
            pageSize : 50,
            pageList : [ 50, 100, 150, 200, 250 ],
            scrollbarSize:5,
            singleSelect:true,
            striped:true,
            columns:[[
                {field:'id',title:'id',checkbox:true,width:50,align:'center'},
                {field:'m1',title:'报警类别',sortable:false,width:60},
                {field:'m2',title:'报警内容',sortable:false,width:100,align:'center'},
                {field:'m3',title:'处理反馈',sortable:false,width:100,align:'center'},
                {field:'cz',title:'操作',sortable:false,width:40,align:'center',
                    formatter: function (value,row) {
                        if(row.m3 != null && row.m3 != '') {
                            return '';
                        }else {
                            return "<a class='btn btn-success btn-xs'  onclick='upd("+row.id+")'>反馈</a>";
                        }
                    }
                }
            ]],
            onDblClickRow: function (rowdata, rowindex, rowDomElement){

            },
            checkOnSelect:false,
            selectOnCheck:false,
            toolbar:'#lydw_bjgl_tb'
        });

    });

    //创建查询对象并查询
    function search(){
        var obj=$("#searchFrom").serializeObject();
        dg.datagrid('load',obj);
    }

    function reset(){
        $("#searchFrom").form("clear");
        var obj=$("#searchFrom").serializeObject();
        dg.datagrid('load',obj);
    }

    //弹窗修改
    function upd(id){
        var row = dg.datagrid('getSelected');
        if(id == null) {
            if(row==null) {
                layer.msg("请选择一行记录！",{time: 1000});
                return;
            }else {
                id = row.id;
            }
        }

        openDialog("修改报警信息",ctx+"/lydw/bjgl/update/"+id,"400px", "350px","");

    }

    function del() {
        var row = dg.datagrid('getChecked');
        if(row==null||row=='') {
            layer.msg("请至少勾选一行记录！",{time: 1000});
            return;
        }

        var ids="";
        for(var i=0;i<row.length;i++){
            if(ids==""){
                ids=row[i].id;
            }else{
                ids=ids+","+row[i].id;
            }
        }

        top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                type:'get',
                url:ctx+"/lydw/bjgl/delete/"+ids,
                success: function(data){
                    layer.alert(data, {offset: 'rb',shade: 0,time: 2000});
                    top.layer.close(index);
                    dg.datagrid('reload');
                    dg.datagrid('clearChecked');
                    dg.datagrid('clearSelections');
                }
            });
        });
    }

</script>
</body>
</html>