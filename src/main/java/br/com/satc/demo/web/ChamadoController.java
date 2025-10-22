package br.com.satc.demo.web;

import br.com.satc.demo.domain.model.Chamado;
import br.com.satc.demo.domain.model.Cliente;
import br.com.satc.demo.domain.model.Tecnico;
import br.com.satc.demo.domain.repository.ChamadoRepository;
import br.com.satc.demo.domain.repository.ClienteRepository;
import br.com.satc.demo.domain.repository.TecnicoRepository;
import br.com.satc.demo.web.dto.ChamadoDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    private final ChamadoRepository chamadoRepo;
    private final ClienteRepository clienteRepo;
    private final TecnicoRepository tecnicoRepo;

    public ChamadoController(ChamadoRepository chamadoRepo,
                             ClienteRepository clienteRepo,
                             TecnicoRepository tecnicoRepo) {
        this.chamadoRepo = chamadoRepo;
        this.clienteRepo = clienteRepo;
        this.tecnicoRepo = tecnicoRepo;
    }

    @GetMapping
    public List<Chamado> listar() {
        return chamadoRepo.findAll();
    }

    @PostMapping
    public Chamado abrir(@Valid @RequestBody ChamadoDTO dto) {
        Cliente cliente = clienteRepo.findById(dto.clienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + dto.clienteId()));

        Tecnico tecnico = tecnicoRepo.findById(dto.tecnicoId())
                .orElseThrow(() -> new IllegalArgumentException("Técnico não encontrado: " + dto.tecnicoId()));

        Chamado ch = new Chamado(
                dto.titulo(),
                dto.descricao(),
                dto.prioridade(),
                dto.status(),
                cliente,
                tecnico
        );
        return chamadoRepo.save(ch);
    }
}
