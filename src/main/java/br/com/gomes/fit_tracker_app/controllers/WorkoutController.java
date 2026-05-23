package br.com.gomes.fit_tracker_app.controllers;

import br.com.gomes.fit_tracker_app.dtos.WorkoutInsertDTO;
import br.com.gomes.fit_tracker_app.dtos.WorkoutResponseDTO;
import br.com.gomes.fit_tracker_app.services.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<List<WorkoutResponseDTO>> findAll(){
        List<WorkoutResponseDTO> workoutList = workoutService.findAll();
        return ResponseEntity.ok().body(workoutList);
    }

    @GetMapping(value= "/{id}")
    public ResponseEntity<WorkoutResponseDTO> findById(@PathVariable Long id){
        WorkoutResponseDTO workout = workoutService.findById(id);
        return ResponseEntity.ok(workout);
    }

    @PostMapping
    public ResponseEntity<WorkoutResponseDTO> insertWorkout(@RequestBody @Valid WorkoutInsertDTO workoutInsert){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(workoutService.insertWorkout(workoutInsert));
    }

}
