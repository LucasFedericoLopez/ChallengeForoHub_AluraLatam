package com.aluracursos.apiForoHub.domain.usuario;

import com.aluracursos.apiForoHub.domain.ValidacionException;
import com.aluracursos.apiForoHub.domain.usuario.validaciones.ValidadorDeUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private List<ValidadorDeUsuario> validadores;

    public Usuario registrarUsuario(DatosRegistroUsuario datos) {

        validadores.forEach(v -> v.validar(datos));

        var passwordHash = passwordEncoder.encode(datos.contrasenia());
        var usuario = new Usuario(datos, passwordHash);

        return repository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, DatosActualizacionUsuario datos) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ValidacionException("El usuario con ese ID no existe"));

        validarEmailEnActualizacion(datos, usuario);

        usuario.actualizarDatos(datos);

        return usuario;
    }

    private void validarEmailEnActualizacion(DatosActualizacionUsuario datos, Usuario usuario) {
        if (datos.email() != null && !datos.email().equals(usuario.getEmail())) {
            if (repository.existsByEmail(datos.email())) {
                throw new ValidacionException("El email ya se encuentra en el sistema");
            }
        }
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ValidacionException("El usuario con ese ID no existe"));
        repository.deleteById(usuario.getId());
    }

    public Usuario detallarUsuario(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidacionException("El usuario con ese ID no existe"));
    }
}
