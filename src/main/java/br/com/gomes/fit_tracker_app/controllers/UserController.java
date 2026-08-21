package br.com.gomes.fit_tracker_app.controllers;

import br.com.gomes.fit_tracker_app.dtos.UserResponseDTO;
import br.com.gomes.fit_tracker_app.dtos.UserUpdateDTO;
import br.com.gomes.fit_tracker_app.dtos.UserUpdateResponseDTO;
import br.com.gomes.fit_tracker_app.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/v1/users")
public class UserController {
    private final UserService userService;

    @PutMapping
    public ResponseEntity<UserUpdateResponseDTO> updateMe(@RequestBody @Valid UserUpdateDTO userUpdate) {
        return ResponseEntity.ok()
                .body(userService.updateMe(userUpdate));
    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> findMe(){
        return ResponseEntity.ok().
                body(userService.findMe());
    }
}