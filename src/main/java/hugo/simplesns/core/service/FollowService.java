package hugo.simplesns.core.service;

import hugo.simplesns.core.domain.Follow;
import hugo.simplesns.core.domain.repository.FollowRepository;
import hugo.simplesns.core.exception.ErrorCode;
import hugo.simplesns.core.support.provider.ClockProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final ClockProvider clockProvider;
    private final FollowRepository followRepository;

    public void follow(Long followerId, Long followingId) {
        checkAlreadyFollowed(followerId, followingId);
        Follow follow = Follow.of(followerId, followingId, clockProvider.millis());
        followRepository.save(follow);
    }

    @Transactional
    public void unfollow(Long followerId, Long followingId) {
        Follow follow = getFollow(followerId, followingId);
        follow.delete(clockProvider.millis());
        followRepository.save(follow);
    }

    public List<Long> getFollowingIds(Long userId) {
        return followRepository.findByFollowerId(userId).stream().map(Follow::getFollowingId).toList();
    }

    public Page<Long> getFollowingIds(Long userId, Pageable pageable) {
        return followRepository.findByFollowerId(userId, pageable).map(Follow::getFollowingId);
    }

    public Page<Long> getFollowerIds(Long userId, Pageable pageable) {
        return followRepository.findByFollowingId(userId, pageable).map(Follow::getFollowerId);
    }

    private Follow getFollow(Long followerId, Long followingId) {
        return followRepository.findByFollowerIdAndFollowingId(followerId, followingId)
            .orElseThrow(ErrorCode.NOT_FOUND_FOLLOW::toException);
    }

    private void checkAlreadyFollowed(Long followerId, Long followingId) {
        if (followRepository.existsByFollowerIdAndFollowingId(followerId, followingId)) {
            throw ErrorCode.ALREADY_FOLLOWED.toException();
        }
    }

}
