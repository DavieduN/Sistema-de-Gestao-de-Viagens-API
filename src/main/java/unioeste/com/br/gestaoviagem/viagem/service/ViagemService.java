package unioeste.com.br.gestaoviagem.viagem.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.historicostatusviagem.domain.HistoricoStatusViagem;
import unioeste.com.br.gestaoviagem.historicostatusviagem.repository.HistoricoStatusViagemRepository;
import unioeste.com.br.gestaoviagem.meiotransporte.domain.MeioTransporte;
import unioeste.com.br.gestaoviagem.meiotransporte.repository.MeioTransporteRepository;
import unioeste.com.br.gestaoviagem.motivo.domain.Motivo;
import unioeste.com.br.gestaoviagem.motivo.repository.MotivoRepository;
import unioeste.com.br.gestaoviagem.situacao.domain.Situacao;
import unioeste.com.br.gestaoviagem.situacao.repository.SituacaoRepository;
import unioeste.com.br.gestaoviagem.viagem.domain.*;
import unioeste.com.br.gestaoviagem.viagem.repository.*;

import java.util.List;

@Service
@AllArgsConstructor
public class ViagemService {

    private final ViagemRepository viagemRepository;

    public Viagem criar(ViagemForm viagemForm, Empregado solicitante, Motivo motivo, MeioTransporte transporte, Situacao situacaoInicial) {
        validarDatas(viagemForm);

        Viagem viagem = new Viagem();
        viagem.setDestino(viagemForm.getDestino());
        viagem.setDataSaida(viagemForm.getDataSaida());
        viagem.setDataRetorno(viagemForm.getDataRetorno());
        viagem.setSolicitante(solicitante);
        viagem.setMotivo(motivo);
        viagem.setMeioTransporte(transporte);
        viagem.setSituacao(situacaoInicial);

        // Snapshot do cargo e área no momento da solicitação
        viagem.setCargoSnapshot(solicitante.getCargo());
        viagem.setAreaSnapshot(solicitante.getArea());

        return viagemRepository.save(viagem);
    }

    public Viagem atualizar(Viagem viagem, ViagemForm viagemForm, Motivo motivo, MeioTransporte transporte) {
        String statusAtual = viagem.getSituacao().getDescricao();
        if (!statusAtual.equals("Rascunho") && !statusAtual.equals("Ajustes Solicitados")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Viagem não pode ser editada no status atual: " + statusAtual);
        }

        validarDatas(viagemForm);

        viagem.setDestino(viagemForm.getDestino());
        viagem.setDataSaida(viagemForm.getDataSaida());
        viagem.setDataRetorno(viagemForm.getDataRetorno());
        viagem.setMotivo(motivo);
        viagem.setMeioTransporte(transporte);

        return viagemRepository.save(viagem);
    }

    public Viagem alterarStatus(Viagem viagem, Empregado agente, Situacao novaSituacao) {
        String novaDesc = novaSituacao.getDescricao();

        if (List.of("Aprovada", "Rejeitada", "Ajustes Solicitados").contains(novaDesc)) {
            if (!agente.getCargo().getNome().equalsIgnoreCase("Gestor")) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas gestores podem realizar esta ação.");
            }
            if (!viagem.getSituacao().getDescricao().equals("Solicitada")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A viagem precisa estar 'Solicitada' para ser avaliada.");
            }
        }

        viagem.setSituacao(novaSituacao);
        return viagemRepository.save(viagem);
    }

    public Viagem buscarPorId(Long numero) {
        return viagemRepository.findById(numero)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada."));
    }

    public List<Viagem> listarTodas() {
        return viagemRepository.findAll();
    }

    public void deletar(Viagem viagem) {
        if (!viagem.getSituacao().getDescricao().equals("Rascunho")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possível deletar viagens em Rascunho.");
        }
        viagemRepository.delete(viagem);
    }

    private void validarDatas(ViagemForm form) {
        if (form.getDataRetorno().isBefore(form.getDataSaida())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data de retorno deve ser posterior à de saída.");
        }
    }

    public List<Viagem> listarPorEmpregado(String matricula) {
        return viagemRepository.findBySolicitanteMatricula(matricula);
    }

    public List<Viagem> listarPorStatus(String statusDescricao) {
        return viagemRepository.findBySituacaoDescricao(statusDescricao);
    }
}