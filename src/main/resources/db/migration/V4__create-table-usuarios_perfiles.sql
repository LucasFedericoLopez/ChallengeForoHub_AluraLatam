create table usuarios (
usuario_id bignit not null,
perfil_id bignit not null,
primary key (usuario_id, perfil_id),
constraint fk_usuario_perfiles_usuario_id foreign key (usuario_id) references usuarios(id),
constraint fk_usuario_perfiles_perfil_id foreign key (perfil_id) references perfiles(id)
);