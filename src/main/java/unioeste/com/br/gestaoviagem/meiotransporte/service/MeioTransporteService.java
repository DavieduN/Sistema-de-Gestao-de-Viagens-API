package unioeste.com.br.gestaoviagem.meiotransporte.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unioeste.com.br.gestaoviagem.meiotransporte.domain.MeioTransporte;
import unioeste.com.br.gestaoviagem.meiotransporte.repository.MeioTransporteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MeioTransporteService {

    private final MeioTransporteRepository meioTransporteRepository;

    public MeioTransporte buscarPorId(Integer id) {
        return meioTransporteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Meio de transporte de ID " + id + " não encontrado."
                ));
    }

    public List<MeioTransporte> listarTodos() {
        return meioTransporteRepository.findAll();
    }
}