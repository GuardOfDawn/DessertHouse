<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.Bill"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>bill view page</title>
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
	    <jsp:useBean id="billList"
			type="dessert.business.ListBean"
			scope="request"></jsp:useBean>
		<jsp:useBean id="bill"
			class="dessert.models.Bill"
			scope="page"></jsp:useBean>
		<jsp:useBean id="store"
			class="dessert.models.Store"
			scope="page"></jsp:useBean>
		<h2 align="center">购买单据查看</h2>
		<table id="rechargeTable" style="width:100%; border-spacing:0;">
		  <tr><th>编号</th><th>购买时间</th><th>店铺名称</th><th>购买金额</th><th>优惠折扣</th><th>使用积分</th><th>折后金额</th><th>奖励积分</th><th>查看详单</th></tr>
		  <%if(billList.getListBean()!=null){ 
		      for(int i=0;i<billList.getListBean().size();i++){
		        pageContext.setAttribute("bill", billList.getBean(i));
		        pageContext.setAttribute("store", ((Bill)billList.getBean(i)).getBillStore());
		      %>
		  <tr>
		    <td><jsp:getProperty name="bill" property="billId" /></td>
		  	<td><jsp:getProperty name="bill" property="billTime" /></td>
		  	<td><jsp:getProperty name="store" property="storeName" /></td>
		  	<td><jsp:getProperty name="bill" property="billCost" /></td>
		  	<td><jsp:getProperty name="bill" property="favorRate" /></td>
		  	<td><jsp:getProperty name="bill" property="bonusUsed" /></td>
		  	<td><jsp:getProperty name="bill" property="costAfterDiscount" /></td>
		  	<td><jsp:getProperty name="bill" property="bonusGiven" /></td>
		  	<td>
		      	<input class="submit" type="button" name="checkOrder" value="查看"
		      		onclick="checkRow('<jsp:getProperty name="bill" property="billId" />')" />
		    </td>
		 </tr>
		  <%  }
		    } %>
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
    function checkRow(billId){
    	
    }
  </script>
</body>
</html>