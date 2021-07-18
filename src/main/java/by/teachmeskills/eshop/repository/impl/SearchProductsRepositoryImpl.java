package by.teachmeskills.eshop.repository.impl;


import by.teachmeskills.eshop.repository.SearchProductsRepository;
import by.teachmeskills.eshop.repository.domain.Category;
import by.teachmeskills.eshop.repository.domain.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchProductsRepositoryImpl implements SearchProductsRepository {

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public List<Product> findProductsListByParams(Map<String, String> searchParams, int currentPage, int recordsPerPage) {
        List<Product> products;
        int start = currentPage * recordsPerPage - recordsPerPage;

        List<Predicate> predicates = new ArrayList<>();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);

        predicates.add(builder.or(
                builder.like(root.get("name"), "%" + searchParams.get("productName") + "%"),
                builder.like(root.get("description"), "%" + searchParams.get("productName") + "%")
        ));


        if (Integer.parseInt(searchParams.get("category")) > 0) {
            Join<Product, Category> categoryJoin = root.join("category");
            predicates.add(builder.equal(categoryJoin.get("id"), searchParams.get("category")));
        }

        if (searchParams.containsKey("priceBefore")) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("price"), searchParams.get("priceBefore")));
        }

        if (searchParams.containsKey("priceAfter")) {
            predicates.add(builder.lessThanOrEqualTo(root.get("price"), searchParams.get("priceAfter")));
        }

        criteria.select(root).where(predicates.toArray(new Predicate[]{}));

        if (recordsPerPage > 1) {
            TypedQuery<Product> typedQuery = entityManager.createQuery(criteria);
            typedQuery.setFirstResult(start);
            typedQuery.setMaxResults(recordsPerPage);
            products = typedQuery.getResultList();
        } else {
            products = entityManager.createQuery(criteria).getResultList();
        }

        return products;
    }

}