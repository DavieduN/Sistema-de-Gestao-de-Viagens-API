package unioeste.com.br.gestaoviagem.empregado.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.empregado.service.EmpregadoService;

import java.util.List;

@RestController
@RequestMapping("/empregado")
@AllArgsConstructor
public class EmpregadoController {
    private final EmpregadoService empregadoService;

    @PostMapping
    public ResponseEntity<Empregado> criar(@RequestBody Empregado empregado) {
        Empregado novoEmpregado = empregadoService.criar(empregado);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEmpregado);
    }

    @GetMapping
    public ResponseEntity<List<Empregado>> listar() {
        List<Empregado> empregados = empregadoService.listarTodos();
        return ResponseEntity.ok(empregados);
    }
}
