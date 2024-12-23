package hugo.simplesns.controller;

import hugo.simplesns.domain.User;

public record UserResponse(
    String username
) {

    static UserResponse from(User user) {
        return new UserResponse(user.getUsername());
    }

}
