CREATE TABLE tipo_despesa (
                                  id SERIAL PRIMARY KEY,
                                  descricao VARCHAR(100) NOT NULL
);

CREATE TABLE despesa (
                             id SERIAL PRIMARY KEY,
                             data_despesa DATE NOT NULL,
                             descricao VARCHAR(255) NOT NULL,
                             valor NUMERIC(10,2) NOT NULL,
                             tipo_despesa_id INT NOT NULL REFERENCES tipo_despesa(id),
                             viagem_numero BIGINT NOT NULL REFERENCES viagem(numero)
);

-- Seeds de Tipo de Despesa
INSERT INTO tipo_despesa (descricao) VALUES
                                             ('Hospedagem'),
                                             ('Alimentação'),
                                             ('Transporte'),
                                             ('Combustível'),
                                             ('Pedágios'),
                                             ('Outras despesas');