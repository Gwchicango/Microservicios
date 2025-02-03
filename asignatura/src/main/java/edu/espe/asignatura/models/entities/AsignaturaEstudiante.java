package edu.espe.asignatura.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "asignaturas_estudiante")
public class AsignaturaEstudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estudiante_id")
    private Long estudianteId;

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AsignaturaEstudiante)) {
            return false;
        }
        AsignaturaEstudiante ae = (AsignaturaEstudiante) obj;
        return this.estudianteId != null && this.estudianteId.equals(ae.getEstudianteId());
    }
}