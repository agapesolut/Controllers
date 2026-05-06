package br.com.empresa.faturamento.alerta.repository;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.empresa.faturamento.alerta.entity.AlertaReajuste;

public interface AlertaRepository extends JpaRepository<AlertaReajuste, Long> {

    Optional<AlertaReajuste> findByClienteId(Long clienteId);

    java.util.List<AlertaReajuste> findAll(Sort sort);
}
