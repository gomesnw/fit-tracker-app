package br.com.gomes.fit_tracker_app.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter

@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExerciseCategory {
    CHEST("Peitoral"),
    BACK("Costas"),
    SHOULDERS("Ombro"),
    BICEPS("Bíceps"),
    TRICEPS("Tríceps"),
    LEGS("Perna"),
    CORE("Core (abdômen ou lombar)"),
    CARDIO("Cardio"),
    FULL_BODY("Full body");

    private final String description;

    public String getCode(){
        return this.name();
    }
}
