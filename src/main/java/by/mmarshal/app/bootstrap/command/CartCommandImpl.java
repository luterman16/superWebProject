package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.PagesPathEnum;
import by.mmarshal.app.bootstrap.model.Cart;
import by.mmarshal.app.bootstrap.model.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.mmarshal.app.bootstrap.RequestParamsEnum.*;

public class CartCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest request){
        return checkReceivedItem(request);
    }

    private String checkReceivedItem(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Cart cart = new Cart((ArrayList<Integer>) session.getAttribute(SHOPPING_CART_PRODUCTS.getValue()));



        List<Item> items = new ArrayList<>();
        double totalPrice = 0;
        try  {
            Connection connection = (Connection) request.getAttribute("connection");
            for (int i = 0; i< cart.getProducts().size(); i++) {

                PreparedStatement statement = connection.prepareStatement("SELECT * FROM items" +
                        " WHERE ID = '" + cart.getProducts().get(i) + "'", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String nameImage = rs.getString("imageName");
                    int price = rs.getInt("price");
                    String description = rs.getString("description");
                    String type = rs.getString("type");
                    items.add(new Item(id, name, nameImage, description, price, type));

                    totalPrice += price;
                }
            }

            request.setAttribute(ITEMS.getValue(), items);
            request.setAttribute(TOTALPRICE.getValue(), totalPrice);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return PagesPathEnum.CART_PAGE.getPath();
    }
}

