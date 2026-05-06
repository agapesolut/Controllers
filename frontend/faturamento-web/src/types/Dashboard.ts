export interface DashboardResumo {
  clientesMonitorados: number;
  alertasPreventivos: number;
  alertasCriticos: number;
  receitaPotencialMensal: number;
  clientesAcimaDaFaixa: number;
}

export interface ChartPoint {
  label: string;
  value: number;
}

export interface HonorarioComparacao {
  cliente: string;
  honorarioAtual: number;
  honorarioRecomendado: number;
  diferencaMensal: number;
}

export interface DashboardPayload {
  resumo: DashboardResumo;
  clientesPorFaixa: ChartPoint[];
  evolucaoFaturamento: ChartPoint[];
  comparativoPeriodos: ChartPoint[];
  previsaoReceita: ChartPoint[];
  honorarios: HonorarioComparacao[];
}
