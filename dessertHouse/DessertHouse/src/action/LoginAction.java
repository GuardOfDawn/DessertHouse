package action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import action.business.StoreUserBean;
import service.UserOpService;

@Controller
public class LoginAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserOpService userManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")==null){
			String loginValue = request.getParameter("studentId");
			boolean isLoginAction = (null == loginValue) ? false : true;
			if (isLoginAction) {
				boolean loginRes = userManage.checkStoreUserLogin(loginValue, request.getParameter("password"));
				if(loginRes){
					StoreUserBean user = userManage.getStoreUser(loginValue);
					session.put("user", user);
					return SUCCESS;
				}
				else{
					return ERROR;
				}
			}
			else{
				return INPUT;
			}
		}
		else{
			return SUCCESS;
		}
	}

}
