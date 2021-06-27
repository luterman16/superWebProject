package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.PagesPathEnum;
import by.mmarshal.app.bootstrap.model.Cart;
import by.mmarshal.app.dao.domain.Product;
import by.mmarshal.app.exceptions.DAOException;
import by.mmarshal.app.exceptions.ServiceException;
import by.mmarshal.app.service.ProductService;
import by.mmarshal.app.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

        List<Product> items = new ArrayList<>();
        double totalPrice = 0;

            for (int i = 0; i< cart.getProducts().size(); i++) {

                ProductService productService = new ProductServiceImpl();

                try {
                    Product product = productService.read(cart.getProducts().get(i));
                    items.add(product);

                    totalPrice += product.getPrice();
                } catch (DAOException e) {
                    e.printStackTrace();
                } catch (ServiceException e) {
                    e.printStackTrace();
                }

            }
            request.setAttribute(ITEMS.getValue(), items);
            request.setAttribute(TOTALPRICE.getValue(), totalPrice);

        return PagesPathEnum.CART_PAGE.getPath();
    }
}

