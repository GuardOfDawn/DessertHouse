package dessert.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import dessert.utility.BonusUtility;
import dessert.utility.IDProducer;
import dessert.utility.MemberLevelUtility;
import dessert.utility.ProductType;

@WebListener
public class Initialization implements ServletContextListener, ServletContextAttributeListener,
HttpSessionListener{

	public void sessionCreated(HttpSessionEvent arg0) {}

	public void sessionDestroyed(HttpSessionEvent arg0) {}

	public void attributeAdded(ServletContextAttributeEvent arg0) {}

	public void attributeRemoved(ServletContextAttributeEvent arg0) {}

	public void attributeReplaced(ServletContextAttributeEvent arg0) {}

	public void contextDestroyed(ServletContextEvent arg0) {}

	public void contextInitialized(ServletContextEvent arg0) {
		IDProducer.getInstance();
		ProductType.getInstance();
		MemberLevelUtility.getInstance();
		BonusUtility.getInstance();
	}

}
