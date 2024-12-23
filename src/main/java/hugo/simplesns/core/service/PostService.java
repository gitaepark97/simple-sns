package hugo.simplesns.core.service;

import hugo.simplesns.core.domain.Post;
import hugo.simplesns.core.domain.repository.PostRepository;
import hugo.simplesns.core.support.provider.ClockProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final ClockProvider clockProvider;
    private final PostRepository postRepository;

    public void createPost(Long writerId, String title, String content) {
        Post post = Post.of(writerId, title, content, clockProvider.millis());
        postRepository.save(post);
    }

    public Page<Post> getUserPosts(Long userId, Pageable pageable) {
        return postRepository.findAllByWriterId(userId, pageable);
    }

    public Page<Post> getUsersPosts(List<Long> userIds, Pageable pageable) {
        return postRepository.findAllByWriterIdIn(userIds, pageable);
    }

}
