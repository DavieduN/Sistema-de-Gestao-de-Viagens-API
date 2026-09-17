package unioeste.com.br.gestaoviagem.despesa.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unioeste.com.br.gestaoviagem.despesa.domain.Despesa;
import unioeste.com.br.gestaoviagem.despesa.domain.DespesaForm;
import unioeste.com.br.gestaoviagem.despesa.domain.ResumoFinanceiroDTO;
import unioeste.com.br.gestaoviagem.despesa.repository.DespesaRepository;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.tipodespesa.domain.TipoDespesa;
import unioeste.com.br.gestaoviagem.viagem.domain.Viagem;
import unioeste.com.br.gestaoviagem.viagem.service.ViagemService;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class DespesaService {

    private final DespesaRepository despesaRepository;

    public Despesa registrarDespesa(DespesaForm form, Viagem viagem, TipoDespesa tipo, Empregado solicitanteLogado) {

        // Regra: Apenas registrar gastos em viagens criadas por ele
        if (!viagem.getSolicitante().getMatricula().equals(solicitanteLogado.getMatricula())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você só pode registrar despesas em suas próprias viagens.");
        }

        // Regra: Apenas viagens aprovadas podem receber despesas
        if (!viagem.getSituacao().getDescricao().equalsIgnoreCase("Aprovada")) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Despesas só podem ser registradas em viagens Aprovadas.");
        }

        Despesa despesa = new Despesa();
        despesa.setViagem(viagem);
        despesa.setTipoDespesa(tipo);
        despesa.setDataDespesa(form.getDataDespesa());
        despesa.setDescricao(form.getDescricao());
        despesa.setValor(form.getValor());

        return despesaRepository.save(despesa);
    }

    public ResumoFinanceiroDTO consultarResumo(Viagem viagem, Empregado usuarioLogado) {

        // Regra: Colaborador vê apenas o dele. Gestor pode ver de todos.
        boolean isDonoDaViagem = viagem.getSolicitante().getMatricula().equals(usuarioLogado.getMatricula());
        boolean isGestor = usuarioLogado.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_GESTOR"));

        if (!isDonoDaViagem && !isGestor) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para visualizar o resumo financeiro desta viagem.");
        }

        List<Despesa> despesas = despesaRepository.findByViagemNumero(viagem.getNumero());

        return new ResumoFinanceiroDTO(viagem, despesas);
    }

    public List<Despesa> listarPorViagem(Viagem viagem) {
        return despesaRepository.findByViagemNumero(viagem.getNumero());
    }

    public BigDecimal calcularTotalGastoGeral() {
        return despesaRepository.somarTotalGasto();
    }
}