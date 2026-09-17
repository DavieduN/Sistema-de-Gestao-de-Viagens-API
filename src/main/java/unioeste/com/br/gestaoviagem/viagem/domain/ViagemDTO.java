package unioeste.com.br.gestaoviagem.viagem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unioeste.com.br.gestaoviagem.area.domain.Area;
import unioeste.com.br.gestaoviagem.cargo.domain.Cargo;
import unioeste.com.br.gestaoviagem.despesa.domain.Despesa;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.meiotransporte.domain.MeioTransporte;
import unioeste.com.br.gestaoviagem.motivo.domain.Motivo;
import unioeste.com.br.gestaoviagem.situacao.domain.Situacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ViagemDTO {
    private Long numero;
    private String destino;
    private LocalDate dataSaida;
    private LocalDate dataRetorno;
    private Empregado solicitante;
    private Motivo motivo;
    private MeioTransporte meioTransporte;
    private Situacao situacao;
    private Cargo cargoSnapshot;
    private Area areaSnapshot;
    private BigDecimal totalGasto;

    public ViagemDTO(Viagem viagem, List<Despesa> despesas) {
        this.numero = viagem.getNumero();
        this.destino = viagem.getDestino();
        this.dataSaida = viagem.getDataSaida();
        this.dataRetorno = viagem.getDataRetorno();
        this.solicitante = viagem.getSolicitante();
        this.motivo = viagem.getMotivo();
        this.meioTransporte = viagem.getMeioTransporte();
        this.situacao = viagem.getSituacao();
        this.cargoSnapshot = viagem.getCargoSnapshot();
        this.areaSnapshot = viagem.getAreaSnapshot();
        this.totalGasto = despesas.stream()
                .map(Despesa::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
