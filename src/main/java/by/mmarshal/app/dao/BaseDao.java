package by.mmarshal.app.dao;


import by.mmarshal.app.dao.domain.BaseEntity;
import by.mmarshal.app.exceptions.DAOException;

public interface BaseDao<T extends BaseEntity> {

    ConnectionPool databaseConnection = ConnectionPool.getInstance();

    int create(T entity) throws DAOException;

    T read (int id) throws DAOException;

    void update(T entity) throws DAOException;

    void delete(int id) throws DAOException;

}
