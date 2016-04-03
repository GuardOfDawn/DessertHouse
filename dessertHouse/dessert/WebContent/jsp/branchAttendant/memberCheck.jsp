<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>member check page</title>
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
        <jsp:useBean id="memberList"
			type="dessert.business.ListBean"
			scope="request"></jsp:useBean>
		<jsp:useBean id="item"
			class="dessert.models.Member"
			scope="page"></jsp:useBean>
        <h2 align="center">店铺信息</h2>
		<table id="storeData" style="width:100%; border-spacing:0;">
		  <tr><th>编号</th><th>会员名称</th><th>联系方式</th><th>地址</th><th>卡状态</th><th>余额</th><th>积分</th><th>操作</th></tr>
		  <%for(int i=0;i<memberList.getListBean().size();i++){
			  Member m = (Member) memberList.getBean(i);
			  pageContext.setAttribute("item",m);
		  %>
		  <tr>
		  	  <td><jsp:getProperty name="item" property="memberId" /></td>
		  	  <td><jsp:getProperty name="item" property="memberName" /></td>
		      <td><jsp:getProperty name="item" property="memberTel" /></td>
		      <td><jsp:getProperty name="item" property="memberLoc" /></td>
		      <%if(m.getCardState()==0){ %>
		      <td>未激活</td>
		      <%}
		        else if(m.getCardState()==1){%>
		      <td>激活</td>
		      <%}
		        else if(m.getCardState()==2){%>
		      <td>暂停</td>
		      <%}
		        else if(m.getCardState()==3){%>
		      <td>停止</td>
		      <%}
		        else{%>
		      <td>取消</td>
		      <%} %>
		      <td><jsp:getProperty name="item" property="residual" /></td>
		      <td><jsp:getProperty name="item" property="bonusPoint" /></td>
		      <td>
		      	<input class="submit" type="button" name="deleteStore" value="查看详细"
		      		onclick="checkRow('<jsp:getProperty name="item" property="memberId" />')" />
		      </td>
		  </tr>
		  <% }%>
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
    function checkRow(memberId){
    	
    }
  </script>
</body>
</html>