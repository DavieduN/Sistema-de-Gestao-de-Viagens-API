package unioeste.com.br.gestaoviagem.viagem.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.viagem.domain.Viagem;
import unioeste.com.br.gestaoviagem.viagem.domain.ViagemForm;
import unioeste.com.br.gestaoviagem.viagem.repository.ViagemRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ViagemService {
    private final ViagemRepository viagemRepository;

    public Viagem criar(ViagemForm viagemForm, Empregado empregado) {
        if (viagemForm.getDataRetorno().isBefore(viagemForm.getDataSaida())) {
            throw new IllegalArgumentException("A data de retorno deve ser igual ou posterior à data de saída.");
        }

        Viagem viagem = new Viagem();
        viagem.setDestino(viagemForm.getDestino());
        viagem.setDataSaida(viagemForm.getDataSaida());
        viagem.setDataRetorno(viagemForm.getDataRetorno());
        viagem.setMotivo(viagemForm.getMotivo());
        viagem.setMeioTransporte(viagemForm.getMeioTransporte());
        viagem.setEmpregado(empregado);

        return viagemRepository.save(viagem);
    }

    public List<Viagem> listarTodas() {
        return viagemRepository.findAll();
    }

    public Viagem buscarPorId(Long numero) {
        return viagemRepository.findById(numero)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Viagem de número " + numero + " não encontrada."
                ));
    }

    public Viagem atualizar(Viagem viagem, ViagemForm viagemForm, Empregado empregado) {
        if (viagemForm.getDataRetorno().isBefore(viagemForm.getDataSaida())) {
            throw new IllegalArgumentException("A data de retorno deve ser igual ou posterior à data de saída.");
        }
        viagem.setDestino(viagemForm.getDestino());
        viagem.setDataSaida(viagemForm.getDataSaida());
        viagem.setDataRetorno(viagemForm.getDataRetorno());
        viagem.setMotivo(viagemForm.getMotivo());
        viagem.setMeioTransporte(viagemForm.getMeioTransporte());
        viagem.setEmpregado(empregado);

        return viagemRepository.save(viagem);
    }
}
