<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dessert.models.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();%>
  <title>member info page</title>
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
          <li><a href="<%=path%>/jsp/member/home.jsp">首页</a></li>
          <li><a href="<%=path%>/Dessert/storevisitall">店铺</a></li>
          <li><a href="">商品</a></li>
          <li><a href="<%=path%>/Dessert/orderview">账单</a></li>
          <li class="current"><a href="<%=path%>/Dessert/memberlogin">我的信息</a></li>
          <li><a href="<%=path%>/Dessert/memberlogout">登出</a></li>
        </ul>
      </nav>
    </header>
    
    <div id="sidebar_container">
		<div class="sidebar">
			 <h3 style="font-size:42px;">News</h3>
			  <h4>New Website Launched</h4>
			  <h5>March 16th, 2016</h5>
		</div>
	</div>
	
	<div id="content">
      <%Member m = (Member) session.getAttribute("member"); %>
	  <h2 align="center">会员基本信息</h2>
	  <p></p>
      <div class="form_settings" style="margin-left:130px;">
        <p><span>会员名称</span><input type="text" name="memberName" readonly="readonly" value="<%=m.getMemberName() %>" /></p>
		<p><span>会员性别</span>
		  <%if(m.getMemberGender()==0){ %>
		    <input type="text" name="memberGender" readonly="readonly" value="男" />
		  <%}
		  else{%>
		    <input type="text" name="memberGender" readonly="readonly" value="女" />
		  <%} %>
		</p>
        <p><span>会员年龄</span><input type="text" name="memberAge" readonly="readonly" value="<%=m.getMemberAge() %>" /></p>
        <p><span>联系方式</span><input type="text" name="memberTel" readonly="readonly" value="<%=m.getMemberTel() %>" /></p>
        <p><span>会员住址</span><input type="text" name="memberLoc" readonly="readonly" value="<%=m.getMemberLoc() %>" /></p>
        <p style="padding-top: 10px;"><span>&nbsp;</span>
		    <input class="submit" type="button" name="modifyInfo" onclick="modifyBaseInfo()" value="修改" />
		</p>
		<p style="padding-top: 15px"><span>&nbsp;</span>
		<%String modifyRes = (String)request.getAttribute("errorType");
		  if("nameExist".equals(modifyRes)){%>
		  	会员信息修改失败，会员名<%=request.getAttribute("value") %>已存在
		<%}
		  else if("telExist".equals(modifyRes)){%>
		  	会员信息修改失败，电话<%=request.getAttribute("value") %>已被使用
		<%} %>
		</p>
      </div>
      <p></p>
      <h2>会员卡信息</h2>
	  <p></p>
      <div class="form_settings"style="margin-left:130px;">
          <p><span>会员卡号</span><input type="text" name="memberId" readonly="readonly" value="<%=m.getMemberId() %>" /></p>
          <p><span>会员卡状态</span>
          <%if(m.getCardState()==0){ %>
		    <input type="text" name="memberGender" readonly="readonly" value="未激活" />
		    <a href="javascript:activateCard()" style="font-size:14px;">前往激活</a>
		  <%}
		    else if(m.getCardState()==1){%>
		    <input type="text" name="memberGender" readonly="readonly" value="已激活" />
		  <%} 
		    else if(m.getCardState()==2){%>
		    <input type="text" name="memberGender" readonly="readonly" value="暂停" />
		    <a href="javascript:regainCard()" style="font-size:14px;">前往充值</a>
		  <%} %>
          </p>
          <p><span>会员等级</span><input type="text" name="memberName" readonly="readonly" value="<%=m.getMemberLevel() %>级" /></p>
          <p><span>绑定的银行卡</span>
          <%if(m.getBankCardId()==null||m.getBankCardId().length()==0){ %>
          	 <input type="text" name="memberLoc" readonly="readonly" value="未绑定银行卡" />
          <%}
          	else{%>
          	 <input type="text" name="memberLoc" readonly="readonly" value="<%=m.getBankCardId() %>" />
          <%} %>
          </p>
          <p><span>会员卡余额</span><input type="text" name="memberLoc" readonly="readonly" value="<%=m.getResidual() %>" />
		    <%if(m.getCardState()==1){ %>
		    <a href="javascript:rechargeMoney()" style="font-size:14px;">充值</a>
		    <%} %>
		  </p>
          <p><span>会员积分</span><input type="text" name="memberLoc" readonly="readonly" value="<%=m.getBonusPoint() %>" />
		    <%if(m.getCardState()==1){ %>
		    <a href="javascript:bonusConvert()" style="font-size:14px;">积分兑换</a>
		    <%} %>
		  </p>
		  <p style="padding-top: 10px;"><span>&nbsp;</span>
		    <input class="submit" type="button" name="withdrawCard" onclick="withdrawCard()" value="注销" />
		</p>
      </div>
      <p></p>
      <p></p>
	</div>
	
	<div id="lightForModifyInfo" class="modify_content">
	  <p></p>
      <h2 align="center">修改会员基本信息</h2>
      <p></p>
      <form action="<%=path%>/Dessert/membermodify">
        <div class="form_settings" style="margin-left:50px" >
          <p><span>会员卡号</span><input type="text" name="memberId" readonly="readonly" value="<%=m.getMemberId() %>" /></p>
          <p><span>会员名称</span><input id="nameInput" type="text" name="memberName" value=""/></p>
          <p><span>会员性别</span>
            <select id="genderInput" name="memberGender">
		      <option value="0">男</option>
              <option value="1">女</option>
		    </select>
		  </p>
          <p><span>会员年龄</span><input id="ageInput" type="text" name="memberAge" value="" /></p>
          <p><span>联系方式</span><input id="telInput" type="text" name="memberTel" value="" /></p>
          <p><span>会员住址</span><input id="locInput" type="text" name="memberLoc" value="" /></p>
        </div>
        <div class="form_settings">
          <input class="submit" type="button" value="关闭" onclick="closeModify()"/>
          <input class="submit" type="submit" value="确认修改"/>
        </div>
      </form>
	</div>
	
	<%if(request.getAttribute("continueActivate")==null){ %>
	<div id="lightForActivateCard" class="activate_content">
	<%}
	  else{%>
	<div id="lightForActivateCard" class="activate_content" style="display:block">
	<%} %>
	  <p></p>
      <h2 align="center">激活会员卡</h2>
      <p></p>
	<%String bandRes = (String)request.getAttribute("continueActivate");
	  if(m.getBankCardId()==null||m.getBankCardId().length()==0){ %>
      <form id="firstStep" >
        <div class="form_settings" style="margin-left:50px" >
          <p><strong>tip:需要绑定一个银行卡，并设置支付密码</strong></p>
          <p><span>会员卡号</span><input id="memberIdInput" type="text" name="memberId" readonly="readonly" value="<%=m.getMemberId() %>" /></p>
          <p><span>银行卡号</span><input id="bankCardInput" type="text" name="bankCard" value=""/></p>
          <p><span>支付密码</span><input id="payPasswdSet" type="password" name="rechargeAmount" value="" /></p>
          <p id="bankCardNull" style="display:none">银行卡不能为空。请重新输入银行卡</p>
          <p id="passwordNull" style="display:none">支付密码不能为空，请重选输入支付密码</p>
        </div>
        <p></p>
        <div class="form_settings">
          <input class="submit" type="button" value="关闭" onclick="closeBankCardBound()"/>
          <input class="submit" type="button" value="下一步" onclick="ensureBandBankCard()"/>
        </div>
        <%if(bandRes!=null){ %>
        <p style="padding-top: 15px"><span>&nbsp;</span>
           <strong>银行卡绑定失败 </strong>
        </p>
        <%} %>
      </form>
    <%}
	  else{%>
      <form id="secondStep" style="margin-left:50px" >
        <div class="form_settings" style="margin-left:50px" >
          <p><strong>tip:充值200元以上，可以激活会员卡</strong></p>
          <p><span>会员卡号</span><input id="memberIdAutoInput" type="text" name="memberId" readonly="readonly" value="<%=m.getMemberId() %>" /></p>
          <p><span>银行卡号</span><input id="bankCardAutoInput" type="text" name="bankCard" readonly="readonly" value="<%=m.getBankCardId() %>"/></p>
          <p><span>充值金额</span><input id="rechargeInput" type="text" name="rechargeAmount" value="" /></p>
          <p><span>支付密码</span><input id="payPasswdInput" type="password" name="paypasswd" value="" /></p>
          <p id="rechargeAlert" style="display:none">充值金额不足200元，不够激活会员卡。请重新输入充值金额</p>
          <p id="passwordNotInput" style="display:none">请输入支付密码</p>
        </div>
        <p></p>
        <div class="form_settings">
          <input class="submit" type="button" value="关闭" onclick="closeActivate()"/>
          <input class="submit" type="button" value="确认激活" onclick="ensureActivateCard()"/>
        </div>
          <%if(bandRes!=null){ %>
          <p style="padding-top: 15px"><span>&nbsp;</span>
          	<strong>银行卡绑定成功 </strong>
         </p>
          <%} %>
      </form>
    <%} %>
	</div>
  </div>
  
  <div id="lightForRegainCard" class="regain_content">
    <p></p>
    <h2 align="center">解除会员卡暂停状态</h2>
    <p></p>
    <form action="<%=path%>/Dessert/memberregain">
      <p style="margin-left:80px"><strong>tip:充值<input id="regainAmount" type="text" readonly="readonly" value=""/>，即可解除暂停状态</strong></p>
      <div class="form_settings" style="margin-left:60px" >
        <p><span>会员卡号</span><input type="text" name="memberId" readonly="readonly" value="<%=m.getMemberId() %>" /></p>
        <p><span>账户余额</span><input type="text" name="residual" readonly="readonly" value="<%=m.getResidual() %>"/></p>
        <p><span>银行卡号</span><input type="text" name="bankCard" readonly="readonly" value="<%=m.getBankCardId() %>" /></p>
        <p><span>充值金额</span><input id="rechargeForRegain" type="text" name="rechargeAmount" value="" /></p>
        <p><span>支付密码</span><input id="payPasswdForRegain" type="password" name="paypasswd" value="" /></p>
        <p id="regainAlert" style="display:none">充值金额不足，不能够解除暂停状态。请重新输入充值金额</p>
        <p id="passwdNullForRegain" style="display:none">请输入支付密码</p>
      </div>
      <div class="form_settings">
        <input class="submit" type="button" value="关闭" onclick="closeRegain()"/>
        <input class="submit" type="button" value="确认充值" onclick="ensureRegain()"/>
      </div>
    </form>
  </div>
  
  <div id="lightForRechargeMoney" class="recharge_content">
    <p></p>
    <h2 align="center">会员卡充值</h2>
    <p></p>
    <form action="<%=path%>/Dessert/memberrecharge">
      <div class="form_settings" style="margin-left:60px" >
        <p><span>会员卡号</span><input type="text" name="memberId" readonly="readonly" value="<%=m.getMemberId() %>" /></p>
        <p><span>银行卡号</span><input id="bankCardForRecharge" type="text" name="bankCard" readonly="readonly" value="<%=m.getBankCardId() %>"/></p>
        <p><span>充值金额</span><input id="rechargeForRecharge" type="text" name="rechargeAmount" value="" /></p>
        <p><span>支付密码</span><input id="payPasswdForRecharge" type="password" name="paypasswd" value="" /></p>
      </div>
      <div class="form_settings">
        <input class="submit" type="button" value="关闭" onclick="closeRecharge()"/>
        <input class="submit" type="submit" value="确认充值"/>
      </div>
    </form>
  </div>
  
  <%if(request.getAttribute("bonusConvertRes")==null){ %>
  <div id="lightForBonusConvert" class="bonus_content">
  <%}
    else{ %>
  <div id="lightForBonusConvert" class="bonus_content" style="display:block">
  <%} %>
    <p></p>
    <h2 align="center">会员积分兑换</h2>
    <p></p>
    <form>
      <div class="form_settings" style="margin-left:60px" >
        <p><span>会员卡号</span><input type="text" readonly="readonly" value="<%=m.getMemberId() %>" /></p>
        <p><span>账户余额</span><input id="residualForConvert" type="text" readonly="readonly" value="<%=m.getResidual() %>"/></p>
        <p><span>会员积分</span><input id="bonusOwn" type="text" name="rechargeAmount" readonly="readonly" value="<%=m.getBonusPoint() %>" /></p>
        <p><span>兑换积分</span><input id="bonusUsed" type="text" name="bonusUsed" value="" /></p>
        <p id="bonusUsedExceed" style="display:none"><strong>兑换积分不能大于会员积分！</strong></p>
        <p id="bonusUsedZero" style="display:none"><strong>兑换积分不能为0！</strong></p>
      </div>
      <div class="form_settings">
        <input class="submit" type="button" value="关闭" onclick="closeConvert()"/>
        <input class="submit" type="button" value="确认兑换" onclick="ensureBonusConvert()"/>
      </div>
      <%if(request.getAttribute("bonusConvertRes")!=null){ %>
        <p><strong>积分兑换成功！</strong></p>
      <%} %>
    </form>
  </div>
  </div>
  
  <%if(request.getAttribute("withdrawFailed")==null){ %>
  <div id="lightForWithdraw" class="withdraw_content">
  <%}
    else{%>
  <div id="lightForWithdraw" class="withdraw_content" style="display:block">
  <%} %>
    <p></p>
    <h2 align="center">会员卡注销</h2>
    <p></p>
    <form action="<%=path%>/Dessert/memberwithdraw">
      <div class="form_settings" style="margin-left:60px" >
        <p><strong>tip:会员卡一旦注销将不再可用，请慎重！</strong></p>
        <%if(m.getCardState()==0){ %>
        <p><span>会员卡号</span><input type="text" name="memberId" readonly="readonly" value="<%=m.getMemberId() %>" /></p>
        <p><span>会员卡状态</span><input type="text" name="cardState" readonly="readonly" value="未激活" /></p>
        <%}
          else{%>
        <p><strong>&nbsp;注销会员卡需要填写支付密码，注销后，卡上余额将返还给绑定的银行卡！So，一定要填对密码哟！</strong></p>
        <p><span>会员卡号</span><input type="text" name="memberId" readonly="readonly" value="<%=m.getMemberId() %>" /></p>
        <p><span>银行卡号</span><input id="bankCardForWithdraw" type="text" name="bankCard" readonly="readonly" value="<%=m.getBankCardId() %>"/></p>
        <p><span>支付密码</span><input id="payPasswdForWithdraw" type="password" name="paypasswd" value="" /></p>
      	<%} %>
      </div>
      <div class="form_settings">
        <input class="submit" type="button" value="关闭" onclick="closeWithdraw()"/>
        <input class="submit" type="submit" value="确认注销"/>
      </div>
      <%if(request.getAttribute("withdrawFailed")!=null){ %>
        <p id="withdrawAlert" style="margin-left:100px"><strong>会员卡注销失败！</strong></p>
      <%} %>
    </form>
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
    function onlyNumber(event){
        var keyCode = event.keyCode;
        if(keyCode<48 || keyCode>57){
            event.keyCode = 0;
        }
    }
    $(function(){
        $("#ageInput").keydown(onlyNumber);
    });
    $(function(){
        $("#telInput").keydown(onlyNumber);
    });
  </script>
  <script type="text/javascript">
    function modifyBaseInfo(){
    	document.getElementById("nameInput").value="<%=m.getMemberName()%>";
    	document.getElementById("genderInput").value="<%=m.getMemberGender()%>";
    	document.getElementById("ageInput").value="<%=m.getMemberAge()%>";
    	document.getElementById("telInput").value="<%=m.getMemberTel()%>";
    	document.getElementById("locInput").value="<%=m.getMemberLoc()%>";
    	document.getElementById("lightForModifyInfo").style.display='block';
    }
    function closeModify(){
    	document.getElementById("nameInput").value="";
    	document.getElementById("genderInput").value="";
		document.getElementById("ageInput").value="";
		document.getElementById("telInput").value="";
		document.getElementById("locInput").value="";
    	document.getElementById("lightForModifyInfo").style.display='none';
    }
    function activateCard(){
    	document.getElementById("lightForActivateCard").style.display='block';
    }
    function closeBankCardBound(){
    	document.getElementById("bankCardInput").value='';
    	document.getElementById("payPasswdSet").value='';
		document.getElementById("bankCardNull").style.display='none';
		document.getElementById("passwordNull").style.display='none';
    	document.getElementById("lightForActivateCard").style.display='none';
    }
    function ensureBandBankCard(){
		document.getElementById("bankCardNull").style.display='none';
		document.getElementById("passwordNull").style.display='none';
    	var bankCard = document.getElementById("bankCardInput").value;
    	if(bankCard.toString()==''){
    		document.getElementById("bankCardNull").style.display='block';
    	}
    	else {
    		var paypasswd = document.getElementById("payPasswdSet").value;
    		if(paypasswd==''){
    			document.getElementById("passwordNull").style.display='block';
    		}
    		else{
	    		var memberId = document.getElementById("memberIdInput").value;
    			window.location.href='<%=path%>/Dessert/bandbankcard?bankCard='+bankCard+'&paypasswd='+paypasswd;
    		}
    	}
    }
    function closeActivate(){
    	document.getElementById("rechargeInput").value='';
    	document.getElementById("payPasswdInput").value='';
		document.getElementById("passwordNotInput").style.display='none';
		document.getElementById("rechargeAlert").style.display='none';
    	document.getElementById("lightForActivateCard").style.display='none';
    }
    function ensureActivateCard(){
		document.getElementById("passwordNotInput").style.display='none';
		document.getElementById("rechargeAlert").style.display='none';
		var payPasswd = document.getElementById("payPasswdInput").value;
    	if(payPasswd==''){
    		document.getElementById("passwordNotInput").style.display='block';
    	}
    	else {
        	var recharge = document.getElementById("rechargeInput").value;
    		if(recharge<200){
    			document.getElementById("rechargeAlert").style.display='block';
	    	}
	    	else{
	    		var bankCard = document.getElementById("bankCardAutoInput").value;
	    		window.location.href='<%=path%>/Dessert/cardactivate?bankCard='+bankCard+'&rechargeAmount='+recharge+'&payPasswdInput='+payPasswd;
	    	}
    	}
    }
    function regainCard(){
    	var residual=<%=m.getResidual() %>
    	if(parseInt(residual)>=30){
    		document.getElementById("regainAmount").value='任意金额';
    		document.getElementById("regainAmount").size=document.getElementById("regainAmount").value.length;
        }
    	else{
    		var regain=(30-residual).toFixed(2);
    		document.getElementById("regainAmount").value=regain+'元';
    		document.getElementById("regainAmount").size=document.getElementById("regainAmount").value.length;
    	}
    	document.getElementById("lightForRegainCard").style.display='block';
    }
    function closeRegain(){
    	document.getElementById("regainAlert").style.display='none';
	    document.getElementById("passwdNullForRegain").style.display='none';
    	document.getElementById("regainAmount").value='';
    	document.getElementById("rechargeForRegain").value='';
    	document.getElementById("payPasswdForRegain").value='';
    	document.getElementById("lightForRegainCard").style.display='none';
    }
    function ensureRegain(){
    	document.getElementById("regainAlert").style.display='none';
	    document.getElementById("passwdNullForRegain").style.display='none';
    	var regainAmount=document.getElementById("rechargeForRegain").value;
    	if(parseInt(regainAmount)==0){
    		document.getElementById("regainAlert").style.display='block';
    	}
    	else{
        	var residual=<%=m.getResidual() %>
    		var temp=30-residual-regainAmount;
    		if(temp>0){
        		document.getElementById("regainAlert").style.display='block';
    		}
    		else{
    			var paypasswd=document.getElementById("payPasswdForRegain").value;
    			if(paypasswd==''){
    			    document.getElementById("passwdNullForRegain").style.display='block';
    			}
    			else{
    				window.location.href='<%=path%>/Dessert/cardregain?regainAmount='+regainAmount+'&paypasswd='+paypasswd;
    		    }
    		}
    	}
    }
    function rechargeMoney(){
		document.getElementById("lightForRechargeMoney").style.display='block';
    }
    function closeRecharge(){
		document.getElementById("rechargeForRecharge").value='';
		document.getElementById("payPasswdForRecharge").value='';
		document.getElementById("lightForRechargeMoney").style.display='none';
    }
    function bonusConvert(){
    	document.getElementById("lightForBonusConvert").style.display='block';
    }
    function closeConvert(){
		document.getElementById("bonusUsed").value='';
		document.getElementById("bonusUsedExceed").style.display='none';
		document.getElementById("bonusUsedZero").style.display='none';
		document.getElementById("lightForBonusConvert").style.display='none';
    }
    function ensureBonusConvert(){
		document.getElementById("bonusUsedExceed").style.display='none';
		document.getElementById("bonusUsedZero").style.display='none';
    	var bonusOwn = document.getElementById("bonusOwn").value;
    	var bonusUsed = document.getElementById("bonusUsed").value;
    	if(parseInt(bonusUsed)>parseInt(bonusOwn)){
			document.getElementById("bonusUsedExceed").style.display='block';
			document.getElementById("bonusUsed").value=bonusOwn;
    	}
    	else{
    		if(bonusUsed==0){
    			document.getElementById("bonusUsedZero").style.display='block';
    		}
    		else{
        		window.location.href='<%=path%>/Dessert/memberbonusconvert?bonusUsed='+bonusUsed;
    		}
    	}
    }
    function withdrawCard(){
		document.getElementById("lightForWithdraw").style.display='block';
    }
    function closeWithdraw(){
    	if(<%=m.getCardState()%>!=0){
			document.getElementById("payPasswdForWithdraw").value='';
    	}
		document.getElementById("lightForWithdraw").style.display='none';
    }
  </script>
</body>
</html>