package unioeste.com.br.gestaoviagem.empregado.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unioeste.com.br.gestaoviagem.area.domain.Area;
import unioeste.com.br.gestaoviagem.area.service.AreaService;
import unioeste.com.br.gestaoviagem.cargo.domain.Cargo;
import unioeste.com.br.gestaoviagem.cargo.service.CargoService;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.empregado.domain.EmpregadoForm;
import unioeste.com.br.gestaoviagem.empregado.service.EmpregadoService;

import java.util.List;

@RestController
@RequestMapping("/empregado")
@AllArgsConstructor
public class EmpregadoController {
    private final EmpregadoService empregadoService;

    // Injeção das services de apoio para orquestração
    private final CargoService cargoService;
    private final AreaService areaService;

    @PostMapping
    public ResponseEntity<Empregado> criar(@Valid @RequestBody EmpregadoForm form) {
        Cargo cargo = cargoService.buscarPorId(form.getCargoId());
        Area area = areaService.buscarPorId(form.getAreaId());

        Empregado novoEmpregado = empregadoService.criar(form, cargo, area);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEmpregado);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Empregado> atualizar(
            @PathVariable String matricula,
            @Valid @RequestBody EmpregadoForm form) {

        Empregado empregado = empregadoService.buscarPorMatricula(matricula);
        Cargo cargo = cargoService.buscarPorId(form.getCargoId());
        Area area = areaService.buscarPorId(form.getAreaId());

        Empregado empregadoAtualizado = empregadoService.atualizar(empregado, form, cargo, area);
        return ResponseEntity.ok(empregadoAtualizado);
    }

    @GetMapping
    public ResponseEntity<List<Empregado>> listarTodos() {
        return ResponseEntity.ok(empregadoService.listarTodos());
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Empregado> buscarPorMatricula(@PathVariable String matricula) {
        return ResponseEntity.ok(empregadoService.buscarPorMatricula(matricula));
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletar(@PathVariable String matricula) {
        Empregado empregado = empregadoService.buscarPorMatricula(matricula);
        empregadoService.deletar(empregado);
        return ResponseEntity.noContent().build();
    }
}
