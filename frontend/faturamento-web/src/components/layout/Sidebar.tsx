import {
  BellRing,
  Building2,
  FileSpreadsheet,
  LayoutDashboard,
  SearchCheck,
  Settings2,
  ShieldCheck,
} from "lucide-react";
import { NavLink } from "react-router-dom";
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
    label: "Relatorios",
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
    label: "Configuracoes",
    icon: Settings2,
    permission: permissions.usuariosGerenciar,
  },
];

export function Sidebar() {
  const { hasPermission } = usePermissions();

  return (
    <aside className="sidebar">
      <div className="sidebar-brand">
        <p className="sidebar-eyebrow">AGAPE CONTROLLERS</p>
        <h1>Honorarios em foco</h1>
        <span>Monitoramento de faturamento e reajustes</span>
      </div>

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
              >
                <Icon size={18} />
                <span>{item.label}</span>
              </NavLink>
            );
          })}
      </nav>
    </aside>
  );
}
