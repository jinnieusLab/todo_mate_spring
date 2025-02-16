package projectJM.jotItDown.service;

import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.dto.request.MemberRequestDTO;

public interface MemberService {

    Member createMember(MemberRequestDTO.JoinDTO joinDTO);
}
