<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<style>
.tabs-wrap{overflow-y: scroll;overflow-x: hidden;}
</style>
</head>
<body>
	<div id="tt" class="easyui-tabs" data-options="tabPosition:'left'" style="width:100%;height:100%;text-align: center;">
		<input type="hidden" id="qyid" value="${qyid }"/>
		<div title="基本信息" style="text-align: center;">
			<iframe frameborder="0" src="${ctx}/bis/qyjbxx/tabview/${qyid }" style="width: 100%;height:98%;"></iframe>
		</div>
		<div title="专家库（${tj.zjk}）" style="padding-top: 5px;" >
			<table id="erm_zjk_dg"></table>
		</div>
		<div title="企业变更（${tj.qybg}）" style="padding-top: 5px;" >
			<table id="bis_qybg_dg"></table>
		</div>
		<div title="风险点信息（${tj.fxd}）" style="padding-top: 5px;" >
			<table id="bis_fxdxx_dg"></table>
		</div>
		<div title="车间信息（${tj.cj}）" style="padding-top: 5px;" >
			<table id="bis_cjxx_dg"></table>
		</div>
		<div title="物料信息（${tj.wl}）" style="padding-top: 5px;" >
			<table id="bis_wlxx_dg"></table>
		</div>
		<div title="仓库信息（${tj.ck}）" style="padding-top: 5px;" >
			<table id="bis_ckxx_dg"></table>
		</div>
		<div title="储罐信息（${tj.cg}）" style="padding-top: 5px;" >
			<table id="bis_cgxx_dg"></table>
		</div>
		<div title="评价报告（${tj.pjbg}）" style="padding-top: 5px;" >
			<table id="bis_pjbg_dg"></table>
		</div>
		<div title="特种设备信息（${tj.tzsb}）" style="padding-top: 5px;" >
			<table id="bis_tzsbxx_dg"></table>
		</div>
		<div title="员工信息（${tj.yg}）" style="padding-top: 5px;" >
			<table id="bis_ygxx_dg"></table>
		</div> 
		<div title="安全培训信息（${tj.aqpx}）" style="padding-top: 5px;" >
			<table id="bis_aqpxxx_dg"></table>
		</div>
		<div title="生产设备信息（${tj.scsb}）" style="padding-top: 5px;" >
			<table id="bis_scsbxx_dg"></table>
		</div>
		<div title="公用工程信息（${tj.gygc}）" style="padding-top: 5px;" >
			<table id="bis_gygcxx_dg"></table>
		</div>
		<div title="危险工艺（${tj.wxgy}）" style="padding-top: 5px;" >
			<table id="bis_gwgy_dg"></table>
		</div>
<%-- 		<div title="安全设施（${tj.aqss}）" style="padding-top: 5px;" >
			<table id="bis_aqss_dg"></table>
		</div> --%>
<%-- 		<div title="职业病危害因素（${tj.zyb}）" style="padding-top: 5px;" >
			<table id="bis_occupharm_dg"></table>
		</div> --%>
<%-- 		<div title="检测报告管理（${tj.jcbg}）" style="padding-top: 5px;" >
			<table id="bis_occupharmreport_dg"></table>
		</div> --%>
<%-- 		<div title="职业病体检（${tj.zybtj}）" style="padding-top: 5px;" >
			<table id="bis_occupillexam_dg"></table>
		</div> --%>
<%-- 		<div title="员工体检信息（${tj.ygtj}）" style="padding-top: 5px;" >
			<table id="bis_ygtj_dg"></table>
		</div> --%>
		<div title="重大危险源信息" style="padding-top: 5px;" >
			<!-- <table id="bis_hazard_dg"></table> -->
			<div id="div14"></div>
		</div>
<!-- 		<div title="安全网络" style="padding-top: 5px;">
			<div id="div15" style="height:98%"></div>
		</div> -->
		<div title="粉尘信息（${tj.fc}）" style="padding-top: 5px;">
			<table id="bis_fcxx_dg"></table>
		</div>
		<div title="受限空间（${tj.sxkj}）" style="padding-top: 5px;">
			<table id="bis_sxkj_dg"></table>
		</div>
		<div title="能源信息（${tj.ny}）" style="padding-top: 5px;">
			<table id="bis_nyxx_dg"></table>
		</div>
		<div title="承包商信息（${tj.cbs}）" style="padding-top: 5px;">
			<table id="bis_cbsxx_dg"></table>
		</div>
		<div title="冶金信息（${tj.yj}）" style="padding-top: 5px;">
			<table id="bis_yjxx_dg"></table>
		</div>
		<div title="作业班次（${tj.zybc}）" style="padding-top: 5px;">
			<table id="bis_zybc_dg"></table>
		</div>
		
	</div>
