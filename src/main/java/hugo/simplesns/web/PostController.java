package hugo.simplesns.web;

import hugo.simplesns.core.domain.Post;
import hugo.simplesns.core.domain.User;
import hugo.simplesns.core.service.FollowService;
import hugo.simplesns.core.service.LikeService;
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
    private final LikeService likeService;

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
            int likeCount = likeService.getPostLikeCount(post.getId());
            return PostResponse.from(post, writer, likeCount);
        });
    }

    @GetMapping("/{postId}")
    PostResponse getPost(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        User writer = userService.getUserById(post.getWriterId());
        int likeCount = likeService.getPostLikeCount(post.getId());
        return PostResponse.from(post, writer, likeCount);
    }

    @PostMapping("/{postId}/likes")
    void likePost(@PathVariable Long postId, @RequestParam Long userId) {
        User user = userService.getUserById(userId);
        Post post = postService.getPostById(postId);
        likeService.likePost(user.getId(), post.getId());
    }

    @DeleteMapping("/{postId}/likes")
    void unlikePost(@PathVariable Long postId, @RequestParam Long userId) {
        User user = userService.getUserById(userId);
        Post post = postService.getPostById(postId);
        likeService.unlikePost(user.getId(), post.getId());
    }

    @GetMapping("/{postId}/likes")
    Page<UserResponse> getLikeUsers(@PathVariable Long postId, Pageable pageable) {
        Post post = postService.getPostById(postId);
        return likeService.getPostLikes(post.getId(), pageable)
            .map(like -> userService.getUserById(like.getUserId()))
            .map(UserResponse::from);
    }

}
