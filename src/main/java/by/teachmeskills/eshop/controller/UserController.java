package by.teachmeskills.eshop.controller;


import by.teachmeskills.eshop.exceptions.ControllerException;
import by.teachmeskills.eshop.repository.domain.User;
import by.teachmeskills.eshop.service.CategoryService;
import by.teachmeskills.eshop.service.OrderService;
import by.teachmeskills.eshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static by.teachmeskills.eshop.PagesPathEnum.*;

@Controller
@RequestMapping("/user")
@Validated
public class UserController {
    private final CategoryService categoryService;
    private final UserService userService;
    private final OrderService orderService;

    public UserController(CategoryService categoryService, UserService userService, OrderService orderService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping
    public ModelAndView openLoginPage() {
        return new ModelAndView(SIGN_IN_PAGE.getPath());
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@Valid @ModelAttribute("user") User user,
                              BindingResult bindingResult, ModelAndView modelAndView) throws ControllerException {

        if (bindingResult.hasErrors()) {
            populateError("email", modelAndView, bindingResult);
            populateError("password", modelAndView, bindingResult);

            modelAndView.setViewName(SIGN_IN_PAGE.getPath());
            return modelAndView;
        }

        User userDataByDB = userService.getUserByEmail(user.getEmail());

        if(userDataByDB.getEmail().equals(user.getEmail())&&
            userDataByDB.getPassword().equals(user.getPassword())
        ){
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