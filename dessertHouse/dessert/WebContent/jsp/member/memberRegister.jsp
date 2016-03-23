<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>member register page</title>
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
		<h2 align="center">会员注册</h2>
		<p></p>
		<form action="<%=path %>/Dessert/memberregister" method="post" style="margin-left:130px;">
          <div class="form_settings">
          	<%Member m = (Member)request.getAttribute("memberInput");
          	  if(m==null){%>
			<p><span>会员名称</span><input type="text" name="memberName" value="" /></p>
			<p><span>会员性别</span>
              <select name="memberGender">
            	<option value="null" selected="selected">请选择性别</option>
            	<option value="0">男</option>
            	<option value="1">女</option>
              </select>
            </p>
            <p><span>会员年龄</span><input type="text" id="ageInput" name="memberAge" value="" /></p>
            <p><span>联系方式</span><input type="text" id="telInput" name="memberTel" value="" /></p>
            <p><span>会员住址</span><input type="text" name="memberLoc" value="" /></p>
			<%}
          	  else{
          	  %>
          	<p><span>会员名称</span><input type="text" name="memberName" value="<%=m.getMemberName() %>" /></p>
			<p><span>会员性别</span>
              <select name="memberGender">
              <%if(m.getMemberGender()==0){ %>
            	<option value="null">请选择性别</option>
            	<option value="0" selected="selected">男</option>
            	<option value="1">女</option>
              <%}
                else { %>
                <option value="null">请选择性别</option>
            	<option value="0">男</option>
            	<option value="1" selected="selected">女</option>
              <%} %>
              </select>
            </p>
            <p><span>会员年龄</span><input type="text" name="memberAge" value="<%=m.getMemberAge() %>" /></p>
            <p><span>联系方式</span><input type="text" name="memberTel" value="<%=m.getMemberTel() %>" /></p>
            <p><span>会员住址</span><input type="text" name="memberLoc" value="<%=m.getMemberLoc() %>" /></p>
          	<%} %>
            <p><span>登录密码</span><input type="password" name="password" value="" /></p>
            <p style="padding-top: 30px;"><span>&nbsp;</span>
				<input class="submit" type="submit" name="register" value="立即注册" />
			</p>
			<p style="padding-top: 15px"><span>&nbsp;</span>
            	<% if(request.getAttribute("errorType")!=null){%>
            		会员注册失败，<%=request.getAttribute("errorType") %>
            	<%}%>
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
  <script type="text/javascript">
    function onlyNumber(event){
	    var keyCode = event.keyCode;
	    if(keyCode<48 || keyCode>57){
	        event.keyCode = 0;
	    }
	}
	$(function(){
	    $("#ageInput").keydown(onlyNumber);
	});
	$(function(){
	    $("#telInput").keydown(onlyNumber);
	});
  </script>
</body>
</html>