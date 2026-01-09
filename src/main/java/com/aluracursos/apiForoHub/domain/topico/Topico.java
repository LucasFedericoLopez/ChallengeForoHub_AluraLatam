package com.aluracursos.apiForoHub.domain.topico;

import com.aluracursos.apiForoHub.domain.curso.Curso;
import com.aluracursos.apiForoHub.domain.respuesta.Respuesta;
import com.aluracursos.apiForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico", cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(DatosRegistroTopico datos) {

    }
}
