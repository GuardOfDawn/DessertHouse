<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.Member"%>
    <%@page import="dessert.models.Bill"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>bill check page</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css" />
  <link rel="stylesheet" type="text/css" href="<%=path %>/css/lightbox.css" />
  <!-- modernizr enables HTML5 elements and feature detects -->
  <script type="text/javascript" src="<%=path %>/js/modernizr-1.5.min.js"></script>
  <script type="text/javascript" src="<%=path %>/calc/calendar.js"></script>
  <script type="text/javascript" src="<%=path %>/calc/calendar-en.js"></script>
  <link type="text/css" rel="stylesheet" href="<%=path %>/calc/calendar-system.css"/>
</head>
<body>
  <div id="main">
    <header>
      <div id="logo"><img width="120" height="120" src="<%=path %>/image/logo.jpg" /></div>
      <nav>
        <ul class="lavaLampWithImage" id="lava_menu">
          <li><a href="<%=path%>/jsp/branchAttendant/home.jsp">首页</a></li>
          <li><a href="<%=path%>/Dessert/sale">处理销售</a></li>
          <li><a href="<%=path%>/jsp/branchAttendant/dealOrder.jsp">处理预订</a></li>
          <li class="current"><a href="<%=path%>/Dessert/membercheck">会员查看</a></li>
          <li><a href="<%=path%>/jsp/branchAttendant/storeInfo.jsp">店铺信息</a></li>
          <li><a href="<%=path%>/Dessert/userlogout">登出</a></li>
        </ul>
      </nav>
    </header>
    
    <div id="site_content">
      <div id="sidebar_container">
        <div class="sidebar">
		 <ul>
			<br></br>
			<li><a href="<%=path%>/Dessert/membercheck">会员消息</a></li>
			<br></br>
			<li><a href="<%=path%>/Dessert/billcheck">消费记录</a></li>
			<br></br>
			<li><a href="<%=path%>/Dessert/rechargecheck">充值记录</a></li>
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
		<jsp:useBean id="member"
			class="dessert.models.Member"
			scope="page"></jsp:useBean>
		<h2 align="center">购买单据查看</h2>
		<div class="form_settings">
		  <span style="margin-left:160px">输入日期:</span>
		  <%if(request.getAttribute("date")!=null){ %>
		  <input type="text" id="mydate" size="12" value="<%=request.getAttribute("date") %>" onclick="calShow('mydate');" onfocus="calShow('mydate');" readonly="readonly" style="width:80px;margin-left:-100px">
          <%}
		    else{%>
		    <input type="text" id="mydate" size="12" value="选择日期" onclick="calShow('mydate');" onfocus="calShow('mydate');" readonly="readonly" style="width:80px;margin-left:-100px">
          <%} %>
          <input class="submit" type="button" value="查找单据" onclick="retrieveBillInfo()"/>
		</div>
		<table id="rechargeTable" style="width:100%; border-spacing:0;">
		  <tr><th>编号</th><th>购买时间</th><th>客户编号</th><th>购买金额</th><th>优惠折扣</th><th>使用积分</th><th>折后金额</th><th>奖励积分</th><th>查看详单</th></tr>
		  <%if(billList.getListBean()!=null){ 
		      for(int i=0;i<billList.getListBean().size();i++){
		        pageContext.setAttribute("bill", billList.getBean(i));
		        pageContext.setAttribute("member", ((Bill)billList.getBean(i)).getBillMember());
		      %>
		  <tr>
		    <td><jsp:getProperty name="bill" property="billId" /></td>
		  	<td><jsp:getProperty name="bill" property="billTime" /></td>
		  	<td><jsp:getProperty name="member" property="memberId" /></td>
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
    function retrieveBillInfo(){
    	var dateInput = document.getElementById("mydate").value;
    	window.location.href='<%=path%>/Dessert/billcheck?dateInput='+dateInput;
    }
    function checkRow(billId){
    	
    }
  </script>
</body>
</html>