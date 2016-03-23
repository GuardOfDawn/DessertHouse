<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.business.ProductExtend"%>
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
	   <jsp:useBean id="sellingProductToday"
			type="dessert.business.ListBean"
			scope="request"></jsp:useBean>
	   <jsp:useBean id="store"
			class="dessert.models.Store"
			scope="request"></jsp:useBean>
	   <jsp:useBean id="item"
			class="dessert.business.ProductExtend"
			scope="page"></jsp:useBean>
		<div>
		  <h2 align="center">Welcome to <jsp:getProperty name="store" property="storeName" />!</h2>
		  <p id="storeId" style="display:none"><jsp:getProperty name="store" property="storeId" /></p>
		</div>
		<%if(sellingProductToday.getListBean()!=null&&sellingProductToday.getListBean().size()!=0){
		      for(int i=0;i<sellingProductToday.getListBean().size();i++){ 
		          pageContext.setAttribute("item", sellingProductToday.getBean(i));%>
		<div class="form_settings" style="float:left;width:220px;height:300px;overflow:hidden">
			<a href="javascript:visitOneProduct(<jsp:getProperty name="item" property="productId" />)">
			  <img id="imgFor<jsp:getProperty name="item" property="productId" />" src="<%=path %><jsp:getProperty name="item" property="imagePath" />" >
			</a>
			<p id="IdFor<jsp:getProperty name="item" property="productId" />" style="display:none"><jsp:getProperty name="item" property="productId" /></p>
			<p id="nameFor<jsp:getProperty name="item" property="productId" />" style="height:10px"><jsp:getProperty name="item" property="productName" /></p>
			<p id="typeFor<jsp:getProperty name="item" property="productId" />" style="height:10px">类型：<jsp:getProperty name="item" property="productType" /></p>
			<p id="priceFor<jsp:getProperty name="item" property="productId" />" style="height:10px">售价：<jsp:getProperty name="item" property="sellingPrice" /></p>
			<p id="sellingCountFor<jsp:getProperty name="item" property="productId" />" style="height:10px">数量：<jsp:getProperty name="item" property="sellingCount" /></p>
		  <%if(session.getAttribute("member")!=null){ %>
		    <p></p>
		  	  <div style="float:right;margin-right:80px;">
		  	    <a id="deleteOneFor<jsp:getProperty name="item" property="productId" />" href="javascript:deleteOne('<jsp:getProperty name="item" property="productId" />Count','<jsp:getProperty name="item" property="productId" />')" style="width:25px;height:25px;text-decoration:none;text-align:center;line-height:20px;background-color:#D9D6CF;display:inline-block;cursor:not-allowed;" ><b>—</b></a>
		  	    <input id="<jsp:getProperty name="item" property="productId" />Count" type="text" value="1" style="width:28px;" onblur="checkCount('<jsp:getProperty name="item" property="productId" />Count','<jsp:getProperty name="item" property="productId" />','<jsp:getProperty name="item" property="sellingCount" />')">
		  	    <a id="addOneFor<jsp:getProperty name="item" property="productId" />" href="javascript:addOne('<jsp:getProperty name="item" property="productId" />Count','<jsp:getProperty name="item" property="productId" />','<jsp:getProperty name="item" property="sellingCount" />')" style="width:25px;height:25px;text-decoration:none;text-align:center;line-height:20px;background-color:#D9D6CF;display:inline-block;" ><b>＋</b></a>
		  	  </div>
		  	  <input class="submit" type="button" value="预订" onclick="orderProduct('<jsp:getProperty name="item" property="productId" />')" style="float:right;margin-right:20px;margin-top:-28px;font-size:15px;width:50px"/>
		    <p></p>
		  <%} %>
		</div>
        <%    }
		  }%>
        
	  </div>
	  
        <div id="myBusinessCart" class="form_settings" style="display:none;">
          <h2 align="center">预订列表</h2>
          <table id="myBusinessCartTable" style="width:100%; border-spacing:0;">
	        <tr><th>产品编号</th><th>产品名称</th><th>产品类型</th><th>产品图片</th><th>产品价格</th><th>预订数量</th><th>条目价格</th><th>删除</th></tr>
	      </table>
	      <div style="float:right">
	        <span>总价：</span><input id="totalCost" type="text" value="" readonly="readonly" style="text-align:center;width:80px"/>
	        <input class="submit" type="button" value="提交预订" onclick="ensureOrder()"/>
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
    function visitOneProduct(productId){
    	
    }
    function deleteOne(text,productId){
    	var count = parseInt(document.getElementById(text).value);
    	if(count>1){
    		count = count-1;
        	document.getElementById(text).value=count;
        	if(count==1){
        		var buttonDelete = "deleteOneFor"+productId;
        		document.getElementById(buttonDelete).style.cursor='not-allowed';
        	}
    		var buttonAdd = "addOneFor"+productId;
    		document.getElementById(buttonAdd).style.cursor='pointer';
    	}
    }
    function addOne(text,productId,maxCount){
    	var count = parseInt(document.getElementById(text).value);
    	if(count<maxCount){
    		count = count+1;
        	document.getElementById(text).value=count;
        	if(count==maxCount){
        		var buttonAdd = "addOneFor"+productId;
        		document.getElementById(buttonAdd).style.cursor='not-allowed';
        	}
        	var buttonDelete = "deleteOneFor"+productId;
    		document.getElementById(buttonDelete).style.cursor='pointer';
    	}
    }
    function checkCount(text,productId,maxCount){
    	if(document.getElementById(text).value==''){
    		document.getElementById(text).value=1;
    		var buttonDelete = "deleteOneFor"+productId;
    		document.getElementById(buttonDelete).style.cursor='not-allowed';
    		var buttonAdd = "addOneFor"+productId;
    		document.getElementById(buttonAdd).style.cursor='pointer';
    	}
    	else{
    		var count = parseInt(document.getElementById(text).value);
        	if(count<1){
        		document.getElementById(text).value=1;
        		var buttonDelete = "deleteOneFor"+productId;
        		document.getElementById(buttonDelete).style.cursor='not-allowed';
        		var buttonAdd = "addOneFor"+productId;
        		document.getElementById(buttonAdd).style.cursor='pointer';
        	}
        	if(count>maxCount){
        		document.getElementById(text).value=maxCount;
        		var buttonAdd = "addOneFor"+productId;
        		document.getElementById(buttonAdd).style.cursor='not-allowed';
            	var buttonDelete = "deleteOneFor"+productId;
        		document.getElementById(buttonDelete).style.cursor='pointer';
        	}
    	}
    }
    function orderProduct(productId){
    	var orderCount = parseInt(document.getElementById(productId+"Count").value);
    	document.getElementById("myBusinessCart").style.display='block';
    	var table = document.getElementById("myBusinessCartTable");
    	var rows = table.rows;
    	var isAdded = 0;
    	for(var i=1;i<rows.length;i++){
    		if(productId==rows[i].cells[0].innerHTML){
    			isAdded = 1;
    			rows[i].cells[5].innerHTML = parseInt(rows[i].cells[5].innerHTML)+orderCount;
    			rows[i].cells[6].innerHTML = rows[i].cells[5].innerHTML*parseFloat(rows[i].cells[4].innerHTML);
    			break;
    		}
    	}
    	if(isAdded==0){
    		var newRow = table.insertRow(table.rows.length);
    	    var newCel1 = newRow.insertCell(0);
    	    var newCel2 = newRow.insertCell(1);
    	    var newCel3 = newRow.insertCell(2);
    	    var newCel4 = newRow.insertCell(3);
    	    var newCel5 = newRow.insertCell(4);
    	    var newCel6 = newRow.insertCell(5);
    	    var newCel7 = newRow.insertCell(6);
    	    var newCel8 = newRow.insertCell(7);
    	    newCel1.innerHTML = productId;
    	    newCel2.innerHTML = document.getElementById("nameFor"+productId).innerHTML;
    	    newCel3.innerHTML = document.getElementById("typeFor"+productId).innerHTML.split("：")[1];
    	    newCel4.innerHTML = "<img width='60' height='40' src='"+document.getElementById("imgFor"+productId).src+"'/>";
    	    var price = parseFloat(document.getElementById("priceFor"+productId).innerHTML.split("：")[1]);
    	    newCel5.innerHTML = price;
    	   	var textId = productId+"CartItem";
    	   	var sellingCount = "sellingCountFor"+productId;
    	   	var buttonDelete = "deleteOneFor"+productId;
    	   	var buttonAdd = "addOneFor"+productId;
    	    newCel6.innerHTML = orderCount;
    	    newCel7.innerHTML = orderCount*price;
    	    newCel8.innerHTML = "<input class='submit' type='button' name='deleteItem' value='删除' onclick=\"deleteRow(\'"+productId+"\')\" style='width:50px;margin-left:0px'/>";
    	}
    	var total = 0;
    	for(var i=1;i<rows.length;i++){
    		total = total+parseFloat(rows[i].cells[6].innerHTML);
    	}
    	document.getElementById("totalCost").value=total;
    	var text = productId+"Count";
    	document.getElementById(text).value=1;
    	var buttonDelete = "deleteOneFor"+productId;
		document.getElementById(buttonDelete).style.cursor='not-allowed';
		var buttonAdd = "addOneFor"+productId;
		document.getElementById(buttonAdd).style.cursor='pointer';
    }
    function deleteRow(productId){
    	var table = document.getElementById("myBusinessCartTable");
    	var rows = table.rows;
    	for(var i=1;i<rows.length;i++){
    		if(rows[i].cells[0].innerHTML==productId){
    			table.deleteRow(i);
    			break;
    		}
    	}
    	var total = 0;
    	for(var i=1;i<rows.length;i++){
    		total = total+parseFloat(rows[i].cells[6].innerHTML);
    	}
    	document.getElementById("totalCost").value=total;
    }
    function ensureOrder(){
    	var order = "";
    	var table = document.getElementById("myBusinessCartTable");
    	var rows = table.rows;
    	for(var i=1;i<rows.length;i++){
    		order += rows[i].cells[0].innerHTML;
    		order += ",";
    		order += rows[i].cells[4].innerHTML;
    		order += ",";
    		order += rows[i].cells[5].innerHTML;
    		order += ";";
    	}
    	order=order.substring(0,order.length-1);
    	var storeId = document.getElementById("storeId").innerHTML;
    	window.location.href='<%=path%>/Dessert/orderproduct?order='+order+'&storeId='+storeId;
    }
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
	 		imgObj.height = 150
	 	}
	}
  </script>
</body>
</html>