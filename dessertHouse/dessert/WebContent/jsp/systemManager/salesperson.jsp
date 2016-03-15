<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>system manager page</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css" />
  <!-- modernizr enables HTML5 elements and feature detects -->
  <script type="text/javascript" src="<%=path%>/js/modernizr-1.5.min.js"></script>
</head>
<body>
  <div id="main">
    <header>
      <div id="logo"><img width="120" height="120" src="<%=path%>/image/logo.jpg" /></div>
      <nav>
        <ul class="lavaLampWithImage" id="lava_menu">
          <li><a href="<%=path%>/jsp/systemManager/home.jsp">首页</a></li>
          <li><a href="<%=path%>/Dessert/storeview">店铺管理</a></li>
          <li class="current"><a href="<%=path%>/Dessert/salespersonview">店员管理</a></li>
          <li><a href="<%=path%>/Dessert/userlogout">登出</a></li>
        </ul>
      </nav>
    </header>
    <div id="site_content">
	  <div id="sidebar_container">
        <div class="sidebar">
		 <ul>
			<br></br>
			<li><a href="<%=path%>/Dessert/salespersonview">浏览店员</a></li>
			<br></br>
			<li><a href="<%=path%>/jsp/systemManager/addSalesperson.jsp">添加店员</a></li>
		 </ul>
		</div>
      </div>
	  <div id="content">
	    <jsp:useBean id="salespersonList"
			type="dessert.business.SalespersonListBean"
			scope="session"></jsp:useBean>
		<jsp:useBean id="item"
			class="dessert.models.Salesperson"
			scope="page"></jsp:useBean>
		<jsp:useBean id="store"
			class="dessert.models.Store"
			scope="page"></jsp:useBean>
		<h2 align="center">店员信息</h2>
		<table style="width:100%; border-spacing:0;">
		  <tr><th>编号</th><th>店员名称</th><th>店员性别</th><th>店员年龄</th><th>店员类型</th><th>店员所属店铺</th><th>修改</th><th>删除</th></tr>
		  <%for(int i=0;i<salespersonList.getListSalesperson().size();i++){
				  pageContext.setAttribute("item",salespersonList.getSalesperson(i));
				  pageContext.setAttribute("store",salespersonList.getSalesperson(i).getStore());
				  %>
		  <tr>
			  <td><jsp:getProperty name="item" property="salespersonId" /></td>
		  	  <td><jsp:getProperty name="item" property="salespersonName" /></td>
		  	 <%if(salespersonList.getSalesperson(i).getSalespersonGender()==0){ %>
		      <td>男</td>
		    <%}
		    else {%>
		      <td>女</td>
		    <%} %>
		      <td><jsp:getProperty name="item" property="salespersonAge" /></td>
		    <%if(salespersonList.getSalesperson(i).getSalespersonLevel()==0){ %>
		      <td>总店服务员</td>
		    <%}
		    else {%>
		      <td>分店服务员</td>
		    <%} %>
		      <td><jsp:getProperty name="store" property="storeId" /></td>
		      <td>
		      	<input class="submit" type="button" name="modifyStore" value="修改"
		      		onclick="modifyRow('<jsp:getProperty name="item" property="salespersonId" />')" />
		      </td>
		      <td>
		      	<input class="submit" type="button" name="deleteStore" value="删除"
		      		onclick="deleteRow('<jsp:getProperty name="item" property="salespersonId" />')" />
		      </td>
		  </tr>
		  <% }%>
		</table>
		<p style="padding-top: 15px"><span>&nbsp;</span>
           	<% String deleteRes = (String)request.getAttribute("deleteRes");
           	if(deleteRes!=null&&deleteRes.equals("true")){
           		%>店员<%=request.getAttribute("deleteSalespersonId") %>删除成功<%
           	}
           	else if(deleteRes!=null&&deleteRes.equals("false")){
           		%>店员<%=request.getAttribute("deleteSalespersonId") %>删除失败<%
           	}%>
        </p>
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
  </script>
  <script type="text/javascript">
	function a(){
		window.open("deleteStoreAlert.html",'go','scrollbars=yes,resizable=yes,width=720,height=400')
	}
	function modifyRow(salespersonId)
    {  
		window.location.href='<%=path%>/jsp/systemManager/modifySalesperson.jsp?salespersonId='+salespersonId
    }
	function deleteRow(salespersonId)
    {  
		window.location.href='<%=path%>/Dessert/salespersondelete?salespersonId='+salespersonId
    }
  </script>
</body>
</html>