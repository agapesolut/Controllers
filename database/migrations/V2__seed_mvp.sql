INSERT INTO permissoes (id, codigo, descricao)
VALUES
    (1, 'PERMISSAO_DASHBOARD_VISUALIZAR', 'Visualizar dashboard'),
    (2, 'PERMISSAO_CLIENTES_VISUALIZAR', 'Visualizar clientes'),
    (3, 'PERMISSAO_CLIENTES_CADASTRAR', 'Cadastrar clientes'),
    (4, 'PERMISSAO_CLIENTES_EDITAR', 'Editar clientes'),
    (5, 'PERMISSAO_FAIXAS_VISUALIZAR', 'Visualizar faixas'),
    (6, 'PERMISSAO_FAIXAS_CADASTRAR', 'Cadastrar faixas'),
    (7, 'PERMISSAO_FAIXAS_EDITAR', 'Editar faixas'),
    (8, 'PERMISSAO_ALERTAS_VISUALIZAR', 'Visualizar alertas'),
    (9, 'PERMISSAO_RELATORIOS_EXPORTAR', 'Exportar relatorios'),
    (10, 'PERMISSAO_AUDITORIA_VISUALIZAR', 'Visualizar auditoria'),
    (11, 'PERMISSAO_USUARIOS_GERENCIAR', 'Gerenciar usuarios')
ON CONFLICT (id) DO NOTHING;

INSERT INTO usuarios (id, nome, email, senha_hash, perfil, ativo)
VALUES
    (
        1,
        'Administrador',
        'admin@agape.local',
        '$2a$10$8wA0cZ7m5z0v1jv2QJ5CluPh7yikd7mUZ3rZ6Em0Dq8I1D1IepVhu',
        'ADMINISTRADOR',
        TRUE
    )
ON CONFLICT (id) DO NOTHING;

INSERT INTO usuario_permissoes (usuario_id, permissao_id)
VALUES
    (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6),
    (1, 7), (1, 8), (1, 9), (1, 10), (1, 11)
ON CONFLICT DO NOTHING;

INSERT INTO faixas_honorario (
    id,
    nome,
    faturamento_minimo,
    faturamento_maximo,
    valor_honorario,
    percentual_alerta_preventivo,
    ativa
)
VALUES
    (1, 'Faixa Essencial', 0.00, 50000.00, 500.00, 90.00, TRUE),
    (2, 'Faixa Crescimento', 50000.01, 100000.00, 800.00, 90.00, TRUE),
    (3, 'Faixa Expansao', 100000.01, 200000.00, 1200.00, 90.00, TRUE),
    (4, 'Faixa Premium', 200000.01, 99999999.00, 1800.00, 90.00, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO clientes (
    id,
    nome,
    cnpj,
    regime_tributario,
    honorario_atual,
    faixa_atual_id,
    responsavel_interno,
    ativo
)
VALUES
    (1, 'Mercado Sol Nascente', '12.345.678/0001-01', 'Simples Nacional', 500.00, 1, 'Ana Paula', TRUE),
    (2, 'Construtora Pilar', '23.456.789/0001-22', 'Lucro Presumido', 800.00, 2, 'Marcos Lima', TRUE),
    (3, 'Clinica Vida Plena', '34.567.890/0001-33', 'Lucro Real', 1200.00, 3, 'Ana Paula', TRUE),
    (4, 'Distribuidora Horizonte', '45.678.901/0001-44', 'Lucro Presumido', 800.00, 2, 'Juliana Costa', TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO faturamentos_clientes (id, cliente_id, mes, ano, valor_faturado, data_referencia)
VALUES
    (1, 1, 1, 2026, 42000.00, DATE '2026-01-01'),
    (2, 1, 2, 2026, 45500.00, DATE '2026-02-01'),
    (3, 1, 3, 2026, 49000.00, DATE '2026-03-01'),
    (4, 1, 4, 2026, 51000.00, DATE '2026-04-01'),
    (5, 2, 1, 2026, 87000.00, DATE '2026-01-01'),
    (6, 2, 2, 2026, 91000.00, DATE '2026-02-01'),
    (7, 2, 3, 2026, 96000.00, DATE '2026-03-01'),
    (8, 2, 4, 2026, 98500.00, DATE '2026-04-01'),
    (9, 3, 1, 2026, 118000.00, DATE '2026-01-01'),
    (10, 3, 2, 2026, 132000.00, DATE '2026-02-01'),
    (11, 3, 3, 2026, 145000.00, DATE '2026-03-01'),
    (12, 3, 4, 2026, 152000.00, DATE '2026-04-01'),
    (13, 4, 1, 2026, 99000.00, DATE '2026-01-01'),
    (14, 4, 2, 2026, 104000.00, DATE '2026-02-01'),
    (15, 4, 3, 2026, 112000.00, DATE '2026-03-01'),
    (16, 4, 4, 2026, 119000.00, DATE '2026-04-01')
ON CONFLICT (id) DO NOTHING;

INSERT INTO auditoria (
    id,
    usuario_id,
    acao,
    modulo,
    entidade,
    entidade_id,
    valor_anterior,
    valor_novo,
    data_hora,
    ip,
    contexto
)
VALUES
    (1, 1, 'LOGIN', 'AUTH', 'USUARIO', 1, NULL, NULL, TIMESTAMP '2026-05-01 08:30:00', '10.0.0.10', 'Acesso ao sistema'),
    (2, 1, 'CONSULTA_DASHBOARD', 'DASHBOARD', 'RESUMO_GERAL', NULL, NULL, NULL, TIMESTAMP '2026-05-01 08:31:00', '10.0.0.10', 'Painel inicial carregado'),
    (3, 1, 'ANALISE_CLIENTE', 'CLIENTE', 'CLIENTE', 4, NULL, NULL, TIMESTAMP '2026-05-01 08:45:00', '10.0.0.10', 'Cliente acima da faixa analisado')
ON CONFLICT (id) DO NOTHING;

CREATE INDEX IF NOT EXISTS idx_faturamentos_cliente_referencia
    ON faturamentos_clientes (cliente_id, data_referencia DESC);

CREATE UNIQUE INDEX IF NOT EXISTS uq_alertas_reajuste_cliente
    ON alertas_reajuste (cliente_id);
