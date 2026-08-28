package unioeste.com.br.gestaoviagem.viagem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.viagem.domain.Viagem;
import unioeste.com.br.gestaoviagem.viagem.domain.ViagemForm;
import unioeste.com.br.gestaoviagem.viagem.repository.ViagemRepository;

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
}
