package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.PagesPathEnum;
import by.mmarshal.app.bootstrap.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;

public class CategoriesPage implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return PagesPathEnum.CATEGORIES.getPath();
    }

}