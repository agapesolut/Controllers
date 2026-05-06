import { mockDelay, usuarioAdmin } from "../data/mockData";
import type { Usuario } from "../types/Usuario";

interface LoginPayload {
  login: string;
  senha: string;
}

export const authService = {
  async login(payload: LoginPayload): Promise<Usuario> {
    const loginValido =
      payload.login.toLowerCase() === "admin" ||
      payload.login.toLowerCase() === "admin@agape.local";

    const senhaValida = payload.senha === "admin123";

    if (!loginValido || !senhaValida) {
      throw new Error("Credenciais invalidas. Use admin / admin123.");
    }

    return mockDelay(usuarioAdmin);
  },

  async me(): Promise<Usuario> {
    return mockDelay(usuarioAdmin, 80);
  },
};
