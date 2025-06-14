package br.com.paulovitor.cibersegurancaapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;  // alterado de jakarta para javax

import java.util.ArrayList;
import java.util.List;

@Entity
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo;
    private String ip;

    @OneToMany(mappedBy = "equipamento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Incidente> incidentes = new ArrayList<>();

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }

    public List<Incidente> getIncidentes() { return incidentes; }
    public void setIncidentes(List<Incidente> incidentes) { this.incidentes = incidentes; }
}
