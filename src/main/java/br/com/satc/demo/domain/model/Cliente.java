package br.com.satc.demo.domain.model;

import br.com.satc.demo.domain.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Cliente extends Pessoa {

    @Column(nullable = false, unique = true, length = 11)
    private String cpf; // salve sem pontos/traços

    @Column(length = 20)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ABERTO;

    // getters e setters
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
