package br.com.satc.demo.web.dto;

import br.com.satc.demo.domain.enums.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TecnicoDTO(
        Long id,

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 120, message = "Nome deve ter entre 3 e 120 caracteres")
        String nome,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotNull(message = "Perfil é obrigatório")
        Perfil perfil,

        @Size(max = 120, message = "Especialidade deve ter no máximo 120 caracteres")
        String especialidade
) {}
