import { useEffect, useState } from "react";
import { AlertCard } from "../components/cards/AlertCard";
import { MetricCard } from "../components/cards/MetricCard";
import { ClientsByRangeChart } from "../components/charts/ClientsByRangeChart";
import { PeriodComparisonChart } from "../components/charts/PeriodComparisonChart";
import { RevenueChart } from "../components/charts/RevenueChart";
import { PageContainer } from "../components/layout/PageContainer";
import { alertaService } from "../services/alertaService";
import { dashboardService } from "../services/dashboardService";
import type { Alerta } from "../types/Alerta";
import type { DashboardPayload } from "../types/Dashboard";
import { formatCurrency } from "../utils/formatCurrency";

export function DashboardPage() {
  const [dashboard, setDashboard] = useState<DashboardPayload | null>(null);
  const [alertas, setAlertas] = useState<Alerta[]>([]);

  useEffect(() => {
    void Promise.all([dashboardService.getDashboard(), alertaService.getAlertas()])
      .then(([dashboardData, alertasData]) => {
        setDashboard(dashboardData);
        setAlertas(alertasData);
      });
  }, []);

  if (!dashboard) {
    return <div className="screen-center">Carregando dashboard...</div>;
  }

  return (
    <PageContainer
      title="Dashboard principal"
      subtitle="Visao consolidada dos clientes monitorados, alertas ativos e potencial de receita."
    >
      <div className="metrics-grid">
        <MetricCard
          label="Clientes monitorados"
          value={String(dashboard.resumo.clientesMonitorados)}
          hint="Base acompanhada no MVP"
        />
        <MetricCard
          label="Alertas preventivos"
          value={String(dashboard.resumo.alertasPreventivos)}
          hint="Clientes perto do teto contratado"
          tone="warning"
        />
        <MetricCard
          label="Alertas criticos"
          value={String(dashboard.resumo.alertasCriticos)}
          hint="Clientes acima da faixa atual"
          tone="danger"
        />
        <MetricCard
          label="Receita potencial"
          value={formatCurrency(dashboard.resumo.receitaPotencialMensal)}
          hint="Estimativa de ganho mensal"
          tone="success"
        />
      </div>

      <div className="content-grid two-columns">
        <RevenueChart
          data={dashboard.evolucaoFaturamento}
          title="Evolucao do faturamento consolidado"
        />
        <ClientsByRangeChart data={dashboard.clientesPorFaixa} />
      </div>

      <div className="content-grid two-columns">
        <PeriodComparisonChart data={dashboard.comparativoPeriodos} />

        <section className="panel-card">
          <div className="chart-header">
            <h3>Previsao de receita</h3>
          </div>

          <div className="forecast-list">
            {dashboard.previsaoReceita.map((item) => (
              <div key={item.label} className="forecast-item">
                <span>{item.label}</span>
                <strong>{formatCurrency(item.value)}</strong>
              </div>
            ))}
          </div>
        </section>
      </div>

      <section className="panel-card">
        <div className="chart-header">
          <h3>Honorario atual x recomendado</h3>
        </div>

        <div className="table-wrap">
          <table>
            <thead>
              <tr>
                <th>Cliente</th>
                <th>Atual</th>
                <th>Recomendado</th>
                <th>Diferenca mensal</th>
              </tr>
            </thead>
            <tbody>
              {dashboard.honorarios.map((item) => (
                <tr key={item.cliente}>
                  <td>{item.cliente}</td>
                  <td>{formatCurrency(item.honorarioAtual)}</td>
                  <td>{formatCurrency(item.honorarioRecomendado)}</td>
                  <td>{formatCurrency(item.diferencaMensal)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </section>

      <section className="panel-card">
        <div className="chart-header">
          <h3>Alertas que pedem acao imediata</h3>
        </div>

        <div className="alert-grid">
          {alertas.map((alerta) => (
            <AlertCard key={alerta.id} alerta={alerta} />
          ))}
        </div>
      </section>
    </PageContainer>
  );
}
