package unioeste.com.br.gestaoviagem.despesa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import unioeste.com.br.gestaoviagem.tipodespesa.domain.TipoDespesa;
import unioeste.com.br.gestaoviagem.viagem.domain.Viagem;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "despesa")
@Getter
@Setter
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataDespesa;
    private String descricao;
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "tipo_despesa_id")
    private TipoDespesa tipoDespesa;

    @ManyToOne
    @JoinColumn(name = "viagem_numero")
    private Viagem viagem;
}
