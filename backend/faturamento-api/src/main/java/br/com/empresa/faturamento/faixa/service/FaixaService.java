package br.com.empresa.faturamento.faixa.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.empresa.faturamento.faixa.dto.FaixaResponse;
import br.com.empresa.faturamento.faixa.dto.FaixaUpsertRequest;
import br.com.empresa.faturamento.faixa.entity.FaixaHonorario;
import br.com.empresa.faturamento.faixa.repository.FaixaRepository;
import br.com.empresa.faturamento.shared.exception.ResourceNotFoundException;

@Service
public class FaixaService {

    private final FaixaRepository faixaRepository;

    public FaixaService(FaixaRepository faixaRepository) {
        this.faixaRepository = faixaRepository;
    }

    public List<FaixaResponse> listar() {
        return faixaRepository.findAll(Sort.by(Sort.Direction.ASC, "faturamentoMinimo")).stream()
            .map(this::toResponse)
            .toList();
    }

    public FaixaResponse buscarPorId(Long id) {
        return toResponse(buscarEntidadePorId(id));
    }

    public FaixaHonorario buscarEntidadePorId(Long id) {
        return faixaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Faixa nao encontrada."));
    }

    public FaixaHonorario encontrarFaixaPorFaturamento(BigDecimal faturamento) {
        return faixaRepository
            .findFirstByFaturamentoMinimoLessThanEqualAndFaturamentoMaximoGreaterThanEqualAndAtivaTrue(
                faturamento,
                faturamento
            )
            .or(() -> faixaRepository.findTopByAtivaTrueOrderByFaturamentoMaximoDesc())
            .orElseThrow(() -> new ResourceNotFoundException("Nenhuma faixa ativa cadastrada."));
    }

    @Transactional
    public FaixaResponse criar(FaixaUpsertRequest request) {
        FaixaHonorario faixa = new FaixaHonorario(
            null,
            request.nome(),
            request.faturamentoMinimo(),
            request.faturamentoMaximo(),
            request.valorHonorario(),
            request.percentualAlertaPreventivo(),
            request.ativa()
        );

        return toResponse(faixaRepository.save(faixa));
    }

    @Transactional
    public FaixaResponse atualizar(Long id, FaixaUpsertRequest request) {
        FaixaHonorario faixa = buscarEntidadePorId(id);
        faixa.atualizar(
            request.nome(),
            request.faturamentoMinimo(),
            request.faturamentoMaximo(),
            request.valorHonorario(),
            request.percentualAlertaPreventivo(),
            request.ativa()
        );
        return toResponse(faixaRepository.save(faixa));
    }

    @Transactional
    public void remover(Long id) {
        FaixaHonorario faixa = buscarEntidadePorId(id);
        faixaRepository.delete(faixa);
    }

    private FaixaResponse toResponse(FaixaHonorario faixa) {
        return new FaixaResponse(
            faixa.getId(),
            faixa.getNome(),
            faixa.getFaturamentoMinimo(),
            faixa.getFaturamentoMaximo(),
            faixa.getValorHonorario(),
            faixa.getPercentualAlertaPreventivo(),
            faixa.getAtiva()
        );
    }
}
