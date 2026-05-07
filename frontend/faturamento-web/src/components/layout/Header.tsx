import { useAuth } from "../../hooks/useAuth";

export function Header() {
  const { user } = useAuth();

  const initials = user?.nome
    ? user.nome
        .split(" ")
        .slice(0, 2)
        .map((n: string) => n[0])
        .join("")
        .toUpperCase()
    : "?";

  return (
    <header className="topbar">
      <div className="topbar-left">
        <p className="topbar-label">Painel interno</p>
        <span className="topbar-date">
          {new Date().toLocaleDateString("pt-BR", {
            weekday: "long",
            day: "numeric",
            month: "long",
            year: "numeric",
          })}
        </span>
      </div>

      <div className="topbar-actions">
        <div className="user-chip">
          <div className="user-avatar" aria-hidden="true">
            {initials}
          </div>
          <div className="user-chip-info">
            <span>{user?.nome}</span>
            <small>{user?.perfil}</small>
          </div>
        </div>
      </div>
    </header>
  );
}
