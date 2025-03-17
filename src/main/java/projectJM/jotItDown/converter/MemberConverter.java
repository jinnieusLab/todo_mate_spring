package projectJM.jotItDown.converter;

import org.springframework.security.crypto.password.PasswordEncoder;
import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.dto.request.MemberRequestDTO;
import projectJM.jotItDown.dto.response.MemberResponseDTO;

import java.util.List;

public class MemberConverter {

    public static Member toMember (MemberRequestDTO.JoinDTO joinDTO, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(joinDTO.getEmail())
                .password(passwordEncoder.encode(joinDTO.getPassword()))
                .signUpType(joinDTO.getSignUpType())
                .role(joinDTO.getRole())
                .build();
    }

    public static MemberResponseDTO.JoinResultDTO toJoinResult (Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static MemberResponseDTO.memberPreviewDTO toMemberPreview (Member member) {
        return MemberResponseDTO.memberPreviewDTO.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .signUpType(member.getSignUpType())
                .nickname(member.getNickname())
                .build();
    }

    public static MemberResponseDTO.memberPreviewListDTO toMemberPreviewList (List<Member> memberList) {
        List<MemberResponseDTO.memberPreviewDTO> memberPreviewDTOList = memberList.stream()
                .map(MemberConverter::toMemberPreview)
                .toList();

        return MemberResponseDTO.memberPreviewListDTO.builder()
                .memberPreviewDTOList(memberPreviewDTOList)
                .build();
    }
}
