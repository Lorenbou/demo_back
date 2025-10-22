package br.com.satc.demo.web;

import br.com.satc.demo.domain.model.Tecnico;
import br.com.satc.demo.domain.repository.TecnicoRepository;
import br.com.satc.demo.web.dto.TecnicoDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicos")
public class TecnicoController {

    private final TecnicoRepository repo;

    public TecnicoController(TecnicoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Tecnico> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Tecnico criar(@Valid @RequestBody TecnicoDTO dto) {
        Tecnico t = new Tecnico(dto.nome(), dto.email(), dto.perfil(), dto.especialidade());
        return repo.save(t);
    }
}
