package projectJM.jotItDown.service;

import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.dto.request.MemberRequestDTO;

import java.util.List;

public interface MemberService {

    Member createMember(MemberRequestDTO.JoinDTO joinDTO);

    Member readMember(Long memberId);

    List<Member> readMembers();

    void deleteMember(Long memberId);
}
