package projectJM.jotItDown.converter;

import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.dto.request.MemberRequestDTO;
import projectJM.jotItDown.dto.response.MemberResponseDTO;

public class MemberConverter {

    public static Member toMember (MemberRequestDTO.JoinDTO joinDTO) {
        return Member.builder()
                .id(joinDTO.getMemberId())
                .email(joinDTO.getEmail())
                .password(joinDTO.getPassword())
                .signup_type(joinDTO.getSignUpType())
                .build();
    }

    public static MemberResponseDTO.JoinResultDTO toJoinResult (Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .createdAt(member.getCreated_at())
                .build();
    }
}
