package br.com.gomes.fit_tracker_app.services;

import br.com.gomes.fit_tracker_app.domain.entities.User;
import br.com.gomes.fit_tracker_app.dtos.UserInsertDTO;
import br.com.gomes.fit_tracker_app.dtos.UserResponseDTO;
import br.com.gomes.fit_tracker_app.dtos.UserUpdateDTO;
import br.com.gomes.fit_tracker_app.dtos.UserUpdateResponseDTO;
import br.com.gomes.fit_tracker_app.exceptions.ResourceNotFoundException;
import br.com.gomes.fit_tracker_app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private static final String MSG_USER_NOT_FOUND = "Usuário não encontrado com o id fornecido: %d";

    public List <UserResponseDTO> findALl(){
        List <User> usersList = userRepository.findAll();
        return usersList.stream().map(UserResponseDTO::new).toList();
    }

    public UserResponseDTO findById (Long id){
        User entity = userRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException(String.format(MSG_USER_NOT_FOUND, id)));
        return new UserResponseDTO(entity);
    }

    public UserResponseDTO insertUser (UserInsertDTO userInsert){
        User entity = userInsert.toEntity();
        entity = userRepository.save(entity);
        return new UserResponseDTO(entity);
    }

    public UserUpdateResponseDTO updateUser (Long id, UserUpdateDTO userUpdate){
        User existentUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_USER_NOT_FOUND, id)));
        updateUserData(existentUser, userUpdate);
        existentUser = userRepository.save(existentUser);

        return new UserUpdateResponseDTO(existentUser);
    }

    public void deleteUser (Long id){
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_USER_NOT_FOUND, id)));

        userRepository.delete(entity);
    }

    private void updateUserData(User entity, UserUpdateDTO updateInsertDTO) {
        entity.setName(updateInsertDTO.name());
        entity.setEmail(updateInsertDTO.email());
        entity.setPhone(updateInsertDTO.phone());
    }
}


