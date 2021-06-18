package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.PagesPathEnum;
import by.mmarshal.app.bootstrap.RequestParamsEnum;
import by.mmarshal.app.bootstrap.model.Item;
import by.mmarshal.app.bootstrap.model.Cart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static by.mmarshal.app.bootstrap.RequestParamsEnum.ITEMS;
import static by.mmarshal.app.bootstrap.RequestParamsEnum.SHOPPING_CART_PRODUCTS;

public class ItemPageCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest request){
        return checkReceivedItem(request);
    }

    private String checkReceivedItem(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (request.getParameter(RequestParamsEnum.ADDITEM.getValue()) != null) {
            Integer countRequest = Integer.valueOf(request.getParameter(RequestParamsEnum.ADDITEM.getValue()));
            Integer countSession = (Integer) session.getAttribute("itemsInCort");

            if (!Optional.ofNullable(countSession).isPresent()) {
                countSession = 0;
            } else {
                countSession = (Integer) session.getAttribute("itemsInCort");
            }
            session.setAttribute("itemsInCort", countRequest + countSession);


            addItemToCart(request,session);
        }

        List<Item> items = new ArrayList<>();

        try  {
            Connection connection = (Connection) request.getAttribute("connection");
            String productName = request.getParameter("product");

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM items" +
                    " WHERE TYPE = '" + productName + "'" , ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String nameImage = rs.getString("imageName");
                int price = rs.getInt("price");
                String description = rs.getString("description");
                String type = rs.getString("type");
                items.add(new Item(id, name, nameImage, description,price, type));
            }

            request.setAttribute(ITEMS.getValue(), items);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return PagesPathEnum.ITEM_PAGE.getPath();
    }

    private void addItemToCart(HttpServletRequest request, HttpSession session){

        if (session.getAttribute(SHOPPING_CART_PRODUCTS.getValue()) != null){
            Cart cart = new Cart((ArrayList<Integer>) session.getAttribute(SHOPPING_CART_PRODUCTS.getValue())) ;
            Integer itemId = Integer.valueOf(request.getParameter(RequestParamsEnum.ITEMID.getValue()));
            cart.addProduct(itemId);
            session.setAttribute(SHOPPING_CART_PRODUCTS.getValue(), cart.getProducts());
        } else {
            Cart cart = new Cart();
            Integer itemId = Integer.valueOf(request.getParameter(RequestParamsEnum.ITEMID.getValue()));
            cart.addProduct(itemId);
            session.setAttribute(SHOPPING_CART_PRODUCTS.getValue(), cart.getProducts());
        }


    }

}
