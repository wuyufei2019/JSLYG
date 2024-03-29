<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>课件管理</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
<div id="aqpx_kjgl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				
	<div class="form-group">
		<input type="text" id="aqpx_kjgl_cx_m1" name="aqpx_kjgl_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '课件名称'" />		        	        
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="aqpx:kjgl:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="aqpx:kjgl:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqpx:kjgl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqpx:kjgl:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="aqpx_kjgl_dg"></table> 

<script type="text/javascript">
    var dg;
    var d;
    $(function(){
        dg=$('#aqpx_kjgl_dg').datagrid({
            method: "post",
            url:ctx+'/aqpx/kjkgl/list',
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
                {field:'ID',title:'id',checkbox:true,width:50,align:'center'},
                {field:'m1',title:'课件名称',width:100},
                {field:'m2',title:'附件',width:50,align:'center',
                    formatter : function(value, row, index){
                        var content="";
                        if(value!=null&&value!='') {
                            var arr1=value.split(",");
                            var url=arr1[0];
                            var arr2=url.split("||");
                            var arr3=arr2[0].split(".");
                            var wjurl=arr3[0]+"."+row.m3;
                            content=content+'<a href="'+wjurl+'" target="_blank">'+arr2[1]+'</a><br>';
                            return content;
                        }else{
                            return '/';
                        }
                    }
                }
            ]],
            onDblClickRow: function (rowdata, rowindex, rowDomElement){
                view();
            },
            checkOnSelect:false,
            selectOnCheck:false,
            toolbar:'#aqpx_kjgl_tb'
        });

    });

    //弹窗增加
    function add(u) {
        openDialog("添加课件信息",ctx+"/aqpx/kjkgl/create/","800px", "400px","");
    }

    //删除
    function del(){
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
                url:ctx+"/aqpx/kjkgl/delete/"+ids,
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

    //弹窗修改
    function upd(){
        var row = dg.datagrid('getSelected');
        if(row==null) {
            layer.msg("请选择一行记录！",{time: 1000});
            return;
        }

        openDialog("修改课件信息",ctx+"/aqpx/kjkgl/update/"+row.id,"800px", "400px","");

    }

    //查看
    function view(){
        var row = dg.datagrid('getSelected');
        if(row==null) {
            layer.msg("请选择一行记录！",{time: 1000});
            return;
        }

        openDialogView("查看课件信息",ctx+"/aqpx/kjkgl/view/"+row.ID,"800px", "400px","");

    }

    //创建查询对象并查询
    function search(){
        var obj=$("#searchFrom").serializeObject();
        dg.datagrid('load',obj);
    }

    function reset(){
        $("#searchFrom").form("clear");
        var obj=$("#searchFrom").serializeObject();
        dg.datagrid('load',obj);
        //if(btflg=='2') $("#filter_EQS_departmen").hide();
    }

</script>
</body>
</html>