package by.mmarshal.app.service.impl;

import by.mmarshal.app.dao.CategoryDao;
import by.mmarshal.app.dao.domain.Category;
import by.mmarshal.app.dao.domain.User;
import by.mmarshal.app.dao.impl.CategoryDaoImpl;
import by.mmarshal.app.exceptions.DAOException;
import by.mmarshal.app.exceptions.ServiceException;
import by.mmarshal.app.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public int create(Category category) throws ServiceException {
        return categoryDao.create(category);
    }

    @Override
    public Category read(int id) throws ServiceException {
        return categoryDao.read(id);
    }

    @Override
    public void update(Category category) throws ServiceException {
        categoryDao.update(category);
    }

    @Override
    public void delete(int id) throws ServiceException {
        categoryDao.delete(id);
    }

    @Override
    public List<Category> getAllCategories() throws DAOException {
        return categoryDao.getAllCategories();
    }


}
