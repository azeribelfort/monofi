package com.monofi.auth;

import com.monofi.model.Authority;
import com.monofi.model.User;
import com.monofi.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserJpaRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User %s was not found in the database", username)));
        org.springframework.security.core.userdetails.User.UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.disabled(false);
        builder.password(user.getPassword());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (Authority authority : user.getAuthorities()) {
            authorityList.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        builder.authorities(authorityList);
        return builder.build();
    }
}
