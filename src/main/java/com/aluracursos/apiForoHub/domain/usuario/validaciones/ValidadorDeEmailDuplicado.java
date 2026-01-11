package com.aluracursos.apiForoHub.domain.usuario.validaciones;

import com.aluracursos.apiForoHub.domain.ValidacionException;
import com.aluracursos.apiForoHub.domain.usuario.DatosRegistroUsuario;
import com.aluracursos.apiForoHub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDeEmailDuplicado implements ValidadorDeUsuario{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(DatosRegistroUsuario datos) {
        if (usuarioRepository.existsByEmail(datos.email())) {
            throw new ValidacionException("El email ya esta registrado en el sistema");
        }
    }

}
