package br.com.gomes.fit_tracker_app.services;

import br.com.gomes.fit_tracker_app.domain.entities.*;
import br.com.gomes.fit_tracker_app.domain.enums.WorkoutStatus;
import br.com.gomes.fit_tracker_app.dtos.*;
import br.com.gomes.fit_tracker_app.exceptions.ResourceNotFoundException;
import br.com.gomes.fit_tracker_app.repositories.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final AuthenticationService authenticationService;
    private static final String MSG_WORKOUT_NOT_FOUND = "Treino não encontrado com o id fornecido: %d";

    private final ExerciseService exerciseService;

    public List<WorkoutResponseDTO> findAll(){
        List<Workout> workoutList = workoutRepository.findAllByUserId(authenticationService.
                getAuthenticatedUser().getId());
        if(workoutList.isEmpty()){
            throw new ResourceNotFoundException("Nenhum treino encontrado para o usuário autenticado.");
        }
        return workoutList.stream().map((WorkoutResponseDTO::new)).toList();
    }

    public WorkoutResponseDTO findById(Long id){
        Workout entity = findByIdOrThrowsNotFoundException(id);
        return new WorkoutResponseDTO(entity);
    }

    public WorkoutResponseDTO insertWorkout(WorkoutInsertDTO workout){
        Workout entity = new Workout();
        User user = authenticationService.getAuthenticatedUser();

        entity.setUser(user);

        for(WorkoutExerciseInsertDTO exerciseInsert : workout.exercises()){
            Exercise exercise = exerciseService.findByIdOrThrowsNotFoundException(exerciseInsert.exerciseId());

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
        Workout existentWorkout = findByIdOrThrowsNotFoundException(id);

        updateWorkoutData(existentWorkout, workoutUpdate);
        workoutRepository.save(existentWorkout);
        return new WorkoutUpdateResponseDTO(existentWorkout);
    }

    private void updateWorkoutData(Workout entity, WorkoutUpdateDTO workoutUpdate){
        entity.setName(workoutUpdate.name());
        entity.setNotes(workoutUpdate.notes());
    }

    public WorkoutUpdateResponseDTO updateWorkoutExercise(Long id, Integer orderIndex, WorkoutExerciseUpdateDTO workoutExerciseUpdate){
        Workout existentWorkout = findByIdOrThrowsNotFoundException(id);

        Exercise exercise = exerciseService.findByIdOrThrowsNotFoundException(workoutExerciseUpdate.exerciseId());

        WorkoutExercise workoutExercise = existentWorkout.findWorkoutExerciseByOrderIndex(orderIndex);
        workoutExercise.setExercise(exercise);
        workoutExercise.setNotes(workoutExerciseUpdate.notes());

        workoutRepository.save(existentWorkout);
        return new WorkoutUpdateResponseDTO(existentWorkout);
    }

    public void deleteWorkout(Long id){
        Workout existentWorkout = findByIdOrThrowsNotFoundException(id);

        workoutRepository.delete(existentWorkout);
    }

    @Transactional
    public WorkoutExerciseSetResponseDTO insertWorkoutSet(Long id, Integer orderIndex, WorkoutSetInsertDTO setInsert) {
        Workout existentWorkout = findByIdOrThrowsNotFoundException(id);

        WorkoutExercise workoutExercise = existentWorkout.findWorkoutExerciseByOrderIndex(orderIndex);

        WorkoutSet set = WorkoutSet.builder()
                .orderIndex(setInsert.orderIndex())
                .reps(setInsert.reps())
                .weight(setInsert.weight())
                .repsInReserve(setInsert.repsInReserve())
                .notes(setInsert.notes())
                .workoutExercise(workoutExercise)
                .build();

        workoutExercise.addSet(set);

        workoutRepository.flush();
        return new WorkoutExerciseSetResponseDTO(set);
    }

    private Workout findByIdOrThrowsNotFoundException(Long id){
        Long userId = authenticationService.getAuthenticatedUser().getId();
        return workoutRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_WORKOUT_NOT_FOUND, id)));
    }
}
