<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.WeekSchedule"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>store manager page</title>
  <% String path = request.getContextPath();%>
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
          <li><a href="<%=path%>/jsp/storeManager/home.jsp">首页</a></li>
          <li class="current"><a href="<%=path%>/Dessert/schedulemessage">批准销售计划</a></li>
          <li><a href="<%=path%>/jsp/storeManager/memberConditionDis.jsp">店铺情况统计</a></li>
          <li><a href="<%=path%>/Dessert/userlogout">登出</a></li>
        </ul>
      </nav>
    </header>
    <div id="site_content">
      <div id="sidebar_container">
        <div class="sidebar">
		 <ul>
			<br></br>
			<li><a href="<%=path%>/Dessert/schedulemessage">待处理的计划</a></li>
			<br></br>
			<li><a href="<%=path%>/Dessert/schedulehistory">已处理的计划</a></li>
		 </ul>
		</div>
      </div>
      <div id="content">
        <jsp:useBean id="undealtSchedule"
			type="dessert.business.ListBean"
			scope="session"></jsp:useBean>
		<jsp:useBean id="item"
			class="dessert.models.WeekSchedule"
			scope="page"></jsp:useBean>
		<jsp:useBean id="store"
			class="dessert.models.Store"
			scope="page"></jsp:useBean>
        <h2 align="center">待审批的计划</h2>
		<table id="undealtScheduleTable" style="width:100%; border-spacing:0;">
		  <tr><th>编号</th><th>店铺</th><th>生效时间</th><th>终止时间</th><th>状态</th><th>查看</th><th>审批</th><th>拒绝</th></tr>
		  <%if(undealtSchedule.getListBean()!=null){
		      for(int i=0;i<undealtSchedule.getListBean().size();i++){
				pageContext.setAttribute("item", undealtSchedule.getBean(i));
				pageContext.setAttribute("store", ((WeekSchedule)undealtSchedule.getBean(i)).getStore());
				  %>
		  <tr>
		  	  <td><jsp:getProperty name="item" property="scheduleId" /></td>
		  	  <td><jsp:getProperty name="store" property="storeId" /></td>
		      <td><jsp:getProperty name="item" property="startTime" /></td>
		      <td><jsp:getProperty name="item" property="endTime" /></td>
		      <td>未审批</td>
			  <td>
		      	<input class="submit" type="button" name="checkSchedule" value="查看"
		      		onclick="checkRow('<jsp:getProperty name="item" property="scheduleId" />')" />
		      </td>
		      <td>
		      	<input class="submit" type="button" name="modifySchedule" value="通过"
		      		onclick="approveRow('<jsp:getProperty name="item" property="scheduleId" />')" />
		      </td>
		      <td>
		      	<input class="submit" type="button" name="deleteSchedule" value="不通过"
		      		onclick="disapproveRow('<jsp:getProperty name="item" property="scheduleId" />')" />
		      </td>
		  </tr>
		  <%  } 
		    }%>
		</table>
		<p style="padding-top: 15px"><span>&nbsp;</span>
           	<% String approveRes = (String)request.getAttribute("approveRes");
           	if(approveRes!=null&&approveRes.equals("true")){
           		%>产品计划<%=request.getAttribute("approvedId") %>批准成功<%
           	}
           	else if(approveRes!=null&&approveRes.equals("false")){
           		%>产品计划<%=request.getAttribute("approvedId") %>批准失败<%
           	}%>
        </p>
        <p style="padding-top: 15px"><span>&nbsp;</span>
           	<% String disapproveRes = (String)request.getAttribute("disapproveRes");
           	if(disapproveRes!=null&&disapproveRes.equals("true")){
           		%>产品计划<%=request.getAttribute("disapprovedId") %>不批准成功<%
           	}
           	else if(disapproveRes!=null&&disapproveRes.equals("false")){
           		%>产品计划<%=request.getAttribute("disapprovedId") %>不批准失败<%
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
    function checkRow(scheduleId){
    	
    }
	function approveRow(scheduleId){
		window.location.href='<%=path%>/Dessert/scheduleapprove?scheduleId='+scheduleId;
    }
	function disapproveRow(scheduleId){
		window.location.href='<%=path%>/Dessert/scheduledisapprove?scheduleId='+scheduleId;
    }
  </script>
</body>
</html>