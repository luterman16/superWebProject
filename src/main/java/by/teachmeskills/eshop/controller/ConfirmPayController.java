package by.teachmeskills.eshop.controller;

import by.teachmeskills.eshop.components.Cart;
import by.teachmeskills.eshop.exceptions.ControllerException;
import by.teachmeskills.eshop.repository.domain.Order;
import by.teachmeskills.eshop.repository.domain.Product;
import by.teachmeskills.eshop.repository.domain.User;
import by.teachmeskills.eshop.service.OrderService;
import by.teachmeskills.eshop.service.ProductService;
import by.teachmeskills.eshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static by.teachmeskills.eshop.PagesPathEnum.*;
import static by.teachmeskills.eshop.RequestParamsEnum.SHOPPING_CART;

@Controller
@RequestMapping("/confirmpay")
@Validated
public class ConfirmPayController {
    private final UserService userService ;
    private final OrderService orderService;
    private final ProductService productService;

    public ConfirmPayController(UserService userService, OrderService orderService, ProductService productService) {
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @PostMapping
    public ModelAndView login(@Valid @ModelAttribute("user") User user,
                              BindingResult bindingResult, ModelAndView modelAndView, HttpSession HttpSession) throws ControllerException {

        if (bindingResult.hasErrors()) {
            populateError("email", modelAndView, bindingResult);
            populateError("password", modelAndView, bindingResult);

            modelAndView.setViewName(AUTH_FOR_CONFIRM_PAY_PAGE.getPath());
            return modelAndView;
        }

        User userDataByDB = userService.getUserByEmail(user.getEmail());

        if(userDataByDB.getEmail().equals(user.getEmail())&&
                userDataByDB.getPassword().equals(user.getPassword())
        ){

            Cart cart = new Cart((ArrayList<Integer>) HttpSession.getAttribute(SHOPPING_CART.getValue()));

            HttpSession.setAttribute(SHOPPING_CART.getValue(), cart.getProducts());
            List<Product> products = productService.findAllByIdIn(cart.getProducts());
            Date date = new Date();

            Order order = Order
                    .newBuilder()
                    .withDate(date)
                    .withPrice(cart.getTotalPrice(products))
                    .withUser(userDataByDB)
                    .withListOfProducts(products)
                    .build();

            orderService.create(order);

            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("userinfo", userDataByDB);
            modelMap.addAttribute("orderslist", orderService.findOrderByUserId(userDataByDB.getId()));
            modelAndView.setViewName(PERSONAL_INFO.getPath());
            modelAndView.addAllObjects(modelMap);
            return modelAndView;
        }

        modelAndView.setViewName(SIGN_IN_PAGE.getPath());
        return modelAndView;
    }

    private void populateError(String field, ModelAndView model, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors(field)) {
            model.addObject(field, bindingResult.getFieldError(field)
                    .getDefaultMessage());
        }
    }

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }
}
