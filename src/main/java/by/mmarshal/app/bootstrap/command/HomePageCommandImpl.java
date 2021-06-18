package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.PagesPathEnum;
import by.mmarshal.app.bootstrap.exceptions.CommandException;
import by.mmarshal.app.bootstrap.model.Category;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static by.mmarshal.app.bootstrap.RequestParamsEnum.CATEGORY;

public class HomePageCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Category> categories = new ArrayList<>();

        Category mobilePhones = new Category("Mobile phone", "mobile.jpg");
        Category laptops = new Category("Laptop", "laptop.jpg");
        Category fridge = new Category("Fridge", "fridge.jpg");
        Category camera = new Category("Camera", "camera.jpg");
        Category gps = new Category("GPS", "gps.jpg");
        Category cars = new Category("Cars", "cars.jpg");

        categories.add(mobilePhones);
        categories.add(laptops);
        categories.add(fridge);
        categories.add(camera);
        categories.add(gps);
        categories.add(cars);

        request.setAttribute(CATEGORY.getValue(), categories);

        return PagesPathEnum.CATEGORIES.getPath();
    }
}
