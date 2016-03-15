<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<<head>
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
          <li class="current"><a href="<%=path%>/Dessert/storeview">店铺管理</a></li>
          <li><a href="<%=path%>/Dessert/salespersonview">店员管理</a></li>
          <li><a href="<%=path%>/Dessert/userlogout">登出</a></li>
        </ul>
      </nav>
    </header>
	<div id="site_content">
	  <div id="sidebar_container">
        <div class="sidebar">
		 <ul>
			<br></br>
			<li><a href="<%=path%>/Dessert/storeview">浏览店铺</a></li>
			<br></br>
			<li><a href="<%=path%>/jsp/systemManager/addStore.jsp">添加店铺</a></li>
		 </ul>
		</div>
      </div>
	  <div id="content">
	  	<jsp:useBean id="storeList"
			type="dessert.business.StoreListBean"
			scope="session"></jsp:useBean>
		<jsp:useBean id="item"
			class="dessert.models.Store"
			scope="page"></jsp:useBean>
		<h2 align="center">店铺信息</h2>
		<table id="storeData" style="width:100%; border-spacing:0;">
		  <tr><th>编号</th><th>店铺名称</th><th>联系方式</th><th>所在省</th><th>所在市</th><th>地址</th><th>操作</th></tr>
		  <%for(int i=0;i<storeList.getListStore().size();i++){
				  pageContext.setAttribute("item",storeList.getStore(i));
				  %>
		  <tr>
		  	  <td><jsp:getProperty name="item" property="storeId" /></td>
		  	  <td><jsp:getProperty name="item" property="storeName" /></td>
		      <td><jsp:getProperty name="item" property="storeTel" /></td>
		      <td><jsp:getProperty name="item" property="province" /></td>
		      <td><jsp:getProperty name="item" property="city" /></td>
		      <td><jsp:getProperty name="item" property="storeLoc" /></td>
		      <td>
		      	<input class="submit" type="button" name="deleteStore" value="删除"
		      		onclick="deleteRow('<jsp:getProperty name="item" property="storeId" />')" />
		      </td>
		  </tr>
		  <% }%>
		</table>
		<p style="padding-top: 15px"><span>&nbsp;</span>
           	<% String deleteRes = (String)request.getAttribute("deleteRes");
           	if(deleteRes!=null&&deleteRes.equals("true")){
           		%>店铺<%=request.getAttribute("deleteStoreId") %>删除成功<%
           	}
           	else if(deleteRes!=null&&deleteRes.equals("false")){
           		%>店铺<%=request.getAttribute("deleteStoreId") %>删除失败,店铺内还有未删除的店员<%
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
	function deleteRow(storeId)
    {  
		window.location.href='<%=path%>/Dessert/storedelete?storeId='+storeId
    }
  </script>
</body>
</html>