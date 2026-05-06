# Estrutura do Projeto — Sistema de Gestão de Faturamento e Honorários

## 1. Contexto do sistema

O sistema será uma plataforma web interna para **Gestão de Faturamento e Honorários**, voltada para escritórios contábeis.

O objetivo principal é **monitorar automaticamente o faturamento dos clientes e alertar quando houver necessidade de reajuste dos honorários**, evitando perda de receita causada pela conferência manual.

Atualmente, o processo é feito manualmente: alguém precisa verificar o faturamento dos clientes, comparar com as faixas de cobrança e calcular se o honorário deveria ser reajustado.

A principal dor identificada é que **clientes passam muito da faixa contratada e ninguém percebe**, gerando perda financeira para o escritório.

O sistema funcionará como uma **vitrine de dashboards**, exibindo indicadores, alertas, comparativos, histórico e previsão de receita potencial com reajustes.

---

## 2. Objetivo geral

Desenvolver um sistema web para monitorar o faturamento dos clientes do escritório contábil e avisar quando o honorário mensal deveria ser reajustado de acordo com faixas configuráveis.

O sistema deve:

- consultar dados do banco da empresa;
- analisar faturamento dos clientes;
- comparar o faturamento com faixas configuradas;
- gerar alertas preventivos e críticos;
- exibir dashboards gerenciais;
- permitir análise individual dos clientes;
- exportar relatórios;
- manter histórico e auditoria completa;
- não executar reajustes automaticamente.

---

## 3. Usuários do sistema

O sistema será usado apenas por usuários internos da empresa.

Não haverá acesso para clientes externos.

O sistema terá camadas de acesso e permissões configuráveis.

### Perfis previstos

#### Administrador

- Visualiza todo o sistema.
- Gerencia usuários.
- Gerencia clientes.
- Gerencia faixas de honorários.
- Visualiza todos os dashboards.
- Visualiza auditoria.
- Exporta relatórios.
- Configura permissões.

#### Contador

- Visualiza os clientes sob sua responsabilidade.
- Analisa alertas relacionados aos seus clientes.
- Acompanha faturamento dos clientes vinculados.
- Pode acessar módulos específicos conforme permissão.

#### Financeiro

- Visualiza histórico de honorários.
- Visualiza histórico de reajustes e alertas.
- Acessa relatórios.
- Pode consultar dashboards financeiros conforme permissão.

### Observação sobre permissões

Não haverá usuário comum apenas de consulta no escopo inicial.

Deve ser possível combinar módulos visíveis para cada usuário ou perfil.

Exemplo: um usuário do financeiro pode ver relatórios e dashboards, mas não editar faixas. Um contador pode ver clientes e alertas, mas não acessar auditoria.

---

## 4. Fonte dos dados

Os dados virão diretamente do **banco de dados da empresa**, ao qual haverá acesso total.

O sistema deverá consultar esse banco para buscar informações de clientes, faturamento e dados necessários para análise.

### Dados esperados

- Cliente.
- CNPJ.
- Regime tributário.
- Faturamento mensal.
- Faturamento acumulado.
- Honorário atual.
- Histórico de faturamento.
- Responsável interno.
- Dados necessários para comparação com faixas.

### Estratégia recomendada

Mesmo com acesso ao banco da empresa, recomenda-se que o sistema tenha um **banco próprio** para armazenar:

- usuários;
- permissões;
- faixas de honorários;
- alertas;
- auditoria;
- configurações;
- relatórios gerados;
- histórico interno do sistema.

O banco da empresa deve ser usado como fonte de consulta para dados operacionais, evitando alterar diretamente dados sensíveis ou legados.

---

## 5. Regras de reajuste

As faixas e taxas definitivas serão definidas na entrevista do Pedido do Investidor.

Enquanto isso, o sistema deve ser planejado para trabalhar com regras configuráveis.

### O sistema deve permitir configurar

