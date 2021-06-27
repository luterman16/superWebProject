package by.mmarshal.app.dao.impl;

import by.mmarshal.app.dao.OrderDao;
import by.mmarshal.app.dao.domain.Order;
import by.mmarshal.app.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    /**
     * SQL-statements
     */
    private static final String ADD_ORDER = "INSERT INTO orders VALUES (NULL, ?, ?, ?)";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private static final String GET_ORDER_BY_USER_ID = "SELECT * FROM orders WHERE USER_ID = ?";

    /**
     * Error causes fields
     */
    private static final String ERROR_IN_CREATE_USER = "Error while adding ORDER to database";
    private static final String ERROR_IN_READ_ORDER = "Error while getting ORDER from database";

    @Override
    public int create(Order order) throws DAOException {
        int orderId;

        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, order.getDate());
            ps.setInt(2, order.getPrice());
            ps.setInt(3, order.getUser_id());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            orderId = rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_CREATE_USER, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return orderId;
    }



    @Override
    public Order read(int id) throws DAOException {
        Order order = null;
        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ORDER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = Order.orderBuilder().buildFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_ORDER, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return order;
    }

    @Override
    public List<Order> getOrderByUserId (int userId) throws DAOException {
        List<Order> orders = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ORDER_BY_USER_ID)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
        while (rs.next()) {
                Order order = Order.orderBuilder().buildFromResultSet(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_ORDER, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return orders;
    }

    @Override
    public void update(Order entity) throws DAOException {
        //пока не применяется
    }

    @Override
    public void delete(int id) throws DAOException {
        //пока не применяется
    }
}
