package br.com.gomes.fit_tracker_app.controllers;

import br.com.gomes.fit_tracker_app.dtos.LoginRequestDTO;
import br.com.gomes.fit_tracker_app.dtos.RegisterRequestDTO;
import br.com.gomes.fit_tracker_app.dtos.TokenResponseDTO;
import br.com.gomes.fit_tracker_app.exceptions.BadRequestException;
import br.com.gomes.fit_tracker_app.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequestDTO registerRequest) {
        authenticationService.register(registerRequest);
    }

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody @Valid LoginRequestDTO loginRequest) throws BadRequestException {
        return authenticationService.login(loginRequest);
    }
}
