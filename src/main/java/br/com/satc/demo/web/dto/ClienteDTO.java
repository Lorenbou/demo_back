package br.com.satc.demo.web.dto;

import br.com.satc.demo.domain.enums.Status;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteDTO(
        Long id,

        @NotBlank @Size(min = 3, max = 120)
        String nome,

        @NotBlank @Email
        String email,

        @NotBlank @CPF
        String cpf,

        @NotNull
        Status status,

        @Size(max = 20)
        String telefone
) {}
