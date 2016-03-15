<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.Salesperson"%>
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
          <li><a href="<%=path%>/Dessert/storeview">店铺管理</a></li>
          <li class="current"><a href="<%=path%>/Dessert/salespersonview">店员管理</a></li>
          <li><a href="<%=path%>/Dessert/userlogout">登出</a></li>
        </ul>
      </nav>
    </header>
    <div id="site_content">
	  <div id="sidebar_container">
        <div class="sidebar">
		 <ul>
			<br></br>
			<li><a href="<%=path%>/Dessert/salespersonview">浏览店员</a></li>
			<br></br>
			<li><a href="<%=path%>/jsp/systemManager/addSalesperson.jsp">添加店员</a></li>
		 </ul>
		</div>
      </div>
      <div id="content">
        <jsp:useBean id="storeIdList"
			type="dessert.business.ListBean"
			scope="session"></jsp:useBean>
		<jsp:useBean id="newSalesperson"
			class="dessert.models.Salesperson"
			scope="page"></jsp:useBean>
		<jsp:useBean id="store"
			class="dessert.models.Store"
			scope="page"></jsp:useBean>
		<%	Salesperson s = (Salesperson) request.getAttribute("newSalesperson");
	  		if(s==null){
	  		%>
		<h2 align="center">添加店员</h2>
        <form action="<%=path%>/Dessert/salespersonadd" method="post">
          <div class="form_settings" style="margin-left:150px">
            <p><span>店员名称</span><input type="text" name="salespersonName" value="" /></p>
            <p><span>店员性别</span>
              <select id="id" name="salespersonGender">
            	<option value="0">男</option>
            	<option value="1">女</option>
              </select>
            </p>
            <p><span>年龄</span><input type="text" name="salespersonAge" value="" /></p>
            <p><span>店员类型</span>
              <select id="id" name="salespersonType">
            	<option value="0">总店服务员</option>
            	<option value="1">分店服务员</option>
              </select>
            </p>
            <p><span>所属店铺</span>
              <select id="id" name="salespersonStore">
              <%for(int i=1;i<=storeIdList.getListBean().size();i++){
            	  %>
            	<option value="<%=String.valueOf(storeIdList.getBean(i-1))%>"><%=String.valueOf(storeIdList.getBean(i-1)) %></option>
            	<%} %>
              </select>
            </p>
            <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="ensure" value="确认添加"/></p>
          </div>
        </form>
        <%}
  		else{
  			pageContext.setAttribute("newSalesperson",s);
  			pageContext.setAttribute("store",s.getStore());
  		%>
	  		<h2 align="center">添加店员成功</h2>
		  	<p><span>店员编号:<jsp:getProperty name="newSalesperson" property="salespersonId" /></span></p>
		  	<p><span>店员名称:<jsp:getProperty name="newSalesperson" property="salespersonName" /></span></p>
		  	<%if(s.getSalespersonGender()==0){ %>
		  	<p><span>店员性别:男</span></p>
		  	<%}
		  	  else{%>
		  	 <p><span>店员性别:女</span></p>
		  	 <%} %>
		  	<p><span>店员年龄:<jsp:getProperty name="newSalesperson" property="salespersonAge" /></span></p>
		  	<%if(s.getSalespersonLevel()==0){ %>
		  	<p><span>店员类型:总店服务员</span></p>
		  	<%}
		  	  else{%>
		  	 <p><span>店员类型:分店服务员</span></p>
		  	 <%} %>
		  	<p><span>所属店铺:<jsp:getProperty name="store" property="storeId" /></span></p>
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
</body>
</html>