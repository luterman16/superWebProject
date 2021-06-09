package by.mmarshal.app.bootstrap;


import by.mmarshal.app.bootstrap.command.BaseCommand;
import by.mmarshal.app.bootstrap.command.CommandFactory;
import by.mmarshal.app.bootstrap.exceptions.CommandException;
import by.mmarshal.app.listeners.DBConnectionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/eshop")
public class ApplicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        DBConnectionManager connectionManager = (DBConnectionManager) servletContext.getAttribute("DBManager");
        Connection connection = connectionManager.getConnection();
        request.setAttribute("connection", connection);

        BaseCommand requestCommand = CommandFactory.defineCommand(request);
        String path;

        try {
            path = requestCommand.execute(request);
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch (CommandException | ServletException | IOException e) {
            e.printStackTrace();
            request.getRequestDispatcher(PagesPathEnum.SIGN_IN_PAGE.getPath()).forward(request,response);
        }
    }
}
