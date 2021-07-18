package by.teachmeskills.eshop.repository;

import by.teachmeskills.eshop.repository.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
