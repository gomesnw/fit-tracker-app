package br.com.gomes.fit_tracker_app.dtos;

public record WorkoutSetInsertDTO (Integer orderIndex,
                                   Integer reps,
                                   Double weight,
                                   Integer repsInReserve,
                                   String notes){
}

