DROP TABLE people IF EXISTS;

CREATE TABLE people  (
    person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    nome VARCHAR(50),
    cpf VARCHAR(11),
    dataNascimento VARCHAR(20),
    rua VARCHAR(100),
    numero INT,
    bairro VARCHAR(50),
    cidade VARCHAR(50),
    estado VARCHAR(50),
    cep VARCHAR(10),
    telefoneResidencial VARCHAR(50),
    telefoneComercial VARCHAR(50),
    celular VARCHAR(15),
    email VARCHAR(50),
    usuario VARCHAR(30),
    senha VARCHAR(100),
    tipoVisitante VARCHAR(20),
    empresaNome VARCHAR(50),
    empresaCnpj VARCHAR(14)
);