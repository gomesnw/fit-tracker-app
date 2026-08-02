package br.com.gomes.fit_tracker_app.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter

@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EquipmentType {
    CABLE("Polia"),
    DUMBBELL("Halteres"),
    MACHINE("Máquina"),
    BARBELL("Barra"),
    OTHER("Outro"),
    BODYWEIGHT("Peso corporal");

    private final String description;

    public String getCode(){
        return this.name();
    }
}
