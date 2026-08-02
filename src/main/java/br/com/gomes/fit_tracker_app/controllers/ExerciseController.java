package br.com.gomes.fit_tracker_app.controllers;

import br.com.gomes.fit_tracker_app.dtos.ExerciseInsertDTO;
import br.com.gomes.fit_tracker_app.dtos.ExerciseResponseDTO;
import br.com.gomes.fit_tracker_app.dtos.ExerciseUpdateInsertDTO;
import br.com.gomes.fit_tracker_app.dtos.ExerciseUpdateResponseDTO;
import br.com.gomes.fit_tracker_app.services.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<List<ExerciseResponseDTO>> findAll(){
        List <ExerciseResponseDTO> exerciseList = exerciseService.findAll();
        return ResponseEntity.ok().body(exerciseList);
    }

    @GetMapping(value= "/{id}")
    public ResponseEntity<ExerciseResponseDTO> findById(@PathVariable Long id){
        ExerciseResponseDTO exercise = exerciseService.findById(id);
        return ResponseEntity.ok(exercise);
    }

    @PostMapping
    public ResponseEntity<ExerciseResponseDTO> insertExercise(@RequestBody @Valid ExerciseInsertDTO exercise){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(exerciseService.insertExercise(exercise));
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ExerciseUpdateResponseDTO> updateExercise(
            @PathVariable Long id, @RequestBody @Valid ExerciseUpdateInsertDTO exerciseUpdate){
        return ResponseEntity.status(HttpStatus.OK).body(exerciseService.updateExercise(id, exerciseUpdate));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id){
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}
