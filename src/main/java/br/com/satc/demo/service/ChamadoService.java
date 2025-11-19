package br.com.satc.demo.service;

import br.com.satc.demo.domain.enums.Status;
import br.com.satc.demo.domain.model.Chamado;
import br.com.satc.demo.domain.model.Cliente;
import br.com.satc.demo.domain.model.Tecnico;
import br.com.satc.demo.domain.repository.ChamadoRepository;
import br.com.satc.demo.domain.repository.ClienteRepository;
import br.com.satc.demo.domain.repository.TecnicoRepository;
import br.com.satc.demo.web.dto.ChamadoDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepo;
    private final ClienteRepository clienteRepo;
    private final TecnicoRepository tecnicoRepo;

    public ChamadoService(ChamadoRepository chamadoRepo,
                          ClienteRepository clienteRepo,
                          TecnicoRepository tecnicoRepo) {
        this.chamadoRepo = chamadoRepo;
        this.clienteRepo = clienteRepo;
        this.tecnicoRepo = tecnicoRepo;
    }

    // --------- compatível com ChamadoResource ---------
    public List<Chamado> listar() {
        return listarTodos();
    }

    @Transactional
    public Chamado atualizarStatus(Long id, Status novo) {
        Chamado ch = buscarPorId(id);
        ch.setStatus(novo);
        if (novo == Status.FECHADO && ch.getFechadoEm() == null) {
            ch.setFechadoEm(Instant.now());
        }
        if (novo == Status.ABERTO) {
            ch.setFechadoEm(null);
        }
        return chamadoRepo.save(ch);
    }
    // ---------------------------------------------------

    public List<Chamado> listarTodos() {
        return chamadoRepo.findAll();
    }

    public Chamado buscarPorId(Long id) {
        return chamadoRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado: " + id));
    }

    @Transactional
    public Chamado abrir(ChamadoDTO dto) {
        Cliente cliente = clienteRepo.findById(dto.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado: " + dto.clienteId()));
        Tecnico tecnico = tecnicoRepo.findById(dto.tecnicoId())
                .orElseThrow(() -> new EntityNotFoundException("Técnico não encontrado: " + dto.tecnicoId()));

        Chamado ch = new Chamado(dto.titulo(), dto.descricao(), dto.prioridade(), dto.status(), cliente, tecnico);
        if (ch.getStatus() == Status.FECHADO) {
            ch.setFechadoEm(Instant.now());
        }
        return chamadoRepo.save(ch);
    }

    @Transactional
    public Chamado atualizar(Long id, ChamadoDTO dto) {
        Chamado ch = buscarPorId(id);

        ch.setTitulo(dto.titulo());
        ch.setDescricao(dto.descricao());
        ch.setPrioridade(dto.prioridade());

        Status novo = dto.status();
        ch.setStatus(novo);
        if (novo == Status.FECHADO && ch.getFechadoEm() == null) {
            ch.setFechadoEm(Instant.now());
        }
        if (novo == Status.ABERTO) {
            ch.setFechadoEm(null);
        }
        return chamadoRepo.save(ch);
    }

    @Transactional
    public void deletar(Long id) {
        Chamado ch = buscarPorId(id);
        if (ch.getStatus() == Status.ABERTO) {
            throw new IllegalStateException("Chamado ABERTO não pode ser excluído.");
        }
        chamadoRepo.deleteById(id);
    }
}
