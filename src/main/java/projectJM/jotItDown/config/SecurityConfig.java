package projectJM.jotItDown.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import projectJM.jotItDown.config.JWT.JWTAccessDeniedHandler;
import projectJM.jotItDown.config.JWT.JWTExceptionFilter;
import projectJM.jotItDown.config.JWT.JWTFilter;
import projectJM.jotItDown.config.JWT.JWTUtil;
import projectJM.jotItDown.config.filter.LoginFilter;
import projectJM.jotItDown.domain.enums.Role;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final PrincipalDetailsService principalDetailsService;
    private final JWTAccessDeniedHandler jwtAccessDeniedHandler;

    private final String[] allowUrl = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/login",
            "/auth",
            "/ws"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                // JWT 사용
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement((session) ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JWTFilter(jwtUtil, principalDetailsService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTExceptionFilter(), JWTFilter.class)
                .addFilterBefore(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

        // 허용 url
        http.authorizeHttpRequests((request) -> request
                .requestMatchers(HttpMethod.POST,"/members").permitAll()
                .requestMatchers(HttpMethod.GET,"/members/{memberId}").hasRole(Role.USER.name())
                .requestMatchers(HttpMethod.GET,"/members").permitAll()
                .requestMatchers(allowUrl).permitAll()
                .anyRequest().authenticated());

        // HttpServletResponse (JWT 관련) 헤더 설정
        http.headers( headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin())
                .addHeaderWriter((request, response) -> {
                    response.setHeader("Access-Control-Expose-Headers", "Authorization");
                }));

        http.exceptionHandling(configurer -> configurer.accessDeniedHandler(jwtAccessDeniedHandler));

        return http.build();
    }
}