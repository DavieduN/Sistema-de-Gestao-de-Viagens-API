package unioeste.com.br.gestaoviagem.situacao.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unioeste.com.br.gestaoviagem.situacao.domain.Situacao;
import unioeste.com.br.gestaoviagem.situacao.service.SituacaoService;

import java.util.List;

@RestController
@RequestMapping("/situacao")
@AllArgsConstructor
public class SituacaoController {

    private final SituacaoService situacaoService;

    @GetMapping
    public ResponseEntity<List<Situacao>> listarTodas() {
        return ResponseEntity.ok(situacaoService.listarTodas());
    }
}
