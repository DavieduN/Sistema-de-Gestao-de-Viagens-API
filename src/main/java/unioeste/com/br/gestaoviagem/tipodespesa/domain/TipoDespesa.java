package unioeste.com.br.gestaoviagem.tipodespesa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipo_despesa")
@Getter
@Setter
public class TipoDespesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
}
