package hugo.simplesns.web;

import hugo.simplesns.core.domain.Post;
import hugo.simplesns.core.domain.User;

record PostResponse(
    Long id,
    UserResponse writer,
    String title,
    String content,
    Long createTime
) {

    static PostResponse from(Post post, User user) {
        return new PostResponse(
            post.getId(),
            UserResponse.from(user),
            post.getTitle(),
            post.getContent(),
            post.getCreateTime()
        );
    }

}