<script type="text/javascript">
var qyid;
$(function(){
	qyid= $("#qyid").val();
	$('#tt').tabs({
		onSelect: function(title,index){
			//$("#div01").html()="<td>test</td>";
			if(title=="风险点信息（${tj.fxd}）"){//风险点信息
				fxdxxlist();	
			 }else if(title=="车间信息（${tj.cj}）"){//车间信息
				cjlist();	
			 }else if(title=="物料信息（${tj.wl}）"){//物料
				 wllist();
			 }else if(title=="仓库信息（${tj.ck}）"){//仓库
				 cklist();
			 }else if(title=="储罐信息（${tj.cg}）"){//储罐
				 cglist();
			 }else if(title=="特种设备信息（${tj.tzsb}）"){//特种设备
				 tzsblist();
			 }else if(title=="安全培训信息（${tj.aqpx}）"){//安全培训
				 aqpxlist();
			 }else if(title=="危险工艺（${tj.wxgy}）"){//高危工艺
				 gwgylist();
			 }else if(title=="安全设施（${tj.aqss}）"){ 
				 aqsslist();
			 }else if(title=="员工信息（${tj.yg}）"){//员工信息
				 ygxxlist();
			 }else if(title=="生产设备信息（${tj.scsb}）"){//生产设备信息
				 scsbxxlist();
			 }else if(title=="公用工程信息（${tj.gygc}）"){//公用工程信息
				 gygcxxlist();
			 }else if(title=="作业班次（${tj.zybc}）"){//作业班次
				 zybclist();
			 }else if(title=="职业病危害因素（${tj.zyb}）"){//职业病危害
				 zybwhlist();
			 }else if(title=="检测报告管理（${tj.jcbg}）"){//职业病报告
				 zybbglist();
			 }else if(title=="职业病体检（${tj.zybtj}）"){//职业病体检
				 zybtjlist();
			 }else if(title=="员工体检信息（${tj.ygtj}）"){  
				 ygtjlist();
			 }else if(title=="重大危险源信息"){//重大危险源
				$("#div14").html('<iframe frameborder="0" scrolling="no" src="${ctx}/bis/hazard/tabindex/${qyid}" style="width:100%;height:500px;"></iframe>');
			 }else if(title=="安全网络"){//安全管理网络
				$("#div15").html('<iframe frameborder="0" scrolling="yes" src="${ctx}/bis/aqwlgl/view/${qyid}" style="width:100%;height:100%;align:center"></iframe>');
			 }else if(title=="粉尘信息（${tj.fc}）"){  
				 fcxxlist();
			 }else if(title=="受限空间（${tj.sxkj}）"){  
				 sxkjlist();
			 }else if(title=="能源信息（${tj.ny}）"){  
				 nyxxlist();
			 }else if(title=="承包商信息（${tj.cbs}）"){  
				 cbsxxlist();
			 }else if(title=="冶金信息（${tj.yj}）"){  
				 yjxxlist();
			 }else if(title=="作业班次（${tj.zybc}）"){  
				 zybclist();
			 }else if(title=="评价报告（${tj.pjbg}）"){
				 pjbglist();
			 }
			 
		}
	});
});

$(function(){
$('#erm_zjk_dg').datagrid({    
	method: "post",
    url:ctx+'/erm/zjk/list', 
    fit : true,
    queryParams:{erm_qyid:qyid},
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
        {field:'qynm',title:'企业名称',width:100,align:'center'},        
        {field:'m1',title:'姓名',width:100,align:'center'},    
        {field:'m2',title:'性别',width:50,align:'center',formatter: function(value,row,index){
																		if (row.m2=='1'){
																			return '男';
																		}else if (row.m2=='0'){
																			return '女';
																		}else {
																			return '';
																		}
																	}
		},
        {field:'m14',title:'职称',width:100,align:'center'},
        {field:'mark',title:'是否为应急专家',width:100,align:'center',formatter: function(value,row,index){
        if (row.mark=='0'){
            return '非应急专家';
        }else if (row.mark=='1'){
            return '应急专家';
        }else {
            return '';
        }
    }}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false
	});
});


