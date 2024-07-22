package com.felipegeroldi.cursos_api.useCase;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.felipegeroldi.cursos_api.dto.UpdateCursoDTO;
import com.felipegeroldi.cursos_api.entities.Curso;
import com.felipegeroldi.cursos_api.entities.Status;
import com.felipegeroldi.cursos_api.exceptions.CursoNotFoundException;
import com.felipegeroldi.cursos_api.repositories.CursoRepository;

@Component
public class UpdateCursoUseCase {
    private CursoRepository repository;

    public UpdateCursoUseCase(CursoRepository repository) {
        this.repository = repository;
    }

    public Curso execute(UUID id, UpdateCursoDTO dto) {
        Curso curso = repository.findById(id)
            .orElseThrow(() -> new CursoNotFoundException());

        curso.setName(dto.getName());
        curso.setCategory(dto.getCategory());
        curso.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));

        curso = repository.save(curso);
        return curso;
    }
}
