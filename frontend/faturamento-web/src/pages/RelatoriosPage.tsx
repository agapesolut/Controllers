import { useState } from "react";
import { PageContainer } from "../components/layout/PageContainer";
import { relatorioService } from "../services/relatorioService";

export function RelatoriosPage() {
  const [status, setStatus] = useState("Escolha um formato para simular a exportacao.");

  async function handleExport(tipo: "excel" | "csv" | "pdf") {
    const mensagem = await relatorioService.exportar(tipo);
    setStatus(mensagem);
  }

  return (
    <PageContainer
      title="Relatorios e exportacoes"
      subtitle="Area preparada para relatórios gerenciais de reajuste, comparativos e auditoria."
    >
      <section className="panel-card">
        <div className="button-row">
          <button className="primary-button" onClick={() => void handleExport("excel")}>
            Exportar Excel
          </button>
          <button className="secondary-button" onClick={() => void handleExport("csv")}>
            Exportar CSV
          </button>
          <button className="secondary-button" onClick={() => void handleExport("pdf")}>
            Exportar PDF
          </button>
        </div>

        <p className="helper-text">{status}</p>
      </section>
    </PageContainer>
  );
}
