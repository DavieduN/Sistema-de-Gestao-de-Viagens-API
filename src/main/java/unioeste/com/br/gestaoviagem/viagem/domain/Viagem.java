package unioeste.com.br.gestaoviagem.viagem.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;

import java.time.LocalDate;

@Entity
@Table(name = "viagem")
@Getter @Setter
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    @Column(nullable = false)
    private String destino;

    @Column(name = "data_saida", nullable = false)
    private LocalDate dataSaida;

    @Column(name = "data_retorno", nullable = false)
    private LocalDate dataRetorno;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String motivo;

    @Column(name = "meio_transporte", nullable = false, length = 100)
    private String meioTransporte;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empregado_matricula", nullable = false)
    private Empregado empregado;
}
