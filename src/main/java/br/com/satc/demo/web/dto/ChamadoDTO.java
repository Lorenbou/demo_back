package br.com.satc.demo.web.dto;

import br.com.satc.demo.domain.enums.Prioridade;
import br.com.satc.demo.domain.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ChamadoDTO(

        @NotBlank(message = "Título é obrigatório")
        @Size(min = 3, max = 120, message = "Título deve ter entre 3 e 120 caracteres")
        String titulo,

        @Size(max = 2000, message = "Descrição deve ter no máximo 2000 caracteres")
        String descricao,

        @NotNull(message = "Prioridade é obrigatória")
        Prioridade prioridade,

        @NotNull(message = "Status é obrigatório")
        Status status,

        @NotNull(message = "clienteId é obrigatório")
        @Positive(message = "clienteId deve ser positivo")
        Long clienteId,

        @NotNull(message = "tecnicoId é obrigatório")
        @Positive(message = "tecnicoId deve ser positivo")
        Long tecnicoId
) {}