$(function(){
$('#bis_qybg_dg').datagrid({    
	method: "post",
	url:ctx+'/bis/qybg/list', 
	fit : true,
	queryParams:{view_qyid:qyid},
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
	scrollbarSize:10,
	singleSelect:true,
	striped:true,
	columns:[[
            {field:'qyname',title:'企业名称',sortable:false,width:80},
            {field:'modification',title:'变更事项',sortable:false,width:50,formatter:function(value){
                   var html="";
                   if(value=="关停增减"){
            	   	html = "关停增减";
                   }else if(value=="辖区转移"){
            	   	html = "辖区转移";
                   }else if(value=="信息变更"){
            	   	html = "信息变更";
                   }
                   return html;
            }},
            {field:'content',title:'变更内容',sortable:false,width:200},
            {field:'s1',title:'申请提交时间',sortable:false,width:80,
            	formatter: function(value){
            		if (value) {
            			return new Date(value).format("yyyy-MM-dd hh:mm:ss");
            		}
            	}
            },
            {field:'applyer',title:'申请人',sortable:false,width:50},
            {field:'s2',title:'审核时间',sortable:false,width:80,
            	formatter: function(value, row){
        			if (row.result) {
        				return new Date(value).format("yyyy-MM-dd hh:mm:ss");
        			}
            	}
            },
            {field:'reviewer',title:'审核人',sortable:false,width:50},
			{
				field: 'result', title: '审核结果', sortable: false, width: 50,align:'center', formatter: function (value) {
					var html = "";
					if (!value) {
						html = "未审核";
					} else if (value == "1") {
						html = "通过";
					} else if (value == "0") {
						html = "未通过";
					}
					return html;
				}, styler: function (value) {
                    if (value == "0") {
                        return "background-color:rgb(249, 156, 140)";
                    }else if(!value){
                        return 'background-color:rgb(255,240,245);';
					}
				}
			},
			{field:'operate',title:'操作',sortable:false,width:50,align:'center',formatter:function(value,row){
					if(!row.result){
                        return "<a class='btn btn-success btn-xs'onclick='review("+row.id+",\""+row.result+"\")'>审核</a>";
					}
				}}

        ]],
	checkOnSelect:false,
	selectOnCheck:false
	});
});



function cjlist(){
	$('#bis_cjxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/cjxx/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'车间名称',sortable:false,width:100},    
	        {field:'m2',title:'车间编号',sortable:false,width:100,align:'center'},
	        {field:'m4',title:'火灾危险等级',sortable:false,width:100,align:'center', 
	        	formatter : function(value, row, index){
	        		if(value=='1') return value='甲类';
	        		if(value=='2') return value='乙类';
	        		if(value=='3') return value='丙类';
	        		if(value=='4') return value='丁类';
	        		if(value=='5') return value='戊类';
	        	}
	        
	        },
	        {field:'m5',title:'建筑结构',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index){
	        		if(value=='1') return value='砖混';
	        		if(value=='2') return value='钢结构';
	        		if(value=='3') return value='框架';
	        		if(value=='4') return value='其他';
	        	}
	        },
	        {field:'m6',title:'层数',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index) {
	           	 return value+'层';
	       	} 
	        }
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}