- Nome da faixa.
- Faturamento mínimo.
- Faturamento máximo.
- Valor de honorário sugerido.
- Percentual de alerta preventivo.
- Status da faixa: ativa ou inativa.

### Exemplo conceitual

```txt
Faixa 1: de R$ 0,00 até R$ 50.000,00
Honorário sugerido: R$ 500,00

Faixa 2: de R$ 50.000,01 até R$ 100.000,00
Honorário sugerido: R$ 800,00

Faixa 3: de R$ 100.000,01 até R$ 200.000,00
Honorário sugerido: R$ 1.200,00
```

### Pontos que ainda serão definidos

- Se a análise será baseada no faturamento mensal.
- Se a análise será baseada no faturamento acumulado.
- Se será usada média dos últimos meses.
- Qual será a tolerância antes de gerar alerta.
- Qual percentual caracteriza alerta preventivo.
- Como será calculado o honorário sugerido.

---

## 6. Alertas

O sistema deve gerar alertas em dois níveis principais.

### Alerta preventivo

Gerado quando o cliente estiver logo abaixo do limite da faixa atual.

Exemplo:

```txt
Cliente está usando 90% do limite da faixa atual.
```

Esse alerta deve indicar atenção, mas ainda não representa ultrapassagem.

### Alerta crítico

Gerado quando o cliente ultrapassar o limite da faixa atual.

Esse alerta deve ser visualmente mais forte, com destaque vermelho.

Exemplo:

```txt
Cliente ultrapassou a faixa de faturamento contratada.
```

### Alerta ao acessar o sistema

Ao fazer login ou abrir o dashboard, o sistema deve apresentar rapidamente os alertas mais importantes.

Exemplos:

- clientes que ultrapassaram faixa;
- clientes próximos do limite;
- receita potencial estimada;
- alertas pendentes de análise.

---

## 7. Dashboards previstos

Os dashboards devem apresentar informações estratégicas para facilitar o acompanhamento de faturamento, reajustes e perda de receita.

### Dashboards obrigatórios

#### 1. Clientes com reajuste pendente

Mostra clientes que ultrapassaram a faixa atual ou estão próximos do limite.

Indicadores possíveis:

- quantidade de clientes em alerta;
- quantidade de alertas críticos;
- quantidade de alertas preventivos;
- valor total de receita potencial.

#### 2. Clientes por faixa de faturamento

Mostra a distribuição dos clientes nas faixas configuradas.

Pode ser exibido em gráfico de barras, pizza ou tabela.

#### 3. Evolução de faturamento por cliente

Mostra como o faturamento de um cliente evoluiu ao longo do tempo.

Pode comparar:

- mês atual;
- meses anteriores;
- média trimestral;
- média anual.

#### 4. Honorário atual x honorário recomendado

Mostra a diferença entre o valor cobrado atualmente e o valor sugerido pela faixa correta.

Indicadores possíveis:

- honorário atual;
- honorário recomendado;
- diferença mensal;
- diferença anual estimada.

#### 5. Histórico de alertas

Mostra alertas gerados, analisados, ignorados ou resolvidos.

#### 6. Comparativo com períodos anteriores

Compara faturamento e receita potencial entre períodos.

Exemplos:

- mês atual x mês anterior;
- trimestre atual x trimestre anterior;
- ano atual x ano anterior.

#### 7. Previsão de receita gerada a partir dos reajustes

Mostra quanto o escritório poderia aumentar de receita caso aplicasse os reajustes sugeridos.

Exemplos:

- receita potencial mensal;
- receita potencial anual;
- clientes com maior diferença;
- ranking de oportunidades.

---

## 8. Fluxo de trabalho

O fluxo completo será definido na entrevista, mas o comportamento inicial pode ser definido assim:

