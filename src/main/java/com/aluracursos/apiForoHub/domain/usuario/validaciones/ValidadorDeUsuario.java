package com.aluracursos.apiForoHub.domain.usuario.validaciones;

import com.aluracursos.apiForoHub.domain.usuario.DatosRegistroUsuario;

public interface ValidadorDeUsuario {
    void validar(DatosRegistroUsuario datos);
}
