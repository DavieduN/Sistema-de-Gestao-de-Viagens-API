package unioeste.com.br.gestaoviagem.viagem.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ViagemForm {
    @NotBlank
    private String destino;
    @NotNull
    private LocalDate dataSaida;
    @NotNull
    private LocalDate dataRetorno;

    @NotNull(message = "O ID do motivo é obrigatório")
    private Integer motivoId;

    @NotNull(message = "O ID do meio de transporte é obrigatório")
    private Integer meioTransporteId;
}