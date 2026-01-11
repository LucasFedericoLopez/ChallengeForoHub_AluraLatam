package com.aluracursos.apiForoHub.domain.respuesta;

import com.aluracursos.apiForoHub.domain.ValidacionException;
import com.aluracursos.apiForoHub.domain.topico.TopicoRepository;
import com.aluracursos.apiForoHub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    public Respuesta registrarRespuesta(DatosRegistroRespuesta datos) {

        var usuario = usuarioRepository.findById(datos.idAutor())
                .orElseThrow(()-> new ValidacionException("No existe un usuario con ese ID"));

        var topico = topicoRepository.findById(datos.idTopico())
                .orElseThrow(() -> new ValidacionException("No existe un topico con ese ID"));

        var respuesta = new Respuesta(datos,topico,usuario);

        topico.agregarRespuesta(respuesta);

        return respuestaRepository.save(respuesta);
    }

    public Respuesta actualizarRespuesta(Long id, DatosActualizacionRespuesta datos) {
        var respuesta =  buscarRespuestaById(id);
        respuesta.actualizarMensaje(datos);

        return respuesta;

    }

    private Respuesta buscarRespuestaById(Long id) {
        return respuestaRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("La respuesta con el id solicitado no existe"));
    }

    public Respuesta detallarRespuesta(Long id) {
        return buscarRespuestaById(id);
    }

    public void eliminarRespuesta(Long id) {
        var respuesta = buscarRespuestaById(id);
        respuestaRepository.delete(respuesta);
    }
}
