package by.mmarshal.app.service;

import by.mmarshal.app.dao.domain.BaseEntity;
import by.mmarshal.app.exceptions.ServiceException;

public interface BaseService<T extends BaseEntity> {

    int create(T entity) throws ServiceException;

    T read(int id) throws ServiceException;

    void update(T entity) throws ServiceException;

    void delete(int id) throws ServiceException;

}
