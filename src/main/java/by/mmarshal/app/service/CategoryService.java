package by.mmarshal.app.service;

import by.mmarshal.app.dao.domain.Category;
import by.mmarshal.app.exceptions.DAOException;

import java.util.List;

public interface CategoryService extends BaseService<Category> {
    List<Category> getAllCategories() throws DAOException, DAOException;
}
