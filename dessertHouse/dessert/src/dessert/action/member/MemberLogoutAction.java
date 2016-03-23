package dessert.action.member;

import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;

@Controller
public class MemberLogoutAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute(){
		if (null != session) {
			session.clear();
        }
		return SUCCESS;
	}
	
}
