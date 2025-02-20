package projectJM.jotItDown.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projectJM.jotItDown.apiPayload.code.status.ErrorStatus;
import projectJM.jotItDown.apiPayload.exception.handler.MemberHandler;
import projectJM.jotItDown.converter.MemberConverter;
import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.dto.request.MemberRequestDTO;
import projectJM.jotItDown.repository.MemberRepository;
import projectJM.jotItDown.service.MemberService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member createMember(MemberRequestDTO.JoinDTO joinDTO) {
        Member member = MemberConverter.toMember(joinDTO);
        return memberRepository.save(member);
    }

    @Override
    public Member readMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new MemberHandler(ErrorStatus._NOT_FOUND_MEMBER);
        });
        return member;
    }

    @Override
    public List<Member> readMembers() {
        return memberRepository.findAll();
    }
}
