package by.teachmeskills.eshop.service.impl;


import by.teachmeskills.eshop.exceptions.ServiceException;
import by.teachmeskills.eshop.repository.ProductRepository;
import by.teachmeskills.eshop.repository.domain.Product;
import by.teachmeskills.eshop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllByIdIn(ArrayList<Integer> productList) {
        return productRepository.findAllByIdIn(productList);
    }

    @Override
    public Page<Product> findProductsByCategoryId(int categoryId, Pageable pageable) {
        return productRepository.findAllByCategory_Id(categoryId, pageable);
    }

    @Override
    public Page<Product> findProductsByNameOrDescriptionLike(String productName, String descriptionName, Pageable pageable) {
        Page<Product> productList = productRepository.findProductsByNameLikeOrDescriptionLike("%" + productName + "%", "%" + descriptionName + "%", pageable);
        return productList;
    }

    @Override
    public Product create(Product entity) throws ServiceException {
        return null;
    }

    @Override
    public Optional<Product> read(int id) throws ServiceException {
        return productRepository.findById(id);
    }

    @Override
    public void update(Product entity) throws ServiceException {

    }

    @Override
    public void delete(Product entity) throws ServiceException {

    }
}