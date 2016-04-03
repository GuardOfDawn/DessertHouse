<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.Order"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>deal order page</title>
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
          <li><a href="<%=path%>/jsp/branchAttendant/home.jsp">首页</a></li>
          <li><a href="<%=path%>/Dessert/sale">处理销售</a></li>
          <li class="current"><a href="<%=path%>/jsp/branchAttendant/dealOrder.jsp">处理预订</a></li>
          <li><a href="<%=path%>/Dessert/membercheck">会员查看</a></li>
          <li><a href="<%=path%>/jsp/branchAttendant/storeInfo.jsp">店铺信息</a></li>
          <li><a href="<%=path%>/Dessert/userlogout">登出</a></li>
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
	    <h2 align="center">处理预订</h2>
	    <div class="form_settings" >
	      <p align="center">
            <span style="margin-left:80px">输入会员卡号:</span>
            <%if(request.getAttribute("memberId")!=null){ %>
            <input id="memberIdInput" type="text" name="memberId" value="<%=request.getAttribute("memberId") %>" style="margin-left:-50px;width:100px;" />
            <%}
              else{%>
            <input id="memberIdInput" type="text" name="memberId" value="" style="margin-left:-20px;width:100px;" />
            <%} %>
            <input class="submit" type="button" value="查找订单" onclick="retrieveOrderInfo()"/>
          </p>
	    </div>
	    <%String res = (String) request.getAttribute("searchRes");
	      if(res!=null){ 
	        if(res.equals("noMember")){%>
	        <p><strong>对不起，您的输入的会员账户不存在，或者账户已经失效！</strong></p>
	      <%}
	        else{%>
	        <p><strong>对不起，您输入的会员账户没有未处理的预订单！欢迎前来本店购买产品！</strong></p>
	    <%  }
	      }
	      else{%>
	      <%if(request.getAttribute("memberId")!=null){ %>
	    <jsp:useBean id="member"
			type="dessert.models.Member"
			scope="request"></jsp:useBean>
	    <jsp:useBean id="orderForMember"
			type="dessert.business.ListBean"
			scope="request"></jsp:useBean>
	    <jsp:useBean id="order"
			class="dessert.models.Order"
			scope="page"></jsp:useBean>
	    <jsp:useBean id="store"
			class="dessert.models.Store"
			scope="page"></jsp:useBean>
		  <div id="memberInfoBox" class="form_settings" style="margin-left:80px">
            <p><span>会员姓名:</span><input id="nameAutoInput" type="text" value="<jsp:getProperty name="member" property="memberName" />" readonly="readonly" style="margin-left:-14px;width:80px" /></p>
		  	<p><span>会员等级:</span><input id="levelAutoInput" type="text" value="<jsp:getProperty name="member" property="memberLevel" />级会员" readonly="readonly" style="margin-left:-14px;width:80px" /></p>
	        <p><span>会员卡余额:</span><input id="favorRateAutoInput" id="favorRate" type="text" value="<jsp:getProperty name="member" property="residual" />" readonly="readonly" style="margin-left:-14px;width:80px;"/></p>
		  	<p><span>会员积分:</span><input id="bonusAutoInput" type="text" value="<jsp:getProperty name="member" property="bonusPoint" />" readonly="readonly" style="margin-left:-14px;width:80px;"/></p>
          </div>
	      <table id="approvedScheduleTable" style="width:100%; border-spacing:0;">
		  <tr><th>编号</th><th>下单时间</th><th>预订日期</th><th>店铺名称</th><th>折扣前价格</th><th>折扣率</th><th>使用积分</th><th>折扣后价格</th><th>查看</th><th>退订</th><th>结算</th></tr>
		  <%if(orderForMember.getListBean()!=null){
		      for(int i=0;i<orderForMember.getListBean().size();i++){
				pageContext.setAttribute("order", orderForMember.getBean(i));
				pageContext.setAttribute("store", ((Order)orderForMember.getBean(i)).getOrderStore());
				 %>
		  <tr>
		  	  <td><jsp:getProperty name="order" property="orderId" /></td>
		  	  <td><jsp:getProperty name="order" property="orderTime" /></td>
		  	  <td><jsp:getProperty name="order" property="targetDay" /></td>
		  	  <td><jsp:getProperty name="store" property="storeName" /></td>
		      <td><jsp:getProperty name="order" property="orderCost" /></td>
		      <td><jsp:getProperty name="order" property="favorRate" /></td>
		      <td><jsp:getProperty name="order" property="bonusUsed" /></td>
		      <td><jsp:getProperty name="order" property="costAfterDiscount" /></td>
			  <td>
		      	<input class="submit" type="button" name="checkOrder" value="查看"
		      		onclick="checkRow('<jsp:getProperty name="order" property="orderId" />')" />
		      </td>
		      <td>
		      	<input class="submit" type="button" name="checkOrder" value="退订"
		      		onclick="deleteRow('<jsp:getProperty name="order" property="orderId" />')" />
		      </td>
		      <td>
		      	<input class="submit" type="button" name="checkOrder" value="结算"
		      		onclick="payRow('<jsp:getProperty name="order" property="orderId" />')" />
		      </td>
		  </tr>
		  <%    }
		      } 
		    }%>
		</table>
		<div>
		
		</div>
	    <%} %>
	    <div>
	      <%if(request.getAttribute("payRes")!=null){ %>
	      <p>预订单结算成功！</p>
	      <%} %>
	    </div>
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
    function retrieveOrderInfo(){
    	var memberId = document.getElementById("memberIdInput").value;
    	window.location.href='<%=path%>/Dessert/searchmemberorder?memberId='+memberId;
    }
    function checkRow(orderId){
    	
    }
	function deleteRow(orderId){
    	
    }
    function payRow(orderId){
    	var memberId = document.getElementById("memberIdInput").value;
    	window.location.href='<%=path%>/Dessert/payorder?orderId='+orderId+'&memberId='+memberId;
	}
  </script>
</body>
</html>