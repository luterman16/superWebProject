package by.mmarshal.app.service.impl;


import by.mmarshal.app.dao.UserDao;
import by.mmarshal.app.dao.domain.User;
import by.mmarshal.app.dao.impl.UserDaoImpl;
import by.mmarshal.app.exceptions.ServiceException;
import by.mmarshal.app.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public int create(User user) throws ServiceException {
        return userDao.create(user);
    }

    @Override
    public User read(int id) throws ServiceException {
        return userDao.read(id);
    }

    @Override
    public void update(User user) throws ServiceException {
        userDao.update(user);
    }

    @Override
    public void delete(int id) throws ServiceException {
        userDao.delete(id);
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        return userDao.getUserByEmail(email);
    }

}
