package br.com.gomes.fit_tracker_app.services;

import br.com.gomes.fit_tracker_app.domain.entities.User;
import br.com.gomes.fit_tracker_app.dtos.UserInsertDTO;
import br.com.gomes.fit_tracker_app.dtos.UserResponseDTO;
import br.com.gomes.fit_tracker_app.exceptions.ResourceNotFoundException;
import br.com.gomes.fit_tracker_app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List <UserResponseDTO> findALl(){
        List <User> usersList = userRepository.findAll();
        return usersList.stream().map(UserResponseDTO::new).toList();
    }

    public UserResponseDTO findById (Long id){
        User entity = userRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("User not found with id " + id));
        return new UserResponseDTO(entity);
    }

    public UserResponseDTO insertUser (UserInsertDTO userInsert){
        User entity = userInsert.toEntity();
        entity = userRepository.save(entity);
        return new UserResponseDTO(entity);
    }
}


