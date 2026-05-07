import { api, unwrapApiData } from "./api";
import type { ClienteAnalise, ClienteResumo } from "../types/Cliente";

interface ClienteResumoApi {
  id: number;
  nome: string;
  cnpj: string;
  responsavelInterno: string;
  faturamentoAtual: number;
  honorarioAtual: number;
  faixaAtual: string;
  statusAlerta: "NORMAL" | "PREVENTIVO" | "CRITICO";
}

interface ClienteAnaliseApi extends ClienteResumoApi {
  regimeTributario: string;
  faixaSugerida: string;
  honorarioSugerido: number;
  diferencaMensal: number;
  diferencaAnualEstimada: number;
  limiteFaixaAtual: number;
  percentualUsoFaixaAtual: number;
  historico: {
    dataReferencia: string;
    valorFaturado: number;
  }[];
}

const mapToClienteResumo = (apiData: ClienteResumoApi): ClienteResumo => ({
  id: apiData.id,
  nome: apiData.nome,
  cnpj: apiData.cnpj,
  responsavelInterno: apiData.responsavelInterno,
  faturamentoAtual: apiData.faturamentoAtual,
  honorarioAtual: apiData.honorarioAtual,
  faixaAtual: apiData.faixaAtual,
  tipoAlerta: apiData.statusAlerta,
});

export const clienteService = {
  async getClientes(): Promise<ClienteResumo[]> {
    const data = await unwrapApiData<ClienteResumoApi[]>(api.get("/clientes"));
    return data.map(mapToClienteResumo);
  },

  async getClienteAnalise(id: number): Promise<ClienteAnalise> {
    const data = await unwrapApiData<ClienteAnaliseApi>(
      api.get(`/clientes/${id}/analise`),
    );
    
    return {
      ...mapToClienteResumo(data),
      regimeTributario: data.regimeTributario,
      faixaSugerida: data.faixaSugerida,
      honorarioSugerido: data.honorarioSugerido,
      diferencaMensal: data.diferencaMensal,
      diferencaAnualEstimada: data.diferencaAnualEstimada,
      limiteFaixaAtual: data.limiteFaixaAtual,
      percentualUsoFaixaAtual: data.percentualUsoFaixaAtual,
      historico: data.historico,
    };
  },

  async criarCliente(clienteData: any): Promise<ClienteResumo> {
    const payload = { ...clienteData };
    // Converte string vazia para null para o backend aceitar faturamento opcional
    if (payload.faturamentoAtual === "") payload.faturamentoAtual = null;
    
    const data = await unwrapApiData<ClienteResumoApi>(
      api.post("/clientes", payload),
    );
    return mapToClienteResumo(data);
  },

  async atualizarCliente(id: number, clienteData: any): Promise<ClienteResumo> {
    const payload = { ...clienteData };
    // Converte string vazia para null para o backend aceitar faturamento opcional
    if (payload.faturamentoAtual === "") payload.faturamentoAtual = null;

    const data = await unwrapApiData<ClienteResumoApi>(
      api.put(`/clientes/${id}`, payload),
    );
    return mapToClienteResumo(data);
  },

  async excluirCliente(id: number): Promise<void> {
    await api.delete(`/clientes/${id}`);
  },
};
