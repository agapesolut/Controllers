import { useEffect, useState } from "react";
import { PageContainer } from "../components/layout/PageContainer";
import { auditoriaService } from "../services/auditoriaService";
import type { AuditoriaRegistro } from "../types/Auditoria";
import { formatDate } from "../utils/formatDate";

export function AuditoriaPage() {
  const [registros, setRegistros] = useState<AuditoriaRegistro[]>([]);

  useEffect(() => {
    void auditoriaService.getAuditoria().then(setRegistros);
  }, []);

  return (
    <PageContainer
      title="Auditoria"
      subtitle="Rastro inicial das acoes importantes executadas no sistema."
    >
      <section className="panel-card">
        <div className="table-wrap">
          <table>
            <thead>
              <tr>
                <th>Usuario</th>
                <th>Acao</th>
                <th>Modulo</th>
                <th>Entidade</th>
                <th>Data e hora</th>
                <th>IP</th>
                <th>Contexto</th>
              </tr>
            </thead>
            <tbody>
              {registros.map((registro) => (
                <tr key={registro.id}>
                  <td>{registro.usuario}</td>
                  <td>{registro.acao}</td>
                  <td>{registro.modulo}</td>
                  <td>{registro.entidade}</td>
                  <td>{formatDate(registro.dataHora)}</td>
                  <td>{registro.ip}</td>
                  <td>{registro.contexto}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </section>
    </PageContainer>
  );
}
