package com.example.GA7220501096AA2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "registros", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un registro en la libreta")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del registro", example = "1")
    private Long id;

    @Column(name = "nombre")
    @Schema(description = "Nombre de la persona", example = "Juan Pérez")
    private String nombre;

    @Column(name = "domicilio")
    @Schema(description = "Dirección de la persona", example = "Calle Principal 123")
    private String domicilio;

    @Column(name = "telefono")
    @Schema(description = "Número de teléfono de la persona", example = "555-1234")
    private String telefono;
}