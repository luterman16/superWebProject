package by.mmarshal.app.dao;


import by.mmarshal.app.dao.domain.User;
import by.mmarshal.app.exceptions.DAOException;
import java.util.List;

public interface UserDao extends BaseDao<User> {
    User getUserByEmail(String email) throws DAOException;
    List<User> getAllUsers() throws DAOException;

}
