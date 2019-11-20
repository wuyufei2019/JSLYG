<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<%--项目路径 --%>
<c:set var="ctx" value="${ctx}" />
<!DOCTYPE HTML >
<html>
<!--
* Cap
* 19/5/5
-->
<head>
	<meta charset="UTF-8">
	<link rel="shortcut icon" href="${ctx}/static/model/images/favicon_zf.ico" />
	<title>登录</title>
	<style>
		/* 公共样式表css */
		html,body {
			color: #333;
			margin: 0;
			height: 100%;
			font-family: "Myriad Set Pro","Helvetica Neue",Helvetica,Arial,Verdana,sans-serif;
		}

		* {
			-webkit-box-sizing: border-box;
			-moz-box-sizing: border-box;
			box-sizing: border-box;
		}

		a {
			text-decoration: none;
			color: #000;
		}

		img {
			border: 0;
		}

		body {
			background: #fff;
			color: #666;
		}

		html, body, div, dl, dt, dd, ol, ul, li, h1, h2, h3, h4, h5, h6, p, blockquote, pre, button, fieldset, form, input, legend, textarea, th, td {
			margin: 0;
			padding: 0;
		}

		a {
			text-decoration: none;
			color: #08acee;
		}

		button {
			outline: 0;
		}

		img {
			border: 0;
		}

		button,input,optgroup,select,textarea {
			margin: 0;
			font: inherit;
			color: inherit;
			outline: none;
		}

		li {
			list-style: none;
		}

		a {
			color: #666;
		}

		.clearfix::after {
			clear: both;
			content: ".";
			display: block;
			height: 0;
			visibility: hidden;
		}

		.clearfix {
		}

		/* 必要布局样式css */
		.Service-box {
			-ms-box-sizing: border-box;
			-o-box-sizing: border-box;
			box-sizing: border-box;
			position: absolute;
			margin: 18% 0 0 14%;
		}
		.title36{
			margin: 8% 10% 10% 10%;
			font-size: 34px;
			font-weight: 600;
			color: white;
			position: absolute;
			letter-spacing:5px;
			text-shadow: 5px 5px 10px #00000045;
			display: flex;
			align-items: center;
		}
		.footer36{position: fixed;bottom: 20px;width: 100%;text-align: center;color: #ffffffab;font-size: 12px;}
		.Service-content {
			width: 1200px;
			margin: 0 auto;
			padding: 0;
		}

		.Service-item {
			background-color: #fff;
			display: block;
			width: 200px;
			padding: 30px 16px;
			text-align: center;
			float: left;
			height: 240px;
			margin: 0 0 10px;
			box-sizing: border-box;
			border-radius: 10px;
			overflow: hidden;
		}

		.Service-content .Service-item {
			-webkit-transition: all 160ms;
			transition: all 160ms;
		}

		.item-image {
			display: inline-block;
			height: 100px;
			width: 100px;
			line-height: 100px;
			text-align: center;
			border: 3px solid transparent;
			margin: 0;
			border-radius: 100%;
			-ms-box-sizing: border-box;
			-o-box-sizing: border-box;
			box-sizing: border-box;
			-webkit-transition: all 160ms;
			transition: all 160ms;
		}

		.item-image img {
			width: 100%;
			height: 100%;
			display: block;
			border: none;
		}

		.item-title {
			line-height: 25px;
			font-size: 18px;
			color: #3D3D3D;
			padding: 22px 0 7px;
			font-family: 'PingFangSC-Regular', "Microsoft YaHei", Tahoma, sans-serif;
			font-weight: normal;
		}

		.item-text {
			color: #9A9A9A;
			font-size: 12px;
			line-height: 17px;
			height: 34px;
			overflow: hidden;
			font-family: 'PingFangSC-Regular', "Microsoft YaHei", Tahoma, sans-serif;
		}

		.item-link {
			font-family: 'PingFangSC-SC', "Microsoft YaHei", Tahoma, sans-serif;
			color: #FFFFFF;
			display: none;
			padding: 6px 20px;
			background-color: #05A6F3;
			margin: 17px auto;
			font-size: 14px;
			line-height: 25px;
			border-radius: 5px;
			-webkit-transition: all 160ms;
			transition: all 160ms;
			font-weight: normal;
		}

		.Service-item:hover {
			border-color: #DEDDDD;
			padding-top: 26px;
			position: relative;
			z-index: 99;
			text-decoration: none;
			box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
			webkit-transform: translate3d(0, -3px, 0);
			transform: translate3d(0, -3px, 0);
		}

		.Service-item:hover .item-image {
			height: 60px;
			width: 60px;
			line-height: 60px;
			margin: 0 auto;
		}

		.Service-item:hover .item-link {
			display: block;
		}

		.Service-item:hover .item-title {
			padding: 10px 0 7px;
		}

		.Service-item + .Service-item {
			margin-left: 50px;
			position: relative;
		}

		.item-tag {
			width: 67px;
			height: 67px;
			color: #FFFFFF;
			position: absolute;
			transform: rotateZ(-315deg);
			top: 9px;
			right: 9px;
			font-size: 12px;
			line-height: 17px;
		}

		.item-background {
			width: 67px;
			height: 67px;
			position: absolute;
			top: 0px;
			right: 0px;
		}

		.item-background img {
			width: 100%;
			height: 100%;
		}

	</style>
</head>
<body style="background: url(${ctx}/static/model/images/login/loginbg2.jpg) no-repeat;background-size: cover;overflow: hidden;">
<div class="title36"><img width="72px" src="${ctx}/static/model/images/login/logo.png" style="margin-right: 10px;" alt="">连云区公共安全监管（应急指挥）云平台</div>

<div class="Service-box">
	<div class="Service-content clearfix">
		<a href="${ctx}/login/zf" class="Service-item">
			<div class="item-image">
				<img src="${ctx}/static/model/images/login/ic001.png" alt="">
			</div>
			<h3 class="item-title">政府端</h3>
			<div class="item-text">政府用户使用该端口登入</div>
			<span class="item-link">登&nbsp;&nbsp;录</span>
		</a>
		<a href="${ctx}/login/qy" class="Service-item">
			<div class="item-image">
				<img src="${ctx}/static/model/images/login/ic002.png" alt="">
			</div>
			<h3 class="item-title">企业端</h3>
			<div class="item-text">企业用户使用该端口登入</div>
			<div class="item-tag" style="display: none;">免手续费</div>
			<span class="item-link">登&nbsp;&nbsp;录</span>
		</a>
		<a onclick="openSgzb()" class="Service-item">
			<div class="item-image">
				<img src="${ctx}/static/model/images/login/sgzb.png" alt="">
			</div>
			<h3 class="item-title">事故直报</h3>
			<div class="item-text">事故直报系统登录入口</div>
			<div class="item-tag" style="display: none;"></div>
			<span class="item-link">登&nbsp;&nbsp;录</span>
		</a>
	</div>
</div>

<div class="footer36">&copy;2019&nbsp;&nbsp;江苏中安联科信息技术有限公司</div>
<script>
	function openSgzb() {
        window.open("http://59.252.192.1/tjzb/tjsy.jsp","_blank");
    }
	
</script>
</body>
</html>
