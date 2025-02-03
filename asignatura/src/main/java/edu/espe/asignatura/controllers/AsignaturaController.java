package edu.espe.asignatura.controllers;

import edu.espe.asignatura.models.Estudiante;
import edu.espe.asignatura.models.entities.Asignatura;
import edu.espe.asignatura.services.AsignaturaService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService service;

    // Método POST para crear una nueva asignatura
    @PostMapping("/add")
    public ResponseEntity<?> crear(@RequestBody Asignatura asignatura){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(asignatura));
    }

    // Método GET para listar todas las asignaturas
    @GetMapping
    public List<Asignatura> listar() {
        return service.findAll();
    }

    // Método GET para obtener una asignatura por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Optional<Asignatura> asignaturaOptional = service.findById(id);
        if (asignaturaOptional.isPresent()){
            return ResponseEntity.ok().body(asignaturaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Método PUT para editar una asignatura existente
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Asignatura asignatura, @PathVariable Long id){
        Optional<Asignatura> asignaturaOptional = service.findById(id);
        if (asignaturaOptional.isPresent()){
            Asignatura asignaturaDB = asignaturaOptional.get();
            asignaturaDB.setNombre(asignatura.getNombre());
            asignaturaDB.setDescripcion(asignatura.getDescripcion());
            asignaturaDB.setCreditos(asignatura.getCreditos());
            return ResponseEntity.ok().body(service.save(asignaturaDB));
        }
        return ResponseEntity.notFound().build();
    }

    // Método DELETE para eliminar una asignatura por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Agregar un estudiante a una asignatura
    @PostMapping("agregar_estudiante/{id}")
    public ResponseEntity<?> agregarEstudiante(@RequestBody Estudiante estudiante, @PathVariable Long id) {
        Optional<Estudiante> optional;
        try {
            optional = service.addUser(estudiante, id);
        } catch (FeignException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Estudiante no encontrado: " + ex.getMessage()));
        }
        if (optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar un estudiante de una asignatura
    @DeleteMapping("eliminar_estudiante/{asignaturaId}/{estudianteId}")
    public ResponseEntity<?> eliminarEstudiante(@PathVariable Long asignaturaId, @PathVariable Long estudianteId) {
        try {
            service.removeUser(asignaturaId, estudianteId);
            return ResponseEntity.noContent().build();
        } catch (FeignException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Estudiante o asignatura no encontrado: " + ex.getMessage()));
        }
    }
}