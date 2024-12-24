package hugo.simplesns.core.domain.repository;

import hugo.simplesns.core.domain.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByUserIdAndPostId(Long userId, Long postId);

    int countByPostId(Long postId);

    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);

    Page<Like> findAllByPostId(Long postId, Pageable pageable);

}
