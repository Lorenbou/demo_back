package br.com.satc.demo.web;

import br.com.satc.demo.domain.model.Cliente;
import br.com.satc.demo.domain.repository.ClienteRepository;
import br.com.satc.demo.web.dto.ClienteDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository repo;

    public ClienteController(ClienteRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Cliente> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Cliente criar(@Valid @RequestBody ClienteDTO dto) {
        Cliente c = new Cliente(dto.nome(), dto.email(), dto.status(), dto.telefone());
        return repo.save(c);
    }
}