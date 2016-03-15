<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <% String path = request.getContextPath();%>
  <title>system manager page</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css" />
  <!-- modernizr enables HTML5 elements and feature detects -->
  <script type="text/javascript" src="<%=path %>/js/modernizr-1.5.min.js"></script>
</head>

<body>
  <div id="main">
    <header>
      <div id="logo"><img width="120" height="120" src="<%=path %>/image/logo.jpg" /></div>
      <nav>
        <ul class="lavaLampWithImage" id="lava_menu">
          <li class="current"><a href="<%=path%>/jsp/systemManager/home.jsp">首页</a></li>
          <li><a href="<%=path%>/Dessert/storeview">店铺管理</a></li>
          <li><a href="<%=path%>/Dessert/salespersonview">店员管理</a></li>
          <li><a href="<%=path%>/Dessert/userlogout">登出</a></li>
        </ul>
      </nav>
    </header>
    <div id="site_content">
      <ul class="slideshow">
        <li class="show"><img width="950" height="550" src="<%=path %>/image/dessert-bg1.JPG" alt="&quot;Welcome! Our deal system manager!&quot;" /></li>
        <li><img width="950" height="550" src="<%=path %>/image/dessert-bg2.JPG" alt="&quot;It's a really nice day. Let's start our work.&quot;" /></li>
        <li><img width="950" height="550" src="<%=path %>/image/dessert-bg3.JPG" alt="&quot;This system will provide you with lots of convenience.&quot;" /></li>
      </ul>
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