package br.com.gomes.fit_tracker_app.services;

import br.com.gomes.fit_tracker_app.domain.entities.User;
import br.com.gomes.fit_tracker_app.dtos.UserResponseDTO;
import br.com.gomes.fit_tracker_app.dtos.UserUpdateDTO;
import br.com.gomes.fit_tracker_app.dtos.UserUpdateResponseDTO;
import br.com.gomes.fit_tracker_app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public UserResponseDTO findMe(){
        User entity = authenticationService.getAuthenticatedUser();
        return new UserResponseDTO(entity);
    }

    public UserUpdateResponseDTO updateMe (UserUpdateDTO userUpdate){
        User existentUser = authenticationService.getAuthenticatedUser();

        updateUserData(existentUser, userUpdate);
        existentUser = userRepository.save(existentUser);

        return new UserUpdateResponseDTO(existentUser);
    }

    private void updateUserData(User entity, UserUpdateDTO updateInsertDTO) {
        entity.setName(updateInsertDTO.name());
    }
}


