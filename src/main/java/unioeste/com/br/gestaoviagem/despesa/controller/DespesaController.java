package unioeste.com.br.gestaoviagem.despesa.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import unioeste.com.br.gestaoviagem.despesa.domain.Despesa;
import unioeste.com.br.gestaoviagem.despesa.domain.DespesaForm;
import unioeste.com.br.gestaoviagem.despesa.domain.ResumoFinanceiroDTO;
import unioeste.com.br.gestaoviagem.despesa.service.DespesaService;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.tipodespesa.domain.TipoDespesa;
import unioeste.com.br.gestaoviagem.tipodespesa.service.TipoDespesaService;
import unioeste.com.br.gestaoviagem.viagem.domain.Viagem;
import unioeste.com.br.gestaoviagem.viagem.service.ViagemService;

@RestController
@RequestMapping("/viagem/{numeroViagem}/despesa")
@AllArgsConstructor
public class DespesaController {

    private final DespesaService despesaService;
    private final ViagemService viagemService;
    private final TipoDespesaService tipoDespesaService;

    @PostMapping
    public ResponseEntity<Despesa> registrar(
            @PathVariable Long numeroViagem,
            @Valid @RequestBody DespesaForm form,
            @AuthenticationPrincipal Empregado usuarioLogado) {

        Viagem viagem = viagemService.buscarPorId(numeroViagem);
        TipoDespesa tipo = tipoDespesaService.buscarPorId(form.getTipoDespesaId());

        Despesa novaDespesa = despesaService.registrarDespesa(form, viagem, tipo, usuarioLogado);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaDespesa);
    }

    @GetMapping("/resumo")
    public ResponseEntity<ResumoFinanceiroDTO> obterResumo(
            @PathVariable Long numeroViagem,
            @AuthenticationPrincipal Empregado usuarioLogado) {

        Viagem viagem = viagemService.buscarPorId(numeroViagem);
        ResumoFinanceiroDTO resumo = despesaService.consultarResumo(viagem, usuarioLogado);

        return ResponseEntity.ok(resumo);
    }
}
