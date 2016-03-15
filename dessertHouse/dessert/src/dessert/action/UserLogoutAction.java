package dessert.action;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.models.StoreUser;
import dessert.service.userOperation.UserOpService;

@Controller
public class UserLogoutAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserOpService userManage;
	
	public String execute(){
		if (null != session) {
        	boolean isUserExist = false;
        	Object stu = null;
        	for(Object o:session.keySet()){
        		if(o.equals("user")){
        			isUserExist = true;
        			stu = o;
        		}
        	}
        	if(isUserExist){
        		StoreUser u = (StoreUser) session.get(stu);
        		u.setLastLogoutTime(new Date(System.currentTimeMillis()));
        		userManage.updateUser(u);
        	}
			session.clear();
        }
		return SUCCESS;
	}

}
