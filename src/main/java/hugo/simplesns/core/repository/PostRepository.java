package hugo.simplesns.core.repository;

import hugo.simplesns.core.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByWriterId(Long writerId, Pageable pageable);

}
