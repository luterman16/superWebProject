package by.mmarshal.app.service.impl;

import by.mmarshal.app.dao.ProductDao;
import by.mmarshal.app.dao.domain.Product;
import by.mmarshal.app.dao.impl.ProductDaoImpl;
import by.mmarshal.app.exceptions.DAOException;
import by.mmarshal.app.exceptions.ServiceException;
import by.mmarshal.app.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public int create(Product product) throws ServiceException {
        return productDao.create(product);
    }

    @Override
    public Product read(int id) throws ServiceException {
        return productDao.read(id);
    }

    @Override
    public void update(Product product) throws ServiceException {
        productDao.update(product);
    }

    @Override
    public void delete(int id) throws ServiceException {
        productDao.delete(id);
    }

    @Override
    public List<Product> getAllProducts() throws DAOException {
        return productDao.getAllProducts();
    }

    @Override
    public List<Product> getProductByName(String productName) throws DAOException {
        return productDao.getProductByName(productName);
    }

    @Override
    public List<Product> getProductByCategoryId(String categoryId) throws DAOException {
        return productDao.getProductByCategoryId(categoryId);
    }

    @Override
    public List<Product> getProductByDescription(String description) throws DAOException {
        return productDao.getProductByDescription(description);
    }

    @Override
    public List<Integer> getProductByOrderId(int orderId) throws DAOException {
        return productDao.getProductByOrderId(orderId);
    }
}
