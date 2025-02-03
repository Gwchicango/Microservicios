package edu.espe.asignatura.models.entities;


import edu.espe.asignatura.models.Estudiante;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Asignatura")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int creditos;

    // Relación uno a muchos
    // Una asignatura puede tener muchos usuarios
    // Un usuario puede tener muchas asignaturas
    // Se crea una lista de AsignaturaUsuario
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    // Se crea una columna asignatura_id
    // Sirve para relacionar la tabla asignaturas con la tabla asignaturas_usuario
    @JoinColumn(name = "asignatura_id")
    private List<AsignaturaEstudiante> asignaturaUsuarios;

    // Se crea una lista de usuarios
    // Transient significa que no se va a guardar en la base de datos
    @Transient
    private List<Estudiante> usuarios;

    // Constructor
    public Asignatura() {
        asignaturaUsuarios = new ArrayList<>();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public List<AsignaturaEstudiante> getAsignaturaUsuarios() {
        return asignaturaUsuarios;
    }

    public void setAsignaturaUsuarios(List<AsignaturaEstudiante> asignaturaUsuarios) {
        this.asignaturaUsuarios = asignaturaUsuarios;
    }

    public void addAsignaturaUsuario(AsignaturaEstudiante asignaturaUsuario) {
        asignaturaUsuarios.add(asignaturaUsuario);
    }

    public void removeAsignaturaUsuario(AsignaturaEstudiante asignaturaUsuario) {
        asignaturaUsuarios.remove(asignaturaUsuario);
    }
}