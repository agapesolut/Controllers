package br.com.empresa.faturamento.shared.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import br.com.empresa.faturamento.auditoria.entity.Auditoria;
import br.com.empresa.faturamento.cliente.entity.Cliente;
import br.com.empresa.faturamento.faixa.entity.FaixaHonorario;
import br.com.empresa.faturamento.faturamento.entity.FaturamentoCliente;
import br.com.empresa.faturamento.shared.enums.StatusAlerta;
import br.com.empresa.faturamento.shared.enums.TipoAlerta;
import br.com.empresa.faturamento.usuario.entity.Usuario;

public final class MockDataFactory {

    private MockDataFactory() {
    }

    public static Usuario admin() {
        return new Usuario(
            1L,
            "Administrador",
            "admin@agape.local",
            "$2a$10$8wA0cZ7m5z0v1jv2QJ5CluPh7yikd7mUZ3rZ6Em0Dq8I1D1IepVhu",
            "ADMINISTRADOR",
            true,
            Set.of(
                "PERMISSAO_DASHBOARD_VISUALIZAR",
                "PERMISSAO_CLIENTES_VISUALIZAR",
                "PERMISSAO_CLIENTES_CADASTRAR",
                "PERMISSAO_CLIENTES_EDITAR",
                "PERMISSAO_FAIXAS_VISUALIZAR",
                "PERMISSAO_FAIXAS_CADASTRAR",
                "PERMISSAO_FAIXAS_EDITAR",
                "PERMISSAO_ALERTAS_VISUALIZAR",
                "PERMISSAO_RELATORIOS_EXPORTAR",
                "PERMISSAO_AUDITORIA_VISUALIZAR",
                "PERMISSAO_USUARIOS_GERENCIAR"
            )
        );
    }

    public static List<FaixaHonorario> faixas() {
        return List.of(
            new FaixaHonorario(1L, "Faixa Essencial", bd("0"), bd("50000"), bd("500"), bd("90"), true),
            new FaixaHonorario(2L, "Faixa Crescimento", bd("50000.01"), bd("100000"), bd("800"), bd("90"), true),
            new FaixaHonorario(3L, "Faixa Expansao", bd("100000.01"), bd("200000"), bd("1200"), bd("90"), true),
            new FaixaHonorario(4L, "Faixa Premium", bd("200000.01"), bd("99999999"), bd("1800"), bd("90"), true)
        );
    }

    public static List<Cliente> clientes() {
        return List.of(
            new Cliente(1L, "Mercado Sol Nascente", "12.345.678/0001-01", "Simples Nacional", bd("500"), 1L, "Ana Paula", true),
            new Cliente(2L, "Construtora Pilar", "23.456.789/0001-22", "Lucro Presumido", bd("800"), 2L, "Marcos Lima", true),
            new Cliente(3L, "Clinica Vida Plena", "34.567.890/0001-33", "Lucro Real", bd("1200"), 3L, "Ana Paula", true),
            new Cliente(4L, "Distribuidora Horizonte", "45.678.901/0001-44", "Lucro Presumido", bd("800"), 2L, "Juliana Costa", true)
        );
    }

    public static List<FaturamentoCliente> faturamentos() {
        return List.of(
            new FaturamentoCliente(1L, 1L, 1, 2026, bd("42000"), LocalDate.of(2026, 1, 1)),
            new FaturamentoCliente(2L, 1L, 2, 2026, bd("45500"), LocalDate.of(2026, 2, 1)),
            new FaturamentoCliente(3L, 1L, 3, 2026, bd("49000"), LocalDate.of(2026, 3, 1)),
            new FaturamentoCliente(4L, 1L, 4, 2026, bd("51000"), LocalDate.of(2026, 4, 1)),
            new FaturamentoCliente(5L, 2L, 1, 2026, bd("87000"), LocalDate.of(2026, 1, 1)),
            new FaturamentoCliente(6L, 2L, 2, 2026, bd("91000"), LocalDate.of(2026, 2, 1)),
            new FaturamentoCliente(7L, 2L, 3, 2026, bd("96000"), LocalDate.of(2026, 3, 1)),
            new FaturamentoCliente(8L, 2L, 4, 2026, bd("98500"), LocalDate.of(2026, 4, 1)),
            new FaturamentoCliente(9L, 3L, 1, 2026, bd("118000"), LocalDate.of(2026, 1, 1)),
            new FaturamentoCliente(10L, 3L, 2, 2026, bd("132000"), LocalDate.of(2026, 2, 1)),
            new FaturamentoCliente(11L, 3L, 3, 2026, bd("145000"), LocalDate.of(2026, 3, 1)),
            new FaturamentoCliente(12L, 3L, 4, 2026, bd("152000"), LocalDate.of(2026, 4, 1)),
            new FaturamentoCliente(13L, 4L, 1, 2026, bd("99000"), LocalDate.of(2026, 1, 1)),
            new FaturamentoCliente(14L, 4L, 2, 2026, bd("104000"), LocalDate.of(2026, 2, 1)),
            new FaturamentoCliente(15L, 4L, 3, 2026, bd("112000"), LocalDate.of(2026, 3, 1)),
            new FaturamentoCliente(16L, 4L, 4, 2026, bd("119000"), LocalDate.of(2026, 4, 1))
        );
    }

    public static List<Auditoria> auditorias() {
        return List.of(
            new Auditoria(1L, 1L, "LOGIN", "AUTH", "USUARIO", 1L, null, null, LocalDateTime.of(2026, 5, 1, 8, 30), "10.0.0.10", "Acesso ao sistema"),
            new Auditoria(2L, 1L, "CONSULTA_DASHBOARD", "DASHBOARD", "RESUMO_GERAL", null, null, null, LocalDateTime.of(2026, 5, 1, 8, 31), "10.0.0.10", "Painel inicial carregado"),
            new Auditoria(3L, 1L, "ANALISE_CLIENTE", "CLIENTE", "CLIENTE", 4L, null, null, LocalDateTime.of(2026, 5, 1, 8, 45), "10.0.0.10", "Cliente acima da faixa analisado")
        );
    }

    public static BigDecimal bd(String value) {
        return new BigDecimal(value);
    }
}
