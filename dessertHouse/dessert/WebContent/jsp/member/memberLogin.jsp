<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>member login page</title>
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
          <li><a href="<%=path%>/jsp/member/home.jsp">首页</a></li>
          <li><a href="<%=path%>/Dessert/storevisitall">店铺</a></li>
          <li><a href="">商品</a></li>
          <li class="current"><a href="<%=path%>/jsp/member/memberLogin.jsp">登录</a></li>
        </ul>
      </nav>
    </header>
    
    <div id="sidebar_container">
		<div class="sidebar">
			 <h3 style="font-size:42px;">News</h3>
			  <h4>New Website Launched</h4>
			  <h5>March 16th, 2016</h5>
		</div>
	</div>
	
    <div id="content">
		<h2 align="center">欢迎来到我们的甜品屋</h2>
		<p></p>
		<form action="<%=path %>/Dessert/memberlogin" method="post" style="margin-left:130px;">
          <div class="form_settings">
			<p><span>会员账号</span><input type="text" name="member" value="" /></p>
            <p><span>登陆密码</span><input type="password" name="password" value="" /></p>
            <p style="padding-top: 15px"><span>&nbsp;</span>
            	<% if(request.getAttribute("loginFailed")!=null){
            		%>会员账号密码错误<% 
            	}%>
            </p>
            <p style="padding-top: 15px"><span>&nbsp;</span>
            	<% if(request.getAttribute("withdrawRes")!=null){
            		%>会员卡注销成功<% 
            	}%>
            </p>
            <p style="padding-top: 30px;"><span>&nbsp;</span>
				<a href="<%=path %>/jsp/member/memberRegister.jsp" style="margin-left:-110px;font-size:12px;">没有账号？go注册</a>
				<input class="submit" type="submit" name="login" value="登录" />
			</p>
          </div>
        </form>
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