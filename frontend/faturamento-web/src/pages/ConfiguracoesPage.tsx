import { PageContainer } from "../components/layout/PageContainer";

export function ConfiguracoesPage() {
  return (
    <PageContainer
      title="Configuracoes e proximos passos"
      subtitle="Espaco reservado para perfis, permissoes, integrações e parametros do sistema."
    >
      <div className="content-grid two-columns">
        <section className="panel-card">
          <h3>Pendencias funcionais</h3>
          <ul className="plain-list">
            <li>Definir faixas oficiais e regras de tolerancia.</li>
            <li>Fechar fluxo de tratamento dos alertas criticos.</li>
            <li>Detalhar perfis de contador e financeiro.</li>
          </ul>
        </section>

        <section className="panel-card">
          <h3>Pendencias tecnicas</h3>
          <ul className="plain-list">
            <li>Trocar mocks por integracao real com o backend.</li>
            <li>Implementar persistencia no PostgreSQL.</li>
            <li>Adicionar autenticacao real e trilha de auditoria automatica.</li>
          </ul>
        </section>
      </div>
    </PageContainer>
  );
}
