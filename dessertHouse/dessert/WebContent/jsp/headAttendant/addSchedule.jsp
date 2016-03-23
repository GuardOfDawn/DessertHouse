<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.Store"%>
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
        <jsp:useBean id="productList"
			type="dessert.business.ListBean"
			scope="session"></jsp:useBean>
        <jsp:useBean id="storeList"
			type="dessert.business.ListBean"
			scope="session"></jsp:useBean>
		<jsp:useBean id="item"
			class="dessert.models.Product"
			scope="page"></jsp:useBean>
		<jsp:useBean id="store"
			class="dessert.models.Store"
			scope="page"></jsp:useBean>
        <h2 align="center">添加销售计划（周）</h2>
        
        <div style="margin-left:80px">
	        <div class="form_settings" style="margin-left:10px" >
	          <span style="font-size:22px">选择周数</span>
	          <p>
	            <select id="scheduleStartTime" name="scheduleStartTime" >
	             
	          	  <option value="null" selected="selected">请选择周数</option>
	         	  <option value="2016-03-14">2016-03-14（周一）</option>
	         	  <option value="2016-03-21">2016-03-21（周一）</option>
	         	  <option value="2016-03-28">2016-03-28（周一）</option>
	         	 
	            </select>
	          </p>
	        </div>
	        <div class="form_settings" style="margin-left:10px" >
	          <span style="font-size:22px">选择店铺</span>
	          <p>
	            <select id="scheduleStoreId" name="scheduleStore" onchange="storeChange(this)">
	          	    <option value="null" selected="selected">请选择店铺</option>
	             <%for(int i=0;i<storeList.getListBean().size();i++){
	            	 pageContext.setAttribute("store", (Store)storeList.getBean(i));
	         	  %>
	         	  <option value="<jsp:getProperty name="store" property="storeId" />;<jsp:getProperty name="store" property="storeName" />;<jsp:getProperty name="store" property="province" />;<jsp:getProperty name="store" property="city" />;<jsp:getProperty name="store" property="storeLoc" />">
	         	    <jsp:getProperty name="store" property="storeId" />
	         	  </option>
	         	 <%} %>
	            </select>
	          </p>
	        </div>
	        <div id="scheduleStoreInfo" style="display:none;margin-left:30px">
		  	  <span>所在省:</span><input id="provinceAutoInput" type="text" name="provinceAuto" value="" readonly="readonly" style="margin-left:14px" />
		  	  <span style="margin-left:5px">所在市:</span><input id="cityAutoInput" type="text" name="cityAuto" value="" readonly="readonly" style="margin-left:14px" />
	          <br>
	          <span >店铺名称:</span><input id="storeNameAutoInput" type="text" name="storeNameAuto" value="" readonly="readonly"/>
		  	  <span style="margin-left:5px">店铺地址:</span><input id="storeLocAutoInput" type="text" name="storeLocAuto" value="" readonly="readonly"/>
	        </div>
	        <p></p>
	        <div>
	          <span>&nbsp;&nbsp;</span><span style="font-size:30px;">周一</span><span>&nbsp;&nbsp;</span>
	          <input id="monday" type="image" width="25" height="25" src="<%=path %>/image/plus.jpg" onclick="changeShowState('mondaySchedule','monday','addMondayItem')" value="0" />
	          <input id="addMondayItem" type="button" value="添加计划条目" onclick="displayItemAddWindow('mondaySchedule')" style="font-size:16px;display:none" />
	          <table id="mondaySchedule" style="width:100%; border-spacing:0;display:none;">
			    <tr><th>产品编号</th><th>产品名称</th><th>产品类型</th><th>产品图片</th><th>产品价格</th><th>产品数量</th><th>修改</th><th>删除</th></tr>
			  </table>
			</div>
			<p></p>
			<div>
	          <span>&nbsp;&nbsp;</span><span style="font-size:30px;">周二</span><span>&nbsp;&nbsp;</span>
	          <input id="tuesday" type="image" width="25" height="25" src="<%=path %>/image/plus.jpg" onclick="changeShowState('tuesdaySchedule','tuesday','addTuesdayItem')" value="0" />
	          <input id="addTuesdayItem" type="button" value="添加计划条目" onclick="displayItemAddWindow('tuesdaySchedule')" style="font-size:16px;display:none" />
	          <table id="tuesdaySchedule" style="width:100%; border-spacing:0;display:none;">
			    <tr><th>产品编号</th><th>产品名称</th><th>产品类型</th><th>产品图片</th><th>产品价格</th><th>产品数量</th><th>修改</th><th>删除</th></tr>
			  </table>
			</div>
			<p></p>
			<div>
	          <span>&nbsp;&nbsp;</span><span style="font-size:30px;">周三</span><span>&nbsp;&nbsp;</span>
	          <input id="wednesday" type="image" width="25" height="25" src="<%=path %>/image/plus.jpg" onclick="changeShowState('wednesdaySchedule','wednesday','addWednesdayItem')" value="0" />
	          <input id="addWednesdayItem" type="button" value="添加计划条目" onclick="displayItemAddWindow('wednesdaySchedule')" style="font-size:16px;display:none" />
	          <table id="wednesdaySchedule" style="width:100%; border-spacing:0;display:none;">
			    <tr><th>产品编号</th><th>产品名称</th><th>产品类型</th><th>产品图片</th><th>产品价格</th><th>产品数量</th><th>修改</th><th>删除</th></tr>
			  </table>
			</div>
			<p></p>
			<div>
	          <span>&nbsp;&nbsp;</span><span style="font-size:30px;">周四</span><span>&nbsp;&nbsp;</span>
	          <input id="thursday" type="image" width="25" height="25" src="<%=path %>/image/plus.jpg" onclick="changeShowState('thursdaySchedule','thursday','addThursdayItem')" value="0" />
	          <input id="addThursdayItem" type="button" value="添加计划条目" onclick="displayItemAddWindow('thursdaySchedule')" style="font-size:16px;display:none" />
	          <table id="thursdaySchedule" style="width:100%; border-spacing:0;display:none;">
			    <tr><th>产品编号</th><th>产品名称</th><th>产品类型</th><th>产品图片</th><th>产品价格</th><th>产品数量</th><th>修改</th><th>删除</th></tr>
			  </table>
			</div>
			<p></p>
			<div>
	          <span>&nbsp;&nbsp;</span><span style="font-size:30px;">周五</span><span>&nbsp;&nbsp;</span>
	          <input id="friday" type="image" width="25" height="25" src="<%=path %>/image/plus.jpg" onclick="changeShowState('fridaySchedule','friday','addFridayItem')" value="0" />
	          <input id="addFridayItem" type="button" value="添加计划条目" onclick="displayItemAddWindow('fridaySchedule')" style="font-size:16px;display:none" />
	          <table id="fridaySchedule" style="width:100%; border-spacing:0;display:none;">
			    <tr><th>产品编号</th><th>产品名称</th><th>产品类型</th><th>产品图片</th><th>产品价格</th><th>产品数量</th><th>修改</th><th>删除</th></tr>
			  </table>
			</div>
			<p></p>
			<div>
	          <span>&nbsp;&nbsp;</span><span style="font-size:30px;">周六</span><span>&nbsp;&nbsp;</span>
	          <input id="saturday" type="image" width="25" height="25" src="<%=path %>/image/plus.jpg" onclick="changeShowState('saturdaySchedule','saturday','addSaturdayItem')" value="0" />
	          <input id="addSaturdayItem" type="button" value="添加计划条目" onclick="displayItemAddWindow('saturdaySchedule')" style="font-size:16px;display:none" />
	          <table id="saturdaySchedule" style="width:100%; border-spacing:0;display:none;">
			    <tr><th>产品编号</th><th>产品名称</th><th>产品类型</th><th>产品图片</th><th>产品价格</th><th>产品数量</th><th>修改</th><th>删除</th></tr>
			  </table>
			</div>
			<p></p>
			<div>
	          <span>&nbsp;&nbsp;</span><span style="font-size:30px;">周日</span><span>&nbsp;&nbsp;</span>
	          <input id="sunday" type="image" width="25" height="25" src="<%=path %>/image/plus.jpg" onclick="changeShowState('sundaySchedule','sunday','addSundayItem')" value="0" />
	          <input id="addSundayItem" type="button" value="添加计划条目" onclick="displayItemAddWindow('sundaySchedule')" style="font-size:16px;display:none" />
	          <table id="sundaySchedule" style="width:100%; border-spacing:0;display:none;">
			    <tr><th>产品编号</th><th>产品名称</th><th>产品类型</th><th>产品图片</th><th>产品价格</th><th>产品数量</th><th>修改</th><th>删除</th></tr>
			  </table>
			</div>
			<p></p>
		    <div class="form_settings" style="margin-left:250px" >
		      <input class="submit" type="button" name="addScheduleEnsure" value="确认提交" onclick="submitSchedule()" />
		    </div>
        </div>
      
      	<div id="light" class="white_content">
      	  <p></p>
      	  <h2 align="center">添加计划条目</h2><input type="text" id="day" value="" style="display:none"/>
      	  <p></p>
          <div class="form_settings" style="margin-left:50px" >
            <p><span>产品编号</span>
              <select id="idInput" name="productId" onchange="selChange(this)" >
          	    <option value="null" selected="selected">请选择产品</option>
              <%for(int i=0;i<productList.getListBean().size();i++){
            	  pageContext.setAttribute("item", productList.getBean(i));
          	  %>
          	    <option value="<jsp:getProperty name="item" property="productId" />;<jsp:getProperty name="item" property="productName" />
          	    			;<jsp:getProperty name="item" property="productType" />;<jsp:getProperty name="item" property="imagePath" />">
          	    	<jsp:getProperty name="item" property="productId" />
          	    </option>
          	  <%} %>
              </select>
              <img id="productImg" width="150" height="120" src="" style="margin-top:5px;margin-left:50px" />
            </p>
            <p><span>产品名称</span><input id="nameAutoInput" type="text" name="productName" value="" readonly="readonly"/></p>
            <p><span>产品类型</span><input id="typeAutoInput" type="text" name="productType" value="" readonly="readonly"/></p>
            <p><span>产品价格</span><input id="priceInput" type="text" name="productPrice" value="" /></p>
            <p><span>产品数量</span><input id="countInput" type="text" name="productCount" value="" /></p>
          </div>
          <div class="form_settings">
            <input class="submit" type="button" value="关闭" onclick="closeScheduleItem()"/>
            <input class="submit" type="button" name="addItemEnsure" value="确认添加" onclick="addScheduleItem()"/>
          </div>
		</div>
		
		<div id="lightForModify" class="white_content">
      	  <p></p>
      	  <h2 align="center">修改计划条目</h2>
      	  <input type="text" id="dayM" value="" style="display:none"/>
      	  <p></p>
          <div class="form_settings" style="margin-left:50px" >
            <p><span>产品编号</span><input id="productIdM" type="text" name="productName" value="" readonly="readonly"/></p>
            <img id="productImgM" width="150" height="120" src="" style="margin-top:5px;margin-left:50px" />
            <p><span>产品名称</span><input id="nameAutoInputM" type="text" name="productName" value="" readonly="readonly"/></p>
            <p><span>产品类型</span><input id="typeAutoInputM" type="text" name="productType" value="" readonly="readonly"/></p>
            <p><span>产品价格</span><input id="priceInputM" type="text" name="productPrice" value="" /></p>
            <p><span>产品数量</span><input id="countInputM" type="text" name="productCount" value="" /></p>
          </div>
          <div class="form_settings">
            <input class="submit" type="button" value="关闭" onclick="closeScheduleItemM()"/>
            <input class="submit" type="button" name="modifyItemEnsure" value="确认修改" onclick="modifyScheduleItem()"/>
          </div>
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
  <script type="text/javascript" src="<%=path %>/js/addSchedule.js"></script>
  <script type="text/javascript">
  function submitSchedule(){
  	var schedule = "";
  	var week = ["mondaySchedule","tuesdaySchedule","wednesdaySchedule","thursdaySchedule"
  	            ,"fridaySchedule","saturdaySchedule","sundaySchedule"];
  	for(var i=0;i<week.length;i++){
  		var table = document.getElementById(week[i]);
  		var rows = table.rows;
  		for(var j=1;j<rows.length;j++){
  			schedule += i;
  			schedule += ",";
  			schedule += rows[j].cells[0].innerHTML;
  			schedule += ",";
  			schedule += rows[j].cells[4].innerHTML;
  			schedule += ",";
  			schedule += rows[j].cells[5].innerHTML;
  			schedule += ";";
  		}
  	}
  	schedule=schedule.substring(0,schedule.length-1);
  	var store = document.getElementById("scheduleStoreId").value;
  	var storeId = store.split(";")[0];
  	var scheduleStartTime = document.getElementById("scheduleStartTime").value;
  	window.location.href='<%=path%>/Dessert/scheduleadd?scheduleStoreId='+storeId+'&scheduleStartTime='+scheduleStartTime+'&newschedule='+schedule;
  }
  </script>
</body>
</html>