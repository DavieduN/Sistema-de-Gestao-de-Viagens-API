package unioeste.com.br.gestaoviagem.cargo.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unioeste.com.br.gestaoviagem.cargo.domain.Cargo;
import unioeste.com.br.gestaoviagem.cargo.domain.CargoForm;
import unioeste.com.br.gestaoviagem.cargo.service.CargoService;

import java.util.List;

@RestController
@RequestMapping("/cargo")
@AllArgsConstructor
public class CargoController {

    private final CargoService cargoService;

    @PostMapping
    public ResponseEntity<Cargo> criar(@Valid @RequestBody CargoForm form) {
        Cargo novoCargo = cargoService.criar(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCargo);
    }

    @GetMapping
    public ResponseEntity<List<Cargo>> listarTodos() {
        return ResponseEntity.ok(cargoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(cargoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargo> atualizar(@PathVariable Integer id, @Valid @RequestBody CargoForm form) {
        Cargo cargoAtualizado = cargoService.atualizar(id, form);
        return ResponseEntity.ok(cargoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        cargoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}