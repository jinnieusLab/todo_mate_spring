package projectJM.jotItDown.domain.mapping;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import projectJM.jotItDown.domain.Chatroom;
import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.domain.Message;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MemberChatroom {

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
    @OneToMany(mappedBy = "MemberChatroom", cascade = CascadeType.ALL)
    private List<Message> messageList = new ArrayList<>();
}
