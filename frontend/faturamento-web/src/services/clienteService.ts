import {
  clientesAnaliseMock,
  clientesMock,
  mockDelay,
} from "../data/mockData";
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
    try {
      const clientes = await unwrapApiData<ClienteResumoApi[]>(
        api.get("/clientes"),
      );

      return clientes.map((cliente) => ({
        ...cliente,
        tipoAlerta: cliente.statusAlerta,
      }));
    } catch {
      return mockDelay(clientesMock);
    }
  },

  async getClienteAnalise(id: number): Promise<ClienteAnalise> {
    try {
      const cliente = await unwrapApiData<ClienteAnalise>(
        api.get(`/clientes/${id}/analise`),
      );
      return cliente;
    } catch {
      const cliente = clientesAnaliseMock.find((item) => item.id === id);

      if (!cliente) {
        throw new Error("Cliente nao encontrado.");
      }

      return mockDelay(cliente);
    }
  },
};
