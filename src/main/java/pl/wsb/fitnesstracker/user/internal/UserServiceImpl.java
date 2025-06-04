package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<UserDto> getUser(final Long userId) {
        return userRepository.findById(userId).map(userMapper::toDto);
    }

    public List<UserEmailDto> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(final Long userId) { userRepository.deleteById(userId);}

    public List<User> getOlderUsers(final LocalDate time) {
        return userRepository.getOlderUsers(time);
    }

    public void updateUser(final Long userId, final UserDto updateUser) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(u -> {
            u.setFirstName(updateUser.firstName());
            u.setLastName(updateUser.lastName());
            u.setEmail(updateUser.email());
            u.setBirthdate(updateUser.birthdate());
            userRepository.save(u);
        });
    }
}