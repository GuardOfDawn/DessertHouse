package dessert.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.models.StoreUser;
import dessert.service.userOperation.UserOpService;
import dessert.utility.IDProducer;

@Controller
public class UserLoginAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserOpService userManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")==null){
			String loginValue = request.getParameter("userId");
			boolean isLoginAction = (null == loginValue) ? false : true;
			if (isLoginAction) {
				IDProducer.getInstance();
				boolean loginRes = userManage.checkStoreUserLogin(loginValue, request.getParameter("password"));
				if(loginRes){
					StoreUser user = userManage.getStoreUser(loginValue);
					session.put("user", user);
					session.put("userId", loginValue);
					session.put("userName", user.getUserName());
					return user.getUserType();
				}
				else{
					request.setAttribute("loginFailed", "1");
					return ERROR;
				}
			}
			else{
				return INPUT;
			}
		}
		else{
			return ((StoreUser)session.get("user")).getUserType();
		}
	}

}
