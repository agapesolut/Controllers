import type { Alerta } from "../types/Alerta";
import { api, unwrapApiData } from "./api";

export const alertaService = {
  async getAlertas(): Promise<Alerta[]> {
    return await unwrapApiData<Alerta[]>(api.get("/alertas"));
  },
};
