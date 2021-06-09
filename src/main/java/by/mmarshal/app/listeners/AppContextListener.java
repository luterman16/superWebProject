package by.mmarshal.app.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

        String url = ctx.getInitParameter("DBURL");
        String dbuser = ctx.getInitParameter("DBUSER");
        String dbpwd = ctx.getInitParameter("DBPWD");

        DBConnectionManager dbConnectionManager = new DBConnectionManager(url, dbuser, dbpwd);
        ctx.setAttribute("DBManager", dbConnectionManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        DBConnectionManager dbConnectionManager = (DBConnectionManager) ctx.getAttribute("DBManeger");
        dbConnectionManager.closeConnection();
        System.out.println("connection off");
    }
}
