package unioeste.com.br.gestaoviagem.tipodespesa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unioeste.com.br.gestaoviagem.tipodespesa.domain.TipoDespesa;
import unioeste.com.br.gestaoviagem.tipodespesa.service.TipoDespesaService;

import java.util.List;

@RestController
@RequestMapping("/tipo-despesa")
@AllArgsConstructor
public class TipoDespesaController {

    private final TipoDespesaService tipoDespesaService;

    @GetMapping
    public ResponseEntity<List<TipoDespesa>> listarTodos() {
        List<TipoDespesa> tipos = tipoDespesaService.listarTodos();
        return ResponseEntity.ok(tipos);
    }
}