function wllist(){
	$('#bis_wlxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/wlxx/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'原料名称',sortable:false,width:100},    
	        {field:'m2',title:'年用量（t）',sortable:false,width:100,align:'center'},
	        {field:'m3',title:'最大储量（t）',sortable:false,width:100,align:'center'},
	        {field:'m4',title:'CAS号',sortable:false,width:100,align:'center'},
	        {field:'m5',title:'储存方式',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index){
	        		if(value=='1') return value='储罐';
	        		if(value=='2') return value='桶装';
	        		if(value=='3') return value='袋装';
	        		if(value=='4') return value='其他';
	        	}
	        },
	        {field:'m8',title:'主要危险性',sortable:false,width:100,align:'center'}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function cklist(){
	$('#bis_ckxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/ckxx/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'仓库名称',sortable:false,width:100},    
	        {field:'m2',title:'仓库编号',sortable:false,width:100,align:'center'},
	        {field:'m3',title:'建筑面积（㎡）',sortable:false,width:100,align:'center'},
	        {field:'m4',title:'火灾危险等级',sortable:false,width:100,align:'center', 
	        	formatter : function(value, row, index){
	        		if(value=='1') return value='甲类';
	        		if(value=='2') return value='乙类';
	        		if(value=='3') return value='丙类';
	        		if(value=='4') return value='丁类';
	        		if(value=='5') return value='戊类';
	        	}
	        },
	        {field:'m5',title:'建筑结构',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index){
	        		if(value=='1') return value='砖混';
	        		if(value=='2') return value='钢结构';
	        		if(value=='3') return value='框架';
	        		if(value=='4') return value='其他';
	        	}
	        },
	        {field:'m6',title:'层数',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index) {
	           	 return value+'层';
	       	} 
	        }
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function cglist(){
	$('#bis_cgxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/cgxx/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'储罐名称',sortable:false,width:100},    
	        {field:'m2',title:'储罐类型',sortable:false,width:100,align:'center',
	         	formatter : function(value, row, index) {
	            	if(value=='1') return value='立式圆筒形储罐';
	            	if(value=='2') return value='卧式圆筒形储罐';
					if(value=='3') return value='球形储罐';
					if(value=='4') return value='双曲线储罐';
					if(value=='5') return value='悬链式储罐';
					if(value=='6') return value='固定顶储罐';
					if(value=='7') return value='外浮顶罐';
					if(value=='8') return value='内浮顶罐';
					if(value=='9') return value='其他储罐';
	        	}      
	        },
	        {field:'m4',title:'材质',sortable:false,width:100,align:'center',
	         	formatter : function(value, row, index) {
	            	if(value=='1') return value='滚塑';
	            	if(value=='2') return value='玻璃钢';
					if(value=='3') return value='碳钢';
					if(value=='4') return value='陶瓷';
					if(value=='5') return value='橡胶';
					if(value=='6') return value='其他';
	        	}      
	       	},
	        {field:'m5',title:'数量',sortable:false,width:100,align:'center'},
	        {field:'m6',title:'储存物料类别',sortable:false,width:100,align:'center',
	         	formatter : function(value, row, index) {
	            	if(value=='1') return value='甲类';
	            	if(value=='2') return value='乙类';
					if(value=='3') return value='丙类';
					if(value=='4') return value='丁类';
					if(value=='5') return value='戊类';
	        	}      
	        },
	        {field:'m7',title:'存储物料名称',sortable:false,width:100,align:'center'},
	        {field:'m8',title:'CAS号',sortable:false,width:100,align:'center'}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function cplist(){
	$('#bis_cpxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/cpxx/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'产品名称',sortable:false,width:100},
	        {field:'m2',title:'年产量（t）',sortable:false,width:100,align:'center'},
	        {field:'m3',title:'最大储量（t）',sortable:false,width:100,align:'center'},
	        {field:'m4',title:'状态',sortable:false,width:100,align:'center',
	        	formatter : function (value, row,index){
	        		if(value=='0') return value='正常';
	        		if(value=='1') return value='注销';
	        	}
	        },
	        {field:'m6',title:'CAS号',sortable:false,width:100,align:'center'}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function tzsblist(){
	$('#bis_tzsbxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/tzsbxx/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'ID',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'M1',title:'特种设备名称',sortable:false,width:100},    
	        {field:'M2',title:'特种设备编号',sortable:false,width:100,align:'center'},
	        {field:'M9',title:'特种设备类型',sortable:false,width:100,align:'center'},
	        {field:'M4',title:'规格型号',sortable:false,width:100,align:'center'},
	        {field:'M6',title:'数量',sortable:false,width:100,align:'center'},
	        {field:'M7',title:'功能',sortable:false,width:100,align:'center'}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function aqpxlist(){
	$('#bis_aqpxxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/aqpxxx/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'安全培训人员姓名',sortable:false,width:100},    
	        {field:'m2',title:'安全培训人员类别',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index){
	        		if(value=='') return value='';
	        		if(value=='1') return value='主要负责人';
	        		if(value=='2') return value='安全管理人员';
	        		if(value=='3') return value='操作人员';
	        		if(value=='4') return value='特种设备作业人员';
	        		if(value=='5') return value='特种作业人员';
	        		if(value=='6') return value='其他人员';
	        	}
	        },
	        {field:'m3',title:'安全培训人员职务',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index){
	        		if(value=='') return value='';
	        		if(value=='1') return value='懂事长';
	        		if(value=='2') return value='总经理';
	        		if(value=='3') return value='副总经理';
	        		if(value=='4') return value='安全总监';
	        		if(value=='5') return value='安全经理';
	        		if(value=='6') return value='部门经理';
	        		if(value=='7') return value='车间主任';
	        		if(value=='8') return value='班组长';
	        		if(value=='9') return value='其他';
	        	}
	        },
	        {field:'m4',title:'最近培训时间',sortable:false,width:100,align:'center',
	        	formatter : function(value,row,index){
	        		 return new Date(value).format('yyyy-MM-dd');
	        	}},
	        {field:'m5',title:'下次培训时间',sortable:false,width:100,align:'center',
        		formatter : function(value,row,index){
	        		 return new Date(value).format('yyyy-MM-dd');
	        	}},
	        {field:'m6',title:'发证机关',sortable:false,width:100,align:'center',
	        	formatter : function(value,row,index){
	        		if(value=='fzjg1') return value='市安监局';
	        		if(value=='fzjg2') return value='区安监局';
	        		if(value=='fzjg3') return value='市质监局';
	        		if(value=='fzjg4') return value='其他部门';
	        	}
	        }
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function gwgylist(){
	$('#bis_gwgy_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/gwgy/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'M4',title:'高危工艺',sortable:false,width:100 },    
	        {field:'M2',title:'套数（套）',sortable:false,width:100,align:'center'} 
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function wxzylist(){
	$('#bis_wxzy_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/wxzy/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'作业名称',sortable:false,width:100},    
	        {field:'m2',title:'时间起',sortable:false,width:100,align:'center'},
	        {field:'m3',title:'时间止',sortable:false,width:100,align:'center'},
	        {field:'m4',title:'作业频次',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index){
	        		if(value=='1') return value='偶尔';
	        		if(value=='2') return value='经常';
	        		if(value=='3') return value='强化';
	        	}
	        }
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function zybclist(){
	$('#bis_zybc_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/zybc/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'班次',sortable:false,width:100},    
	        {field:'m2',title:'接班时间',sortable:false,width:100,align:'center'},
	        {field:'m3',title:'交班时间',sortable:false,width:100,align:'center'},
	        {field:'m4',title:'人数',sortable:false,width:100,align:'center'}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function zybwhlist(){
	$('#bis_occupharm_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/occupharm/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'类别',sortable:false,width:100},    
	        {field:'m2',title:'名称',sortable:false,width:100,align:'center' },
	        {field:'m3',title:'危害',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index){
	        		if(value=='1') return value='轻度危险性';
	        		if(value=='2') return value='一般危险性';
	        		if(value=='3') return value='严重危险性';
	        		
	        	}
	        }
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function zybbglist(){
	$('#bis_occupharmreport_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/occupharmreport/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'检测机构',sortable:false,width:100},    
	        {field:'m2',title:'检测日期',sortable:false,width:100,align:'center',
	        	formatter : function(value,row,index){
       		 		return new Date(value).format('yyyy-MM-dd');
        	}},
	        {field:'m3',title:'检测结果',sortable:false,width:100,align:'center'},
	        {field:'m4',title:'下次检测日期',sortable:false,width:100,align:'center',
	        	formatter : function(value,row,index){
	        		 return new Date(value).format('yyyy-MM-dd');
	        	}},
	        {field:'m5',title:'附件下载',sortable:false,width:100,align:'center',
	        	formatter:function(val,row,index){
	        		if(val!=null&&val!=""){
	        			var t="||";
	        			val=val.split(t);
	        			return '<a onclick="window.open(\''+val[0]+'\')" href="javascript:void(0)">附件下载</a>';  
	        		}else{
	        			return '--';
	        		}
		        	
	        	}  
	        }
	        
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function zybtjlist(){
	$('#bis_occupillexam_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/occupillexam/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'体检日期',sortable:false,width:100,
	        	formatter : function(value,row,index){
	        		 return new Date(value).format('yyyy-MM-dd');
	        	}},    
	        {field:'m2',title:'体检医院',sortable:false,width:100,align:'center'},
	        {field:'m3',title:'体检人数',sortable:false,width:100,align:'center'},
	        {field:'m4',title:'体检结论',sortable:false,width:100,align:'center'},
	        {field:'m5',title:'附件',sortable:false,width:100,align:'center',
	        	formatter:function(val,row,index){
	        		if(val!=""){
	        			var t="||";
	        			val=val.split(t);
	        			return '<a onclick="window.open(\''+val[0]+'\')" href="javascript:void(0)">附件下载</a>';  
	        		}else{
	        			return '--';
	        		}
		        	
	        	}
	        }
	       
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function zdwxylist(){
	$('#bis_hazard_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/hazard/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'重大危险源级别',sortable:false,width:100,
	        	formatter : function(value, row, index){
	        		if(value=='1') return value='一级';
	        		if(value=='2') return value='二级';
	        		if(value=='3') return value='三级';
	        		if(value=='4') return value='四级';
	        	}
	        },    
	        {field:'m2',title:'R值',sortable:false,width:100},
	        {field:'m3',title:'厂区人数',sortable:false,width:100},
	        {field:'m4',title:'是否有安全监控预警系统',sortable:false,width:100,
	        	formatter : function(value, row, index){
	        		if(value=='0') return value='否';
	        		if(value=='1') return value='是';
	        	}
	        },
	        {field:'m5',title:'主要危险性',sortable:false,width:100},
	        {field:'m6',title:'联系人',sortable:false,width:100},
	        {field:'m7',title:'联系电话',sortable:false,width:100}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function ygxxlist(){
	dg=$('#bis_ygxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/ygxx/tablist/'+qyid, 
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
	        {field:'m1',title:'姓名',sortable:false,width:100},    
	        {field:'m8',title:'身份证号',sortable:false,width:100,align:'center'},
	        {field:'m3',title:'性别',sortable:false,width:100,align:'center'},
	        {field:'m4',title:'岗位',sortable:false,width:100,align:'center'},
	        {field:'m5',title:'学历',sortable:false,width:100,align:'center'},
	        {field:'m7',title:'职称',sortable:false,width:100,align:'center'},
	    ]],

		checkOnSelect:false,
		selectOnCheck:false 
		});
}
function scsbxxlist(){
	dg=$('#bis_scsbxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/scsb/tablist/'+qyid, 
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
	        {field:'m1',title:'设备位号',width:80},
	        {field:'m2',title:'设备类别',width:80,align:'center'},
	        {field:'m3',title:'设备名称',width:80,align:'center'},
	        {field:'m4',title:'规格尺寸',width:80,align:'center'},
	        {field:'m5',title:'型号',width:80,align:'center'},
	        {field:'m6',title:'数量',width:80,align:'center'},
	        {field:'m7',title:'制造单位',width:80,align:'center'},
	    ]],
		checkOnSelect:false,
		selectOnCheck:false 
		});
}
function gygcxxlist(){
	dg=$('#bis_gygcxx_dg').datagrid({    
		nowrap:false,
		method: "post",
	    url:ctx+'/bis/gygc/tablist/'+qyid, 
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
		checkOnSelect:false,
		selectOnCheck:false,
	    columns:[[    
	        {field:'m1',title:'类别',width:80},
	        {field:'m2',title:'占地面积',width:80,align:'center'},
	        {field:'m3',title:'建筑结构',width:80,align:'center'},
	        {field:'m4',title:'主要危险性',width:150,align:'center',
	        	formatter:function(value,row,index){
	        		if(value.length>24){
	        			value=value.substring(0,24)+"...";
	        		}
	        		return value;
	        	}},
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
	});
}

function aqsslist(){
	dg=$('#bis_aqss_dg').datagrid({    
		nowrap:false,
		method: "post",
	    url:ctx+'/bis/aqss/tablist/'+qyid, 
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
		checkOnSelect:false,
		selectOnCheck:false,
	    columns:[[    
	        {field:'m1',title:'类别',sortable:false,width:100,align:'center'},    
	        {field:'m2',title:'安全设施名称',sortable:false,width:100,align:'center'},
	        {field:'m3',title:'涉及工艺设施',sortable:false,width:100,align:'center' },
	        {field:'m4',title:'数量',sortable:false,width:100,align:'center' },
	        {field:'m5',title:'检测时间',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index) {
	           	 if(value!=null){
	           		var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');    
	        	 }
	       	}},
	       	{field:'m6',title:'到期时间',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index) {
	        		if(value!=null){
	               		var datetime = new Date(value);  
	    				 return datetime.format('yyyy-MM-dd');    
	            	 }
	       	}},
	       	{field:'m7',title:'状态',sortable:false,width:100,align:'center',
	       		formatter : function(value, row, index){
	       			if(value==null) return value='';
	       			else if(value=='1') return value='失效';
	        		else if(value=='2') return value='停用';
	        		else if(value=='0') return value='正常'; 
	        		else return value='';
	        	}} 
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
	});
}


function ygtjlist(){
	dg=$('#bis_ygtj_dg').datagrid({    
		nowrap:false,
		method: "post",
	    url:ctx+'/bis/ygtjxx/tablist/'+qyid, 
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
		checkOnSelect:false,
		selectOnCheck:false,
	    columns:[[    
			{field:'m7',title:'员工姓名',width:80},
			{field:'m1',title:'身份证号',width:80,align:'center'},
			{field:'m2',title:'体检类型',width:80,align:'center'},
			{field:'m3',title:'体检日期',width:80,align:'center',
				  formatter : function(value, row, index) {
			    	if(value!='') {
			    		var datetime=new Date(value);
			    		 return datetime.format('yyyy-MM-dd');  
			    	}
				} 
			},
			{field:'m4',title:'体检医院',width:110,align:'center'}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
	});
}
function fcxxlist(){
	dg=$('#bis_fcxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/fcxx/tablist/'+qyid, 
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
	              {field:'m1',title:'粉尘种类',sortable:false,width:100,align:'center'},    
	              {field:'m2',title:'涉粉作业人数',sortable:false,width:100,align:'center'},
	              {field:'m3',title:'涉粉单班作业人数',sortable:false,width:100,align:'center'},
	              {field:'m4',title:'取缔',sortable:false,width:100,align:'center', 
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              },
	              {field:'m5',title:'关闭',sortable:false,width:100,align:'center',
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              },
	              {field:'m6',title:'有除尘器',sortable:false,width:100,align:'center',
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              },
	              {field:'m7',title:'除尘器种类',sortable:false,width:100,align:'center'},    
	              {field:'m8',title:'建立粉尘清扫制度',sortable:false,width:100,align:'center',
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              },
	              {field:'m9',title:'安装监控',sortable:false,width:100,align:'center',
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              },
	              {field:'m11',title:'通过验收',sortable:false,width:100,align:'center',
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              },
	              {field:'m12',title:'除尘器数量',sortable:false,width:100,align:'center'  },
	              {field:'m13',title:'是否涉爆',sortable:false,width:100,align:'center', 
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              },
	              {field:'m14',title:'是否职业病危害',sortable:false,width:100,align:'center',
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              }
	    ]],
		checkOnSelect:false,
		selectOnCheck:false,
		});
}
function sxkjlist(){
	dg=$('#bis_sxkj_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/sxkj/tablist/'+qyid, 
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
	              {field:'m5',title:'名称',sortable:false,width:100,align:'center'},    
	              {field:'m6',title:'主要危险有害物质',sortable:false,width:100,align:'center'},
	              {field:'m2',title:'数量',sortable:false,width:50,align:'center'},
	              {field:'m3',title:'位置',sortable:false,width:100,align:'center'},
	              {field:'m7',title:'易导致事故类型',sortable:false,width:100,align:'center'},
	              {field:'m8',title:'安全设施（预防/应急）',sortable:false,width:100,align:'center'}
	    ]],
		checkOnSelect:false,
		selectOnCheck:false,
		});
}
function nyxxlist(){
	dg=$('#bis_nyxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/hxnyxx/tablist/'+qyid, 
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
	              {field:'m1',title:'能源名称',sortable:false,width:100,align:'center'},    
	              {field:'m2',title:'年用量（t/m3）',sortable:false,width:100,align:'center'},
	              {field:'m3',title:'最大储存量（t/m3）',sortable:false,width:100,align:'center'},
	              {field:'m4',title:'涉及工艺',sortable:false,width:100,align:'center'},
	              {field:'m5',title:'是否进行安全评价',sortable:false,width:100,align:'center',
	            	  formatter : function(value,row,index){
	                		if(value=='0') return value='否';
	                		if(value=='1') return value='是';
	                		
	              }},
	              {field:'m6',title:'是否设置监控设施',sortable:false,width:100,align:'center',
	                  formatter : function(value,row,index){
	                    	if(value=='0') return value='否';
	                    	if(value=='1') return value='是';
	                    		
	               }}
	     ]],
		checkOnSelect:false,
		selectOnCheck:false,
		});
}
function cbsxxlist(){
	dg=$('#bis_cbsxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/cbsxx/tablist/'+qyid, 
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
	              {field:'m1',title:'承包商名称',sortable:false,width:100,align:'center'},    
	              {field:'m2',title:'承包项目',sortable:false,width:100,align:'center'},
	              {field:'m3',title:'作业内容',sortable:false,width:100,align:'center'},
	              {field:'m4',title:'作业人数',sortable:false,width:100,align:'center'},            
	              {field:'m8',title:'承包商负责人',sortable:false,width:100,align:'center'},
	              {field:'m9',title:'联系方式',sortable:false,width:100,align:'center'}
	    ]],
		checkOnSelect:false,
		selectOnCheck:false,
		});
}
function yjxxlist(){
	dg=$('#bis_yjxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/yjxx/tablist/'+qyid, 
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
		          {field:'m1',title:'类别名称',sortable:false,width:100},
	              {field:'m2',title:'主要产品',sortable:false,width:100,align:'center'},
	              {field:'m3',title:'载体是否为移动式',sortable:false,width:60,align:'center',
	            	  formatter : function(value,row,index){
	              		if(value=='0') return value='否';
	              		if(value=='1') return value='是';
	              		
	              	}},
	              {field:'m4',title:'加热方式',sortable:false,width:60,align:'center'},
	              {field:'m5',title:'年产量',sortable:false,width:60,align:'center'},
	              {field:'m6',title:'现场人数',sortable:false,width:60,align:'center'},
	              {field:'m7',title:'熔炼场所建筑物结构',sortable:false,width:60,align:'center'},
	              {field:'m8',title:'金属液体转运方式',sortable:false,width:60,align:'center'}
	    ]],
		checkOnSelect:false,
		selectOnCheck:false,
		});
}
function zybclist(){
	dg=$('#bis_zybc_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/zybc/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		border : false,
		idField : 'ID',
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
	              {field:'m1',title:'班次',sortable:false,width:100},    
	              {field:'m2',title:'接班时间',sortable:false,width:100,align:'center'},
	              {field:'m3',title:'交班时间',sortable:false,width:100,align:'center'},
	              {field:'m4',title:'人数',sortable:false,width:100,align:'center'}
	    ]],
		checkOnSelect:false,
		selectOnCheck:false,
		});
}

