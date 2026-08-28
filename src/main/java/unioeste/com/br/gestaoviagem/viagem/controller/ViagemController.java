package unioeste.com.br.gestaoviagem.viagem.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.empregado.service.EmpregadoService;
import unioeste.com.br.gestaoviagem.viagem.domain.Viagem;
import unioeste.com.br.gestaoviagem.viagem.domain.ViagemForm;
import unioeste.com.br.gestaoviagem.viagem.service.ViagemService;

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
}
