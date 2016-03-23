<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.OrderDetail"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>order form page</title>
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
          <li class="current"><a href="<%=path%>/Dessert/storevisitall">店铺</a></li>
          <li><a href="">商品</a></li>
          <li><a href="<%=path%>/Dessert/orderview">账单</a></li>
          <li><a href="<%=path%>/Dessert/memberlogin">我的信息</a></li>
          <li><a href="<%=path%>/Dessert/memberlogout">登出</a></li>
        </ul>
      </nav>
    </header>
    <div id="site_content">
      <div id="sidebar_container">
		<div class="sidebar">
			 <h3 style="font-size:42px;">News</h3>
			  <h4>New Website Launched</h4>
			  <h5>March 16th, 2016</h5>
		</div>
	  </div>
	  
	  <div id="content">
	    <jsp:useBean id="store"
			class="dessert.models.Store"
			scope="request"></jsp:useBean>
	  <%if(request.getAttribute("orderProductFail")==null){ %>
	    <jsp:useBean id="orderItemList"
			type="dessert.business.ListBean"
			scope="session"></jsp:useBean>
	    <jsp:useBean id="item"
			class="dessert.models.OrderDetail"
			scope="page"></jsp:useBean>
	    <jsp:useBean id="product"
			class="dessert.models.Product"
			scope="page"></jsp:useBean>
	    <jsp:useBean id="member"
			class="dessert.models.Member"
			scope="session"></jsp:useBean>
	    <h2 align="center">确认预订订单</h2>
        <p><strong>tip:如果退出该界面，您的预订将失效！</strong></p>
      	<form action="ensureorder">
          <div class="form_settings" >
        	<p><span>会员卡号</span><input type="text" name="memberId" readonly="readonly" value="<jsp:getProperty name="member" property="memberId" />" /></p>
            <p><span>会员姓名</span><input type="text" readonly="readonly" value="<jsp:getProperty name="member" property="memberName" />" /></p>
            <p><span>会员等级</span><input type="text" readonly="readonly" value="<jsp:getProperty name="member" property="memberLevel" />级会员" /></p>
            <p style="display:none"><span>店铺编号</span><input type="text" id="storeId" name="storeId" readonly="readonly" value="<jsp:getProperty name="store" property="storeId" />" /></p>
            <p><span>店铺名称</span><input type="text" readonly="readonly" value="<jsp:getProperty name="store" property="storeName" />" /></p>
            <p><span>订单详情</span></p>
            <table id="myBusinessCartTable" style="width:100%; border-spacing:0;margin-left:auto;margin-right:auto;">
	          <tr><th>产品编号</th><th>产品名称</th><th>产品类型</th><th>产品图片</th><th>产品价格</th><th>预订数量</th><th>条目价格</th></tr>
	        
            <%double total = 0;
              double subTotal = 0;
              for(int i=0;i<orderItemList.getListBean().size();i++){ 
            	OrderDetail detail = (OrderDetail)orderItemList.getBean(i);
            	pageContext.setAttribute("item", orderItemList.getBean(i));
            	pageContext.setAttribute("product", detail.getProduct());
            	subTotal = detail.getProductPrice()*detail.getProductCount();
            	request.setAttribute("subTotal", subTotal);
            	total += subTotal;
            %>
              <tr>
		  	    <td><jsp:getProperty name="product" property="productId" /></td>
		  	    <td><jsp:getProperty name="product" property="productName" /></td>
		        <td><jsp:getProperty name="product" property="productType" /></td>
		        <td><img width="60" height="40" src="<%=path %><jsp:getProperty name="product" property="imagePath" />"/></td>
		        <td><jsp:getProperty name="item" property="productPrice" /></td>
		        <td><jsp:getProperty name="item" property="productCount" /></td>
		        <td><%=subTotal %></td>
		      </tr>
            <%}
              double totalAfterDiscount = total*Double.parseDouble(String.valueOf(request.getAttribute("favorRate")));
            %>
            </table>
            <p><span>折扣前总价</span><input type="text" name="total" readonly="readonly" value="<%=total %>" /></p>
            <p><span>会员折扣率</span><input type="text" name="favorRate" readonly="readonly" value="<%=request.getAttribute("favorRate") %>" /></p>
            <p><span>折扣后总价</span><input id="totalAfterDiscount" name="totalAfterDiscount" type="text" readonly="readonly" value="<%=totalAfterDiscount %>" /></p>
            <p><span>会员积分</span><input id="memberBonusUsed" name="memberBonusUsed" type="text" value="0" onblur="checkBonus()"/>(拥有积分：<strong id="memberBonus"><jsp:getProperty name="member" property="bonusPoint" /></strong>)</p>
            <p id="bonusAlert" style="display:none"><strong>使用的积分不能超过您拥有的积分！</strong></p>
            <p><span>最终价格</span><input type="text" id="finalTotal" name="finalTotal" readonly="readonly" value="<%=totalAfterDiscount %>" /></p>
            <div>
              <input class="submit" type="button" value="取消预订" onclick="cancelOrder()"/>
              <input class="submit" type="submit" value="确认订单"/>
            </div>
          </div>
      	</form>
	  <%}
	    else{%>
	      <p><strong>对不起，经过系统的检测，您的预订金额超过了会员卡余额，请充值后再预订！或者减少预订产品的数量。</strong></p>
          <form action="<%=path%>/Dessert/storevisitall">
            <div class="form_settings">
              <input class="submit" type="button" value="前往充值" onclick="gotoRecharge()"/>
              <input class="submit" type="submit" value="返回店铺" />
            </div>
          </form>
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
  </script><script type="text/javascript">
    function checkBonus(){
		document.getElementById("bonusAlert").style.display='none';
    	var bonusUsed = parseInt(document.getElementById("memberBonusUsed").value);
    	var bonusOwn = parseInt(document.getElementById("memberBonus").innerHTML);
    	if(bonusUsed<0){
    		document.getElementById("memberBonusUsed").value=0;
    		bonusUsed = 0;
    	}
    	if(bonusUsed>bonusOwn){
    		document.getElementById("memberBonusUsed").value=bonusOwn;
    		document.getElementById("bonusAlert").style.display='block';
    		bonusUsed=bonusOwn;
    	}
    	var finalTotal = parseFloat(document.getElementById("totalAfterDiscount").value);
    	finalTotal -= 0.1*bonusUsed;
    	document.getElementById("finalTotal").value=finalTotal.toFixed(2);
    }
    function cancelOrder(){
    	var storeId = document.getElementById("storeId").value;
    	window.location.href='<%=path%>/Dessert/storevisitone?storeId='+storeId;
    }
    function gotoRecharge(){
    	window.location.href='<%=path%>/Dessert/memberlogin';
    }
  </script>
</body>
</html>