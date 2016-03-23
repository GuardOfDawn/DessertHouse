<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>visit store page</title>
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
          <%if(session.getAttribute("member")==null){ %>
          <li><a href="<%=path%>/jsp/member/memberLogin.jsp">登录</a></li>
          <%}
            else{%>
          <li><a href="<%=path%>/Dessert/orderview">账单</a></li>
          <li><a href="<%=path%>/Dessert/memberlogin">我的信息</a></li>
          <li><a href="<%=path%>/Dessert/memberlogout">登出</a></li>
          <%} %>
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
	  <jsp:useBean id="storeList"
			type="dessert.business.ListBean"
			scope="request"></jsp:useBean>
	  <jsp:useBean id="item"
			class="dessert.models.Store"
			scope="page"></jsp:useBean>
		<%if(storeList.getListBean()!=null&&storeList.getListBean().size()!=0){
		      for(int i=0;i<storeList.getListBean().size();i++){ 
		          pageContext.setAttribute("item", storeList.getBean(i));%>
		<div style="float:left;width:220px;height:300px;overflow:hidden">
			<a href="<%=path%>/Dessert/storevisitone?storeId=<jsp:getProperty name="item" property="storeId" />">
			  <img src="<%=path %><jsp:getProperty name="item" property="imagePath" />" >
			</a>
			<p id="storeId" style="display:none"><jsp:getProperty name="item" property="storeId" /></p>
			<p id="storeName" style="height:0px"><jsp:getProperty name="item" property="storeName" /></p>
			<p id="storeTel" style="height:0px"><jsp:getProperty name="item" property="storeTel" /></p>
			<p id="storeLoc" style="height:0px"><jsp:getProperty name="item" property="province" /><jsp:getProperty name="item" property="city" /><jsp:getProperty name="item" property="storeLoc" /></p>
		</div>
        <%    }
		  }%>
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
  <script language="JavaScript"> 
	var imgObj; 
	for( i=0; i<document.getElementsByTagName("img").length; i++ )
	{
	 	imgObj = document . getElementsByTagName("img")[i];
	 	if(imgObj.id=='logoImg'){
	 		continue;
	 	}
	 	//建议只判断高度或者宽度其中一个，那样可以自动按比例缩放
	 	if ( imgObj.width > imgObj.height ) //判断图片的宽度，如果大于700，则设置为700，值可以自己修改
	 	{
	  		imgObj.width = 200
	 	}
	 	else{
	 		imgObj.height = 200
	 	}
	}
  </script>
</body>
</html>