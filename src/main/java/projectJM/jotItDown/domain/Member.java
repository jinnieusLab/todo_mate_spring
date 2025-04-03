package projectJM.jotItDown.domain;

import jakarta.persistence.*;
import lombok.*;
import projectJM.jotItDown.domain.common.BaseEntity;
import projectJM.jotItDown.domain.enums.Role;
import projectJM.jotItDown.domain.enums.SignUpType;
import projectJM.jotItDown.domain.mapping.MemberChatroom;
import projectJM.jotItDown.dto.request.MemberRequestDTO;

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
    @Column(name = "memberId")
    private Long id;

    @Column(length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SignUpType signUpType;

    @Column(length = 20, nullable = false)
    @Builder.Default
    private String nickname = "User";

    @Column(columnDefinition = "TEXT")
    private String profileUrl;

    private String introMessage;

    private String accessToken;

    private String refreshToken;

    private boolean isDeleted;

    // 참조 되는 PK
    @OneToMany(mappedBy = "memberFollower", cascade = CascadeType.ALL)
    private List<Follow> followingList = new ArrayList<>();

    @OneToMany(mappedBy = "memberFollowed", cascade = CascadeType.ALL)
    private List<Follow> followerList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Diary> diaryList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Alarm> alarmList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<TodoCategory> todoCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberChatroom> memberChatroomList = new ArrayList<>();

    //
    public void update(MemberRequestDTO.MemberUpdateDTO memberUpdateDTO) {
        nickname = memberUpdateDTO.getNickname();
        profileUrl = memberUpdateDTO.getProfileUrl();
        introMessage = memberUpdateDTO.getIntroMessage();
    }
}
