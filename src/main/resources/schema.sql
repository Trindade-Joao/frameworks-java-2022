CREATE TABLE carro
(
    placa         VARCHAR(8) PRIMARY KEY,
    marca         VARCHAR(20)   NOT NULL,
    modelo        VARCHAR(20)   NOT NULL,
    cor           VARCHAR(20)   NOT NULL,
    ano           INT           NOT NULL,
    quilometragem INT           NOT NULL,
    diaria        DECIMAL(7, 2) NOT NULL
);

CREATE TABLE cliente
(
    cliente_key INT PRIMARY KEY AUTO_INCREMENT,
    nome        VARCHAR(100) NOT NULL,
    cpf         BIGINT       NOT NULL,
    email       VARCHAR(50)  NOT NULL,
    celular     BIGINT       NOT NULL
);

CREATE TABLE endereco
(
    endereco_key INT PRIMARY KEY AUTO_INCREMENT,
    rua          VARCHAR(50) NOT NULL,
    numero       INT         NOT NULL,
    complemento  VARCHAR(50),
    bairro       VARCHAR(50) NOT NULL,
    cidade       VARCHAR(50) NOT NULL,
    estado       VARCHAR(2)  NOT NULL,
    cliente_key  INT         NOT NULL,
    foreign key (cliente_key) references cliente (cliente_key)
        ON DELETE CASCADE
);

CREATE TABLE vendedor
(
    vendedor_key  INT PRIMARY KEY AUTO_INCREMENT,
    nome          VARCHAR(100) NOT NULL,
    cpf           BIGINT       NOT NULL,
    data_admissao DATE         NOT NULL
);

CREATE TABLE conta_corrente
(
    conta_corrente_key INT PRIMARY KEY AUTO_INCREMENT,
    banco              VARCHAR(50) NOT NULL,
    agencia            INT         NOT NULL,
    conta_corrente     INT         NOT NULL,
    vendedor_key       INT         NOT NULL,
    foreign key (vendedor_key) references vendedor (vendedor_key) ON DELETE CASCADE

);

CREATE TABLE aluguel
(
    aluguel_key  INT PRIMARY KEY AUTO_INCREMENT,
    cliente_key  INT           NOT NULL,
    vendedor_key INT           NOT NULL,
    placa_carro  VARCHAR(8)    NOT NULL,
    data_aluguel DATE          NOT NULL,
    dias_alugado SMALLINT      NOT NULL,
    valor_total  DECIMAL(7, 2) NOT NULL,
    foreign key (cliente_key) references cliente (cliente_key),
    foreign key (vendedor_key) references vendedor (vendedor_key),
    foreign key (placa_carro) references carro (placa)
        ON DELETE CASCADE
);

