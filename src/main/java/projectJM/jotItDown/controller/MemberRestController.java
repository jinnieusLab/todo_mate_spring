package projectJM.jotItDown.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/")
    public BaseResponse<MemberResponseDTO.JoinResultDTO> createMember (@RequestBody MemberRequestDTO.JoinDTO joinDTO) {
        Member member = memberService.createMember(joinDTO);
        return BaseResponse.onSuccess(MemberConverter.toJoinResult(member));
    }
}
