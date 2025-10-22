package br.com.satc.demo.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@MappedSuperclass
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, length = 120)
    protected String nome;

    @Column(nullable = false, unique = true, length = 150)
    protected String email;

    @CreationTimestamp
    @Column(updatable = false)
    protected Instant criadoEm;

    @UpdateTimestamp
    protected Instant atualizadoEm;

    public Pessoa() {}
    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Instant getCriadoEm() { return criadoEm; }
    public Instant getAtualizadoEm() { return atualizadoEm; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa that = (Pessoa) o;
        return Objects.equals(id, that.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }

    @Override public String toString() {
        return "Pessoa{id=" + id + ", nome='" + nome + "', email='" + email + "'}";
    }
}
