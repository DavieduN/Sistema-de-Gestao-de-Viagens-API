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
    @NotBlank
    private String motivo;
    @NotBlank
    private String meioTransporte;
    @NotBlank
    private String empregadoMatricula;
}
