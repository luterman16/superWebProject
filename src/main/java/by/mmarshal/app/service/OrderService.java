package by.mmarshal.app.service;

import by.mmarshal.app.dao.domain.Order;
import by.mmarshal.app.exceptions.DAOException;

import java.util.List;

public interface OrderService extends BaseService<Order>{
    List<Order> getOrderByUserId (int userId) throws DAOException;
}
