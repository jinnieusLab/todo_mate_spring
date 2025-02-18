package projectJM.jotItDown.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projectJM.jotItDown.apiPayload.BaseResponse;
import projectJM.jotItDown.converter.MemberConverter;
import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.dto.request.MemberRequestDTO;
import projectJM.jotItDown.dto.response.MemberResponseDTO;
import projectJM.jotItDown.service.MemberService;

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
    @GetMapping("/members/{memberId}")
    public BaseResponse<MemberResponseDTO.memberPreviewDTO> readMember (@PathVariable Long memberId) {
        Member member = memberService.readMember(memberId);
        return BaseResponse.onSuccess(MemberConverter.toMemberPreview(member));
    }
}
