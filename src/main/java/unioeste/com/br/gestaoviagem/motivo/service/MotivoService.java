package unioeste.com.br.gestaoviagem.motivo.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unioeste.com.br.gestaoviagem.motivo.domain.Motivo;
import unioeste.com.br.gestaoviagem.motivo.repository.MotivoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MotivoService {

    private final MotivoRepository motivoRepository;

    public Motivo buscarPorId(Integer id) {
        return motivoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Motivo de ID " + id + " não encontrado."
                ));
    }
    public List<Motivo> listarTodos() {
        return motivoRepository.findAll();
    }
}