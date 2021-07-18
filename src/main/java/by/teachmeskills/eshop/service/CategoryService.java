package by.teachmeskills.eshop.service;

import by.teachmeskills.eshop.exceptions.ServiceException;
import by.teachmeskills.eshop.repository.domain.Category;

import java.util.List;

public interface CategoryService extends BaseService<Category> {
    List<Category> getAllCategories() throws ServiceException;
}
