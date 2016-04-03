<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>member condition page</title>
  <% String path = request.getContextPath();%>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css" />
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
          <li><a href="<%=path%>/jsp/storeManager/home.jsp">首页</a></li>
          <li><a href="<%=path%>/Dessert/schedulemessage">批准销售计划</a></li>
          <li class="current"><a href="<%=path%>/jsp/storeManager/memberConditionDis.jsp">店铺情况统计</a></li>
          <li><a href="<%=path%>/Dessert/userlogout">登出</a></li>
        </ul>
      </nav>
    </header>
    <div id="site_content">
      <div id="sidebar_container">
        <div class="sidebar">
		 <ul>
			<br></br>
			<li><a href="<%=path%>/jsp/storeManager/memberConditionDis.jsp">会员状况统计</a></li>
			<br></br>
			<li><a href="<%=path%>/jsp/storeManager/memberConsumeDis.jsp">会员消费统计</a></li>
			<br></br>
			<li><a href="<%=path%>/jsp/storeManager/storeSaleDis.jsp">销售情况统计</a></li>
			<br></br>
			<li><a href="<%=path%>/jsp/storeManager/popularProductDis.jsp">热卖产品统计</a></li>
		 </ul>
		</div>
      </div>
      
      <div id="content">
		<div class="form_settings">
		  <span style="margin-left:160px">输入月份:</span>
		  <%if(request.getAttribute("date")!=null){ %>
		  <input type="text" id="mydate" size="12" value="<%=request.getAttribute("date") %>" onclick="calShow('mydate');" onfocus="calShow('mydate');" readonly="readonly" style="width:80px;margin-left:-100px">
          <%}
		    else{%>
		    <input type="text" id="mydate" size="12" value="选择月份" onclick="calShow('mydate');" onfocus="calShow('mydate');" readonly="readonly" style="width:80px;margin-left:-100px">
          <%} %>
          <input class="submit" type="button" value="查看" onclick="checkInfo()"/>
		</div>
		
		<div>
			<img id="memberAgeImg" border="0" src="<%=path%>/memberage.action?date=<%=request.getParameter("date") %>" style="margin-left:50px">
		</div>
		<p></p>
		<div>
			<img id="memberGenderImg" border="0" src="<%=path%>/membergender.action?date=<%=request.getParameter("date") %>" style="margin-left:50px">
		</div>
		<p></p>
		<div>
			<img id="memberCardImg" border="0" src="<%=path%>/membercard.action?date=<%=request.getParameter("date") %>" style="margin-left:50px">
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
  	function checkInfo(){
		var date = document.getElementById("mydate").value;
		if(date!="输入月份:")
    		window.location.href='<%=path%>/jsp/storeManager/memberConditionDis.jsp?date='+date;
  	}
  </script>
</body>
</html>