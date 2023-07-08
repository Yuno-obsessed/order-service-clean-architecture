package sanity.nil.tourservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sanity.nil.tourservice.infrastructure.database.dao.UserRepository;
import sanity.nil.tourservice.infrastructure.database.model.User;
import sanity.nil.tourservice.service.UserService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void save(User model) {
        userRepository.save(model);
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
        return userRepository.findById(id).map(User::isEmailConfirmed).orElse(false);
    }

    @Override
    public void update(User model) {
        if (userRepository.findById(model.getUserId()).isPresent()) {
            userRepository.save(model);
        }
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
