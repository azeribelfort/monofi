package com.monofi.controller;

import com.monofi.dto.LoginDto;
import com.monofi.dto.UserRequestDto;
import com.monofi.model.Authority;
import com.monofi.model.User;
import com.monofi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/authorities")
    public ResponseEntity<Set<Authority>> getUserAuthorities(@RequestBody UserRequestDto userRequestDto){
        User user = userService.findByUsername(userRequestDto.getUsername());
        Set<Authority> authorities = user.getAuthorities();
        return new ResponseEntity<>(authorities, HttpStatus.OK);
    }

    @GetMapping("/credentials")
    public ResponseEntity<User> getUserByUsername(@RequestBody UserRequestDto userRequestDto){
        User user = userService.findByUsername(userRequestDto.getUsername());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
