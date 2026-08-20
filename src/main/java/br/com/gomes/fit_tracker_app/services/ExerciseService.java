package br.com.gomes.fit_tracker_app.services;

import br.com.gomes.fit_tracker_app.domain.entities.Exercise;
import br.com.gomes.fit_tracker_app.domain.entities.User;
import br.com.gomes.fit_tracker_app.dtos.ExerciseInsertDTO;
import br.com.gomes.fit_tracker_app.dtos.ExerciseResponseDTO;
import br.com.gomes.fit_tracker_app.dtos.ExerciseUpdateDTO;
import br.com.gomes.fit_tracker_app.dtos.ExerciseUpdateResponseDTO;
import br.com.gomes.fit_tracker_app.exceptions.ResourceNotFoundException;
import br.com.gomes.fit_tracker_app.repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final AuthenticationService authenticationService;

    private static final String MSG_EXERCISE_NOT_FOUND = "Exercício não encontrado com o id fornecido: %d";

    public List <ExerciseResponseDTO> findAll(){
        List <Exercise> exerciseList = exerciseRepository.findAllByUserId(authenticationService.
                getAuthenticatedUser().getId());
        if(exerciseList.isEmpty()){
            throw new ResourceNotFoundException("Nenhum exercício encontrado para o usuário autenticado.");
        }
        return exerciseList.stream().map((ExerciseResponseDTO::new)).toList();
    }

    public ExerciseResponseDTO findById(Long id){
        Exercise entity = findByIdOrThrowsNotFoundException(id);
        return new ExerciseResponseDTO(entity);
    }

    public ExerciseResponseDTO insertExercise (ExerciseInsertDTO exercise){
        Exercise entity = new Exercise();
        User user = authenticationService.getAuthenticatedUser();

        entity.setUser(user);
        entity.setName(exercise.name());
        entity.setExerciseCategory(exercise.category());
        entity.setEquipmentType(exercise.type());
        entity.setNotes(exercise.notes());

        entity = exerciseRepository.save(entity);
        return new ExerciseResponseDTO(entity);
    }

    public ExerciseUpdateResponseDTO updateExercise (Long id, ExerciseUpdateDTO exerciseUpdate){
        Exercise existentExercise = findByIdOrThrowsNotFoundException(id);

        updateExerciseData(existentExercise, exerciseUpdate);
        exerciseRepository.save(existentExercise);

        return new ExerciseUpdateResponseDTO(existentExercise);
    }

    public void deleteExercise (Long id){
        Exercise exercise = findByIdOrThrowsNotFoundException(id);
        exerciseRepository.delete(exercise);
    }

    private void updateExerciseData (Exercise entity, ExerciseUpdateDTO exerciseUpdate){
        entity.setName(exerciseUpdate.name());
        entity.setExerciseCategory(exerciseUpdate.category());
        entity.setEquipmentType(exerciseUpdate.type());
        entity.setNotes(exerciseUpdate.notes());
    }

    protected Exercise findByIdOrThrowsNotFoundException(Long id){
        User user = authenticationService.getAuthenticatedUser();
        return exerciseRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(()-> new ResourceNotFoundException(String.format(MSG_EXERCISE_NOT_FOUND, id)));
    }
}
