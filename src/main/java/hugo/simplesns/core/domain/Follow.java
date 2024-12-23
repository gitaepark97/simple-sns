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
@Entity
@SQLRestriction("delete_time IS NULL")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long followerId;

    private Long followingId;

    private Long creatTime;

    private Long deleteTime;

    public static Follow of(Long followerId, Long followingId, Long currentTime) {
        return Follow.builder()
            .followerId(followerId)
            .followingId(followingId)
            .creatTime(currentTime)
            .build();
    }

    public void delete(Long currentTime) {
        deleteTime = currentTime;
    }

}
