package by.mmarshal.app.service;

import by.mmarshal.app.dao.domain.User;
import by.mmarshal.app.exceptions.ServiceException;

public interface UserService extends BaseService<User> {

    User getUserByEmail(String email) throws ServiceException;
}
