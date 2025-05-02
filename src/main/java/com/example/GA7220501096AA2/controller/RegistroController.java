package com.example.GA7220501096AA2.controller;

import com.example.GA7220501096AA2.dto.RegistroDTO;
import com.example.GA7220501096AA2.model.Registro;
import com.example.GA7220501096AA2.service.RegistroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registros")
@Tag(name = "Registro", description = "API para gestionar registros de la libreta")
public class RegistroController {

    private final RegistroService registroService;

    @Autowired
    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @Operation(summary = "Obtener todos los registros", description = "Devuelve la lista de todos los registros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registros encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Registro.class)))
    })
    @GetMapping
    public ResponseEntity<List<Registro>> getAllRegistros() {
        List<Registro> registros = registroService.getAllRegistros();
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un registro por ID", description = "Devuelve un registro según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Registro.class))),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Registro> getRegistroById(@PathVariable Long id) {
        Optional<Registro> registro = registroService.getRegistroById(id);
        return registro.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Crear un nuevo registro", description = "Crea un nuevo registro y devuelve el registro creado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Registro.class)))
    })
    @PostMapping
    public ResponseEntity<Registro> createRegistro(@RequestBody RegistroDTO registroDTO) {
        Registro registro = new Registro();
        registro.setNombre(registroDTO.getNombre());
        registro.setDomicilio(registroDTO.getDomicilio());
        registro.setTelefono(registroDTO.getTelefono());

        Registro nuevoRegistro = registroService.saveRegistro(registro);
        return new ResponseEntity<>(nuevoRegistro, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un registro existente", description = "Actualiza un registro según su ID y devuelve el registro actualizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Registro.class))),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Registro> updateRegistro(@PathVariable Long id, @RequestBody RegistroDTO registroDTO) {
        try {
            // Primero verificamos si el registro existe
            Optional<Registro> optionalRegistro = registroService.getRegistroById(id);

            if (optionalRegistro.isPresent()) {
                // Actualizamos solo los campos proporcionados en el DTO
                Registro existingRegistro = optionalRegistro.get();
                existingRegistro.setNombre(registroDTO.getNombre());
                existingRegistro.setDomicilio(registroDTO.getDomicilio());
                existingRegistro.setTelefono(registroDTO.getTelefono());

                Registro updatedRegistro = registroService.saveRegistro(existingRegistro);
                return new ResponseEntity<>(updatedRegistro, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar un registro", description = "Elimina un registro según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro eliminado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRegistro(@PathVariable Long id) {
        try {
            registroService.deleteRegistro(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar registros por nombre", description = "Devuelve la lista de registros que contienen el nombre especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registros encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Registro.class))),
            @ApiResponse(responseCode = "204", description = "No se encontraron registros",
                    content = @Content)
    })
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<List<Registro>> getRegistrosByNombre(@PathVariable String nombre) {
        List<Registro> registros = registroService.findByNombre(nombre);
        if (registros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Operation(summary = "Buscar registros por teléfono", description = "Devuelve la lista de registros que contienen el teléfono especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registros encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Registro.class))),
            @ApiResponse(responseCode = "204", description = "No se encontraron registros",
                    content = @Content)
    })
    @GetMapping("/buscar/telefono/{telefono}")
    public ResponseEntity<List<Registro>> getRegistrosByTelefono(@PathVariable String telefono) {
        List<Registro> registros = registroService.findByTelefono(telefono);
        if (registros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }
}