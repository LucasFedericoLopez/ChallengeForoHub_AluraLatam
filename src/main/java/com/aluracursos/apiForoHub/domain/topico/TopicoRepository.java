package com.aluracursos.apiForoHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTitulo(String titulo);

    boolean existsByMensaje(@NotBlank String mensaje);
}