```txt
1. O sistema consulta o faturamento do cliente.
2. O sistema identifica a faixa atual do cliente.
3. O sistema compara o faturamento com os limites da faixa.
4. Se o cliente estiver próximo do limite, gera alerta preventivo.
5. Se o cliente ultrapassar o limite, gera alerta crítico.
6. O alerta aparece no dashboard.
7. O usuário acessa a tela de análise do cliente.
8. O usuário visualiza faturamento, faixa atual, faixa sugerida e diferença de honorário.
9. O sistema apenas informa e registra histórico.
10. Nenhum reajuste é executado automaticamente.
```

### Importante

O sistema não toma decisões financeiras automaticamente.

Ele apenas:

- monitora;
- calcula;
- sinaliza;
- exibe alertas;
- mostra previsão;
- gera relatórios.

A decisão humana continua sendo obrigatória.

---

## 9. Histórico e auditoria

O sistema deve guardar todo o histórico e permitir auditoria completa.

### Deve ser auditado

- Login de usuários.
- Logout de usuários.
- Tentativas de login inválidas.
- Cadastro de clientes.
- Edição de clientes.
- Exclusão ou inativação de clientes.
- Cadastro de faixas.
- Edição de faixas.
- Inativação de faixas.
- Geração de alertas.
- Análise de alertas.
- Exportação de relatórios.
- Alterações de permissões.
- Alterações de usuários.
- Acessos a módulos sensíveis.

### Informações da auditoria

Cada registro de auditoria deve armazenar:

- usuário responsável;
- ação executada;
- módulo afetado;
- entidade afetada;
- ID da entidade;
- valor anterior;
- valor novo;
- data e hora;
- IP de origem;
- contexto da ação.

---

## 10. Integração

A integração inicial será apenas com o banco de dados da empresa.

Não haverá, no escopo inicial:

- integração com ERP externo;
- integração com emissão de nota;
- integração com cobrança automática;
- integração bancária;
- alteração automática de mensalidade;
- comunicação automática com clientes.

### Recomendação técnica

Criar um módulo separado chamado `integracao`, responsável por consultar o banco da empresa.

Esse módulo deve isolar a lógica de leitura externa para evitar acoplamento entre o sistema novo e o banco legado.

---

## 11. Tecnologias definidas

### Frontend

- React.
- TypeScript.
- Vite.
- Axios.
- React Router DOM.
- Recharts.
- Lucide React.

### Backend

- Java.
- Spring Boot.
- Spring Web.
- Spring Data JPA.
- Spring Security.
- Bean Validation.
- PostgreSQL Driver.
- Lombok.
- Spring Boot DevTools.

### Banco recomendado para o sistema

- PostgreSQL.

### Justificativa para PostgreSQL

O PostgreSQL é recomendado porque:

- é relacional;
- é confiável;
- trabalha bem com relatórios;
- suporta índices e views;
- funciona muito bem com Spring Boot;
- é adequado para auditoria e dados históricos.

---

## 12. Plataforma

O sistema será web.

A hospedagem ainda será decidida na reunião.

Possibilidades:

- servidor interno;
- nuvem;
- ambiente híbrido.

---

## 13. Atualização dos dados

A expectativa é que os dados sejam atualizados em tempo real.

Tecnicamente, isso pode significar:

- consultar o banco sempre que abrir o dashboard;
- atualizar dados a cada intervalo de tempo;
- criar rotina de sincronização;
- consultar views atualizadas;
- usar eventos do banco, se disponível.

Essa definição depende da estrutura do banco da empresa e da performance permitida.

---

## 14. Relatórios e exportações

O sistema deve gerar relatórios e exportações nos seguintes formatos:

- PDF;
- Excel;
- CSV.

### Relatórios previstos

- Relatório de clientes com reajuste pendente.
- Relatório de alertas críticos.
- Relatório de alertas preventivos.
- Relatório de receita potencial.
- Relatório comparativo por período.
- Relatório de histórico de faturamento.
- Relatório de auditoria.

---

## 15. Segurança

O sistema deve contemplar requisitos de segurança desde o início.

### Requisitos de segurança

