package br.com.empresa.faturamento.faixa.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.empresa.faturamento.faixa.entity.FaixaHonorario;

public interface FaixaRepository extends JpaRepository<FaixaHonorario, Long> {

    List<FaixaHonorario> findByAtivaTrue(Sort sort);

    Optional<FaixaHonorario> findFirstByFaturamentoMinimoLessThanEqualAndFaturamentoMaximoGreaterThanEqualAndAtivaTrue(
        BigDecimal faturamentoMaximo,
        BigDecimal faturamentoMinimo
    );

    Optional<FaixaHonorario> findTopByAtivaTrueOrderByFaturamentoMaximoDesc();
}
