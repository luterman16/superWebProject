package by.mmarshal.app.dao;

import by.mmarshal.app.dao.domain.Category;
import by.mmarshal.app.exceptions.DAOException;

import java.util.List;

public interface CategoryDao extends BaseDao<Category> {
    List<Category> getAllCategories() throws DAOException;
}
