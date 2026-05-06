import { alertasMock, mockDelay } from "../data/mockData";
import type { Alerta } from "../types/Alerta";
import { api, unwrapApiData } from "./api";

export const alertaService = {
  async getAlertas(): Promise<Alerta[]> {
    try {
      return await unwrapApiData<Alerta[]>(api.get("/alertas"));
    } catch {
      return mockDelay(alertasMock);
    }
  },
};
