package unioeste.com.br.gestaoviagem.historicostatusviagem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unioeste.com.br.gestaoviagem.historicostatusviagem.domain.HistoricoStatusViagem;
import unioeste.com.br.gestaoviagem.historicostatusviagem.service.HistoricoStatusViagemService;
import unioeste.com.br.gestaoviagem.viagem.domain.Viagem;
import unioeste.com.br.gestaoviagem.viagem.service.ViagemService;

import java.util.List;

@RestController
@RequestMapping("/historico-viagem")
@AllArgsConstructor
public class HistoricoStatusViagemController {

    private final HistoricoStatusViagemService historicoService;
    private final ViagemService viagemService; // Usada para orquestração

    @GetMapping("/viagem/{numeroViagem}")
    public ResponseEntity<List<HistoricoStatusViagem>> listarHistoricoDaViagem(@PathVariable Long numeroViagem) {
        Viagem viagem = viagemService.buscarPorId(numeroViagem);

        List<HistoricoStatusViagem> historico = historicoService.listarPorViagem(viagem);

        return ResponseEntity.ok(historico);
    }
}
