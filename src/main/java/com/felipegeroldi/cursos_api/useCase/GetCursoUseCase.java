package com.felipegeroldi.cursos_api.useCase;

import com.felipegeroldi.cursos_api.entities.Curso;
import com.felipegeroldi.cursos_api.exceptions.CursoNotFoundException;
import com.felipegeroldi.cursos_api.repositories.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetCursoUseCase {
    private CursoRepository cursoRepository;

    public GetCursoUseCase(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso execute(UUID cursoId) {
        return cursoRepository.findById(cursoId)
                .orElseThrow(CursoNotFoundException::new);
    }
}
