import type { Alerta } from "../types/Alerta";
import type { AuditoriaRegistro } from "../types/Auditoria";
import type { ClienteAnalise, ClienteResumo } from "../types/Cliente";
import type { DashboardPayload } from "../types/Dashboard";
import type { FaixaHonorario } from "../types/FaixaHonorario";
import type { Usuario } from "../types/Usuario";

export const usuarioAdmin: Usuario = {
  id: 1,
  nome: "Administrador",
  email: "admin@agape.local",
  perfil: "ADMINISTRADOR",
  permissoes: [
    "PERMISSAO_DASHBOARD_VISUALIZAR",
    "PERMISSAO_CLIENTES_VISUALIZAR",
    "PERMISSAO_CLIENTES_CADASTRAR",
    "PERMISSAO_CLIENTES_EDITAR",
    "PERMISSAO_FAIXAS_VISUALIZAR",
    "PERMISSAO_FAIXAS_CADASTRAR",
    "PERMISSAO_FAIXAS_EDITAR",
    "PERMISSAO_ALERTAS_VISUALIZAR",
    "PERMISSAO_RELATORIOS_EXPORTAR",
    "PERMISSAO_AUDITORIA_VISUALIZAR",
    "PERMISSAO_USUARIOS_GERENCIAR",
  ],
};

export const faixasMock: FaixaHonorario[] = [
  {
    id: 1,
    nome: "Faixa Essencial",
    faturamentoMinimo: 0,
    faturamentoMaximo: 50000,
    valorHonorario: 500,
    percentualAlertaPreventivo: 90,
    ativa: true,
  },
  {
    id: 2,
    nome: "Faixa Crescimento",
    faturamentoMinimo: 50000.01,
    faturamentoMaximo: 100000,
    valorHonorario: 800,
    percentualAlertaPreventivo: 90,
    ativa: true,
  },
  {
    id: 3,
    nome: "Faixa Expansao",
    faturamentoMinimo: 100000.01,
    faturamentoMaximo: 200000,
    valorHonorario: 1200,
    percentualAlertaPreventivo: 90,
    ativa: true,
  },
  {
    id: 4,
    nome: "Faixa Premium",
    faturamentoMinimo: 200000.01,
    faturamentoMaximo: 99999999,
    valorHonorario: 1800,
    percentualAlertaPreventivo: 90,
    ativa: true,
  },
];

export const clientesAnaliseMock: ClienteAnalise[] = [
  {
    id: 1,
    nome: "Mercado Sol Nascente",
    cnpj: "12.345.678/0001-01",
    regimeTributario: "Simples Nacional",
    responsavelInterno: "Ana Paula",
    faturamentoAtual: 51000,
    honorarioAtual: 500,
    faixaAtual: "Faixa Essencial",
    faixaSugerida: "Faixa Crescimento",
    honorarioSugerido: 800,
    diferencaMensal: 300,
    diferencaAnualEstimada: 3600,
    limiteFaixaAtual: 50000,
    percentualUsoFaixaAtual: 102,
    tipoAlerta: "CRITICO",
    historico: [
      { dataReferencia: "2026-01-01", valorFaturado: 42000 },
      { dataReferencia: "2026-02-01", valorFaturado: 45500 },
      { dataReferencia: "2026-03-01", valorFaturado: 49000 },
      { dataReferencia: "2026-04-01", valorFaturado: 51000 },
    ],
  },
  {
    id: 2,
    nome: "Construtora Pilar",
    cnpj: "23.456.789/0001-22",
    regimeTributario: "Lucro Presumido",
    responsavelInterno: "Marcos Lima",
    faturamentoAtual: 98500,
    honorarioAtual: 800,
    faixaAtual: "Faixa Crescimento",
    faixaSugerida: "Faixa Crescimento",
    honorarioSugerido: 800,
    diferencaMensal: 0,
    diferencaAnualEstimada: 0,
    limiteFaixaAtual: 100000,
    percentualUsoFaixaAtual: 98.5,
    tipoAlerta: "PREVENTIVO",
    historico: [
      { dataReferencia: "2026-01-01", valorFaturado: 87000 },
      { dataReferencia: "2026-02-01", valorFaturado: 91000 },
      { dataReferencia: "2026-03-01", valorFaturado: 96000 },
      { dataReferencia: "2026-04-01", valorFaturado: 98500 },
    ],
  },
  {
    id: 3,
    nome: "Clinica Vida Plena",
    cnpj: "34.567.890/0001-33",
    regimeTributario: "Lucro Real",
    responsavelInterno: "Ana Paula",
    faturamentoAtual: 152000,
    honorarioAtual: 1200,
    faixaAtual: "Faixa Expansao",
    faixaSugerida: "Faixa Expansao",
    honorarioSugerido: 1200,
    diferencaMensal: 0,
    diferencaAnualEstimada: 0,
    limiteFaixaAtual: 200000,
    percentualUsoFaixaAtual: 76,
    tipoAlerta: "NORMAL",
    historico: [
      { dataReferencia: "2026-01-01", valorFaturado: 118000 },
      { dataReferencia: "2026-02-01", valorFaturado: 132000 },
      { dataReferencia: "2026-03-01", valorFaturado: 145000 },
      { dataReferencia: "2026-04-01", valorFaturado: 152000 },
    ],
  },
  {
    id: 4,
    nome: "Distribuidora Horizonte",
    cnpj: "45.678.901/0001-44",
    regimeTributario: "Lucro Presumido",
    responsavelInterno: "Juliana Costa",
    faturamentoAtual: 119000,
    honorarioAtual: 800,
    faixaAtual: "Faixa Crescimento",
    faixaSugerida: "Faixa Expansao",
    honorarioSugerido: 1200,
    diferencaMensal: 400,
    diferencaAnualEstimada: 4800,
    limiteFaixaAtual: 100000,
    percentualUsoFaixaAtual: 119,
    tipoAlerta: "CRITICO",
    historico: [
      { dataReferencia: "2026-01-01", valorFaturado: 99000 },
      { dataReferencia: "2026-02-01", valorFaturado: 104000 },
      { dataReferencia: "2026-03-01", valorFaturado: 112000 },
      { dataReferencia: "2026-04-01", valorFaturado: 119000 },
    ],
  },
];

