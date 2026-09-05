package unioeste.com.br.gestaoviagem.historicostatusviagem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.historicostatusviagem.domain.HistoricoStatusViagem;
import unioeste.com.br.gestaoviagem.historicostatusviagem.repository.HistoricoStatusViagemRepository;
import unioeste.com.br.gestaoviagem.situacao.domain.Situacao;
import unioeste.com.br.gestaoviagem.viagem.domain.Viagem;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class HistoricoStatusViagemService {

    private final HistoricoStatusViagemRepository historicoRepository;

    public HistoricoStatusViagem registrarHistorico(Viagem viagem, Situacao situacao, Empregado responsavel, String comentario) {
        HistoricoStatusViagem historico = new HistoricoStatusViagem();
        historico.setViagem(viagem);
        historico.setSituacao(situacao);
        historico.setResponsavel(responsavel);
        historico.setComentario(comentario);
        historico.setDataHora(LocalDateTime.now());

        return historicoRepository.save(historico);
    }

    public List<HistoricoStatusViagem> listarPorViagem(Viagem viagem) {
        return historicoRepository.findByViagemNumeroOrderByDataHoraDesc(viagem.getNumero());
    }
}