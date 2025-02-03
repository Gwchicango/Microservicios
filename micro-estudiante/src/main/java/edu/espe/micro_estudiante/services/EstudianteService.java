package edu.espe.micro_estudiante.services;


import edu.espe.micro_estudiante.model.entities.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {

    List<Estudiante> findAll();
    Optional<Estudiante> findById(Long id);
    Estudiante save(Estudiante estudiante);
    void edit(Estudiante estudiante, Long id);
    void deleteById(Long id);

}
