<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.Store"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>system manager page</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css" />
  <!-- modernizr enables HTML5 elements and feature detects -->
  <script type="text/javascript" src="<%=path%>/js/modernizr-1.5.min.js"></script>
</head>
<body>
  <div id="main">
    <header>
      <div id="logo"><img width="120" height="120" src="<%=path%>/image/logo.jpg" /></div>
      <nav>
        <ul class="lavaLampWithImage" id="lava_menu">
          <li><a href="<%=path%>/jsp/systemManager/home.jsp">首页</a></li>
          <li class="current"><a href="<%=path%>/Dessert/storeview">店铺管理</a></li>
          <li><a href="<%=path%>/Dessert/salespersonview">店员管理</a></li>
          <li><a href="<%=path%>/Dessert/userlogout">登出</a></li>
        </ul>
      </nav>
    </header>
    <div id="site_content">
	  <div id="sidebar_container">
        <div class="sidebar">
		 <ul>
			<br></br>
			<li><a href="<%=path%>/Dessert/storeview">浏览店铺</a></li>
			<br></br>
			<li><a href="<%=path%>/jsp/systemManager/addStore.jsp">添加店铺</a></li>
		 </ul>
		</div>
      </div>
	  <div id="content">
	  <jsp:useBean id="newStore"
			class="dessert.models.Store"
			scope="page"></jsp:useBean>
	  	<%	Store s = (Store) request.getAttribute("newStore");
	  	if(s==null){
	  		%>
		<h2 align="center">添加店铺</h2>
        <form action="<%=path%>/Dessert/storeadd" method="post">
          <div class="form_settings" style="margin-left:150px">
            <p><span>店铺名称</span><input type="text" name="storeName" value="" /></p>
            <p><span>联系方式</span><input type="text" name="storeTel" value="" /></p>
            <p><span>所在省</span><input type="text" name="province" value="" /></p>
            <p><span>所在市</span><input type="text" name="city" value="" /></p>
            <p><span>店铺地址</span><input type="text" name="storeLoc" value="" /></p>
            <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="ensure" value="确认添加"/></p>
          </div>
        </form>
        <%
	  	} 
	  	else{
	  		pageContext.setAttribute("newStore",s);%>
	  	<h2 align="center">添加店铺成功</h2>
	  	<p><span>店铺编号:</span><jsp:getProperty name="newStore" property="storeId" /></p>
	  	<p><span>店铺名称:</span><jsp:getProperty name="newStore" property="storeName" /></p>
	  	<p><span>联系方式:</span><jsp:getProperty name="newStore" property="storeTel" /></p>
	  	<p><span>所在省:</span><jsp:getProperty name="newStore" property="province" /></p>
	  	<p><span>所在市:</span><jsp:getProperty name="newStore" property="city" /></p>
	  	<p><span>店铺地址:</span><jsp:getProperty name="newStore" property="storeLoc" /></p>
	  	<%} %>
	  </div>
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
  <script type="text/javascript">
	function a(){
		window.open("deleteStoreAlert.html",'go','scrollbars=yes,resizable=yes,width=720,height=400')
	}
  </script>
</body>
</html>