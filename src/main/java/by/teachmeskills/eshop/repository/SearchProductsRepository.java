package by.teachmeskills.eshop.repository;

import by.teachmeskills.eshop.repository.domain.Product;

import java.util.List;
import java.util.Map;

public interface SearchProductsRepository {
    List<Product> findProductsListByParams(Map<String, String> searchParams, int currentPage, int recordsPerPage);
}
