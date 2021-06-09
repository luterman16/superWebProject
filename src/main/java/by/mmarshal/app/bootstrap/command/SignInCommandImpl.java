package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.PagesPathEnum;
import by.mmarshal.app.bootstrap.RequestParamsEnum;
import by.mmarshal.app.bootstrap.exceptions.CommandException;
import by.mmarshal.app.bootstrap.exceptions.RequestParamNullException;
import by.mmarshal.app.bootstrap.model.User;
import by.mmarshal.app.bootstrap.model.Category;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


import static by.mmarshal.app.bootstrap.RequestParamsEnum.CATEGORY;
import static by.mmarshal.app.bootstrap.utils.HttpsRequestParamValidator.*;

public class SignInCommandImpl implements BaseCommand {
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(RequestParamsEnum.LOGIN.getValue());
        String password = request.getParameter(RequestParamsEnum.PASSWORD.getValue());

        User user = getUser();

        return checkReceivedUser(user, request);
    }


    private User getUser() {
        return new User(ADMIN_LOGIN, ADMIN_PASSWORD);
    }

    private String checkReceivedUser(User user, HttpServletRequest request) throws RequestParamNullException {
        String login = request.getParameter(RequestParamsEnum.LOGIN.getValue());
        String password = request.getParameter(RequestParamsEnum.PASSWORD.getValue());

        validateParamNotNull(login);
        validateParamNotNull(password);

        if (user != null && login.equals(ADMIN_LOGIN) && password.equals(ADMIN_PASSWORD)) {
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
        } else {
            return PagesPathEnum.SIGN_IN_PAGE.getPath();
        }
    }
}
