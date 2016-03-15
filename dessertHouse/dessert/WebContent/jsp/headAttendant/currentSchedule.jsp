<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.WeekSchedule"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>head attendant page</title>
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
          <li><a href="<%=path%>/jsp/headAttendant/home.jsp">首页</a></li>
          <li class="current"><a href="<%=path%>/Dessert/currentscheduleview">产品计划</a></li>
          <li><a href="<%=path%>/Dessert/productview">产品管理</a></li>
          <li><a href="<%=path%>/Dessert/userlogout">登出</a></li>
        </ul>
      </nav>
    </header>
    <div id="site_content">
	  <div id="sidebar_container">
        <div class="sidebar">
		 <ul>
			<br></br>
			<li><a href="<%=path%>/Dessert/currentscheduleview">待处理的计划</a></li>
			<br></br>
			<li><a href="<%=path%>/Dessert/scheduleview">通过的计划</a></li>
			<br></br>
			<li><a href="<%=path%>/jsp/headAttendant/addSchedule.jsp">制定新计划</a></li>
		 </ul>
		</div>
      </div>
      <div id="content">
        <jsp:useBean id="undealtSchedule"
			type="dessert.business.ListBean"
			scope="session"></jsp:useBean>
        <jsp:useBean id="disapprovedSchedule"
			type="dessert.business.ListBean"
			scope="session"></jsp:useBean>
		<jsp:useBean id="item"
			class="dessert.models.WeekSchedule"
			scope="page"></jsp:useBean>
		<jsp:useBean id="store"
			class="dessert.models.Store"
			scope="page"></jsp:useBean>
        <h2 align="center">未审批的计划</h2>
        <table id="undealtScheduleTable" style="width:100%; border-spacing:0;">
		  <tr><th>编号</th><th>店铺</th><th>生效时间</th><th>终止时间</th><th>状态</th><th>查看</th><th>修改</th><th>删除</th></tr>
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
		      <td><jsp:getProperty name="item" property="scheduleState" /></td>
			  <td>
		      	<input class="submit" type="button" name="checkSchedule" value="查看"
		      		onclick="checkRow('<jsp:getProperty name="item" property="scheduleId" />')" />
		      </td>
		      <td>
		      	<input class="submit" type="button" name="modifySchedule" value="修改"
		      		onclick="modifyRow('<jsp:getProperty name="item" property="scheduleId" />')" />
		      </td>
		      <td>
		      	<input class="submit" type="button" name="deleteSchedule" value="删除"
		      		onclick="deleteRow('<jsp:getProperty name="item" property="scheduleId" />')" />
		      </td>
		  </tr>
		  <%  }
		    }%>
		</table>
        
        <h2 align="center">未通过的计划</h2>
        <table id="disapprovedSchedule" style="width:100%; border-spacing:0;">
		  <tr><th>编号</th><th>店铺</th><th>生效时间</th><th>终止时间</th><th>状态</th><th>查看</th><th>修改</th><th>删除</th></tr>
		  <%if(disapprovedSchedule.getListBean()!=null){
		      for(int i=0;i<disapprovedSchedule.getListBean().size();i++){
				pageContext.setAttribute("item", disapprovedSchedule.getBean(i));
				pageContext.setAttribute("store", ((WeekSchedule)disapprovedSchedule.getBean(i)).getStore());
				  %>
		  <tr>
		  	  <td><jsp:getProperty name="item" property="scheduleId" /></td>
		  	  <td><jsp:getProperty name="store" property="storeId" /></td>
		      <td><jsp:getProperty name="item" property="startTime" /></td>
		      <td><jsp:getProperty name="item" property="endTime" /></td>
		      <td><jsp:getProperty name="item" property="scheduleState" /></td>
			  <td>
		      	<input class="submit" type="button" name="checkSchedule" value="查看"
		      		onclick="checkRow('<jsp:getProperty name="item" property="scheduleId" />')" />
		      </td>
		      <td>
		      	<input class="submit" type="button" name="modifySchedule" value="修改"
		      		onclick="modifyRow('<jsp:getProperty name="item" property="scheduleId" />')" />
		      </td>
		      <td>
		      	<input class="submit" type="button" name="deleteSchedule" value="删除"
		      		onclick="deleteRow('<jsp:getProperty name="item" property="scheduleId" />')" />
		      </td>
		  </tr>
		  <%  }
		    }%>
		</table>
      
      </div>
      
      <div id="lightDeleteSchedule" class="delete_content" >
      	  <p></p>
      	  <div align="center">
      	    <p><input id="deleteScheduleId" type="text" value="" width="40px" readonly="readonly"/></p>
      	    <h2>您确认删除该计划吗？</h2>
      	  </div>
      	  <div class="form_settings" style="margin-left:-30px;margin-top:20px">
            <input class="submit" type="button" value="取消" onclick="cancelDeleteSchedule()"/>
            <input class="submit" type="button" value="确认删除" onclick="ensureDeleteSchedule()"/>
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
    function checkRow(scheduleId)
    {  
		
    }
	function modifyRow(scheduleId)
    {  
		
    }
	function deleteRow(scheduleId)
    {  
		document.getElementById("lightDeleteSchedule").style.display='block';
		document.getElementById("deleteScheduleId").value=scheduleId;
    }
	function cancelDeleteSchedule(){
		document.getElementById("deleteScheduleId").value='';
		document.getElementById("lightDeleteSchedule").style.display='none';
	}
	function ensureDeleteSchedule(){
		var scheduleTodelete = document.getElementById("deleteScheduleId").value;
		document.getElementById("deleteScheduleId").value='';
		document.getElementById("lightDeleteSchedule").style.display='none';
		window.location.href='<%=path%>/Dessert/scheduledelete?scheduleTodelete='+scheduleTodelete;
	}
  </script>
</body>
</html>