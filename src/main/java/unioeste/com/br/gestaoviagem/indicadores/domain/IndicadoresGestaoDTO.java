package unioeste.com.br.gestaoviagem.indicadores.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class IndicadoresGestaoDTO {
    private Long quantidadeTotalViagens;
    private Long quantidadeViagensAprovadas;
    private Long quantidadeViagensRejeitadas;
    private BigDecimal valorTotalGasto;
    private String destinoMaisVisitado;
    private BigDecimal custoMedioPorViagem;
}