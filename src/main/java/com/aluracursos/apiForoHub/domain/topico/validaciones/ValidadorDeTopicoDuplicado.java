package com.aluracursos.apiForoHub.domain.topico.validaciones;

import com.aluracursos.apiForoHub.domain.ValidacionException;
import com.aluracursos.apiForoHub.domain.topico.DatosRegistroTopico;
import com.aluracursos.apiForoHub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDeTopicoDuplicado implements ValidadorDeTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DatosRegistroTopico datos) {
        if (topicoRepository.existsByTitulo(datos.titulo())) {
            throw new ValidacionException("Ya existe un tópico con este título");
        }
        if (topicoRepository.existsByMensaje(datos.mensaje())) {
            throw new ValidacionException("Ya existe un tópico con este mensaje");
        }
    }
}
