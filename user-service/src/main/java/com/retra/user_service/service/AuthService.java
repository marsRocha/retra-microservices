package com.retra.user_service.service;

import com.retra.user_service.config.JwtService;
import com.retra.user_service.dto.AuthResponse;
import com.retra.user_service.dto.UserSetDTO;
import com.retra.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse authenticate(UserSetDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        var u = userRepository.findByEmail(request.email()).orElseThrow();
        var jwtToken = jwtService.generateToken(u);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
