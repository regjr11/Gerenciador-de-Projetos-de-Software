CREATE DATABASE gerenciador_projeto_software;

use gerenciador_projeto_software;

CREATE TABLE Usuario(
	Matricula INTEGER NOT NULL AUTO_INCREMENT,
    Nome VARCHAR(30) NOT NULL,
    Funcao VARCHAR(30) NOT NULL, #enum
    Name_User VARCHAR(15) NOT NULL,
    Senha VARCHAR(10) NOT NULL,
    Email VARCHAR (25) NOT NULL,
    Telefone VARCHAR (11) NOT NULL,
    
    PRIMARY KEY (Matricula)
);

CREATE TABLE Projeto(
	Id_Projeto INTEGER NOT NULL AUTO_INCREMENT,
	Nome_Projeto VARCHAR(50) NOT NULL,
    Descricao VARCHAR(1000),
    Status_pj VARCHAR(20),
   
    PRIMARY KEY (Id_Projeto),
        
	Id_proprietario INTEGER NOT NULL,
	foreign key (Id_proprietario)
	references Usuario (Matricula)
    
);

CREATE TABLE Requisito(

	Id_Req INTEGER NOT NULL AUTO_INCREMENT,
    Nome_Req VARCHAR(50) NOT NULL,
    Modulo VARCHAR(50) NOT NULL,
    Funcionalidades VARCHAR(1000) NOT NULL,
    Data_Criacao DATE NOT NULL,
    Data_Ult_Altr DATE NOT NULL,
    Versão VARCHAR(50) NOT NULL,
    Prioridade INTEGER NOT NULL, #enum 
    complexidade  VARCHAR(50) NOT NULL, #enum
    Esforco_hrs DECIMAL NOT NULL, #verificar
    Estado VARCHAR(50) NOT NULL, #enum
    Fase VARCHAR(50) NOT NULL, 
    Descricao VARCHAR(1000) NOT NULL,
	PRIMARY KEY (Id_Req),
    
    Id_Pj INTEGER NOT NULL,
	foreign key (Id_Pj)
	references Projeto (Id_Projeto),
    
    Autor INTEGER NOT NULL,
    foreign key (Autor)
    references Usuario (Matricula),
    
	Autor_Alt INTEGER NOT NULL,
    foreign key (Autor_Alt)
    references Usuario (Matricula)
);