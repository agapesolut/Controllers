package br.com.empresa.faturamento.auditoria.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.empresa.faturamento.auditoria.entity.Auditoria;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

    List<Auditoria> findByUsuarioId(Long usuarioId, Sort sort);

    List<Auditoria> findByEntidadeIgnoreCaseAndEntidadeId(String entidade, Long entidadeId, Sort sort);
}
