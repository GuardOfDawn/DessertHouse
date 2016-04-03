package dessert.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.models.Store;
import dessert.models.StoreUser;
import dessert.remoteService.salespersonManage.SalespersonManageService;
import dessert.remoteService.userManage.UserManageService;
import dessert.utility.IDProducer;

@Controller
public class UserLoginAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserManageService userManage;
	@Autowired
	private SalespersonManageService salespersonManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")==null){
			String loginValue = request.getParameter("userId");
			boolean isLoginAction = (null == loginValue) ? false : true;
			if (isLoginAction) {
				IDProducer.getInstance();
				boolean loginRes = userManage.checkUserLogin(loginValue, request.getParameter("password"));
				if(loginRes){
					StoreUser user = userManage.findStoreUserInfo(loginValue);
					session.put("user", user);
					session.put("userId", loginValue);
					session.put("userName", user.getUserName());
					if(user.getUserType().equals("BranchAttendant")){
						Store s = salespersonManage.getSalespersonStore(user.getUserId());
						session.put("store",s);
					}
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
