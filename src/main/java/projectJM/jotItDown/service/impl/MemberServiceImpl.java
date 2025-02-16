package projectJM.jotItDown.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projectJM.jotItDown.converter.MemberConverter;
import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.dto.request.MemberRequestDTO;
import projectJM.jotItDown.repository.MemberRepository;
import projectJM.jotItDown.service.MemberService;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member createMember(MemberRequestDTO.JoinDTO joinDTO) {
        Member member = MemberConverter.toMember(joinDTO);
        return memberRepository.save(member);
    }
}
