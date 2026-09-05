package unioeste.com.br.gestaoviagem.viagem.domain;


import jakarta.persistence.*;
import lombok.*;
import unioeste.com.br.gestaoviagem.area.domain.Area;
import unioeste.com.br.gestaoviagem.cargo.domain.Cargo;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.meiotransporte.domain.MeioTransporte;
import unioeste.com.br.gestaoviagem.motivo.domain.Motivo;
import unioeste.com.br.gestaoviagem.situacao.domain.Situacao;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "viagem")
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

    @ManyToOne
    @JoinColumn(name = "empregado_matricula", nullable = false)
    private Empregado solicitante;

    @ManyToOne
    @JoinColumn(name = "motivo_id", nullable = false)
    private Motivo motivo;

    @ManyToOne
    @JoinColumn(name = "meio_transporte_id", nullable = false)
    private MeioTransporte meioTransporte;

    @ManyToOne
    @JoinColumn(name = "situacao_id", nullable = false)
    private Situacao situacao;

    @ManyToOne
    @JoinColumn(name = "empregado_cargo_id")
    private Cargo cargoSnapshot;

    @ManyToOne
    @JoinColumn(name = "empregado_area_id")
    private Area areaSnapshot;
}
