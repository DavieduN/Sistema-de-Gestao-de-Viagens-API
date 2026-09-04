package unioeste.com.br.gestaoviagem.area.controller;

import unioeste.com.br.gestaoviagem.area.domain.Area;
import unioeste.com.br.gestaoviagem.area.domain.AreaForm;
import unioeste.com.br.gestaoviagem.area.service.AreaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/area")
@AllArgsConstructor
public class AreaController {
    private final AreaService areaService;

    @PostMapping
    public ResponseEntity<Area> criar(@Valid @RequestBody AreaForm form) {
        Area novaArea = areaService.criar(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaArea);
    }

    @GetMapping
    public ResponseEntity<List<Area>> listarTodas() {
        return ResponseEntity.ok(areaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(areaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Area> atualizar(@PathVariable Integer id, @Valid @RequestBody AreaForm form) {
        Area areaAtualizada = areaService.atualizar(id, form);
        return ResponseEntity.ok(areaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        areaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
