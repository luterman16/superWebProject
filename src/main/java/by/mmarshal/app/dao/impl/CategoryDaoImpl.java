package by.mmarshal.app.dao.impl;

import by.mmarshal.app.dao.CategoryDao;
import by.mmarshal.app.dao.domain.Category;
import by.mmarshal.app.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    /**
     * SQL-statements
     */
    private static final String ADD_CATEGORY = "INSERT INTO categories VALUES (NULL, ?, ?, ?)";
    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM categories WHERE id = ?";
    private static final String GET_CATEGORY_BY_EMAIL = "SELECT * FROM categories WHERE email = ?";
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM categories";
    private static final String UPDATE_CATEGORY = "UPDATE categories SET RAITING ? =  WHERE id = ?";
    private static final String DELETE_CATEGORY = "DELETE FROM categories WHERE id = ?";

    /**
     * Error causes fields
     */
    private static final String ERROR_IN_CREATE_USER = "Error while adding CATEGORY to database";
    private static final String ERROR_IN_READ_CATEGORY = "Error while getting CATEGORY from database";
    private static final String ERROR_IN_UPDATE_CATEGORY = "Error while trying to update CATEGORY in database";
    private static final String ERROR_IN_DELETE_CATEGORY = "Error while deleting CATEGORY from database";
    private static final String ERROR_IN_GET_CATEGORY = "Error while getting CATEGORY by email";
    private static final String ERROR_IN_GET_ALL_CATEGORY = "Error while getting list of CATEGORY";


    @Override
    public List<Category> getAllCategories() throws DAOException {
        List<Category> categories = new ArrayList<Category>();
        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_CATEGORIES)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = Category.categoryBuilder().buildFromResultSet(rs);
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_GET_ALL_CATEGORY, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return categories;
    }


    @Override
    public int create(Category category) throws DAOException {
        int userId;

        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(ADD_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());
            ps.setInt(2, category.getRATING());
            ps.setString(3, category.getIMAGE_PATH());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            userId = rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException  e) {
            throw new DAOException(ERROR_IN_CREATE_USER, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return userId;
    }


    @Override
    public Category read(int id) throws DAOException {
        Category category = null;
        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_CATEGORY_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                category = Category.categoryBuilder().buildFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_CATEGORY, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return category;
    }

    @Override
    public void update(Category category) throws DAOException {
        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CATEGORY)) {
            ps.setInt(2, category.getId());
            ps.setInt(1, category.getRATING());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_UPDATE_CATEGORY, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_CATEGORY)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_DELETE_CATEGORY, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
    }
}
