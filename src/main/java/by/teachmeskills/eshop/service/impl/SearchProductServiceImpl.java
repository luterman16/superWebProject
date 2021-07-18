package by.teachmeskills.eshop.service.impl;

import by.teachmeskills.eshop.repository.SearchProductsRepository;
import by.teachmeskills.eshop.repository.domain.Product;
import by.teachmeskills.eshop.service.SearchProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchProductServiceImpl implements SearchProductService {
    private final SearchProductsRepository searchProductsRepository;

    public SearchProductServiceImpl(SearchProductsRepository searchProductsRepository) {
        this.searchProductsRepository = searchProductsRepository;
    }

    public List<Product> findProductsListByParams(Map<String, String> searchParams, int currentPage, int recordsPerPage){
        return searchProductsRepository.findProductsListByParams(searchParams, currentPage, recordsPerPage);
    }
}