- Login por usuário.
- Senha criptografada.
- Controle de acesso por perfil.
- Controle de permissões por módulo.
- Auditoria das ações dos usuários.
- Backup.
- Criptografia de dados sensíveis.
- Conformidade com LGPD.
- Proteção de rotas no frontend.
- Proteção de endpoints no backend.

### Observação sobre senhas

Senhas nunca devem ser armazenadas em texto puro.

O backend deve armazenar apenas hash seguro da senha.

---

## 16. Escopo inicial do MVP

A primeira versão do sistema deve conter:

- Login único de administrador.
- Dashboard geral.
- Gráficos de faturamento.
- Previsão de receita gerada a partir de reajustes.
- Tela de análise do cliente.
- Exportação para Excel.
- Cadastro de clientes.
- Cadastro de faixas de honorários.

### Telas iniciais do MVP

1. Login.
2. Dashboard principal.
3. Clientes.
4. Análise do cliente.
5. Cadastro de faixas.
6. Alertas.
7. Relatórios.
8. Auditoria.

---

## 17. Estrutura geral do projeto

Recomenda-se utilizar um monorepo com frontend, backend, documentação, banco e arquivos de infraestrutura.

```txt
gestao-faturamento-honorarios/
│
├── backend/
│   └── faturamento-api/
│
├── frontend/
│   └── faturamento-web/
│
├── docs/
│   ├── requisitos/
│   ├── casos-de-uso/
│   ├── arquitetura/
│   ├── banco-de-dados/
│   └── entrevistas/
│
├── database/
│   ├── migrations/
│   ├── seeds/
│   └── diagramas/
│
├── docker/
│   ├── docker-compose.yml
│   └── README.md
│
├── .gitignore
└── README.md
```

---

## 18. Comandos para criar a estrutura inicial

```bash
mkdir gestao-faturamento-honorarios
cd gestao-faturamento-honorarios

mkdir backend frontend docs database docker
mkdir docs/requisitos docs/casos-de-uso docs/arquitetura docs/banco-de-dados docs/entrevistas
mkdir database/migrations database/seeds database/diagramas
```

---

## 19. Backend — Spring Boot

O backend deve ser criado com Java e Spring Boot.

### Configuração sugerida

```txt
Project: Maven
Language: Java
Spring Boot: versão estável atual
Group: br.com.empresa
Artifact: faturamento-api
Name: faturamento-api
Package name: br.com.empresa.faturamento
Packaging: Jar
Java: 21
```

### Dependências iniciais

```txt
Spring Web
Spring Data JPA
Spring Security
Validation
PostgreSQL Driver
Lombok
Spring Boot DevTools
```

### Local do backend

```txt
backend/faturamento-api/
```

---

## 20. Estrutura de pacotes do backend

Dentro de:

```txt
backend/faturamento-api/src/main/java/br/com/empresa/faturamento/
```

Criar a seguinte estrutura:

```txt
faturamento/
│
├── FaturamentoApiApplication.java
│
├── config/
│   ├── SecurityConfig.java
│   ├── CorsConfig.java
│   └── AuditConfig.java
│
├── auth/
│   ├── controller/
│   ├── service/
│   ├── dto/
│   └── security/
│
├── usuario/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
│
├── cliente/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
│
├── faturamento/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
│
├── faixa/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
│
├── alerta/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
│
├── dashboard/
│   ├── controller/
│   ├── service/
│   └── dto/
│
├── relatorio/
│   ├── controller/
│   ├── service/
│   └── exporter/
│
├── auditoria/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
│
├── integracao/
│   ├── empresa/
│   │   ├── ExternalDbConfig.java
│   │   ├── ExternalClienteRepository.java
│   │   └── ExternalFaturamentoRepository.java
│   └── sync/
│
└── shared/
    ├── exception/
    ├── response/
    ├── enums/
    └── util/
```

---

## 21. Responsabilidade dos módulos do backend

### auth

