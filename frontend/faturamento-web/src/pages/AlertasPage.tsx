import { useEffect, useState } from "react";
import { AlertCard } from "../components/cards/AlertCard";
import { PageContainer } from "../components/layout/PageContainer";
import { alertaService } from "../services/alertaService";
import type { Alerta } from "../types/Alerta";

export function AlertasPage() {
  const [alertas, setAlertas] = useState<Alerta[]>([]);

  useEffect(() => {
    void alertaService.getAlertas().then(setAlertas);
  }, []);

  return (
    <PageContainer
      title="Alertas de reajuste"
      subtitle="Fila priorizada para o time identificar perda de receita e agir rapidamente."
    >
      <div className="alert-grid">
        {alertas.map((alerta) => (
          <AlertCard key={alerta.id} alerta={alerta} />
        ))}
      </div>
    </PageContainer>
  );
}
