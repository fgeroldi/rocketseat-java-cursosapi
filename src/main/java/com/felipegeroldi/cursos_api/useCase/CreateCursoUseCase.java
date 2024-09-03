package com.felipegeroldi.cursos_api.useCase;

import org.springframework.stereotype.Component;

import com.felipegeroldi.cursos_api.dto.CreateCursoDTO;
import com.felipegeroldi.cursos_api.entities.Curso;
import com.felipegeroldi.cursos_api.entities.Status;
import com.felipegeroldi.cursos_api.repositories.CursoRepository;

@Component
public class CreateCursoUseCase {
    private CursoRepository repository;

    public CreateCursoUseCase(CursoRepository repository) {
        this.repository = repository;
    }

    public Curso execute(CreateCursoDTO dto) {
        var curso = Curso.builder()
            .name(dto.getName())
            .category(dto.getCategory())
            .professor(dto.getProfessor())
            .status(Status.valueOf(dto.getStatus().toUpperCase()))
            .build();

        curso = repository.save(curso);

        return curso;
    }
}
