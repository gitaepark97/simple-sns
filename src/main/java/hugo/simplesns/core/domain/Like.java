package hugo.simplesns.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "\"like\"")
@SQLRestriction("delete_time IS NULL")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long postId;

    private Long createTime;

    private Long deleteTime;

    public static Like of(Long userId, Long postId, Long currentTime) {
        return Like.builder()
            .userId(userId)
            .postId(postId)
            .createTime(currentTime)
            .build();
    }

    public void delete(Long currentTime) {
        deleteTime = currentTime;
    }

}
