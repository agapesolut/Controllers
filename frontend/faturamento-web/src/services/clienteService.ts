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

export const clienteService = {
  async getClientes(): Promise<ClienteResumo[]> {
    const clientes = await unwrapApiData<ClienteResumoApi[]>(
      api.get("/clientes"),
    );

    return clientes.map((cliente) => ({
      ...cliente,
      tipoAlerta: cliente.statusAlerta,
    }));
  },

  async getClienteAnalise(id: number): Promise<ClienteAnalise> {
    const cliente = await unwrapApiData<ClienteAnalise>(
      api.get(`/clientes/${id}/analise`),
    );
    return cliente;
  },
};
