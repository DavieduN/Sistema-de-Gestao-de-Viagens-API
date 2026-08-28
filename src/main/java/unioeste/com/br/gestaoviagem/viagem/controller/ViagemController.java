package unioeste.com.br.gestaoviagem.viagem.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.empregado.service.EmpregadoService;
import unioeste.com.br.gestaoviagem.viagem.domain.Viagem;
import unioeste.com.br.gestaoviagem.viagem.domain.ViagemForm;
import unioeste.com.br.gestaoviagem.viagem.service.ViagemService;

import java.util.List;

@RestController
@RequestMapping("/viagem")
@AllArgsConstructor
public class ViagemController {
    private final ViagemService viagemService;
    private final EmpregadoService empregadoService;

    @PostMapping
    public ResponseEntity<Viagem> criar(@Valid @RequestBody ViagemForm viagemForm) {
        Empregado empregado = empregadoService.buscarPorMatricula(viagemForm.getEmpregadoMatricula());
        Viagem novaViagem = viagemService.criar(viagemForm, empregado);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaViagem);
    }

    @GetMapping
    public ResponseEntity<List<Viagem>> listarTodas() {
        List<Viagem> viagens = viagemService.listarTodas();
        return ResponseEntity.ok(viagens);
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Viagem> buscarPorId(@PathVariable Long numero) {
        Viagem viagem = viagemService.buscarPorId(numero);
        return ResponseEntity.ok(viagem);
    }

    @PutMapping("/{numero}")
    public ResponseEntity<Viagem> atualizar(
            @PathVariable Long numero,
            @Valid @RequestBody ViagemForm viagemForm) {

        Viagem viagem = viagemService.buscarPorId(numero);
        Empregado empregado = empregadoService.buscarPorMatricula(viagemForm.getEmpregadoMatricula());
        Viagem viagemAtualizada = viagemService.atualizar(viagem, viagemForm, empregado);
        return ResponseEntity.ok(viagemAtualizada);
    }
}
