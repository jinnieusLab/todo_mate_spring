package projectJM.jotItDown.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "follower_id")
    private Member member_follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "followed_id")
    private Member member_followed;
}
