package projectJM.jotItDown.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import projectJM.jotItDown.domain.mapping.MemberChatroom;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    @Column(length = 50)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String chatroom_img_url;

    // 참조 되는 PK
    @OneToMany(mappedBy = "Chatroom", cascade = CascadeType.ALL)
    private List<MemberChatroom> memberChatroomList = new ArrayList<>();
}
