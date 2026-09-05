package unioeste.com.br.gestaoviagem.motivo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unioeste.com.br.gestaoviagem.motivo.domain.Motivo;
import unioeste.com.br.gestaoviagem.motivo.service.MotivoService;

import java.util.List;

@RestController
@RequestMapping("/motivo")
@AllArgsConstructor
public class MotivoController {

    private final MotivoService motivoService;

    @GetMapping
    public ResponseEntity<List<Motivo>> listarTodos() {
        return ResponseEntity.ok(motivoService.listarTodos());
    }
}
