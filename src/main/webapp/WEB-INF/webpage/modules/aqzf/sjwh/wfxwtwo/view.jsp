<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查表库信息查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-15 active"><label class="pull-right">分类：</label></td>
					<td class="width-85" >${wfxw.m6}</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">违法行为：</label></td>
					<td class="width-85" >${wfxw.m1}</td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">违法条款：</label></td>
					<td class="width-85" >${wfxw.m2}</td>
				</tr>
                <tr>
					<td class="width-15 active"><label class="pull-right">违反条款详情：</label></td>
					<td class="width-85" >${wfxw.m3 }</td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">处罚依据：</label></td>
					<td class="width-85" >${wfxw.m4}</td>
				</tr>
                <tr>
					<td class="width-15 active"><label class="pull-right">处罚标准：</label></td>
					<td class="width-85" >${wfxw.m5 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">自由裁量依据：</label></td>
					<td class="width-85" >${wfxw.m7 }</td>
				</tr>
				</tbody>
			</table>
		  	
	 </form>
	 <h3 style="text-align: center;color: #337ab7;">违法裁量</h3>
	 <div style="height:200px;">
		 <table id="aqzf_cfcl_dg" style="width: 100%;height: 100%"></table>
	 </div>
<script type="text/javascript">
var id1 = '${wfxw.ID}';
$(function(){
	dg=$('#aqzf_cfcl_dg').datagrid({    
	method: "post",
    url:ctx+'/aqzf/cfcl/list?id1='+id1+'&version=2', 
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
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
  			//{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
			{field : 'm1',title : '处罚档次',sortable : false,width : 40},
			{field : 'm2',title : '裁量幅度',sortable : false,width : 100,align : 'center'},
			{field : 'e',title : 'E值（元）',sortable : false,width : 40,align : 'center',
           	  formatter : function(value, row, index) {
           		  	return row.m3+"~"+row.m4;  
            	}	 
			}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    },
    onLoadSuccess: function(){
    },
    onLoadError:function(){
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:''
	});
});
</script>
</body>
</html>