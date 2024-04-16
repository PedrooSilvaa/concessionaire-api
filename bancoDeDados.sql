-- DROP DATABASE concessionaria;

CREATE DATABASE concessionaria;
USE concessionaria;
 
create table filial(
id INT auto_increment, 
nome varchar (120),
rua VARCHAR(255),
nr INT(6),
bairro VARCHAR(120),
constraint pk_filial primary key (id)
);
 
create table vendedor(
id INT auto_increment, 
nome varchar (120),
cpf varchar (14),
id_filial int,
constraint pk_vendedor primary key (id),
CONSTRAINT fk_vendedor FOREIGN KEY (id_filial) REFERENCES filial(id)
);
 

 
INSERT INTO filial (NOME, rua, nr, bairro) 
VALUES
('Pedro cars', 'Avenida', 233, 'Ipe'),
('Nilson cars', 'Alameda', 222, 'Pompeia');

 
INSERT INTO vendedor (NOME, CPF, ID_FILIAL) 
VALUES
('Tereza ', '111.111.111-11', 1),
('Pedro', '728.394.082-84', 1),
('Edu', '222.222.222-22', 1),
('Gabi', '333.333.333-33', 1),
('Joao ', '111.111.111-11', 2),
('Carlos', '728.394.082-84', 2),
('Marcos', '222.222.222-22', 2),
('Milena', '333.333.333-33', 2);

 select * from filial;	
select * from vendedor;

SELECT v.nome, v.cpf, v.id_filial FROM vendedor v join filial f on f.id = v.id_filial;

SELECT f.id, f.nome, f.rua, f.nr, f.bairro FROM vendedor v join filial f on v.id_filial = f.id WHERE v.id = ?

