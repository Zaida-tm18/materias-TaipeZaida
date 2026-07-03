package com.uteq.materias.service;

import com.uteq.materias.model.Materia;
import com.uteq.materias.repository.MateriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    private final MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public List<Materia> listarActivas() {
        return materiaRepository.findByActivaTrueOrderByCodigoAsc();
    }

    public Optional<Materia> buscarPorId(Long id) {
        return materiaRepository.findById(id);
    }

    public boolean codigoDuplicado(String codigo, Long idActual) {
        if (idActual == null) {
            return materiaRepository.existsByCodigo(codigo);
        }
        return materiaRepository.existsByCodigoAndIdNot(codigo, idActual);
    }

    public Materia crear(Materia materia) {
        materia.setId(null);
        materia.setActiva(true);
        return materiaRepository.save(materia);
    }

    public Materia actualizar(Long id, Materia datos) {
        Materia existente = materiaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada: " + id));
        existente.setCodigo(datos.getCodigo());
        existente.setNombre(datos.getNombre());
        existente.setCreditos(datos.getCreditos());
        existente.setSemestre(datos.getSemestre());
        return materiaRepository.save(existente);
    }

    // Eliminacion logica: nunca se borra fisicamente el registro.
    public void eliminarLogicamente(Long id) {
        Materia existente = materiaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada: " + id));
        existente.setActiva(false);
        materiaRepository.save(existente);
    }
}
