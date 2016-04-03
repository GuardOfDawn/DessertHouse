<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>bill check page</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css" />
  <link rel="stylesheet" type="text/css" href="<%=path %>/css/lightbox.css" />
  <!-- modernizr enables HTML5 elements and feature detects -->
  <script type="text/javascript" src="<%=path %>/js/modernizr-1.5.min.js"></script>
</head>
<body>
  <div id="main">
    <header>
      <div id="logo"><img width="120" height="120" src="<%=path %>/image/logo.jpg" /></div>
      <nav>
        <ul class="lavaLampWithImage" id="lava_menu">
          <li><a href="<%=path%>/jsp/branchAttendant/home.jsp">首页</a></li>
          <li><a href="<%=path%>/Dessert/sale">处理销售</a></li>
          <li><a href="<%=path%>/jsp/branchAttendant/dealOrder.jsp">处理预订</a></li>
          <li><a href="<%=path%>/Dessert/membercheck">会员查看</a></li>
          <li class="current"><a href="<%=path%>/jsp/branchAttendant/storeInfo.jsp">店铺信息</a></li>
          <li><a href="<%=path%>/Dessert/userlogout">登出</a></li>
        </ul>
      </nav>
    </header>
    <div id="site_content" style="margin-left:20px;">
	  <jsp:useBean id="store"
			class="dessert.models.Store"
			scope="session"></jsp:useBean>
      <div id="sidebar_container">
		<div class="sidebar" style="width:200px;">
          <h2>店铺信息</h2>
          <p><span>店铺编号:</span><jsp:getProperty name="store" property="storeId" /></p>
	  	  <p><span>店铺名称:</span><jsp:getProperty name="store" property="storeName" /></p>
	  	  <p><span>联系方式:</span><jsp:getProperty name="store" property="storeTel" /></p>
	  	  <p><span>所在省:</span><jsp:getProperty name="store" property="province" /></p>
	  	  <p><span>所在市:</span><jsp:getProperty name="store" property="city" /></p>
	  	  <p><span>店铺地址:</span><jsp:getProperty name="store" property="storeLoc" /></p>
		  </div>
	  </div>
      <div id="content" style="margin-left:20px;">
        <img width="520" height="340" src="<%=path %>/<jsp:getProperty name="store" property="imagePath" />" />
	  </div>
    </div>
  </div>
  <!-- javascript at the bottom for fast page loading -->
  <script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/js/jquery.easing.min.js"></script>
  <script type="text/javascript" src="<%=path %>/js/jquery.lavalamp.min.js"></script>
  <script type="text/javascript" src="<%=path %>/js/image_fade.js"></script>
  <script type="text/javascript">
    $(function() {
      $("#lava_menu").lavaLamp({
        fx: "backout",
        speed: 200
      });
    });
  </script>
</body>
</html>