package com.monofi.service.impl;

import com.monofi.auth.mapper.ModelMapperConfig;
import com.monofi.dto.RegistrationDto;
import com.monofi.exception.EmailAlreadyUsedException;
import com.monofi.exception.NotFoundException;
import com.monofi.model.Authority;
import com.monofi.model.User;
import com.monofi.repository.AuthorityJpaRepository;
import com.monofi.repository.UserJpaRepository;
import com.monofi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userRepository;
    private final AuthorityJpaRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()->new NotFoundException("Can't find user with given username"));
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException("Can't find user with given id"));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void register(RegistrationDto dto) {
        userRepository.findByUsername(dto.getUsername())
                .ifPresent(user -> {
                    throw new EmailAlreadyUsedException(dto.getUsername());
                });
        User user = createUserEntity(dto);
        userRepository.save(user);
    }


    private User createUserEntity(RegistrationDto dto) {
        User user = modelMapper.map(dto, User.class);
        Authority authority = Authority.builder()
                .authority("ROLE_USER")
                .build();
        authorityRepository.save(authority);
        Set<Authority> userAuthority = new HashSet<>();
        userAuthority.add(authority);
        user.setAuthorities(userAuthority);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        return user;
    }
}
