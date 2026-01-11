package com.aluracursos.apiForoHub.domain.topico;

import com.aluracursos.apiForoHub.domain.curso.DatosListaCurso;
import com.aluracursos.apiForoHub.domain.usuario.DatosRespuestaUsuario;

import java.time.LocalDateTime;

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        DatosRespuestaUsuario usuario,
        DatosListaCurso curso
) {
    public DatosListaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                new DatosRespuestaUsuario(topico.getAutor()),
                new DatosListaCurso(topico.getCurso())
        );
    }
}
