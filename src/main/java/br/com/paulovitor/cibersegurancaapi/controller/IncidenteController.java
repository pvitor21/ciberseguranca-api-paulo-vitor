package br.com.paulovitor.cibersegurancaapi.controller;

import br.com.paulovitor.cibersegurancaapi.model.Incidente;
import br.com.paulovitor.cibersegurancaapi.repository.IncidenteRepository;
import br.com.paulovitor.cibersegurancaapi.repository.EquipamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/incidentes")
public class IncidenteController {

    @Autowired
    private IncidenteRepository incidenteRepo;

    @Autowired
    private EquipamentoRepository equipamentoRepo;

    @GetMapping
    public List<Incidente> listar() {
        return incidenteRepo.findAll();
    }

    @PostMapping
    public Incidente criar(@RequestBody Incidente incidente) {
        return equipamentoRepo.findById(incidente.getEquipamento().getId())
                .map(equipamento -> {
                    incidente.setEquipamento(equipamento);
                    return incidenteRepo.save(incidente);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado"));
    }

    @PutMapping("/{id}")
    public Incidente atualizar(@PathVariable Long id, @RequestBody Incidente dados) {
        Incidente incidente = incidenteRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incidente não encontrado"));

        incidente.setDescricao(dados.getDescricao());
        incidente.setDataHora(dados.getDataHora()); // CORRIGIDO

        return incidenteRepo.save(incidente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        incidenteRepo.deleteById(id);
    }
}
