CREATE TABLE empregado (
                           matricula VARCHAR(50) PRIMARY KEY,
                           nome VARCHAR(255) NOT NULL,
                           area VARCHAR(100) NOT NULL
);


CREATE TABLE viagem (
                        numero BIGSERIAL PRIMARY KEY,
                        destino VARCHAR(255) NOT NULL,
                        data_saida DATE NOT NULL,
                        data_retorno DATE NOT NULL,
                        motivo TEXT NOT NULL,
                        meio_transporte VARCHAR(100) NOT NULL,
                        situacao VARCHAR(50) NOT NULL DEFAULT 'Rascunho',
                        empregado_matricula VARCHAR(50) NOT NULL,

                        CONSTRAINT fk_viagem_empregado
                        FOREIGN KEY (empregado_matricula)
                        REFERENCES empregado(matricula)
);