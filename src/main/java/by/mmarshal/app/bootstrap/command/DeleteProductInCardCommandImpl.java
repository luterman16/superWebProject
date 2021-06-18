package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.exceptions.CommandException;
import by.mmarshal.app.bootstrap.model.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static by.mmarshal.app.bootstrap.RequestParamsEnum.*;

public class DeleteProductInCardCommandImpl implements BaseCommand{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        Cart cart = new Cart((ArrayList<Integer>)session.getAttribute(SHOPPING_CART_PRODUCTS.getValue()));
        cart.removeProduct(itemId);

        session.setAttribute(SHOPPING_CART_PRODUCTS.getValue(), cart.getProducts());
        session.setAttribute(COUNT_CART_PRODUCTS.getValue(), cart.getProducts().size());
        CartCommandImpl cartCommand = new CartCommandImpl();
        String path = cartCommand.execute(request);
        return path;
    }
}
