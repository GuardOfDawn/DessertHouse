package action;

import org.springframework.stereotype.Controller;

@Controller
public class LogoutAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String execute(){
		if (null != session) {
        	boolean isIdexist = false;
        	boolean isStudentExist = false;
        	Object Id = null;
        	Object stu = null;
        	for(Object o:session.keySet()){
        		if(o.equals("userId")){
        			isIdexist = true;
        			Id = o;
        		}
        		if(o.equals("user")){
        			isStudentExist = true;
        			stu = o;
        		}
        	}
        	if(isIdexist){
        		session.remove(Id);
        	}
        	if(isStudentExist){
        		session.remove(stu);
        	}
        }
		return SUCCESS;
	}

}
