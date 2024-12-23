package hugo.simplesns.service;

import hugo.simplesns.domain.User;
import hugo.simplesns.repository.UserRepository;
import hugo.simplesns.support.exception.ErrorCode;
import hugo.simplesns.support.provider.ClockProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final ClockProvider clockProvider;
    private final UserRepository userRepository;

    public void createUser(String username, String password) {
        checkUniqueUsername(username);

        User newUser = User.of(username, password, clockProvider.millis());
        userRepository.save(newUser);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(ErrorCode.NOT_FOUND_USER::toException);
    }

    private void checkUniqueUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw ErrorCode.DUPLICATED_USERNAME.toException();
        }
    }

}