Responsável por:

- login;
- logout;
- usuário autenticado;
- futuramente JWT;
- validação de senha;
- controle de sessão.

### usuario

Responsável por:

- cadastro de usuários;
- perfis;
- permissões;
- associação de usuários aos módulos.

### cliente

Responsável por:

- cadastro de clientes;
- edição de clientes;
- consulta de clientes;
- responsável interno;
- CNPJ;
- regime tributário;
- honorário atual;
- status.

### faturamento

Responsável por:

- faturamento mensal;
- faturamento acumulado;
- histórico de faturamento;
- comparativo com períodos anteriores.

### faixa

Responsável por:

- cadastro de faixas;
- limite mínimo;
- limite máximo;
- valor de honorário;
- percentual de alerta preventivo;
- ativação e inativação de faixas.

### alerta

Responsável por:

- alerta preventivo;
- alerta crítico;
- alerta ao acessar;
- status do alerta;
- histórico do alerta.

### dashboard

Responsável por consolidar dados para gráficos e indicadores.

### relatorio

Responsável por:

- exportação Excel;
- exportação CSV;
- exportação PDF;
- relatórios gerenciais.

### auditoria

Responsável por registrar ações realizadas pelos usuários.

### integracao

Responsável por consultar o banco da empresa.

### shared

Responsável por classes comuns, exceções, respostas padronizadas, enums e utilitários.

---

## 22. Entidades iniciais do backend

### Cliente

```java
public class Cliente {
    private Long id;
    private String nome;
    private String cnpj;
    private String regimeTributario;
    private BigDecimal honorarioAtual;
    private Long faixaAtualId;
    private String responsavelInterno;
    private Boolean ativo;
}
```

### FaixaHonorario

```java
public class FaixaHonorario {
    private Long id;
    private String nome;
    private BigDecimal faturamentoMinimo;
    private BigDecimal faturamentoMaximo;
    private BigDecimal valorHonorario;
    private BigDecimal percentualAlertaPreventivo;
    private Boolean ativa;
}
```

### FaturamentoCliente

```java
public class FaturamentoCliente {
    private Long id;
    private Long clienteId;
    private Integer mes;
    private Integer ano;
    private BigDecimal valorFaturado;
    private LocalDate dataReferencia;
}
```

### AlertaReajuste

```java
public class AlertaReajuste {
    private Long id;
    private Long clienteId;
    private Long faixaAtualId;
    private Long faixaSugeridaId;
    private BigDecimal faturamentoAtual;
    private BigDecimal honorarioAtual;
    private BigDecimal honorarioSugerido;
    private String tipoAlerta;
    private String status;
    private LocalDateTime criadoEm;
}
```

### Auditoria

```java
public class Auditoria {
    private Long id;
    private Long usuarioId;
    private String acao;
    private String modulo;
    private String entidade;
    private Long entidadeId;
    private String valorAnterior;
    private String valorNovo;
    private LocalDateTime dataHora;
    private String ip;
}
```

---

## 23. Endpoints iniciais da API

### Autenticação

```txt
POST /api/auth/login
POST /api/auth/logout
GET  /api/auth/me
```

### Clientes

```txt
GET    /api/clientes
GET    /api/clientes/{id}
POST   /api/clientes
PUT    /api/clientes/{id}
DELETE /api/clientes/{id}
GET    /api/clientes/{id}/analise
GET    /api/clientes/{id}/historico-faturamento
```

### Faixas

```txt
GET    /api/faixas
POST   /api/faixas
PUT    /api/faixas/{id}
DELETE /api/faixas/{id}
```

### Alertas

```txt
GET /api/alertas
GET /api/alertas/resumo
GET /api/alertas/criticos
GET /api/alertas/preventivos
PUT /api/alertas/{id}/marcar-como-analisado
```

### Dashboards

