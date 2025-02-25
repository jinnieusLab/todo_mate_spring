package projectJM.jotItDown.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import projectJM.jotItDown.apiPayload.BaseResponse;
import projectJM.jotItDown.converter.MemberConverter;
import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.dto.request.MemberRequestDTO;
import projectJM.jotItDown.dto.response.MemberResponseDTO;
import projectJM.jotItDown.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberService memberService;

    @PostMapping("/members")
    public BaseResponse<MemberResponseDTO.JoinResultDTO> createMember (@RequestBody MemberRequestDTO.JoinDTO joinDTO) {
        Member member = memberService.createMember(joinDTO);
        return BaseResponse.onSuccess(MemberConverter.toJoinResult(member));
    }

    // 특정 멤버 1명 조회
    @Transactional(readOnly = true)
    @GetMapping("/members/{memberId}")
    public BaseResponse<MemberResponseDTO.memberPreviewDTO> readMember (@PathVariable Long memberId) {
        Member member = memberService.readMember(memberId);
        return BaseResponse.onSuccess(MemberConverter.toMemberPreview(member));
    }

    // 맴버 리스트 조회
    @Transactional(readOnly = true)
    @GetMapping("/members")
    public BaseResponse<MemberResponseDTO.memberPreviewListDTO> readMembers () {
        List<Member> memberList = memberService.readMembers();
        return BaseResponse.onSuccess(MemberConverter.toMemberPreviewList(memberList));
    }

    // 멤버 업데이트
    @PatchMapping("/members/{memberId}")
    public BaseResponse<MemberResponseDTO.memberPreviewDTO> updateMember (@PathVariable Long memberId, @RequestBody MemberRequestDTO.MemberUpdateDTO memberUpdateDTO) {
        Member member = memberService.updateMember(memberId, memberUpdateDTO);
        return BaseResponse.onSuccess(MemberConverter.toMemberPreview(member));
    }

    // 특정 멤버 삭제
    @DeleteMapping("/members/{memberId}")
    public void deleteMember (@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }
}
