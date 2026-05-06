package br.com.empresa.faturamento.auditoria.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.empresa.faturamento.auditoria.dto.AuditoriaResponse;
import br.com.empresa.faturamento.auditoria.entity.Auditoria;
import br.com.empresa.faturamento.auditoria.repository.AuditoriaRepository;

@Service
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;

    public AuditoriaService(AuditoriaRepository auditoriaRepository) {
        this.auditoriaRepository = auditoriaRepository;
    }

    public List<AuditoriaResponse> listar() {
        return auditoriaRepository.findAll(Sort.by(Sort.Direction.DESC, "dataHora")).stream()
            .map(this::toResponse)
            .toList();
    }

    public List<AuditoriaResponse> listarPorUsuario(Long usuarioId) {
        return auditoriaRepository.findByUsuarioId(usuarioId, Sort.by(Sort.Direction.DESC, "dataHora")).stream()
            .map(this::toResponse)
            .toList();
    }

    public List<AuditoriaResponse> listarPorEntidade(String entidade, Long entidadeId) {
        return auditoriaRepository.findByEntidadeIgnoreCaseAndEntidadeId(
            entidade,
            entidadeId,
            Sort.by(Sort.Direction.DESC, "dataHora")
        ).stream()
            .map(this::toResponse)
            .toList();
    }

    private AuditoriaResponse toResponse(Auditoria item) {
        return new AuditoriaResponse(
            item.getId(),
            item.getUsuarioId(),
            item.getAcao(),
            item.getModulo(),
            item.getEntidade(),
            item.getEntidadeId(),
            item.getValorAnterior(),
            item.getValorNovo(),
            item.getDataHora(),
            item.getIp(),
            item.getContexto()
        );
    }
}
