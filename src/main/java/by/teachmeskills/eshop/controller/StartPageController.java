package by.teachmeskills.eshop.controller;


import by.teachmeskills.eshop.exceptions.ControllerException;
import by.teachmeskills.eshop.repository.domain.Category;
import by.teachmeskills.eshop.service.CategoryService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

import static by.teachmeskills.eshop.PagesPathEnum.START_PAGE;
import static by.teachmeskills.eshop.RequestParamsEnum.POPULAR_CATEGORIES_LIST_REQ_PARAM;
import static by.teachmeskills.eshop.Utils.CheckCort.checkCort;

@RestController
@RequestMapping("/home")
public class StartPageController {
    private final CategoryService categoryService;

    public StartPageController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView getHomePage(HttpSession HttpSession) throws ControllerException {
        checkCort(HttpSession);
        ModelMap model = new ModelMap();

        List<Category> categoriesList = categoryService.getAllCategories();

        model.addAttribute(POPULAR_CATEGORIES_LIST_REQ_PARAM.getValue(), categoriesList);

        return new ModelAndView(START_PAGE.getPath(), model);
    }
}