```txt
GET /api/dashboard/resumo-geral
GET /api/dashboard/clientes-reajuste-pendente
GET /api/dashboard/clientes-por-faixa
GET /api/dashboard/evolucao-faturamento
GET /api/dashboard/honorario-atual-vs-recomendado
GET /api/dashboard/previsao-receita
GET /api/dashboard/comparativo-periodos
```

### Relatórios

```txt
GET /api/relatorios/reajustes/excel
GET /api/relatorios/reajustes/csv
GET /api/relatorios/reajustes/pdf
GET /api/relatorios/receita-potencial/excel
GET /api/relatorios/comparativo-periodos/pdf
```

### Auditoria

```txt
GET /api/auditoria
GET /api/auditoria/usuario/{id}
GET /api/auditoria/entidade/{entidade}/{id}
```

---

## 24. Frontend — React com Vite

O frontend será criado com React, TypeScript e Vite.

### Criar projeto frontend

Dentro da pasta raiz:

```bash
cd frontend
npm create vite@latest faturamento-web
```

Escolher:

```txt
React
TypeScript
```

Depois executar:

```bash
cd faturamento-web
npm install
npm install axios react-router-dom recharts lucide-react
```

---

## 25. Estrutura do frontend

Dentro de:

```txt
frontend/faturamento-web/src/
```

Criar:

```txt
src/
│
├── main.tsx
├── App.tsx
│
├── assets/
│
├── components/
│   ├── layout/
│   │   ├── Sidebar.tsx
│   │   ├── Header.tsx
│   │   └── PageContainer.tsx
│   │
│   ├── cards/
│   │   ├── MetricCard.tsx
│   │   └── AlertCard.tsx
│   │
│   ├── charts/
│   │   ├── RevenueChart.tsx
│   │   ├── ClientsByRangeChart.tsx
│   │   └── PeriodComparisonChart.tsx
│   │
│   └── ui/
│
├── pages/
│   ├── LoginPage.tsx
│   ├── DashboardPage.tsx
│   ├── ClientesPage.tsx
│   ├── ClienteAnalisePage.tsx
│   ├── FaixasPage.tsx
│   ├── AlertasPage.tsx
│   ├── RelatoriosPage.tsx
│   ├── AuditoriaPage.tsx
│   └── ConfiguracoesPage.tsx
│
├── services/
│   ├── api.ts
│   ├── authService.ts
│   ├── clienteService.ts
│   ├── dashboardService.ts
│   ├── faixaService.ts
│   ├── alertaService.ts
│   └── relatorioService.ts
│
├── routes/
│   └── AppRoutes.tsx
│
├── hooks/
│   ├── useAuth.ts
│   └── usePermissions.ts
│
├── contexts/
│   └── AuthContext.tsx
│
├── types/
│   ├── Cliente.ts
│   ├── FaixaHonorario.ts
│   ├── Alerta.ts
│   ├── Dashboard.ts
│   └── Usuario.ts
│
├── utils/
│   ├── formatCurrency.ts
│   ├── formatDate.ts
│   └── permissions.ts
│
└── styles/
    └── global.css
```

---

## 26. Telas iniciais do frontend

### LoginPage

Tela simples com:

- campo de usuário ou e-mail;
- campo de senha;
- botão entrar;
- mensagem de erro.

### DashboardPage

Tela principal do sistema.

Deve conter cards como:

- clientes monitorados;
- alertas preventivos;
- alertas críticos;
- receita potencial com reajuste;
- clientes acima da faixa.

Deve conter gráficos como:

- faturamento por período;
- clientes por faixa;
- honorário atual x recomendado;
- comparativo com período anterior.

### ClientesPage

Tabela com:

- cliente;
- CNPJ;
- responsável;
- faturamento atual;
- honorário atual;
- faixa atual;
- status;
- ação para analisar.

### ClienteAnalisePage

Tela mais importante do sistema.

Deve exibir:

- dados do cliente;
- faturamento atual;
- histórico de faturamento;
- faixa atual;
- faixa sugerida;
- honorário atual;
- honorário sugerido;
- diferença mensal;
- diferença anual estimada;
- alertas;
- histórico.

