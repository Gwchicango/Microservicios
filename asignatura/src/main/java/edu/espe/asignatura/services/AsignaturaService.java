package edu.espe.asignatura.services;

import edu.espe.asignatura.models.Estudiante;
import edu.espe.asignatura.models.entities.Asignatura;

import java.util.List;
import java.util.Optional;


public interface AsignaturaService {
    List<Asignatura> findAll();
    Optional<Asignatura> findById(Long id);
    Asignatura save(Asignatura asignatura);
    void deleteById(Long id);
    void removeUser(Long id, Long idUsuario);
    Optional<Estudiante> addUser(Estudiante estudiante, Long id);
}
