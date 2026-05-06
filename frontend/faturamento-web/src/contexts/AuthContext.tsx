import {
  createContext,
  startTransition,
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
    const session = window.sessionStorage.getItem(SESSION_KEY);

    if (session) {
      startTransition(() => {
        setUser(JSON.parse(session) as Usuario);
      });
    }

    setLoading(false);
  }, []);

  const signIn = async (login: string, senha: string) => {
    const authenticatedUser = await authService.login({ login, senha });
    window.sessionStorage.setItem(SESSION_KEY, JSON.stringify(authenticatedUser));

    startTransition(() => {
      setUser(authenticatedUser);
    });
  };

  const signOut = () => {
    window.sessionStorage.removeItem(SESSION_KEY);

    startTransition(() => {
      setUser(null);
    });
  };

  const value: AuthContextValue = {
    user,
    loading,
    signIn,
    signOut,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