### FaixasPage

Tela para cadastro e edição de faixas.

Campos:

- nome da faixa;
- faturamento mínimo;
- faturamento máximo;
- honorário sugerido;
- percentual de alerta preventivo;
- ativo/inativo.

### AlertasPage

Lista de alertas com:

- cliente;
- tipo do alerta;
- faturamento atual;
- limite da faixa;
- valor excedido;
- status;
- data.

### RelatoriosPage

Tela com botões de exportação:

- Excel;
- CSV;
- PDF.

### AuditoriaPage

Tabela com:

- usuário;
- ação;
- módulo;
- data/hora;
- valor anterior;
- valor novo.

---

## 27. Modelo de permissões

Permissões sugeridas:

```txt
PERMISSAO_DASHBOARD_VISUALIZAR
PERMISSAO_CLIENTES_VISUALIZAR
PERMISSAO_CLIENTES_CADASTRAR
PERMISSAO_CLIENTES_EDITAR
PERMISSAO_FAIXAS_VISUALIZAR
PERMISSAO_FAIXAS_CADASTRAR
PERMISSAO_FAIXAS_EDITAR
PERMISSAO_ALERTAS_VISUALIZAR
PERMISSAO_RELATORIOS_EXPORTAR
PERMISSAO_AUDITORIA_VISUALIZAR
PERMISSAO_USUARIOS_GERENCIAR
```

Esse modelo permite combinar módulos visíveis para diferentes perfis.

---

## 28. Banco de dados próprio do sistema

Tabelas iniciais sugeridas:

```txt
usuarios
perfis
permissoes
perfil_permissoes
usuario_perfis
clientes
faixas_honorario
alertas_reajuste
auditoria
configuracoes_sistema
relatorios_gerados
```

### Observação

Mesmo que clientes e faturamento venham do banco da empresa, pode ser necessário manter uma tabela interna de clientes para relacionar:

- responsável interno;
- faixa atual;
- permissões;
- status;
- configurações específicas.

---

## 29. README inicial do projeto

Criar um arquivo `README.md` na raiz com o seguinte conteúdo:

```md
# Gestão de Faturamento e Honorários

Sistema web interno para monitoramento de faturamento de clientes e identificação de oportunidades de reajuste de honorários contábeis.

## Objetivo

Automatizar a análise de faturamento dos clientes do escritório contábil, identificando clientes próximos ou acima das faixas de cobrança configuradas.

## Tecnologias

- Frontend: React + TypeScript + Vite
- Backend: Java + Spring Boot
- Banco de dados: PostgreSQL
- Integração: Banco de dados interno da empresa
- Relatórios: PDF, Excel e CSV

## Módulos

- Autenticação
- Usuários e permissões
- Clientes
- Faixas de honorários
- Faturamento
- Alertas de reajuste
- Dashboards
- Relatórios
- Auditoria

## Escopo inicial

- Login de administrador
- Dashboard geral
- Cadastro de clientes
- Cadastro de faixas
- Tela de análise do cliente
- Alertas preventivos e críticos
- Exportação em Excel
```

---

## 30. .gitignore recomendado

Criar um arquivo `.gitignore` na raiz:

```gitignore
# Node
node_modules/
dist/
build/
.env
.env.local
.env.production

# Java / Maven
target/
*.class
*.jar
*.war

# IntelliJ
.idea/
*.iml

# VS Code
.vscode/

# Logs
*.log

# OS
.DS_Store
Thumbs.db

# Database
*.db
*.sqlite

# Backups
*.bak
*.backup

# Secrets
application-prod.properties
application-prod.yml
```

---

## 31. Ordem recomendada de desenvolvimento

### Backend

