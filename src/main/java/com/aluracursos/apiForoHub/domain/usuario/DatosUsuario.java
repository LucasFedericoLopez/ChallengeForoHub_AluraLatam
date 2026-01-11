package com.aluracursos.apiForoHub.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DatosUsuario(
        @NotNull Long id_autor
) {
}
