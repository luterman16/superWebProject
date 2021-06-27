package by.mmarshal.app.service.impl;

import by.mmarshal.app.dao.OrderDao;
import by.mmarshal.app.dao.domain.Order;
import by.mmarshal.app.dao.impl.OrderDaoImpl;
import by.mmarshal.app.exceptions.DAOException;
import by.mmarshal.app.exceptions.ServiceException;
import by.mmarshal.app.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public int create(Order order) throws ServiceException {
        return orderDao.create(order);
    }

    @Override
    public Order read(int id) throws ServiceException {
        return orderDao.read(id);
    }

    @Override
    public void update(Order order) throws ServiceException {
        orderDao.update(order);
    }

    @Override
    public void delete(int id) throws ServiceException {
        orderDao.delete(id);
    }

    @Override
    public List<Order> getOrderByUserId(int userId) throws DAOException {
        return orderDao.getOrderByUserId(userId);
    }
}
