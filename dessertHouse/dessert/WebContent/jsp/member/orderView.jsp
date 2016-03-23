<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.Order"%>
    <%@page import="dessert.models.OrderDetail"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>order view page</title>
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
      <div id="logo"><img id="logoImg" width="120" height="120" src="<%=path %>/image/logo.jpg" /></div>
      <nav>
        <ul class="lavaLampWithImage" id="lava_menu">
          <li><a href="<%=path%>/jsp/member/home.jsp">首页</a></li>
          <li><a href="<%=path%>/Dessert/storevisitall">店铺</a></li>
          <li><a href="">商品</a></li>
          <li class="current"><a href="<%=path%>/Dessert/orderview">账单</a></li>
          <li><a href="<%=path%>/Dessert/memberlogin">我的信息</a></li>
          <li><a href="<%=path%>/Dessert/memberlogout">登出</a></li>
        </ul>
      </nav>
    </header>
    <div id="site_content">
      <div id="sidebar_container">
        <div class="sidebar">
		 <ul>
			<br></br>
			<li><a href="<%=path%>/Dessert/orderview">预订信息</a></li>
			<br></br>
			<li><a href="<%=path%>/Dessert/billview">消费信息</a></li>
			<br></br>
			<li><a href="<%=path%>/Dessert/rechargeview">充值信息</a></li>
		 </ul>
		</div>
      </div>
	  <div id="content">
	    <jsp:useBean id="orderMade"
			type="dessert.business.ListBean"
			scope="request"></jsp:useBean>
        <jsp:useBean id="orderPaid"
			type="dessert.business.ListBean"
			scope="request"></jsp:useBean>
		<jsp:useBean id="order"
			class="dessert.models.Order"
			scope="page"></jsp:useBean>
		<jsp:useBean id="orderDetail"
			class="dessert.models.OrderDetail"
			scope="page"></jsp:useBean>
		<jsp:useBean id="store"
			class="dessert.models.Store"
			scope="page"></jsp:useBean>
			
		<h2 align="center">未支付的预订单</h2>
		<table id="approvedScheduleTable" style="width:100%; border-spacing:0;">
		  <tr><th>编号</th><th>预订时间</th><th>店铺名称</th><th>折扣前价格</th><th>折扣率</th><th>使用积分</th><th>折扣后价格</th><th>查看</th></tr>
		  <%if(orderMade.getListBean()!=null){
		      for(int i=0;i<orderMade.getListBean().size();i++){
				pageContext.setAttribute("order", orderMade.getBean(i));
				pageContext.setAttribute("store", ((Order)orderMade.getBean(i)).getOrderStore());
				 %>
		  <tr>
		  	  <td><jsp:getProperty name="order" property="orderId" /></td>
		  	  <td><jsp:getProperty name="order" property="orderTime" /></td>
		  	  <td><jsp:getProperty name="store" property="storeName" /></td>
		      <td><jsp:getProperty name="order" property="orderCost" /></td>
		      <td><jsp:getProperty name="order" property="favorRate" /></td>
		      <td><jsp:getProperty name="order" property="bonusUsed" /></td>
		      <td><jsp:getProperty name="order" property="costAfterDiscount" /></td>
			  <td>
		      	<input class="submit" type="button" name="checkOrder" value="查看"
		      		onclick="checkRow('<jsp:getProperty name="order" property="orderId" />')" />
		      </td>
		  </tr>
		  <%  } 
		    }%>
		</table>
		
		<h2 align="center">历史预订单</h2>
		<table id="disapprovedScheduleTable" style="width:100%; border-spacing:0;">
		  <tr><th>编号</th><th>店铺</th><th>生效时间</th><th>终止时间</th><th>状态</th><th>查看</th></tr>
		  <%if(orderPaid.getListBean()!=null){
		      for(int i=0;i<orderPaid.getListBean().size();i++){
				pageContext.setAttribute("item", orderPaid.getBean(i));
				pageContext.setAttribute("store", ((Order)orderPaid.getBean(i)).getOrderStore());
				  %>
		  <tr>
		  	  <td><jsp:getProperty name="order" property="orderId" /></td>
		  	  <td><jsp:getProperty name="order" property="orderTime" /></td>
		  	  <td><jsp:getProperty name="store" property="storeName" /></td>
		      <td><jsp:getProperty name="order" property="orderCost" /></td>
		      <td><jsp:getProperty name="order" property="favorRate" /></td>
		      <td><jsp:getProperty name="order" property="bonusUsed" /></td>
		      <td><jsp:getProperty name="order" property="costAfterDiscount" /></td>
			  <td>
		      	<input class="submit" type="button" name="checkOrder" value="查看"
		      		onclick="checkRow('<jsp:getProperty name="order" property="orderId" />')" />
		      </td>
		  </tr>
		  <% }
		   }%>
		</table>	
		
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
    function checkRow(orderId){
    	
    }
  </script>
</body>
</html>