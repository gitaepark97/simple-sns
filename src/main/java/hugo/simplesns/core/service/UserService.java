package hugo.simplesns.core.service;

import hugo.simplesns.core.domain.User;
import hugo.simplesns.core.domain.repository.UserRepository;
import hugo.simplesns.core.exception.ErrorCode;
import hugo.simplesns.core.support.provider.ClockProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final ClockProvider clockProvider;
    private final UserRepository userRepository;

    @Transactional
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
