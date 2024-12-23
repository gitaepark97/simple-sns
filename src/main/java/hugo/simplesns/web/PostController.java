package hugo.simplesns.web;

import hugo.simplesns.core.domain.User;
import hugo.simplesns.core.service.PostService;
import hugo.simplesns.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping
    void createPost(@RequestParam String username, @RequestBody PostCreateRequest request) {
        User writer = userService.getUserByUsername(username);
        postService.createPost(writer.getId(), request.title(), request.content());
    }

}
