<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>home page</title>
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
          <li class="current"><a href="<%=path%>/jsp/member/home.jsp">首页</a></li>
          <li><a href="<%=path%>/Dessert/storevisitall">店铺</a></li>
          <li><a href="">商品</a></li>
          <%if(session.getAttribute("member")==null){ %>
          <li><a href="<%=path%>/jsp/member/memberLogin.jsp">登录</a></li>
          <%}
            else{%>
          <li><a href="<%=path%>/Dessert/orderview">账单</a></li>
          <li><a href="<%=path%>/Dessert/memberlogin">我的信息</a></li>
          <li><a href="<%=path%>/Dessert/memberlogout">登出</a></li>
          <%} %>
        </ul>
      </nav>
    </header>
    <div id="site_content">
      <ul class="slideshow">
        <li class="show"><img width="950" height="650" src="<%=path %>/image/dessert-bg1.JPG" alt="&quot;Welcome to our dessert house website!&quot;" /></li>
        <li><img width="950" height="650" src="<%=path %>/image/dessert-bg2.JPG" alt="&quot;We are sure you will have a pleasent journey here.&quot;" /></li>
        <li><img width="950" height="650" src="<%=path %>/image/dessert-bg3.JPG" alt="&quot;We will provide you with the best desserts.&quot;" /></li>
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