package com.aluracursos.apiForoHub.controller;

import com.aluracursos.apiForoHub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private TopicoService topicoService;

    @Transactional
    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriBuilder){

        var topico = topicoService.registrarTopico(datos);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopicos(@PageableDefault(sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListaTopico::new));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizacionTopico datos, @PathVariable Long id) {
        var topico = topicoService.actualizarTopico(id,datos);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id){
        var topico = topicoService.buscarTopicoPorId(id);
        return ResponseEntity.ok(new DatosListaTopico(topico));
    }


    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        var topico = topicoService.buscarTopicoPorId(id);
        topicoRepository.deleteById(topico.getId());
        return ResponseEntity.noContent().build();
    }

}
