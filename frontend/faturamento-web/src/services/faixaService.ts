import type { FaixaHonorario } from "../types/FaixaHonorario";
import { api, unwrapApiData } from "./api";

export const faixaService = {
  async getFaixas(): Promise<FaixaHonorario[]> {
    return await unwrapApiData<FaixaHonorario[]>(api.get("/faixas"));
  },
};
