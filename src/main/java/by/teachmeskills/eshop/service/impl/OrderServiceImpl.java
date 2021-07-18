package by.teachmeskills.eshop.service.impl;

import by.teachmeskills.eshop.exceptions.ServiceException;
import by.teachmeskills.eshop.repository.OrderRepository;
import by.teachmeskills.eshop.repository.domain.Order;
import by.teachmeskills.eshop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findOrderByUserId(int userId)  throws ServiceException{
        return orderRepository.findOrderByUserId(userId);
    }

    @Override
    public Order create(Order entity) throws ServiceException {
        return orderRepository.saveAndFlush(entity);
    }

    @Override
    public Optional<Order> read(int id) throws ServiceException {
        return orderRepository.findById(id);
    }

    @Override
    public void update(Order entity) throws ServiceException {
        orderRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Order entity) throws ServiceException {
        orderRepository.delete(entity);
    }
}
