package projectJM.jotItDown.domain;

import jakarta.persistence.*;
import lombok.*;
import projectJM.jotItDown.domain.common.BaseEntity;
import projectJM.jotItDown.domain.enums.SignUpType;
import projectJM.jotItDown.domain.mapping.MemberChatroom;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private SignUpType signup_type;

    @Column(length = 20)
    private String nickname;

    @Column(columnDefinition = "TEXT")
    private String profile_url;

    private String intro_message;

    private String access_token;

    private boolean is_deleted;

    // 참조 되는 PK
    @OneToMany(mappedBy = "member_follower", cascade = CascadeType.ALL)
    private List<Follow> followingList = new ArrayList<>();

    @OneToMany(mappedBy = "member_followed", cascade = CascadeType.ALL)
    private List<Follow> followerList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Diary> diaryList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Alarm> alarmList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Todo> todoList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberChatroom> memberChatroomList = new ArrayList<>();
}
