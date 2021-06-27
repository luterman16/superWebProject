package by.mmarshal.app.dao;

import by.mmarshal.app.dao.domain.Product;
import by.mmarshal.app.exceptions.DAOException;

import java.util.List;

public interface ProductDao extends BaseDao<Product> {
    List<Product> getAllProducts() throws DAOException;
    List<Product> getProductByName(String productName) throws DAOException;
    List<Product> getProductByCategoryId(String categoryId) throws DAOException;
    List<Product> getProductByDescription(String description) throws DAOException;
    List<Integer> getProductByOrderId(int orderId) throws DAOException;
}
