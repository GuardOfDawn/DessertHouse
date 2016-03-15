<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<body>
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
      	<jsp:useBean id="productList"
			type="dessert.business.ListBean"
			scope="session"></jsp:useBean>
		<jsp:useBean id="item"
			class="dessert.models.Product"
			scope="page"></jsp:useBean>
        <h2 align="center">产品信息</h2>
		<table id="productData" style="width:100%; border-spacing:0;">
		  <tr><th>编号</th><th>产品名称</th><th>产品类型</th><th>产品图片</th><th>修改</th><th>删除</th></tr>
		  <%for(int i=0;i<productList.getListBean().size();i++){
				pageContext.setAttribute("item", productList.getBean(i)); 
				  %>
		  <tr>
		  	  <td><jsp:getProperty name="item" property="productId" /></td>
		  	  <td><jsp:getProperty name="item" property="productName" /></td>
		      <td><jsp:getProperty name="item" property="productType" /></td>
		      <td><img width="60" height="40" src="<%=path %><jsp:getProperty name="item" property="imagePath" />"/></td>
		      <td>
		      	<input class="submit" type="button" name="modifyStore" value="修改"
		      		onclick="modifyRow('<jsp:getProperty name="item" property="productId" />')" />
		      </td>
		      <td>
		      	<input class="submit" type="button" name="deleteStore" value="删除"
		      		onclick="deleteRow('<jsp:getProperty name="item" property="productId" />')" />
		      </td>
		  </tr>
		  <%} %>
		</table>
		<p style="padding-top: 15px"><span>&nbsp;</span>
           	<% String deleteRes = (String)request.getAttribute("deleteRes");
           	if(deleteRes!=null&&deleteRes.equals("true")){
           		%>产品<%=request.getAttribute("deleteProductId") %>删除成功<%
           	}
           	else if(deleteRes!=null&&deleteRes.equals("false")){
           		%>产品<%=request.getAttribute("deleteProductId") %>删除失败,有包含该产品的预订单或购物单<%
           	}%>
        </p>
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
  <script type="text/javascript">
	function modifyRow(productId)
    {  
		window.location.href='<%=path%>/jsp/headAttendant/modifyProduct.jsp?productId='+productId
    }
	function deleteRow(productId)
    {  
		window.location.href='<%=path%>/Dessert/productdelete?productId='+productId
    }
  </script>
</body>
</html>