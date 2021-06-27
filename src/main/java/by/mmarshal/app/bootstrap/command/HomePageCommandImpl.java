package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.PagesPathEnum;
import by.mmarshal.app.bootstrap.exceptions.CommandException;
import by.mmarshal.app.dao.domain.Category;
import by.mmarshal.app.exceptions.DAOException;
import by.mmarshal.app.service.CategoryService;
import by.mmarshal.app.service.impl.CategoryServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static by.mmarshal.app.bootstrap.RequestParamsEnum.CATEGORY;

public class HomePageCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Category> categories = new ArrayList<>();

        CategoryService categoryService = new CategoryServiceImpl();

        try {
            categories = categoryService.getAllCategories();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        request.setAttribute(CATEGORY.getValue(), categories);
        return PagesPathEnum.CATEGORIES.getPath();
    }
}
