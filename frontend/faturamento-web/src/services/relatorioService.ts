import { api, unwrapApiData } from "./api";

export const relatorioService = {
  async exportar(tipo: "excel" | "csv" | "pdf"): Promise<string> {
    return await unwrapApiData<string>(api.get(`/relatorios/reajustes/${tipo}`));
  },
};
