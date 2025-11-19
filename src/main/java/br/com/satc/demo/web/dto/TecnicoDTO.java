package br.com.satc.demo.web.dto;

import br.com.satc.demo.domain.enums.Perfil;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record TecnicoDTO(
        Long id,

        @NotBlank @Size(min = 3, max = 120)
        String nome,

        @NotBlank @Email
        String email,

        @NotBlank @CPF
        String cpf,

        @NotNull
        Perfil perfil,

        @Size(max = 120)
        String especialidade
) {}
