package com.aluracursos.apiForoHub.domain.respuesta;

import com.aluracursos.apiForoHub.domain.topico.DatosDetalleTopico;
import com.aluracursos.apiForoHub.domain.usuario.DatosRespuestaUsuario;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        DatosDetalleTopico topico,
        DatosRespuestaUsuario autor,
        LocalDateTime fechaCreacion
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                new DatosDetalleTopico(respuesta.getTopico()),
                new DatosRespuestaUsuario(respuesta.getAutor()),
                respuesta.getFechaCreacion()
        );
    }
}
