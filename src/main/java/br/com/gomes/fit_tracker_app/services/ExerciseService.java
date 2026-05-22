package br.com.gomes.fit_tracker_app.services;

import br.com.gomes.fit_tracker_app.domain.entities.Exercise;
import br.com.gomes.fit_tracker_app.dtos.ExerciseInsertDTO;
import br.com.gomes.fit_tracker_app.dtos.ExerciseResponseDTO;
import br.com.gomes.fit_tracker_app.exceptions.ResourceNotFoundException;
import br.com.gomes.fit_tracker_app.repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public List <ExerciseResponseDTO> findAll(){
        List <Exercise> exerciseList = exerciseRepository.findAll();
        return exerciseList.stream().map((ExerciseResponseDTO::new)).toList();
    }

    public ExerciseResponseDTO findById(Long id){
        Exercise entity = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id " + id));
        return new ExerciseResponseDTO(entity);
    }

    public ExerciseResponseDTO insertExercise (ExerciseInsertDTO exercise){
        Exercise entity = exercise.toEntity();
        entity = exerciseRepository.save(entity);
        return new ExerciseResponseDTO(entity);
    }

}
