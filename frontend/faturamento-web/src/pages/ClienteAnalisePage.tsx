import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { RevenueChart } from "../components/charts/RevenueChart";
import { PageContainer } from "../components/layout/PageContainer";
import { clienteService } from "../services/clienteService";
import type { ChartPoint } from "../types/Dashboard";
import type { ClienteAnalise } from "../types/Cliente";
import { formatCurrency } from "../utils/formatCurrency";

export function ClienteAnalisePage() {
  const { id } = useParams();
  const [cliente, setCliente] = useState<ClienteAnalise | null>(null);

  useEffect(() => {
    if (!id) {
      return;
    }

    void clienteService.getClienteAnalise(Number(id)).then(setCliente);
  }, [id]);

  if (!cliente) {
    return <div className="screen-center">Carregando analise do cliente...</div>;
  }

  const historico: ChartPoint[] = cliente.historico.map((item) => ({
    label: new Date(item.dataReferencia).toLocaleDateString("pt-BR", {
      month: "2-digit",
      year: "numeric",
    }),
    value: item.valorFaturado,
  }));

  return (
    <PageContainer
      title={`Analise de ${cliente.nome}`}
      subtitle="Tela-chave do MVP para evidenciar perda de receita e oportunidade de reajuste."
    >
      <section
        className={`hero-analysis ${
          cliente.tipoAlerta === "CRITICO" ? "critical" : "preventive"
        }`}
      >
        <div>
          <p className="page-eyebrow">Cliente em observacao</p>
          <h3>{cliente.nome}</h3>
          <p>
            {cliente.cnpj} • {cliente.regimeTributario} • Responsavel:{" "}
            {cliente.responsavelInterno}
          </p>
        </div>

        <div className="hero-badge-group">
          <span className="status-pill large">{cliente.tipoAlerta}</span>
          <span className="hero-kpi">
            Uso da faixa atual: {cliente.percentualUsoFaixaAtual.toFixed(1)}%
          </span>
        </div>
      </section>

      <div className="metrics-grid">
        <div className="detail-card">
          <span>Faturamento atual</span>
          <strong>{formatCurrency(cliente.faturamentoAtual)}</strong>
          <small>Limite contratado: {formatCurrency(cliente.limiteFaixaAtual)}</small>
        </div>
        <div className="detail-card">
          <span>Faixa atual</span>
          <strong>{cliente.faixaAtual}</strong>
          <small>Faixa sugerida: {cliente.faixaSugerida}</small>
        </div>
        <div className="detail-card">
          <span>Honorario atual</span>
          <strong>{formatCurrency(cliente.honorarioAtual)}</strong>
          <small>Sugerido: {formatCurrency(cliente.honorarioSugerido)}</small>
        </div>
        <div className="detail-card">
          <span>Ganho potencial</span>
          <strong>{formatCurrency(cliente.diferencaMensal)}</strong>
          <small>{formatCurrency(cliente.diferencaAnualEstimada)} ao ano</small>
        </div>
      </div>

      <div className="content-grid two-columns">
        <RevenueChart data={historico} title="Historico de faturamento" />

        <section className="panel-card emphasis-panel">
          <div className="chart-header">
            <h3>Leitura executiva</h3>
          </div>

          <div className="analysis-summary">
            <p>
              O cliente esta atualmente em <strong>{cliente.faixaAtual}</strong>,
              mas o faturamento mais recente ja aponta para{" "}
              <strong>{cliente.faixaSugerida}</strong>.
            </p>
            <p>
              Se o reajuste fosse aplicado hoje, o honorario sairia de{" "}
              <strong>{formatCurrency(cliente.honorarioAtual)}</strong> para{" "}
              <strong>{formatCurrency(cliente.honorarioSugerido)}</strong>.
            </p>
            <p>
              Isso representa uma diferenca mensal de{" "}
              <strong>{formatCurrency(cliente.diferencaMensal)}</strong> e uma
              projeção anual de{" "}
              <strong>{formatCurrency(cliente.diferencaAnualEstimada)}</strong>.
            </p>
          </div>
        </section>
      </div>
    </PageContainer>
  );
}
