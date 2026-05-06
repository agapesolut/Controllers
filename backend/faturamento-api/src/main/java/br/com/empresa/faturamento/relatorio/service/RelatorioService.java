package br.com.empresa.faturamento.relatorio.service;

import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    public String exportar(String identificador) {
        return "Exportacao " + identificador + " preparada para a proxima etapa de integracao.";
    }
}
