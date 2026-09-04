package unioeste.com.br.gestaoviagem.historicostatusviagem.domain;

import jakarta.persistence.*;
import lombok.*;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.situacao.domain.Situacao;
import unioeste.com.br.gestaoviagem.viagem.domain.Viagem;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "historico_status_viagem")
public class HistoricoStatusViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "viagem_id", nullable = false)
    private Viagem viagem;

    @ManyToOne
    @JoinColumn(name = "situacao_id", nullable = false)
    private Situacao situacao;

    @ManyToOne
    @JoinColumn(name = "responsavel_matricula", nullable = false)
    private Empregado responsavel;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(columnDefinition = "TEXT")
    private String comentario;
}