export const clientesMock: ClienteResumo[] = clientesAnaliseMock.map((cliente) => ({
  id: cliente.id,
  nome: cliente.nome,
  cnpj: cliente.cnpj,
  responsavelInterno: cliente.responsavelInterno,
  faturamentoAtual: cliente.faturamentoAtual,
  honorarioAtual: cliente.honorarioAtual,
  faixaAtual: cliente.faixaAtual,
  tipoAlerta: cliente.tipoAlerta,
}));

export const alertasMock: Alerta[] = [
  {
    id: 1,
    clienteNome: "Mercado Sol Nascente",
    tipoAlerta: "CRITICO",
    status: "PENDENTE_ANALISE",
    faturamentoAtual: 51000,
    limiteFaixa: 50000,
    valorExcedido: 1000,
    honorarioAtual: 500,
    honorarioSugerido: 800,
    criadoEm: "2026-05-01T09:05:00",
  },
  {
    id: 2,
    clienteNome: "Construtora Pilar",
    tipoAlerta: "PREVENTIVO",
    status: "PENDENTE_ANALISE",
    faturamentoAtual: 98500,
    limiteFaixa: 100000,
    valorExcedido: 0,
    honorarioAtual: 800,
    honorarioSugerido: 800,
    criadoEm: "2026-05-01T09:10:00",
  },
  {
    id: 4,
    clienteNome: "Distribuidora Horizonte",
    tipoAlerta: "CRITICO",
    status: "PENDENTE_ANALISE",
    faturamentoAtual: 119000,
    limiteFaixa: 100000,
    valorExcedido: 19000,
    honorarioAtual: 800,
    honorarioSugerido: 1200,
    criadoEm: "2026-05-01T09:20:00",
  },
];

export const dashboardMock: DashboardPayload = {
  resumo: {
    clientesMonitorados: 4,
    alertasPreventivos: 1,
    alertasCriticos: 2,
    receitaPotencialMensal: 700,
    clientesAcimaDaFaixa: 2,
  },
  clientesPorFaixa: [
    { label: "Faixa Crescimento", value: 2 },
    { label: "Faixa Expansao", value: 2 },
  ],
  evolucaoFaturamento: [
    { label: "01/2026", value: 346000 },
    { label: "02/2026", value: 372500 },
    { label: "03/2026", value: 402000 },
    { label: "04/2026", value: 420500 },
  ],
  comparativoPeriodos: [
    { label: "Periodo anterior", value: 402000 },
    { label: "Periodo atual", value: 420500 },
  ],
  previsaoReceita: [
    { label: "Potencial mensal", value: 700 },
    { label: "Potencial anual", value: 8400 },
  ],
  honorarios: [
    {
      cliente: "Mercado Sol Nascente",
      honorarioAtual: 500,
      honorarioRecomendado: 800,
      diferencaMensal: 300,
    },
    {
      cliente: "Construtora Pilar",
      honorarioAtual: 800,
      honorarioRecomendado: 800,
      diferencaMensal: 0,
    },
    {
      cliente: "Clinica Vida Plena",
      honorarioAtual: 1200,
      honorarioRecomendado: 1200,
      diferencaMensal: 0,
    },
    {
      cliente: "Distribuidora Horizonte",
      honorarioAtual: 800,
      honorarioRecomendado: 1200,
      diferencaMensal: 400,
    },
  ],
};

export const auditoriaMock: AuditoriaRegistro[] = [
  {
    id: 1,
    usuario: "Administrador",
    acao: "LOGIN",
    modulo: "AUTH",
    entidade: "USUARIO",
    dataHora: "2026-05-01T08:30:00",
    ip: "10.0.0.10",
    contexto: "Acesso ao sistema",
  },
  {
    id: 2,
    usuario: "Administrador",
    acao: "CONSULTA_DASHBOARD",
    modulo: "DASHBOARD",
    entidade: "RESUMO_GERAL",
    dataHora: "2026-05-01T08:31:00",
    ip: "10.0.0.10",
    contexto: "Painel inicial carregado",
  },
  {
    id: 3,
    usuario: "Administrador",
    acao: "ANALISE_CLIENTE",
    modulo: "CLIENTE",
    entidade: "CLIENTE",
    dataHora: "2026-05-01T08:45:00",
    ip: "10.0.0.10",
    contexto: "Cliente acima da faixa analisado",
  },
];

export async function mockDelay<T>(value: T, timeout = 180): Promise<T> {
  return new Promise((resolve) => {
    window.setTimeout(() => resolve(value), timeout);
  });
}
