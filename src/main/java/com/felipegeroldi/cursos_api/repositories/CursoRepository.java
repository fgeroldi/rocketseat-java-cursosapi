package com.felipegeroldi.cursos_api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipegeroldi.cursos_api.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, UUID> {
    
}