1. Criar projeto Spring Boot.
2. Configurar banco PostgreSQL.
3. Criar entidade Cliente.
4. Criar entidade FaixaHonorario.
5. Criar entidade FaturamentoCliente com dados mockados.
6. Criar entidade AlertaReajuste.
7. Criar endpoints de clientes.
8. Criar endpoints de faixas.
9. Criar serviço de cálculo de alertas.
10. Criar endpoints de dashboard.
11. Criar exportação Excel.
12. Criar auditoria.
13. Criar autenticação e permissões completas.
14. Criar integração com banco da empresa.

### Frontend

1. Criar projeto React com Vite.
2. Criar layout base.
3. Criar tela de login mockada.
4. Criar dashboard com dados mockados.
5. Criar página de clientes.
6. Criar página de análise do cliente.
7. Criar página de faixas.
8. Criar página de alertas.
9. Criar página de relatórios.
10. Criar página de auditoria.
11. Integrar com backend.
12. Implementar autenticação.
13. Implementar permissões por módulo.

---

## 32. Prioridade para protótipo visual

Para impressionar na apresentação e na entrevista, priorizar o frontend mockado.

A tela mais importante é a **Análise do Cliente**.

Ela deve mostrar claramente:

- faturamento atual;
- faixa atual;
- faixa sugerida;
- honorário atual;
- honorário recomendado;
- diferença mensal;
- diferença anual estimada;
- alerta preventivo ou crítico;
- histórico de faturamento.

Essa tela evidencia a dor principal: o escritório perde receita quando o cliente ultrapassa a faixa e ninguém percebe.

---

## 33. Nomes sugeridos para o projeto

### Nome técnico do repositório

```txt
gestao-faturamento-honorarios
```

### Nome formal para documentação

```txt
Sistema de Gestão de Faturamento e Honorários
```

### Nomes comerciais provisórios

```txt
Honorix
FaturaCheck
ContaView
```

Para documentação acadêmica, usar preferencialmente:

```txt
Sistema de Gestão de Faturamento e Honorários
```

---

## 34. Pontos pendentes para a entrevista

Durante a entrevista do Pedido do Investidor, validar:

1. Quais são exatamente as faixas de faturamento e honorários?
2. A regra usa faturamento mensal, acumulado ou média dos últimos meses?
3. Com quantos por cento antes do limite o alerta preventivo deve aparecer?
4. Quem deve analisar o alerta depois que ele aparece?
5. Existe aprovação de gestor ou apenas análise do responsável?
6. O reajuste terá status como pendente, analisado, aprovado ou ignorado?
7. Quais dados existem hoje no banco da empresa?
8. Qual é o tipo de banco utilizado pela empresa?
9. O banco atual permite consulta em tempo real sem prejudicar o sistema principal?
10. O sistema ficará em servidor interno ou na nuvem?
11. Quais relatórios são obrigatórios na primeira entrega?
12. Quais permissões cada perfil deve ter?
13. Como deve funcionar o fluxo após um alerta crítico?
14. Quem pode cadastrar ou alterar faixas?
15. Como será validada a receita potencial estimada?

---

## 35. Resumo final para o Codex

Desenvolver um sistema web chamado **Sistema de Gestão de Faturamento e Honorários**.

O sistema deve ser desenvolvido com:

- Frontend em React + TypeScript + Vite.
- Backend em Java + Spring Boot.
- Banco próprio recomendado: PostgreSQL.
- Integração com banco de dados da empresa.

O sistema deve monitorar faturamento de clientes, comparar com faixas configuráveis de honorários, gerar alertas preventivos e críticos, exibir dashboards gerenciais, permitir análise individual do cliente, exportar relatórios e manter auditoria completa.

O sistema não deve executar reajustes automaticamente. Ele apenas informa, sinaliza, calcula e registra histórico.

O MVP deve conter:

- login de administrador;
- dashboard geral;
- cadastro de clientes;
- cadastro de faixas;
- tela de análise do cliente;
- alertas;
- exportação Excel;
- estrutura preparada para auditoria e permissões.
