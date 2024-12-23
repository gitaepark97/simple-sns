package hugo.simplesns.web;

import hugo.simplesns.core.domain.User;
import hugo.simplesns.core.service.FollowService;
import hugo.simplesns.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/follows")
class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @PostMapping("/{username}")
    void follow(@PathVariable String username, @RequestParam Long userId) {
        User follower = userService.getUserById(userId);
        User following = userService.getUserByUsername(username);
        followService.follow(follower.getId(), following.getId());
    }

    @DeleteMapping("/{username}")
    void unfollow(@PathVariable String username, @RequestParam Long userId) {
        User follower = userService.getUserById(userId);
        User following = userService.getUserByUsername(username);
        followService.unfollow(follower.getId(), following.getId());
    }

}
