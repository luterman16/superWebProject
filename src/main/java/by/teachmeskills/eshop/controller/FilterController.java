package by.teachmeskills.eshop.controller;

import by.teachmeskills.eshop.Utils.SearchParamGenerator;
import by.teachmeskills.eshop.exceptions.ControllerException;
import by.teachmeskills.eshop.repository.domain.Product;
import by.teachmeskills.eshop.service.ProductService;
import by.teachmeskills.eshop.service.SearchProductService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

import static by.teachmeskills.eshop.PagesPathEnum.FILTER_PAGE;
import static by.teachmeskills.eshop.RequestParamsEnum.ITEMS;
import static by.teachmeskills.eshop.Utils.CheckCort.checkCort;

@RestController
@RequestMapping("/filter")
public class FilterController {
    private final ProductService productService;
    private final SearchProductService searchProductService;

    public FilterController(ProductService productService, SearchProductService searchProductService) {
        this.productService = productService;
        this.searchProductService = searchProductService;
    }

    @PostMapping
    public ModelAndView getFilterPage(@RequestParam String pricebefore, @RequestParam String priceafter, @RequestParam String category,HttpSession HttpSession) throws ControllerException {
        checkCort(HttpSession);
        String searchText = HttpSession.getAttribute("searchText").toString();

        Map<String,String> params = SearchParamGenerator.generatorSearchParams(searchText, category, pricebefore, priceafter );

        List<Product> productList = searchProductService.findProductsListByParams(params, 1, 5);
        ModelMap model = new ModelMap();

        model.addAttribute(ITEMS.getValue(), productList);

        return new ModelAndView(FILTER_PAGE.getPath(), model);
    }

}


