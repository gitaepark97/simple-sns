package hugo.simplesns.controller;

import hugo.simplesns.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
class UserController {

    private final UserService userService;

    @PostMapping
    void createUser(@RequestBody @Valid CreateUserRequest request) {
        userService.createUser(request.username(), request.password());
    }

    @GetMapping("/{username}")
    UserResponse getUser(@PathVariable String username) {
        return UserResponse.from(userService.getUserByUsername(username));
    }

}
