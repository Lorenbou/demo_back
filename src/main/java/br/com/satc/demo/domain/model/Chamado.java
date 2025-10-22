package br.com.satc.demo.domain.model;

import br.com.satc.demo.domain.enums.Prioridade;
import br.com.satc.demo.domain.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "chamados")
public class Chamado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 140)
    private String titulo;

    @Lob
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Prioridade prioridade = Prioridade.MEDIA;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.ATIVO;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant abertoEm;

    private Instant fechadoEm;

    // N:1 (muitos chamados para um cliente)
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // N:1 (muitos chamados para um técnico)
    @ManyToOne(optional = false)
    @JoinColumn(name = "tecnico_id", nullable = false)
    private Tecnico tecnico;

    public Chamado() {}

    public Chamado(String titulo, String descricao, Prioridade prioridade,
                   Status status, Cliente cliente, Tecnico tecnico) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = status;
        this.cliente = cliente;
        this.tecnico = tecnico;
    }

    // getters/setters
    public Long getId() { return id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Prioridade getPrioridade() { return prioridade; }
    public void setPrioridade(Prioridade prioridade) { this.prioridade = prioridade; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Instant getAbertoEm() { return abertoEm; }
    public Instant getFechadoEm() { return fechadoEm; }
    public void setFechadoEm(Instant fechadoEm) { this.fechadoEm = fechadoEm; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Tecnico getTecnico() { return tecnico; }
    public void setTecnico(Tecnico tecnico) { this.tecnico = tecnico; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chamado)) return false;
        Chamado chamado = (Chamado) o;
        return Objects.equals(id, chamado.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }

    @Override public String toString() {
        return "Chamado{id=" + id + ", titulo='" + titulo + "'}";
    }
}
