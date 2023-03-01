package com.monofi.auth;

import com.monofi.model.Authority;
import com.monofi.model.EmailVerificationToken;
import com.monofi.model.User;
import com.monofi.model.enums.UserAuthority;
import com.monofi.repository.AuthorityJpaRepository;
import com.monofi.repository.EmailVerificationTokenRepository;
import com.monofi.repository.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private EmailVerificationTokenRepository emailVerificationTokenRepository;
    @Autowired
    private AuthorityJpaRepository authorityJpaRepository;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2SuccessHandler.class);

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response , Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String email = oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email").toString();
        if (userJpaRepository.findByUsername(email).isPresent()){
            User user = userJpaRepository.findByUsername(email).get();
            if(user.isEnabled() && user.isAccountNonLocked() && user.isCredentialsNonExpired() && user.isAccountNonExpired()){
                LOGGER.info("{} logged in",email);
                final Authentication auth = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                email,
                                ""
                        )
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
                String token = tokenProvider.generateToken(auth);
                response.sendRedirect("/auth/oauth2/token?token="+token);
            }
            else {
                response.sendRedirect("/auth/oauth2/token?token=");
            }
        }else{
            User user = new User();
            if(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("iss")==null){//Facebook registration
                String fullName = oAuth2AuthenticationToken.getPrincipal().getAttributes().get("name").toString();
                String[] nameAndSurname = fullName.split(" ");
                user.setName(nameAndSurname[0]);
                user.setSurname(nameAndSurname[1]);
            }else{
                user.setName(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("given_name").toString());
                if (oAuth2AuthenticationToken.getPrincipal().getAttributes().get("family_name")==null){
                    user.setSurname(null);
                }
                else {
                    user.setSurname(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("family_name").toString());
                }
            }

            user.setUsername(email);
            user.setPassword(passwordEncoder.encode(""));
            user.setEnabled(true);
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setRegistrationDate(LocalDateTime.now());
            Set<Authority> authorities =  new HashSet<>();
            Authority authority = authorityJpaRepository.findByAuthority(UserAuthority.ROLE_NOT_VERIFIED_USER.toString()).orElse(
                    Authority.builder().authority(UserAuthority.ROLE_NOT_VERIFIED_USER.toString()).build()
            );
            authorityJpaRepository.save(authority);
            authorities.add(authority);
            user.setAuthorities(authorities);
            userJpaRepository.save(user);
            EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
            emailVerificationToken.setVerifiedAt(LocalDateTime.now());
            emailVerificationToken.setUser(user);
            emailVerificationTokenRepository.save(emailVerificationToken);
            final Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            ""
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = tokenProvider.generateToken(auth);
            LOGGER.info("{} registered",email);
            response.sendRedirect("/auth/oauth2/token?token="+token);
        }
    }
}
