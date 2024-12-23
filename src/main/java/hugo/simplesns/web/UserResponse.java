package hugo.simplesns.web;

import hugo.simplesns.core.domain.User;

public record UserResponse(
    String username
) {

    static UserResponse from(User user) {
        return new UserResponse(user.getUsername());
    }

}
