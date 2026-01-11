package com.aluracursos.apiForoHub.controller;

import com.aluracursos.apiForoHub.domain.usuario.*;
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
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;


    @Transactional
    @PostMapping("/registrar")
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriBuilder) {

        var usuario = usuarioService.registrarUsuario(datos);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosRespuestaUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaUsuario>> listarUsuarios(@PageableDefault Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(DatosRespuestaUsuario::new));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizarUsuario(@RequestBody @Valid DatosActualizacionUsuario datos, @PathVariable Long id) {
        var usuario = usuarioService.actualizarUsuario(id,datos);

        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarUsuario(@PathVariable Long id) {
        var usuario =  usuarioService.detallarUsuario(id);

        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);

        return ResponseEntity.noContent().build();
    }




}
