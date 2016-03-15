<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.Product"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>head attendant page</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css" />
  <!-- modernizr enables HTML5 elements and feature detects -->
  <script type="text/javascript" src="<%=path %>/js/modernizr-1.5.min.js"></script>
</head>
  <div id="main">
    <header>
      <div id="logo"><img width="120" height="120" src="<%=path %>/image/logo.jpg" /></div>
      <nav>
        <ul class="lavaLampWithImage" id="lava_menu">
          <li><a href="<%=path%>/jsp/headAttendant/home.jsp">首页</a></li>
          <li><a href="<%=path%>/Dessert/currentscheduleview">产品计划</a></li>
          <li class="current"><a href="<%=path%>/Dessert/productview">产品管理</a></li>
          <li><a href="<%=path%>/Dessert/userlogout">登出</a></li>
        </ul>
      </nav>
    </header>
    <div id="site_content">
	  <div id="sidebar_container">
        <div class="sidebar">
		 <ul>
			<br></br>
			<li><a href="<%=path%>/Dessert/productview">浏览产品</a></li>
			<br></br>
			<li><a href="<%=path%>/jsp/headAttendant/addProduct.jsp">添加产品</a></li>
		 </ul>
		</div>
      </div>
      <div id="content">
        <jsp:useBean id="productTypeList"
			type="dessert.business.ListBean"
			scope="session"></jsp:useBean>
	  	<jsp:useBean id="newProduct"
			class="dessert.models.Product"
			scope="page"></jsp:useBean>
	  	<%	Product p = (Product) request.getAttribute("newProduct");
	  		if(p==null){
	  		%>
		<h2 align="center">添加产品</h2>
        <form action="<%=path%>/Dessert/productadd" method="post">
          <div class="form_settings" style="margin-left:150px">
            <p><span>产品名称</span><input type="text" name="productName" value="" /></p>
            <p><span>产品类型</span>
              <select id="id" name="productType">
              <%for(int i=0;i<productTypeList.getListBean().size();i++){
            	  %>
            	<option value="<%=String.valueOf(productTypeList.getBean(i))%>"><%=String.valueOf(productTypeList.getBean(i)) %></option>
            	<%} %>
              </select>
            </p>
            <p><span>产品图片</span><input type="file" name="imagePath" /></p>
            <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="ensure" value="确认添加"/></p>
          </div>
        </form>
        <%  }
	  		else{
	  		pageContext.setAttribute("newProduct", p);
	  		%>
	  	<h2 align="center">添加产品成功</h2>
	  	<p><span>产品编号:</span><jsp:getProperty name="newProduct" property="productId" /></p>
	  	<p><span>产品名称:</span><jsp:getProperty name="newProduct" property="productName" /></p>
	  	<p><span>产品类型:</span><jsp:getProperty name="newProduct" property="productType" /></p>
	  	<p><span>产品图片:</span></p>
	  	<p><img width="140" height="120" src="<%=path %><jsp:getProperty name="newProduct" property="imagePath" />"/></p>
	  	<%} %>
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