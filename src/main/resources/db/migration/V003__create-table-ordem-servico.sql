create table if not exists ordem_servico (
	id bigint not null auto_increment,
    cliente_id bigint not null,
    descricao text not null,
    preco decimal (10,2)  not null,
    situacao varchar (20) not null,
	data_abertura datetime not null,
    data_finalizacao datetime,
    primary key (id)
); 
