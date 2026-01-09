create table usuarios (
id bigint not null auto_increment,
mensaje varchar(500) not null,
fecha_creacion datetime not null,
solucion boolean not null default 0,
autor_id bigint not null,
topico_id bigint not null,

primary key(id),

constraint fk_respuestas_autor_id foreign key (autor_id) references usuarios(id),
constraint fk_respuestas_topico_id foreign key (topico_id) references topicos(id)
);