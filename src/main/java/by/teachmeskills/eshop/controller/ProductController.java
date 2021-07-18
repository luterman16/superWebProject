package by.teachmeskills.eshop.controller;

import by.teachmeskills.eshop.repository.domain.Product;
import by.teachmeskills.eshop.exceptions.ControllerException;
import by.teachmeskills.eshop.service.ProductService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


import static by.teachmeskills.eshop.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.eshop.RequestParamsEnum.*;
import static by.teachmeskills.eshop.Utils.CheckCort.checkCort;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView getProduct(@RequestParam int itemId, HttpSession HttpSession) throws ControllerException {
        checkCort(HttpSession);

        ModelMap model = new ModelMap();
        Product product = productService.read(itemId).get();
        model.addAttribute(ITEMS.getValue(), product);

        return new ModelAndView(PRODUCT_PAGE.getPath(), model);
    }
}
