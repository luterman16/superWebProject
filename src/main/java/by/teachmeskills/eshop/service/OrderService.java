package by.teachmeskills.eshop.service;

import by.teachmeskills.eshop.exceptions.ServiceException;
import by.teachmeskills.eshop.repository.domain.Order;

import java.util.List;

public interface OrderService extends BaseService<Order> {
    List<Order> findOrderByUserId(int userId) throws ServiceException;
}
