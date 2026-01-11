package com.aluracursos.apiForoHub.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DatosCurso(
        @NotNull Long id_curso
        ) {
}
