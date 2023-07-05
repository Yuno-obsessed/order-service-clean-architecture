package sanity.nil.tourservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sanity.nil.tourservice.dao.UserRepository;
import sanity.nil.tourservice.entity.User;
import sanity.nil.tourservice.service.UserService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void save(User entity) {
        userRepository.save(entity);
    }

    @Override
    public User get(UUID id) {
        return userRepository.getByUserId(id).orElse(null);
    }

    @Override
    public User getByIdentifier(String identifier) {
        return userRepository.getByIdentifier(identifier).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean isEmailVerified(UUID id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public boolean delete(UUID id) {
        userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }
}
