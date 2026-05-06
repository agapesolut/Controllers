package br.com.empresa.faturamento.faturamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.empresa.faturamento.faturamento.entity.FaturamentoCliente;

public interface FaturamentoRepository extends JpaRepository<FaturamentoCliente, Long> {

    List<FaturamentoCliente> findByClienteIdOrderByDataReferenciaAsc(Long clienteId);

    Optional<FaturamentoCliente> findTopByClienteIdOrderByDataReferenciaDesc(Long clienteId);

    List<FaturamentoCliente> findAll(Sort sort);
}
