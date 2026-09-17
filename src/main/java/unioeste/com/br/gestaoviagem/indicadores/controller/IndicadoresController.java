package unioeste.com.br.gestaoviagem.indicadores.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unioeste.com.br.gestaoviagem.despesa.service.DespesaService;
import unioeste.com.br.gestaoviagem.indicadores.domain.IndicadoresGestaoDTO;
import unioeste.com.br.gestaoviagem.viagem.service.ViagemService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/indicadores")
@AllArgsConstructor
public class IndicadoresController {

    private final ViagemService viagemService;
    private final DespesaService despesaService;

    @GetMapping
    public ResponseEntity<IndicadoresGestaoDTO> obterIndicadoresGlobais() {

        Long totalViagens = viagemService.contarTodas();
        Long viagensAprovadas = viagemService.contarPorSituacao("Aprovada");
        Long viagensRejeitadas = viagemService.contarPorSituacao("Rejeitada");

        BigDecimal totalGasto = despesaService.calcularTotalGastoGeral();
        String destinoMaisVisitado = viagemService.obterDestinoMaisVisitado();

        BigDecimal custoMedio = BigDecimal.ZERO;
        if (viagensAprovadas > 0 && totalGasto.compareTo(BigDecimal.ZERO) > 0) {
            custoMedio = totalGasto.divide(new BigDecimal(viagensAprovadas), 2, RoundingMode.HALF_UP);
        }

        IndicadoresGestaoDTO indicadores = new IndicadoresGestaoDTO(
                totalViagens,
                viagensAprovadas,
                viagensRejeitadas,
                totalGasto,
                destinoMaisVisitado != null ? destinoMaisVisitado : "Nenhum",
                custoMedio
        );

        return ResponseEntity.ok(indicadores);
    }
}
