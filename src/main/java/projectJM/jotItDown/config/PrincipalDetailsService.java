package projectJM.jotItDown.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projectJM.jotItDown.apiPayload.code.status.ErrorStatus;
import projectJM.jotItDown.apiPayload.exception.handler.MemberHandler;
import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> {
            throw new MemberHandler(ErrorStatus._NOT_FOUND_MEMBER);
        });

        return new PrincipalDetails(member);
    }
}
