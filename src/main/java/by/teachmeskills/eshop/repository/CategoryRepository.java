package by.teachmeskills.eshop.repository;

import by.teachmeskills.eshop.repository.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
