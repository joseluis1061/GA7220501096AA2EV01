package com.example.GA7220501096AA2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear o actualizar un registro")
public class RegistroDTO {

    @Schema(description = "Nombre de la persona", example = "Juan Pérez", required = true)
    private String nombre;

    @Schema(description = "Dirección de la persona", example = "Calle Principal 123", required = true)
    private String domicilio;

    @Schema(description = "Número de teléfono de la persona", example = "555-1234", required = true)
    private String telefono;
}