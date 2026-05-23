package br.com.gomes.fit_tracker_app.services;

import br.com.gomes.fit_tracker_app.domain.entities.Workout;
import br.com.gomes.fit_tracker_app.dtos.WorkoutInsertDTO;
import br.com.gomes.fit_tracker_app.dtos.WorkoutResponseDTO;
import br.com.gomes.fit_tracker_app.exceptions.ResourceNotFoundException;
import br.com.gomes.fit_tracker_app.repositories.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    public List<WorkoutResponseDTO> findAll(){
        List<Workout> workoutList = workoutRepository.findAll();
        return workoutList.stream().map((WorkoutResponseDTO::new)).toList();
    }

    public WorkoutResponseDTO findById(Long id){
        Workout entity = workoutRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Workout not found with id " + id));
        return new WorkoutResponseDTO(entity);
    }

    public WorkoutResponseDTO insertWorkout(WorkoutInsertDTO workout){
        Workout entity = workout.toEntity();
        entity = workoutRepository.save(entity);
        return new WorkoutResponseDTO(entity);
    }


}
