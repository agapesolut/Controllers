import type { Alerta } from "../../types/Alerta";
import { formatCurrency } from "../../utils/formatCurrency";
import { formatDate } from "../../utils/formatDate";

export function AlertCard({ alerta }: { alerta: Alerta }) {
  return (
    <article
      className={`alert-card ${
        alerta.tipoAlerta === "CRITICO" ? "critical" : "preventive"
      }`}
    >
      <div className="alert-card-head">
        <span className="badge">{alerta.tipoAlerta}</span>
        <small>{formatDate(alerta.criadoEm)}</small>
      </div>

      <strong>{alerta.clienteNome}</strong>
      <p>
        Faturamento atual de {formatCurrency(alerta.faturamentoAtual)} para um
        limite contratado de {formatCurrency(alerta.limiteFaixa)}.
      </p>

      <div className="alert-card-foot">
        <span>Atual: {formatCurrency(alerta.honorarioAtual)}</span>
        <span>Sugerido: {formatCurrency(alerta.honorarioSugerido)}</span>
      </div>
    </article>
  );
}
