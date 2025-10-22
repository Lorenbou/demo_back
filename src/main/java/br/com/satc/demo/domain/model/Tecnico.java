package br.com.satc.demo.domain.model;

import br.com.satc.demo.domain.enums.Perfil;
import jakarta.persistence.*;

@Entity
@Table(name = "tecnicos")
public class Tecnico extends Pessoa {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Perfil perfil = Perfil.GERENTE; // exemplo

    @Column(length = 120)
    private String especialidade;

    public Tecnico() {}
    public Tecnico(String nome, String email, Perfil perfil, String especialidade) {
        super(nome, email);
        this.perfil = perfil;
        this.especialidade = especialidade;
    }

    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
}
