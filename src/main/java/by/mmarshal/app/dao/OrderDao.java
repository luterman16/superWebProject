package by.mmarshal.app.dao;

import by.mmarshal.app.dao.domain.Order;
import by.mmarshal.app.exceptions.DAOException;

import java.util.List;

public interface OrderDao extends BaseDao<Order>{
    List<Order> getOrderByUserId (int userId) throws DAOException;
}
