package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.PagesPathEnum;
import by.mmarshal.app.bootstrap.RequestParamsEnum;
import by.mmarshal.app.dao.domain.Product;
import by.mmarshal.app.exceptions.DAOException;
import by.mmarshal.app.exceptions.ServiceException;
import by.mmarshal.app.service.ProductService;
import by.mmarshal.app.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
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

        List<Product> items = new ArrayList<>();

        ProductService productService = new ProductServiceImpl();

        try {
            items.add(productService.read(productId));
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute(ITEMS.getValue(), items);
        return PagesPathEnum.PRODUCT_INFO.getPath();
    }
}
