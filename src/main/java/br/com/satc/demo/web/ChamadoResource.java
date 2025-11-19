package br.com.satc.demo.web;

import br.com.satc.demo.domain.enums.Status;
import br.com.satc.demo.domain.model.Chamado;
import br.com.satc.demo.service.ChamadoService;
import br.com.satc.demo.web.dto.ChamadoDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoResource {

    private final ChamadoService service;

    public ChamadoResource(ChamadoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lista todos os chamados")
    public List<Chamado> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um chamado pelo ID")
    public Chamado buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Abre um chamado")
    public Chamado abrir(@Valid @RequestBody ChamadoDTO dto) {
        return service.abrir(dto);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Muda o status de um chamado")
    public Chamado moverStatus(@PathVariable Long id, @RequestParam Status novo) {
        return service.atualizarStatus(id, novo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um chamado pelo ID (impede exclusão se tiver status ABERTO)")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
