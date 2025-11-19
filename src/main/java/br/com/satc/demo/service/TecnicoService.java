package br.com.satc.demo.service;

import br.com.satc.demo.domain.enums.Status;
import br.com.satc.demo.domain.model.Tecnico;
import br.com.satc.demo.domain.repository.ChamadoRepository;
import br.com.satc.demo.domain.repository.TecnicoRepository;
import br.com.satc.demo.web.dto.TecnicoDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepo;
    private final ChamadoRepository chamadoRepo;

    public TecnicoService(TecnicoRepository tecnicoRepo, ChamadoRepository chamadoRepo) {
        this.tecnicoRepo = tecnicoRepo;
        this.chamadoRepo = chamadoRepo;
    }

    public List<Tecnico> listarTodos() {
        return tecnicoRepo.findAll();
    }

    public Tecnico buscarPorId(Long id) {
        return tecnicoRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Técnico não encontrado: " + id));
    }

    @Transactional
    public Tecnico salvar(TecnicoDTO dto) {
        if (dto.email() != null && tecnicoRepo.existsByEmailIgnoreCase(dto.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado para outro técnico");
        }
        Tecnico t = new Tecnico();
        t.setNome(dto.nome());
        t.setEmail(dto.email());
        t.setPerfil(dto.perfil());
        t.setEspecialidade(dto.especialidade());
        return tecnicoRepo.save(t);
    }

    @Transactional
    public Tecnico atualizar(Long id, TecnicoDTO dto) {
        Tecnico t = buscarPorId(id);

        if (dto.email() != null && !dto.email().equalsIgnoreCase(t.getEmail())
                && tecnicoRepo.existsByEmailIgnoreCase(dto.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado para outro técnico");
        }

        t.setNome(dto.nome());
        t.setEmail(dto.email());
        t.setPerfil(dto.perfil());
        t.setEspecialidade(dto.especialidade());
        return tecnicoRepo.save(t);
    }

    @Transactional
    public void deletar(Long id) {
        if (chamadoRepo.existsByTecnicoIdAndStatus(id, Status.ABERTO)) {
            throw new IllegalStateException("Técnico possui chamado ABERTO e não pode ser excluído.");
        }
        tecnicoRepo.deleteById(id);
    }
}
