package br.com.gomes.fit_tracker_app.controllers;

import br.com.gomes.fit_tracker_app.dtos.UserInsertDTO;
import br.com.gomes.fit_tracker_app.dtos.UserResponseDTO;
import br.com.gomes.fit_tracker_app.dtos.UserUpdateDTO;
import br.com.gomes.fit_tracker_app.dtos.UserUpdateResponseDTO;
import br.com.gomes.fit_tracker_app.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(){
       List<UserResponseDTO> usersList = userService.findALl();
       return ResponseEntity.ok().body(usersList);
    }

    @GetMapping(value= "/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        UserResponseDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> insertUser (@RequestBody @Valid UserInsertDTO userInsert){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.insertUser(userInsert));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserUpdateResponseDTO> updateUser (@PathVariable Long id, @RequestBody @Valid UserUpdateDTO userUpdate){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateUser(id, userUpdate));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
