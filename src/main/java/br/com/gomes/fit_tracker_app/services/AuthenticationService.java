package br.com.gomes.fit_tracker_app.services;

import br.com.gomes.fit_tracker_app.config.TokenProvider;
import br.com.gomes.fit_tracker_app.domain.entities.Role;
import br.com.gomes.fit_tracker_app.domain.entities.User;
import br.com.gomes.fit_tracker_app.domain.enums.RoleType;
import br.com.gomes.fit_tracker_app.dtos.LoginRequestDTO;
import br.com.gomes.fit_tracker_app.dtos.RegisterRequestDTO;
import br.com.gomes.fit_tracker_app.dtos.TokenResponseDTO;
import br.com.gomes.fit_tracker_app.exceptions.BadRequestException;
import br.com.gomes.fit_tracker_app.exceptions.ResourceNotFoundException;
import br.com.gomes.fit_tracker_app.repositories.RoleRepository;
import br.com.gomes.fit_tracker_app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public void register(RegisterRequestDTO registerRequest) {
        User user = userRepository.findByEmail(registerRequest.email())
                .orElse(null);

        if (user != null) {
            throw new IllegalArgumentException("Email já está em uso");
        }

        Role role = roleRepository.findByName(RoleType.ROLE_USER.name())
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .name(RoleType.ROLE_USER.name())
                        .build()));
        userRepository.save(User.builder()
                .name(registerRequest.name())
                .email(registerRequest.email())
                .phone(registerRequest.phone())
                .password(passwordEncoder.encode(registerRequest.password()))
                .roles(Set.of(role))
                .build());
    }

    public TokenResponseDTO login(LoginRequestDTO loginRequest) throws BadRequestException {
        try{
           Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequest.email(),
                            loginRequest.password()));
           String token = tokenProvider.generateToken(authentication);

           return new TokenResponseDTO(token, expirationTime);
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Credenciais inválidas");
        } catch (Exception e) {
            throw e;
        }
    }

    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

       return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }
}

