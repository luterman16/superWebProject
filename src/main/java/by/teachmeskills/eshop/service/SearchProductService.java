package by.teachmeskills.eshop.service;

import by.teachmeskills.eshop.repository.domain.Product;

import java.util.List;
import java.util.Map;

public interface SearchProductService {
    List<Product> findProductsListByParams(Map<String, String> searchParams, int currentPage, int recordsPerPage);
}
