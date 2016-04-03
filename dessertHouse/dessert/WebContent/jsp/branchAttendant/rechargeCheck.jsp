<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.Recharge"%>
    <%@page import="dessert.models.Bill"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>recharge check page</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css" />
  <link rel="stylesheet" type="text/css" href="<%=path %>/css/lightbox.css" />
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
	    <jsp:useBean id="rechargeList"
			type="dessert.business.ListBean"
			scope="request"></jsp:useBean>
		<jsp:useBean id="recharge"
			class="dessert.models.Recharge"
			scope="page"></jsp:useBean>
		<jsp:useBean id="member"
			class="dessert.models.Member"
			scope="page"></jsp:useBean>
		<h2 align="center">充值单据查看</h2>
		<div class="form_settings">
		  <p align="center">
            <span style="margin-left:80px">输入会员卡号:</span>
            <%if(request.getAttribute("memberId")!=null){ %>
            <input id="memberIdInput" type="text" name="memberId" value="<%=request.getAttribute("memberId") %>" style="margin-left:-50px;width:100px;" />
            <%}
              else{%>
            <input id="memberIdInput" type="text" name="memberId" value="" style="margin-left:-20px;width:100px;" />
            <%} %>
            <input class="submit" type="button" value="查找充值记录" style="width:150px" onclick="retrieveRecharge()"/>
          </p>
		</div>
		<table id="rechargeTable" style="width:100%; border-spacing:0;">
		  <tr><th>编号</th><th>充值时间</th><th>客户编号</th><th>充值金额</th></tr>
		  <%if(rechargeList.getListBean()!=null){ 
		      for(int i=0;i<rechargeList.getListBean().size();i++){
		        pageContext.setAttribute("recharge", rechargeList.getBean(i));
		        pageContext.setAttribute("member", ((Recharge)rechargeList.getBean(i)).getMember());
		      %>
		  <tr>
		    <td><jsp:getProperty name="recharge" property="rechargeId" /></td>
		  	<td><jsp:getProperty name="recharge" property="rechargeTime" /></td>
		  	<td><jsp:getProperty name="member" property="memberId" /></td>
		  	<td><jsp:getProperty name="recharge" property="rechargeAmount" /></td>
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
    function retrieveRecharge(){
  		var memberId = document.getElementById("memberIdInput").value;
  	  	window.location.href='<%=path%>/Dessert/rechargecheck?memberId='+memberId;
    }
  </script>
</body>
</html>