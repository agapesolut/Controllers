import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { PageContainer } from "../components/layout/PageContainer";
import { clienteService } from "../services/clienteService";
import type { ClienteResumo } from "../types/Cliente";
import { formatCurrency } from "../utils/formatCurrency";

export function ClientesPage() {
  const [clientes, setClientes] = useState<ClienteResumo[]>([]);

  useEffect(() => {
    void clienteService.getClientes().then(setClientes);
  }, []);

  return (
    <PageContainer
      title="Clientes monitorados"
      subtitle="Lista consolidada com faturamento atual, faixa contratada e prioridade de analise."
      actions={<button className="primary-button">Novo cliente</button>}
    >
      <section className="panel-card">
        <div className="table-wrap">
          <table>
            <thead>
              <tr>
                <th>Cliente</th>
                <th>CNPJ</th>
                <th>Responsavel</th>
                <th>Faturamento atual</th>
                <th>Honorario</th>
                <th>Faixa atual</th>
                <th>Status</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {clientes.map((cliente) => (
                <tr key={cliente.id}>
                  <td>{cliente.nome}</td>
                  <td>{cliente.cnpj}</td>
                  <td>{cliente.responsavelInterno}</td>
                  <td>{formatCurrency(cliente.faturamentoAtual)}</td>
                  <td>{formatCurrency(cliente.honorarioAtual)}</td>
                  <td>{cliente.faixaAtual}</td>
                  <td>
                    <span className={`status-pill ${cliente.tipoAlerta.toLowerCase()}`}>
                      {cliente.tipoAlerta}
                    </span>
                  </td>
                  <td>
                    <Link className="inline-link" to={`/clientes/${cliente.id}`}>
                      Analisar
                    </Link>
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
