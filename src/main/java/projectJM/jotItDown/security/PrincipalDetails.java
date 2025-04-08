package projectJM.jotItDown.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import projectJM.jotItDown.domain.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails {

    private final Member member;

    // 역할들
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roleList = new ArrayList<>();
        roleList.add("ROLE_" + member.getRole().name());
        Collection<? extends GrantedAuthority> authorities = roleList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // 🚨 디버깅: Spring Security가 어떤 권한을 인식하는지 확인
        System.out.println("Authorities: " + authorities);

        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }
}
