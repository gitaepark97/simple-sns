package hugo.simplesns.web;

import hugo.simplesns.core.domain.User;
import hugo.simplesns.core.service.FollowService;
import hugo.simplesns.core.service.PostService;
import hugo.simplesns.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
class PostController {

    private final PostService postService;
    private final UserService userService;
    private final FollowService followService;

    @PostMapping
    void createPost(@RequestParam Long userId, @RequestBody PostCreateRequest request) {
        User writer = userService.getUserById(userId);
        postService.createPost(writer.getId(), request.title(), request.content());
    }

    @GetMapping("/followers")
    Page<PostResponse> getFollowerPosts(@RequestParam Long userId, Pageable pageable) {
        User user = userService.getUserById(userId);
        List<Long> followerIds = followService.getFollowingIds(user.getId());
        return postService.getUsersPosts(followerIds, pageable).map(post -> {
            User writer = userService.getUserById(post.getWriterId());
            return PostResponse.from(post, writer);
        });
    }

}
