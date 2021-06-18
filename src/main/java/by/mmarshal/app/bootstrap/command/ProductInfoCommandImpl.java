package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.PagesPathEnum;
import by.mmarshal.app.bootstrap.RequestParamsEnum;
import by.mmarshal.app.bootstrap.model.Item;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.mmarshal.app.bootstrap.RequestParamsEnum.ITEMS;

public class ProductInfoCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest request){
        return checkReceivedItem(request);
    }

    private String checkReceivedItem(HttpServletRequest request) {
        Integer productId = Integer.valueOf(request.getParameter(RequestParamsEnum.ITEMID.getValue()));

        List<Item> items = new ArrayList<>();

        try  {
            Connection connection = (Connection) request.getAttribute("connection");
            String productName = request.getParameter("product");

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM items" +
                    " WHERE ID = '" + productId + "'" , ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
        return PagesPathEnum.PRODUCT_INFO.getPath();
    }



}
