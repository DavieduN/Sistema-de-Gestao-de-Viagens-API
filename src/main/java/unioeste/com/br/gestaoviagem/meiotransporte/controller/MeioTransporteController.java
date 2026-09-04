package unioeste.com.br.gestaoviagem.meiotransporte.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unioeste.com.br.gestaoviagem.meiotransporte.domain.MeioTransporte;
import unioeste.com.br.gestaoviagem.meiotransporte.service.MeioTransporteService;

import java.util.List;

@RestController
@RequestMapping("/meio-transporte")
@AllArgsConstructor
public class MeioTransporteController {

    private final MeioTransporteService meioTransporteService;

    @GetMapping
    public ResponseEntity<List<MeioTransporte>> listarTodos() {
        return ResponseEntity.ok(meioTransporteService.listarTodos());
    }
}
