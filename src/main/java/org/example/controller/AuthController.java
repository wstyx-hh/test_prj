package org.example.controller;

import org.example.dto.AuthRequest;
import org.example.dto.AuthResponse;
import org.example.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                          UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails user = (UserDetails) authentication.getPrincipal();

        String accessToken = jwtUtil.generateAccessToken(
                user.getUsername(),
                user.getAuthorities().stream().map(a -> a.getAuthority()).toList()
        );
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        return new AuthResponse(accessToken, refreshToken);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestParam String refreshToken) {
        if (jwtUtil.validate(refreshToken)) {
            String username = jwtUtil.getUsername(refreshToken);

            UserDetails user = userDetailsService.loadUserByUsername(username);

            String newAccess = jwtUtil.generateAccessToken(
                    user.getUsername(),
                    user.getAuthorities().stream().map(a -> a.getAuthority()).toList()
            );
            String newRefresh = jwtUtil.generateRefreshToken(username);

            return new AuthResponse(newAccess, newRefresh);
        }
        throw new RuntimeException("Invalid refresh token");
    }



}
