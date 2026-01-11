package com.aluracursos.apiForoHub.controller;

import com.aluracursos.apiForoHub.domain.usuario.DatosAutenticacionUsuario;
import com.aluracursos.apiForoHub.domain.usuario.Usuario;
import com.aluracursos.apiForoHub.infra.security.DatosTokenJWT;
import com.aluracursos.apiForoHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity inicarSesion(@RequestBody @Valid DatosAutenticacionUsuario datos){
        var autenticacionToken = new UsernamePasswordAuthenticationToken(datos.email(),datos.contrasenia());
        var autenticacion = authenticationManager.authenticate(autenticacionToken);

        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }

}
