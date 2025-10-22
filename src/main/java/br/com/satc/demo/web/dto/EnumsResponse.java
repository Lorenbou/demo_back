package br.com.satc.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(name = "EnumsResponse")
public record EnumsResponse(
        List<String> perfil,
        List<String> prioridade,
        List<String> status
) {}
