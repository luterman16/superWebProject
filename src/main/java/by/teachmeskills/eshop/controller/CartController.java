package by.teachmeskills.eshop.controller;

import by.teachmeskills.eshop.components.Cart;
import by.teachmeskills.eshop.exceptions.ControllerException;
import by.teachmeskills.eshop.repository.domain.Product;
import by.teachmeskills.eshop.service.ProductService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static by.teachmeskills.eshop.PagesPathEnum.AUTH_FOR_CONFIRM_PAY_PAGE;
import static by.teachmeskills.eshop.PagesPathEnum.CART_PAGE;
import static by.teachmeskills.eshop.RequestParamsEnum.*;
import static by.teachmeskills.eshop.Utils.CheckCort.checkCort;


@RestController
@RequestMapping("/cart")
public class CartController {
    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView getProductsInCategory(HttpSession HttpSession) throws ControllerException {
        checkCort(HttpSession);
        Cart cart = new Cart((ArrayList<Integer>) HttpSession.getAttribute(SHOPPING_CART.getValue()));

        List<Product> products = productService.findAllByIdIn(cart.getProducts());

        ModelMap model = new ModelMap();
        model.addAttribute(SHOPPING_CART.getValue(), products);
        model.addAttribute(TOTAL_PRICE.getValue(), cart.getTotalPrice(products));

        return new ModelAndView(CART_PAGE.getPath(), model);
    }

    @GetMapping("/remove")
    public ModelAndView getProductsInCategory(@RequestParam int itemId,HttpSession HttpSession) throws ControllerException {

        Cart cart = new Cart((ArrayList<Integer>) HttpSession.getAttribute(SHOPPING_CART.getValue()));
        cart.removeProduct(itemId);

        List<Product> products = productService.findAllByIdIn(cart.getProducts());

        ModelMap model = new ModelMap();
        HttpSession.setAttribute(SHOPPING_CART.getValue(), cart.getProducts());
        HttpSession.setAttribute(SHOPPING_CART_COUNTER.getValue(), products.size());
        model.addAttribute(SHOPPING_CART.getValue(), products);
        model.addAttribute(TOTAL_PRICE.getValue(), cart.getTotalPrice(products));

        return new ModelAndView(CART_PAGE.getPath(), model);
    }

    @PostMapping("*/confirmpay")
    public ModelAndView confirmOrder () throws ControllerException {
        return new ModelAndView(AUTH_FOR_CONFIRM_PAY_PAGE.getPath());
    }
}
