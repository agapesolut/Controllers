CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    perfil VARCHAR(60) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS permissoes (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(100) NOT NULL UNIQUE,
    descricao VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS usuario_permissoes (
    usuario_id BIGINT NOT NULL REFERENCES usuarios (id),
    permissao_id BIGINT NOT NULL REFERENCES permissoes (id),
    PRIMARY KEY (usuario_id, permissao_id)
);

CREATE TABLE IF NOT EXISTS faixas_honorario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    faturamento_minimo NUMERIC(14, 2) NOT NULL,
    faturamento_maximo NUMERIC(14, 2) NOT NULL,
    valor_honorario NUMERIC(14, 2) NOT NULL,
    percentual_alerta_preventivo NUMERIC(5, 2) NOT NULL,
    ativa BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS clientes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    regime_tributario VARCHAR(80) NOT NULL,
    honorario_atual NUMERIC(14, 2) NOT NULL,
    faixa_atual_id BIGINT NOT NULL REFERENCES faixas_honorario (id),
    responsavel_interno VARCHAR(150) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS faturamentos_clientes (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL REFERENCES clientes (id),
    mes INTEGER NOT NULL,
    ano INTEGER NOT NULL,
    valor_faturado NUMERIC(14, 2) NOT NULL,
    data_referencia DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS alertas_reajuste (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL REFERENCES clientes (id),
    faixa_atual_id BIGINT NOT NULL REFERENCES faixas_honorario (id),
    faixa_sugerida_id BIGINT NOT NULL REFERENCES faixas_honorario (id),
    faturamento_atual NUMERIC(14, 2) NOT NULL,
    honorario_atual NUMERIC(14, 2) NOT NULL,
    honorario_sugerido NUMERIC(14, 2) NOT NULL,
    tipo_alerta VARCHAR(30) NOT NULL,
    status VARCHAR(30) NOT NULL,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS auditoria (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT REFERENCES usuarios (id),
    acao VARCHAR(100) NOT NULL,
    modulo VARCHAR(100) NOT NULL,
    entidade VARCHAR(100) NOT NULL,
    entidade_id BIGINT,
    valor_anterior TEXT,
    valor_novo TEXT,
    data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ip VARCHAR(45),
    contexto TEXT
);
