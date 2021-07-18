package by.teachmeskills.eshop.service.impl;


import by.teachmeskills.eshop.exceptions.ServiceException;
import by.teachmeskills.eshop.repository.UserRepository;
import by.teachmeskills.eshop.repository.domain.User;
import by.teachmeskills.eshop.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        return userRepository.findByEmail(email);
    }

    @Override
    public User create(User entity) throws ServiceException {
        return userRepository.saveAndFlush(entity);
    }

    @Override
    public Optional<User> read(int id) throws ServiceException {
        return userRepository.findById(id);
    }

    @Override
    public void update(User entity) throws ServiceException {
        userRepository.saveAndFlush(entity);

    }

    @Override
    public void delete(User entity) throws ServiceException {
        userRepository.delete(entity);

    }
}
