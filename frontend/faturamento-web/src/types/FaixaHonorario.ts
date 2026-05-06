export interface FaixaHonorario {
  id: number;
  nome: string;
  faturamentoMinimo: number;
  faturamentoMaximo: number;
  valorHonorario: number;
  percentualAlertaPreventivo: number;
  ativa: boolean;
}
