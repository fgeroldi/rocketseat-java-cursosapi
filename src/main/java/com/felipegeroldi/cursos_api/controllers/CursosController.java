package com.felipegeroldi.cursos_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipegeroldi.cursos_api.dto.CreateCursoDTO;
import com.felipegeroldi.cursos_api.dto.UpdateCursoDTO;
import com.felipegeroldi.cursos_api.entities.Curso;
import com.felipegeroldi.cursos_api.exceptions.CursoNotFoundException;
import com.felipegeroldi.cursos_api.useCase.CreateCursoUseCase;
import com.felipegeroldi.cursos_api.useCase.DeleteCursoUseCase;
import com.felipegeroldi.cursos_api.useCase.GetAllCursoUseCase;
import com.felipegeroldi.cursos_api.useCase.UpdateCursoStatusUseCase;
import com.felipegeroldi.cursos_api.useCase.UpdateCursoUseCase;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/cursos")
public class CursosController {
    private CreateCursoUseCase createCursoUseCase;
    private GetAllCursoUseCase getAllCursosUseCase;
    private DeleteCursoUseCase deleteCursoUseCase;
    private UpdateCursoUseCase updateCursoUseCase;
    private UpdateCursoStatusUseCase updateCursoStatusUseCase;

    

    public CursosController(CreateCursoUseCase createCursoUseCase, GetAllCursoUseCase getAllCursosUseCase,
            DeleteCursoUseCase deleteCursoUseCase, UpdateCursoUseCase updateCursoUseCase,
            UpdateCursoStatusUseCase updateCursoStatusUseCase) {
        this.createCursoUseCase = createCursoUseCase;
        this.getAllCursosUseCase = getAllCursosUseCase;
        this.deleteCursoUseCase = deleteCursoUseCase;
        this.updateCursoUseCase = updateCursoUseCase;
        this.updateCursoStatusUseCase = updateCursoStatusUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateCursoDTO createCursoDTO) {
        try {
            Curso curso = createCursoUseCase.execute(createCursoDTO);

            var resourceURI = URI.create("/cursos/" + curso.getId());
            return ResponseEntity.created(resourceURI).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<Curso> cursos = getAllCursosUseCase.execute();
            return ResponseEntity.ok().body(cursos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/{status}")
    public ResponseEntity<?> update(@PathVariable UUID id, @PathVariable String status) {
        try {
            updateCursoStatusUseCase.execute(id, status);
            return ResponseEntity.ok().build();
        } catch (CursoNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UpdateCursoDTO updateCursoDTO) {
        try {
            Curso curso = updateCursoUseCase.execute(id, updateCursoDTO);
            return ResponseEntity.ok().body(curso);
        } catch (CursoNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{cursoId}")
    public ResponseEntity<?> delete(@PathVariable UUID cursoId) {
        try {
            deleteCursoUseCase.execute(cursoId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
