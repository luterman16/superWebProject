package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.PagesPathEnum;
import by.mmarshal.app.bootstrap.RequestParamsEnum;
import by.mmarshal.app.bootstrap.model.Cart;
import by.mmarshal.app.dao.domain.Category;
import by.mmarshal.app.dao.domain.Product;
import by.mmarshal.app.exceptions.DAOException;
import by.mmarshal.app.exceptions.ServiceException;
import by.mmarshal.app.service.CategoryService;
import by.mmarshal.app.service.ProductService;
import by.mmarshal.app.service.impl.CategoryServiceImpl;
import by.mmarshal.app.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

        //________________________________________________________________
        //получить id категории
        String productCategoryId = request.getParameter("product");

        //получить название категории по ид
        CategoryService categoryService = new CategoryServiceImpl();
        try {
            Category category = categoryService.read(Integer.valueOf(productCategoryId));
            request.setAttribute("CATEGORY_NAME", category.getName());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        //получить продукт по категории из бд
        List<Product> items = new ArrayList<>();
        ProductService productService = new ProductServiceImpl();

        try {
            items = productService.getProductByCategoryId(productCategoryId);
            request.setAttribute(ITEMS.getValue(), items);
        } catch (DAOException e) {
            e.printStackTrace();
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
