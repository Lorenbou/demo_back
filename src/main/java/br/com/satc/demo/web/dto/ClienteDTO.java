package br.com.satc.demo.web.dto;

import br.com.satc.demo.domain.enums.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteDTO(
        Long id,

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 120, message = "Nome deve ter entre 3 e 120 caracteres")
        String nome,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotNull(message = "Status é obrigatório")
        Status status,

        // Ex.: só dígitos com 10 a 11 caracteres (fixe/celular BR). Ajuste se quiser.
        @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter 10 a 11 dígitos numéricos")
        String telefone
) {}
