import { LogOut } from "lucide-react";
import { useAuth } from "../../hooks/useAuth";

export function Header() {
  const { user, signOut } = useAuth();

  return (
    <header className="topbar">
      <div>
        <p className="topbar-label">Painel interno</p>
        <strong>{new Date().toLocaleDateString("pt-BR")}</strong>
      </div>

      <div className="topbar-actions">
        <div className="user-chip">
          <span>{user?.nome}</span>
          <small>{user?.perfil}</small>
        </div>

        <button type="button" className="ghost-button" onClick={signOut}>
          <LogOut size={16} />
          Sair
        </button>
      </div>
    </header>
  );
}
