import {
  createContext,
  useEffect,
  useState,
  type ReactNode,
} from "react";
import { authService } from "../services/authService";
import type { Usuario } from "../types/Usuario";

interface AuthContextValue {
  user: Usuario | null;
  loading: boolean;
  signIn: (login: string, senha: string) => Promise<void>;
  signOut: () => void;
}

export const AuthContext = createContext<AuthContextValue | undefined>(
  undefined,
);

const SESSION_KEY = "faturamento-web-session";

export function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<Usuario | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Tenta recuperar do localStorage para persistir após fechar aba,
    // ou sessionStorage se quiser apenas na mesma sessão.
    // Vamos usar localStorage para uma experiência mais fluida.
    const session = window.localStorage.getItem(SESSION_KEY);

    if (session) {
      try {
        setUser(JSON.parse(session) as Usuario);
      } catch (e) {
        window.localStorage.removeItem(SESSION_KEY);
      }
    }

    setLoading(false);
  }, []);

  const signIn = async (login: string, senha: string) => {
    const authenticatedUser = await authService.login({ login, senha });
    window.localStorage.setItem(SESSION_KEY, JSON.stringify(authenticatedUser));
    setUser(authenticatedUser);
  };

  const signOut = () => {
    window.localStorage.removeItem(SESSION_KEY);
    setUser(null);
  };

  const value: AuthContextValue = {
    user,
    loading,
    signIn,
    signOut,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

