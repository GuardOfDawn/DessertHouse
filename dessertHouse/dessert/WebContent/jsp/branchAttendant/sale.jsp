<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>sale page</title>
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
          <li><a href="<%=path%>/jsp/branchAttendant/home.jsp">首页</a></li>
          <li class="current"><a href="<%=path%>/Dessert/sale">处理销售</a></li>
          <li><a href="<%=path%>/jsp/branchAttendant/dealOrder.jsp">处理预订</a></li>
          <li><a href="<%=path%>/Dessert/membercheck">会员查看</a></li>
          <li><a href="<%=path%>/jsp/branchAttendant/storeInfo.jsp">店铺信息</a></li>
          <li><a href="<%=path%>/Dessert/userlogout">登出</a></li>
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
			scope="session"></jsp:useBean>
	    <jsp:useBean id="store"
			type="dessert.models.Store"
			scope="session"></jsp:useBean>
	    <jsp:useBean id="item"
			class="dessert.business.ProductExtend"
			scope="page"></jsp:useBean>
		<div>
		  <h2 align="center">Welcome to <jsp:getProperty name="store" property="storeName" />!</h2>
		  <p id="storeId" style="display:none"><jsp:getProperty name="store" property="storeId" /></p>
		</div>
	  	
        <p></p>
        	
        <div id="myBusinessCart" class="form_settings" >
          <h3 align="center" style="font-size:25px"><strong>购物单</strong></h3>
          <p>
          <span>支付方式:</span>
          <select id="payType" name="payType" onchange="selChange(this)" style="margin-left:-50px;width:160px" >
          <%if((String) request.getAttribute("memberCheckRes")==null){%>
            <option value="null" selected="selected">请选择支付方式</option>
            <option value="cardPay" >会员支付</option>
            <%}
            else{%>
            <option value="null" >请选择支付方式</option>
            <option value="cardPay" selected="selected" >会员支付</option>
            <%} %>
            <option value="cashPay" >现金支付</option>
          </select>
          </p>
          <%String res = (String) request.getAttribute("memberCheckRes");
            if(res==null){%>
          <div id="memberInfoBox" class="form_settings" style="display:none">
            <p>
              <span>会员卡号:</span>
              <input id="memberIdInput" type="text" name="memberId" value="" style="margin-left:-50px;width:160px;" onblur="retrieveMemberInfo()" />
            </p>
            <p><span>会员姓名:</span><input id="nameAutoInput" type="text" value="" readonly="readonly" style="margin-left:-14px;width:80px" /></p>
		  	<p><span>会员等级:</span><input id="levelAutoInput" type="text" value="" readonly="readonly" style="margin-left:-14px;width:80px" /></p>
	        <p><span>会员折扣率:</span><input id="favorRateAutoInput" id="favorRate" type="text" value="" readonly="readonly" style="margin-left:-14px;width:80px;"/></p>
		  	<p><span>会员积分:</span><input id="bonusAutoInput" type="text" value="" readonly="readonly" style="margin-left:-14px;width:80px;"/></p>
           </div>
          <%}
            else{
            Member m = (Member) request.getAttribute("member"); %>
          <div id="memberInfoBox" class="form_settings" >
            <p>
              <span>会员卡号:</span>
              <input id="memberIdInput" type="text" name="memberId" value="<%=request.getAttribute("memberId") %>" style="margin-left:-50px;width:160px;" onblur="retrieveMemberInfo()" />
            </p>
            <%if(res.equals("fail")){%>
          	<p id="memberAlert"><strong>对不起，您输入的会员不存在！请查看会员资格是否有效，或者使用现金支付！</strong></p>
            <%}
              else{  %>
            <p><span>会员姓名:</span><input id="nameAutoInput" type="text" value="<%=m.getMemberName() %>" readonly="readonly" style="margin-left:-14px;width:80px" /></p>
		  	<p><span>会员等级:</span><input id="levelAutoInput" type="text" value="<%=m.getMemberLevel() %>级" readonly="readonly" style="margin-left:-14px;width:80px" /></p>
	        <p><span>会员折扣率:</span><input id="favorRateAutoInput" id="favorRate" type="text" value="<%=request.getAttribute("favorRate") %>" readonly="readonly" style="margin-left:-14px;width:80px;"/></p>
		  	<p><span>会员积分:</span><input id="bonusAutoInput" type="text" value="<%=m.getBonusPoint() %>" readonly="readonly" style="margin-left:-14px;width:80px;"/></p>
           <% }%>
            </div>
          <%} %>
          <h3 style="font-size:25px">销售列表</h3>
          <table id="myBusinessCartTable" style="width:100%; border-spacing:0;">
            <tr><th>产品编号</th><th>产品名称</th><th>产品类型</th><th>产品图片</th><th>产品价格</th><th>预订数量</th><th>条目价格</th><th>删除</th></tr>
          </table>
          <%if(res==null){ %>     
          <div id="cashPaymentCal" style="float:center;margin-left:50px;">
          <%}
            else{%>
          <div id="cashPaymentCal" style="float:center;display:none;margin-left:50px;">
          <%} %>
            <p><span>总价：</span><input id="totalCost" type="text" value="0" readonly="readonly" style="text-align:center;width:80px"/></p>
            <p><span>支付金额：</span><input id="payment" type="text" value="" style="text-align:center;width:80px" onblur="cashPaymentInput()"/>元</p>
            <p><span>找零：</span><input id="change" type="text" value="" readonly="readonly" style="text-align:center;width:80px"/>元</p>
            <p id="paymentAlert" style="display:none"><strong>支付金额必须大于总价！</strong></p>
            <p  style="margin-left:250px;"><input class="submit" type="button" value="确认销售" onclick="ensureSale()"/></p>
          </div>
          <%if(res==null){ %>
          <div id="cardPaymentCal" style="display:none">
            <p><span>折扣前总价</span><input type="text" id="total" readonly="readonly" value="0" /></p>
            <p><span>会员折扣率</span><input type="text" id="favorRate" readonly="readonly" value="1" /></p>
            <p><span>折扣后总价</span><input id="totalAfterDiscount" name="totalAfterDiscount" type="text" readonly="readonly" value="0" /></p>
            <p><span>会员积分</span><input id="memberBonusUsed" name="memberBonusUsed" type="text" value="0" onblur="checkBonus()"/>(拥有积分：<strong id="memberBonus"></strong>)</p>
            <p id="bonusAlert" style="display:none"><strong>使用的积分不能超过您拥有的积分！</strong></p>
            <p><span>最终价格</span><input type="text" id="finalTotal" name="finalTotal" readonly="readonly" value="0" /></p>
          </div>
          <%}
            else if(res.equals("fail")){ %>
          <div id="cardPaymentCal">
            <p><span>折扣前总价</span><input type="text" id="total" readonly="readonly" value="0" /></p>
            <p><span>会员折扣率</span><input type="text" id="favorRate" readonly="readonly" value="1" /></p>
            <p><span>折扣后总价</span><input id="totalAfterDiscount" name="totalAfterDiscount" type="text" readonly="readonly" value="0" /></p>
            <p><span>会员积分</span><input id="memberBonusUsed" name="memberBonusUsed" type="text" value="0" onblur="checkBonus()"/>(拥有积分：<strong id="memberBonus"></strong>)</p>
            <p id="bonusAlert" style="display:none"><strong>使用的积分不能超过您拥有的积分！</strong></p>
            <p><span>最终价格</span><input type="text" id="finalTotal" name="finalTotal" readonly="readonly" value="0" /></p>
          </div>
          <%}
            else{%>
          <div id="cardPaymentCal" >
            <p><span>折扣前总价</span><input type="text" id="total" readonly="readonly" value="0" /></p>
            <p><span>会员折扣率</span><input type="text" id="favorRate" readonly="readonly" value="<%=request.getAttribute("favorRate") %>" /></p>
            <p><span>折扣后总价</span><input id="totalAfterDiscount" type="text" readonly="readonly" value="0" /></p>
            <p><span>会员积分</span><input id="memberBonusUsed" type="text" value="0" onblur="checkBonus()"/>(拥有积分：<strong id="memberBonus"><%=((Member) request.getAttribute("member")).getBonusPoint() %></strong>)</p>
            <p id="bonusAlert" style="display:none"><strong>使用的积分不能超过您拥有的积分！</strong></p>
            <p><span>最终价格</span><input type="text" id="finalTotal" readonly="readonly" value="0" /></p>
            <div>
              <input class="submit" type="button" value="取消销售" onclick="cancelSale()"/>
              <input class="submit" type="button" value="确认销售" onclick="ensureSale()"/>
            </div>
          </div>
          <%} %>
        </div>
        
        <p></p>
        <p></p>
        <p></p>
        
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
			<p id="sellingCountFor<jsp:getProperty name="item" property="productId" />" style="height:10px">数量：<jsp:getProperty name="item" property="remainingCount" /></p>
		    <p></p>
		  	  <div style="float:right;margin-right:80px;">
		  	    <a id="deleteOneFor<jsp:getProperty name="item" property="productId" />" href="javascript:deleteOne('<jsp:getProperty name="item" property="productId" />Count','<jsp:getProperty name="item" property="productId" />')" style="width:25px;height:25px;text-decoration:none;text-align:center;line-height:20px;background-color:#D9D6CF;display:inline-block;cursor:not-allowed;" ><b>—</b></a>
		  	    <input id="<jsp:getProperty name="item" property="productId" />Count" type="text" value="1" style="width:28px;" onblur="checkCount('<jsp:getProperty name="item" property="productId" />Count','<jsp:getProperty name="item" property="productId" />','<jsp:getProperty name="item" property="remainingCount" />')">
		  	    <a id="addOneFor<jsp:getProperty name="item" property="productId" />" href="javascript:addOne('<jsp:getProperty name="item" property="productId" />Count','<jsp:getProperty name="item" property="productId" />','<jsp:getProperty name="item" property="remainingCount" />')" style="width:25px;height:25px;text-decoration:none;text-align:center;line-height:20px;background-color:#D9D6CF;display:inline-block;" ><b>＋</b></a>
		  	  </div>
		  	  <input class="submit" type="button" value="销售" onclick="orderProduct('<jsp:getProperty name="item" property="productId" />')" style="float:right;margin-right:20px;margin-top:-28px;font-size:15px;width:50px"/>
		    <p></p>
		  </div>
        <%    }
		  }%>
	    </div>
	</div>
	
	<p></p>
    
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
    function selChange(obj){
    	var payType = obj.value;
    	if(payType=='cardPay'){
    		document.getElementById("memberInfoBox").style.display='block';
    		document.getElementById("cardPaymentCal").style.display='block';
    		document.getElementById("cashPaymentCal").style.display='none';
    		document.getElementById("totalCost").value=0;
    		var table = document.getElementById("myBusinessCartTable");
        	var rows = table.rows;
        	var total = 0;
        	for(var i=1;i<rows.length;i++){
        		total = total+parseFloat(rows[i].cells[6].innerHTML);
        	}
        	document.getElementById("total").value=total;
        	document.getElementById("totalAfterDiscount").value=total;
        	document.getElementById("finalTotal").value=total;
    	}
    	else{
    		document.getElementById("memberInfoBox").style.display='none';
       		document.getElementById("memberIdInput").value='';
       		document.getElementById("nameAutoInput").value='';
       		document.getElementById("levelAutoInput").value='';
       		document.getElementById("favorRateAutoInput").value='';
       		document.getElementById("bonusAutoInput").value='';
       		document.getElementById("cashPaymentCal").style.display='block';
       		document.getElementById("cardPaymentCal").style.display='none';
       		document.getElementById("bonusAutoInput").value='';
       		document.getElementById("favorRate").value='1';
       		var table = document.getElementById("myBusinessCartTable");
        	var rows = table.rows;
        	var total = 0;
        	for(var i=1;i<rows.length;i++){
        		total = total+parseFloat(rows[i].cells[6].innerHTML);
        	}
        	document.getElementById("totalCost").value=total;
    	}
    }
    function retrieveMemberInfo(){
    	var memberId = document.getElementById("memberIdInput").value;
    	if(memberId==''){
    		
    	}
    	else{
    		window.location.href='<%=path%>/Dessert/salememberinput?memberId='+memberId;
    	}
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
    	if(document.getElementById("memberIdInput")==null){
        	document.getElementById("totalCost").value=total;
    	}
    	else if(document.getElementById("memberIdInput").value==""){
    		if(document.getElementById("payType").value=="cardPay"){
    			document.getElementById("total").value=total;
        		var totalAfterRate = (total*parseFloat(document.getElementById("favorRate").value)).toFixed(2);
        		document.getElementById("totalAfterDiscount").value=totalAfterRate;
        		document.getElementById("finalTotal").value=totalAfterRate-parseInt(document.getElementById("memberBonusUsed").value);
    		}
    		else{
    			document.getElementById("totalCost").value=total;
    		}
    	}
    	else{
        	document.getElementById("total").value=total;
    		var totalAfterRate = (total*parseFloat(document.getElementById("favorRate").value)).toFixed(2);
    		document.getElementById("totalAfterDiscount").value=totalAfterRate;
    		var finalTotal = totalAfterRate-parseInt(document.getElementById("memberBonusUsed").value)*0.1;
    		document.getElementById("finalTotal").value=finalTotal.toFixed(2);
    	}
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
    	if(document.getElementById("memberIdInput")==null||document.getElementById("memberIdInput").value==""){
        	document.getElementById("totalCost").value=total;
    	}
    	else{
        	document.getElementById("total").value=total;
    		var totalAfterRate = (total*parseFloat(document.getElementById("favorRate").value)).toFixed(2);
    		document.getElementById("totalAfterDiscount").value=totalAfterRate;
    		var finalTotal = totalAfterRate-parseInt(document.getElementById("memberBonusUsed").value)*0.1;
    		document.getElementById("finalTotal").value=finalTotal.toFixed(2);
    	}
    }
    function cashPaymentInput(){
		document.getElementById("paymentAlert").style.display='none';
    	var payment = parseFloat(document.getElementById("payment").value);
    	var total = parseFloat(document.getElementById("totalCost").value);
    	if(payment<total){
    		document.getElementById("paymentAlert").style.display='block';
    		document.getElementById("payment").value='';
    	}
    	else{
    		var change = payment-total;
    		document.getElementById("change").value=change.toFixed(2);
    	}
    }
    function checkBonus(){
		document.getElementById("bonusAlert").style.display='none';
    	var bonusUsed = parseInt(document.getElementById("memberBonusUsed").value);
    	if(bonusUsed<0){
    		document.getElementById("memberBonusUsed").value=0;
    	}
    	else{
        	var bonusOwn = parseInt(document.getElementById("memberBonus").innerHTML);
    		if(bonusUsed>bonusOwn){
    			document.getElementById("memberBonusUsed").value=bonusOwn;
    			document.getElementById("bonusAlert").style.display='block';
    			bonusUsed=bonusOwn;
    		}
    		var finalTotal = parseFloat(document.getElementById("totalAfterDiscount").value);
        	finalTotal -= 0.1*bonusUsed;
        	document.getElementById("finalTotal").value=finalTotal.toFixed(2);
    	}
    }
    function cancelSale(){
    	
    }
    function ensureSale(){
    	var sale = "";
    	var table = document.getElementById("myBusinessCartTable");
    	var rows = table.rows;
    	for(var i=1;i<rows.length;i++){
    		sale += rows[i].cells[0].innerHTML;
    		sale += ",";
    		sale += rows[i].cells[4].innerHTML;
    		sale += ",";
    		sale += rows[i].cells[5].innerHTML;
    		sale += ";";
    	}
    	sale=sale.substring(0,sale.length-1);
    	var storeId = document.getElementById("storeId").innerHTML;
    	if(document.getElementById("memberIdInput")!=null){
        	var memberId = document.getElementById("memberIdInput").value;
        	if(memberId==""){
        		var payment = document.getElementById("payment").value;
            	window.location.href='<%=path%>/Dessert/ensuresale?sale='+sale+'&storeId='+storeId+'&payment='+payment;
        	}
        	else{
    			var bonusUsed = document.getElementById("memberBonusUsed").value;
            	window.location.href='<%=path%>/Dessert/ensuresale?sale='+sale+'&storeId='+storeId+'&memberId='+memberId+'&bonusUsed='+bonusUsed;
        	}
    	}
    	else{
    		var payment = document.getElementById("payment").value;
        	window.location.href='<%=path%>/Dessert/ensuresale?sale='+sale+'&storeId='+storeId+'&payment='+payment;
    	}
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