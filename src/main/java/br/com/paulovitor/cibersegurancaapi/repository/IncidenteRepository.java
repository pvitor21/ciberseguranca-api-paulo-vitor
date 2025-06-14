package br.com.paulovitor.cibersegurancaapi.repository;

import br.com.paulovitor.cibersegurancaapi.model.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenteRepository extends JpaRepository<Incidente, Long> {}
