package com.felipegeroldi.cursos_api.useCase;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.felipegeroldi.cursos_api.repositories.CursoRepository;

@Component
public class DeleteCursoUseCase {
    private CursoRepository repository;

    public DeleteCursoUseCase(CursoRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        repository.deleteById(id);
    }
}
