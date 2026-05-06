export interface Alerta {
  id: number;
  clienteNome: string;
  tipoAlerta: "PREVENTIVO" | "CRITICO";
  status: "PENDENTE_ANALISE" | "ANALISADO";
  faturamentoAtual: number;
  limiteFaixa: number;
  valorExcedido: number;
  honorarioAtual: number;
  honorarioSugerido: number;
  criadoEm: string;
}
