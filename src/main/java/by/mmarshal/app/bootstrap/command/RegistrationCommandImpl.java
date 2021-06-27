package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.PagesPathEnum;
import by.mmarshal.app.bootstrap.exceptions.CommandException;
import by.mmarshal.app.bootstrap.exceptions.RequestParamNullException;
import by.mmarshal.app.dao.domain.User;
import by.mmarshal.app.exceptions.ServiceException;
import by.mmarshal.app.service.UserService;
import by.mmarshal.app.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return checkReceivedUser(request);
    }

    private String checkReceivedUser(HttpServletRequest request) throws RequestParamNullException {

        if (request.getParameter("name") == null) {
            return PagesPathEnum.REGISTRATION.getPath();
        } else {
            User user = User.userBuilder()
                    .withName(request.getParameter("name"))
                    .withSurname(request.getParameter("surname"))
                    .withEmail(request.getParameter("username"))
                    .withPassword(request.getParameter("password"))
                    .withDateOfBirthday(request.getParameter("dateofbirthday"))
                    .build();

            UserService userService = new UserServiceImpl();
            try {
                userService.create(user);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            return PagesPathEnum.SIGN_IN_PAGE.getPath();
        }
    }
}
