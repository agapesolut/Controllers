import {
  BellRing,
  Building2,
  FileSpreadsheet,
  LayoutDashboard,
  LogOut,
  SearchCheck,
  Settings2,
  ShieldCheck,
} from "lucide-react";
import { NavLink } from "react-router-dom";
import { useAuth } from "../../hooks/useAuth";
import { usePermissions } from "../../hooks/usePermissions";
import { permissions } from "../../utils/permissions";

const items = [
  {
    to: "/",
    label: "Dashboard",
    icon: LayoutDashboard,
    permission: permissions.dashboardVisualizar,
  },
  {
    to: "/clientes",
    label: "Clientes",
    icon: Building2,
    permission: permissions.clientesVisualizar,
  },
  {
    to: "/faixas",
    label: "Faixas",
    icon: SearchCheck,
    permission: permissions.faixasVisualizar,
  },
  {
    to: "/alertas",
    label: "Alertas",
    icon: BellRing,
    permission: permissions.alertasVisualizar,
  },
  {
    to: "/relatorios",
    label: "Relatórios",
    icon: FileSpreadsheet,
    permission: permissions.relatoriosExportar,
  },
  {
    to: "/auditoria",
    label: "Auditoria",
    icon: ShieldCheck,
    permission: permissions.auditoriaVisualizar,
  },
  {
    to: "/configuracoes",
    label: "Configurações",
    icon: Settings2,
    permission: permissions.usuariosGerenciar,
  },
];

export function Sidebar() {
  const { hasPermission } = usePermissions();
  const { signOut } = useAuth();

  return (
    <aside className="sidebar">
      {/* Brand / Logo */}
      <div className="sidebar-brand">
        <div className="sidebar-logo" aria-hidden="true">
          <div className="bar bar-1" />
          <div className="bar bar-2" />
          <div className="bar bar-3" />
        </div>
        <div className="sidebar-brand-text">
          <strong>AGAPE</strong>
          <small>Controllers</small>
        </div>
      </div>

      {/* Navigation */}
      <nav className="sidebar-nav">
        {items
          .filter((item) => hasPermission(item.permission))
          .map((item) => {
            const Icon = item.icon;
            return (
              <NavLink
                key={item.to}
                to={item.to}
                end={item.to === "/"}
                className={({ isActive }) =>
                  isActive ? "nav-link active" : "nav-link"
                }
                title={item.label}
              >
                <span className="nav-link-icon">
                  <Icon size={18} />
                </span>
                <span className="nav-link-label">{item.label}</span>
              </NavLink>
            );
          })}
      </nav>

      {/* Footer: Sign out */}
      <div className="sidebar-footer">
        <button
          type="button"
          className="nav-link"
          onClick={signOut}
          title="Sair"
          style={{ width: "100%", background: "none", border: "none", cursor: "pointer" }}
        >
          <span className="nav-link-icon">
            <LogOut size={18} />
          </span>
          <span className="nav-link-label">Sair</span>
        </button>
      </div>
    </aside>
  );
}
