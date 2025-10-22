package br.com.satc.demo.domain.model;

import br.com.satc.demo.domain.enums.Status;
import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.ATIVO;

    @Column(length = 20)
    private String telefone;

    public Cliente() {}
    public Cliente(String nome, String email, Status status, String telefone) {
        super(nome, email);
        this.status = status;
        this.telefone = telefone;
    }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
