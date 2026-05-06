import { mockDelay } from "../data/mockData";

export const relatorioService = {
  async exportar(tipo: "excel" | "csv" | "pdf"): Promise<string> {
    const mensagem = `Exportacao ${tipo.toUpperCase()} iniciada no modo demonstracao.`;
    return mockDelay(mensagem, 250);
  },
};
