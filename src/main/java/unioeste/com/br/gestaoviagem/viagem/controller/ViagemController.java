package unioeste.com.br.gestaoviagem.viagem.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.empregado.service.EmpregadoService;
import unioeste.com.br.gestaoviagem.historicostatusviagem.service.HistoricoStatusViagemService;
import unioeste.com.br.gestaoviagem.meiotransporte.domain.MeioTransporte;
import unioeste.com.br.gestaoviagem.meiotransporte.service.MeioTransporteService;
import unioeste.com.br.gestaoviagem.motivo.domain.Motivo;
import unioeste.com.br.gestaoviagem.motivo.service.MotivoService;
import unioeste.com.br.gestaoviagem.situacao.domain.Situacao;
import unioeste.com.br.gestaoviagem.situacao.service.SituacaoService;
import unioeste.com.br.gestaoviagem.viagem.domain.*;
import unioeste.com.br.gestaoviagem.viagem.service.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viagem")
@AllArgsConstructor
public class ViagemController {

    private final ViagemService viagemService;
    private final EmpregadoService empregadoService;
    private final MotivoService motivoService;
    private final MeioTransporteService transporteService;
    private final SituacaoService situacaoService;

    private final HistoricoStatusViagemService historicoService;

    @PostMapping
    public ResponseEntity<Viagem> criar(@Valid @RequestBody ViagemForm form, @AuthenticationPrincipal Empregado empregadoLogado) {
        Motivo motivo = motivoService.buscarPorId(form.getMotivoId());
        MeioTransporte transporte = transporteService.buscarPorId(form.getMeioTransporteId());
        Situacao situacaoInicial = situacaoService.buscarPorDescricao("Rascunho");

        Viagem novaViagem = viagemService.criar(form, empregadoLogado, motivo, transporte, situacaoInicial);

        historicoService.registrarHistorico(novaViagem, situacaoInicial, empregadoLogado, "Criação do rascunho");

        return ResponseEntity.status(HttpStatus.CREATED).body(novaViagem);
    }

    @PutMapping("/{numero}")
    public ResponseEntity<Viagem> atualizar(@PathVariable Long numero, @Valid @RequestBody ViagemForm form) {
        Viagem viagem = viagemService.buscarPorId(numero);
        Motivo motivo = motivoService.buscarPorId(form.getMotivoId());
        MeioTransporte transporte = transporteService.buscarPorId(form.getMeioTransporteId());

        Viagem viagemAtualizada = viagemService.atualizar(viagem, form, motivo, transporte);
        return ResponseEntity.ok(viagemAtualizada);
    }

    @PatchMapping("/{numero}/solicitar")
    public ResponseEntity<Void> solicitarViagem(@PathVariable Long numero, @AuthenticationPrincipal Empregado empregadoLogado) {
        Viagem viagem = viagemService.buscarPorId(numero);
        Situacao novaSituacao = situacaoService.buscarPorDescricao("Solicitada");

        Viagem viagemAtualizada = viagemService.alterarStatus(viagem, empregadoLogado, novaSituacao);
        historicoService.registrarHistorico(viagemAtualizada, novaSituacao, empregadoLogado, "Enviado para avaliação");

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{numero}/cancelar")
    public ResponseEntity<Void> cancelarViagem(@PathVariable Long numero, @RequestHeader("X-Empregado-Matricula") String matricula) {
        Viagem viagem = viagemService.buscarPorId(numero);
        Empregado empregado = empregadoService.buscarPorMatricula(matricula);
        Situacao novaSituacao = situacaoService.buscarPorDescricao("Cancelada");

        Viagem viagemAtualizada = viagemService.alterarStatus(viagem, empregado, novaSituacao);
        historicoService.registrarHistorico(viagemAtualizada, novaSituacao, empregado, "Cancelado pelo solicitante");

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{numero}/avaliar")
    public ResponseEntity<Void> avaliarViagem(
            @PathVariable Long numero,
            @RequestBody Map<String, String> payload,
            @AuthenticationPrincipal Empregado gestorLogado) {

        String acao = payload.get("acao");
        String comentario = payload.get("comentario");

        Viagem viagem = viagemService.buscarPorId(numero);
        Situacao novaSituacao = situacaoService.buscarPorDescricao(acao);

        Viagem viagemAtualizada = viagemService.alterarStatus(viagem, gestorLogado, novaSituacao);
        historicoService.registrarHistorico(viagemAtualizada, novaSituacao, gestorLogado, comentario);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Viagem>> listarTodas() {
        return ResponseEntity.ok(viagemService.listarTodas());
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Viagem> buscarPorId(@PathVariable Long numero) {
        return ResponseEntity.ok(viagemService.buscarPorId(numero));
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> deletar(@PathVariable Long numero) {
        Viagem viagem = viagemService.buscarPorId(numero);
        viagemService.deletar(viagem);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/empregado")
    public ResponseEntity<List<Viagem>> listarPorEmpregado(@AuthenticationPrincipal Empregado empregadoLogado) {
        return ResponseEntity.ok(viagemService.listarPorEmpregado(empregadoLogado.getMatricula()));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Viagem>> listarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(viagemService.listarPorStatus(status));
    }
}