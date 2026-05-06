import { faixasMock, mockDelay } from "../data/mockData";
import type { FaixaHonorario } from "../types/FaixaHonorario";
import { api, unwrapApiData } from "./api";

export const faixaService = {
  async getFaixas(): Promise<FaixaHonorario[]> {
    try {
      return await unwrapApiData<FaixaHonorario[]>(api.get("/faixas"));
    } catch {
      return mockDelay(faixasMock);
    }
  },
};
