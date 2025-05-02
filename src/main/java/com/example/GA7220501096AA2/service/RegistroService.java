package com.example.GA7220501096AA2.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.GA7220501096AA2.repository.RegistroRepository;
import org.springframework.stereotype.Service;

import com.example.GA7220501096AA2.model.Registro;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroService {

    private final RegistroRepository registroRepository;

    @Autowired
    public RegistroService(RegistroRepository registroRepository) {
        this.registroRepository = registroRepository;
    }

    // Obtener todos los registros
    public List<Registro> getAllRegistros() {
        return registroRepository.findAll();
    }

    // Obtener un registro por ID
    public Optional<Registro> getRegistroById(Long id) {
        return registroRepository.findById(id);
    }

    // Guardar un nuevo registro
    public Registro saveRegistro(Registro registro) {
        return registroRepository.save(registro);
    }

    // Actualizar un registro existente
    public Registro updateRegistro(Long id, Registro registroDetails) {
        Optional<Registro> optionalRegistro = registroRepository.findById(id);

        if (optionalRegistro.isPresent()) {
            Registro existingRegistro = optionalRegistro.get();
            existingRegistro.setNombre(registroDetails.getNombre());
            existingRegistro.setDomicilio(registroDetails.getDomicilio());
            existingRegistro.setTelefono(registroDetails.getTelefono());
            return registroRepository.save(existingRegistro);
        } else {
            throw new RuntimeException("Registro no encontrado con id: " + id);
        }
    }

    // Eliminar un registro
    public void deleteRegistro(Long id) {
        registroRepository.deleteById(id);
    }

    // Buscar registros por nombre (opcional)
    public List<Registro> findByNombre(String nombre) {
        return registroRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Buscar registros por teléfono (opcional)
    public List<Registro> findByTelefono(String telefono) {
        return registroRepository.findByTelefonoContaining(telefono);
    }
}