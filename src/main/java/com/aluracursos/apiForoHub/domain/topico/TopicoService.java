package com.aluracursos.apiForoHub.domain.topico;

import com.aluracursos.apiForoHub.domain.ValidacionException;
import com.aluracursos.apiForoHub.domain.curso.CursoRepository;
import com.aluracursos.apiForoHub.domain.topico.validaciones.ValidadorDeTopico;
import com.aluracursos.apiForoHub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidadorDeTopico> validadores;

    public Topico registrarTopico(DatosRegistroTopico datos) {
        validadores.forEach(v -> v.validar(datos));

        var usuario = usuarioRepository.findById(datos.autor().id_autor())
                .orElseThrow(() -> new ValidacionException("El usuario con ese ID no existe"));

        var curso = cursoRepository.findById(datos.curso().id_curso())
                .orElseThrow(() -> new ValidacionException("El curso con ese ID no existe"));

        var topico = new Topico(datos.titulo(),datos.mensaje(),usuario,curso);
        return topicoRepository.save(topico);
    }

    public Topico buscarTopicoPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("El tópico con ese ID no existe"));
    }

    public Topico actualizarTopico(Long id, DatosActualizacionTopico datos) {
        var topico = buscarTopicoPorId(id);

        if (datos.titulo() != null && !datos.titulo().equals(topico.getTitulo())) {
            if (topicoRepository.existsByTitulo(datos.titulo())) {
                throw new ValidacionException("Ya existe un tópico con ese título");
            }
        }

        if (datos.mensaje() != null && !datos.mensaje().equals(topico.getMensaje())) {
            if (topicoRepository.existsByMensaje(datos.mensaje())) {
                throw new ValidacionException("Ya existe un tópico con ese mensaje");
            }
        }

        topico.actualizarDatos(datos);
        return topico;
    }
}
