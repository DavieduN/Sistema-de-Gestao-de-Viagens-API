package unioeste.com.br.gestaoviagem.situacao.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unioeste.com.br.gestaoviagem.situacao.domain.Situacao;
import unioeste.com.br.gestaoviagem.situacao.repository.SituacaoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SituacaoService {

    private final SituacaoRepository situacaoRepository;

    public Situacao buscarPorDescricao(String descricao) {
        return situacaoRepository.findByDescricao(descricao)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Situação '" + descricao + "' não encontrada no sistema."
                ));
    }

    public List<Situacao> listarTodas() {
        return situacaoRepository.findAll();
    }
}