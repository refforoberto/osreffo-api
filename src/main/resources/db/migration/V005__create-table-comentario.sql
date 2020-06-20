create table if not exists comentario (
	id bigint not null auto_increment,
    ordem_servico_id bigint not null,
    descricao text not null,   
    data_envio datetime,
    primary key (id)
); 


alter table comentario add constraint  fk_ordem_servico_comentario
foreign key (ordem_servico_id) references ordem_servico (id);
