<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=9,IE=10" />
<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
<!-- 引入jquery插件 -->
<script src="${ctxStatic}/jquery/jquery-2.1.1.js" type="text/javascript"></script>

<script src="${ctxStatic}/jquery/jquery-migrate-1.1.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxStatic}/TableDnD/jquery.tablednd.js"></script>


<!-- 引入依赖的第三方插件 -->
<script src="${ctxStatic}/slimscroll/jquery.slimscroll.min.js"></script>



<script src="${ctxStatic}/jquery-validation/1.14.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-validation/1.14.0/localization/messages_zh.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctxStatic}/pace/pace.min.js"></script>
<script src="${ctxStatic}/metisMenu/jquery.metisMenu.js"></script>
<!--  -->
<link href="${ctxStatic}/iCheck/custom.css" rel="stylesheet" />
<script src="${ctxStatic}/iCheck/icheck.min.js"></script>
<script src="${ctxStatic}/iCheck/icheck-init.js"></script>

<link href="${ctxStatic}/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
<script src="${ctxStatic}/dataTables/jquery.dataTables.js"></script>
<script src="${ctxStatic}/dataTables/dataTables.bootstrap.js"></script>



<link href="${ctxStatic}/common/jeecom.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/common/jeecom.js?v=1.0.1" type="text/javascript"></script>

<!-- jquery ui -->
<script src="${ctxStatic}/jquery-ui/jquery-ui.min.js"></script>

<!-- 引入bootstrap插件 -->
<!-- bootswatch主题效果不怎么好，停止切换，使用官网的默认版本，如果你想切换主题请访问http://bootswatch.com/下载最新版版主题，by刘高峰 -->
<!--  <link href="${ctxStatic}/bootstrap/3.3.4/css_${not empty cookie.theme.value ? cookie.theme.value : 'default'}/bootstrap.min.css" type="text/css" rel="stylesheet" />-->
<link href="${ctxStatic}/bootstrap/3.3.4/css_default/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/3.3.4/js/bootstrap.min.js"  type="text/javascript"></script>
<link href="${ctxStatic}/awesome/4.4/css/font-awesome.min.css" rel="stylesheet" />
<link href="${ctxStatic}/common/css/style.css?v=3.2.0" type="text/css" rel="stylesheet" />

<!-- 引入layer插件 -->
<script src="${ctxStatic}/layer-v2.0/layer/layer.js"></script>
<script src="${ctxStatic}/layer-v2.0/layer/laydate/laydate.js"></script>

<!--引入webuploader-->
<link rel="stylesheet" type="text/css" href="${ctxStatic}/webuploader-0.1.5/webuploader.css">
<script type="text/javascript" src="${ctxStatic}/webuploader-0.1.5/webuploader.js"></script>


<!-- 引入自定义文件 -->
<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/content.js" type="text/javascript"></script>
<link href="${ctxStatic}/common/css/animate.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}/common/css/login.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
var ctx = '${ctx}', ctxStatic='${ctxStatic}';
var wfwurl='http://218.93.15.98:2228';
</script>


<!-- 引入easyui插件 -->
<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery-easyui-1.4.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery-easyui-1.4.5/themes/icon.css">

<script src="${ctxStatic}/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<script src="${ctxStatic}/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-easyui-1.4.5/easyui.extensions.js?v=0.1"></script>

<script src="${ctxStatic}/jquery-plugin/jquery.serializeObject.js"></script>


<script>
//全局的AJAX访问，处理AJAX清求时SESSION超时
$.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    complete:function(XMLHttpRequest,textStatus){
          //通过XMLHttpRequest取得响应头，sessionstatus           
          var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); 
          if(sessionstatus=="timeout"){
               //跳转的登录页面
               top.location.replace('${ctx}');
       		}	
          if(textStatus=='error'){
        	  if(XMLHttpRequest.status='500'){
              	  var html=XMLHttpRequest.responseText;
              	  layer.open({
              		  type: 1,  
              		  title : " ",
              		  area : ["300px","150px"],
              		  maxmin :true,
              		  content: html, //这里content是一个普通的String
              		  full :function(dom){
              				$(dom.find(".layui-layer-content")[0]).height("90%");//高度自适应
              		  },
              		  restore  :function(dom){
              				$(dom.find(".layui-layer-content")[0]).height("90%");//高度自适应
              		  }
              		});
        	  }
          }
    }
});

</script>


