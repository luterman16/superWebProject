package by.mmarshal.app;

import by.mmarshal.app.model.Calculator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calculator")
public class MainServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Double num1 = Double.parseDouble(req.getParameter("number1"));
        Double num2 = Double.parseDouble(req.getParameter("number2"));
        String operation = req.getParameter("operation");

        switch (operation) {
            case "toPlus" :
                resp.getWriter().write("Result: " + Calculator.toPlus(num1, num2));
                break;
            case "toMinus" :
                resp.getWriter().write("Result: " + Calculator.toMinus(num1, num2));
                break;
            case "toMultiply" :
                resp.getWriter().write("Result: " + Calculator.toMultiply(num1, num2));
                break;
            case "toSplit" :
                resp.getWriter().write("Result: " + Calculator.toSplit(num1, num2));
                break;
            default: resp.getWriter().write("Operation failed ");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/calculator.html");
        dispatcher.forward(req, resp);
    }
}
