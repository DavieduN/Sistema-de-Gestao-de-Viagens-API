package unioeste.com.br.gestaoviagem.despesa.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DespesaForm {
    @NotNull(message = "A data da despesa é obrigatória")
    @PastOrPresent(message = "Não são aceitos lançamentos com datas futuras")
    private LocalDate dataDespesa;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "Despesas com valor igual ou inferior a zero não são aceitas")
    private BigDecimal valor;

    @NotNull(message = "O tipo de despesa é obrigatório")
    private Integer tipoDespesaId;
}