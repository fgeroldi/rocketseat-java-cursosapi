package com.felipegeroldi.cursos_api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCursoDTO {
    @NotEmpty
    private String name;

    @NotEmpty
    private String category;

    @NotEmpty
    private String status;
}
