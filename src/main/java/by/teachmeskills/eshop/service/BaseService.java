package by.teachmeskills.eshop.service;

import by.teachmeskills.eshop.exceptions.ServiceException;
import by.teachmeskills.eshop.repository.domain.BaseEntity;

import java.util.Optional;

public interface BaseService<T extends BaseEntity> {
    T create(T entity) throws ServiceException;

    Optional<T> read(int id) throws ServiceException;

    void update(T entity) throws ServiceException;

    void delete(T entity) throws ServiceException;

}
