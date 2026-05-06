import { api, unwrapApiData } from "./api";
import type { Usuario } from "../types/Usuario";

interface LoginPayload {
  login: string;
  senha: string;
}

export const authService = {
  async login(payload: LoginPayload): Promise<Usuario> {
    return await unwrapApiData<Usuario>(api.post("/auth/login", payload));
  },

  async me(): Promise<Usuario> {
    return await unwrapApiData<Usuario>(api.get("/auth/me"));
  },
};
