package hugo.simplesns.web;

import hugo.simplesns.core.domain.User;
import hugo.simplesns.core.service.FollowService;
import hugo.simplesns.core.service.PostService;
import hugo.simplesns.core.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
class UserController {

    private final UserService userService;
    private final PostService postService;
    private final FollowService followService;

    @PostMapping
    void createUser(@RequestBody @Valid UserCreateRequest request) {
        userService.createUser(request.username(), request.password());
    }

    @GetMapping("/{username}")
    UserResponse getUser(@PathVariable String username) {
        return UserResponse.from(userService.getUserByUsername(username));
    }

    @GetMapping("/{username}/posts")
    Page<PostResponse> getUserPosts(@PathVariable String username, Pageable pageable) {
        User writer = userService.getUserByUsername(username);
        return postService.getUserPosts(writer.getId(), pageable)
            .map(post -> PostResponse.from(post, writer));
    }

    @GetMapping("/{username}/followers")
    Page<UserResponse> getUserFollowers(@PathVariable String username, Pageable pageable) {
        User user = userService.getUserByUsername(username);
        return followService.getFollowerIds(user.getId(), pageable)
            .map(userService::getUserById)
            .map(UserResponse::from);
    }

    @GetMapping("/{username}/followings")
    Page<UserResponse> getUserFollowings(@PathVariable String username, Pageable pageable) {
        User user = userService.getUserByUsername(username);
        return followService.getFollowingIds(user.getId(), pageable)
            .map(userService::getUserById)
            .map(UserResponse::from);
    }

}

