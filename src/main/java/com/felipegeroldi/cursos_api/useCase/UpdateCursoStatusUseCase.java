package com.felipegeroldi.cursos_api.useCase;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.felipegeroldi.cursos_api.entities.Curso;
import com.felipegeroldi.cursos_api.entities.Status;
import com.felipegeroldi.cursos_api.exceptions.CursoNotFoundException;
import com.felipegeroldi.cursos_api.repositories.CursoRepository;

@Component
public class UpdateCursoStatusUseCase {
    private CursoRepository repository;

    public UpdateCursoStatusUseCase(CursoRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id, String status) {
        Curso curso = repository.findById(id)
            .orElseThrow(() -> new CursoNotFoundException());

        curso.setStatus(Status.valueOf(status.toUpperCase()));
        curso = repository.save(curso);
    }
}
