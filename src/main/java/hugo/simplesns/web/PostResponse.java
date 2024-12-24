package hugo.simplesns.web;

import hugo.simplesns.core.domain.Post;
import hugo.simplesns.core.domain.User;

record PostResponse(
    Long id,
    UserResponse writer,
    String title,
    String content,
    Long createTime,
    int likeCount
) {

    static PostResponse from(Post post, User writer, int likeCount) {
        return new PostResponse(
            post.getId(),
            UserResponse.from(writer),
            post.getTitle(),
            post.getContent(),
            post.getCreateTime(),
            likeCount
        );
    }

}
