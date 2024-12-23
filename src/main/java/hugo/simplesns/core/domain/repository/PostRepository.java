package hugo.simplesns.core.domain.repository;

import hugo.simplesns.core.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByWriterId(Long writerId, Pageable pageable);

    Page<Post> findAllByWriterIdIn(List<Long> writerIds, Pageable pageable);

}
