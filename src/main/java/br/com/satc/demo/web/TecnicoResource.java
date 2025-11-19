package br.com.satc.demo.web;

import br.com.satc.demo.domain.model.Tecnico;
import br.com.satc.demo.service.TecnicoService;
import br.com.satc.demo.web.dto.TecnicoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicos")
@Tag(name = "Técnicos", description = "Operações relacionadas aos técnicos")
public class TecnicoResource {

    private final TecnicoService service;

    public TecnicoResource(TecnicoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lista todos os técnicos")
    public ResponseEntity<List<Tecnico>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um técnico pelo ID")
    public ResponseEntity<Tecnico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cria um novo técnico")
    public ResponseEntity<Tecnico> criar(@Valid @RequestBody TecnicoDTO dto) {
        Tecnico salvo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um técnico")
    public ResponseEntity<Tecnico> atualizar(@PathVariable Long id,
                                             @Valid @RequestBody TecnicoDTO dto) {
        Tecnico atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um técnico pelo ID (impede exclusão se tiver chamado ABERTO)")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
