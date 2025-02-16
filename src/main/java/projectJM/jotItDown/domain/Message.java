package projectJM.jotItDown.domain;

import jakarta.persistence.*;
import lombok.*;
import projectJM.jotItDown.domain.common.BaseEntity;
import projectJM.jotItDown.domain.mapping.MemberChatroom;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private int readCount;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberChatroomId")
    private MemberChatroom memberChatroom;
}
