package br.com.gomes.fit_tracker_app.services;

import br.com.gomes.fit_tracker_app.domain.entities.Exercise;
import br.com.gomes.fit_tracker_app.dtos.ExerciseInsertDTO;
import br.com.gomes.fit_tracker_app.dtos.ExerciseResponseDTO;
import br.com.gomes.fit_tracker_app.dtos.ExerciseUpdateInsertDTO;
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
    private static final String MSG_EXERCISE_NOT_FOUND = "Exercício não encontrado com o id fornecido: %d";

    public List <ExerciseResponseDTO> findAll(){
        List <Exercise> exerciseList = exerciseRepository.findAll();
        return exerciseList.stream().map((ExerciseResponseDTO::new)).toList();
    }

    public ExerciseResponseDTO findById(Long id){
        Exercise entity = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_EXERCISE_NOT_FOUND, id)));
        return new ExerciseResponseDTO(entity);
    }

    public ExerciseResponseDTO insertExercise (ExerciseInsertDTO exercise){
        Exercise entity = exercise.toEntity();
        entity = exerciseRepository.save(entity);
        return new ExerciseResponseDTO(entity);
    }

    public ExerciseUpdateResponseDTO updateExercise (Long id, ExerciseUpdateInsertDTO exerciseUpdate){
        Exercise existentExercise = exerciseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(String.format(MSG_EXERCISE_NOT_FOUND, id)));

        updateExerciseData(existentExercise, exerciseUpdate);
        exerciseRepository.save(existentExercise);

        return new ExerciseUpdateResponseDTO(existentExercise);
    }

    public void deleteExercise (Long id){
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(String.format(MSG_EXERCISE_NOT_FOUND, id)));

        exerciseRepository.delete(exercise);
    }

    private void updateExerciseData (Exercise entity, ExerciseUpdateInsertDTO exerciseUpdate){
        entity.setName(exerciseUpdate.name());
        entity.setExerciseCategory(exerciseUpdate.category());
        entity.setEquipmentType(exerciseUpdate.type());
        entity.setNotes(exerciseUpdate.notes());
    }
}
