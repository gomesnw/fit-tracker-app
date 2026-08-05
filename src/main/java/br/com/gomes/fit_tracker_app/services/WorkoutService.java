package br.com.gomes.fit_tracker_app.services;

import br.com.gomes.fit_tracker_app.domain.entities.Exercise;
import br.com.gomes.fit_tracker_app.domain.entities.User;
import br.com.gomes.fit_tracker_app.domain.entities.Workout;
import br.com.gomes.fit_tracker_app.domain.entities.WorkoutExercise;
import br.com.gomes.fit_tracker_app.domain.enums.WorkoutStatus;
import br.com.gomes.fit_tracker_app.dtos.WorkoutExerciseInsertDTO;
import br.com.gomes.fit_tracker_app.dtos.WorkoutInsertDTO;
import br.com.gomes.fit_tracker_app.dtos.WorkoutResponseDTO;
import br.com.gomes.fit_tracker_app.exceptions.ResourceNotFoundException;
import br.com.gomes.fit_tracker_app.repositories.ExerciseRepository;
import br.com.gomes.fit_tracker_app.repositories.UserRepository;
import br.com.gomes.fit_tracker_app.repositories.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private static final String MSG_EXERCISE_NOT_FOUND = "Exercício não encontrado com o id fornecido: %d";

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
        Workout entity = new Workout();
        User user = userRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("Usuário inexistente."));

        entity.setUser(user);

        for(WorkoutExerciseInsertDTO exerciseInsert : workout.exercises()){
            Exercise exercise = exerciseRepository.findById(exerciseInsert.exerciseId())
                    .orElseThrow(() -> new ResourceNotFoundException
                            (String.format(MSG_EXERCISE_NOT_FOUND, exerciseInsert.exerciseId())));

            WorkoutExercise workoutExercise = WorkoutExercise.builder().exercise(exercise)
                    .orderIndex(exerciseInsert.orderIndex()).notes(exerciseInsert.notes()).build();
            entity.addWorkoutExercise(workoutExercise);

            entity.setName(workout.name());
            entity.setNotes(workout.notes());
            entity.setStatus(WorkoutStatus.STARTED);
        }

        workoutRepository.save(entity);
        return new WorkoutResponseDTO(entity);
    }
}
