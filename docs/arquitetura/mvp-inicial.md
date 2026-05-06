# Arquitetura Inicial do MVP

## Visao geral

O projeto foi estruturado como monorepo para permitir evolucao paralela do frontend e backend sem misturar responsabilidades.

## Backend

- arquitetura modular por dominio;
- camada `integracao` reservada para consulta ao banco legado;
- servicos iniciais funcionando com dados mockados para acelerar a validacao de regras;
- configuracoes preparadas para seguranca, CORS e auditoria.

## Frontend

- React + TypeScript + Vite;
- rotas protegidas por contexto de autenticacao;
- layout principal com navegacao lateral;
- paginas do MVP prontas para evoluir de mock para integracao real.

## Fluxo inicial

1. usuario faz login;
2. frontend carrega indicadores principais;
3. dashboard destaca alertas e receita potencial;
4. usuario acessa lista de clientes;
5. tela de analise mostra faixa atual, faixa sugerida e impacto financeiro;
6. backend futuramente substitui mocks por leitura do banco da empresa e persistencia local.
