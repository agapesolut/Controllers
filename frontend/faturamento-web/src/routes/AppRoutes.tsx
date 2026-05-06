import { Suspense, lazy } from "react";
import { Navigate, Outlet, Route, Routes, useLocation } from "react-router-dom";
import { Header } from "../components/layout/Header";
import { Sidebar } from "../components/layout/Sidebar";
import { useAuth } from "../hooks/useAuth";

const LoginPage = lazy(async () => {
  const module = await import("../pages/LoginPage");
  return { default: module.LoginPage };
});

const DashboardPage = lazy(async () => {
  const module = await import("../pages/DashboardPage");
  return { default: module.DashboardPage };
});

const ClientesPage = lazy(async () => {
  const module = await import("../pages/ClientesPage");
  return { default: module.ClientesPage };
});

const ClienteAnalisePage = lazy(async () => {
  const module = await import("../pages/ClienteAnalisePage");
  return { default: module.ClienteAnalisePage };
});

const FaixasPage = lazy(async () => {
  const module = await import("../pages/FaixasPage");
  return { default: module.FaixasPage };
});

const AlertasPage = lazy(async () => {
  const module = await import("../pages/AlertasPage");
  return { default: module.AlertasPage };
});

const RelatoriosPage = lazy(async () => {
  const module = await import("../pages/RelatoriosPage");
  return { default: module.RelatoriosPage };
});

const AuditoriaPage = lazy(async () => {
  const module = await import("../pages/AuditoriaPage");
  return { default: module.AuditoriaPage };
});

const ConfiguracoesPage = lazy(async () => {
  const module = await import("../pages/ConfiguracoesPage");
  return { default: module.ConfiguracoesPage };
});

function ProtectedRoute() {
  const { user, loading } = useAuth();
  const location = useLocation();

  if (loading) {
    return <div className="screen-center">Carregando ambiente...</div>;
  }

  if (!user) {
    return <Navigate to="/login" replace state={{ from: location }} />;
  }

  return <Outlet />;
}

function AppShell() {
  return (
    <div className="app-shell">
      <Sidebar />
      <div className="app-content">
        <Header />
        <main className="app-main">
          <Outlet />
        </main>
      </div>
    </div>
  );
}

function RouteFallback() {
  return <div className="screen-center">Carregando modulo...</div>;
}

export function AppRoutes() {
  return (
    <Routes>
      <Route
        path="/login"
        element={
          <Suspense fallback={<RouteFallback />}>
            <LoginPage />
          </Suspense>
        }
      />

      <Route element={<ProtectedRoute />}>
        <Route element={<AppShell />}>
          <Route
            path="/"
            element={
              <Suspense fallback={<RouteFallback />}>
                <DashboardPage />
              </Suspense>
            }
          />
          <Route
            path="/clientes"
            element={
              <Suspense fallback={<RouteFallback />}>
                <ClientesPage />
              </Suspense>
            }
          />
          <Route
            path="/clientes/:id"
            element={
              <Suspense fallback={<RouteFallback />}>
                <ClienteAnalisePage />
              </Suspense>
            }
          />
          <Route
            path="/faixas"
            element={
              <Suspense fallback={<RouteFallback />}>
                <FaixasPage />
              </Suspense>
            }
          />
          <Route
            path="/alertas"
            element={
              <Suspense fallback={<RouteFallback />}>
                <AlertasPage />
              </Suspense>
            }
          />
          <Route
            path="/relatorios"
            element={
              <Suspense fallback={<RouteFallback />}>
                <RelatoriosPage />
              </Suspense>
            }
          />
          <Route
            path="/auditoria"
            element={
              <Suspense fallback={<RouteFallback />}>
                <AuditoriaPage />
              </Suspense>
            }
          />
          <Route
            path="/configuracoes"
            element={
              <Suspense fallback={<RouteFallback />}>
                <ConfiguracoesPage />
              </Suspense>
            }
          />
        </Route>
      </Route>
    </Routes>
  );
}
