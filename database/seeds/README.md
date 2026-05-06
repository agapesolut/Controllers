# Seeds

Este diretorio vai receber cargas iniciais de:

- usuario administrador;
- permissoes padrao;
- faixas de honorarios de demonstracao;
- clientes de exemplo para ambiente de homologacao.

## Estado atual

O seed inicial do MVP agora esta automatizado pelo backend via Flyway em:

- `backend/faturamento-api/src/main/resources/db/migration/V2__seed_mvp.sql`

Uma copia de referencia tambem foi mantida em:

- `database/migrations/V2__seed_mvp.sql`
