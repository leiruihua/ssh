<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
	<link rel="stylesheet" type="text/css" href="${path}/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../css/left_menu.css">
	
	<script type="text/javascript" src="${path}/common/js/require.js" data-main="${path}/common/js/main.js" async="false"></script>
</head>
<body class="easyui-layout">
		
<div data-options="region:'north',split:false" 	  title=""    style="overflow: hidden; height: 70px;
        background: url(../images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        
         <div style="padding-left:10px;padding-top:17px; font-size: 20px; "><img src="../images/blocks.gif" width="20" height="20" align="absmiddle" />传销管理系统</div>
</div>
<!--  <div data-options="region:'south',split:false"    title=""    style="height:70px;background: #D2E0F2;"></div>-->
<!--  <div data-options="region:'east',split:true" title="East" style="width:100px;"></div>-->
<div data-options="region:'west',split:true"      title="导航菜单"   style="width:200px;" href="left_menu.jsp"></div>
<div data-options="region:'center'"               title="" style="background:#eee;overflow:hidden"></div>
<div class="easyui-tabs" fit="true" border="false"></div>

</body>
</html>