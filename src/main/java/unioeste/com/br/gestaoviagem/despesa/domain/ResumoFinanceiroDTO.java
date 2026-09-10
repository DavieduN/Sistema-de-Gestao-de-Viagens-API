package unioeste.com.br.gestaoviagem.despesa.domain;

import lombok.Getter;
import lombok.Setter;
import unioeste.com.br.gestaoviagem.viagem.domain.Viagem;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ResumoFinanceiroDTO {
    private Long numeroViagem;
    private String destino;
    private BigDecimal totalGasto;
    private List<Despesa> despesas;

    public ResumoFinanceiroDTO(Viagem viagem, List<Despesa> despesas) {
        this.numeroViagem = viagem.getNumero();
        this.destino = viagem.getDestino();
        this.despesas = despesas;
        this.totalGasto = despesas.stream()
                .map(Despesa::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
