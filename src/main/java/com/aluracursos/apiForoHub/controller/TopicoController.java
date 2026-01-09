package com.aluracursos.apiForoHub.controller;

import com.aluracursos.apiForoHub.domain.topico.DatosRegistroTopico;
import com.aluracursos.apiForoHub.domain.topico.Topico;
import com.aluracursos.apiForoHub.domain.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    /*@Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriBuilder){
        var topico = new Topico(datos);

    }*/
}
