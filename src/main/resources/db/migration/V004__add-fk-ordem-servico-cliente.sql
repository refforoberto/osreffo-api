alter table ordem_servico add constraint  fk_ordem_servico_cliente 
foreign key (cliente_id) references cliente (id);
