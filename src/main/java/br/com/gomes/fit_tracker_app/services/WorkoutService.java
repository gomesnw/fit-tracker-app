package br.com.gomes.fit_tracker_app.services;

import br.com.gomes.fit_tracker_app.domain.entities.Exercise;
import br.com.gomes.fit_tracker_app.domain.entities.User;
import br.com.gomes.fit_tracker_app.domain.entities.Workout;
import br.com.gomes.fit_tracker_app.domain.entities.WorkoutExercise;
import br.com.gomes.fit_tracker_app.domain.enums.WorkoutStatus;
import br.com.gomes.fit_tracker_app.dtos.*;
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
    private static final String MSG_WORKOUT_NOT_FOUND = "Treino não encontrado com o id fornecido: %d";

    public List<WorkoutResponseDTO> findAll(){
        List<Workout> workoutList = workoutRepository.findAll();
        return workoutList.stream().map((WorkoutResponseDTO::new)).toList();
    }

    public WorkoutResponseDTO findById(Long id){
        Workout entity = workoutRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException(String.format(MSG_WORKOUT_NOT_FOUND, id)));
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

    public WorkoutUpdateResponseDTO updateWorkout (Long id, WorkoutUpdateDTO workoutUpdate){
        Workout existentWorkout = workoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_WORKOUT_NOT_FOUND, id)));

        updateWorkoutData(existentWorkout, workoutUpdate);
        workoutRepository.save(existentWorkout);
        return new WorkoutUpdateResponseDTO(existentWorkout);
    }

    private void updateWorkoutData(Workout entity, WorkoutUpdateDTO workoutUpdate){
        entity.setName(workoutUpdate.name());
        entity.setNotes(workoutUpdate.notes());
    }

    public WorkoutUpdateResponseDTO updateWorkoutExercise(Long id, Integer orderIndex, WorkoutExerciseUpdateDTO workoutExerciseUpdate){
        Workout existentWorkout = workoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_WORKOUT_NOT_FOUND, id)));

        Exercise exercise = exerciseRepository.findById(workoutExerciseUpdate.exerciseId())
                .orElseThrow(() -> new ResourceNotFoundException
                        (String.format(MSG_EXERCISE_NOT_FOUND, workoutExerciseUpdate.exerciseId())));

        WorkoutExercise workoutExercise = existentWorkout.findWorkoutExerciseByOrderIndex(orderIndex);
        workoutExercise.setExercise(exercise);
        workoutExercise.setNotes(workoutExerciseUpdate.notes());

        workoutRepository.save(existentWorkout);
        return new WorkoutUpdateResponseDTO(existentWorkout);
    }

    public void deleteWorkout(Long id){
        Workout existentWorkout = workoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_WORKOUT_NOT_FOUND, id)));

        workoutRepository.delete(existentWorkout);
    }
}
