package projectJM.jotItDown.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import projectJM.jotItDown.domain.Chatroom;
import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.domain.Message;
import projectJM.jotItDown.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class MemberChatroom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_chatroom_id")
    private Long id;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom;

    // 참조 되는 PK
    @OneToMany(mappedBy = "memberChatroom", cascade = CascadeType.ALL)
    private List<Message> messageList = new ArrayList<>();
}
