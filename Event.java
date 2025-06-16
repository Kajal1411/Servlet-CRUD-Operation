package myPack;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Event implements ServletContextListener{
	
	public void  contextInitialized(ServletContextEvent event)
	{
		ServletContext context=event.getServletContext();
		System.out.println("Servlet Context Initialized");
		
		//Performs Initialization task here
		String url=context.getInitParameter("url");
		String username=context.getInitParameter("username");
		String password=context.getInitParameter("password");
		
		//Store DB Connection setting in servletContext
		context.setAttribute("url", url);
		context.setAttribute("username", username);
		context.setAttribute("password", password);
		
	}
	
	public void contextDestroyed(ServletContextEvent event)
	{
		    ServletContext context = event.getServletContext();

	        // Perform cleanup tasks here
	        context.removeAttribute("url");
	        context.removeAttribute("username");
	        context.removeAttribute("password");

	        System.out.println("ServletContext destroyed");
	}
}
