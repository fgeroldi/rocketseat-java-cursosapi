package com.felipegeroldi.cursos_api.useCase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.felipegeroldi.cursos_api.entities.Curso;
import com.felipegeroldi.cursos_api.repositories.CursoRepository;

@Component
public class GetAllCursoUseCase {
    private CursoRepository repository;

    public GetAllCursoUseCase(CursoRepository repository) {
        this.repository = repository;
    }

    public List<Curso> execute() {
        return repository.findAll();
    }
}
