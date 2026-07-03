package com.uteq.materias.repository;

import com.uteq.materias.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Unico lugar donde se generan consultas contra la tabla "materias".
// Spring Data JPA construye internamente sentencias preparadas (PreparedStatement),
// por lo que nunca se concatenan datos del usuario al SQL.
public interface MateriaRepository extends JpaRepository<Materia, Long> {

    List<Materia> findByActivaTrueOrderByCodigoAsc();

    Optional<Materia> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
