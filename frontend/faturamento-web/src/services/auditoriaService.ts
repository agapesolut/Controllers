import { auditoriaMock, mockDelay } from "../data/mockData";
import type { AuditoriaRegistro } from "../types/Auditoria";
import { api, unwrapApiData } from "./api";

interface AuditoriaApi {
  id: number;
  usuarioId: number | null;
  acao: string;
  modulo: string;
  entidade: string;
  entidadeId: number | null;
  valorAnterior?: string | null;
  valorNovo?: string | null;
  dataHora: string;
  ip: string;
  contexto: string;
}

export const auditoriaService = {
  async getAuditoria(): Promise<AuditoriaRegistro[]> {
    try {
      const registros = await unwrapApiData<AuditoriaApi[]>(
        api.get("/auditoria"),
      );

      return registros.map((registro) => ({
        id: registro.id,
        usuario: registro.usuarioId ? `Usuario #${registro.usuarioId}` : "Sistema",
        acao: registro.acao,
        modulo: registro.modulo,
        entidade: registro.entidade,
        dataHora: registro.dataHora,
        valorAnterior: registro.valorAnterior,
        valorNovo: registro.valorNovo,
        ip: registro.ip,
        contexto: registro.contexto,
      }));
    } catch {
      return mockDelay(auditoriaMock);
    }
  },
};
