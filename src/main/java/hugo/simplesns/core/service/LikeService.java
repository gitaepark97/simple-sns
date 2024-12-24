package hugo.simplesns.core.service;

import hugo.simplesns.core.domain.Like;
import hugo.simplesns.core.domain.repository.LikeRepository;
import hugo.simplesns.core.exception.ErrorCode;
import hugo.simplesns.core.support.provider.ClockProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final ClockProvider clockProvider;
    private final LikeRepository likeRepository;

    @Transactional
    public void likePost(Long userId, Long postId) {
        checkAlreadyLike(userId, postId);
        Like like = Like.of(userId, postId, clockProvider.millis());
        likeRepository.save(like);
    }

    @Transactional
    public void unlikePost(Long userId, Long postId) {
        Like like = getLike(userId, postId);
        like.delete(clockProvider.millis());
        likeRepository.save(like);
    }

    public int getPostLikeCount(Long postId) {
        return likeRepository.countByPostId(postId);
    }

    public Page<Like> getPostLikes(Long postId, Pageable pageable) {
        return likeRepository.findAllByPostId(postId, pageable);
    }

    private Like getLike(Long userId, Long postId) {
        return likeRepository.findByUserIdAndPostId(userId, postId)
            .orElseThrow(ErrorCode.NOT_FOUND_FOLLOW::toException);
    }

    private void checkAlreadyLike(Long userId, Long postId) {
        if (likeRepository.existsByUserIdAndPostId(userId, postId)) {
            throw ErrorCode.ALREADY_LIKED.toException();
        }
    }

}
