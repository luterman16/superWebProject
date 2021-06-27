package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.PagesPathEnum;
import by.mmarshal.app.bootstrap.exceptions.CommandException;
import by.mmarshal.app.dao.domain.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.mmarshal.app.bootstrap.RequestParamsEnum.*;

public class FilterCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return checkReceivedProduct(request);
    }

    private String checkReceivedProduct(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Product> items = (List<Product>) session.getAttribute(ITEMS_BY_SEARCH.getValue());

        int pricebefore = Integer.parseInt(request.getParameter("pricebefore"));
        int priceafter = Integer.parseInt(request.getParameter("priceafter"));
        int category = Integer.parseInt(request.getParameter("category"));

        for (Product product : items) {
            if (product.getPrice() < pricebefore || product.getPrice() > priceafter) {
                items.remove(product);
            }
        }

        if (category > 0) {
            for (Product product : items) {
                if (product.getCategory_id() != category) {
                    items.remove(product);
                }
            }
        }

        request.setAttribute(ITEMS.getValue(), items);
        return PagesPathEnum.FILTER.getPath();
    }
}
