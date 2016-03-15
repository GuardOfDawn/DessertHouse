<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>user login page</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css" />
  <!-- modernizr enables HTML5 elements and feature detects -->
  <script type="text/javascript" src="<%=path%>/js/modernizr-1.5.min.js"></script>
</head>

<body>
  <div id="main" align="center">
    <div id="site_middle">
		<h2>欢迎来到你的甜品屋</h2>
		<h3>打造世界最好的甜品屋</h3>
		<form action="<%=path%>/Dessert/userlogin" method="post">
          <div class="form_settings">
			<p><span>用户名</span><input type="text" name="userId" value="" /></p>
            <p><span>用户密码</span><input type="password" name="password" value="" /></p>
            <p style="padding-top: 15px"><span>&nbsp;</span>
            	<% if(request.getAttribute("loginFailed")!=null){
            		%>用户名密码错误<% 
            	}%>
            </p>
            <p style="padding-top: 15px"><span>&nbsp;</span>
				<input class="submit" type="submit" name="login" value="登录"/>
			</p>
          </div>
        </form>
    </div>
  </div>
  <!-- javascript at the bottom for fast page loading -->
  <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path%>/js/jquery.easing.min.js"></script>
  <script type="text/javascript" src="<%=path%>/js/jquery.lavalamp.min.js"></script>
  <script type="text/javascript" src="<%=path%>/js/image_fade.js"></script>
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