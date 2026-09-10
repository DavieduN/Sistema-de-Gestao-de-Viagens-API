package unioeste.com.br.gestaoviagem.tipodespesa.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unioeste.com.br.gestaoviagem.tipodespesa.domain.TipoDespesa;
import unioeste.com.br.gestaoviagem.tipodespesa.repository.TipoDespesaRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoDespesaService {

    private final TipoDespesaRepository tipoDespesaRepository;

    public TipoDespesa buscarPorId(Integer id) {
        return tipoDespesaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tipo de despesa não encontrado."
                ));
    }

    public List<TipoDespesa> listarTodos() {
        return tipoDespesaRepository.findAll();
    }
}
