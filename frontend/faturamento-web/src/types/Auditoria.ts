export interface AuditoriaRegistro {
  id: number;
  usuario: string;
  acao: string;
  modulo: string;
  entidade: string;
  dataHora: string;
  valorAnterior?: string | null;
  valorNovo?: string | null;
  ip: string;
  contexto: string;
}
