import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { PageContainer } from "../components/layout/PageContainer";
import { clienteService } from "../services/clienteService";
import { faixaService } from "../services/faixaService";
import type { ClienteResumo } from "../types/Cliente";
import type { FaixaHonorario } from "../types/FaixaHonorario";
import { formatCurrency } from "../utils/formatCurrency";

interface ClienteFormData {
  nome: string;
  cnpj: string;
  regimeTributario: string;
  honorarioAtual: number;
  faturamentoAtual: number | string;
  faixaAtualId: number;
  responsavelInterno: string;
  ativo: boolean;
}

export function ClientesPage() {
  const [clientes, setClientes] = useState<ClienteResumo[]>([]);
  const [faixas, setFaixas] = useState<FaixaHonorario[]>([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isEditMode, setIsEditMode] = useState(false);
  const [editingId, setEditingId] = useState<number | null>(null);
  const [errorMsg, setErrorMsg] = useState("");

  const emptyForm: ClienteFormData = {
    nome: "",
    cnpj: "",
    regimeTributario: "SIMPLES_NACIONAL",
    honorarioAtual: 0,
    faturamentoAtual: "",
    faixaAtualId: 0,
    responsavelInterno: "",
    ativo: true,
  };

  const [formData, setFormData] = useState<ClienteFormData>({ ...emptyForm });

  useEffect(() => {
    void clienteService.getClientes().then(setClientes);
    void faixaService.getFaixas().then((res) => {
      setFaixas(res);
      if (res.length > 0) {
        setFormData((prev) => ({ ...prev, faixaAtualId: res[0].id }));
      }
    });
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]:
        name === "honorarioAtual" || name === "faixaAtualId"
          ? Number(value)
          : name === "faturamentoAtual"
          ? (value === "" ? "" : Number(value))
          : value,
    }));
  };

  const openCreateModal = () => {
    setIsEditMode(false);
    setEditingId(null);
    setFormData({ ...emptyForm, faixaAtualId: faixas[0]?.id || 0 });
    setIsModalOpen(true);
  };

  const openEditModal = (cliente: ClienteResumo) => {
    setIsEditMode(true);
    setEditingId(cliente.id);
    setFormData({
      nome: cliente.nome,
      cnpj: cliente.cnpj,
      regimeTributario: "SIMPLES_NACIONAL",
      honorarioAtual: cliente.honorarioAtual,
      faturamentoAtual: cliente.faturamentoAtual,
      faixaAtualId: faixas.find((f) => f.nome === cliente.faixaAtual)?.id || faixas[0]?.id || 0,
      responsavelInterno: cliente.responsavelInterno,
      ativo: true,
    });
    setIsModalOpen(true);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setErrorMsg("");
    try {
      if (isEditMode && editingId !== null) {
        const updated = await clienteService.atualizarCliente(editingId, formData);
        setClientes((prev) => prev.map((c) => (c.id === editingId ? updated : c)));
      } else {
        const newCliente = await clienteService.criarCliente(formData);
        setClientes((prev) => [...prev, newCliente]);
      }
      setIsModalOpen(false);
    } catch (err: any) {
      setErrorMsg(err.response?.data?.message || "Erro ao salvar cliente.");
    }
  };

  const handleDelete = async (id: number) => {
    if (!window.confirm("Tem certeza que deseja remover este cliente?")) return;
    try {
      await clienteService.excluirCliente(id);
      setClientes((prev) => prev.filter((c) => c.id !== id));
    } catch (err: any) {
      alert(err.response?.data?.message || "Erro ao remover cliente.");
    }
  };

  return (
    <PageContainer
      title="Clientes monitorados"
      subtitle="Lista consolidada com faturamento atual, faixa contratada e prioridade de analise."
      actions={<button className="primary-button" onClick={openCreateModal}>Novo cliente</button>}
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
                <th>Ações</th>
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
                    <span className={`status-pill ${cliente.tipoAlerta.toLowerCase()}`}> {cliente.tipoAlerta} </span>
                  </td>
                  <td>
                    <button className="ghost-button" onClick={() => openEditModal(cliente)}>Editar</button>
                    <button className="secondary-button" onClick={() => handleDelete(cliente.id)}>Excluir</button>
                    <Link className="inline-link" to={`/clientes/${cliente.id}`}>Analisar</Link>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </section>

      {isModalOpen && (
        <div className="modal-overlay">
          <div className="modal-content">
            <h2>{isEditMode ? "Editar Cliente" : "Novo Cliente"}</h2>
            {errorMsg && <div className="form-error" style={{ marginBottom: "16px" }}>{errorMsg}</div>}
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label>Nome:</label>
                <input required type="text" name="nome" value={formData.nome} onChange={handleChange} className="form-input" />
              </div>
              <div className="form-group">
                <label>CNPJ:</label>
                <input required type="text" name="cnpj" value={formData.cnpj} onChange={handleChange} className="form-input" />
              </div>
              <div className="form-group">
                <label>Regime Tributário:</label>
                <select required name="regimeTributario" value={formData.regimeTributario} onChange={handleChange} className="form-input">
                  <option value="SIMPLES_NACIONAL">Simples Nacional</option>
                  <option value="LUCRO_PRESUMIDO">Lucro Presumido</option>
                  <option value="LUCRO_REAL">Lucro Real</option>
                </select>
              </div>
              <div className="form-group">
                <label>Honorário Atual (R$):</label>
                <input required type="number" step="0.01" name="honorarioAtual" value={formData.honorarioAtual} onChange={handleChange} className="form-input" />
              </div>
              <div className="form-group">
                <label>Faturamento Atual (R$):</label>
                <input required type="number" step="0.01" name="faturamentoAtual" value={formData.faturamentoAtual} onChange={handleChange} className="form-input" />
              </div>
              <div className="form-group">
                <label>Faixa Atual:</label>
                <select required name="faixaAtualId" value={formData.faixaAtualId} onChange={handleChange} className="form-input">
                  {faixas.map(f => (
                    <option key={f.id} value={f.id}>{f.nome}</option>
                  ))}
                </select>
              </div>
              <div className="form-group">
                <label>Responsável Interno:</label>
                <input required type="text" name="responsavelInterno" value={formData.responsavelInterno} onChange={handleChange} className="form-input" />
              </div>
              <div className="modal-actions">
                <button type="button" onClick={() => setIsModalOpen(false)} className="ghost-button">Cancelar</button>
                <button type="submit" className="primary-button">Salvar</button>
              </div>
            </form>
          </div>
        </div>
      )}
    </PageContainer>
  );
}
