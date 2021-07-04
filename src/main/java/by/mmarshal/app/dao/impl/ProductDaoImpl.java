package by.mmarshal.app.dao.impl;

import by.mmarshal.app.dao.ProductDao;
import by.mmarshal.app.dao.domain.Product;
import by.mmarshal.app.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final String ADD_PRODUCT = "INSERT INTO products VALUES (NULL, ?, ?, ?, ?, ?)";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ?";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM products";
    private static final String GET_PRODUCT_BY_NAME = "SELECT * FROM products WHERE NAME like ?";
    private static final String GET_PRODUCT_BY_DESCRIPTION = "SELECT * FROM products WHERE DESCRIPTION like ?";
    private static final String GET_PRODUCT_BY_CATEGORY_ID = "SELECT * FROM products WHERE CATEGORY_ID = ?";
    private static final String GET_PRODUCT_BY_ORDER_ID = "SELECT * FROM eshop.orders_products WHERE ORDER_ID = ?";

    private static final String ERROR_IN_CREATE_PRODUCT = "Error while adding product to database";
    private static final String ERROR_IN_READ_PRODUCT = "Error while getting product from database";
    private static final String ERROR_IN_GET_ALL_PRODUCT = "Error while getting list of PRODUCTS";
    private static final String ERROR_IN_GET_PRODUCT_BY_NAME = "Error while getting product of PRODUCTS by name";
    private static final String ERROR_IN_GET_PRODUCT_BY_DESCRIPTION = "Error while getting product of PRODUCTS by description";
    private static final String ERROR_IN_GET_PRODUCT_BY_CATEGORY_ID = "Error while getting product of PRODUCTS by category id";
    private static final String ERROR_IN_GET_PRODUCT_BY_ORDER_ID = "Error while getting product of ORDER_PRODUCT by ORDER_ID";

    @Override
    public int create(Product entity) throws DAOException {
        int productId;

        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(ADD_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getDescription());
            ps.setString(3, entity.getImage_path());
            ps.setInt(4, entity.getPrice());
            ps.setInt(5, entity.getCategory_id());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            productId = rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_CREATE_PRODUCT, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return productId;
    }

    @Override
    public Product read(int id) throws DAOException {
        Product product = null;
        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = Product.productBuilder().buildFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_PRODUCT, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return product;
    }

    @Override
    public void update(Product entity) throws DAOException {
        //пока не применяется
    }

    @Override
    public void delete(int id) throws DAOException, DAOException {
        //пока не применяется
    }

    public List<Product> getProductByName(String productName) throws DAOException{
        List<Product> products = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_PRODUCT_BY_NAME)) {
            ps.setString(1, productName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = Product.productBuilder().buildFromResultSet(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_GET_PRODUCT_BY_NAME, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return products;
    }

    public List<Product> getProductByDescription(String description) throws DAOException{
        List<Product> products = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_PRODUCT_BY_DESCRIPTION)) {
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = Product.productBuilder().buildFromResultSet(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_GET_PRODUCT_BY_DESCRIPTION, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return products;
    }

    public List<Integer> getProductByOrderId(int orderId) throws DAOException{
        List<Integer> pruductsIdList = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_PRODUCT_BY_ORDER_ID)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pruductsIdList.add(rs.getInt("PRODUCT_ID"));
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_GET_PRODUCT_BY_ORDER_ID, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return pruductsIdList;
    }

    public List<Product> getProductByCategoryId(String categoryId) throws DAOException{
        List<Product> products = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_PRODUCT_BY_CATEGORY_ID)) {
            ps.setString(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = Product.productBuilder().buildFromResultSet(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_GET_PRODUCT_BY_CATEGORY_ID, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return products;
    }


    @Override
    public List<Product> getAllProducts() throws DAOException {
        List<Product> products = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_PRODUCTS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = Product.productBuilder().buildFromResultSet(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_GET_ALL_PRODUCT, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return products;
    }
}
