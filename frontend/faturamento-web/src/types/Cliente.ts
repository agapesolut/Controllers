export interface HistoricoFaturamento {
  dataReferencia: string;
  valorFaturado: number;
}

export interface ClienteResumo {
  id: number;
  nome: string;
  cnpj: string;
  responsavelInterno: string;
  faturamentoAtual: number;
  honorarioAtual: number;
  faixaAtual: string;
  tipoAlerta: "NORMAL" | "PREVENTIVO" | "CRITICO";
}

export interface ClienteAnalise extends ClienteResumo {
  regimeTributario: string;
  faixaSugerida: string;
  honorarioSugerido: number;
  diferencaMensal: number;
  diferencaAnualEstimada: number;
  limiteFaixaAtual: number;
  percentualUsoFaixaAtual: number;
  historico: HistoricoFaturamento[];
}
