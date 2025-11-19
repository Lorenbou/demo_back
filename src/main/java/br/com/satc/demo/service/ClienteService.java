package br.com.satc.demo.service;

import br.com.satc.demo.domain.enums.Status;
import br.com.satc.demo.domain.model.Cliente;
import br.com.satc.demo.domain.repository.ChamadoRepository;
import br.com.satc.demo.domain.repository.ClienteRepository;
import br.com.satc.demo.web.dto.ClienteDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepo;
    private final ChamadoRepository chamadoRepo;

    public ClienteService(ClienteRepository clienteRepo, ChamadoRepository chamadoRepo) {
        this.clienteRepo = clienteRepo;
        this.chamadoRepo = chamadoRepo;
    }

    public List<Cliente> listarTodos() {
        return clienteRepo.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado: " + id));
    }

    @Transactional
    public Cliente salvar(ClienteDTO dto) {
        if (dto.email() != null && clienteRepo.existsByEmailIgnoreCase(dto.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado para outro cliente");
        }
        Cliente c = new Cliente();
        c.setNome(dto.nome());
        c.setEmail(dto.email());
        c.setStatus(dto.status());
        c.setTelefone(dto.telefone());
        return clienteRepo.save(c);
    }

    @Transactional
    public Cliente atualizar(Long id, ClienteDTO dto) {
        Cliente c = buscarPorId(id);

        if (dto.email() != null && !dto.email().equalsIgnoreCase(c.getEmail())
                && clienteRepo.existsByEmailIgnoreCase(dto.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado para outro cliente");
        }

        c.setNome(dto.nome());
        c.setEmail(dto.email());
        c.setStatus(dto.status());
        c.setTelefone(dto.telefone());
        return clienteRepo.save(c);
    }

    @Transactional
    public void deletar(Long id) {
        if (chamadoRepo.existsByClienteIdAndStatus(id, Status.ABERTO)) {
            throw new IllegalStateException("Cliente possui chamado ABERTO e não pode ser excluído.");
        }
        clienteRepo.deleteById(id);
    }
}
