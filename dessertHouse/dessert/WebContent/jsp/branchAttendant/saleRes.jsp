<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.Member"%>
    <%@page import="dessert.models.BillDetail"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>sale result page</title>
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
          <li><a href="">店铺信息</a></li>
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
		<%String saleRes = (String) request.getAttribute("saleRes");
		  if(saleRes.equals("0")){%>
		<p><strong>对不起，您的销售提交失败！</strong></p>
		<%}
		  else{ %>
	    <jsp:useBean id="store"
			class="dessert.models.Store"
			scope="session"></jsp:useBean>
	    <jsp:useBean id="saleBill"
			type="dessert.models.Bill"
			scope="request"></jsp:useBean>
		<jsp:useBean id="billDetailList"
			type="dessert.business.ListBean"
			scope="request"></jsp:useBean>
		<jsp:useBean id="billDetail"
			class="dessert.models.BillDetail"
			scope="page"></jsp:useBean>
		<jsp:useBean id="product"
			class="dessert.models.Product"
			scope="page"></jsp:useBean>
          <h3 align="center" style="font-size:25px"><strong>购物单</strong></h3>
          <div class="form_settings" >
          	<p><span>销售单号</span><input type="text" readonly="readonly" value="<jsp:getProperty name="saleBill" property="billId" />" /></p>
            <p><span>销售时间</span><input type="text" readonly="readonly" value="<jsp:getProperty name="saleBill" property="billTime" />" /></p>
            <p><span>店铺名称</span><input type="text" readonly="readonly" value="<jsp:getProperty name="store" property="storeName" />" /></p>
          	<%if(saleBill.getBillType()==0){ %>
            <p><span>支付方式</span><input type="text" readonly="readonly" value="现金支付" /></p>
            <%}
          	  else{
		        Member m = saleBill.getBillMember();
		    %>
            <p><span>支付方式</span><input type="text" readonly="readonly" value="会员卡支付" /></p>
            <p><span>会员卡号</span><input type="text" readonly="readonly" value="<%=m.getMemberId() %>" /></p>
            <p><span>会员姓名</span><input type="text" readonly="readonly" value="<%=m.getMemberName() %>" /></p>
            <p><span>会员等级</span><input type="text" readonly="readonly" value="<%=m.getMemberLevel() %>级会员" /></p>
            <%} %>
            <h3 style="font-size:25px">销售列表</h3>
            <table id="myBusinessCartTable" style="width:100%; border-spacing:0;">
              <tr><th>产品编号</th><th>产品名称</th><th>产品类型</th><th>产品图片</th><th>产品价格</th><th>预订数量</th><th>条目价格</th></tr>
            <%for(int i=0;i<billDetailList.getListBean().size();i++){
            	BillDetail item = (BillDetail)billDetailList.getBean(i);
            	pageContext.setAttribute("billDetail", item);
            	pageContext.setAttribute("product", item.getProduct());
            	double itemTotal = item.getProductPrice()*item.getProductCount();
            %>
              <tr>
		  	    <td><jsp:getProperty name="product" property="productId" /></td>
		  	    <td><jsp:getProperty name="product" property="productName" /></td>
		        <td><jsp:getProperty name="product" property="productType" /></td>
		        <td><img width="60" height="40" src="<%=path %><jsp:getProperty name="product" property="imagePath" />"/></td>
		        <td><jsp:getProperty name="billDetail" property="productPrice" /></td>
		        <td><jsp:getProperty name="billDetail" property="productCount" /></td>
		        <td><%=itemTotal %></td>
		      </tr>	
            <%} %>
            </table>
            <%if(saleBill.getBillType()==0){ %>
            <p><span>总价</span><input type="text" readonly="readonly" value="<jsp:getProperty name="saleBill" property="billCost" />" /></p>
            <p><span>支付金额</span><input type="text" readonly="readonly" value="<jsp:getProperty name="saleBill" property="payment" />" /></p>
            <p><span>找零</span><input type="text" readonly="readonly" value="<jsp:getProperty name="saleBill" property="changeGiven" />" /></p>
            <%}
              else{
                double totalAfterRate = saleBill.getBillCost()*saleBill.getFavorRate();
            %>
            <p><span>折扣前总价</span><input type="text" readonly="readonly" value="<jsp:getProperty name="saleBill" property="billCost" />" /></p>
            <p><span>会员折扣率</span><input type="text" readonly="readonly" value="<jsp:getProperty name="saleBill" property="favorRate" />" /></p>
            <p><span>折扣后总价</span><input type="text" readonly="readonly" value="<%=totalAfterRate %>" /></p>
            <p><span>会员积分使用</span><input type="text" readonly="readonly" value="<jsp:getProperty name="saleBill" property="bonusUsed" />" /></p>
            <p><span>最终价格</span><input type="text" readonly="readonly" value="<jsp:getProperty name="saleBill" property="costAfterDiscount" />" /></p>
            <p><span>获得积分</span><input type="text" readonly="readonly" value="<jsp:getProperty name="saleBill" property="bonusGiven" />" /></p>
            <%} %>
          </div>
		<%} %>
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
</body>
</html>