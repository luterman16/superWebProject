package by.teachmeskills.eshop.service;

import by.teachmeskills.eshop.exceptions.ServiceException;
import by.teachmeskills.eshop.repository.domain.User;

public interface UserService extends BaseService<User> {

    User getUserByEmail(String email) throws ServiceException;

}
