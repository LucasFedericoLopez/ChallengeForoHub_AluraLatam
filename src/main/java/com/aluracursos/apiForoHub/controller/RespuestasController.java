package com.aluracursos.apiForoHub.controller;

import com.aluracursos.apiForoHub.domain.respuesta.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestasController {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private RespuestaService respuestaService;

    @Transactional
    @PostMapping
    public ResponseEntity registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos, UriComponentsBuilder uriBuilder) {
        var respuesta = respuestaService.registrarRespuesta(datos);
        var uri = uriBuilder.path("/{id}/respuestas").buildAndExpand(respuesta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleRespuesta(respuesta));
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleRespuesta>> listarRespuestas(@PageableDefault Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(DatosDetalleRespuesta::new));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid DatosActualizacionRespuesta datos, @PathVariable Long id) {
        var respuesta = respuestaService.actualizarRespuesta(id,datos);

        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarRespuesta(@PathVariable Long id) {
        var respuesta =  respuestaService.detallarRespuesta(id);

        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarRespuesta(@PathVariable Long id) {
        respuestaService.eliminarRespuesta(id);

        return ResponseEntity.noContent().build();
    }
}
