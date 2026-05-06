import { useState, type FormEvent } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";

export function LoginPage() {
  const navigate = useNavigate();
  const { signIn } = useAuth();
  const [login, setLogin] = useState("admin");
  const [senha, setSenha] = useState("admin123");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setError("");
    setLoading(true);

    try {
      await signIn(login, senha);
      navigate("/");
    } catch (submissionError) {
      setError(
        submissionError instanceof Error
          ? submissionError.message
          : "Falha ao entrar no sistema.",
      );
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="login-screen">
      <div className="login-panel">
        <div className="login-copy">
          <p className="page-eyebrow">Sistema interno</p>
          <h1>Gestao de faturamento com visibilidade real de reajustes</h1>
          <p>
            Entre com o acesso inicial do administrador para navegar no MVP.
          </p>
        </div>

        <form className="login-form" onSubmit={handleSubmit}>
          <label>
            Login
            <input
              value={login}
              onChange={(event) => setLogin(event.target.value)}
              placeholder="admin ou admin@agape.local"
            />
          </label>

          <label>
            Senha
            <input
              type="password"
              value={senha}
              onChange={(event) => setSenha(event.target.value)}
              placeholder="admin123"
            />
          </label>

          {error ? <p className="form-error">{error}</p> : null}

          <button type="submit" className="primary-button" disabled={loading}>
            {loading ? "Entrando..." : "Entrar no painel"}
          </button>
        </form>
      </div>
    </div>
  );
}
