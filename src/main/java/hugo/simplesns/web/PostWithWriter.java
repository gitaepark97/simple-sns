package hugo.simplesns.web;

import hugo.simplesns.core.domain.Post;
import hugo.simplesns.core.domain.User;

record PostWithWriter(
    Post post,
    User writer
) {

    static PostWithWriter of(Post post, User writer) {
        return new PostWithWriter(post, writer);
    }

}
