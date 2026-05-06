import { dashboardMock, mockDelay } from "../data/mockData";
import type {
  ChartPoint,
  DashboardPayload,
  DashboardResumo,
  HonorarioComparacao,
} from "../types/Dashboard";
import { api, unwrapApiData } from "./api";

export const dashboardService = {
  async getDashboard(): Promise<DashboardPayload> {
    try {
      const [
        resumo,
        clientesPorFaixa,
        evolucaoFaturamento,
        comparativoPeriodos,
        previsaoReceita,
        honorarios,
      ] = await Promise.all([
        unwrapApiData<DashboardResumo>(api.get("/dashboard/resumo-geral")),
        unwrapApiData<ChartPoint[]>(api.get("/dashboard/clientes-por-faixa")),
        unwrapApiData<ChartPoint[]>(api.get("/dashboard/evolucao-faturamento")),
        unwrapApiData<ChartPoint[]>(api.get("/dashboard/comparativo-periodos")),
        unwrapApiData<ChartPoint[]>(api.get("/dashboard/previsao-receita")),
        unwrapApiData<HonorarioComparacao[]>(
          api.get("/dashboard/honorario-atual-vs-recomendado"),
        ),
      ]);

      return {
        resumo,
        clientesPorFaixa,
        evolucaoFaturamento,
        comparativoPeriodos,
        previsaoReceita,
        honorarios,
      };
    } catch {
      return mockDelay(dashboardMock);
    }
  },
};
