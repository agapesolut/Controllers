# Sistema de Gestao de Faturamento e Honorarios

Sistema web interno para monitoramento de faturamento de clientes e identificacao de oportunidades de reajuste de honorarios contabeis.

## Estrutura do repositorio

```txt
backend/
  faturamento-api/
frontend/
  faturamento-web/
docs/
database/
docker/
```

## Status atual

O projeto agora contem:

- monorepo separado em backend e frontend;
- backend Spring Boot organizado por modulos;
- PostgreSQL integrado ao backend nos modulos centrais do MVP;
- migrations e seed automaticos com Flyway;
- frontend React + TypeScript consumindo a API real com fallback para mock;
- layout e telas iniciais do MVP prontos para evolucao.

## Stack

- Frontend: React + TypeScript + Vite
- Backend: Java + Spring Boot
- Banco: PostgreSQL
- Integracao: leitura do banco operacional da empresa por modulo dedicado

## MVP planejado

- login de administrador;
- dashboard geral;
- cadastro de clientes;
- cadastro de faixas;
- analise individual do cliente;
- alertas preventivos e criticos;
- exportacao inicial de relatorios;
- base pronta para auditoria e permissoes.

## Como seguir

### Frontend

```bash
cd frontend/faturamento-web
npm install
npm run dev
```

### Backend

O backend esta configurado com Spring Boot, JPA, Flyway e PostgreSQL. O `pom.xml` esta em Java 17 para combinar com o ambiente local atual. Se voce for padronizar o projeto em Java 21 no servidor, basta ajustar a propriedade `java.version`.

Quando o Maven estiver disponivel no ambiente:

```bash
cd backend/faturamento-api
mvn spring-boot:run
```

## Guia de execucao

O passo a passo mais completo esta em [COMO-RODAR.md](/C:/Users/estudio/Documents/Agape/Controllers/COMO-RODAR.md:1).
