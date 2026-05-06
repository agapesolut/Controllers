import { useEffect, useState } from "react";
import { PageContainer } from "../components/layout/PageContainer";
import { faixaService } from "../services/faixaService";
import type { FaixaHonorario } from "../types/FaixaHonorario";
import { formatCurrency } from "../utils/formatCurrency";

export function FaixasPage() {
  const [faixas, setFaixas] = useState<FaixaHonorario[]>([]);

  useEffect(() => {
    void faixaService.getFaixas().then(setFaixas);
  }, []);

  return (
    <PageContainer
      title="Faixas de honorarios"
      subtitle="Base configuravel para identificar clientes proximos ou acima do escopo contratado."
      actions={<button className="primary-button">Nova faixa</button>}
    >
      <section className="panel-card">
        <div className="table-wrap">
          <table>
            <thead>
              <tr>
                <th>Faixa</th>
                <th>Faturamento minimo</th>
                <th>Faturamento maximo</th>
                <th>Honorario sugerido</th>
                <th>Alerta preventivo</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {faixas.map((faixa) => (
                <tr key={faixa.id}>
                  <td>{faixa.nome}</td>
                  <td>{formatCurrency(faixa.faturamentoMinimo)}</td>
                  <td>{formatCurrency(faixa.faturamentoMaximo)}</td>
                  <td>{formatCurrency(faixa.valorHonorario)}</td>
                  <td>{faixa.percentualAlertaPreventivo}%</td>
                  <td>
                    <span className={`status-pill ${faixa.ativa ? "normal" : "critical"}`}>
                      {faixa.ativa ? "ATIVA" : "INATIVA"}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </section>
    </PageContainer>
  );
}
