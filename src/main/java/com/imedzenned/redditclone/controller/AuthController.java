package com.imedzenned.redditclone.controller;

import com.imedzenned.redditclone.dto.LoginRequest;
import com.imedzenned.redditclone.dto.RegisterRequest;
import com.imedzenned.redditclone.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration succes", HttpStatus.OK);
    }
    @GetMapping("accountVerification/{token}")
    public  ResponseEntity<String> verifyAccount (@PathVariable String token){
        authService.verityAccount(token);
        return new ResponseEntity<>("Account Acivated Succesfuly", HttpStatus.OK);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest){
        authService.login(loginRequest);
    }
}
