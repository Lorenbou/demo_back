package br.com.satc.demo.web;

import br.com.satc.demo.domain.enums.Perfil;
import br.com.satc.demo.domain.enums.Prioridade;
import br.com.satc.demo.domain.enums.Status;
import br.com.satc.demo.web.dto.EnumsResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class EnumsController {

    @Operation(summary = "Lista os valores dos Enums do sistema")
    @GetMapping("/api/enums")
    public EnumsResponse listarEnums() {
        return new EnumsResponse(
                Arrays.stream(Perfil.values()).map(Enum::name).toList(),
                Arrays.stream(Prioridade.values()).map(Enum::name).toList(),
                Arrays.stream(Status.values()).map(Enum::name).toList()
        );
    }
}
