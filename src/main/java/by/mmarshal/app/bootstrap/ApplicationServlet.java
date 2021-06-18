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

import static by.mmarshal.app.bootstrap.RequestParamsEnum.COUNT_CART_PRODUCTS;
import static by.mmarshal.app.bootstrap.RequestParamsEnum.SHOPPING_CART_PRODUCTS;

@WebServlet({"/eshop", ""})
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
        HttpSession httpSession = request.getSession();
        ServletContext servletContext = getServletContext();
        DBConnectionManager connectionManager = (DBConnectionManager) servletContext.getAttribute("DBManager");
        Connection connection = connectionManager.getConnection();
        request.setAttribute("connection", connection);

        if (httpSession.getAttribute(COUNT_CART_PRODUCTS.getValue()) == null){
            httpSession.setAttribute(COUNT_CART_PRODUCTS.getValue(), 0);
        }

        BaseCommand requestCommand = CommandFactory.defineCommand(request);
        String path;

        try {
            path = requestCommand.execute(request);
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch (CommandException | ServletException | IOException e) {
            e.printStackTrace();
            request.getRequestDispatcher(PagesPathEnum.HOME_PAGE.getPath()).forward(request,response);
        }
    }
}
