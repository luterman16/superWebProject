package by.teachmeskills.eshop.service;

import by.teachmeskills.eshop.repository.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface ProductService extends BaseService<Product> {

    List<Product> findAllByIdIn(ArrayList<Integer> productList);

    Page<Product> findProductsByCategoryId(int categoryId, Pageable pageable);

    Page<Product> findProductsByNameOrDescriptionLike(String productName, String descriptionName, Pageable pageable);

}