function pjbglist(){
	dg=$('#bis_pjbg_dg').datagrid({    
		method: "post",
	    url:ctx+'/aqjg/aqba/pjtablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		border : false,
		idField : 'ID',
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
	            {field:'m3',title:'备案类别',sortable:false,width:50,align:'center'},
		        {field:'m1',title:'备案编号',sortable:false,width:80,align:'center'},
		        {field:'m2',title:'备案日期',sortable:false,width:50,align:'center',
		        	formatter:function(value,row,index){
		     		if(value!=null){
		                var datetime = new Date(value);  
		                return datetime.format('yyyy-MM-dd');  }  
		            } },
		        {field:'m4',title:'是否审核',sortable:false,width:50,align:'center',
		         	formatter : function(value, row, index) {
		            	if(value=='0') return value='否';
				        if(value=='1') return value='是';
				        else return value='否';
		        	} 
		        },
		        {field:'m7',title:'备案经手人',sortable:false,width:50,align:'center'},
		        {field:'m6',title:'附件下载',sortable:false,width:50,align:'center',
		        	formatter : function(value) {
	        		if(value){
	        			var urls=value.split(",");
	        			var html="";
	        			for(var index in urls){
	        				html+="<a class='fa fa-download btn-danger btn-outline' target='_blank' style='margin:1px auto' href='"+urls[index].split("||")[0]+"'> 下载报告</a><br>"; 
	        			}
	        			return html;
	        		}else return ; 
	       	 }}
	    ]],
		checkOnSelect:false,
		selectOnCheck:false,
		});
}
function fxdxxlist(){
	dg=$('#bis_fxdxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/fxgk/fxxx/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		border : false,
		idField : 'ID',
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
	            {field:'m1',title:'较大风险点名称',sortable:false,width:100,align:'center'},    
			{field:'m2',title:'风险类别',sortable:false,width:100,align:'center'},    
			{field:'m3',title:'行业',sortable:false,width:100,align:'center'},    
			{field:'m4',title:'行业类别',sortable:false,width:100,align:'center'},    
			{field:'m8',title:'事故类型',sortable:false,width:100,align:'center'},    
			{field:'m9',title:'风险分级',sortable:false,width:100,align:'center',sortable:true, 
				formatter : function(value, row, index){
					if(value=='1') return value='红';
					else if(value=='2') return value='橙';
					else if(value=='3') return value='黄';
					else if(value=='4') return value='蓝'; 
				},
				styler : function(value, row, index){
					if(value=='1')  return 'background-color:#FF0000;color:#1E1E1E';
					else if(value=='2')  return 'background-color:#FFC125;color:#1E1E1E';
					else if(value=='3')  return 'background-color:#FFFF00;color:#1E1E1E';
					else if(value=='4')  return 'background-color:#3A5FCD;color:#1E1E1E'; 
					}
			}
	    ]],
		checkOnSelect:false,
		selectOnCheck:false,
		});
}
</script>
</body>
</html>