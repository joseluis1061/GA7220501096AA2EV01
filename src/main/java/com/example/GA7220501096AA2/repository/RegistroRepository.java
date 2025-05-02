package com.example.GA7220501096AA2.repository;

import com.example.GA7220501096AA2.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

    // Método para buscar registros por nombre (opcional)
    List<Registro> findByNombreContainingIgnoreCase(String nombre);

    // Método para buscar registros por teléfono (opcional)
    List<Registro> findByTelefonoContaining(String telefono);
}