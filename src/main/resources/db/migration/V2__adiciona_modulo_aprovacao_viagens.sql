-- 1. Criação das tabelas de domínio
CREATE TABLE cargo (
                       id SERIAL PRIMARY KEY,
                       nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE area (
                      id SERIAL PRIMARY KEY,
                      nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE motivo (
                        id SERIAL PRIMARY KEY,
                        descricao VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE meio_transporte (
                                 id SERIAL PRIMARY KEY,
                                 descricao VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE situacao (
                          id SERIAL PRIMARY KEY,
                          descricao VARCHAR(50) NOT NULL UNIQUE
);

-- 2. Inserção dos valores pré-cadastrados (Seeds)
INSERT INTO cargo (nome) VALUES ('Colaborador'), ('Gestor');
INSERT INTO situacao (descricao) VALUES
                                     ('Rascunho'), ('Solicitada'), ('Cancelada'),
                                     ('Aprovada'), ('Rejeitada'), ('Ajustes Solicitados');
INSERT INTO meio_transporte (descricao) VALUES
                                            ('Avião'),
                                            ('Ônibus'),
                                            ('Carro da Empresa'),
                                            ('Carro Próprio'),
                                            ('Carro Alugado');
INSERT INTO motivo (descricao) VALUES
                                   ('Reunião com Cliente'),
                                   ('Treinamento / Capacitação'),
                                   ('Evento / Conferência'),
                                   ('Visita Técnica'),
                                   ('Implantação de Projeto');

-- 3. Atualização da tabela de Empregado
-- Validação de formato da matrícula (XXXX-X numérico)
ALTER TABLE empregado ADD CONSTRAINT chk_matricula_formato CHECK (matricula ~ '^[0-9]{4}-[0-9]$');

ALTER TABLE empregado ADD COLUMN cargo_id INT;
ALTER TABLE empregado ADD COLUMN area_id INT;

ALTER TABLE empregado ADD CONSTRAINT fk_empregado_cargo FOREIGN KEY (cargo_id) REFERENCES cargo(id);
ALTER TABLE empregado ADD CONSTRAINT fk_empregado_area FOREIGN KEY (area_id) REFERENCES area(id);

ALTER TABLE empregado ADD COLUMN senha VARCHAR(255);

-- 4. Atualização da tabela de Viagem
ALTER TABLE viagem ADD COLUMN motivo_id INT;
ALTER TABLE viagem ADD COLUMN meio_transporte_id INT;
ALTER TABLE viagem ADD COLUMN situacao_id INT;

-- Colunas de "Snapshot" para registrar a área e cargo no momento da solicitação
ALTER TABLE viagem ADD COLUMN empregado_cargo_id INT;
ALTER TABLE viagem ADD COLUMN empregado_area_id INT;

ALTER TABLE viagem ADD CONSTRAINT fk_viagem_motivo FOREIGN KEY (motivo_id) REFERENCES motivo(id);
ALTER TABLE viagem ADD CONSTRAINT fk_viagem_transporte FOREIGN KEY (meio_transporte_id) REFERENCES meio_transporte(id);
ALTER TABLE viagem ADD CONSTRAINT fk_viagem_situacao FOREIGN KEY (situacao_id) REFERENCES situacao(id);
ALTER TABLE viagem ADD CONSTRAINT fk_viagem_cargo_snap FOREIGN KEY (empregado_cargo_id) REFERENCES cargo(id);
ALTER TABLE viagem ADD CONSTRAINT fk_viagem_area_snap FOREIGN KEY (empregado_area_id) REFERENCES area(id);

-- Remover as colunas de texto antigas (Atenção: se houver dados, faça um UPDATE migrando para as chaves antes do DROP)
ALTER TABLE viagem DROP COLUMN motivo;
ALTER TABLE viagem DROP COLUMN meio_transporte;
ALTER TABLE viagem DROP COLUMN situacao;
ALTER TABLE empregado DROP COLUMN area;

-- 5. Tabela de Histórico de Mudança de Status
CREATE TABLE historico_status_viagem (
                                         id BIGSERIAL PRIMARY KEY,
                                         viagem_id BIGINT NOT NULL,
                                         situacao_id INT NOT NULL,
                                         responsavel_matricula VARCHAR(50) NOT NULL,
                                         data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         comentario TEXT,

                                         CONSTRAINT fk_historico_viagem FOREIGN KEY (viagem_id) REFERENCES viagem(numero) ON DELETE CASCADE,
                                         CONSTRAINT fk_historico_situacao FOREIGN KEY (situacao_id) REFERENCES situacao(id),
                                         CONSTRAINT fk_historico_responsavel FOREIGN KEY (responsavel_matricula) REFERENCES empregado(matricula)
);