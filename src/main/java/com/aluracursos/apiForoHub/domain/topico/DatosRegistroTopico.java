package com.aluracursos.apiForoHub.domain.topico;

import com.aluracursos.apiForoHub.domain.curso.DatosCurso;
import com.aluracursos.apiForoHub.domain.usuario.DatosUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull @Valid DatosUsuario autor,
        @NotNull @Valid DatosCurso curso
) {
}
