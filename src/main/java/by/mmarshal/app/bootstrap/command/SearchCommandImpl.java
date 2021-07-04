package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.PagesPathEnum;
import by.mmarshal.app.bootstrap.RequestParamsEnum;
import by.mmarshal.app.bootstrap.exceptions.CommandException;
import by.mmarshal.app.dao.domain.Product;
import by.mmarshal.app.exceptions.DAOException;
import by.mmarshal.app.service.ProductService;
import by.mmarshal.app.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static by.mmarshal.app.bootstrap.RequestParamsEnum.ITEMS;
import static by.mmarshal.app.bootstrap.RequestParamsEnum.ITEMS_BY_SEARCH;

public class SearchCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return checkReceivedProduct(request);
    }

    private String checkReceivedProduct(HttpServletRequest request)  {
        HttpSession session = request.getSession();
        List<Product> products = new ArrayList<>();
            String searchText = request.getParameter(RequestParamsEnum.SEARCH_TEXT.getValue());

        ProductService productService = new ProductServiceImpl();
        try {
            products = productService.getProductByName("%" + searchText + "%");
            request.setAttribute(ITEMS.getValue(), products);

            products.addAll(productService.getProductByDescription("%" + searchText + "%"));

            session.setAttribute(ITEMS_BY_SEARCH.getValue(), products);
            return PagesPathEnum.FILTER.getPath();

        } catch (DAOException e) {
            e.printStackTrace();
        }
        return PagesPathEnum.HOME_PAGE.getPath();
    }
}
