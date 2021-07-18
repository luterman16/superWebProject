package by.teachmeskills.eshop.controller;

import by.teachmeskills.eshop.exceptions.ControllerException;
import by.teachmeskills.eshop.repository.domain.User;
import by.teachmeskills.eshop.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static by.teachmeskills.eshop.PagesPathEnum.*;
import static by.teachmeskills.eshop.PagesPathEnum.SIGN_IN_PAGE;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView openRegistrationPage() {
        return new ModelAndView(REGISTRATION.getPath());
    }

    @PostMapping(value = "/register")
    public ModelAndView register(@Valid @ModelAttribute("user") User user,
                              BindingResult bindingResult, ModelAndView modelAndView) throws ControllerException {

        if (bindingResult.hasErrors()) {
            populateError("email", modelAndView, bindingResult);
            populateError("password", modelAndView, bindingResult);

            modelAndView.setViewName(REGISTRATION.getPath());
            return modelAndView;
        }

        userService.create(user);
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