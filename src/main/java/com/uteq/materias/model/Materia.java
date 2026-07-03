package com.uteq.materias.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El codigo es obligatorio")
    @Size(min = 6, max = 6, message = "El codigo debe tener exactamente 6 caracteres")
    @Column(name = "codigo", nullable = false, unique = true, length = 6)
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 5, max = 80, message = "El nombre debe tener entre 5 y 80 caracteres")
    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    @NotNull(message = "Los creditos son obligatorios")
    @Min(value = 1, message = "Los creditos deben estar entre 1 y 6")
    @Max(value = 6, message = "Los creditos deben estar entre 1 y 6")
    @Column(name = "creditos", nullable = false)
    private Integer creditos;

    @NotNull(message = "El semestre es obligatorio")
    @Min(value = 1, message = "El semestre debe estar entre 1 y 10")
    @Max(value = 10, message = "El semestre debe estar entre 1 y 10")
    @Column(name = "semestre", nullable = false)
    private Integer semestre;

    @Column(name = "activa", nullable = false)
    private boolean activa = true;

    public Materia() {}

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getCreditos() { return creditos; }
    public void setCreditos(Integer creditos) { this.creditos = creditos; }

    public Integer getSemestre() { return semestre; }
    public void setSemestre(Integer semestre) { this.semestre = semestre; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}
