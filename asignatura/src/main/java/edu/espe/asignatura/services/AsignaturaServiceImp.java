package edu.espe.asignatura.services;

import edu.espe.asignatura.clients.EstudianteClientRest;
import edu.espe.asignatura.models.Estudiante;
import edu.espe.asignatura.models.entities.Asignatura;
import edu.espe.asignatura.models.entities.AsignaturaEstudiante;
import edu.espe.asignatura.repositories.AsignaturaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaServiceImp implements AsignaturaService {

    @Autowired
    private AsignaturaRepositories repository;

    @Autowired
    private EstudianteClientRest clientRest;

    @Override
    public List<Asignatura> findAll() {
        return (List<Asignatura>) repository.findAll();
    }

    @Override
    public Optional<Asignatura> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Asignatura save(Asignatura asignatura) {
        return repository.save(asignatura);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void removeUser(Long id, Long idUsuario) {
        Optional<Asignatura> optional = repository.findById(id);
        if (optional.isPresent()) {
            Asignatura curso = optional.get();
            Optional<AsignaturaEstudiante> cursoUsuarioOptional = curso.getAsignaturaUsuarios().stream()
                    .filter(cursoUsuario -> cursoUsuario.getEstudianteId().equals(idUsuario))
                    .findFirst();
            if (cursoUsuarioOptional.isPresent()) {
                curso.removeAsignaturaUsuario(cursoUsuarioOptional.get());
                repository.save(curso);
            }
        }
    }

    @Override
    public Optional<Estudiante> addUser(Estudiante estudiante, Long id) {
        //Optional: es un contenedor que puede o no contener un valor no nulo
        // Creo un objeto de tipo Optional y le paso el valor que me devuelve el metodo findById Curso
        Optional<Asignatura> optional = repository.findById(id);
        //Verifico si el objeto optional tiene un valor
        if (optional.isPresent()) {
            // Si el objeto optional tiene un valor, obtengo el valor y lo guardo en la variable curso
            Estudiante usaurioTemp = clientRest.findById(estudiante.getId());
            //Obtengo el valor del optional
            Asignatura curso = optional.get();
            //Creo un objeto de tipo CursoUsuario
            AsignaturaEstudiante cursoUsuario = new AsignaturaEstudiante();
            //Seteo el id del usuario
            cursoUsuario.setEstudianteId(usaurioTemp.getId());

            //Añado el cursoUsuario al curso
            curso.addAsignaturaUsuario(cursoUsuario);
            //Guardo el curso
            repository.save(curso);
            //Retorno el usuario
            return Optional.of(usaurioTemp);
        }
        //Retorno un valor vacio
        return Optional.empty();
    }
}