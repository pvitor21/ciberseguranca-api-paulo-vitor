package br.com.paulovitor.cibersegurancaapi.controller;

import br.com.paulovitor.cibersegurancaapi.model.Equipamento;
import br.com.paulovitor.cibersegurancaapi.repository.EquipamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository repo;

    @GetMapping
    public List<Equipamento> listar() {
        return repo.findAll();
    }

    @PostMapping(consumes = "application/json")
    public Equipamento criar(@RequestBody Equipamento equipamento) {
        return repo.save(equipamento);
    }

    @PutMapping("/{id}")
    public Equipamento atualizar(@PathVariable Long id, @RequestBody Equipamento dados) {
        Equipamento equipamento = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado"));

        equipamento.setNome(dados.getNome());
        equipamento.setTipo(dados.getTipo());
        equipamento.setIp(dados.getIp());

        return repo.save(equipamento);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado");
        }
        repo.deleteById(id);
    }
}
