package br.com.paulovitor.cibersegurancaapi.repository;

import br.com.paulovitor.cibersegurancaapi.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {}
