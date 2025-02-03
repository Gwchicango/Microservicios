package edu.espe.asignatura.clients;

import edu.espe.asignatura.models.Estudiante;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "micro-estudiante", url = "http://localhost:8003/api/estudiantes/")
public interface EstudianteClientRest {
    @GetMapping("/{id}")
    Estudiante findById(@PathVariable Long id);
}
