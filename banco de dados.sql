create schema proj;
use proj;

create table produto(
	idproduto 	int primary key not null auto_increment,
    nome		varchar(45) not null,
    preco		decimal(8,2)
);

select * from produto;

insert into produto (nome,preco) values ('POLO LACOSTE',439.00);
insert into produto (nome,preco) values ('POLO TOMMY',339.00);
insert into produto (nome,preco) values ('POLO CK',239.00);