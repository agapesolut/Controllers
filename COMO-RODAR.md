# Como Rodar o Projeto

Este documento explica como subir o projeto localmente no estado atual do repositorio.

## Visao rapida

Hoje o projeto esta assim:

- o frontend tenta consumir a API real e faz fallback para mock se o backend nao estiver disponivel;
- o backend ja esta integrado ao PostgreSQL para os modulos centrais do MVP;
- o banco sobe via Docker e recebe migrations automaticamente com Flyway;
- clientes, faixas, faturamentos, alertas e auditoria ja podem sair do banco real.

## Estrutura principal

```txt
backend/faturamento-api
frontend/faturamento-web
database/migrations
docker/docker-compose.yml
```

## 1. Rodando o frontend

Entre na pasta do frontend:

```powershell
cd C:\Users\estudio\Documents\Agape\Controllers\frontend\faturamento-web
```

Instale as dependencias:

```powershell
npm.cmd install
```

Suba o servidor de desenvolvimento:

```powershell
npm.cmd run dev
```

Abra no navegador:

```txt
http://localhost:5173
```

### Login mock atual

Use um destes logins:

- usuario: `admin`
- ou: `admin@agape.local`
- senha: `admin123`

## 2. Rodando o banco com Docker

Entre na pasta do Docker:

```powershell
cd C:\Users\estudio\Documents\Agape\Controllers\docker
```

Suba os containers:

```powershell
docker compose up -d
```

Isso sobe:

- PostgreSQL na porta `5432`
- pgAdmin na porta `5050`

### Credenciais do PostgreSQL

- host: `localhost`
- porta: `5432`
- database: `faturamento_db`
- usuario: `faturamento_user`
- senha: `faturamento_pass`

### Credenciais do pgAdmin

- URL: `http://localhost:5050`
- email: `admin@agape.local`
- senha: `admin123`

## 3. Migration do banco

As migrations de execucao automatica agora ficam em:

```txt
backend/faturamento-api/src/main/resources/db/migration/
```

Os arquivos principais sao:

- `V1__baseline.sql`
- `V2__seed_mvp.sql`

As tabelas criadas sao:

- `usuarios`
- `permissoes`
- `usuario_permissoes`
- `faixas_honorario`
- `clientes`
- `faturamentos_clientes`
- `alertas_reajuste`
- `auditoria`

### Importante

Agora o backend roda com Flyway.

Isso significa que:

1. o schema sobe automaticamente ao iniciar a API
2. os dados iniciais do MVP tambem sao inseridos automaticamente
3. nao e mais necessario aplicar o SQL manualmente em um banco vazio

### Seed inicial

Ao subir a API com um banco vazio, o sistema cria dados iniciais de:

- faixas de honorarios;
- clientes;
- historico de faturamento;
- usuario administrador base;
- permissoes;
- auditoria inicial.

## 4. Rodando o backend

Entre na pasta do backend:

```powershell
cd C:\Users\estudio\Documents\Agape\Controllers\backend\faturamento-api
```

Suba a aplicacao:

```powershell
mvn spring-boot:run
```

O backend esta configurado para subir na porta:

```txt
http://localhost:8080
```

### Configuracao atual do backend

O arquivo de configuracao esta em:

```txt
backend/faturamento-api/src/main/resources/application.yml
```

Configuracao principal:

- `DB_URL=jdbc:postgresql://localhost:5432/faturamento_db`
- `DB_USERNAME=faturamento_user`
- `DB_PASSWORD=faturamento_pass`
- `SERVER_PORT=8080`
- `CORS_ORIGIN=http://localhost:5173`
- `Flyway habilitado`
- `Hibernate em modo validate`

## 5. Situacao real do sistema hoje

No estado atual, o comportamento e este:

- frontend tenta usar a API real primeiro;
- se a API nao responder, o frontend cai para mock automaticamente;
- backend persiste e consulta dados reais para `faixas`, `clientes`, `faturamentos`, `alertas` e `auditoria`;
- autenticacao do frontend ainda esta mockada para facilitar a demo do MVP.

## 6. Ordem recomendada para testar

Se voce quiser usar o sistema com banco real:

1. rode o frontend
2. suba o Docker
3. rode o backend
4. acesse `http://localhost:5173`
5. entre com `admin` / `admin123`

Se voce quiser apenas navegar visualmente sem backend:

1. rode o frontend
2. acesse `http://localhost:5173`
3. entre com `admin` / `admin123`
4. o frontend vai usar fallback mock automaticamente

## 7. Problemas comuns

### `npm` bloqueado no PowerShell

Se o PowerShell bloquear `npm`, use:

```powershell
npm.cmd install
npm.cmd run dev
```

### `mvn` nao encontrado

Se esse erro acontecer, significa que o Maven nao esta instalado no ambiente ou nao esta no `PATH`.

Nesse caso voce pode:

1. instalar o Maven globalmente
2. ou adicionar depois o `mvnw` ao projeto

### Docker nao sobe

Verifique se o Docker Desktop esta aberto antes de rodar:

```powershell
docker compose up -d
```

## 8. O que ja esta no banco

Hoje o banco ja sustenta:

- cadastro e listagem de clientes;
- cadastro e listagem de faixas;
- leitura do historico de faturamento;
- geracao e persistencia de alertas calculados;
- leitura de auditoria;
- dashboards alimentados a partir dos dados persistidos.

## 9. Proximo passo recomendado

Os proximos passos mais naturais agora sao:

- integrar autenticacao real com a tabela `usuarios`;
- trocar o login mock do frontend pelo endpoint do backend;
- adicionar cadastro real de faturamentos;
- evoluir relatorios para exportacao real baseada no banco.
