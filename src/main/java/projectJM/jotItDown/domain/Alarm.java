package projectJM.jotItDown.domain;

import jakarta.persistence.*;
import lombok.*;
import projectJM.jotItDown.domain.common.BaseEntity;
import projectJM.jotItDown.domain.enums.DType;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Alarm extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarmId")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private boolean isConfirmed;

    @Enumerated(EnumType.STRING)
    private DType dType;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
}
